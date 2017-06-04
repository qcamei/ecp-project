<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="../common/headCss.jsp"%>

<div class='container'>
	<div class='panel' style="margin-top: 20px;">
		<div class='panel-body'>
			<form id="upload-form" action="" enctype="multipart/form-data" method="post">
				<div class="col-md-3">
					<label for='excelFile' class='btn btn-info'>浏览...</label>&nbsp;&nbsp;&nbsp;&nbsp;<img id="select-excel-img" src='static/images/excel-img.jpg' style="width:36px;height:36px;display:none;" />
					<input type="file" class="form-control" id="excelFile" name="excelFile" style='display:none;' onchange="javascript:changeFile(this);" />
				</div>
				<div class="col-md-9">
					<button type="button" class='btn btn-info' onclick="javascript:importExcelFun();">导入Excel</button>&nbsp;&nbsp;
					<button type="button" class='btn btn-info' onclick="javascript:location.href='static/doc/客户信息模版.rar';">下载客户信息Excel模版</button>
				</div>
			</form>
		</div>
	</div>
	<div class="col-md-12">
		<div id="customer-item"></div>
	</div>
</div>

<!-- JS Scripts-->
<%@ include file="../common/headJs.jsp"%>

<script type="text/javascript">
	/*
	 * 判断用户选择上传的文件类型，只支持上传excel文件(*.xls,*.xlsx)
	 */
	function changeFile(uploadFile) {
		var filename = uploadFile.value;
		var mime = filename.toLowerCase().substr(filename.lastIndexOf("."));
		if (mime != ".xls" && mime != ".xlsx") {
			show_err_msg("请选择excel文件!");
			uploadFile.outerHTML = uploadFile.outerHTML;
			$("#select-excel-img").css("display", "none");
		}else{
			$("#select-excel-img").css("display", "inline-block");
		}
	}
	
	/*
	 * 导入excel文件ajax提交
	 */
	function importExcelFun() {
		var fileVal = $("#excelFile").val();
        if(fileVal==""){
        	show_err_msg("请选择要上传的Excel文件！");
            return;
        }
		show_loading();
		var reqUrl = "front/import/excel/importExcel";
		var params = new Object();
		$("#upload-form").ajaxSubmit({
			type : "post",
			url : reqUrl,
			data : {

			},
			success : function(res) {
				console.log(res);
				var result = $.parseJSON(res);
				if (result.result_code == "success") {
					var fileEle = document.getElementById("excelFile");
					fileEle.outerHTML = fileEle.outerHTML;
					$("#select-excel-img").css("display", "none");
					show_err_msg(result.result_msg);
					loadUploadCustomerItemFun(result.uploadExcelPath);
				} else {
					show_err_msg(result.result_err_msg);
				}
			},
		});
	}
	
	/*
	 * 导入excel文件（客户信息）成功后加载导入客户信息列表
	 */
	function loadUploadCustomerItemFun(uploadExcelPath){
		var params = new Object();
		params.uploadExcelPath = uploadExcelPath;
		//show_loading();
		$("#customer-item").load("front/import/goImportExcelTable", params, function() {
			console.log("加载完成");
		});
	}
</script>