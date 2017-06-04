<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Advanced Tables -->
<div class="card">
	<div class="card-action">导入客户信息列表</div>
	<div class="card-content">
		<div class="table-responsive" style="overflow: hidden">
			<table class="table table-striped table-bordered table-hover" id="dataTables-import-customer">
				<thead>
					<tr>
						<th>客户姓名</th>
						<th>电话</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${customerList}" var="customer">
						<tr>
							<td>${customer.cusName}</td>
							<td>${customer.mobile}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</div>
<!--End Advanced Tables -->

<!-- JS Scripts-->
<%@ include file="../common/headJs.jsp"%>

<script type="text/javascript">

	/* 初始化列表分页插件 */
	$('#dataTables-import-customer').dataTable();
	
</script>
