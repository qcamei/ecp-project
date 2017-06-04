package com.ecp.service.impl.back;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecp.dao.UserMapper;
import com.ecp.entity.User;
import com.ecp.service.back.IUserService;
import com.ecp.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl extends AbstractBaseService<User, Long> implements IUserService {

	private UserMapper userMapper;

	/**
	 * @param userMapper the userMapper to set
	 * set方式注入
	 */
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
		this.setMapper(userMapper);
	}

	/** 
	 * @author: srd
	 * @see com.ecp.service.IUserService#login(java.lang.String, java.lang.String)
	 * 根据用户名和密码查询
	 */
	public User login(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		List<User> userList = userMapper.select(user);
		if(userList!=null && userList.size()>0){
			return userList.get(0);
		}
		return null;
	}

	/** 
	 * (non-Javadoc)
	 * @see com.ecp.service.IUserService#getByUsername(java.lang.String)
	 * 根据用户名查询
	 */
	@Override
	public List<User> getByUsername(String username) {
		Example example=new Example(User.class);
		example.createCriteria().andEqualTo("username", username);
		List<User> list=userMapper.selectByExample(example);
		return list;
	}

}
