package com.ecp.dao;

import com.ecp.entity.Contract;

import tk.mybatis.mapper.common.Mapper;

public interface ContractMapper extends Mapper<Contract> {
	public String getMaxContractNo(String monthCond);
}