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