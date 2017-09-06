<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>类目属性</title>
<%@ include file="../../../common/headCss.jsp"%>
<link rel="stylesheet" href="static/ztree/css/metroStyle/metroStyle.css" type="text/css">
<%@ include file="../../../common/headJs.jsp"%>

</head>
<body class="gray-bg">
	<div class="container-fluid" style="margin-top: 20px;">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div class="tabbable" id="tabs-819446">
					<ul class="nav nav-tabs">
						<li class="active" onclick="javascript:resetCateAttrAndValTab();"><a href="#panel-508461" data-toggle="tab">类目列表</a>
						</li>
						<li class="hide" id="category-attr-li"><a href="#panel-355189" data-toggle="tab">添加/编辑</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="panel-508461">
							<div class="container-fluid" style="margin-top: 20px;">
								<div class="row clearfix">
									<div class="col-md-12 column">
										<div class="panel panel-default">
											<div class="panel-heading">
												<button type="button" class="btn btn-default btn-primary" id="edit-btn" onclick="javascript:selectCategoryAttrIem();">编辑类目属性</button>
											</div>
											<div class="panel-body">
												<ul id="ztree-category" class="ztree"></ul>
												<!-- 类目树 -->
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tab-pane" id="panel-355189">
							<div class="container-fluid" style="margin-top: 20px;">
								<div class="row clearfix">
									<div class="col-md-12 column">
										<div class="panel panel-default">
											<div class="panel-heading">
												<b>您选择的三级类目和属性是：<i id="third-category-name"></i><i id="third-category-attr-name"></i></b>
											</div>
											<div class="panel-body">
												<div class="container-fluid" style="margin-top: 20px;">
													<div class="row clearfix">
														<div class="col-md-12 column">
															<div class="tabbable" id="tabs-819447">
																<ul class="nav nav-tabs">
																	<li class="active" onclick="javascript:resetCateAttrValTab();"><a href="#panel-508462" data-toggle="tab">类目属性列表</a></li>
																	<li><a href="#panel-355190" data-toggle="tab">类目属性值列表</a></li>
																</ul>
																<div class="tab-content">
																	<div class="tab-pane active" id="panel-508462">
																		<div id="category-attr-item">
																			<!-- 类目属性列表 -->
																		</div>
																	</div>
																	<div class="tab-pane" id="panel-355190">
																		<div id="category-attr-value-item">
																			<!-- 类目属性值列表 -->
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
							</div>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<!-- 用于保存用户选择类目的ID -->
<input type="hidden" id="category-id" value="" />
<!-- 用于保存用户选择类目的级别 -->
<input type="hidden" id="category-level" value="" />

<script type="text/javascript" src="static/ztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="static/ztree/js/jquery.ztree.excheck.js"></script>

	<script type="text/javascript">
		var zTreeObj;

		var setting = {
			data : {
				simpleData : {
					enable : true,
					idKey : "cid",
					pIdKey : "parent_cid",
					rootPId : 0
				},
				key : {
					name : "c_name"
				}
			},
			callback : {
				onClick : zTreeOnClick,//节点点击事件 
			}
		};

		var zNodes = ${categoryListJson};
		/* var zNodes=[   
		   {"id":1, "pId":0, "name":"test1"},   
		   {"id":11, "pId":1, "name":"test11"},   
		   {"id":12, "pId":1, "name":"test12"},   
		   {"id":111, "pId":11, "name":"test111"},];  */

		function zTreeOnClick(event, treeId, treeNode) {
			console.log("treeNode:" + JSON.stringify(treeNode));
			console.log(treeNode.cid + ", " + treeNode.c_name + " ," + treeNode.level);
			$("#category-id").val(treeNode.cid);
			$("#category-level").val(treeNode.level);
			$("#third-category-name").text(treeNode.c_name);
		};

		/* 初始化ztree（类目） */
		zTreeObj = $.fn.zTree.init($("#ztree-category"), setting, zNodes);
		
		/* function loadCategoryAttr() {

			$.ajax({
				type : "post", // 提交方式 get/post
				url : "loadcategorytree", // 需要提交的 url
				// dataType: "json",
				data : {},
				success : function(res, status) { // data 保存提交后返回的数据，一般为 json 数据
					console.log("status:" + status);
					if (res != null) {
						var obj = $.parseJSON(res);
						zNodes = obj;
						displayTree();

						if (status == "success") {

						} else {
							alert(obj.result_err_msg);
						}
						//alert("request ok!");
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					/*弹出jqXHR对象的信息
					alert(jqXHR.responseText);
					alert(jqXHR.status);
					alert(jqXHR.readyState);
					alert(jqXHR.statusText);
					/*弹出其他两个参数的信息
					alert(textStatus);
					alert(errorThrown);
				}
			});

		} //end of loadCategoryAttr

		function displayTree() {
			zTreeObj = $.fn.zTree.init($("#ztree-category"), setting, zNodes);
		} */

		var g_cid = 0;
		var g_level = 0;//级别，因为ZTree有该属性，根节点默认值为0；自定义级别时，level级别以ZTree属性为准

		//在此处执行加载数据的函数
		$(function() {

			//loadCategoryAttr();

		});
		
		/*
		 * 查询类目属性列表
		 * 		函数功能：判断用户选择是否选择类目，如果已选择则根据类目ID查询类目属性，如果未选择则提示用户
		 */
		function selectCategoryAttrIem(){
			var level = $("#category-level").val();
			if(level==null || level==""){
				util.message("请选择类目！");
			}else if (level == 2) {
				$("#category-attr-li").removeClass("hide");//隐藏添加编辑类目属性和类目属性值选项卡
				$('#tabs-819446 a[href="#panel-355189"]').tab('show');//显示编辑类目属性选项卡
				var params = new Object();
				selectCategoryAttrItemByPagehelper(params);//获取类目ID查询类目属性
			} else {
				console.log("级别：" + g_level + " （从0开始，0为级别1）");
				util.message("请选择三级类目！");
			}
		}
		
		/*
		 * 点击类目属性页面中的页码执行此函数
		 * 		函数功能：根据页码数请求当前页内容
		 */
		function selectCategoryAttrItemByPagehelper(params){
			var cid = $("#category-id").val();
			if(cid==null || cid==""){
				util.message("请选择类目！");
				return;
			}
			var url = "back/attr/getCategoryAttrItem";
			params.categoryId = cid;
			params.pagehelperFun = "selectCategoryAttrItemByPagehelper";
			//util.loading();
			$("#category-attr-item").load(url, params, function(){
				console.log("类目属性列表加载完成！");
			});
		}
		
		/*
		 * 重置
		 */
		function resetCateAttrAndValTab(){
			$("#third-category-name").text("");//清空用户选择的三级类目
			$("#third-category-attr-name").text("");//清空用户选择的类目属性
			$("#category-attr-item").empty();//清空类目属性列表
			$("#category-attr-value-item").empty();//清空类目属性值列表
			$('#tabs-819447 a[href="#panel-508462"]').tab('show');//显示类目属性列表选项卡
			$('#tabs-819446 a[href="#panel-508461"]').tab('show');//显示类目列表选项卡
			$("#category-attr-li").addClass("hide");//隐藏添加编辑类目属性和类目属性值选项卡
		}
		
	</script>
</body>
</html>