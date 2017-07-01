package com.ecp.service.impl.back;

import org.springframework.stereotype.Service;

import com.ecp.dao.SkuMapper;
import com.ecp.entity.Sku;
import com.ecp.service.back.ISkuService;
import com.ecp.service.impl.AbstractBaseService;

@Service("skuServiceBean")
public class SkuServiceImpl extends AbstractBaseService<Sku, Long> implements ISkuService {

	private SkuMapper skuMapper;

	/**
	 * @param skuMapper the skuMapper to set
	 * set方式注入
	 */
	public void setSkuMapper(SkuMapper skuMapper) {
		this.skuMapper = skuMapper;
		this.setMapper(skuMapper);
	}

}
