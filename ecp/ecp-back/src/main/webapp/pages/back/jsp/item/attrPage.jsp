<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class=" fieldset" id="item-attr">
	<div class=" legend" onclick="javascript:alert(getItemAttr());">商品属性</div>
	<div id="item-attr-body">
		<%-- <%@ include file="attrPage.jsp"%> --%>
		<div class="form-group">
			<label class="col-sm-2 control-label">品牌</label>
			<div class="col-sm-10">
				<select class="form-control" id="brand" name="brand">
					<option value="0">请选择</option>
					<c:forEach items="${brandList}" var="brand">
						<option value="${brand.brandId}">${brand.brandName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<c:forEach items="${attrList}" var="attr">
			<div class="form-group">
				<label class="col-sm-2 control-label">${attr.attr_name}</label>
				<div class="col-sm-10">
					<c:forEach items="${attr.valList}" var="val">
						<label><input type="checkbox" id="" name="itemAttr" value="${attr.attr_id}:${val.value_id}" />&nbsp;${val.value_name}</label>&nbsp;&nbsp;&nbsp;
					</c:forEach>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<br>
<div class=" fieldset" id="sale-attr">
	<div class=" legend" onclick="javascript:alert(getSaleAttr());">销售属性</div>
	<div id="sale-attr-body">
		<c:forEach items="${attrList}" var="attr">
			<c:if test="${attr.attr_type==4}">
				<div class="form-group">
					<label class="col-sm-2 control-label">${attr.attr_name}</label>
					<div class="col-sm-10">
						<c:forEach items="${attr.valList}" var="val">
							<label>
								<input type="checkbox" id="" name="saleAttr" onclick="javascript:createSku(${attr.attr_id}, '${attr.attr_name}', ${val.value_id}, '${val.value_name}');" value="${attr.attr_id}:${val.value_id}" />
								&nbsp;${val.value_name}
							</label>
							&nbsp;&nbsp;&nbsp;
						</c:forEach>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</div>
</div>
<div id="sku">
	<%@ include file="skuPage.jsp"%>
</div>
<script>
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

function createSku(attrid, attrname, valid, valname){
	var arr = new Array();
	//销售属性
	$("#sale-attr input[name='saleAttr']:checked").each(function(){
		var keyVal = $(this).val().split(":");
		var key = keyVal[0];
		var val = keyVal[1];
		var obj = new Object();
		obj.key = key;
		obj.val = val;
		obj.valText = $(this).parent().text();
		
		if(arr.length>0){
			var isAdd = false;
			for(var i=0; i<arr.length; i++){
				var currObj = arr[i];
				if(currObj!=null){
					var k = currObj.key;
					if(k==obj.key){
						currObj.val.push(obj);
						isAdd = true;
					}
				}
			}
			if(!isAdd){
				var currArr = new Array();
				currArr.push(obj);
				
				var currObj = new Object();
				currObj.key = obj.key;
				currObj.attrText = $(this).parent().parent().prev().text();
				currObj.val = currArr;
				
				arr.push(currObj);
			}
		}else{
			
			var currArr = new Array();
			currArr.push(obj);
			
			var currObj = new Object();
			currObj.key = obj.key;
			currObj.attrText = $(this).parent().parent().prev().text();
			currObj.val = currArr;
			
			arr.push(currObj);
		}
		
		
		
		/* var obj = new Object();
		obj.keyValId = $(this).val();
		obj.valText = $(this).parent().text();
		obj.attrText = $(this).parent().parent().prev().text();
		arr.push(obj);
		console.log($(this).val());
		console.log($(this).parent().text());
		console.log($(this).parent().parent().prev().text()); */
	});
	//debugger;
	var htmlHead = "<tr>";
	var htmlBody = "<tr>";
	
	htmlHead += "</tr>";
	htmlBody += "</tr>";
	$("#sku-head").html(createHeadHtml(arr));
	$("#sku-body").html(createBodyHtml(arr));
	
}
function createHeadHtml(arr){
	var htmlHead = "<tr>";
	for(var i=0; i<arr.length; i++){
		htmlHead += "<th>"+arr[i].attrText+"</th>";
		console.log(JSON.stringify("key:"+arr[i].key+", text:"+arr[i].attrText));
	}
	htmlHead += "</tr>";
	return htmlHead;
}
function createBodyHtml(arr){
	
	/* for(var i=0; i<arr.length; i++){
		//htmlBody += "<tr>";
		var currArr = arr[i].val;
		
		//htmlBody += "</tr>";
	}
	
	
	htmlBody += "<tr>";
	var currArr = arr[i].val;
	for(var n=0; n<currArr.length; n++){
		console.log(JSON.stringify("key:"+currArr[n].key+", text:"+currArr[n].valText));
		htmlBody += "<td>"+currArr[n].valText+"</td>";
	}
	htmlBody += "</tr>"; */
	
	var htmlBody = "";
	for(var i=0; i<arr.length; i++){
		/* if(i==0){
			htmlBody += "<tr>";
		} */
		
		var currArr = arr[i].val;
		for(var n=0; n<currArr.length; n++){
			if(i==0){
				htmlBody += "<tr>";
			}
			console.log(JSON.stringify("key:"+currArr[n].key+", text:"+currArr[n].valText));
			htmlBody += "<td>"+currArr[i].valText+"</td>";
			htmlBody += "<td>"+currArr[n].valText+"</td>";
		}
		/* if(i==(arr.length-1)){
			htmlBody += "</tr>";
		} */
		
	}
	htmlBody += "";
	return htmlBody;
}
function createHtmlTd(currArr){
	var htmlBody = "";
	for(var n=0; n<currArr.length; n++){
		if(n==0){
			htmlBody += "<tr>";
		}
		console.log(JSON.stringify("key:"+currArr[n].key+", text:"+currArr[n].valText));
		htmlBody += "<td>"+currArr[n].valText+"</td>";
	}
	return htmlBody;
}
</script>