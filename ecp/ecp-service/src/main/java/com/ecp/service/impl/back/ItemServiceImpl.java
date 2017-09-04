package com.ecp.service.impl.back;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ecp.bean.ItemStatusType;
import com.ecp.common.util.FileUploadUtil;
import com.ecp.dao.ItemMapper;
import com.ecp.entity.CategoryAttr;
import com.ecp.entity.CategoryAttrValue;
import com.ecp.entity.Item;
import com.ecp.entity.ItemAttrValue;
import com.ecp.entity.ItemPicture;
import com.ecp.entity.Sku;
import com.ecp.entity.SkuPicture;
import com.ecp.entity.SkuPrice;
import com.ecp.service.back.ICategoryAttrService;
import com.ecp.service.back.ICategoryAttrValueService;
import com.ecp.service.back.IItemAttrValueService;
import com.ecp.service.back.IItemPictureService;
import com.ecp.service.back.IItemService;
import com.ecp.service.back.ISkuPictureService;
import com.ecp.service.back.ISkuPriceService;
import com.ecp.service.back.ISkuService;
import com.ecp.service.commons.DefaultConstants;
import com.ecp.service.impl.AbstractBaseService;

@Service("itemServiceBean")
public class ItemServiceImpl extends AbstractBaseService<Item, Long> implements IItemService {

	private static final Logger log = Logger.getLogger(ItemServiceImpl.class);
	
	private ItemMapper itemMapper;
	
	/**
	 * @param itemMapper the itemMapper to set
	 * set方式注入
	 */
	public void setItemMapper(ItemMapper itemMapper) {
		this.itemMapper = itemMapper;
		this.setMapper(itemMapper);
	}
	
	@Resource(name="itemPictureServiceBean")
	private IItemPictureService itemPictureService;//商品图片
	
	@Resource(name="skuServiceBean")
	private ISkuService skuService;//SKU
	@Resource(name="skuPictureServiceBean")
	private ISkuPictureService skuPictureService;//SKU图片
	@Resource(name="skuPriceServiceBean")
	private ISkuPriceService skuPriceService;//SKU价格
	
	@Resource(name="categoryAttrServiceBean")
	private ICategoryAttrService categoryAttrService;//类目属性
	@Resource(name="categoryAttrValueServiceBean")
	private ICategoryAttrValueService categoryAttrValueService;//类目属性值
	
	@Resource(name="itemAttrValueServiceBean")
	private IItemAttrValueService itemAttrValueService;//商品-属性值关系
	
	/** 
	 * (non-Javadoc)
	 * @see com.ecp.service.IProductService#selectItemsByCondition(java.util.Map)
	 * 根据条件查询列表（条件为空时查询所有）
	 */
	@Override
	public List<Map<String, Object>> selectItemsByCondition(Map<String, Object> map) {
		return itemMapper.selectItemsByCondition(map);
	}

	/** 
	 * (non-Javadoc)
	 * @see com.ecp.service.IProductService#deleteByIds(java.lang.String)
	 * 批量删除
	 */
	@Override
	public int deleteByIds(String ids) {
		String[] idArr = ids.split(",");
		int rows = 0;
		for(int i=0; i<idArr.length; i++){
			rows = itemMapper.deleteByPrimaryKey(idArr[i]);
			if(rows<=0){
				log.error("删除 id 为 "+idArr[i]+" 的商品信息异常，回滚sql");
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return 0;
			}
		}
		return rows;
	}

