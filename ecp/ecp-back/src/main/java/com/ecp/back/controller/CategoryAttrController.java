package com.ecp.back.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ecp.back.commons.StaticConstants;
import com.ecp.bean.CategoryAttrBean;
import com.ecp.bean.PageBean;
import com.ecp.bean.UserBean;
import com.ecp.entity.Category;
import com.ecp.entity.User;
import com.ecp.service.back.ICategoryAttrService;
import com.ecp.service.back.ICategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName CategoryAttrController
 * @Description 类目、属性、类目属性值 维护控制器
 * @author Administrator
 * @Date 2017年5月5日 下午4:34:20
 * @version 1.0.0
 */
@Controller
@RequestMapping("/back/attr")
public class CategoryAttrController {
	final String RESPONSE_THYMELEAF = "back/thymeleaf/";
	final String RESPONSE_JSP = "jsp/";

	//@Autowired
	@Resource(name="categoryServiceBean")
	ICategoryService categoryService;

	//@Autowired
	@Resource(name="categoryAttrServiceBean")
	ICategoryAttrService categoryAttrService;

	
	/**
	 * @Description 导航至--->类目树
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/categorytree", method = RequestMethod.GET)
	public ModelAndView categoryAttr(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		List<Map<String, Object>> categoryList = categoryService.getAllCategory();
		mav.addObject("categoryListJson", JSON.toJSONString(categoryList));
		mav.setViewName(StaticConstants.CATEGORY_ATTR_MANAGE_PAGE);
		return mav;
	}

	/**
	 * @Description 导根据类目ID查询类目属性列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getCategoryAttrItem")
	public ModelAndView categoryAttrEditor(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, String pagehelperFun, Long categoryId) {
		
		ModelAndView mav = new ModelAndView();
		Subject subject = SecurityUtils.getSubject();
		UserBean user = (UserBean)subject.getPrincipal();
		
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		// 查询相关叶子结点的属性
		List<CategoryAttrBean> cateAttrList = categoryAttrService.getCategoryAttrListByCid(categoryId);
		PageInfo<CategoryAttrBean> pagehelper = new PageInfo<CategoryAttrBean>(cateAttrList);
		
		
		mav.addObject("pagehelper", pagehelper);
		
		mav.setViewName(StaticConstants.CATEGORY_ATTR_MANAGE_TABLE_PAGE);
		
		mav.addObject("pagehelperFun", pagehelperFun);
		return mav;
	}

	/**
	 * @Description 导航至--->增加类目属性
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addcategoryattr/{cid}", method = RequestMethod.GET)
	public String addCategoryAttr(@PathVariable("cid") long cid, Model model) {

		// model.addAttribute("categoryAttrList",cateAttrList);
		model.addAttribute("cid", cid); // 传递cid信息

		return RESPONSE_THYMELEAF + "addcategoryattr";
	}

	/**
	 * @Description 保存-类目属性
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/savecategoryattr", method = RequestMethod.POST)
	public String saveCategoryAttr(CategoryAttrBean cateAttrBean, Model model) {

		System.out.println("debug-----cid=" + cateAttrBean.getCid());
		System.out.println("debug-----attr_type=" + cateAttrBean.getAttr_type());
		
		//TODO 在此处对数据的有效性验证  ADD VALIDATION CODE HERE
		categoryAttrService.saveCategoryAttr(cateAttrBean);  

		return RESPONSE_THYMELEAF + "savecategoryattrok";
	}
	
	
	/**
	 * @Description 导航--->属性值编辑页面中
	 * @param cid  类目ID
	 * @param attr_id  类目属性ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cate_attr_val_edit", method = RequestMethod.GET)
	public String categoryAttrValEdit(@RequestParam("cid") long cid,
									@RequestParam("attr_id") long attr_id,
										Model model) {

		 	 
		 //TODO   此处模拟
		 //以下语句为stub
		 //List<Map<String,String>> categoryAttrValList=new ArrayList<Map<String,String>>();
		 
		 //自服务层获取类目-属性-属性值 列表
		 List<Map<String,String>> categoryAttrValList=this.categoryAttrService.getCategoryAttrValList(cid, attr_id);
		 
		 model.addAttribute("categoryAttrValList",categoryAttrValList);
		 model.addAttribute("cid",cid);
		 model.addAttribute("attr_id",attr_id);

		return RESPONSE_THYMELEAF + "CategoryAttrValEdit";
	}

	
	/**
	 * 响应ajax请求
	 * 未用
	 * @Description 加载类目树数据
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value = "/loadcategorytree", method = RequestMethod.POST)
	@ResponseBody
	public Object LoadCategoryAttr(Model model) {

		List<Map<String, Object>> categoryList = categoryService.getAllCategory();

		return categoryList;
	}*/

		
	//saveCategoryAttrValue
	@RequestMapping(value = "/saveCategoryAttrValue", method = RequestMethod.POST)
	@ResponseBody
	public Object saveCategoryAttrValue(HttpServletRequest request, Model model) {

		String valueName = request.getParameter("valueName");
		long cid=Long.parseLong(request.getParameter("cid"));
		long attrId=Long.parseLong(request.getParameter("attrId"));
		
		//TODO save valueName into db
		categoryAttrService.saveCategoryAttrValue(cid, attrId, valueName);
		
		
		//返回状态数据
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "保存成功!");
		jsonObject.put("status", "success");

		return jsonObject;
	}


}
