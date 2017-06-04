package com.ecp.service.back;

import java.util.List;
import java.util.Map;

import com.ecp.entity.Item;
import com.ecp.service.IBaseService;

public interface IItemService extends IBaseService<Item, Long> {
	
	/**
	 * 根据条件查询列表（条件为空时查询所有）
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> selectItemsByCondition(Map<String, Object> map);
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public int deleteByIds(String ids);
}
