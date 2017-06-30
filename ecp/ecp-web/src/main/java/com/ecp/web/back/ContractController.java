package com.ecp.web.back;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ecp.bean.AddSkuToOrderBean;
import com.ecp.bean.ContractAttrValueBean;
import com.ecp.bean.ContractOrderItemBean;
import com.ecp.common.util.NumberToCN;
import com.ecp.entity.ContractAttribute;
import com.ecp.service.front.IContractAttributeService;
import com.ecp.service.front.IOrderItemService;



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
	
	/*@Autowired
	ICompanyInfoService companyInfoService;*/
	@Autowired
	IOrderItemService orderItemService;	
	@Autowired
	IContractAttributeService contractAttributeService;
	
	/**
	 * @Description 合同详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/detail")
	public String contract_detail(Model model){
		return RESPONSE_THYMELEAF_BACK+"contract_detail";
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
	
	/**
	 * @Description 合同列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/show")
	public String contract_show(Model model){
		return RESPONSE_THYMELEAF_BACK+"contract_show";
	}
	
	/**
	 * @Description 显示公司信息维护页面
	 * @param model
	 * @param request
	 * @return
	 */
	
	/*@RequestMapping(value="/show" ,method=RequestMethod.GET)
	public String companyInfo_Show(Model model){
			
		CompanyInfo company=companyInfoService.selectByPrimaryKey((long)1);
		model.addAttribute("company", company);		
		
		return RESPONSE_THYMELEAF_BACK+"company_info";
	}*/
	
	/**
	 * @Description 修改客户信息
	 * @param company
	 * @param request
	 * @return
	 */
	/*@RequestMapping(value="/modify" ,method=RequestMethod.POST)
	@ResponseBody
	public Object companyInfo_modify(@RequestBody CompanyInfo company,HttpServletRequest request){
		
		company.setId((long)1);		
		companyInfoService.updateByPrimaryKeySelective(company);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "updated");
		jsonObject.put("status", "success");
		return jsonObject;
	}
*/
	
	
	
}
