<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="common/headCss.jsp"%>

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
										<div class="card-action">邀请函管理</div>
										<div class="card-content">
											<div class="table-responsive" style="overflow: hidden">
												<table class="table table-striped table-bordered table-hover" id="dataTables-invitation">
													<thead>
														<tr>
															<th style="width: 5%;">ID</th>
															<th style="width: 20%;">邀请函主题</th>
															<th style="width: 10%;">主办方联系人</th>
															<th style="width: 10%;">联系方式</th>
															<th style="width: 20%;">活动地点</th>
															<th style="width: 15%;">活动日期</th>
															<th style="width: 30%;">操作</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${invitationList}" var="invitation">
															<tr>
																<td>${invitation.invitationId}</td>
																<td>${invitation.invTitle}</td>
																<td>${invitation.linkMan}</td>
																<td>${invitation.phone}</td>
																<td>${invitation.actAddress}</td>
																<td>${invitation.actDate}</td>
																<td>
																	<button type="button"  class="btn btn-info" onclick="javascript:selectDetails(${invitation.invitationId});">详情</button> &nbsp;
																	<button type="button" class="btn btn-danger" onclick="javascript:deleteInfoFun(${invitation.invitationId});">删除</button>
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
											<form class="form-horizontal" id="save-form" role="form">
												<input type="hidden" id="invitation-id" name="invitationId" value="" />
												<!-- <div class="form-group">
												<label class="col-md-2 control-label">ID</label>
												<div class="col-md-10 ">
													<input type="hidden" class="form-control" id="invitation-id" name="invitationId" placeholder="ID" readonly="readonly" />
												</div>
											</div> -->
												<div class="form-group">
													<label class="col-md-3 control-label">邀请函主题</label>
													<div class="col-md-9 ">
														<input type="text" class="form-control" id="inv-title" name="invTitle" placeholder="邀请函主题" />
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">主办方联系人</label>
													<div class="col-md-9 ">
														<input type="text" class="form-control" id="link-man" name="linkMan" placeholder="联系人" />
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-3 control-label">联系方式</label>
													<div class="col-sm-9">
														<input type="text" class="form-control" id="phone" name="phone" placeholder="手机号" />
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">地点</label>
													<div class="col-md-9 ">
														<input type="text" class="form-control" id="act-address" name="actAddress" placeholder="活动地点" />
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">内容</label>
													<div class="col-md-9 ">
														<textarea style="height:80px;width: 90%;" rows="100" cols="" id="inv-content" name="invContent" placeholder="内容"></textarea>
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">开始日期</label>
													<div class="col-md-9 ">
														<input type="text" class="datetimepicker datetime form-control" id="act-date" name="actDate" readonly="readonly" placeholder="活动开始日期"  />
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">结束日期</label>
													<div class="col-md-9 ">
															<input type="text" class="datetimepicker datetime form-control"  name="endDate" id="end-date"  placeholder="活动结束日期" readonly> 
													</div>
												</div>
												<div class="form-group">
													<label class="col-md-3 control-label">&nbsp;</label>
													<div class="col-md-9 ">
														<button type="button" class="btn btn-primary" id="save-submit-btn">保存</button>
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
<%@ include file="common/headJs.jsp"%>

<script type="text/javascript">
	/* 初始化列表分页插件 */
	$('#dataTables-invitation').dataTable();
	$('#act-date').datepicker({
		format: 'yyyy.mm.dd',
		weekStart: 1,
		autoclose: true,
		todayBtn: 'linked',
		language: 'cn'
		});
	$('#end-date').datepicker({
		format: 'yyyy.mm.dd',
		weekStart: 1,
		width:100,
	    height:100,
		autoclose: true,
		todayBtn: 'linked',
		language: 'cn'
		}).on('changeDate',function(ev){
		var actDate=$("#act-date").val().toString();
		while(actDate.indexOf(".")>0){
			actDate = actDate.replace(".","/");
		}
		var start=new Date(actDate);
		var endTime = ev.date.valueOf();
		end = endTime;
		if(end<start){
			alert("活动结束日期不能早于活动开始日期！！");
			return;
		}else{
		}
		});

s</script>
<script src="static/js/invitation.js"></script>
