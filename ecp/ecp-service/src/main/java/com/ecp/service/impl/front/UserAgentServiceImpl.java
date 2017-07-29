package com.ecp.service.impl.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ecp.dao.UserExtendsMapper;
import com.ecp.entity.UserExtends;
import com.ecp.service.front.IUserAgentService;
import com.ecp.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

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


	@Override
	public List<UserExtends> searchUserAgent(int searchTypeValue, String condValue) {
		List<UserExtends> resultList=new ArrayList<UserExtends>();
		Example example =new Example(UserExtends.class);
		Map<Integer,String> map=new HashMap<Integer,String>();
		map.put(1, "companyName");
		map.put(2, "artificialPersonName");
		map.put(3, "contactPhone");
		
		switch(searchTypeValue){
		case 0:   	//没有选择条件
			resultList=userExtendsMapper.selectAll();
			break;
		case 1:
		case 2:
		case 3:    	
			example.createCriteria().andLike(map.get(searchTypeValue), condValue);
			resultList=userExtendsMapper.selectByExample(example);
			break;
		default:
			resultList=userExtendsMapper.selectAll();
			break;
		}
		
		return resultList;
		
	}
	
	
	

	

}
