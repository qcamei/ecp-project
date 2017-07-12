package com.ecp.web.front;

import java.math.BigDecimal;
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
	
		
		//准备用于订单提交成功的数据
		model.addAttribute("orderNo", orderId);  //订单号
		BigDecimal totalPrice=calcCartItemTotalPayable(cartItemList);  //calc total_price  计算优惠前总金额
		model.addAttribute("totalPrice",totalPrice);  //订单金额
		model.addAttribute("orderTime",new Date());  //订单时间
		

		//订单提交成功页面
		return RESPONSE_THYMELEAF+"my_order_add_ok";
	}
	
	
	
	
	
	
	/**
	 * @Description 计算应付总金额（优惠前）
	 * @param cartItemList  己选购物车中商品列表
	 * @return
	 */
	private BigDecimal calcCartItemTotalPayable(List<AddSkuToOrderBean> cartItemList){
		BigDecimal total=new BigDecimal(0.00);
		for(AddSkuToOrderBean cartItem:cartItemList){
			total=total.add(cartItem.getSkuPrice().multiply(new BigDecimal(cartItem.getSkuNum())));
		}
		return total;
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
		
		order.setOrderId(orderId);        //订单号
		order.setCreateTime(new Date());  //订单创建时间
		order.setOrderTime(new Date());   //下单时间
		order.setBuyerId(addr.getBuyerId());  //买家id
		order.setName(addr.getContactPerson());  //收货人姓名
		order.setPhone(addr.getContactTel());    //收货人固定电话
		order.setMobile(addr.getContactPhone());  //收货人手机号码
		order.setEmail(addr.getContactEmail());   //收货人邮件
		order.setFullAddress(addr.getFullAddress());  //收货全地址
		BigDecimal totalPrice=calcCartItemTotalPayable(cartItemList);  //calc total_price  计算优惠前总金额
		order.setTotalPrice(totalPrice); //优惠前总金额
		
		
		//TODO set other field's value
		
		orderService.insertSelective(order);
		
		return orderId;
	}
	
	/**
	 * @Description 删除购物车中已经加入订单中的商品
	 * @param cartItemList
	 */
	private void delSelectedCartItem(List<AddSkuToOrderBean> cartItemList){
		for(AddSkuToOrderBean item:cartItemList){
			String idStr=Long.toString(item.getId());
			cartService.deleteByPrimaryKey(Integer.parseInt(idStr));
		}
		
	}
	
	
	
	

}
