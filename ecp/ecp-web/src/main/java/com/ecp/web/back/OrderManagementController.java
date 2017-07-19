package com.ecp.web.back;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecp.entity.Orders;
import com.ecp.entity.UserExtends;
import com.ecp.service.front.IOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @ClassName OrderManagementController
 * @Description 订单管理(后台)
 * @author Administrator
 * @Date 2017年6月27日 上午9:55:24
 * @version 1.0.0
 */
@Controller
@RequestMapping("/back/order")
public class OrderManagementController {
	final String RESPONSE_THYMELEAF_BACK = "thymeleaf/back/";
	final String RESPONSE_FRONT="/front/";
	final String RESPONSE_JSP = "jsps/front/";

	private final int PAGE_SIZE = 8;

	private final Logger log = Logger.getLogger(getClass());

	@Autowired
	IOrderService orderService;  //订单服务
	

	/**
	 * @Description 显示-订单列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/show")
	public String order_show(Model model) {
		return RESPONSE_THYMELEAF_BACK + "order_show";
	}
	
	@RequestMapping(value = "/ordertable")
	public String order_table(int orderTimeCond,int dealStateCond,Integer pageNum, Integer pageSize,Model model) {
		if(pageNum==null || pageNum==0)
		{
			pageNum=1;
			pageSize=PAGE_SIZE;
		}
		
		//回传查询条件
		model.addAttribute("orderTimeCond", orderTimeCond);
		model.addAttribute("dealStateCond", dealStateCond);
		
		// 查询 并分页		
		PageHelper.startPage(pageNum, pageSize); // PageHelper			

		List<Orders> orderList = orderService.selectAllOrderByOrderTimeAndDealState(orderTimeCond,dealStateCond);
		PageInfo<Orders> pageInfo = new PageInfo<Orders>(orderList);// (使用了拦截器或是AOP进行查询的再次处理)
		
		model.addAttribute("pageInfo", pageInfo);  //分页
		model.addAttribute("orderList", orderList); //列表
		
		return RESPONSE_THYMELEAF_BACK + "order_table";
	}
	
	/**
	 * @Description 订单详情（后台）
	 * @param id  订单主键（pk）
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String order_detail(Long id,Model model,HttpServletRequest request){
		
		model.addAttribute("orderId", id);  //向订单详细table传递参数
		
		return RESPONSE_THYMELEAF_BACK + "order_detail";
	}
	
	/**
	 * @Description 合同详情（后台）
	 * @param id 合同id（pk）
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/contractdetail")
	public String order_contract_detail(Long id,Model model,HttpServletRequest request){
		
		model.addAttribute("contractId", id);
		
		return RESPONSE_THYMELEAF_BACK + "contract_detail";
	}
	
	/**
	 * @Description 订单详情模块页
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/detailtable")
	public String order_detail_table(Long id,Model model,HttpServletRequest request){
		return "forward:"+RESPONSE_FRONT + "order/"+"detailtable";
	}
	

}
