<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container-fluid" style="margin-top: 20px;">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="tabs-container" id="tabs-edit-item">
				<ul class="nav nav-tabs" id="top_tab">
					<li class="active"><a data-toggle="tab" href="#tab-5"
						aria-expanded="false">商品基本信息</a></li>
					<li class=""><a data-toggle="tab" href="#tab-6"
						aria-expanded="false">商品图片</a></li>
					<li class=""><a data-toggle="tab" href="#tab-7"
						aria-expanded="false">属性和SKU</a></li>
				</ul>
				<div class="tab-content">
					<div id="tab-5" class="tab-pane active">
						<div class="container-fluid" style="margin-top: 20px;">
							<div class="row clearfix">
								<div class="col-md-12 column">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h3 class="panel-title">商品基本信息</h3>
										</div>
										<div class="panel-body">
											<form class="form-horizontal" id="save-form">
												<!-- 商品ID -->
												<input type="hidden" id="item-id" name="itemId" value="" />
												<!-- 商品类目ID -->
												<!-- <input type="hidden" id="item-cid" name="cid" value="" /> -->
												<div class="form-group">
													<label class="col-sm-2 control-label">商品类目</label>
													<div class="col-sm-10">
														<select class="form-control" id="item-cid" name="cid">
															<c:forEach items="${categoryList}" var="category">
																<option value="${category.cid}">${category.cName}</option>
															</c:forEach>
														</select>
													</div>
												</div>
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
												<!-- <div class="form-group">
											<label class="col-sm-2 control-label">商品简介</label>
											<div class="col-sm-10">
												<input type="tel" class="form-control" id="introduction"
													name="introduction" placeholder="商品简介" />
											</div>
										</div> -->
												<div class="form-group">
													<label class="col-sm-2 control-label">商城指导价格</label>
													<div class="col-sm-10">
														<input type="text" id="guide-price" name="guidePrice"
															class="form-control" placeholder="商城指导价格">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">市场价格</label>
													<div class="col-sm-10">
														<input type="text" id="market-price" name="marketPrice"
															class="form-control" placeholder="市场价格">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">成本价格</label>
													<div class="col-sm-10">
														<input type="text" id="market-price2" name="marketPrice2"
															class="form-control" placeholder="成本价格">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">库存数量</label>
													<div class="col-sm-10">
														<input type="text" id="inventory" name="inventory"
															class="form-control" placeholder="库存数量">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">商品产地</label>
													<div class="col-sm-10">
														<input type="text" id="origin" name="origin"
															class="form-control" placeholder="商品产地">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">包装清单</label>
													<div class="col-sm-10">
														<input type="text" id="packing-list" name="packingList"
															class="form-control" placeholder="包装清单">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">商品体积</label>
													<div class="col-sm-10">
														<input type="text" id="volume" name="volume"
															class="form-control" placeholder="商品体积">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">商品毛重</label>

													<div class="col-sm-10">
														<input type="text" id="weight" name="weight"
															class="form-control" placeholder="商品毛重">
													</div>
												</div>
												<div class="form-group">
													<label class="col-sm-2 control-label">重量单位</label>

													<div class="col-sm-10">
														<input type="text" id="weight-unit" name="weightUnit"
															class="form-control" placeholder="重量单位">
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
												<!-- <div class="form-group">
											<label class="col-sm-2 control-label">&nbsp;</label>
											<div class="col-sm-10">
												<button type="button" class="btn btn-primary"
													id="save-submit-btn">保存</button>
											</div>
										</div> -->
											</form>
										</div>
										<div class="panel-footer">商品基本信息</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="tab-6" class="tab-pane">
						<div class="container-fluid" style="margin-top: 20px;">
							<div class="row clearfix">
								<div class="col-md-12 column">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h3 class="panel-title">商品图片</h3>
										</div>
										<div class="panel-body">
											<form class="form-horizontal" id="">
												<div class="form-group">
													<label class="col-md-2 control-label">缩略图</label>
													<div class="col-md-5 ">
														<input type="file" id="picture-url" name="pictureImg"
															onchange="javascript:validateImgFileSizeFun(this);"
															accept="image/*" multiple="multiple" /> <input
															type="hidden" id="brand-logo-url" name="pictureUrl" />
													</div>
												</div>
												<div class="form-group">
													<label for="name" class="col-sm-2 control-label">&nbsp;</label>
													<div class="col-sm-10" id="thumbnail-portrait"></div>
												</div>
											</form>
										</div>
										<div class="panel-footer">商品图片</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="tab-7" class="tab-pane">
						<div class="container-fluid" style="margin-top: 20px;">
							<div class="row clearfix">
								<div class="col-md-12 column">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h3 class="panel-title">属性和SKU</h3>
										</div>
										<div class="panel-body">
											<form class="form-horizontal" id="">
												<div class="form-group">
													<label for="name" class="col-sm-2 control-label">&nbsp;</label>
													<div class="col-sm-10" id="">
														<div id="attr-page">
															<%-- <%@ include file="attrPage.jsp"%> --%>
														</div>
														<%-- sku页面在属性页面中
											<div id="sku-page">
												<%@ include file="skuPage.jsp"%>
											</div> --%>
													</div>
												</div>
											</form>
										</div>
										<div class="panel-footer">属性和SKU</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
			<!-- <div class="form-group">
				<label for="name" class="col-sm-2 control-label">&nbsp;</label>
				<div class="col-sm-10">
					<button type="button" class="btn btn-primary delet_btn">保存</button>
				</div>
			</div> -->
			<button type="button" class="btn btn-default btn-primary btn-block" id="save-submit-btn">保存</button>
		</div>
	</div>
</div>
<!-- <script type="text/javascript" src="static/js/addItem.js"></script> -->
