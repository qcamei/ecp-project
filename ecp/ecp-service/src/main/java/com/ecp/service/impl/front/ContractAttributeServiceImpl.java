package com.ecp.service.impl.front;

import org.springframework.stereotype.Service;

import com.ecp.dao.ContractAttributeMapper;
import com.ecp.entity.ContractAttribute;
import com.ecp.service.front.IContractAttributeService;
import com.ecp.service.impl.AbstractBaseService;

@Service
public class ContractAttributeServiceImpl extends AbstractBaseService<ContractAttribute, Long> implements IContractAttributeService {

	private ContractAttributeMapper contractAttributeMapper;

	/**
	 * @param 
	 * set方式注入
	 */	
	
	public void setContractAttributeMapper(ContractAttributeMapper contractAttributeMapper) {
		this.contractAttributeMapper=contractAttributeMapper;
		this.setMapper(contractAttributeMapper);
	}
	

}
