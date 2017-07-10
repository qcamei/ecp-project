package com.ecp.web.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecp.bean.CartItemBean;
import com.ecp.bean.FavouriteBean;
import com.ecp.bean.FavouriteStatisticBean;
import com.ecp.bean.SkuPriceBean;
import com.ecp.common.SessionConstants;
import com.ecp.entity.Attribute;
import com.ecp.entity.AttributeValue;
import com.ecp.entity.Favourite;
import com.ecp.entity.Item;
import com.ecp.entity.Orders;
import com.ecp.entity.SkuPicture;
import com.ecp.entity.User;
import com.ecp.service.front.IAttrValueService;
import com.ecp.service.front.IAttributeService;
import com.ecp.service.front.ICartService;
import com.ecp.service.front.IFavouriteService;
import com.ecp.service.front.IItemService;
import com.ecp.service.front.IOrderItemService;
import com.ecp.service.front.IOrderService;
import com.ecp.service.front.ISkuPictureService;
import com.ecp.service.front.ISkuService;

/**
 * @ClassName PersonalCenterController
 * @Description 个人中心控制
 * @author Administrator
 * @Date 2017年5月8日 下午7:46:17
 * @version 1.0.0
 */
@Controller
@RequestMapping("/front/personalcenter")
public class PersonalCenterController {
	final String RESPONSE_THYMELEAF = "thymeleaf/front/";
	final String RESPONSE_JSP = "jsps/front/";

	@Autowired
	ICartService cartService;
	@Autowired
	IFavouriteService favouriteService;
	@Autowired
	IOrderService orderService;
	@Autowired
	IOrderItemService orderItemService;

	/**
	 * @Description 个人中心-我的订单
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public String orders(Model model, HttpServletRequest request) {
		// (1）获取订单信息
		// (2)获取订单下条目信息

		// 用于页面显示
		List<Map<String, Object>> orderList = new ArrayList<Map<String, Object>>();

		// 获取登录用户信息
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionConstants.USER);

		// 查询当前登录用户的订单
		List<Orders> orders = orderService.selectOrderByUserId(user.getId());
		for (Orders order : orders) { // 迭代订单，查询订单条目
			
			Map<String,Object> orderMap=new HashMap<String,Object>();			
			orderMap.put("order", order);
			
			// 读取订单条目数据
			List<Map<String,String>> orderItems = orderItemService.selectItemsByOrderId(order.getOrderId());	
			//System.out.println("查询到的条目有："+orderItems.size());
			
			orderMap.put("orderItems",orderItems);
			
			orderList.add(orderMap);  //加入列表中
			
		}

		model.addAttribute("orderList", orderList);

		return RESPONSE_THYMELEAF + "my_orders";
	}

	/**
	 * 
	 * @Description 个人中心-我的收藏
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/favourites", method = RequestMethod.GET)
	public String favourites(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionConstants.USER);

		List<FavouriteBean> favouriteItems = favouriteService.getFavouritesByUserId(user.getId());
		List<FavouriteStatisticBean> statisticInfos = favouriteService.getFavouriteStatistic(user.getId());

		model.addAttribute("statisticInfos", statisticInfos);
		model.addAttribute("favouriteItems", favouriteItems);

		return RESPONSE_THYMELEAF + "my_favourites";
	}

	@Autowired
	IItemService itemService;
	@Autowired
	ISkuService skuService;
	@Autowired
	ISkuPictureService skuPictureService;
	@Autowired
	IAttrValueService attrValueService;
	@Autowired
	IAttributeService attriubteService;

	/**
	 * 
	 * @Description 导航至-个人中心：我的购物车
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/cart")
	public String cart(Integer cartItemId,Model model, HttpServletRequest request) {

		// 自session读取用户信息
		// 如果用户在此处没有登录时
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionConstants.USER);
		long userId = user.getId();

		model.addAttribute("cartItemId", cartItemId);  //需要自动选定的cart item

		return RESPONSE_THYMELEAF + "my_cart";
	}

}
