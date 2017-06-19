package com.ecp.service.front;

import java.util.List;

import com.ecp.entity.User;
import com.ecp.entity.UserExtends;
import com.ecp.service.IBaseService;


/**
 * @ClassName IUserAgentService
 * @Description 签约客户-业务层
 * @author Administrator
 * @Date 2017年6月17日 下午1:50:03
 * @version 1.0.0
 */
public interface IUserAgentService extends IBaseService<UserExtends, Long> {
	
	
	/**
	 * @Description 增加签约客户
	 * @param agent 签约客户对象
	 * @return 影响的行数
	 */
	public int  addUserAgent(UserExtends agent);
	
	/**
	 * @Description 获取所有签约用户列表
	 * @return
	 */
	public List<UserExtends> getAllUserAgent();
	
	
	
	
	
}
