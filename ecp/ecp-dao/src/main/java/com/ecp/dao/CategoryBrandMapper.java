package com.ecp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ecp.bean.CategoryBrandBean;
import com.ecp.entity.CategoryBrand;

import tk.mybatis.mapper.common.Mapper;

public interface CategoryBrandMapper extends Mapper<CategoryBrand> {
	
	public List<Map<String,String>> getBrandByCid(@Param("cid") Long cid);  //根据CID读取类目品牌
	
	/**
	 * @Description 根据品牌名称列表查询类目
	 * @param brands
	 * @return
	 */
	public List<CategoryBrandBean> getCategoryByBrand(@Param("brands") List<String> brands);
	
	
	/**
	 * @Description 通过品牌id查询 
	 * @param ids
	 * @return
	 */
	public List<CategoryBrandBean> getCategoryByBrandIds(@Param("ids") List<Long> ids);
	
}