<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<div class="fieldset" id="sku">
	<div class="legend" onclick="getSkuInfo();" title="选择销售属性自动创建SKU">SKU</div>
	<div id="sku-content">
		<table class="table table-striped table-bordered table-hover " id="sku-table"
	style="width: 100%;"center">
	<thead id="sku-head" style="width: 98%; padding-top: 80px;">
		
	</thead>
	<tbody id="sku-body">
		
	</tbody>
</table>
	</div>
</div>
<!-- <script>
/* 如果商品属性为空时不显示标题 */
var itemAttrBody = $("#item-attr-body").html().trim();
console.log("itemAttrBody:"+itemAttrBody);
if(itemAttrBody==null || itemAttrBody==""){
	$("#item-attr").remove();
}
/* 如果销售属性为空时不显示标题 */
var saleAttrBody = $("#sale-attr-body").html().trim();
console.log("saleAttrBody:"+saleAttrBody);
if(saleAttrBody==null || saleAttrBody==""){
	$("#sale-attr").remove();
}

/*
 * 获取商品属性和属性值
 */
function getItemAttr(){
	var attrArr = new Array();
	//商品属性
	$("#item-attr input[name='itemAttr']:checked").each(function(){
		attrArr.push($(this).val());
	});
	console.log("商品属性："+attrArr);
	return attrArr;
}
/*
 * 获取销售属性和属性值
 */
function getSaleAttr(){
	var attrArr = new Array();
	//销售属性
	$("#sale-attr input[name='saleAttr']:checked").each(function(){
		attrArr.push($(this).val());
	});
	console.log("销售属性："+attrArr);
	return attrArr;
}
</script> -->
<!-- 用于保存默认sku规格 -->
<div id="edit-default-sku-spec-div" class="hide">
	<input type="hidden" id="default-sku-spec" value="" />
</div>
<button type="button" class="btn btn-primary" id="edit-default-sku-spec-btn" onclick="javascript:openDefaultSpecModel(0);">编辑默认sku规格</button>
<!-- sku规格对话框 -->
<div class="modal fade" id="sku-spec-modal" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width:80%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 class="modal-title" id="">SKU规格</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="sku-spec-form">				
					<div class="form-group">
						<div class="col-sm-12">
							<button type="button" class="btn btn-primary" id="add-spec-group-btn">增加规格</button>
							<!-- <button type="button" class="btn btn-primary" id="add-spec-btn">添加规格</button> -->
						</div>
					</div>
					<div class="form-group" id="spec-item">
						<!-- <div class="col-sm-6" id="group-1">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										Panel title
									</h3>
								</div>
								<div class="panel-body" id="group-value-1">
									Panel content
								</div>
								<div class="panel-footer">
									Panel footer
								</div>
							</div>
						</div>
						<div class="col-sm-6" id="group-2">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										Panel title
									</h3>
								</div>
								<div class="panel-body" id="group-value-2">
									Panel content
								</div>
								<div class="panel-footer">
									Panel footer
								</div>
							</div>
						</div>
						<div class="col-sm-6" id="group-2">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										Panel title
									</h3>
								</div>
								<div class="panel-body" id="group-value-2">
									Panel content
								</div>
								<div class="panel-footer">
									Panel footer
								</div>
							</div>
						</div>
						<div class="col-sm-6" id="group-2">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										Panel title
									</h3>
								</div>
								<div class="panel-body" id="group-value-2">
									Panel content
								</div>
								<div class="panel-footer">
									Panel footer
								</div>
							</div>
						</div>
						<div class="col-sm-6" id="group-2">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										Panel title
									</h3>
								</div>
								<div class="panel-body" id="group-value-2">
									Panel content
								</div>
								<div class="panel-footer">
									Panel footer
								</div>
							</div>
						</div>
						<div class="col-sm-6" id="group-2">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">
										Panel title
									</h3>
								</div>
								<div class="panel-body" id="group-value-2">
									Panel content
								</div>
								<div class="panel-footer">
									Panel footer
								</div>
							</div>
						</div> -->
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="close-attr-value-btn">关闭</button>
				<button type="button" class="btn btn-primary hide" id="spec-submit-btn">保存</button>
				<button type="button" class="btn btn-primary" id="default-spec-submit-btn" onclick="javascript:saveDefaultSpec(0);">保存默认sku规格</button>
			</div>
		</div>
	</div>
