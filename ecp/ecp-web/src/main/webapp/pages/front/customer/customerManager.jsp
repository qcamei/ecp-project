<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../common/headCss.jsp"%>
<div class='container'>
	<div class="row" style='margin-top: 20px;'>
		<div class="col-md-12">
			<div class="tabbable" id="tabs-243687">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#panel-631030" data-toggle="tab">列表</a></li>
					<li><a href="#panel-909746" data-toggle="tab" onclick="javascript:resetFun();">添加/修改</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-631030">
						<div class="panel panel-default">
							<div class="panel panel-info">
								<!-- <div class="panel-heading">
								<h3 class="panel-title">邀请函管理</h3>
							</div> -->
								<div class="panel-body">
									<!-- Advanced Tables -->
									<div class="card">
										<div class="card-action">客户管理
											<div class="btn btn-danger" style="float: right;" onclick="deleteAll();">一键删除</div>
										</div>
										<div class="card-content">
											<div class="table-responsive" style="overflow: hidden">
												<table class="table table-striped table-bordered table-hover" id="dataTables-customer">
													<thead>
														<tr>
															<th style="width: 5%;">ID</th>
															<th style="width: 10%;">客户名</th>
															<th style="width: 10%;">客户类型</th>
															<th style="width: 15%;">客户电话</th>
															<th style="width: 10%;">客户登录名</th>
															<th style="width: 15%;">创建时间</th>
															<th style="width: 15%;">修改时间</th>
															<th style="width: 20%;">操作</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${results}" var="customer">
															<tr>
																<td>${customer.customerId}</td>
																<td>${customer.cusName}</td>
																<td>${customer.cusType==1?"企业":"个人"}</td>
																<td>${customer.mobile}</td>
																<td>${customer.loginName}</td>
																<td><fmt:formatDate type="both" value="${customer.createTime}" /></td>
																<td><fmt:formatDate type="both" value="${customer.updateTime}" /></td>
																<td>
																	<button type="button" class="btn btn-info" onclick="detailCustomer(${customer.customerId});">详情</button>&nbsp;&nbsp;
																	<button type="button" class="btn btn-danger" onclick="deleteCustomer(${customer.customerId});">删除</button>
																</td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>
									<!--End Advanced Tables -->
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="panel-909746">
						<div class="panel panel-default">
							<div class="panel panel-info">
								<div class="panel-body">
									<div class="row clearfix">
										<div class="col-xs-8 col-md-offset-1">
											<form id="customerForm" action="" method="post" class="form-horizontal">
												<input type="hidden" id="customerId" name="customerId" readonly="readonly" style="width: 98%;" />
												<div class="form-group">
													<label for="cusName" class="col-sm-2 control-label">客户姓名:</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="cusName" name="cusName" placeholder="客户姓名">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label" style=""> 客户类型</label>

													<div class="col-sm-10">
														<select class="form-control m-b" name="cusType" id="cusType">
															<option value="1">企业</option>
															<option value="2" selected="selected">个人</option>
														</select>
													</div>
												</div>
												<div class="form-group">
													<label for="cusName" class="col-sm-2 control-label">手机号:</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="mobile" name="mobile" placeholder="手机号">
													</div>
												</div>
												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-10">
														<center>
															<button type="button" class="btn btn-default" id="save-submit-btn">提交</button>
														</center>
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- JS Scripts-->
<%@ include file="../common/headJs.jsp"%>

<script>
	/* 初始化列表分页插件 */
	$('#dataTables-customer').dataTable();
    
</script>
<script src="static/js/customerManager.js"></script>
