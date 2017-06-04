<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>商品管理</title>
<%@ include file="../../../common/headCss.jsp"%>
<script type="text/javascript">
	window.UEDITOR_HOME_URL = "/ecp-back/tools/ueditor/"; //一定要用这句话，否则你需要去ueditor.config.js修改路径的配置信息
</script>

<script type="text/javascript" charset="utf-8"
	src="tools/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="tools/ueditor/ueditor.all.min.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8"
	src="tools/ueditor/lang/zh-cn/zh-cn.js"></script>
<!-- 日期工具 -->
<script type="text/javascript" src="static/calendar/WdatePicker.js"></script>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12">
				<div class="card">
					<div class="tabs-container" id="tabs-243687">
						<ul class="nav nav-tabs" id="top_tab">
							<li class="active"><a data-toggle="tab" href="#tab-1"
								aria-expanded="true"> 商品列表</a></li>
							<li class="" onclick="javascript:resetFun();"><a
								data-toggle="tab" href="#tab-2" aria-expanded="false">添加/编辑</a></li>
						</ul>
						<div class="tab-content">
							<div id="tab-1" class="tab-pane active">
								<div class="card-content">
									<div class="table-responsive"
										style="background: #fff; margin-top: 10px; border: 1px solid #e5e5e5;">
										<div id="dataTables-example_wrapper"
											class="dataTables_wrapper form-inline" role="grid">
											<div id="item-div" style="margin: 20px">
												<%@ include file="itemManageTable.jsp"%>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div id="tab-2" class="tab-pane">

								<div class="tabs-container" style="margin-top: 20px;">
									<div class="modal-dialog" style="width: 100%;">
										<div class="modal-content animated fadeIn">
											<div class="modal-header">
												<h4 class="modal-title">商品信息</h4>
											</div>
											<div class="modal-body">
												<form class="form-horizontal" id="save-form">
													<input type="hidden" id="item-id" name="itemId" value="" />
													<div class="form-group">
														<label class="col-sm-2 control-label">商品名称</label>
														<div class="col-sm-10">
															<input type="text" id="item-name" name="itemName"
																class="form-control" placeholder="商品名称" />
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label">关键字</label>
														<div class="col-sm-10">
															<input type="text" id="keywords" name="keywords"
																class="form-control" placeholder="关键字" />
														</div>
													</div>

													<style>
/* lanmu */
.item-category li {
	list-style: none;
	cursor: pointer;
	padding: 3px 10px;
}

.item-category li.current {
	font-weight: 700;
	color: #000;
	background: #ccc;
}

.item-category ul {
	border: 1px solid #999;
	height: 104px;
	overflow-y: auto;
	padding: 0;
}
/* biaoti */
.fieldset {
	position: relative;
	border: 1px solid #ccc;
	padding: 10px;
	margin: 10px 0;
}

.fieldset .legend {
	position: absolute;
	left: 80px;
	top: -15px;
	padding: 0 10px;
	background: #fff;
	font-weight: 700;
	font-size: 18px;
}
</style>


													<div class="form-group item-category">
														<label class="col-sm-2 control-label">类目</label>
														<div class="col-sm-3">
															<ul id="first-category">
																<c:forEach items="${categoryList}" var="category">
																	<li id="first-${category.cid}" class=""
																		onclick="javascript:selectCategoryByPid(${category.cid}, 1);">${category.cName}<b
																		class="pull-right">></b></li>
																</c:forEach>
															</ul>
														</div>
														<div class="col-sm-3">
															<ul id="second-category">

															</ul>
														</div>
														<div class="col-sm-3">
															<ul id="third-category">

															</ul>
														</div>
														<input type="hidden" id="item-cid" name="cid" value="" />
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label">&nbsp;</label>
														<div class="col-sm-10" id="current-select-category">
															当前选择的类目： <b id="first"></b> <b id="second"></b> <b
																id="third"></b>
														</div>
													</div>

													<!-- <fieldset>
													    <legend>【使用方法】</c>
													    身高：<input type="text" />
													    体重：<input type="text" />
													  </fieldset> -->

													<div id="attr-page">
														<%-- <%@ include file="attrPage.jsp"%> --%>
													</div>
													<div id="sku-page">
														<%-- <%@ include file="skuPage.jsp"%> --%>
													</div>

													<div class="form-group">
														<label class="col-sm-2 control-label">商品简介</label>

														<div class="col-sm-10">
															<input type="tel" class="form-control" id="introduction"
																name="introduction" />
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label">商城指导价格</label>

														<div class="col-sm-10">
															<input type="text" id="guide-price" name="guidePrice"
																class="form-control">
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label">市场价格</label>

														<div class="col-sm-10">
															<input type="text" id="market-price" name="marketPrice"
																class="form-control">
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label">成本价格</label>

														<div class="col-sm-10">
															<input type="text" id="market-price2" name="marketPrice2"
																class="form-control">
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label">库存数量</label>
														<div class="col-sm-10">
															<input type="text" id="inventory" name="inventory"
																class="form-control">
														</div>
													</div>
													<!-- <div class="form-group">
														<label class="col-sm-2 control-label">上架状态</label>
														<div class="col-sm-10">
															<select class="form-control" id="state" name="state">
																<option value="0">上架</option>
																<option value="1">下架</option>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label">推荐商品</label>
														<div class="col-sm-10">
															<select class="form-control" id="isrecommend"
																name="isrecommend">
																<option value="0">推荐</option>
																<option value="1">非推荐</option>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label">新品</label>
														<div class="col-sm-10">
															<select class="form-control" id="isnew" name="isnew">
																<option value="0">新品</option>
																<option value="1">非新品</option>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label">热卖商品</label>
														<div class="col-sm-10">
															<select class="form-control" id="ishot" name="ishot">
																<option value="0">热卖</option>
																<option value="1">非热卖</option>
															</select>
														</div>
													</div> -->
													<div class="form-group">
														<label class="col-sm-2 control-label">缩略图</label>
														<div class="col-sm-10">
															<input type="file" id="product-img" name=""
																class="form-control">
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label">创建时间</label>
														<div class="col-sm-10">
															<input type="text" id="create-time-str"
																name="createtimestr"
																class="datetimepicker datetime form-control"
																readonly="readonly" placeholder="创建时间"
																onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
														</div>
													</div>
													<div class="form-group">
														<label class="col-md-2 control-label">描述</label>
														<div class="col-md-10 ">
															<script id="item-ueditor" type="text/plain"
																style="width: 100%; height: 500px;"></script>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-2 control-label">&nbsp;</label>
														<div class="col-sm-10">
															<button type="button" class="btn btn-primary"
																id="save-submit-btn">保存</button>
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

		<%@ include file="../../../common/headJs.jsp"%>
		<script type="text/javascript" src="static/js/itemManage.js"></script>
</body>
</html>