</div>
<script>
var g_is_save_sku = false;//是否保存sku(skuPage.jsp)

var g_group_num = 0;//记录sku规格个数
var g_sku_id = 0;//sku的ID，>0时，表示是修改，保存时直接根据skuId直接更新数据库；<=0时，表示是添加，保存时在js中保存，添加商品随sku一起添加
var g_sku_index = 0;//记录当前编辑的sku是哪一个

/**
 * 点击sku对话框中的保存按钮时，保存sku规格
 */
$("#spec-submit-btn").click(function(){
	var skuSpec = getSkuSpec();//获取sku规格信息
	if(!skuSpec){
		return false;
	}
	if(skuSpec==null || skuSpec=="" || skuSpec=="[]"){
		console.log("保存sku规格为空");
	}else{
		if(g_sku_id<=0){//添加sku
			$("#sku-spec-"+g_sku_index).val(skuSpec);
		}else{//编辑sku
			ajaxPostSaveSkuSpec(g_sku_id, skuSpec);
		}
	}
	
	g_group_num=0;
	$('#sku-spec-modal').modal('hide');
});
/**
 * 显示打开默认sku规格对话框按钮
 */
function showOpenDefaultSpecBtn(skuId){
	$("#edit-default-sku-spec-btn").removeClass("hide");//显示打开默认sku规格对话框按钮
	$("#edit-default-sku-spec-btn").attr("onclick", "javascript:openDefaultSpecModel("+skuId+");");
	$("#spec-submit-btn").addClass("hide");//隐藏sku规格对话框中保存按钮
	$("#default-spec-submit-btn").removeClass("hide");////显示sku规格对话框中保存默认sku规格按钮
	$("#default-spec-submit-btn").attr("onclick", "javascript:saveDefaultSpec("+skuId+");");
}
/**
 * 隐藏打开默认sku规格对话框按钮
 */
function hideOpenDefaultSpecBtn(skuId){
	$("#edit-default-sku-spec-btn").addClass("hide");//显示打开默认sku规格对话框按钮
	$("#edit-default-sku-spec-btn").attr("onclick", "javascript:openDefaultSpecModel("+skuId+");");
	$("#spec-submit-btn").removeClass("hide");//隐藏sku规格对话框中保存按钮
	$("#default-spec-submit-btn").addClass("hide");////显示sku规格对话框中保存默认sku规格按钮
	$("#default-spec-submit-btn").attr("onclick", "javascript:saveDefaultSpec("+skuId+");");
	$("#default-sku-spec").val("");
}

/**
 * 打开编辑sku规格对话框
 */
function openSpecModel(skuId, index){
	console.log("skuId:"+skuId);
	g_sku_id = skuId;//sku的ID，>0时，表示是修改，保存时直接根据skuId直接更新数据库；<=0时，表示是添加，保存时在js中保存，添加商品随sku一起添加
	g_sku_index = index;//记录当前编辑的sku是哪一个
	$("#spec-item").empty();//清空sku规格对话框中的sku规格内容
	if(skuId<=0){
		//TODO
		//<=0时，表示是添加，不查询数据库中sku规格；保存时在js中保存，添加商品随sku一起添加
	}else{
		//TODO
		//>0时，表示是修改，先查询数据库中sku规格并在对话框中显示；保存时直接根据skuId修改更新数据库
		ajaxGetSkuSpec(skuId);
	}
	$('#sku-spec-modal').modal('show');
}
/**
 * 打开默认sku规格对话框
 */
function openDefaultSpecModel(skuId){
	console.log("skuId:"+skuId);
	$("#spec-item").empty();//清空sku规格对话框中的sku规格内容
	if(skuId>0){
		//TODO
		//>0时，表示是修改，先查询数据库中sku规格并在对话框中显示；保存时直接根据skuId修改更新数据库
		ajaxGetSkuSpec(skuId);
	}
	$('#sku-spec-modal').modal('show');
}
/**
 * 保存默认sku规格
 */
