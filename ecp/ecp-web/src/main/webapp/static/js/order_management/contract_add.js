//===============变量定义=================
var g_orderItemId = 0;

/* 设置orderItemId */
function setOrderItemId(orderItemId) {
	g_orderItemId = orderItemId;
}

function getOrderItemId() {
	return g_orderItemId;
}

function getContractAttrList() {
	return g_contractAttrList;
}

// ==================对话框通用部分=START=================
/*
 * 显示增加用户地址对话框
 */
function showDialog() {
	
	$('#modal-container-273078').modal({
		backdrop : 'static',
		keyboard : false
	});
	
	
	
}

/* close dialog */
function closeDialog() {
	$("#modal-container-273078").modal("hide");
}

/* set dialog's title */
function setDialogTitle(title) {
	$("#myModalLabel").text(title);
}

/* reset dialog */
function resetDialog() {
	$("#dialog-form")[0].reset();
}

// ===============合同条目================

/* 获取合同行项目列表 */
function getOrderItemList() {
	return g_orderItemList;
}

/*
 * 显示折减对话框
 */
function displayDiscountDialog(e) {
	var orderItemId = $(e).attr("data-bind"); // get orderItem'id
	setOrderItemId(orderItemId);

	var sku_name = $("#sku_name_" + orderItemId).html(); // get sku_name and
															// set dialog's
															// title
	setDialogTitle("折减金额(" + sku_name + ")");

	resetDialog();
	showDialog();
}

/*
 * 初始化订单条目中的折减字段为0
 */
function initOrderItemDiscount() {
	var orderItemList = getOrderItemList();
	for (var i = 0; i < orderItemList.length; i++) {
		orderItemList[i].discount_price = 0;
		orderItemList[i].pay_price = orderItemList[i].primitive_price;
		orderItemList[i].pay_price_total = orderItemList[i].primitive_price
				* orderItemList[i].num;
	}
}

/*
 * 计算订单条目小计 params: id:order item id discount:折减金额
 */
function calcAmount(id, discount) {
	var orderItemList = getOrderItemList();
	var index = -1;
	for (var i = 0; i < orderItemList.length; i++) {
		if (orderItemList[i].id == id) {
			index = i;
			break;
		}
	}
	orderItemList[index].discount_price = discount;
	orderItemList[index].pay_price = orderItemList[index].primitive_price
			- discount;
	orderItemList[index].pay_price_total = orderItemList[index].pay_price
			* orderItemList[index].num;
}

/*
 * 更新 (UI) params: id:order item id
 */
function displayOrderTtem(id) {
	var orderItemList = getOrderItemList();
	var index = -1;
	for (var i = 0; i < orderItemList.length; i++) {
		if (orderItemList[i].id == id) {
			index = i;
			break;
		}
	}

	$("#" + "discount_price_" + id).html(
			'￥' + orderItemList[index].discount_price.toFixed(2)); // 折减
	$("#" + "pay_price_" + id).html(
			'￥' + orderItemList[index].pay_price.toFixed(2)); // 成交价格
	$("#" + "amount_" + id)
			.html(
					'￥'
							+ (orderItemList[index].pay_price * orderItemList[index].num)
									.toFixed(2)); // 小计

}

/*
 * 计算所有订单条目合计
 */
function calcSumAmount() {
	var orderItemList = getOrderItemList();
	var sumAmount = 0.00;
	for (var i = 0; i < orderItemList.length; i++) {
		sumAmount = sumAmount + orderItemList[i].pay_price
				* orderItemList[i].num;
	}
	return sumAmount;
}

/*
 * 显示总计
 */
function displaySumAmount(sumAmount) {
	$("#sumAmount").text('￥' + sumAmount.toFixed(2));
}

// ===============合同属性==================

/*
 * return:返回付款类型
 */
function getPayType() {
	var val = 0;
	$("input[name='pay_type']").each(function(index, elem) {
		if (this.checked) {
			val = $(this).val();
			return false;
		}
	});
	return val;
}

/*
 * return:返回质保期
 * 
 */
function getGuaranteePeriod() {
	var val = 0;
	$("input[name='guarantee_period']").each(function(index, elem) {
		if (this.checked) {
			val = $(this).val();
			return false;
		}
	});
	return val;
}

/*
 * 初始化合同属性
 */
function initContractAttr() {
	$("input[name='guarantee_period']").eq(0).attr("checked", true);
	$("input[name='pay_type']").eq(0).attr("checked", true);
}

