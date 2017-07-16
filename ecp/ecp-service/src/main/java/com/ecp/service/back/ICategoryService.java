package com.ecp.service.back;

import java.util.List;
import java.util.Map;

import com.ecp.bean.CategoryAttrBean;
import com.ecp.bean.CategoryTreeNode;
import com.ecp.entity.Category;
import com.ecp.service.IBaseService;

public interface ICategoryService extends IBaseService<Category, Integer> {
	
	/**
	 * 根据主键删除（如果删除节点中有子节点一起删除）
	 * @param id
	 * @return
	 */
	public int deleteById(Long id);
	/**
	 * 根据父ID查询类目
	 * @param parentCid
	 * @return
	 */
	public List<Category> selectByPid(Long parentCid);
	/**
	 * 根据级别查询类目
	 * @param lev
	 * @return
	 */
	public List<Category> selectByLev(int lev);
	/**
	 * @Description 读取类目树
	 * @return  一级类目结点列表
	 */
	public List<CategoryTreeNode> getCategoryTree();
	
	public List<Map<String, Object>> getAllCategory();
	
	public List<CategoryAttrBean> getCategoryAttrListByCid(Long cid);
	
}
