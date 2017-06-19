package com.ecp.web.back;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecp.common.util.FileUploadUtil;
import com.ecp.common.util.RequestResultUtil;
import com.ecp.entity.UserExtends;
import com.ecp.service.front.IUserAgentService;

/**
 * @ClassName UserAgentController
 * @Description 签约客户维护控制器
 * @author Administrator
 * @Date 2017年6月17日 上午11:37:13
 * @version 1.0.0
 */
@Controller
@RequestMapping("/back/agent")
public class UserAgentController {
	final String RESPONSE_THYMELEAF_BACK = "thymeleaf/back/";
	final String RESPONSE_JSP = "jsps/front/";

	private final Logger log = Logger.getLogger(getClass());

	@Autowired
	IUserAgentService userAgentService;

	/**
	 * @Description 显示-签约客户列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String user_agent_show(Model model) {
		List<UserExtends> userAgents=userAgentService.getAllUserAgent();
		
		model.addAttribute("userAgents", userAgents);
		
		
		return RESPONSE_THYMELEAF_BACK + "user_agent_show";
	}
	

	/**
	 * @Description 增加签约客户
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String user_agent_add(Model model) {
		return RESPONSE_THYMELEAF_BACK + "user_agent_add";
	}

	/**
	 * @Description 插入签约客户
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public Object user_agent_insert(HttpServletRequest request, UserExtends agent) {
		System.out.println(agent.getBusinessLicencePicSrc());
		//处理上传文件
		if (!this.processUploadFile(request, agent)) {
			return RequestResultUtil.getResultUploadWarn();
		}
		
		
		agent.setCreateDt(new Date());
		
		int row=userAgentService.addUserAgent(agent);
		if(row>0){
			return RequestResultUtil.getResultAddSuccess();
		}
				
		
		return RequestResultUtil.getResultAddWarn();
	}
	
	

	
	/**
	 * @Description 文件上传
	 * @param request
	 * @param brand
	 * @return
	 */
	private boolean processUploadFile(HttpServletRequest request, UserExtends agent) {
		boolean flag = false;
		try {
			//上传营业执照
			String backImgPath = FileUploadUtil.getFile2Upload(request, "back logo", "businessLicencePic_Src");
			if (StringUtils.isNotBlank(backImgPath)) {
				if (!FileUploadUtil.deleteFile(request, agent.getBusinessLicencePicSrc())) {
					log.error("文件不存在或已删除 logo图路径：" + agent.getBusinessLicencePicSrc());
				}
				agent.setBusinessLicencePicSrc(backImgPath);
			}
			
			//上传税务登录证
			backImgPath = FileUploadUtil.getFile2Upload(request, "back logo", "taxRegistrationCertificatePic_Src");
			if (StringUtils.isNotBlank(backImgPath)) {
				if (!FileUploadUtil.deleteFile(request, agent.getTaxRegistrationCertificatePicSrc())) {
					log.error("文件不存在或已删除 logo图路径：" + agent.getTaxRegistrationCertificatePicSrc());
				}
				agent.setTaxRegistrationCertificatePicSrc(backImgPath);
			}
			
			//上传组织机构代码证
			backImgPath = FileUploadUtil.getFile2Upload(request, "back logo", "organizationPic_Src");
			if (StringUtils.isNotBlank(backImgPath)) {
				if (!FileUploadUtil.deleteFile(request, agent.getOrganizationPicSrc())) {
					log.error("文件不存在或已删除 logo图路径：" + agent.getOrganizationPicSrc());
				}
				agent.setOrganizationPicSrc(backImgPath);
			}
			
			
			flag = true;
		} catch (IOException e) {
			log.error("上传文件异常", e);
		} catch (Exception e) {
			log.error("删除上传文件异常", e);
		}
		return flag;
	}

	/**
	 * @Description 签约客户-详情（修改）
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/detail/{id}")
	public String user_agent_detail(@PathVariable("id") long extendId, Model model) {
		
		UserExtends agent=userAgentService.selectByPrimaryKey(extendId);
		model.addAttribute("agent", agent);
		
		return RESPONSE_THYMELEAF_BACK + "user_agent_detail";
	}

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
