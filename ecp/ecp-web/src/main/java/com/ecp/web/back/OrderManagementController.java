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
 * @Description 订单管理
 * @author Administrator
 * @Date 2017年6月27日 上午9:55:24
 * @version 1.0.0
 */
@Controller
@RequestMapping("/back/order")
public class OrderManagementController {
	final String RESPONSE_THYMELEAF_BACK = "thymeleaf/back/";
	final String RESPONSE_JSP = "jsps/front/";

	private final int PAGE_SIZE = 10;

	private final Logger log = Logger.getLogger(getClass());

	@Autowired
	IOrderService orderService;  //订单服务
	

	/**
	 * @Description 显示-订单列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/show")
	public String order_show(Model model, Integer pageNum, Integer pageSize) {
		
		if(pageNum==null || pageNum==0)
		{
			pageNum=1;
			pageSize=PAGE_SIZE;
		}
		
		// 查询 并分页		
		PageHelper.startPage(pageNum, pageSize); // PageHelper			

		List<Orders> orderList = orderService.selectAll();
		PageInfo<Orders> pageInfo = new PageInfo<Orders>(orderList);// (使用了拦截器或是AOP进行查询的再次处理)
		
		setPageInfo(model, pageInfo); // 向前台传递分页信息

		model.addAttribute("orderList", orderList);

		return RESPONSE_THYMELEAF_BACK + "order_show";
	}
	

	private void setPageInfo(Model model, PageInfo pageInfo) {
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
	}

	
	
	
	/*@RequestMapping(value="/dispatch" ,method=RequestMethod.POST)
	@ResponseBody
	public Object user_agent_dispatch(HttpServletRequest request){
		
		String loginName=request.getParameter("loginName");
		String password=request.getParameter("password");
		long agentId=Long.parseLong(request.getParameter("agentId"));
		
		
		User user=new User();
		user.setCreatedTime(new Date());
		user.setUsername(loginName);
		String md5Pass=genMD5Password(loginName,password);
		user.setPassword(md5Pass);
		user.setType(UserType.AGENT);  //帐号类型
		
		
		
		int row =userService.insertSelective(user);
		if(row>0){
			UserExtends agent=new UserExtends();
			agent.setExtendId(agentId);
			agent.setUserId(user.getId());
			userAgentService.updateByPrimaryKeySelective(agent);
		}
		
		return RequestResultUtil.getResultUpdateSuccess();
		
	}*/
	
	/**
	 * @Description 根据用户名及口令生成MD5加密口令
	 * @param loginName
	 * @param password
	 * @return 生成MD5的加密口令
	 */
	/*private String genMD5Password(String loginName, String password) {
		// user_pass加密规则：UPPER(MD5(CONCAT(user_name,":CNWELL:",user_pass)))
		String pass = loginName + ":CNWELL:" + password;
		// log.debug("md5 password upper : " +
		// DigestUtils.md5Hex(pass.getBytes()).toUpperCase());
		String md5Password = DigestUtils.md5Hex(pass.getBytes()).toUpperCase();
		return md5Password;
	}*/
	

	/**
	 * @Description 插入签约客户
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object user_agent_insert(HttpServletRequest request, UserExtends agent) {
		System.out.println(agent.getBusinessLicencePicSrc());
		//处理上传文件
		if (!this.processUploadFile(request, agent)) {
			return RequestResultUtil.getResultUploadWarn();
		}

		agent.setCreateDt(new Date());

		int row = userAgentService.addUserAgent(agent);
		if (row > 0) {
			return RequestResultUtil.getResultAddSuccess();
		}

		return RequestResultUtil.getResultAddWarn();
	}*/

	/**
	 * @Description 文件上传
	 * @param request
	 * @param brand
	 * @return
	 */
	

	/**
	 * @Description 签约客户-详情（修改）
	 * @param model
	 * @return
	 */
	/*@RequestMapping(value = "/detail/{id}")
	public String user_agent_detail(@PathVariable("id") long extendId, Model model) {

		UserExtends agent = userAgentService.selectByPrimaryKey(extendId);
		model.addAttribute("agent", agent);

		return RESPONSE_THYMELEAF_BACK + "user_agent_detail";
	}*/

	/**
	 * @Description 修改客户信息
	 * @param company
	 * @param request
	 * @return
	 */
	/*@RequestMapping(value="/modify" ,method=RequestMethod.POST)
	@ResponseBody
	public Object companyInfo_modify(@RequestBody CompanyInfo company,HttpServletRequest request){
		
		company.setId((long)1);		
		companyInfoService.updateByPrimaryKeySelective(company);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "updated");
		jsonObject.put("status", "success");
		return jsonObject;
	}*/

}