function saveDefaultSpec(skuId){
	var skuSpec = getSkuSpec();//获取sku规格信息
	if(!skuSpec){
		return false;
	}
	if(skuSpec==null || skuSpec=="" || skuSpec=="[]"){
		console.log("保存默认sku规格为空");
	}else{
		if(skuId<=0){//添加sku
			$("#default-sku-spec").val(skuSpec);
		}else{//编辑sku
			ajaxPostSaveSkuSpec(skuId, skuSpec);
		}
	}
	
	g_group_num=0;
	$('#sku-spec-modal').modal('hide');
}

/**
 * ajax请求获取sku规格
 */
function ajaxGetSkuSpec(skuId){
	var url = "back/item/selectSkuBySkuId";
	var params = {"skuId": skuId};
	$.get(url, params, function(res){
		console.log(res);
		if(res!=null){
			var obj = $.parseJSON(res);
			if(obj.result_code=="success"){
				var skuSpec = obj.skuSpec;
				if(skuSpec!=null && skuSpec!=""){
					var skuSpecObj = $.parseJSON(skuSpec);
					var html = "";
					$.each(skuSpecObj, function(index){
						html += createSpecGroup(index, this);
						g_group_num++;//记录sku规格个数，添加一个sku规格分组后把个数+1
					});
					$("#spec-item").append(html);
					return false;
				}else{
					console.log("sku规格为空");
				}
				
			}else{
				util.message("查询失败！");
			}
		}
	});
}
/**
 * ajax请求保存sku规格
 */
function ajaxPostSaveSkuSpec(skuId, skuSpec){
	var url = "back/item/updateSkuSpecBySkuId";
	var params = {"skuId": skuId, "skuSpec": skuSpec};
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null){
			var obj = $.parseJSON(res);
			if(obj.result_code=="success"){
				util.message(obj.result_msg);
			}else{
				util.message(obj.result_err_msg);
			}
		}
	});
}
/**
 * 设置默认sku规格保存时html
 */
/* function setDefaultSkuSpecHtml(){
	var html = "<input type='hidden' id='sku-spec-0' value='' />";
	$("#edit-default-sku-spec-div").html(html);
} */
/**
 * 移除默认sku规格保存html
 */
/* function removeDefaultSkuSpecHtml(){
	$("#edit-default-sku-spec-div").html("");
} */
/**
 * 添加规格分组
 */
$("#add-spec-group-btn").click(function(){
	var html = createSpecGroup(g_group_num, null);
	g_group_num++;//记录sku规格个数，添加一个sku规格分组后把个数+1
	console.log(html);
	$("#spec-item").append(html);
	//addSpec(g_group_num+1);
});
/**
 * 创建sku规格分组html字符串
 */
function createSpecGroup(groupNum, skuSpec){
	/* [
			{
				"spec_group":"主体",
				"spec":[
							{"spec_key":"品牌",	"spec_val":"apple"},
							{"spec_key":"型号",	"spec_val":"a9"}
						]
						
			},
			{
				"spec_group":"基本信息",
				"spec":[
							{"spec_key":"机身长度（mm）",	"spec_val":"151.9"},
							{"spec_key":"机身重量（g）",	"spec_val":"175"}
						]
						
			}
	] */
	var specHtml = "";
	var spec_group = "";
	var spec = null;
	if(skuSpec==null){
		specHtml = createSpecHtml(null);
	}else{
		spec_group = skuSpec.spec_group;
		spec = skuSpec.spec;
		if(spec!=null){
			for(var i=0; i<spec.length; i++){
				specHtml += createSpecHtml(spec[i]);
			}
		}
	}
	var html = ''
		+ '<div class="col-sm-4 spec-group-div" id="spec-group-'+groupNum+'">'
		+ '<div class="panel panel-default">'
		+ '<div class="panel-heading">'
		+ '<h3 class="panel-title">'
		+ '<input type="text" id="input-spec-group-'+groupNum+'" value="'+spec_group+'" placeholder="规格分组名称" >'
		+ '&nbsp;<button type="button" title="增加规格" onclick="javascript:addSpec('+groupNum+');">+</button>'
		+ '&nbsp;<button type="button" title="删除规格分组" onclick="javascript:removeSpecGroup('+groupNum+');">x</button>'
		+ '</h3>'
		+ '</div>'
		+ '<div class="panel-body spec-div" id="group-value-'+groupNum+'">'
		+ specHtml
		+ '</div>'
		+ '</div>'
		+ '</div>';
	return html;
}
/**
 * 移除sku规格分组html字符串
 */
