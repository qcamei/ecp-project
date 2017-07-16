package com.ecp.service.impl.back;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecp.bean.CategoryAttrBean;
import com.ecp.dao.AttributeMapper;
import com.ecp.dao.AttributeValueMapper;
import com.ecp.dao.CategoryAttrMapper;
import com.ecp.dao.CategoryAttrValueMapper;
import com.ecp.entity.Attribute;
import com.ecp.entity.AttributeValue;
import com.ecp.entity.CategoryAttr;
import com.ecp.entity.CategoryAttrValue;
import com.ecp.service.back.ICategoryAttrService;
import com.ecp.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("categoryAttrServiceBean")
public class CategoryAttrServiceImpl extends AbstractBaseService<CategoryAttr, Long> implements ICategoryAttrService {

	@Autowired
	CategoryAttrMapper categoryAttrMapper;
	@Autowired
	AttributeMapper attributeMapper;
	@Autowired
	CategoryAttrValueMapper categoryAttrValueMapper;
	@Autowired
	AttributeValueMapper attributeValueMapper;
	
	

	/**
	 * @param categoryAttrMapper
	 *            the mapper to set set方式注入
	 */

	public void setCategoryAttrMapper(CategoryAttrMapper categoryAttrMapper) {
		this.categoryAttrMapper = categoryAttrMapper;
		setMapper(categoryAttrMapper);
	}

	@Override
	public List<CategoryAttrBean> getCategoryAttrListByCid(Long cid) {

		// categoryAttrMapper.findByCid(cid);
		// List<CategoryAttrBean> list=null;
		List<CategoryAttrBean> list = categoryAttrMapper.getCategoryAttrListByCid(cid);

		return list;
	}

	@Override
	public List<CategoryAttr> findByCid(Long cid) {
		return categoryAttrMapper.findByCid(cid);
	}

	@Override
	public List<Map<String, String>> getCategoryAttrListByCid1(Long cid) {
		return categoryAttrMapper.getCategoryAttrListByCid1(cid);
	}

	final int VALID = 1; // final int DELETED=2;
	
	@Override
	public void saveCategoryAttr(CategoryAttrBean cateAttrBean) {
		

		// (1)保存属性，
		Attribute attr = new Attribute();
		attr.setAttrName(cateAttrBean.getAttr_name());
		attr.setCreated(new Date());
		attr.setStatus(VALID);

		int recNum = attributeMapper.insert(attr); // 返回的是记录的个数

		long attr_id = attr.getAttrId(); // 获取新插入记录的pk

		// (2)保存类目-属性关系
		CategoryAttr cateAttr = new CategoryAttr();
		cateAttr.setCid(cateAttrBean.getCid());
		cateAttr.setAttrType((byte) cateAttrBean.getAttr_type());
		cateAttr.setInputType((byte) cateAttrBean.getInput_type());
		cateAttr.setOptionType((byte) cateAttrBean.getOption_type());
		cateAttr.setSortNumber((long) cateAttrBean.getSort_number());
		cateAttr.setAttrId(attr_id);
		cateAttr.setCreated(new Date());
		cateAttr.setStatus((byte) VALID);
		categoryAttrMapper.insert(cateAttr);

	}

	@Override
	public List<Map<String, String>> getCategoryAttrValList(Long cid, Long attrId) {
		
		return categoryAttrMapper.getCategoryAttrValList(cid, attrId);
	}

	@Override
	public void saveCategoryAttrValue(Long cid, Long attrId, String valueName) {
		//(1)保存至  属性-值  表中  item_attribute_value
		AttributeValue attrValue=new AttributeValue();
		attrValue.setCreated(new Date());
		attrValue.setStatus(VALID);
		attrValue.setAttrId(attrId);
		attrValue.setValueName(valueName);
		
		int recNum=attributeValueMapper.insert(attrValue);
		
		long valueId=attrValue.getValueId();  //获取属性值 id		
		
		//（2）保存到 类目-属性-属性值表中  item_attribute_attr_value
		CategoryAttrValue categoryAttrValue=new CategoryAttrValue();
		categoryAttrValue.setCid(cid);
		categoryAttrValue.setAttrId(attrId);
		categoryAttrValue.setValueId(valueId);
		categoryAttrValue.setStatus((byte)VALID);
		categoryAttrValue.setCreated(new Date());
		categoryAttrValue.setSortNumber((long)1);
		categoryAttrValueMapper.insert(categoryAttrValue);		
		
	}

	/**
	 * @see com.ecp.service.back.ICategoryAttrService#getByAttrId(java.lang.Long)
	 * 根据属性ID查询类目属性表
	 */
	@Override
	public CategoryAttr getByAttrId(Long attrId) {
		Example example = new Example(CategoryAttr.class);
		example.createCriteria().andEqualTo("attrId", attrId);
		List<CategoryAttr> list = categoryAttrMapper.selectByExample(example);
		if(!list.isEmpty() && list.size()==1){
			return list.get(0);
		}
		return null;
	}

}