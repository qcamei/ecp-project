package com.ecp.web.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ecp.entity.CompanyInfo;
import com.ecp.service.back.ICompanyInfoService;



/**
 * @ClassName ContractController
 * @Description 合同管理
 * @author Administrator
 * @Date 2017年6月22日 上午11:52:24
 * @version 1.0.0
 */
@Controller
@RequestMapping("/back/contract")
public class ContractController {
	final String RESPONSE_THYMELEAF_BACK="thymeleaf/back/";
	final String RESPONSE_JSP="jsps/front/";	
	
	/*@Autowired
	ICompanyInfoService companyInfoService;*/
	
	/**
	 * @Description 合同详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/detail")
	public String contract_detail(Model model){
		return RESPONSE_THYMELEAF_BACK+"contract_detail";
	}
	
	
	/**
	 * @Description 增加合同
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add")
	public String contract_add(Model model){
		return RESPONSE_THYMELEAF_BACK+"contract_add";
	}
	
	
	/**
	 * @Description 预览合同
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/preview")
	public String contract_preview(Model model){
		return RESPONSE_THYMELEAF_BACK+"contract_preview";
	}
	
	/**
	 * @Description 合同列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/show")
	public String contract_show(Model model){
		return RESPONSE_THYMELEAF_BACK+"contract_show";
	}
	
	
	/**
	 * @Description 显示公司信息维护页面
	 * @param model
	 * @param request
	 * @return
	 */
	
	/*@RequestMapping(value="/show" ,method=RequestMethod.GET)
	public String companyInfo_Show(Model model){
			
		CompanyInfo company=companyInfoService.selectByPrimaryKey((long)1);
		model.addAttribute("company", company);		
		
		return RESPONSE_THYMELEAF_BACK+"company_info";
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
	}
*/
}
