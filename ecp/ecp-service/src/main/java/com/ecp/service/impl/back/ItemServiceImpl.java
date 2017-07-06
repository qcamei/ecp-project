package com.ecp.service.impl.back;

import java.io.IOException;
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

import com.alibaba.fastjson.JSONArray;
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
			//TODO 添加商品 item
			item.setHasPrice(1);
			int rows = itemMapper.insertSelective(item);
			if(rows>0){
				log.info("添加商品 item 成功");
			}
			//TODO 添加商品图片 item_picture
			ItemPicture picture = new ItemPicture(item.getItemId(), null);
			//处理上传缩略图
			if(!this.processUploadFile(request, picture)){
				return -1;
			}
			
			rows = itemPictureService.insertSelective(picture);
			if(rows>0){
				//TODO 添加商品属性值关系表
				rows = this.saveItemAttrValue(item.getItemId(), item.getAttrSale());
				if(rows>0){
					rows = this.processSkuRelate(item.getItemId(), picture.getPictureUrl(), skuJson, skuPriceJson);
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
	 * 处理SKU相关表
	 * @param itemId
	 * @param pictureUrl
	 * @param skuJson
	 * @param skuPriceJson
	 * @return
	 */
	@Transactional
	private int processSkuRelate(Long itemId, String pictureUrl, String skuJson, String skuPriceJson){
		try {
			int rows = 0;
			//TODO 添加sku item_sku
			List<Sku> skuList = JSONArray.parseArray(skuJson, Sku.class);
			int size = skuList.size();
			for(int i=0; i<size; i++){
				Sku sku = skuList.get(i);
				sku.setCreated(new Date());
				sku.setModified(new Date());
				sku.setItemId(itemId);
				sku.setSkuStatus(1);
				sku.setSkuType(1);
				rows = skuService.insertSelective(sku);
				if(rows>0){
					//TODO 添加sku价格 item_sku_price
					List<SkuPrice> skuPriceList = JSONArray.parseArray(skuPriceJson, SkuPrice.class);
					SkuPrice skuPrice = skuPriceList.get(i);
					skuPrice.setCreateTime(new Date());
					skuPrice.setUpdateTime(new Date());
					skuPrice.setItemId(itemId);
					skuPrice.setSkuId(sku.getSkuId());
					rows = skuPriceService.insertSelective(skuPrice);
					if(rows>0){
						//TODO 添加sku图片 item_sku_picture
						SkuPicture skuPicture = new SkuPicture();
						skuPicture.setCreated(new Date());
						skuPicture.setModified(new Date());
						skuPicture.setPictureStatus((byte)1);
						skuPicture.setSortNumber((byte)1);
						skuPicture.setPictureUrl(pictureUrl);
						skuPicture.setSkuId(sku.getSkuId());
						rows = skuPictureService.insertSelective(skuPicture);
						if(rows>0){
							continue;
						}else{
							log.error("添加SKU图片 item_sku_picture 异常");
						}
					}else{
						log.error("添加SKU价格 item_sku_price 异常");
					}
				}else{
					log.error("添加SKU item_sku_price 异常");
				}
			}
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("保存SKU相关数据异常", e);
		}
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
				Long valueId = Long.valueOf(attrSaleStr[0].toString());
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
	 * @param gwContent
	 * @return
	 * <hr>
	 * <b>描述：</b>
	 * <p>Description:方法功能详细说明</p> 
	 * <p>Version: 1.0</p>
	 * <p>Author: srd </p>
	 * <p>Date: 2017年1月11日 下午6:09:34</p>
	 */
	private boolean processUploadFile(HttpServletRequest request, ItemPicture picture){
		boolean flag = false;
		try {
			//获取上传背景图文件
			String backImgPath = FileUploadUtil.getFile2Upload(request, "item", "pictureImg");
			if(StringUtils.isNotBlank(backImgPath)){
				if(!FileUploadUtil.deleteFile(request, picture.getPictureUrl())){
					log.error("文件不存在或已删除 缩略图路径："+picture.getPictureUrl());
				}
				picture.setPictureUrl(backImgPath);
			}
			flag = true;
		} catch (IOException e) {
			log.error("上传文件异常", e);
		} catch (Exception e) {
			log.error("删除上传文件异常", e);
		}
		return flag;
	}

}
