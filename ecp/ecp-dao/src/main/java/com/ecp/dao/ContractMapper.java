package com.ecp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ecp.entity.Contract;

import tk.mybatis.mapper.common.Mapper;

public interface ContractMapper extends Mapper<Contract> {
	public String getMaxContractNo(String monthCond);
	
	public List<Map<String,Object>> selectContractByCond(@Param("timeCond") int timeCond,
														@Param("dealStateCond") int dealStateCond,
														@Param("searchTypeValue") int searchTypeValue,
														@Param("condValue") String condValue);
}