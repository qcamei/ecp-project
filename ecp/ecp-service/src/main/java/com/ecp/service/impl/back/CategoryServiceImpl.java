package com.ecp.service.impl.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ecp.bean.CategoryAttrBean;
import com.ecp.bean.CategoryTreeNode;
import com.ecp.dao.CategoryMapper;
import com.ecp.entity.Category;
import com.ecp.service.back.ICategoryBrandService;
import com.ecp.service.back.ICategoryService;
import com.ecp.service.impl.AbstractBaseService;

import tk.mybatis.mapper.entity.Example;

@Service("categoryServiceBean")
public class CategoryServiceImpl extends AbstractBaseService<Category, Integer> implements ICategoryService {

	private CategoryMapper categoryMapper;

	/**
	 * @param categoryMapper the categoryMapper to set
	 * set方式注入
	 */
	public void setCategoryMapper(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
		this.setMapper(categoryMapper);
	}

	@Resource(name="categoryBrandServiceBean")
	private ICategoryBrandService iCategoryBrandService;// 类目品牌
	
	/** 
	 * (non-Javadoc)
	 * @see com.ecp.service.ICategoryService#deleteById(java.lang.Long)
	 * 根据主键删除（如果删除节点中有子节点一起删除）
	 */
	@Override
	@Transactional
	public int deleteById(Long id) {
		try {
			int rows = 0;
			Category category = categoryMapper.selectByPrimaryKey(id);
			if(category.getHasLeaf()==1){//如果是叶子节点（没有子节点）
				//如果此类目为三级目录则删除类目相关联的品牌
				iCategoryBrandService.deleteByThirdLevCid(category.getCid());
				rows = 1;
			}else{
				rows = deleteNodes(id);
			}
			
			if(rows>0){
				rows = categoryMapper.deleteByPrimaryKey(id);
			}
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return 0;
	}
	
	/**
	 * 循环删除
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unused")
	@Transactional
	private int deleteNodes(Long pid) throws Exception{
		System.out.println("删除节点 pid："+pid);
		//LmCategory category = lmCategoryMapper.selectByPrimaryKey(id);
		Example example = new Example(Category.class);
		example.createCriteria().andEqualTo("parentCid", pid);
		List<Category> categoryList = categoryMapper.selectByExample(example);
		int rows = 1;
		for(int i=0; i<categoryList.size(); i++){
			Category cate = categoryList.get(i);
			System.out.println("for循环 类目："+cate.toString());
			if(cate.getHasLeaf()==2){//如果不是叶子节点（有子节点）
				rows = deleteNodes(cate.getCid());
			}
			//rows = deleteNodes(cate.getCid());
			if(rows>0){
				rows = categoryMapper.deleteByPrimaryKey(cate.getCid());
				if(rows>0){
					if(cate.getHasLeaf()==1){//如果是叶子节点（没有子节点）
						//如果此类目为三级目录则删除类目相关联的品牌
						iCategoryBrandService.deleteByThirdLevCid(cate.getCid());
					}
				}else{
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return 0;
				}
			}else{
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return 0;
			}
		}
		return rows;
	}
	
	/* (非 Javadoc)
	 * Description:此处的算法需要进一步的改进
	 * @see com.donglicms.service.front.ICategoryService#getCategoryTree()
	 */
	@Override
	public List<CategoryTreeNode> getCategoryTree() {
		final long FIRST_LEVEL_PARENT_ID = 0; // 一级节点的父ID默认为0

		
		//返回的一级目录列表
		List<CategoryTreeNode> categoryList = new ArrayList<CategoryTreeNode>();

		
		// 查询一级结点  均采用上层数据对象的属性
		Example example = new Example(Category.class);
		example.createCriteria().andEqualTo("parentCid", FIRST_LEVEL_PARENT_ID);
		example.orderBy("sortNumber");		
		List<Category> list = categoryMapper.selectByExample(example);

		for (Category firstLevelNode : list) { // 迭代一级结点

			CategoryTreeNode categoryTreeNode1 = new CategoryTreeNode(); // 生成类目树结点
			ArrayList<CategoryTreeNode> subCategoryList2 = new ArrayList<CategoryTreeNode>(); // 生成当前结点的子结点
			
			Category category=new Category();
			category.setCid(firstLevelNode.getCid());
			category.setcName(firstLevelNode.getcName());

			categoryTreeNode1.setCategory(category); // 加入当前结点
			categoryTreeNode1.setSubCategoryList(subCategoryList2);

			categoryList.add(categoryTreeNode1);

			
			//System.out.println("first level cid is:"+firstLevelNode.getCid());
			
			// 查询二级结点
			Example example2 = new Example(Category.class);
			example2.createCriteria().andEqualTo("parentCid", firstLevelNode.getCid());
			example2.orderBy("sortNumber");
			List<Category> list2 = categoryMapper.selectByExample(example2);
			
			/*System.out.println("second level number:"+list2.size());*/

			for (Category secondLevelNode : list2) { // 迭代二级类目

				CategoryTreeNode categoryTreeNode2 = new CategoryTreeNode(); // 生成类目结点
				ArrayList<CategoryTreeNode> subCategoryList3 = new ArrayList<CategoryTreeNode>(); // 生成当前结点的子结点

				Category category2=new Category();
				category2.setCid(secondLevelNode.getCid());
				category2.setcName(secondLevelNode.getcName());
				
				/*System.out.println("second level node cid:"+secondLevelNode.getCid());*/
				
				categoryTreeNode2.setCategory(category2); // 加入当前结点
				categoryTreeNode2.setSubCategoryList(subCategoryList3); // 设置当前结点的子结点列表
				
				subCategoryList2.add(categoryTreeNode2);

				// 查询三级结点  均采用上层业务对象的属性
				Example example3 = new Example(Category.class);
				example3.createCriteria().andEqualTo("parentCid", secondLevelNode.getCid());
				example3.orderBy("sortNumber");
				
				List<Category> list3 = categoryMapper.selectByExample(example3);

				for (Category thirdLevelNode : list3) { // 迭代三级类目

					CategoryTreeNode categoryTreeNode3 = new CategoryTreeNode(); // 生成类目树结点
					categoryTreeNode3.setCategory(thirdLevelNode);
					categoryTreeNode3.setSubCategoryList(null);

					subCategoryList3.add(categoryTreeNode3);
				}

			}

		}

		return categoryList;

	}

	@Override
	public List<Map<String, Object>> getAllCategory() {
		
		return categoryMapper.getAllCategory();
		
	}

	@Override
	public List<CategoryAttrBean> getCategoryAttrListByCid(Long cid) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * (non-Javadoc)
	 * @see com.ecp.service.ICategoryService#selectByPid(java.lang.Long)
	 * 根据父ID查询类目
	 */
	@Override
	public List<Category> selectByPid(Long parentCid) {
		Example example = new Example(Category.class);
		example.createCriteria().andEqualTo("parentCid", parentCid);
		return categoryMapper.selectByExample(example);
	}

	/**
	 * @see com.ecp.service.back.ICategoryService#selectByLev(int)
	 * 根据级别查询类目
	 */
	@Override
	public List<Category> selectByLev(int lev) {
		Example example = new Example(Category.class);
		example.createCriteria().andEqualTo("lev", lev);
		return categoryMapper.selectByExample(example);
	}

	/**
	 * @see com.ecp.service.back.ICategoryService#getAll(java.lang.String)
	 * 获取所有类目（默认为sort_number正序，多个用英文逗号隔离）
	 * 		sort参数说明：表字段+空格+排序（正序：ASC,倒序：DESC）
	 */
	@Override
	public List<Category> getAll(String sort) {
		if(StringUtils.isBlank(sort)){
			sort = "sort_number ASC";
		}
		Example example = new Example(Category.class);
		example.setOrderByClause(sort);
		return categoryMapper.selectByExample(example);
	}

}
