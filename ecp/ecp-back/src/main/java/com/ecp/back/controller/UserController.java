package com.ecp.back.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ecp.back.commons.SessionConstants;
import com.ecp.bean.PageBean;
import com.ecp.bean.UserBean;
import com.ecp.common.util.RequestResultUtil;
import com.ecp.entity.User;
import com.ecp.service.back.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Class: UserController
 * 		用户Controller类
 * @author srd 
 * @version 1.0 $Date: 2017年5月7日 下午4:16:02
 */
@Controller
@RequestMapping("/back/user")
public class UserController {

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private IUserService iUserService;
	
	/**
	 * 方法功能：查询列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/selectItem")
	public ModelAndView selectLinkItem(HttpServletRequest request, HttpServletResponse response, Boolean clickPageBtn, PageBean pageBean, String pagehelperFun) {
		ModelAndView mav = new ModelAndView();
		UserBean user = (UserBean)request.getSession().getAttribute(SessionConstants.USER);
		
		PageHelper.startPage(pageBean.getPageNum(), pageBean.getPageSize());
		List<User> userList = iUserService.selectAll();
		PageInfo<User> pagehelper = new PageInfo<User>(userList);
		
		mav.addObject("pagehelper", pagehelper);
		
		if(clickPageBtn!=null && clickPageBtn){
			//mav.setViewName(StaticConstants.SCRM_USER_MANAGE_TABLE_PAGE);
		}else{
			//mav.setViewName(StaticConstants.SCRM_USER_MANAGE_PAGE);
		}
		
		mav.addObject("pagehelperFun", pagehelperFun);
		return mav;
	}
	
	/**
	 * 方法功能：查询要修改的信息
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 */
	@RequestMapping("/selectUpdateById")
	@ResponseBody
	public Map<String, Object> selectUpdateById(HttpServletRequest request, HttpServletResponse response, Long userId) {
		try {
			User user = iUserService.selectByPrimaryKey(userId);
			Map<String, Object> respM = RequestResultUtil.getResultSelectSuccess();
			respM.put("user", user);
			return respM;
		} catch (Exception e) {
			log.error("查询异常", e);
			return RequestResultUtil.getResultSelectWarn();
		}
	}
	
	/**
	 * 方法功能：添加
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String, Object> insertContent(HttpServletRequest request, HttpServletResponse response, User user) {
		
		UserBean userBean = (UserBean)request.getSession().getAttribute(SessionConstants.USER);
		if(userBean!=null){
			user.setCreatedTime(new Date());
			int rows = iUserService.insertSelective(user);
			if(rows>0){
				return RequestResultUtil.getResultAddSuccess();
			}
		}
		return RequestResultUtil.getResultAddWarn();
	}
	
	/**
	 * 方法功能：修改
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping("/updateById")
	@ResponseBody
	public Map<String, Object> updateById(HttpServletRequest request, HttpServletResponse response, User user) {
		
		try {
			int rows = iUserService.updateByPrimaryKeySelective(user);
			if(rows>0){
				return RequestResultUtil.getResultUpdateSuccess();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return RequestResultUtil.getResultUpdateWarn();
	}
	
	/**
	 * 方法功能：删除
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public Map<String, Object> deleteById(HttpServletRequest request, HttpServletResponse response, Long userId) {
		int rows = iUserService.deleteByPrimaryKey(userId);
		if(rows>0){
			return RequestResultUtil.getResultDeleteSuccess();
		}
		return RequestResultUtil.getResultDeleteWarn();
	}
	
}
