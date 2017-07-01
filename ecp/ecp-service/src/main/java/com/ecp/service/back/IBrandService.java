package com.ecp.service.back;

import java.util.List;
import java.util.Map;

import com.ecp.entity.Brand;
import com.ecp.service.IBaseService;

public interface IBrandService extends IBaseService<Brand, Long> {
	
	/**
	 * 根据map中的条件查询品牌
	 * @param map
	 * @return
	 * 		返回品牌mapList
	 */
	public List<Map<String, Object>> getBrandByCategoryId(Long categoryId);
}
