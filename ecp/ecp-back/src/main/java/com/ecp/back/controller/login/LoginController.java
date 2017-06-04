package com.ecp.back.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecp.back.commons.SessionConstants;
import com.ecp.back.commons.StaticConstants;
import com.ecp.back.commons.TipInfoConstants;
import com.ecp.bean.UserBean;
import com.ecp.entity.User;
import com.ecp.service.back.IUserService;
import com.google.code.kaptcha.Constants;

@Controller
public class LoginController {

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private IUserService userService;

	/**
	 * 进入登录页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goLogin")
	public String backLogin(HttpServletRequest request, HttpServletResponse response, String error) {
		return StaticConstants.LOGIN;
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @param loginname
	 * @param password
	 * @param remember
	 * @param verify
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> loginSubmit(HttpServletRequest request, HttpServletResponse response, String username,
			String password, String kaptcha) {
		HttpSession session = request.getSession();
		
		log.debug("username : "+username+" , password : "+password);
    	
    	if(StringUtils.isNotBlank(kaptcha)){
    		if(session!=null){
    			String sessionVerifyCode = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
    			if(sessionVerifyCode.equalsIgnoreCase(kaptcha)){
    				return this.verifyUser(request, username, password);
    			}
    		}
    	}
    	log.error(TipInfoConstants.VERIFY_CODE_ERROR_INFO);
    	Map<String, Object> respMap = new HashMap<String, Object>();
    	respMap.put("result_code", "fail");
    	respMap.put("result_err_msg", TipInfoConstants.VERIFY_CODE_ERROR_INFO);
		return respMap;
	}
	
	/**
	 * 方法功能：验证用户名和密码
	 * @param request
	 * @param loginname
	 * @param password
	 * @return
	 */
	private Map<String, Object> verifyUser(HttpServletRequest request, String loginname, String password){
		
		Map<String, Object> respMap = new HashMap<String, Object>();
		
    	if(StringUtils.isNotBlank(loginname) && StringUtils.isNotBlank(password)){
    		//user_pass加密规则：UPPER(MD5(CONCAT(user_name,":ECP:",user_pass)))
        	String pass=loginname+":ECP:"+password;
        	log.debug("md5 password upper : "+DigestUtils.md5Hex(pass.getBytes()).toUpperCase());
        	
        	User user = userService.login(loginname, DigestUtils.md5Hex(pass.getBytes()).toUpperCase());
        	if(user!=null){
        		log.debug("用户名为 "+loginname+" 的用户登录成功");
        		HttpSession session = request.getSession();
        		UserBean userBean = createUserBean(user);
        		session.setAttribute(SessionConstants.USER, userBean);
        		//session.setMaxInactiveInterval(10);
        		respMap.put("result_code", "success");
        		respMap.put("result_msg", TipInfoConstants.LOGIN_SUCCESS_INFO);
        		return respMap;
        	}else{
        		log.error("用户名为 "+loginname+" "+TipInfoConstants.LOGIN_FAIL_INFO);
        		respMap.put("result_code", "fail");
        		respMap.put("result_err_msg", TipInfoConstants.LOGIN_FAIL_INFO);
            	return respMap;
        	}
    	}else{
    		log.error(TipInfoConstants.LOGININFO_EMPTY_ERROR_INFO);
    		respMap.put("result_code", "fail");
    		respMap.put("result_err_msg", TipInfoConstants.LOGININFO_EMPTY_ERROR_INFO);
    		return respMap;
    	}
    }
	
	/**
	 * 方法功能：创建用户bean
	 * @param user
	 * @return
	 * <hr>
	 * <b>描述：</b>
	 * <p>Description:方法功能详细说明</p> 
	 * <p>Version: 1.0</p>
	 * <p>Author: srd </p>
	 * <p>Date: 2017年5月7日 上午15:25:39</p>
	 */
	private UserBean createUserBean(User user){
		return new UserBean(user.getId(), user.getAuditRemark(), user.getAuditor(), user.getCreatedTime(), user.getDeleted(), user.getDepartment(), user.getEmail(), user.getGrowthValue(), user.getLinkMan(), user.getLinkPhoneNum(), user.getMobile(), user.getNickname(), user.getOldpassword(), user.getParentId(), user.getPassword(), user.getQuickType(), user.getSecurityLevel(), user.getStatus(), user.getType(), user.getUpdateTime(), user.getUsername());
	}
	
	/**
	 * 用户退出登录
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();// session 无效
		return "redirect:/login";
	}

}
