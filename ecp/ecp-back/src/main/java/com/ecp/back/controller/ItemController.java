package com.ecp.back.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ecp.back.commons.SessionConstants;
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
import com.ecp.service.back.IAttributeValueService;
import com.ecp.service.back.IAttributeService;
import com.ecp.service.back.IBrandService;
import com.ecp.service.back.ICategoryService;
import com.ecp.service.back.IItemService;
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
	
	//@Autowired
	@Resource(name="itemServiceBean")
	private IItemService iItemService;//商品
	//@Autowired
	@Resource(name="brandServiceBean")
	private IBrandService iBrandService;//品牌
	//@Autowired
	@Resource(name="categoryServiceBean")
	private ICategoryService iCategoryService;//类目
	//@Autowired
	@Resource(name="attributeServiceBean")
	private IAttributeService iAttributeService;//类目的属性
	@Autowired
	private IAttributeValueService iAttributeValueService;//类目的属性值
	
	/**
	 * 方法功能：查询列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/selectItems")
	public ModelAndView selectLinkItem(HttpServletRequest request, HttpServletResponse response, Boolean clickPageBtn, PageBean pageBean, String pagehelperFun) {
		ModelAndView mav = new ModelAndView();
		UserBean user = (UserBean)request.getSession().getAttribute(SessionConstants.USER);
		
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
			Item item = iItemService.selectByPrimaryKey(id);
			Map<String, Object> respM = RequestResultUtil.getResultSelectSuccess();
			respM.put("item", item);
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
		
		UserBean userBean = (UserBean)request.getSession().getAttribute(SessionConstants.USER);
		
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
