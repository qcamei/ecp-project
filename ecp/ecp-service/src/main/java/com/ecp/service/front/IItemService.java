package com.ecp.service.front;

import java.util.List;

import com.ecp.entity.Item;
import com.ecp.service.IBaseService;

public interface IItemService extends IBaseService<Item, Long> {	
	
	
	/**
	 * @Description 查询SPU
	 * @param cid 类目id
	 * @param brands 品牌列表 brand_id
	 * @param attrValPairs attr_id:val_id
	 * @return
	 */
	public List<Item> getItemByBrandAndAttr(Long cid,List<Long> brands,List<String> attrValPairs);
	
	
	/**
	 * @Description 查询SPU
	 * @param brands 品牌id列表
	 * @param cids 三级类目id列表
	 * @return
	 */
	public List<Item> getItemByBrandAndCid(List<Long> brands, List<Long> cids);
	
	
	
	/**
	 * @Description 按SPU关键字、品牌、类目查询
	 * @param keywords
	 * @param brands
	 * @param cids
	 * @return
	 */
	public List<Item> getItemByKeywordsAndBrandAndCid(String keywords,List<Long> brands,List<Long>cids); 
	
	
	
	/**
	 * @Description 通过关键字进行查询
	 * @param keywords  关键字
	 * @return
	 */
	public List<Item> getItemByKeywords(String keywords);
	
	
	/**
	 * @Description 根据品牌id获取SPU
	 * @param ids
	 * @return
	 */
	public List<Item> getItemByBrandIds(List<Long> ids);
	
	
	/**
	 * @Description 根据item id 读取一个SPU
	 * @param itemId
	 * @return
	 */
	public Item getItemById(Long itemId);
	
	
	
}
