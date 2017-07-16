package com.ecp.service.impl.front;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecp.dao.UserExtendsMapper;
import com.ecp.entity.UserExtends;
import com.ecp.service.front.IUserAgentService;
import com.ecp.service.impl.AbstractBaseService;

@Service
public class UserAgentServiceImpl extends AbstractBaseService<UserExtends, Long> implements IUserAgentService {
	
	UserExtendsMapper userExtendsMapper;

	/**
	 * @param userMapper the mapper to set
	 * set方式注入
	 */	
	
	public void setUserExtendsMapper(UserExtendsMapper userExtendsMapper) {
		this.userExtendsMapper=userExtendsMapper;
		this.setMapper(userExtendsMapper);
	}

	
	@Override
	public int addUserAgent(UserExtends agent) {
		return userExtendsMapper.insertSelective(agent);
	}

	@Override
	public List<UserExtends> getAllUserAgent() {
		return userExtendsMapper.selectAll();
	}


	@Override
	public UserExtends getUserAgentByUserId(long userId) {
		UserExtends record=new UserExtends();
		record.setUserId(userId);
		return userExtendsMapper.selectOne(record);
		
	}
	
	
	

	

}
