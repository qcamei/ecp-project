package com.ecp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ecp.entity.Brand;

import tk.mybatis.mapper.common.Mapper;

public interface BrandMapper extends Mapper<Brand> {
	
	/**
	 * @Description 根据关键字列表查询品牌
	 * @param keywords
	 * @return
	 */
	public List<Brand> getBrandByKeywords(@Param("keywords") List<String> keywords);
}