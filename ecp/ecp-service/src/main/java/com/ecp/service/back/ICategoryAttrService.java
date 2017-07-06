package com.ecp.service.back;

import java.util.List;
import java.util.Map;

import com.ecp.bean.CategoryAttrBean;
import com.ecp.entity.CategoryAttr;
import com.ecp.service.IBaseService;

public interface ICategoryAttrService extends IBaseService<CategoryAttr, Long> {	
	
	//读取某类目的所有属性列表
	public List<CategoryAttrBean> getCategoryAttrListByCid(Long cid);
	
	//读取某类目的所有属性列表
	public List<Map<String,String>> getCategoryAttrListByCid1(Long cid);
	
	public List<CategoryAttr> findByCid(Long cid);  //test function
	
	public void saveCategoryAttr(CategoryAttrBean cateAttrBean);  //保存类目属性
	
	/**
	 * @Description //读取类目属性值列表
	 * @param cid
	 * @param attrId
	 * @return
	 * 	 每个map对象是一个值列表信息，结构如下：
	 * 		key		value
	 *      -------------
	 * 		cid:
	 * 		attr_id: 
	 * 		value_id:
	 * 		value_name:
	 * 		-------------
	 * 		
	 */
	public List<Map<String,String>> getCategoryAttrValList(Long cid,Long attrId); 
	
	/**
	 * @Description 保存 类目-属性值
	 * @param cid  类目id
	 * @param attrId  类目属性id
	 * @param valueName   值名称
	 */
	public void saveCategoryAttrValue(Long cid,Long attrId,String valueName);
	
	/**
	 * 根据属性ID查询类目属性表
	 * @param attrId
	 * @return
	 */
	public CategoryAttr getByAttrId(Long attrId);
	
}
