package com.ecp.service.impl.front;

import java.util.ArrayList;
import java.util.List;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.springframework.stereotype.Service;

import com.ecp.dao.ItemMapper;
import com.ecp.entity.Item;
import com.ecp.service.front.IItemService;
import com.ecp.service.impl.AbstractBaseService;

@Service
public class ItemServiceImpl extends AbstractBaseService<Item, Long> implements IItemService {

	ItemMapper itemMapper;

	/**
	 * @param mapper
	 *            the mapper to set set方式注入
	 */
	public void setItemMapper(ItemMapper itemMapper) {
		this.itemMapper = itemMapper;
		this.setMapper(itemMapper);
	}

	@Override
	public List<Item> getItemByBrandAndAttr(Long cid, List<Long> brands, List<String> attrValPairs) {

		return itemMapper.getItemByBrandAndAttr(cid, brands, attrValPairs);
	}

	@Override
	public Item getItemById(Long itemId) {
		Item record = new Item();
		record.setItemId(itemId);
		return itemMapper.selectOne(record);

	}

	@Override
	public List<Item> getItemByKeywords(String keywords) {
		// 读取关键字 分词
		List<Word> words = WordSegmenter.seg(keywords);
		List<String> keywordList = new ArrayList<String>();
		for (Word word : words) {
			keywordList.add(word.toString());
		}

		return itemMapper.getItemByKeywords(keywordList);

	}

	@Override
	public List<Item> getItemByBrandIds(List<Long> ids) {
		return itemMapper.getItemByBrandIds(ids);

	}

	@Override
	public List<Item> getItemByBrandAndCid(List<Long> brands, List<Long> cids) {
		return itemMapper.getItemByBrandAndCid(brands, cids);

	}

	@Override
	public List<Item> getItemByKeywordsAndBrandAndCid(String keywords, List<Long> brands, List<Long> cids) {
		// 读取关键字 分词
		List<Word> words = WordSegmenter.seg(keywords);
		List<String> keywordList = new ArrayList<String>();
		for (Word word : words) {
			keywordList.add(word.toString());
		}
		return itemMapper.getItemByKeywordsAndBrandAndCid(keywordList,brands,cids);
	}

}
