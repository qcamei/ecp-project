package com.ecp.service.impl.back;

import org.springframework.stereotype.Service;

import com.ecp.dao.BrandMapper;
import com.ecp.entity.Brand;
import com.ecp.service.back.IBrandService;
import com.ecp.service.impl.AbstractBaseService;

@Service("brandServiceBean")
public class BrandServiceImpl extends AbstractBaseService<Brand, Long> implements IBrandService {

	private BrandMapper brandMapper;

	/**
	 * @param brandMapper the brandMapper to set
	 * set方式注入
	 */
	public void setBrandMapper(BrandMapper brandMapper) {
		this.brandMapper = brandMapper;
		this.setMapper(brandMapper);
	}

}
