package com.ecp.back.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ecp.back.commons.StaticConstants;
import com.ecp.bean.PageBean;
import com.ecp.bean.UserBean;
import com.ecp.common.util.FileUploadUtil;
import com.ecp.common.util.RequestResultUtil;
import com.ecp.entity.Attribute;
import com.ecp.entity.AttributeValue;
import com.ecp.entity.Brand;
import com.ecp.entity.Category;
import com.ecp.entity.Item;
import com.ecp.entity.ItemPicture;
import com.ecp.entity.Sku;
import com.ecp.entity.SkuPicture;
import com.ecp.entity.SkuPrice;
import com.ecp.service.back.IAttributeService;
import com.ecp.service.back.IAttributeValueService;
import com.ecp.service.back.IBrandService;
import com.ecp.service.back.ICategoryService;
import com.ecp.service.back.IItemPictureService;
import com.ecp.service.back.IItemService;
import com.ecp.service.back.ISkuPictureService;
import com.ecp.service.back.ISkuPriceService;
import com.ecp.service.back.ISkuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Class: ItemController
 * 		商品Controller类
 * @author srd 
 * @version 1.0 $Date: 2017年5月7日 下午5:16:02
 */
@Controller
@RequestMapping("/back/item")
public class ItemController {

	private final Logger log = Logger.getLogger(getClass());
	
	@Resource(name="itemServiceBean")
	private IItemService iItemService;//商品
	@Resource(name="itemPictureServiceBean")
	private IItemPictureService itemPictureService;//商品图片
	
	@Resource(name="skuServiceBean")
	private ISkuService skuService;//SKU
	@Resource(name="skuPictureServiceBean")
	private ISkuPictureService skuPictureService;//SKU图片
	@Resource(name="skuPriceServiceBean")
	private ISkuPriceService skuPriceService;//SKU价格
	
	@Resource(name="brandServiceBean")
	private IBrandService iBrandService;//品牌
	@Resource(name="categoryServiceBean")
	private ICategoryService iCategoryService;//类目
	@Resource(name="attributeServiceBean")
	private IAttributeService iAttributeService;//类目的属性
	@Resource(name="attributeValueServiceBean")
	private IAttributeValueService iAttributeValueService;//类目的属性值
	
	/**
	 * 方法功能：添加商品
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addItem")
	public ModelAndView addItem(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		UserBean user = (UserBean)subject.getPrincipal();
		
		//类目
		List<Category> categoryList = iCategoryService.selectByPid(0l);
		mav.addObject("categoryList", categoryList);
		
		mav.setViewName(StaticConstants.ADD_ITEM_PAGE);
		return mav;
	}
	
	/**
	 * 方法功能：查询列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/selectItems")
	public ModelAndView selectLinkItem(HttpServletRequest request, HttpServletResponse response, Boolean clickPageBtn, PageBean pageBean, String pagehelperFun) {
		ModelAndView mav = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		UserBean user = (UserBean)subject.getPrincipal();
		
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<Map<String, Object>> itemList = iItemService.selectItemsByCondition(null);
		PageInfo<Map<String, Object>> pagehelper = new PageInfo<Map<String, Object>>(itemList);
		mav.addObject("pagehelper", pagehelper);
		
		//品牌
		List<Brand> brandList = iBrandService.selectAll();
		mav.addObject("brandList", brandList);
		
		//类目
		List<Category> categoryList = iCategoryService.selectByPid(0l);
		mav.addObject("categoryList", categoryList);
		
		//属性
		List<Attribute> attrList = iAttributeService.selectAll();
		mav.addObject("attrList", attrList);
		
		//属性值
		List<AttributeValue> attrValueList = iAttributeValueService.selectAll();
		mav.addObject("attrValueList", attrValueList);
		
		if(clickPageBtn!=null && clickPageBtn){
			mav.setViewName(StaticConstants.ITEM_MANAGE_TABLE_PAGE);
		}else{
			mav.setViewName(StaticConstants.ITEM_MANAGE_PAGE);
		}
		
		mav.addObject("pagehelperFun", pagehelperFun);
		return mav;
	}
	
	/**
	 * 方法功能：查询要修改的信息
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectUpdateById")
	@ResponseBody
	public Map<String, Object> selectUpdateById(HttpServletRequest request, HttpServletResponse response, Long id) {
		try {
			//查询商品信息
			Item item = iItemService.selectByPrimaryKey(id);
			//查询商品图片信息
			List<ItemPicture> pictureList = itemPictureService.getByItemId(id);
			
			//查询sku信息
			List<Sku> skuList = skuService.getByItemId(id);
			//查询sku价格信息
			List<SkuPrice> skuPirceList = skuPriceService.getByItemId(id);
			//查询sku图片信息
			List<Long> skuIds = new ArrayList<Long>();
			for(Sku sku : skuList){
				skuIds.add(sku.getSkuId());
			}
			List<SkuPicture> skuPictureList = skuPictureService.getBySkuIds(skuIds);
			
			Map<String, Object> respM = RequestResultUtil.getResultSelectSuccess();
			respM.put("item", item);
			respM.put("pictureList", pictureList);
			respM.put("skuList", skuList);
			respM.put("skuPirceList", skuPirceList);
			respM.put("skuPictureList", skuPictureList);
			return respM;
		} catch (Exception e) {
			log.error("查询异常", e);
			return RequestResultUtil.getResultSelectWarn();
		}
	}
	
	/**
	 * 方法功能：添加
	 * @param request
	 * @param response
	 * @param item
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String, Object> insertContent(HttpServletRequest request, HttpServletResponse response, Item item, String skuJson, String skuPriceJson) {
		
		Subject subject = SecurityUtils.getSubject();
		UserBean userBean = (UserBean)subject.getPrincipal();
		
		log.info("insert item:"+item);
		
		if(userBean!=null){
			int rows = iItemService.saveItem(request, item, skuJson, skuPriceJson);
			if(rows>0){
				return RequestResultUtil.getResultAddSuccess();
			}else if(rows<0){
				return RequestResultUtil.getResultUploadWarn();
			}
		}
		return RequestResultUtil.getResultAddWarn();
	}
	
	/**
	 * 方法功能：修改
	 * @param request
	 * @param response
	 * @param item
	 * @return
	 */
	@RequestMapping("/updateById")
	@ResponseBody
	public Map<String, Object> updateById(HttpServletRequest request, HttpServletResponse response, Item item) {
		log.info("update item:"+item);
		try {
			int rows = iItemService.updateByPrimaryKeySelective(item);
			if(rows>0){
				return RequestResultUtil.getResultUpdateSuccess();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return RequestResultUtil.getResultUpdateWarn();
	}
	
	/**
	 * 方法功能：删除
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public Map<String, Object> deleteById(HttpServletRequest request, HttpServletResponse response, Long id) {
		int rows = iItemService.deleteByPrimaryKey(id);
		if(rows>0){
			return RequestResultUtil.getResultDeleteSuccess();
		}
		return RequestResultUtil.getResultDeleteWarn();
	}
	
	/**
	 * 方法功能：批量删除
	 * @param request
	 * @param response
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteByIds")
	@ResponseBody
	public Map<String, Object> deleteByIds(HttpServletRequest request, HttpServletResponse response, String ids) {
		int rows = iItemService.deleteByIds(ids);
		if(rows>0){
			return RequestResultUtil.getResultDeleteSuccess();
		}
		return RequestResultUtil.getResultDeleteWarn();
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
