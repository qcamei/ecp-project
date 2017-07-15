package com.ecp.web.back;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.ecp.bean.ContractAttrValueBean;
import com.ecp.bean.ContractOrderItemBean;
import com.ecp.bean.ContractStateType;
import com.ecp.common.SessionConstants;
import com.ecp.common.util.NumberToCN;
import com.ecp.entity.Contract;
import com.ecp.entity.ContractAttrValue;
import com.ecp.entity.ContractAttribute;
import com.ecp.entity.ContractItems;
import com.ecp.entity.Orders;
import com.ecp.entity.User;
import com.ecp.entity.UserExtends;
import com.ecp.service.front.IContractAttrValueService;
import com.ecp.service.front.IContractAttributeService;
import com.ecp.service.front.IContractItemsService;
import com.ecp.service.front.IContractService;
import com.ecp.service.front.IOrderItemService;
import com.ecp.service.front.IOrderService;
import com.ecp.service.front.IUserAgentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;



/**
 * @ClassName ContractController
 * @Description 合同管理
 * @author Administrator
 * @Date 2017年6月22日 上午11:52:24
 * @version 1.0.0
 */
@Controller
@RequestMapping("/back/contract")
public class ContractController {
	final String RESPONSE_THYMELEAF_BACK="thymeleaf/back/";
	final String RESPONSE_JSP="jsps/front/";
	
	private final int PAGE_SIZE = 5;

	private final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	IOrderItemService orderItemService;	
	@Autowired
	IContractAttributeService contractAttributeService;
	@Autowired
	IContractService contractService;
	@Autowired
	IContractItemsService contractItemsService;
	@Autowired
	IContractAttrValueService contractAttrValueService;
	@Autowired
	IOrderService orderService;
	@Autowired
	IUserAgentService userAgentService;
	
	/**
	 * @Description 合同详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/detail")
	public String contract_detail(long id,Model model){
		
		model.addAttribute("contractId", id);
		
		return RESPONSE_THYMELEAF_BACK+"contract_detail";
	}
	
	
	/**
	 * @Description 合同详情模块
	 * @param id  合同id(pk)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/detailtable")
	public String contract_detail_table(long id,Model model){
		//(1)合同对象
		Contract contract=contractService.selectByPrimaryKey(id);		
		model.addAttribute("contract", contract);
		
		//(2) order:select order by orderNo
		Orders order=orderService.selectOrderByOrderNo(contract.getOrderNo());
		//model.addAttribute("order", order);
		
		//(3)合同商品条目(根据合同编号)
		List<Map<String,String>> contractItems=contractItemsService.selectItemsByContractNo(contract.getContractNo());
		model.addAttribute("contractItems", contractItems);
		
		//(4)代理商
		UserExtends agent=userAgentService.getUserAgentByUserId(order.getBuyerId());
		model.addAttribute("agent", agent);
		
		return RESPONSE_THYMELEAF_BACK+"contract_detail_table";
	}
	
	
	
	
	/**
	 * @Description 显示-合同列表
	 * @param model
	 * @param pageNum  页号
	 * @param pageSize 页大小
	 * @return  
	 */
	@RequestMapping(value = "/show")
	public String contract_show(Model model, Integer pageNum, Integer pageSize) {
		
		if(pageNum==null || pageNum==0)
		{
			pageNum=1;
			pageSize=PAGE_SIZE;
		}
		
		// 查询 并分页		
		PageHelper.startPage(pageNum, pageSize); // PageHelper			

		List<Contract> contractList = contractService.selectAll();
		PageInfo<Contract> pageInfo = new PageInfo<Contract>(contractList);// (使用了拦截器或是AOP进行查询的再次处理)
		
		setPageInfo(model, pageInfo); // 向前台传递分页信息

		model.addAttribute("contractList", contractList);

		return RESPONSE_THYMELEAF_BACK + "contract_show";
	}
	
	
	
	
	private void setPageInfo(Model model, PageInfo pageInfo) {
		// 获得当前页
		model.addAttribute("pageNum", pageInfo.getPageNum());
		// 获得一页显示的条数
		model.addAttribute("pageSize", pageInfo.getPageSize());
		// 是否是第一页
		model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
		// 获得总页数
		model.addAttribute("totalPages", pageInfo.getPages());
		// 是否是最后一页
		model.addAttribute("isLastPage", pageInfo.isIsLastPage());
	}
	
	
	/**
	 * @Description 创建合同
	 * @param id  订单主键:id
	 * @param orderId 订单编号 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/add")
	public String contract_add(long id,String orderId,Model model){
		//（1）获取关于订单中详细条目
		List<Map<String,String>> orderItemList = orderItemService.selectItemsByOrderId(orderId);
		model.addAttribute("orderItemList", orderItemList);
		
		//(2)获取合同属性
		//TODO 查询条件：template_id=1  此处暂时直接查询，而后进行修改
		List<ContractAttribute> contractAttrList=contractAttributeService.selectAll();
		model.addAttribute("contractAttrList", contractAttrList);
		
		return RESPONSE_THYMELEAF_BACK+"contract_add";
	}
	
	/**
	 * @Description 合同预览
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/preview")
	public String contract_preview(String contractAttrVals,String orderItems,Model model){
		
		List<ContractAttrValueBean> contractAttrValueList=getEntity1(contractAttrVals);
		Map<String,String> attrValueMap=  convertAttrValueList(contractAttrValueList);
		
		List<ContractOrderItemBean> contractOrderItemBeanList=getEntity2(orderItems);
		
		BigDecimal contractItemTotal=calcContractItemTotalPrice(contractOrderItemBeanList);  //计算合同总额
		String totalCN= NumberToCN.number2CNMontrayUnit(contractItemTotal);
		
		
		model.addAttribute("attrValue", attrValueMap);  //合同属性
		model.addAttribute("contractItemList", contractOrderItemBeanList);  //合同商品列表
		model.addAttribute("contractItemTotal",contractItemTotal);
		model.addAttribute("contractItemTotalCN",totalCN);
		
		
		return RESPONSE_THYMELEAF_BACK+"contract_preview";
	}
	
	/**
	 * @Description 创建新合同
	 * @param contractAttrVals  合同属性（JSON格式）
	 * @param orderItems  合同商品列表（JSON格式）
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/create")
	/*@ResponseBody*/
	public Object contract_create(String contractAttrVals,String orderItems,Model model,HttpServletRequest request){
		
		List<ContractAttrValueBean> contractAttrValueList=getEntity1(contractAttrVals);  //合同属性列表
		Map<String,String> attrValueMap=  convertAttrValueList(contractAttrValueList);
		
		List<ContractOrderItemBean> contractOrderItemBeanList=getEntity2(orderItems);  //合同商品列表
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionConstants.USER);
		
