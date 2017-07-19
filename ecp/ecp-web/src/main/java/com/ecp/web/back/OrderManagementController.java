package com.ecp.web.back;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecp.entity.Orders;
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
	final String RESPONSE_JSP = "jsps/front/";

	private final int PAGE_SIZE = 2;

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
	public String order_table(Integer pageNum, Integer pageSize,Model model) {
		if(pageNum==null || pageNum==0)
		{
			pageNum=1;
			pageSize=PAGE_SIZE;
		}
		
		// 查询 并分页		
		PageHelper.startPage(pageNum, pageSize); // PageHelper			

		List<Orders> orderList = orderService.selectAll();
		PageInfo<Orders> pageInfo = new PageInfo<Orders>(orderList);// (使用了拦截器或是AOP进行查询的再次处理)
		
		model.addAttribute("pageInfo", pageInfo);  //分页
		model.addAttribute("orderList", orderList); //列表
		
		return RESPONSE_THYMELEAF_BACK + "order_table";
	}
	
	

	/*private void setPageInfo(Model model, PageInfo pageInfo) {
		// 获得当前页
		model.addAttribute("pageNum", pageInfo.getPageNum());
		// 获得一页显示的条数
		model.addAttribute("pageSize", pageInfo.getPageSize());
		// 是否是第一页
		model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
		// 获得总页数
		model.addAttribute("totalPages", pageInfo.getPages());
		// 是否是最后一页
		model.addAttribute("isLastPage", pageInfo.isIsLastPage());
	}*/


}
