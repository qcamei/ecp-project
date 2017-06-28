package com.ecp.web.front;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecp.bean.AddSkuToOrderBean;
import com.ecp.bean.CartToOrderItemList;
import com.ecp.common.SessionConstants;
import com.ecp.common.util.RequestResultUtil;
import com.ecp.entity.User;
import com.ecp.entity.UserAddressInfo;
import com.ecp.service.front.ICartService;
import com.ecp.service.front.IUserAddressInfoService;

/**
 * @ClassName CartController
 * @Description 购物车-控制器
 * @author Administrator
 * @Date 2017年5月5日 下午4:34:20
 * @version 1.0.0
 */
@Controller
@RequestMapping("/front/cart")
public class CartController {
	final String RESPONSE_THYMELEAF = "thymeleaf/front/";
	final String RESPONSE_JSP = "jsps/front/";

	@Autowired
	ICartService cartService;
	@Autowired
	IUserAddressInfoService userAddressInfoService;

	/**
	 * @Description sku加购物车
	 * @param itemId
	 * @param skuId
	 * @param quantity
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProductToCart(int itemId, int skuId, int quantity, String skuName, Model model,
			HttpServletRequest request) {

		//如果用户在此处没有登录时
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionConstants.USER);

		//TODO 判定用户是否已经登录
		if (user == null) { //如果用户没有登录
			return RESPONSE_THYMELEAF + "login";
		} else { //已经登录，将sku加入到购物车中
			cartService.addToCart(itemId, skuId, quantity, Integer.parseInt(user.getId().toString()));
		}

		model.addAttribute("skuName", skuName);

		return RESPONSE_THYMELEAF + "my_cart_add_ok";
	}

	/**
	 * @Description 将购物车所选数据置于session中  此种方式并不好，采用动态表单来处理。
	 * @param model
	 * @param request
	 * @return 结算页面（settle_account）
	 */
	@RequestMapping("/gosettle")
	@ResponseBody
	public Object cart_go_settlement(@RequestBody List<AddSkuToOrderBean> cartItemList, Model model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.setAttribute("cartItemList", cartItemList);

		Map<String, String> respMap = new HashMap<String, String>();
		respMap.clear();
		respMap.put(RequestResultUtil.RESULT_CODE, RequestResultUtil.RESULT_CODE_SUCCESS);
		respMap.put(RequestResultUtil.RESULT_MSG, "/front/cart/settle");  //导航到结算页面
		return respMap;
	}

	/**
	 * @Description 结算页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/settle")
	public String cart_settlement(CartToOrderItemList cartToOrderItemList,Model model, HttpServletRequest request) {
		
		List<AddSkuToOrderBean> cartItemList=cartToOrderItemList.getCartItemList();
		//传递自购物车中所选择的SKU列表
		model.addAttribute("cartItemList", cartItemList);

		//获取买家地址
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionConstants.USER);
		List<UserAddressInfo> addrs=userAddressInfoService.selectByBuyerId(user.getId());
		model.addAttribute("addrs", addrs);
		
		//计算商品数量及总金额
		int cartItemNumAmount=calcCartItemAmount(cartItemList);
		BigDecimal totalPayable=calcCartItemTotalPayable(cartItemList);
		//加入model
		model.addAttribute("cartItemNumAmount", cartItemNumAmount);  //商品数量
		model.addAttribute("totalPayable", totalPayable);  //总金额

		return RESPONSE_THYMELEAF+"settle_account";
	}
	
	
	private int calcCartItemAmount(List<AddSkuToOrderBean> cartItemList){
		int amount=0;
		for(AddSkuToOrderBean cartItem:cartItemList){
			amount=amount+cartItem.getSkuNum();
		}
		return amount;
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
	

}
