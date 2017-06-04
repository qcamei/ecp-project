package com.ecp.service.back;

import java.util.List;

import com.ecp.entity.User;
import com.ecp.service.IBaseService;

public interface IUserService extends IBaseService<User, Long> {
	
	/**
	 * 根据登录名和密码查询
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(String username, String password);
	
	/**
	 * 根据登录名查询
	 * @param username
	 * @return
	 */
	public List<User> getByUsername(String username);
}
