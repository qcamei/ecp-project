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
					<li><a href="#panel-909746" data-toggle="tab" onclick="javascript:resetFun();">配置登录账户</a></li>
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
										<div class="card-action">配置登录账户</div>
										<div class="card-content">
											<div class="table-responsive" style="overflow: hidden">
												<table class="table table-striped table-bordered table-hover" id="dataTables-example">
													<thead>
														<tr>
															<th>ID</th>
															<th>客户名</th>
															<th>客户类型</th>
															<th>客户电话</th>
															<th>客户登录名</th>
															<th>创建时间</th>
															<th>修改时间</th>
															<th>操作</th>
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
																	<c:choose>
																		<c:when test="${customer.loginName!=null}">
																			<input type="button" class="btn btn-danger"  disabled="disabled" value="已配置登录账户"/>&nbsp;&nbsp;&nbsp;&nbsp;    
											   						</c:when>
																		<c:otherwise>
																			<button type="button" class="btn btn-danger" onclick="addUser(${customer.customerId},0);">配置登录账户</button>&nbsp;&nbsp;&nbsp;&nbsp;
											   						</c:otherwise>
																	</c:choose>
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
												<input type="hidden" id="loginId" name="loginId" readonly="readonly" style="width: 95%;" />
												<div class="form-group">
													<label for="loginName" class="col-sm-2 control-label">登录名:</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="loginName" name="loginName" placeholder="登录名">
													</div>
												</div>
												<div class="form-group">
													<label for="loginPass" class="col-sm-2 control-label">登录密码:</label>
													<div class="col-sm-10">
														<input type="password" class="form-control" id="loginPass" name="loginPass" placeholder="登录密码">
													</div>
												</div>
												<div class="form-group">
													<label for="email" class="col-sm-2 control-label">电子邮箱:</label>
													<div class="col-sm-10">
														<input type="email" class="form-control" id="email" name="email" placeholder="电子邮箱" value="">
													</div>
												</div>

												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-10">
														<center>
															<button type="button" class="btn btn-default" id="save-submit-btn">提交</button>
														</center>
													</div>
												</div>
												<input type="hidden" id="customerId" name="customerId" value="" />
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

<script type="text/javascript">
	  /* 初始化列表分页插件 */
      $('#dataTables-example').dataTable();
       
</script>
<script src="static/js/loginUser.js"></script>
