package com.ecp.back.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.ecp.back.commons.SessionConstants;
import com.ecp.back.commons.StaticConstants;
import com.ecp.bean.UserBean;
import com.ecp.common.util.RequestResultUtil;
import com.ecp.entity.Brand;
import com.ecp.entity.Category;
import com.ecp.service.back.IAttributeService;
import com.ecp.service.back.IAttributeValueService;
import com.ecp.service.back.IBrandService;
import com.ecp.service.back.ICategoryService;

/**
 * Class: CategoryController 商品分类Controller类
 * 
 * @author srd
 * @version 1.0 $Date: 2017年5月7日 下午5:20:46
 */
@Controller
@RequestMapping("/back/category")
public class CategoryController {

	private final Logger log = Logger.getLogger(getClass());

	//@Autowired
	@Resource(name="categoryServiceBean")
	private ICategoryService iCategoryService;// 类目
	//@Autowired
	@Resource(name="brandServiceBean")
	private IBrandService iBrandService;// 品牌
	//@Autowired
	@Resource(name="attributeServiceBean")
	private IAttributeService iAttributeService;// 类目的属性
	@Autowired	
	private IAttributeValueService iAttributeValueService;// 类目的属性值

	/**
	 * 方法功能：查询列表
	 * 
	 * @param request
	 * @param response
	 * @param clickPageBtn
	 * @param pageBean
	 * @param pagehelperFun
	 * @return
	 */
	@RequestMapping("/selectItems")
	public ModelAndView selectLinkItems(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		UserBean user = (UserBean)subject.getPrincipal();

		List<Category> categoryList = iCategoryService.selectAll();
		log.info("List:" + categoryList);
		mav.addObject("categoryList", categoryList);
		// request.setAttribute("categoryList", categoryList);

		String categoryListJson = JSON.toJSONString(categoryList);
		log.info("category list json string:" + categoryListJson);
		mav.addObject("categoryListJson", categoryListJson);

		mav.setViewName(StaticConstants.CATEGORY_MANAGE_PAGE);

		return mav;
	}

	/**
	 * 根据父ID查询类目
	 * 
	 * @param request
	 * @param response
	 * @param pid
	 * @return
	 */
	@RequestMapping("/selectByPid")
	@ResponseBody
	public Map<String, Object> selectByPid(HttpServletRequest request, HttpServletResponse response, Long pid) {
		try {
			List<Category> categoryList = iCategoryService.selectByPid(pid);
			Map<String, Object> respM = RequestResultUtil.getResultSelectSuccess();
			respM.put("categoryList", categoryList);
			return respM;
		} catch (Exception e) {
			log.error("查询异常", e);
			return RequestResultUtil.getResultSelectWarn();
		}
	}

	/**
	 * 方法功能：查询要修改的信息
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectUpdateById")
	@ResponseBody
	public Map<String, Object> selectUpdateById(HttpServletRequest request, HttpServletResponse response, Integer id) {
		try {
			Category category = iCategoryService.selectByPrimaryKey(id);
			Map<String, Object> respM = RequestResultUtil.getResultSelectSuccess();
			respM.put("category", category);
			return respM;
		} catch (Exception e) {
			log.error("查询异常", e);
			return RequestResultUtil.getResultSelectWarn();
		}
	}

	/**
	 * 方法功能：添加
	 * 
	 * @param request
	 * @param response
	 * @param category
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String, Object> insertContent(HttpServletRequest request, HttpServletResponse response,
			Category category) {

		Subject subject = SecurityUtils.getSubject();
		UserBean userBean = (UserBean)subject.getPrincipal();
		if (userBean != null) {
			int rows = iCategoryService.insertSelective(category);
			if (rows > 0) {
				return RequestResultUtil.getResultAddSuccess();
			}
		}
		return RequestResultUtil.getResultAddWarn();
	}

	/**
	 * 方法功能：修改
	 * 
	 * @param request
	 * @param response
	 * @param category
	 * @return
	 */
	@RequestMapping("/updateById")
	@ResponseBody
	public Map<String, Object> updateById(HttpServletRequest request, HttpServletResponse response, Category category) {

		try {
			int rows = iCategoryService.updateByPrimaryKeySelective(category);
			if (rows > 0) {
				return RequestResultUtil.getResultUpdateSuccess();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return RequestResultUtil.getResultUpdateWarn();
	}

	/**
	 * 方法功能：删除
	 * 
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public Map<String, Object> deleteById(HttpServletRequest request, HttpServletResponse response, Long id) {
		int rows = iCategoryService.deleteById(id);
		if (rows > 0) {
			return RequestResultUtil.getResultDeleteSuccess();
		}
		return RequestResultUtil.getResultDeleteWarn();
	}

	/**
	 * 根据类目ID查询属性和属性值
	 * @param request
	 * @param response
	 * @param cid
	 * @return
	 */
	@RequestMapping("/selectAttrAndValue")
	public ModelAndView selectAttrAndValue(HttpServletRequest request, HttpServletResponse response,
			Long cid) {
		ModelAndView mav = new ModelAndView();
		// 品牌
		List<Map<String, Object>> brandMapList = iBrandService.getBrandByCategoryId(cid);
		mav.addObject("brandMapList", brandMapList);

		// 属性和属性值
		List<Map<String, Object>> attrList = iAttributeService.selectByCid(cid);
		for(Map<String, Object> map : attrList){
			String attr_id = map.get("attr_id").toString();
			if(StringUtils.isNotBlank(attr_id)){
				List<Map<String, Object>> valList = iAttributeValueService.selectByAttrId(Long.valueOf(attr_id));
				map.put("valList", valList);
			}
		}
		mav.addObject("attrList", attrList);
		mav.setViewName(StaticConstants.ATTR_PAGE);
		return mav;
	}

}
