<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="tabs-container" id="tabs-add-item">
	<ul class="nav nav-tabs" id="top_tab">
		<li class="active"><a data-toggle="tab" href="#tab-3"
			aria-expanded="true">选择类目</a></li>
		<li class="" onclick="javascript:resetFun();"><a
			data-toggle="tab" href="#tab-4" aria-expanded="false">商品信息</a></li>
	</ul>
	<div class="tab-content">
		<div id="tab-3" class="tab-pane active">
			<div class="card-content">
				<div class="table-responsive"
					style="background: #fff; margin-top: 10px; border: 1px solid #e5e5e5;">
					<div id="dataTables-example_wrapper"
						class="dataTables_wrapper form-inline" role="grid">
						<div id="" style="margin: 20px">
							<style>
/* 类目 */
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

							<div class="container">
								<div class="row clearfix">
									<div class="col-md-12 column">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h3 class="panel-title">选择类目</h3>
											</div>
											<div class="panel-body item-category">
												<div class="col-sm-4">
													<ul id="first-category">
														<c:forEach items="${categoryList}" var="category">
															<li id="first-${category.cid}" class=""
																onclick="javascript:selectCategoryByPid(${category.cid}, 1);">${category.cName}<b
																class="pull-right">></b></li>
														</c:forEach>
													</ul>
												</div>
												<div class="col-sm-4">
													<ul id="second-category">

													</ul>
												</div>
												<div class="col-sm-4">
													<ul id="third-category">

													</ul>
												</div>
											</div>
											<div class="panel-footer">
												<div class="" id="current-select-category">
													当前选择的类目： <b id="first"></b> <b id="second"></b> <b
														id="third"></b>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div>
								<label class="col-sm-2 control-label">&nbsp;</label>
								<div class="col-sm-10" id="">
									<button type="button" class="btn btn-default btn-primary"
										id="next-step-btn">下一步</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="tab-4" class="tab-pane">

			<div class="tabs-container" style="margin-top: 20px;">
				<div class="modal-dialog" style="width: 100%;">
					<div class="modal-content animated fadeIn">
						<div class="modal-header">
							<h4 class="modal-title">商品信息</h4>
						</div>
						<div class="modal-body">
							<form class="form-horizontal" id="save-form">
								<!-- 商品ID -->
								<input type="hidden" id="item-id" name="itemId" value="" />
								<!-- 商品类目ID -->
								<input type="hidden" id="item-cid" name="cid" value="" />
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
								<div class="form-group">
									<label class="col-sm-2 control-label">商品产地</label>

									<div class="col-sm-10">
										<input type="text" id="origin" name="origin"
											class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">包装清单</label>

									<div class="col-sm-10">
										<input type="text" id="packing-list" name="packingList"
											class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">商品体积</label>

									<div class="col-sm-10">
										<input type="text" id="volume" name="volume"
											class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">商品毛重</label>

									<div class="col-sm-10">
										<input type="text" id="weight" name="weight"
											class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">密度</label>

									<div class="col-sm-10">
										<input type="text" id="weight-unit" name="weightUnit"
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
									<label class="col-md-2 control-label">缩略图</label>
									<div class="col-md-5 ">
										<input type="file" id="item" name="pictureImg"
											onchange="javascript:validateImgFileSizeFun(this);"
											accept="image/*" /> 
											<input type="hidden" id="brand-logo-url" name="pictureUrl" />
									</div>
									<!-- <div class="col-md-5">
										<button type="button" class="btn btn-primary" onclick="javascript:deleteImgFun('logo');">删除</button>
									</div> -->
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-2 control-label">&nbsp;</label>
									<div class="col-sm-10" id="thumbnail-portrait"></div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">创建时间</label>
									<div class="col-sm-10">
										<input type="text" id="create-time-str" name="createtimestr"
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
<script>
/*
 * 点击下一步按钮时执行，显示添加商品基本信息选项卡
 */
$("#next-step-btn").click(function(){
	var cid = $("#item-cid").val();
	var url = "back/category/selectAttrAndValue";
	var params = {"cid": cid};
	$("#attr-page").load(url, params, function(){
		console.log("加载属性页面完成");
		$('#tabs-add-item a[href="#tab-4"]').tab('show');
	});
	
});
</script>