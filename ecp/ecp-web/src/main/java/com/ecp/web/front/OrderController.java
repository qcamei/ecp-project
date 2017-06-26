package com.ecp.web.front;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ecp.bean.AddSkuToOrderBean;
import com.ecp.bean.CartToOrderItemList;
import com.ecp.common.SessionConstants;
import com.ecp.common.util.OrderIdGenerator;
import com.ecp.entity.Orders;
import com.ecp.entity.User;
import com.ecp.entity.UserAddressInfo;
import com.ecp.service.front.ICartService;
import com.ecp.service.front.IOrderItemService;
import com.ecp.service.front.IOrderService;
import com.ecp.service.front.IUserAddressInfoService;

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
	@Autowired
	ICartService cartService;
	@Autowired
	IUserAddressInfoService userAddressInfoService;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String order_create(CartToOrderItemList cartToOrderItemList, Model model,
			HttpServletRequest request) {

		//获取所选购物车中的商品列表
		List<AddSkuToOrderBean> cartItemList=cartToOrderItemList.getCartItemList();
		UserAddressInfo addr=userAddressInfoService.selectByPrimaryKey(cartToOrderItemList.getAddrId());
		
		// 如果用户在此处没有登录时
		/*HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionConstants.USER);*/

		String orderId=createOrder(cartItemList,addr);  //增加订单
		orderItemService.addItemIntoOrder(cartItemList, orderId); //增加订单条目
		delSelectedCartItem(cartItemList);  //自购购物车删除用户已下单商品
		

		//订单提交成功页面
		return RESPONSE_THYMELEAF+"my_order_add_ok";
	}
	
	/**
	 * @Description 增加订单
	 * @param cartItemList  用户所选商品列表
	 * @param addr  收货地址
	 * @return  订单号
	 */
	private String createOrder(List<AddSkuToOrderBean> cartItemList,UserAddressInfo addr){
		String orderId = OrderIdGenerator.getOrderIdByUUId();  //生成订单号
		Orders order=new Orders();
		
		order.setOrderId(orderId);
		order.setCreateTime(new Date());
		order.setBuyerId(addr.getBuyerId());
		order.setName(addr.getContactPerson());
		order.setPhone(addr.getContactTel());
		order.setMobile(addr.getContactPhone());
		order.setEmail(addr.getContactEmail());
		order.setFullAddress(addr.getFullAddress());
		
		//TODO set other field's value
		
		orderService.insertSelective(order);
		
		return orderId;
	}
	
	private void delSelectedCartItem(List<AddSkuToOrderBean> cartItemList){
		for(AddSkuToOrderBean item:cartItemList){
			String idStr=Long.toString(item.getId());
			cartService.deleteByPrimaryKey(Integer.parseInt(idStr));
		}
		
	}
	
	
	
	

}