function removeSpecGroup(groupNum){
	$("#spec-group-"+groupNum).remove();
}
/**
 * 添加sku规格html字符串
 */
function addSpec(groupNum){
	var html = createSpecHtml(null);
	console.log(html);
	$("#group-value-"+groupNum).append(html);
}
/**
 * 创建sku规格html字符串
 */
function createSpecHtml(spec){
	var spec_key = "";
	var spec_val = "";
	if(spec!=null){
		spec_key = spec.spec_key;
		spec_val = spec.spec_val;
	}
	var time = new Date().getTime();
	var html = ''
		+ '<div class="input-spec-item" id="'+time+'">'
		+ '<div class="col-sm-5">'
		+ '<input type="text" id="input-spec-key-'+time+'" value="'+spec_key+'" placeholder="规格属性" size="15" >'
		+ '</div>'
		+ '<div class="col-sm-5">'
		+ '<input type="text" id="input-spec-val-'+time+'" value="'+spec_val+'" placeholder="规格属性值" size="15" >'
		+ '</div>'
		+ '<div class="col-sm-2">'
		+ '<button type="button" title="删除规格" onclick="javascript:removeSpec('+time+');">x</button>'
		+ '</div>'
		+ '<br><br>'
		+ '</div>';
	return html;
}
/**
 * 移除sku规格html字符串
 */
function removeSpec(id){
	$("#"+id).remove();
}
/**
 * 获取sku规格
 */
function getSkuSpec(){
	var isReturn = true;
	var spec_group_Arr = new Array();
	$("#spec-item .spec-group-div").each(function(index){
		var spec_group = $("#input-spec-group-"+index).val();
		console.log("第 "+index+" 个规格分组的分组名称:"+spec_group);
		if(spec_group==null || spec_group==""){
			util.message("第 "+index+" 个规格分组的分组名称不能为空!");
			isReturn = false;
			return false;
		}
		var spec_arr = new Array();
		$(this).find(".spec-div").find(".input-spec-item").each(function(currIndex){
			var randomId = $(this).attr("id");
			var spec_key = $("#input-spec-key-"+randomId).val();
			console.log("第 "+index+" 个规格分组的第 "+currIndex+" 个规格属性:"+spec_key);
			if(spec_key==null || spec_key==""){
				util.message("第 "+index+" 个规格分组的第 "+currIndex+" 个规格属性不能为空!");
				isReturn = false;
				return false;
			}
			var spec_val = $("#input-spec-val-"+randomId).val();
			console.log("第 "+index+" 个规格分组的第 "+currIndex+" 个规格属性值:"+spec_val);
			if(spec_val==null || spec_val==""){
				util.message("第 "+index+" 个规格分组的第 "+currIndex+" 个规格属性值不能为空!");
				isReturn = false;
				return false;
			}
			var spec_obj = new Object();
			spec_obj.spec_key = spec_key;
			spec_obj.spec_val = spec_val;
			spec_arr.push(spec_obj);
		});
		var spec_group_obj = new Object();
		spec_group_obj.spec_group = spec_group;
		spec_group_obj.spec = spec_arr;
		spec_group_Arr.push(spec_group_obj);
	});
	if(!isReturn){
		return false
	}
	var specJson = JSON.stringify(spec_group_Arr);
	console.log("sku规格:"+specJson);
	return specJson;
}
</script>