		//(1)创建合同
		String orderNo=getOrderNo(contractOrderItemBeanList.get(0));   //获取订单号
		long contractTemplateId=getTemplateId(contractAttrValueList.get(0));  //获取合同模板ID
		
		String contractNo=getContractNo();  //生成合同号
		Long contractId=createContract(contractNo,contractTemplateId,orderNo,user.getId());
		
		//(2)插入合同属性值
		addContractAttrValues(contractId,contractAttrValueList);
		
		//(3)插入合同商品条目
		addContractItems(contractOrderItemBeanList,contractNo);
		
		//(4)在订单中加入合同信息
		updateOrderContract(orderNo,contractId,contractNo);
		
		return RESPONSE_THYMELEAF_BACK+"contract_create_success";
	}
	
	/**
	 * @Description 生成合同后，更新相关订单合同信息
	 * @param orderNo   订单编号
	 * @param contractId  合同's id (pk)
	 * @param contractNo  合同编号
	 */
	private void updateOrderContract(String orderNo,long contractId,String contractNo){
		//根据订单号获取订单id(pk)
		long id= orderService.getIdByOrderNo(orderNo);  //根据订单号获取订单ID
		Orders order=new Orders();
		
		order.setId(id);
		order.setContractNo(contractNo);
		order.setContractId(contractId);
		order.setContractState((byte)ContractStateType.CREATED_YES); //状态：已建合同
		
		orderService.updateByPrimaryKeySelective(order);		
	}
	
	
	/**
	 * @Description 增加合同属性
	 * @param contractId  合同ID
	 * @param contractAttrValueList 合同属性列表
	 */
	private void addContractAttrValues(Long contractId,List<ContractAttrValueBean> contractAttrValueList){
		for(ContractAttrValueBean attrValue:contractAttrValueList){
			
			ContractAttrValue record=new ContractAttrValue();
			
			record.setTemplateId(attrValue.getTemplateId());  //模板id
			record.setAttrId(attrValue.getId());  //属性id
			record.setAttrValue(attrValue.getAttrValue());  //属性值			
			record.setContractId(contractId);  //合同id(非合同号)
			
			
			contractAttrValueService.insertSelective(record);
		}
	}
	
	
	
	/**
	 * @Description 增加合同行项目（商品列表）
	 * @param orderItemList 商品列表
	 * @param contractNo  合同号
	 */
	private void addContractItems(List<ContractOrderItemBean> orderItemList,String contractNo){
		for(ContractOrderItemBean orderItem:orderItemList){
			
			
			/*
			 "create_time":"2017-06-27T17:45:12.000+08:00",
							"picture_url":"/static/images/iphone_index.png",
							"item_id":1,
							"num":2,
							"sku_id":2,
							"picture_id":2,
							"picture_status":1,
							"primitive_price":222,
							"sort_number":1,
							"sku_name":"Apple8新品颜色:RED版本:V2","id":59,
							"order_id":"1000000937189443",
							"cid":13,
							"discount_price":0,
							"pay_price":222,
							"pay_price_total":444}, 
			 */
			
			
			ContractItems record=new ContractItems();
			
			record.setCid(orderItem.getCid());
			record.setItemId(orderItem.getItem_id());
			record.setSkuId(orderItem.getSku_id());
			record.setSkuName(orderItem.getSku_name());
			record.setOrderId(orderItem.getOrder_id());
			record.setContractNo(contractNo);
			
			record.setPrimitivePrice(orderItem.getPrimitive_price());  //原始价
			record.setDiscountPrice(orderItem.getDiscount_price());  //价格折减
			record.setPayPrice(orderItem.getPay_price());  //实际支付价格
			record.setNum(orderItem.getNum());  //商品数量
			record.setPayPriceTotal(orderItem.getPay_price_total());  //小计
			
			
			record.setCreateTime(new Date());
			
			contractItemsService.insertSelective(record);
		}
	}
	
	
	
	
	/**
	 * @Description 取得合同号
	 * @param contractItem  合同条目对象
	 * @return  合同编号
	 */
	private String getOrderNo(ContractOrderItemBean contractItem){
		return contractItem.getOrder_id();
	}
	
	/**
	 * @Description  取得合同模板ID
	 * @param attrValue  合同属性值对象
	 * @return 合同模板ID
	 */
	private long getTemplateId(ContractAttrValueBean attrValue){
		return attrValue.getTemplateId();
	}
	
	/**
	 * @Description 创建合同
	 * @param contractNo 合同号
	 * @param contractTemplateId  合同模板id
	 * @param orderNo 订单号
	 * @param createUserId 合同创建者id
	 * @return  合同id
	 */
	private Long createContract(String contractNo,Long contractTemplateId,String orderNo,Long createUserId){
		//生成合同对象
		Contract contract=new Contract();
		
		//设置合同属性
		contract.setContractTemplateId(contractTemplateId);
		contract.setOrderNo(orderNo);
		contract.setContractNo(contractNo);
		//创建用户及时间
		contract.setCreateDate(new Date());
		contract.setCreateUser(createUserId);
		
		//插入记录
		contractService.insertSelective(contract);
		
		return contract.getId();
	}
	
	/**
	 * @Description 生成合同号
	 * 	合同编号格式为：LM-年（四位）-月（两位）-序号（四位）
	 * @return  返回合同号
	 */
	private String getContractNo(){
		return contractService.getContractNo();
	}
	
	/**
	 * @Description 计算合同总金额（优惠前）
	 * @param itemList  合同条目商品列表
	 * @return  合同总金额
	 */
	private BigDecimal calcContractItemTotalPrice(List<ContractOrderItemBean> itemList){
		BigDecimal total=new BigDecimal(0.00);
		for(ContractOrderItemBean item:itemList){
			total=total.add(item.getPay_price_total());
		}
		return total;
	}
	
	
	/**
	 * @Description 将合同属性list转换为map
	 * @param list
	 * @return
	 */
	private Map<String,String> convertAttrValueList(List<ContractAttrValueBean> list){
		Map<String,String> map=new HashMap<String,String>();
		for(ContractAttrValueBean attrValue:list){
			map.put(attrValue.getAttrName(), attrValue.getAttrValue());
		}
		return map;
	}
	
	
	/**
	 * @Description 自JSON生成对象
	 * @param resp
	 * @return
	 */
	private List<ContractAttrValueBean> getEntity1(String resp) {
		List<ContractAttrValueBean> list = JSONArray.parseArray(resp,ContractAttrValueBean.class);
		return list; 
	} 
	
	/**
	 * @Description 自JSON生成对象
	 * @param resp
	 * @return
	 */
	private List<ContractOrderItemBean> getEntity2(String resp) {
		List<ContractOrderItemBean> list =JSONArray.parseArray(resp,ContractOrderItemBean.class);    
        return list;
	} 
	
}
