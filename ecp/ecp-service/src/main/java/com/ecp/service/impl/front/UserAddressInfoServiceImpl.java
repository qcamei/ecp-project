package com.ecp.service.impl.front;

import org.springframework.stereotype.Service;

import com.ecp.dao.UserAddressInfoMapper;
import com.ecp.entity.UserAddressInfo;
import com.ecp.service.front.IUserAddressInfoService;
import com.ecp.service.impl.AbstractBaseService;

@Service
public class UserAddressInfoServiceImpl extends AbstractBaseService<UserAddressInfo, Long> implements IUserAddressInfoService {
	
	UserAddressInfoMapper userAddressInfoMapper;
	
	/**
	 * @param mapper
	 * the mapper to set set方式注入
	 */	
	public void setUserAddressInfoMapper(UserAddressInfoMapper userAddressInfoMapper) { 
		this.userAddressInfoMapper=userAddressInfoMapper;
		this.setMapper(userAddressInfoMapper);
	}	

}
