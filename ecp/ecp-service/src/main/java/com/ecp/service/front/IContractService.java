package com.ecp.service.front;

import com.ecp.entity.Contract;
import com.ecp.service.IBaseService;


/**
 * @ClassName IContractService
 * @Description 合同-业务层
 * @author Administrator
 * @Date 2017年6月28日 下午5:02:50
 * @version 1.0.0
 */
public interface IContractService extends IBaseService<Contract, Long> {
	
	/**
	 * @Description 获取合同号
	 * @return 合同号
	 */
	public String getContractNo();
	
}
