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
import org.springframework.web.servlet.ModelAndView;

import com.ecp.bean.AddSkuToOrderBean;
import com.ecp.common.SessionConstants;
import com.ecp.entity.User;
import com.ecp.service.front.ICartService;


/**
 * @ClassName UserAddressController
 * @Description 用户地址前端控制器
 * @author Administrator
 * @Date 2017年6月23日 下午12:41:21
 * @version 1.0.0
 */
@Controller
@RequestMapping("/front/useraddress")
public class UserAddressController {
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
	
	
	/**
	 * @Description 购物车：结算
	 * @param model
	 * @param request
	 * @return 结算页面（settle_account）
	 */
	@RequestMapping("/settle")
	@ResponseBody	
	public ModelAndView cart_settlement(@RequestBody List<AddSkuToOrderBean> cartItemList,ModelAndView mv){
		
		mv.setViewName(RESPONSE_THYMELEAF+"settle_account");
		
		return mv;
	}
	
	

}
