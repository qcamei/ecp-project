<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<table class="table table-striped table-bordered table-hover " id="category-attr-table"
	style="width: 100%;"center">
	<thead style="width: 98%; padding-top: 80px;">
		<tr>
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
				<td>${categoryAttr.attr_name}</td>
				<td>${categoryAttr.attr_type}</td>
				<td>${categoryAttr.option_type}</td>
				<td>${categoryAttr.input_type}</td>
				<td>${categoryAttr.sort_number}</td>
				<td class="center ">
					<div style="text-align: center;; height: auto;">
						<button class="btn btn-default btn-primary"
							onclick="javascript:selectCategoryAttrValItem(${categoryAttr.cid}, ${categoryAttr.attr_id});">编辑属性值</button>
						<button class="btn btn-default btn-info"
							onclick="javascript:selectCategoryAttr(${categoryAttr.cid}, ${categoryAttr.attr_id});">编辑属性</button>	
						<button class="btn btn-default btn-danger"
							onclick="javascript:deleteCategoryAttr(${categoryAttr.cid}, ${categoryAttr.attr_id});">删除</button>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

	<script>
		//在此处执行加载数据的函数
		$(function() {

			//当点击编辑按钮时，在一个新页中打开叶子结点的编辑页面
			$("#btnadd").click(function(){
				window.open("http://localhost/ecp-back/back/attr/addcategoryattr/" + $("#cid").val(), "_blank");
			});
			//当点击编辑按钮时，在一个新页中打开叶子结点的编辑页面
			$("#btnrefresh").click(function(){
				location.reload();
			});
			

		});
		
		/*
		 * 点击类目属性值页面中的页码执行此函数
		 * 		函数功能：根据页码数请求当前页内容
		 */
		function searchCateAttrValByPageNum(params){
			var action = "back/user/selectItems";
			//util.loading();
			$("#category-attr-value-item").load(action, params, function(){
				console.log("类目属性值列表加载完成！");
			});
		}
		
	</script>
	
<%@include file="../../../common/pagehelper.jsp"%><!-- 分页页面 -->
