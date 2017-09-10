package com.ecp.service.front;

import java.util.List;
import java.util.Map;

import com.ecp.entity.Orders;
import com.ecp.service.IBaseService;

public interface IOrderService extends IBaseService<Orders, Long> {
	/**
	 * @Description 生成新订单
	 * @param buyerId  买家ID
	 * @param buyerName 买家姓名
	 * @param orderId  订单号
	 */
	public void createNewOrder(long buyerId,String buyerName,String orderId);
	
	/**
	 * @Description 根据登录用户的ID获取订单
	 * @param buyerId
	 * @return
	 */
	public List<Orders>  selectOrderByUserId(long buyerId);
	
	/**
	 * @Description 根据订单号获取订单ID(pk)
	 * @param orderNo  订单号
	 * @return  order'id(pk)
	 */
	public long getIdByOrderNo(String orderNo);
	
	/**
	 * @Description 根据订单时间及订单处理状态查询(指定用户的)
	 * @param buyerId  登录用户
	 * @param orderTimeCond 订单时间条件值
	 * @param dealStateCond 合同处理状态条件值
	 * @return  订单列表
	 */
	public List<Orders> selectOrderByOrderTimeAndDealState(long buyerId,int orderTimeCond,int dealStateCond);
	
	
	/**
	 * @Description 根据订单时间及订单处理状态查询(所有用户-用于后台订单管理)
	 * @param orderTimeCond 订单时间条件值
	 * @param dealStateCond 合同处理状态条件值
	 * @return
	 */
	public List<Orders> selectAllOrderByOrderTimeAndDealState(int orderTimeCond,int dealStateCond);
	
	
	/**
	 * @Description 根据订单号读取订单
	 * @param orderNo
	 * @return
	 */
	public Orders selectOrderByOrderNo(String orderNo);
	
	/**
	 * @Description 后台:读取订单列表
	 * @param orderTimeCond 时间段条件
	 * @param dealStateCond 处理状态条件
	 * @param searchTypeValue  搜索条件类型
	 * @param condValue  搜索值
	 * @return
	 */
	public List<Map<String,Object>> selectOrder(int orderTimeCond,int dealStateCond,int searchTypeValue,String condValue);
	
	
}
