package com.ecp.service.impl.back;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecp.dao.MenuMapper;
import com.ecp.entity.Menu;
import com.ecp.service.back.IMenuService;
import com.ecp.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("menuServiceBean")
public class MenuServiceImpl extends AbstractBaseService<Menu, Long> implements IMenuService {

	private MenuMapper menuMapper;

	/**
	 * @param menuMapper the menuMapper to set
	 * set方式注入
	 */
	public void setMenuMapper(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
		this.setMapper(menuMapper);
	}

	/**
	 * @see com.ecp.service.back.IMenuService#getByMenuIds(java.util.List)
	 * 根据菜单ID集合查询菜单
	 */
	@Override
	public List<Menu> getByMenuIds(List<Long> menuIds) {
		return menuMapper.getByMenuIds(menuIds);
	}

	/**
	 * @see com.ecp.service.back.IMenuService#getListAllByAsc()
	 * 根据sort_num正序查询所有菜单
	 */
	@Override
	public List<Menu> getListAllByAsc() {
		Example example = new Example(Menu.class);
		example.setOrderByClause("sort_num ASC");
		return menuMapper.selectByExample(example);
	}

}
