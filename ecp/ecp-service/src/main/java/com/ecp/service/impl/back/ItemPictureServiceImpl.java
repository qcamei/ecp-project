package com.ecp.service.impl.back;

import org.springframework.stereotype.Service;

import com.ecp.dao.ItemPictureMapper;
import com.ecp.entity.ItemPicture;
import com.ecp.service.back.IItemPictureService;
import com.ecp.service.impl.AbstractBaseService;

@Service("itemPictureServiceBean")
public class ItemPictureServiceImpl extends AbstractBaseService<ItemPicture, Long> implements IItemPictureService {

	private ItemPictureMapper itemPictureMapper;

	/**
	 * @param itemPictureMapper the itemPictureMapper to set
	 * set方式注入
	 */
	public void setItemPictureMapper(ItemPictureMapper itemPictureMapper) {
		this.itemPictureMapper = itemPictureMapper;
		this.setMapper(itemPictureMapper);
	}

}
