package com.ecp.service.back;

import java.util.List;

import com.ecp.entity.SlideshowSetting;
import com.ecp.service.IBaseService;

public interface ISlideshowService extends IBaseService<SlideshowSetting, Long> {
	
	/**
	 * 查询所有未删除的数据
	 * @return
	 */
	public List<SlideshowSetting> getAll();
	
	/**
	 * 删除（逻辑删除）
	 * @return
	 */
	public int deleteById(Long id);
	
	/**
	 * 批量删除（逻辑删除）
	 * @return
	 */
	public int deleteByIds(List<Long> ids);
}