/* 收集合同属性 */
function getContractAttrValue(contractAttrList) {

	/*
	 * guarantee_period delivery_time delivery_address transport_charge
	 * payment_percent_signed payment_amount_signed payment_percent_delivery
	 * payment_amount_delivery days_signed date_signed fee_rate voucher pay_type
	 */

	contractAttrList.forEach(function(item, index) {
		if (item.attrName == 'guarantee_period') {
			item.attrvalue = getGuaranteePeriod();
		} else if (item.attrName == 'pay_type') {
			item.attrValue = getPayType();
		} else {
			item.attrValue = $("#" + item.attrName).val();
		}
	});

	return contractAttrList;
}

/* 合同预览 */
function contractPreview() {
	var url = BASE_CONTEXT_PATH + "/back/contract/preview"; // 需要提交的 url

	var contractAttrList = getContractAttrList(); // 合同属性列表
	var contractAttrValList = getContractAttrValue(contractAttrList); // 合同属性值

	var orderItemList = getOrderItemList(); // 订单列表行

	var form = document.createElement("form"); // 生成表单
	form.id = "test";
	form.name = "test";
	form.target = "_blank";
	document.body.appendChild(form);

	// 请求参数
	var attrVals = generateHideElement("contractAttrVals", JSON
			.stringify(contractAttrValList)), orderItems = generateHideElement(
			"orderItems", JSON.stringify(orderItemList));
	form.appendChild(attrVals); // 合同属性列表
	form.appendChild(orderItems); // 订单列表行

	// 发送请求
	form.method = "post";
	form.action = url;
	form.submit();
}

/* 合同确认 */
function contractCreate() {
	var url = BASE_CONTEXT_PATH + "/back/contract/create"; // 需要提交的 url

	var contractAttrList = getContractAttrList(); // 合同属性列表
	var contractAttrValList = getContractAttrValue(contractAttrList); // 合同属性值

	var orderItemList = getOrderItemList(); // 订单列表行

	var form = document.createElement("form"); // 生成表单
	form.id = "test";
	form.name = "test";
	form.target = "_blank";
	document.body.appendChild(form);

	// 请求参数
	var attrVals = generateHideElement("contractAttrVals", JSON
			.stringify(contractAttrValList)), orderItems = generateHideElement(
			"orderItems", JSON.stringify(orderItemList));
	form.appendChild(attrVals); // 合同属性列表
	form.appendChild(orderItems); // 订单列表行

	// 发送请求
	form.method = "post";
	form.action = url;
	form.submit();

}

// ========================================
/*
 * 生成隐藏表单域函数 params: name:input's name value:inptu's value
 */
var generateHideElement = function(name, value) {
	var tempInput = document.createElement("input");
	tempInput.type = "hidden";
	tempInput.name = name;
	tempInput.value = value;
	return tempInput;
}

// ==================页面-READY-初始化======================
$(function() {

	initOrderItemDiscount();
	displaySumAmount(calcSumAmount());
	initContractAttr(); // 初始化合同属性
	
	$('#modal-container-273078').on('shown.bs.modal', function (e) {
	    // 处理代码...
		//alert("2333");
		$("#discount")[0].focus();
	})

	/**
	 * 折减对话框【确定】按钮-click处理
	 */
	$("#btnConfirm").on('click', function(e) {
		closeDialog();
		var discountStr = $("#discount").val();
		var discount = parseInt(discountStr, 10);
		if (isNaN(discount)) {
			util.message("所输入的不是数字！");
		} else {
			calcAmount(getOrderItemId(), discount);
			displayOrderTtem(getOrderItemId());
			displaySumAmount(calcSumAmount());
		}

	});

	/* 质保期 checkbox click处理 */
	$("input[name='guarantee_period']").on('click', function(e) {

		$("input[name='guarantee_period']").each(function(index, val) {
			(this).checked = false;
		});

		this.checked = true;

	});

	/* 收款方式 checkbox click处理 */
	$("input[name='pay_type']").on('click', function(e) {

		$("input[name='pay_type']").each(function(index, val) {
			(this).checked = false;
		});

		this.checked = true;

	});

	/* 【合同预览】 按钮 click process */
	$(".btn-preview").on('click', function(e) {
		// alert("质保期："+getGuaranteePeriod());
		// alert("付款类型:"+getPayType());
		contractPreview();

	});

	/* 【合同确认】 按钮 click process */
	$(".btn-create").on('click', function(e) {
		contractCreate();
	});
	
	/* 折减对话框 -输入金额-enter */
	$("#dialog-form").on("keydown",function(event){		
		if(event.keyCode==13){
			//console.log("debug!");
			$("#btnConfirm").trigger("click");
		}
	})

});