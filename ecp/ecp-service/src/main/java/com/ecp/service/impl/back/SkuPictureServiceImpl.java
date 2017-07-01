package com.ecp.service.impl.back;

import org.springframework.stereotype.Service;

import com.ecp.dao.SkuPictureMapper;
import com.ecp.entity.SkuPicture;
import com.ecp.service.back.ISkuPictureService;
import com.ecp.service.impl.AbstractBaseService;

@Service("skuPictureServiceBean")
public class SkuPictureServiceImpl extends AbstractBaseService<SkuPicture, Long> implements ISkuPictureService {

	private SkuPictureMapper skuPictureMapper;

	/**
	 * @param skuPictureMapper the skuPictureMapper to set
	 * set方式注入
	 */
	public void setSkuPictureMapper(SkuPictureMapper skuPictureMapper) {
		this.skuPictureMapper = skuPictureMapper;
		this.setMapper(skuPictureMapper);
	}

}
