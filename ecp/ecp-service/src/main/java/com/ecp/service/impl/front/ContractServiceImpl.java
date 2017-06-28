package com.ecp.service.impl.front;

import org.springframework.stereotype.Service;

import com.ecp.dao.ContractMapper;
import com.ecp.entity.Contract;
import com.ecp.service.front.IContractService;
import com.ecp.service.impl.AbstractBaseService;

@Service
public class ContractServiceImpl extends AbstractBaseService<Contract, Long> implements IContractService {

	private ContractMapper contractMapper;

	/**
	 * @param  the mapper to set
	 * set方式注入
	 */	
	
	public void setContractMapper(ContractMapper contractMapper) {
		this.contractMapper=contractMapper;
		this.setMapper(contractMapper);
	}
	

}
