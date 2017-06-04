package com.ecp.web.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apdplat.word.WordSegmenter;
import org.apdplat.word.segmentation.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ecp.bean.CategoryAttrBean;
import com.ecp.bean.CategoryBrandBean;
import com.ecp.bean.SearchCondBean;
import com.ecp.entity.Category;
import com.ecp.entity.Item;
import com.ecp.entity.ItemPicture;
import com.ecp.service.front.IBrandService;
import com.ecp.service.front.ICategoryAttrService;
import com.ecp.service.front.ICategoryBrandService;
import com.ecp.service.front.ICategoryService;
import com.ecp.service.front.IItemPictureService;
import com.ecp.service.front.IItemService;

/**
 * @ClassName ProductSearchController
 * @Description 产品查询-控制器（包括：分类查询、关键字查询、产品代码）
 * @author Administrator
 * @Date 2017年5月6日 下午9:43:10
 * @version 1.0.0
 */
@Controller
@RequestMapping("/front/search")
public class SearchController {
	final String RESPONSE_THYMELEAF = "thymeleaf/front/";
	final String RESPONSE_JSP = "jsps/front/";

	/**
	 * 查询的数据格式为：
	 * 
	 * { "cid": "1", "brands": "1,2,3", "attrs": [ { "attr_id": "1",
	 * "attr_vals": "1,2,3" }, { "attr_id": "2", "attr_vals": "5,6,7" } ] }
	 * 对应的POJO类 SearchCondBean
	 * 
	 */

	@Autowired
	IItemService itemService;
	@Autowired
	ICategoryBrandService cateBrandService;
	@Autowired
	ICategoryAttrService categoryAttrService;
	@Autowired
	IItemPictureService itemPictureService;
	@Autowired
	ICategoryService categoryService;
	@Autowired
	IBrandService brandService;

	/**
	 * @Description 通过关键字查询
	 * @param keywords
	 *            关键字
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/keywords", method = RequestMethod.GET)
	public String keywordsSearch(String keywords, Model model) {
		String url = RESPONSE_THYMELEAF + "search_no_result";
		
		// 读取关键字  分词
				List<Word> words = WordSegmenter.seg(keywords);
				
				for(Word word:words){ System.out.println("---------------分词结果是："+word.toString()); }
				
				List<String> keywordList = new ArrayList<String>();
				for (Word word : words) {
					keywordList.add(word.toString());
				}
		

		if(keywords.trim()=="")
			return url;
		
		// 先查询类目 查询优先级最高
		List<Category> cateList = categoryService.getCategoryByKeywords(keywords);
		if (cateList.size() > 0) {
			String cid = cateList.get(0).getCid().toString();
			url = "redirect:/front/search/category/" + cid;
		}

		List<Map<String, Object>> item_pict_list = new ArrayList<Map<String, Object>>(); // 向前台传递的数据

		// 按品牌查询
		List<Map<String, Object>> filterList = new ArrayList<Map<String, Object>>();
		List<CategoryBrandBean> brandList = new ArrayList<CategoryBrandBean>(); // 品牌列表
		// 如果查询不到相应的类目，查询类目中的品牌
		List<CategoryBrandBean> cateBrandList = null;
		if (cateList.size() == 0) {
			// 根据关键词（品牌）查询类目品牌(叶子结点)
			cateBrandList = cateBrandService.getCategoryByBrand(keywords); // 品牌列表
			if (cateBrandList.size() > 0) {
				// 过滤重复品牌（获取品牌列表）
				// TODO 可通过数据库来处理
				List<Long> brandIds = new ArrayList<Long>(); // 品牌IDS
				Map<Long, Long> brandMap = new HashMap<Long, Long>();
				for (CategoryBrandBean cateBrand : cateBrandList) {
					if (brandMap.get(cateBrand.getBrandId()) == null) {
						brandMap.put(cateBrand.getBrandId(), cateBrand.getBrandId());
						brandList.add(cateBrand);
						brandIds.add(cateBrand.getBrandId());
					}
				}

				// 获取二级类目ID
				Map<Long, Long> secondLevMap = new HashMap<Long, Long>();
				for (CategoryBrandBean cateBrand : cateBrandList) {
					if (secondLevMap.get(cateBrand.getSecondLevCid()) == null) {
						secondLevMap.put(cateBrand.getSecondLevCid(), cateBrand.getSecondLevCid());
					}
				}
				// 读取二级类目
				for (long secondLevCid : secondLevMap.keySet()) {
					Category secondCategory = categoryService.getCategoryByCid(secondLevCid);
					Map<String, Object> condMap = new HashMap<String, Object>();
					condMap.put("secondCategory", secondCategory);
					// 读取三级类目列表
					List<Category> thirdCategoryList = categoryService.getCategoryByParentCid(secondLevCid);
					condMap.put("thirdCategoryList", thirdCategoryList);

					filterList.add(condMap);
				}

				List<Item> itemList = itemService.getItemByBrandIds(brandIds); // 查询商品列表
				/**
				 * --------------------------- 按SPU查询SPU的picture
				 * -----------------------------
				 */
				for (Item item : itemList) {
					List<ItemPicture> pictList = itemPictureService.getItemPictureByItemId(item.getItemId());

					Map<String, Object> itemPictMap = new HashMap<String, Object>();
					itemPictMap.put("item", item);
					itemPictMap.put("pict", pictList);
					item_pict_list.add(itemPictMap);
				}

