package com.ecp.web.back;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ecp.bean.UserType;
import com.ecp.common.util.FileUploadUtil;
import com.ecp.common.util.RequestResultUtil;
import com.ecp.entity.User;
import com.ecp.entity.UserExtends;
import com.ecp.service.back.IUserService;
import com.ecp.service.front.IUserAgentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

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

	private final int PAGE_SIZE = 8;

	private final Logger log = Logger.getLogger(getClass());

	@Autowired
	IUserAgentService userAgentService;
	@Autowired
	IUserService userService;

	/**
	 * @Description 显示-签约客户列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/show")
	public String user_agent_show(Model model, Integer pageNum, Integer pageSize) {
		
		if(pageNum==null || pageNum==0)
		{
			pageNum=1;
			pageSize=PAGE_SIZE;
		}
		
		// 查询 并分页		
		PageHelper.startPage(pageNum, pageSize); // PageHelper
			

		List<UserExtends> userAgents = userAgentService.getAllUserAgent();
		PageInfo<UserExtends> pageInfo = new PageInfo<>(userAgents);// (使用了拦截器或是AOP进行查询的再次处理)
		
		setPageInfo(model, pageInfo); // 向前台传递分页信息

		model.addAttribute("userAgents", userAgents);

		return RESPONSE_THYMELEAF_BACK + "user_agent_show";
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

	/**
	 * @Description 增加签约客户 导航至增加签约客户界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String user_agent_add(Model model) {
		return RESPONSE_THYMELEAF_BACK + "user_agent_add";
	}
	
	
	@RequestMapping(value="/dispatch" ,method=RequestMethod.POST)
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
		
	}
	
	/**
	 * @Description 根据用户名及口令生成MD5加密口令
	 * @param loginName
	 * @param password
	 * @return 生成MD5的加密口令
	 */
	private String genMD5Password(String loginName, String password) {
		// user_pass加密规则：UPPER(MD5(CONCAT(user_name,":CNWELL:",user_pass)))
		String pass = loginName + ":CNWELL:" + password;
		// log.debug("md5 password upper : " +
		// DigestUtils.md5Hex(pass.getBytes()).toUpperCase());
		String md5Password = DigestUtils.md5Hex(pass.getBytes()).toUpperCase();
		return md5Password;
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

		int row = userAgentService.addUserAgent(agent);
		if (row > 0) {
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

		UserExtends agent = userAgentService.selectByPrimaryKey(extendId);
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
