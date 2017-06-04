package com.ecp.web.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ecp.bean.AddSkuToOrderBean;
import com.ecp.common.SessionConstants;
import com.ecp.common.util.OrderIdGenerator;
import com.ecp.entity.User;
import com.ecp.service.front.IOrderItemService;
import com.ecp.service.front.IOrderService;

/**
 * @ClassName FavouriteController
 * @Description 用户订单-控制器
 * @author Administrator
 * @Date 2017年5月5日 下午4:34:20
 * @version 1.0.0
 */
@Controller
@RequestMapping("/front/order")
public class OrderController {
	final String RESPONSE_THYMELEAF = "thymeleaf/front/";
	final String RESPONSE_JSP = "jsps/front/";

	/*
	 * @Autowired IdGenerator orderIdGenerator;
	 */

	@Autowired
	IOrderService orderService;
	@Autowired
	IOrderItemService orderItemService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object categoryCond(@RequestBody List<AddSkuToOrderBean> cartItemList, Model model,
			HttpServletRequest request) {

		// System.out.println("cartitem size is:"+cartItemList.size());
		// String orderId=orderIdGenerator.nextId();

		String orderId = OrderIdGenerator.getOrderIdByUUId();  //生成订单号
		// System.out.println("order id is:"+orderId);

		// 如果用户在此处没有登录时
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionConstants.USER);

		//增加订单
		orderService.createNewOrder(user.getId(), user.getNickname(), orderId);
		
		//增加订单条目		
		orderItemService.addItemIntoOrder(cartItemList, orderId);		
		

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "added");
		jsonObject.put("status", "success");

		return jsonObject;
	}

}
