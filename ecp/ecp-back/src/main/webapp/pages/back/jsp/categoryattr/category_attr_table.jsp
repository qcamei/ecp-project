<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container-fluid" style="margin-top: 20px;">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<button type="button" id="add-btn" class="btn btn-default btn-primary" onclick="javascript:addCategoryAttr();">添加属性</button>
					</h3>
				</div>
				<div class="panel-body">
					<table class="table table-striped table-bordered table-hover "
						id="category-attr-table" style="width: 100%;"center">
						<thead style="width: 98%; padding-top: 80px;">
							<tr>
								<th>
									<input type="checkbox" name="checkbox" id="attr-all-checkbox" onclick="javascript:checkAttrAll(this);">
									<label for="attr-all-checkbox">ALL</label>
								</th>
								<th>ID</th>
								<th>属性名称</th>
								<th>类型</th>
								<th>是否必填</th>
								<th>是否多选</th>
								<th>排序号</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pagehelper.list}" var="categoryAttr">
								<tr>
									<td>
										<input type="checkbox" name="checkbox2" id="attr-td-${categoryAttr.attr_id}" onclick="javascript:checkAttrOne();" value="${categoryAttr.attr_id}">
									</td>
									<td>${categoryAttr.attr_id}</td>
									<td id="attr-name-${categoryAttr.attr_id}">${categoryAttr.attr_name}</td>
									<td>${categoryAttr.attr_type}</td>
									<td>${categoryAttr.option_type}</td>
									<td>${categoryAttr.input_type}</td>
									<td>${categoryAttr.sort_number}</td>
									<td class="center ">
										<div style="text-align: center;; height: auto;">
											<button class="btn btn-default btn-primary"
												onclick="javascript:selectCategoryAttrValItem(${categoryAttr.cid}, ${categoryAttr.attr_id});">编辑属性值</button>
											<button class="btn btn-default btn-info"
												onclick="javascript:selectAttrDetails(${categoryAttr.cid}, ${categoryAttr.attr_id});">编辑属性</button>
											<button class="btn btn-default btn-danger"
												onclick="javascript:deleteCategoryAttr(${categoryAttr.cid}, ${categoryAttr.attr_id});">删除</button>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- 用于保存用户选择属性的ID，用于分页查询 -->
<input type="hidden" id="attribute-id" value="" />

	<script type="text/javascript">
		//在此处执行加载数据的函数
		$(function() {

		});
		
		/*
		 * 编辑类目属性和类目属性值信息
		 * 		函数功能：判断用户选择是否选择类目，如果已选择则根据类目ID查询类目属性，如果未选择则提示用户
		 */
		function selectCategoryAttrValItem(cid, attrId){
			var attrName = $("#attr-name-"+attrId).text();
			$("#third-category-attr-name").text("   >   "+attrName);
			$("#attribute-id").val(attrId);
			$('#tabs-819447 a[href="#panel-355190"]').tab('show');//显示编辑类目属性值选项卡
			var params = new Object();
			selectCategoryAttrValItemByPagehelper(params);
		}
		
		/**
		 * 点击类目属性值页面中的页码执行此函数
		 * 		函数功能：根据页码数请求当前页内容
		 */
		function selectCategoryAttrValItemByPagehelper(params){
			var cid = $("#category-id").val();
			var attrId = $("#attribute-id").val();
			if(attrId==null || attrId==""){
				util.message("请选择属性！");
				return;
			}
			var url = "back/attr/getCategoryAttrValItem";
			params.categoryId = cid;
			params.attributeId = attrId;
			params.pagehelperFun = "selectCategoryAttrValItemByPagehelper";
			//util.loading();
			$("#category-attr-value-item").load(url, params, function(){
				console.log("类目属性值列表加载完成！");
			});
		}
		
		/**
		 * 点击添加属性按钮执行
		 *		函数功能：打开添加属性对话框
		 */
		function addCategoryAttr(){
			$("#attr-modal-title").text("添加属性");
			var cid = $("#category-id").val();
			$("#attr-form-cid").val(cid);
			$('#edit-category-attr-modal').modal('show');
		}
		
		/*
		 * 重置
		 */
		function resetCateAttrValTab(){
			$("#third-category-attr-name").text("");//清空用户选择的类目属性
			$("#category-attr-value-item").empty();//清空类目属性值列表
		}
		
	</script>

<%@include file="../../../common/pagehelper.jsp"%><!-- 分页页面 -->

<!-- 添加/编辑属性对话框 -->
<div class="modal fade" id="edit-category-attr-modal" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="javascript:resetAttrForm();">×</button>
				<h4 class="modal-title" id="attr-modal-title">标题</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="save-attr-form">				
					<input type="hidden" name="cid" id="attr-form-cid" value="">
					<input type="hidden" name="attrId" id="attr-form-attr-id" value="">
					<input type="hidden" name="id" id="attr-form-cate-attr-id" value="">
					<!-- 属性名称------ -->
					<div class="form-group">
						<label for="attr-form-attr-name" class="col-sm-2 control-label">属性名称</label>
						<div class="col-sm-10">
							<input class="form-control" name="attrName" id="attr-form-attr-name" type="text" />
						</div>
					</div>					
					
					<!--------- 属性类型   下拉框------ -->
					<div class="form-group">
						<label for="attr-form-attr-type" class="col-sm-2 control-label">属性类型</label>
						<div class="col-sm-10">
							<select class="form-control" name="attrType" id="attr-form-attr-type">
								<option value="1">关键属性</option>
								<option value="2">不变属性</option>
								<option value="3">可为属性</option>
								<option value="4">销售属性</option>
							</select>
						</div>
					</div>

					<!-- 是否必填  单选按钮 -->
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">是否必填</label>
						<div class="col-sm-10" id="attr-form-option-type">
							<input type="radio" name="optionType" id="attr-form-option-type-one" value="1" checked="checked" /> 
							<label for="attr-form-option-type-one">必填</label>
							<input type="radio" name="optionType" id="attr-form-option-type-two" value="2" />
							<label for="attr-form-option-type-two">非必填</label>
						</div>
					</div>


					<!-- 是否多选  下拉框  -->
					<div class="form-group">
						<label for="attr-form-input-type" class="col-sm-2 control-label">属性类型</label>
						<div class="col-sm-10">
							<select class="form-control" name="inputType" id="attr-form-input-type">
								<option value="1">单选</option>
								<option value="2">多选</option>
								<option value="3">可输入</option>								
							</select>
						</div>
					</div>

					<!-- 排序号 -->					
					<div class="form-group">
						<label for="attr-form-sort" class="col-sm-2 control-label">排列序号</label>
						<div class="col-sm-10">
							<input type="number" class="form-control" name="sortNumber" id="attr-form-sort" />
						</div>
					</div>
					
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" onclick="javascript:resetAttrForm();">关闭</button>
				<button type="button" class="btn btn-primary" id="save-attr-submit-btn">保存</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="static/js/category_attr.js"></script>