				model.addAttribute("brandList", brandList);
				model.addAttribute("filterList", filterList);
				model.addAttribute("itemList", item_pict_list);

				url = RESPONSE_THYMELEAF + "search_brand_result";
			}

		}

		// 按关键字查询
		if ((cateBrandList==null || cateBrandList.size() == 0) && (cateList.size() == 0)) {
			List<Item> itemList = itemService.getItemByKeywords(keywords); // 查询商品列表
			if (itemList.size() > 0) {
				// 生成筛选条件，按品牌搜索的方式处理
				// 获取商品列表中的品牌id
				Map<Long, Long> tempMap = new HashMap<Long, Long>();
				List<Long> brandIdList = new ArrayList<Long>();
				for (Item item : itemList) {
					if (tempMap.get(item.getBrand()) == null) {
						tempMap.put(item.getBrand(), item.getBrand());
						brandIdList.add(item.getBrand());
					}
				}

				// 以下与品牌查询相同
				cateBrandList = cateBrandService.getCategoryByBrandIds(brandIdList); // 品牌列表
				// 过滤重复品牌（获取品牌列表）
				// TODO 可通过数据库来处理
				Map<Long, Long> brandMap = new HashMap<Long, Long>();
				for (CategoryBrandBean cateBrand : cateBrandList) {
					if (brandMap.get(cateBrand.getBrandId()) == null) {
						brandMap.put(cateBrand.getBrandId(), cateBrand.getBrandId());
						brandList.add(cateBrand);
					}
				}

				// 获取二级类目ID
				Map<Long, Long> secondLevMap = new HashMap<Long, Long>();
				for (CategoryBrandBean cateBrand : cateBrandList) {
					if (secondLevMap.get(cateBrand.getSecondLevCid()) == null) {
						secondLevMap.put(cateBrand.getSecondLevCid(), cateBrand.getSecondLevCid());
					}
				}
				// 读取二级类目
				for (long secondLevCid : secondLevMap.keySet()) {
					Category secondCategory = categoryService.getCategoryByCid(secondLevCid);
					Map<String, Object> condMap = new HashMap<String, Object>();
					condMap.put("secondCategory", secondCategory);
					// 读取三级类目列表
					List<Category> thirdCategoryList = categoryService.getCategoryByParentCid(secondLevCid);
					condMap.put("thirdCategoryList", thirdCategoryList);

					filterList.add(condMap);
				}

				/**
				 * --------------------------- 按SPU查询SPU的picture
				 * -----------------------------
				 */
				for (Item item : itemList) {
					List<ItemPicture> pictList = itemPictureService.getItemPictureByItemId(item.getItemId());

					Map<String, Object> itemPictMap = new HashMap<String, Object>();
					itemPictMap.put("item", item);
					itemPictMap.put("pict", pictList);
					item_pict_list.add(itemPictMap);
				}

				model.addAttribute("brandList", brandList);
				model.addAttribute("filterList", filterList);
				model.addAttribute("itemList", item_pict_list);

				url = RESPONSE_THYMELEAF + "search_brand_result";
			}

		}

		return url;

	}

	/**
	 * @Description 用户选择筛选条件时 处理
	 * @param searchBean
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/categorycond", method = RequestMethod.POST)
	// @ResponseBody @RequestBody
	public String categoryCond(SearchCondBean searchBean, Model model) {

		/*
		 * JSONObject jsonObject = new JSONObject(); jsonObject.put("message",
		 * "查询成功"); jsonObject.put("status", "success");
		 * 
		 * return jsonObject;
		 */
		// System.out.println("-------------cid is:"+searchBean.getCid());

		/**
		 * 
		 * (1)筛选条件（动态） 筛选条件分为两种: 类目的品牌条件 类目的其它属性 (2)当前条件下的SPU列表
		 * 
		 * 采用数据进行模拟： 三个数据列表： 品牌列表 类目其它属性列表（包括属性名称及属性值列表） SPU列表
		 */

		HashMap<String, Integer> attrMap = (HashMap<String, Integer>) processSelectedAttrCond(searchBean.getAttrs());

		Long categoryId = Long.parseLong(searchBean.getCid());

		/**
		 * ------------------------------ 筛选条件数据
		 * --------------------------------
		 */
		// （1）类目-品牌列表
		if (searchBean.getBrands().equals("")) {
			List<Map<String, String>> brandList = cateBrandService.getBrandByCid(categoryId);
			model.addAttribute("brandList", brandList);
		} else {
			model.addAttribute("brandList", null); // 用户已经选择品牌，查询结果中不再显示此筛选条件（筛选条件-品牌）
		}

		// (2)属性-属性值列表
		List<Map<String, Object>> attrValueList = new ArrayList<Map<String, Object>>();

		// List<CategoryAttrBean>
		// cateAttrList=categoryAttrService.getCategoryAttrListByCid(categoryId);
		// //read类目属性列表
		List<CategoryAttrBean> cateAttrList = categoryAttrService.getCategoryAttrByCidAndType(categoryId, 1); // read类目属性列表
		for (CategoryAttrBean attrBean : cateAttrList) {
			// 不再显示用户已经选择的筛选条件
			if (attrMap.get(attrBean.getAttr_id().toString()) == null) {
				// 属性名称
				HashMap<String, Object> attrValMap = new HashMap<String, Object>();
				attrValMap.put("attrName", attrBean.getAttr_name());
				// 属性值列表
				List<Map<String, String>> valueList = categoryAttrService.getCategoryAttrValList(categoryId,
						attrBean.getAttr_id());
				attrValMap.put("valueList", valueList);

				attrValueList.add(attrValMap); // 加入列表中
			}

		}

		model.addAttribute("attrValueList", attrValueList);

		/**
		 * ----------------------------- 查询类目下的SPU -----------------------------
		 */
		List<Map<String, Object>> item_pict_list = new ArrayList<Map<String, Object>>(); // 向前台传递的数据
		// (1)品牌ID列表 条件列表
		List<Long> brands = null;
		if (!searchBean.getBrands().equals("")) {
			brands = new ArrayList<Long>();
			String[] brandArr = convertStrToArray(searchBean.getBrands());
			for (String str : brandArr) {
				brands.add(Long.parseLong(str));
			}
		}
		// (2)属性-属性值 条件列表
		List<String> attrValPair = null;
		if (!searchBean.getAttrs().equals("")) {
			attrValPair = new ArrayList<String>();
			String[] attrArr = convertStrToArray(searchBean.getAttrs());
			for (String str : attrArr) {
				attrValPair.add(str);
			}
		}

		List<Item> itemList = itemService.getItemByBrandAndAttr(categoryId, brands, attrValPair); // 查询spu
		// System.out.println("-------list size is:"+itemList.size());

		/**
		 * --------------------------- 按SPU查询SPU的picture
		 * -----------------------------
		 */
		for (Item item : itemList) {
			List<ItemPicture> pictList = itemPictureService.getItemPictureByItemId(item.getItemId());

			Map<String, Object> itemPictMap = new HashMap<String, Object>();
			itemPictMap.put("item", item);
			itemPictMap.put("pict", pictList);
			item_pict_list.add(itemPictMap);
		}

		// 向页面传递的数据
		model.addAttribute("itemList", item_pict_list);
		model.addAttribute("cid", categoryId);
		model.addAttribute("brandCond", searchBean.getBrands());
		model.addAttribute("attrCond", searchBean.getAttrs());

		return RESPONSE_THYMELEAF + "search_category_result";
	}

	/**
	 * @Description 处理用户已经选择的属性条件
	 * @param attrConds
	 *            attr_id:val_id,attr_id:val_id
	 * @return attr_id:此属性条件个数
	 */
	private Map<String, Integer> processSelectedAttrCond(String attrConds) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		String[] attrValArr = attrConds.split(",");
		for (String attrVal : attrValArr) {
			String[] attrPair = attrVal.split(":");
			if (map.get(attrPair[0]) == null) {
				map.put(attrPair[0], 1);
			} else {
				map.put(attrPair[0], map.get(attrPair[0]) + 1);
			}
		}

		return map;

	}

	/**
	 * @Description 使用String的split 方法
	 * @param str
	 *            采用","分隔的字符串
	 * @return
	 */
	private String[] convertStrToArray(String str) {
		String[] strArray = null;
		strArray = str.split(","); // 拆分字符为"," ,然后把结果交给数组strArray
		return strArray;
	}

	/**
	 * @Description 通过类目进行查询
	 * @param categoryId
	 *            通过目录查询的参数
	 * @param model
	 *            spring model
	 * @return
	 */
	@RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
	public String categorySearch(@PathVariable("categoryId") Long categoryId, Model model) {

		/* List<Word> words = WordSegmenter.seg("杨尚川是APDPlat应用级产品开发平台的作者"); */
		/*
		 * List<Word> words = WordSegmenter.seg("联想戴尔"); for(Word word:words){
		 * System.out.println("分词结果是："+word.toString()); }
		 */

		/**
		 * 
		 * (1)筛选条件（动态） 筛选条件分为两种: 类目的品牌条件 类目的其它属性 (2)当前条件下的SPU列表
		 * 
		 * 采用数据进行模拟： 三个数据列表： 品牌列表 类目其它属性列表（包括属性名称及属性值列表） SPU列表
		 */

		/**
		 * ------------------------------ 筛选条件数据
		 * --------------------------------
		 */
		// （1）类目-品牌列表
		List<Map<String, String>> brandList = cateBrandService.getBrandByCid(categoryId);
		model.addAttribute("brandList", brandList);

		// (2)属性-属性值列表
		List<Map<String, Object>> attrValueList = new ArrayList<Map<String, Object>>();

		// List<CategoryAttrBean>
		// cateAttrList=categoryAttrService.getCategoryAttrListByCid(categoryId);
		// //read类目属性列表
		List<CategoryAttrBean> cateAttrList = categoryAttrService.getCategoryAttrByCidAndType(categoryId, 1); // read类目属性列表
		for (CategoryAttrBean attrBean : cateAttrList) {
			// 属性名称
			HashMap<String, Object> attrValMap = new HashMap<String, Object>();
			attrValMap.put("attrName", attrBean.getAttr_name());
			// 属性值列表
			List<Map<String, String>> valueList = categoryAttrService.getCategoryAttrValList(categoryId,
					attrBean.getAttr_id());
			if(valueList.size()>0)
				System.out.println("test-----------"+valueList.get(0).get("attr_name"));
			attrValMap.put("valueList", valueList);

			attrValueList.add(attrValMap); // 加入列表中
		}

		model.addAttribute("attrValueList", attrValueList);

		/**
		 * ----------------------------- 查询类目下的SPU -----------------------------
		 */
		List<Map<String, Object>> item_pict_list = new ArrayList<Map<String, Object>>(); // 向前台传递的数据

		List<Long> brands = new ArrayList<Long>(); // (1)品牌ID列表 条件列表
		List<String> params = new ArrayList<String>();// (2)属性-属性值 条件列表
		List<Item> itemList = itemService.getItemByBrandAndAttr(categoryId, null, null); // 查询spu
		// System.out.println("-------list size is:"+itemList.size());

		/**
		 * --------------------------- 按SPU查询SPU的picture
		 * -----------------------------
		 */
		for (Item item : itemList) {
			List<ItemPicture> pictList = itemPictureService.getItemPictureByItemId(item.getItemId());

			Map<String, Object> itemPictMap = new HashMap<String, Object>();
			itemPictMap.put("item", item);
			itemPictMap.put("pict", pictList);
			item_pict_list.add(itemPictMap);
		}

		// 向页面传递的数据
		model.addAttribute("itemList", item_pict_list);
		model.addAttribute("cid", categoryId);
		model.addAttribute("brandCond", "");
		model.addAttribute("attrCond", "");

		return RESPONSE_THYMELEAF + "search_category_result";

	}

	private SearchCondBean getEntity(String resp) {
		JSONObject jsonObj = (JSONObject) JSON.parse(resp);
		SearchCondBean entity = JSONObject.toJavaObject(jsonObj, SearchCondBean.class);
		return entity;
	}

	

}
