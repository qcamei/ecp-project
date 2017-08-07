<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<table class="table table-striped table-bordered table-hover " id="product-table"
	style="width: 100%;"center">
	<thead style="width: 98%; padding-top: 80px;">
		<tr role="row">
			<th><input type="checkbox" name="checkbox" id="checkbox" onclick="javascript:checkAll(this);">
				<label for="checkbox">ALL</label>
			</th>
			<th>ID</th>
			<th>类目</th>
			<th>品牌</th>
			<th>名称</th>
			<th>状态</th>
			<th>商品指导价</th>
			<th>市场价</th>
			<th>成本价</th>
			<th>库存量</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagehelper.list}" var="item">
			<tr>
				<td><input type="checkbox" name="checkbox2" id="item-td-${item.item_id}" onclick="javascript:checkOne();" value="${item.item_id}"></td>
				<td>${item.item_id}</td>
				<td>${item.c_name}</td>
				<td>${item.brand_name}</td>
				<%-- <td>${item.cid}</td>
				<td>${item.brand}</td> --%>
				<td>${item.item_name}</td>
				<td>
					<c:if test="${item.item_status==null || item.item_status==''}">
						<button type="button" class="btn btn-primary" id="item-status-up-${item.item_id}" onclick="javascript:updateItemStatus(${item.item_id}, 4);">上架</button>	
						<button type="button" class="btn btn-danger" id="item-status-down-${item.item_id}" onclick="javascript:updateItemStatus(${item.item_id}, 5);">下架</button>
					</c:if>
					<c:if test="${item.item_status==4}">
						<button type="button" class="btn btn-primary" id="item-status-up-${item.item_id}" onclick="javascript:updateItemStatus(${item.item_id}, 4);" disabled="disabled">上架</button>	
						<button type="button" class="btn btn-danger" id="item-status-down-${item.item_id}" onclick="javascript:updateItemStatus(${item.item_id}, 5);">下架</button>
					</c:if>
					<c:if test="${item.item_status==5}">
						<button type="button" class="btn btn-primary" id="item-status-up-${item.item_id}" onclick="javascript:updateItemStatus(${item.item_id}, 4);">上架</button>	
						<button type="button" class="btn btn-danger" id="item-status-down-${item.item_id}" onclick="javascript:updateItemStatus(${item.item_id}, 5);" disabled="disabled">下架</button>
					</c:if>
				</td>
				<td>￥${item.guide_price}</td>
				<td>￥${item.market_price}</td>
				<td>￥${item.market_price2}</td>
				<td>${item.inventory}</td>
				<td class="center">
					<button type="button" class="btn btn-primary" onclick="javascript:selectDetails(${item.item_id}, ${item.cid});">详情</button>	
					<button type="button" class="btn btn-danger" onclick="javascript:deleteInfoFun(${item.item_id});">删除</button>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@include file="../../../common/pagehelper.jsp"%><!-- 分页页面 -->
