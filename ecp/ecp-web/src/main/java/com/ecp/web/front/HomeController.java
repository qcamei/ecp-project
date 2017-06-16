package com.ecp.web.front;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ecp.bean.CategoryTreeNode;
import com.ecp.entity.Favourite;
import com.ecp.service.front.ICategoryAttrService;
import com.ecp.service.front.ICategoryService;

/**
 * @ClassName HomeController
 * @Description 前台主页控制器 （可用jsp及thymeleaf模板）
 * @author Administrator
 * @Date 2017年5月5日 下午4:34:20
 * @version 1.0.0
 */
@Controller
@RequestMapping("/front/home")
public class HomeController {
	final String RESPONSE_THYMELEAF = "thymeleaf/front/";
	final String RESPONSE_THYMELEAF_BACK = "thymeleaf/back/";
	final String RESPONSE_JSP = "jsps/";

	@Autowired
	ICategoryService categoryService;

	@Autowired
	ICategoryAttrService categoryAttrService;

	/**
	 * @Description 导航->主页 显示分类目录
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {

		// 此处模拟了自数据库读取类目信息并与前叶接口的情形。应该是自应用层读取类目信息，将功能向后端延伸。
		// 模拟数据

		/*
		 * ArrayList<CategoryTreeNode> categoryList = new
		 * ArrayList<CategoryTreeNode>(); //返回第一级类目结点列表
		 * 
		 * 
		 * //增加一级类目 for (int i = 1; i <=3; i++) { //一级类目 Category category1 =
		 * new Category(); category1.setCid((long) i); category1.setcName("类目" +
		 * i);
		 * 
		 * CategoryTreeNode categoryTreeNode1=new CategoryTreeNode(); //生成类目树结点
		 * ArrayList<CategoryTreeNode> subCategoryList2 = new
		 * ArrayList<CategoryTreeNode>(); //生成当前结点的子结点
		 * 
		 * categoryTreeNode1.setCategory(category1); //加入当前结点
		 * categoryTreeNode1.setSubCategoryList(subCategoryList2);
		 * 
		 * categoryList.add(categoryTreeNode1);
		 * 
		 * for(int j=1;j<=3;j++){ //二级类目 Category category2 = new Category();
		 * category2.setCid((long) i*10+j); category2.setcName("类目" + (i*10+j));
		 * 
		 * CategoryTreeNode categoryTreeNode2=new CategoryTreeNode(); //生成类目结点
		 * ArrayList<CategoryTreeNode> subCategoryList3 = new
		 * ArrayList<CategoryTreeNode>(); //生成当前结点的子结点
		 * 
		 * categoryTreeNode2.setCategory(category2); //加入当前结点
		 * categoryTreeNode2.setSubCategoryList(subCategoryList3);
		 * //设置当前结点的子结点列表
		 * 
		 * subCategoryList2.add(categoryTreeNode2);
		 * 
		 * for(int k=1;k<=3;k++){ Category category3 = new Category();
		 * category3.setCid((long) i*100+j*10+k); category3.setcName("类目" +
		 * (i*100+j*10+k));
		 * 
		 * CategoryTreeNode categoryTreeNode3=new CategoryTreeNode(); //生成类目树结点
		 * categoryTreeNode3.setCategory(category3);
		 * categoryTreeNode3.setSubCategoryList(null);
		 * 
		 * subCategoryList3.add(categoryTreeNode3); }
		 * 
		 * }
		 * 
		 * }
		 */

		List<CategoryTreeNode> categoryList = categoryService.getCategoryTree();

		model.addAttribute("categoryList", categoryList); // 返回类目树列表

		return RESPONSE_THYMELEAF + "home";
	}

	/** 
	 * @Description 显示购物车中的商品列表
	 * @param model
	 * @return
	 */
	//TODO 购物车路径单词拼写错误
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String chart(Model model) {
		ArrayList<Favourite> cartItems = new ArrayList<Favourite>();
		for (int i = 0; i < 3; i++) {
			Favourite cartItem = new Favourite();
			cartItem.setId(i);
			cartItems.add(cartItem);
		}

		model.addAttribute("cart", cartItems);

		return RESPONSE_THYMELEAF + "cart";
	}
	
	
	/**
	 * @Description 导航--->产品详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/product_detail", method = RequestMethod.GET)
	public String productDetail(Model model) {		

		return RESPONSE_THYMELEAF + "product_detail";
	}
	
	
	

	/**
	 * @Description ajaxtest  导航-->ajax测试页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ajax", method = RequestMethod.GET)
	public String ajaxTest(Model model) {

		return RESPONSE_THYMELEAF + "ajaxtest";
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(Model model) {

		//return RESPONSE_JSP + "hello";
		return RESPONSE_THYMELEAF + "ajaxtest";
	}

	@RequestMapping(value = "/ajaxpost", method = RequestMethod.POST)
	@ResponseBody
	public Object ajaxpost(HttpServletRequest request, Model model) {

		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");

		System.out.println("param1 is:" + loginName);
		System.out.println("param2 is:" + password);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "解锁成功");
		jsonObject.put("status", "success");

		return jsonObject;
	}
	
	
	
	
	
	/**
	 * @Description 导航->登录页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return RESPONSE_THYMELEAF + "login";
	}

	/**
	 * @Description 导航->注册页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		return RESPONSE_THYMELEAF + "register";
	}

	/**
	 * @Description退出登录
	 * @param model
	 * @return 导航到->主页面
	 */
	@RequestMapping(value = "/unRegister", method = RequestMethod.GET)
	public String unRegister(HttpServletRequest request) {
		request.getSession().invalidate();
		// return RESPONSE_THYMELEAF+"home";
		return "redirect:/front/home/login";
	}

	/**
	 * @Description 测试代码  用于测试jsp视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jsp", method = RequestMethod.GET)
	public String home1(Model model) {
		return RESPONSE_JSP + "hello";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
