package com.ecp.service.impl.back;

import org.springframework.stereotype.Service;

import com.ecp.dao.SkuPriceMapper;
import com.ecp.entity.SkuPrice;
import com.ecp.service.back.ISkuPriceService;
import com.ecp.service.impl.AbstractBaseService;

@Service("skuPriceServiceBean")
public class SkuPriceServiceImpl extends AbstractBaseService<SkuPrice, Long> implements ISkuPriceService {

	private SkuPriceMapper skuPriceMapper;

	/**
	 * @param skuPriceMapper the skuPriceMapper to set
	 * set方式注入
	 */
	public void setSkuPriceMapper(SkuPriceMapper skuPriceMapper) {
		this.skuPriceMapper = skuPriceMapper;
		this.setMapper(skuPriceMapper);
	}

}
