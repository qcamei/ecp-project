package com.ecp.service.impl.back;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ecp.dao.ItemMapper;
import com.ecp.entity.Item;
import com.ecp.service.back.IItemService;
import com.ecp.service.impl.AbstractBaseService;

@Service("itemServiceBean")
public class ItemServiceImpl extends AbstractBaseService<Item, Long> implements IItemService {

	private static final Logger log = Logger.getLogger(ItemServiceImpl.class);
	
	private ItemMapper itemMapper;

	/**
	 * @param itemMapper the itemMapper to set
	 * set方式注入
	 */
	public void setItemMapper(ItemMapper itemMapper) {
		this.itemMapper = itemMapper;
		this.setMapper(itemMapper);
	}

	/** 
	 * (non-Javadoc)
	 * @see com.ecp.service.IProductService#selectItemsByCondition(java.util.Map)
	 * 根据条件查询列表（条件为空时查询所有）
	 */
	@Override
	public List<Map<String, Object>> selectItemsByCondition(Map<String, Object> map) {
		return itemMapper.selectItemsByCondition(map);
	}

	/** 
	 * (non-Javadoc)
	 * @see com.ecp.service.IProductService#deleteByIds(java.lang.String)
	 * 批量删除
	 */
	@Override
	public int deleteByIds(String ids) {
		String[] idArr = ids.split(",");
		int rows = 0;
		for(int i=0; i<idArr.length; i++){
			rows = itemMapper.deleteByPrimaryKey(idArr[i]);
			if(rows<=0){
				log.error("删除 id 为 "+idArr[i]+" 的商品信息异常，回滚sql");
				TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
				return 0;
			}
		}
		return rows;
	}

}
