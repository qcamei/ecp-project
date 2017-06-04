package com.ecp.web.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecp.common.SessionConstants;
import com.ecp.entity.User;
import com.ecp.service.front.ICartService;


/**
 * @ClassName CartController
 * @Description  购物车-控制器
 * @author Administrator
 * @Date 2017年5月5日 下午4:34:20
 * @version 1.0.0
 */
@Controller
@RequestMapping("/front/cart")
public class CartController {
	final String RESPONSE_THYMELEAF="thymeleaf/front/";
	final String RESPONSE_JSP="jsps/front/";	
	
	@Autowired
	ICartService cartService;
	
	/**
	 * @Description sku加购物车
	 * @param itemId
	 * @param skuId
	 * @param quantity
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add" ,method=RequestMethod.POST)
	public String addProductToCart(int itemId,int skuId,int quantity,String skuName, Model model,HttpServletRequest request){		
		/*System.out.println("itemId:"+itemId);
		System.out.println("skuId:"+skuId);
		System.out.println("quantity:"+quantity);
		System.out.println("skuName:"+skuName);*/
		
		//如果用户在此处没有登录时
		HttpSession session = request.getSession();    		
		User user =(User)session.getAttribute(SessionConstants.USER);
		
		//TODO 判定用户是否已经登录
		if(user==null){  //如果用户没有登录
			return RESPONSE_THYMELEAF+"login";
		}
		else{  //已经登录，将sku加入到购物车中
			cartService.addToCart(itemId, skuId, quantity, Integer.parseInt(user.getId().toString()));
		}			
			
		model.addAttribute("skuName", skuName);
		
		return RESPONSE_THYMELEAF+"my_cart_add_ok";
	}
	
	
	

}
