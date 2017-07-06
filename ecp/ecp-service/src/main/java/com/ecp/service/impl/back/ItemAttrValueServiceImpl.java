package com.ecp.service.impl.back;

import org.springframework.stereotype.Service;

import com.ecp.dao.ItemAttrValueMapper;
import com.ecp.entity.ItemAttrValue;
import com.ecp.service.back.IItemAttrValueService;
import com.ecp.service.impl.AbstractBaseService;

@Service("itemAttrValueServiceBean")
public class ItemAttrValueServiceImpl extends AbstractBaseService<ItemAttrValue, Long> implements IItemAttrValueService {

	private ItemAttrValueMapper itemAttrValueMapper;

	/**
	 * @param itemAttrValueMapper the itemAttrValueMapper to set
	 * set方式注入
	 */
	public void setItemAttrValueMapper(ItemAttrValueMapper itemAttrValueMapper) {
		this.itemAttrValueMapper = itemAttrValueMapper;
		this.setMapper(itemAttrValueMapper);
	}

}