	/**
	 * @see com.ecp.service.back.IItemService#saveItem(com.ecp.entity.Item)
	 * 保存商品
	 */
	@Override
	@Transactional
	public int saveItem(HttpServletRequest request, Item item, String skuJson, String skuPriceJson) {
		
		try {
			//添加商品 item
			item.setHasPrice(1);
			item.setItemStatus(ItemStatusType.IS_PUT_OFF_SHELVES);
			
			int rows = itemMapper.insertSelective(item);
			if(rows>0){
				log.info("添加商品 item 成功");
			}
			//TODO 添加商品图片 item_picture
			//处理上传缩略图
			List<String> filePathList = this.processUploadFile(request, new ArrayList<ItemPicture>());
			//如果上传图片为空时，添加默认图片
			if(filePathList!=null && filePathList.size()>0){
				
			}else{
				//TODO 此处添加默认图片
				filePathList = new ArrayList<String>();
				filePathList.add(DefaultConstants.DEFAULT_PICTURE_PATH);
			}
			
			//根据上传图片数量创建商品图片List集合
			List<ItemPicture> pictureList = new ArrayList<ItemPicture>();
			for(String filePath : filePathList){
				pictureList.add(new ItemPicture(item.getItemId(), filePath));
			}
			
			//循环商品图片List集合，添加商品图片信息
			for(int i=0; i<pictureList.size(); i++){
				ItemPicture picture = pictureList.get(i);
				//添加商品图片
				rows = itemPictureService.insertSelective(picture);
			}
			
			if(rows>0){
				//添加商品属性值关系表
				if(StringUtils.isNotBlank(item.getAttrSale())){
					//TODO 如果销售属性为空时，是否添加？
					rows = this.saveItemAttrValue(item.getItemId(), item.getAttrSale());
				}
				if(rows>0){
					//添加SKU信息
					if(StringUtils.isBlank(skuJson) || skuJson.equals("[]")){
						//TODO 如果没有SKU信息时，手动创建一个SKU，此处添加默认SKU；注：商品需要有默认图片和默认SKU
						List<Sku> skuList = new ArrayList<Sku>();
						Sku sku = new Sku();
						sku.setVolume(item.getVolume());
						sku.setWeight(item.getWeight());
						skuList.add(sku);
						skuJson = JSON.toJSONString(skuList);
						List<SkuPrice> skuPriceList = new ArrayList<SkuPrice>();
						SkuPrice skuPrice = new SkuPrice();
						skuPrice.setCostPrice(item.getMarketPrice2());
						skuPrice.setMarketPrice(item.getMarketPrice());
						skuPrice.setSellPrice(item.getMarketPrice());
						skuPriceList.add(skuPrice);
						skuPriceJson = JSON.toJSONString(skuPriceList);
					}
					rows = this.processSkuRelate(item, filePathList, skuJson, skuPriceJson);
					if(rows>0){
						return rows;
					}else{
						log.error("保存SKU相关数据异常");
					}
				}else{
					log.error("保存商品属性值关系 item_attr_value_item 异常");
				}
			}else{
				log.error("添加商品图片 item_picture 异常");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存商品异常，事务回滚！", e);
		}
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return 0;
	}
	

	/**
	 * @see com.ecp.service.back.IItemService#updateItem(javax.servlet.http.HttpServletRequest, com.ecp.entity.Item, java.lang.String, java.lang.String)
	 * 修改商品
	 */
	@Override
	@Transactional
	public int updateItem(HttpServletRequest request, Item item, String skuJson, String skuPriceJson) {
		try {
			if(item.getItemId()==null || item.getItemId()<=0){
				return 0;
			}
			//修改商品 item
			int rows = itemMapper.updateByPrimaryKeySelective(item);
			if(rows>0){
				log.info("修改商品 item 成功");
			}
			//TODO 修改商品图片 item_picture
			//处理上传缩略图
			List<String> filePathList = this.processUploadFile(request, new ArrayList<ItemPicture>());
			//如果上传图片为空时，添加默认图片
			if(filePathList!=null && filePathList.size()>0){
				
				//根据上传图片数量创建商品图片List集合
				List<ItemPicture> pictureList = new ArrayList<ItemPicture>();
				for(String filePath : filePathList){
					pictureList.add(new ItemPicture(item.getItemId(), filePath));
				}
				//删除原来的图片数据
				//TODO 修改时如果未选择上传图片，则图片还是原来的图片，不修改
				List<ItemPicture> pictList = itemPictureService.getByItemId(item.getItemId());
				for(int i=0; i<pictList.size(); i++){
					ItemPicture pict = pictList.get(i);
					FileUploadUtil.deleteFile(request, pict.getPictureUrl());
				}
				itemPictureService.deleteByItemId(item.getItemId());
				
				//循环商品图片List集合，添加商品图片信息
				for(int i=0; i<pictureList.size(); i++){
					ItemPicture picture = pictureList.get(i);
					//添加商品图片
					rows = itemPictureService.insertSelective(picture);
				}
			}else{
				filePathList = new ArrayList<String>();
				//TODO 修改时如果未选择上传图片，则图片还是原来的图片，不修改
				List<ItemPicture> pictureList = itemPictureService.getByItemId(item.getItemId());
				//循环商品图片List集，获取商品图片信息并添加到缩略图集合（filePathList）
				for(int i=0; i<pictureList.size(); i++){
					ItemPicture picture = pictureList.get(i);
					//商品图片不为空则添加到缩略图集合
					if(StringUtils.isNotBlank(picture.getPictureUrl())){
						filePathList.add(picture.getPictureUrl());
					}
				}
			}
			
			if(rows>0){
				//修改商品属性值关系表
				//添加新商品属性值关系之前先删除该商品对应原来数据
				itemAttrValueService.deleteByItemId(item.getItemId());
				
				//添加新商品属性值关系
				if(StringUtils.isNotBlank(item.getAttrSale())){
					rows = this.saveItemAttrValue(item.getItemId(), item.getAttrSale());
				}else{
					//TODO 如果销售属性为空时，是否添加？如何添加？
				}
				if(rows>0){
					//修改SKU相关信息
					//添加新SKU相关信息前先删除该商品对应的SKU相关信息
					List<Long> skuIds = new ArrayList<Long>();
					List<Sku> skuListTemp = skuService.getByItemId(item.getItemId());
					for(Sku sku : skuListTemp){
						skuIds.add(sku.getItemId());
					}
					if(skuIds!=null && skuIds.size()>0){
						skuService.deleteByItemId(item.getItemId());//删除SKU信息
						skuPriceService.deleteByItemId(item.getItemId());//删除SKU价格信息
						skuPictureService.deleteBySkuIds(skuIds);//删除SKU图片信息
					}
					
					//添加新SKU相关信息
					if(StringUtils.isBlank(skuJson) || skuJson.equals("[]")){
						//TODO 如果没有SKU信息时，手动创建一个SKU，此处添加默认SKU；注：商品需要有默认图片和默认SKU
						List<Sku> skuList = new ArrayList<Sku>();
						Sku sku = new Sku();
						sku.setVolume(item.getVolume());
						sku.setWeight(item.getWeight());
						skuList.add(sku);
						skuJson = JSON.toJSONString(skuList);
						List<SkuPrice> skuPriceList = new ArrayList<SkuPrice>();
						SkuPrice skuPrice = new SkuPrice();
						skuPrice.setCostPrice(item.getMarketPrice2());
						skuPrice.setMarketPrice(item.getMarketPrice());
						skuPrice.setSellPrice(item.getMarketPrice());
						skuPriceList.add(skuPrice);
						skuPriceJson = JSON.toJSONString(skuPriceList);
					}
					rows = this.processSkuRelate(item, filePathList, skuJson, skuPriceJson);
					if(rows>0){
						return rows;
					}else{
						log.error("修改SKU相关数据异常");
					}
				}else{
					log.error("修改商品属性值关系 item_attr_value_item 异常");
				}
			}else{
				log.error("修改商品图片 item_picture 异常");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("修改商品异常，事务回滚！", e);
		}
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return 0;
	}
	
	/**
	 * 处理SKU相关表
	 * @param itemId
	 * @param pictureUrl
	 * @param skuJson
	 * @param skuPriceJson
	 * @return
	 */
	@Transactional
	private int processSkuRelate(Item item, List<String> filePathList, String skuJson, String skuPriceJson){
		try {
			int rows = 0;
			//添加sku item_sku
			List<Sku> skuList = JSONArray.parseArray(skuJson, Sku.class);
			int size = skuList.size();
			for(int i=0; i<size; i++){
				Sku sku = skuList.get(i);
				sku.setCreated(new Date());
				sku.setModified(new Date());
				sku.setItemId(item.getItemId());
				sku.setSkuStatus(1);
				sku.setSkuType(1);
				rows = skuService.insertSelective(sku);
				if(rows>0){
					//添加sku价格 trade_sku_price
					List<SkuPrice> skuPriceList = JSONArray.parseArray(skuPriceJson, SkuPrice.class);
					SkuPrice skuPrice = skuPriceList.get(i);
					skuPrice.setCreateTime(new Date());
					skuPrice.setUpdateTime(new Date());
					skuPrice.setItemId(item.getItemId());
					skuPrice.setSkuId(sku.getSkuId());
					rows = skuPriceService.insertSelective(skuPrice);
					if(rows>0){
						//添加sku图片 item_sku_picture
						if(filePathList!=null && filePathList.size()>0){
							for(String filePath : filePathList){
								SkuPicture skuPicture = new SkuPicture();
								skuPrice.setMarketPrice(item.getMarketPrice());
								skuPicture.setCreated(new Date());
								skuPicture.setModified(new Date());
								skuPicture.setPictureStatus((byte)1);
								skuPicture.setSortNumber((byte)1);
								skuPicture.setPictureUrl(filePath);
								skuPicture.setSkuId(sku.getSkuId());
								rows = skuPictureService.insertSelective(skuPicture);
								if(rows>0){
									continue;
								}else{
									log.error("循环添加SKU图片 item_sku_picture 异常");
									break;
								}
							}
						}
						if(rows>0){
							continue;
						}else{
							log.error("添加SKU图片 item_sku_picture 异常");
							break;
						}
					}else{
						log.error("添加SKU价格 item_sku_price 异常");
						break;
					}
				}else{
					log.error("添加SKU item_sku_price 异常");
					break;
				}
			}
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存SKU相关数据异常", e);
		}
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return 0;
	}
	
	/**
	 * 保存类目属性值关系
	 * @param itemId
	 * @param attrSale
	 * @return
	 */
	@Transactional
	private int saveItemAttrValue(Long itemId, String attrSale){
		
		try {
			
			int rows = 0;
			String[] attrSaleArrStr = attrSale.split(",");
			for(int i=0; i<attrSaleArrStr.length; i++){
				String str = attrSaleArrStr[i];
				String[] attrSaleStr = str.split(":");
				Long attrId = Long.valueOf(attrSaleStr[0].toString());
				Long valueId = Long.valueOf(attrSaleStr[1].toString());
				CategoryAttr attr = categoryAttrService.getByAttrId(attrId);
				CategoryAttrValue value = categoryAttrValueService.getByValueId(valueId);
				ItemAttrValue temp = new ItemAttrValue();
				temp.setAttrId(attrId);
				temp.setAttrType(attr.getAttrType().intValue());
				temp.setCreated(new Date());
				temp.setItemId(itemId);
				temp.setModified(new Date());
				temp.setSortNumber(value.getSortNumber().intValue());
				temp.setStatus(value.getStatus().intValue());
				temp.setValueId(valueId);
				rows = itemAttrValueService.insertSelective(temp);
			}
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存类目属性值关系异常", e);
		}
		return 0;
	}
	
	/**
	 * 方法功能：处理上传文件
	 * @param request
	 * @param 
	 * @return
	 * 		List<String>:上传成功返回路径集合
	 * <hr>
	 * <b>描述：</b>
	 * <p>Description:方法功能详细说明</p> 
	 * <p>Version: 1.0</p>
	 * <p>Author: srd </p>
	 * <p>Date: 2017年1月11日 下午6:09:34</p>
	 */
	private List<String> processUploadFile(HttpServletRequest request, List<ItemPicture> pictureList){
		try {
			//获取上传背景图文件
			List<String> filePathList = FileUploadUtil.getFiles2UploadPath(request, "item", "pictureImg");
			if(filePathList!=null && filePathList.size()>0){
				if(pictureList!=null && pictureList.size()>0){
					for(ItemPicture picture : pictureList){
						if(!FileUploadUtil.deleteFile(request, picture.getPictureUrl())){
							log.error("文件不存在或已删除 缩略图路径："+picture.getPictureUrl());
						}
					}
				}
				return filePathList;
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			log.info("上传文件为空，返回null");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("上传文件异常，返回null");
		}
		
		return null;
	}
	
	/**
	 * 修改商品状态
	 * @param itemId
	 * @param itemStatus
	 * @return
	 */
	public int updateStatusById(Long itemId, Integer itemStatus){
		Item item = new Item();
		item.setItemId(itemId);
		item.setItemStatus(itemStatus);
		return itemMapper.updateByPrimaryKeySelective(item);
	}
	
	public static void main(String[] args) {
		for(int j=0; j<10; j++){
			System.out.println("外循环 第 "+j+" 次循环。");
			for(int i=0; i<10; i++){
				System.out.println("内循环 第 "+i+" 次循环。");
				if(i==5){
					break;
				}
			}
			if(j==3){
				break;
			}
		}
	}

}
