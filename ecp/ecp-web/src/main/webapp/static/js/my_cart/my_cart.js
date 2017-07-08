/*
	设置己选数量
 */
function selectedCount() {
	// 已选商品计数
	var selectedCounter = 0;
	$(".cart_item_selector").each(function(index, elem) {
		if (this.checked)
			selectedCounter = selectedCounter + 1;
	});
	$("#selected_count").text(selectedCounter); // 设置已选数
}

/*
 * 计算所选sku的总金额 返回总金额
 */
function calcAmount() {

	var itemArr = new Array();
	var amount = 0.0;

	// 扫描用户己选SKU
	$(".cart_item_selector").each(function(index, elem) {
		if (this.checked) { // 对于己选商品
			var surfix = $(this).attr("data-bind");
			var prefix = "num_";

			var dataOrder = $(this).attr("data-order");
			var dataArr = dataOrder.split("|");
			var item = new Object();
			item.cid = dataArr[0];
			item.itemId = dataArr[1];
			item.skuId = dataArr[2];
			item.skuName = dataArr[3];
			item.skuPrice = dataArr[4];

			var numId = prefix + surfix;
			item.skuNum = $('#' + numId).val(); // sku 个数

			amount = amount + item.skuPrice * item.skuNum;

		}

	}); // end of .cart_item_selector

	return amount; // 返回总金额
}

function displayAmount(amount) {
	$("#amount").text(amount);
}

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

/*
 * 购物车：结算 采用隐藏表单来处理
 */
function settle(itemArr) {
	var url = BASE_CONTEXT_PATH + "/front/cart/settle"; // 需要提交的 url

	// 如果未选择购物车条目则提示用户 来自于HBuilder8.8.0.201706142254—1

	if (itemArr.length <= 0) {
		util.message("请在购物车中选择商品!");
		return;
	}

	var form = document.createElement("form");
	form.id = "test";
	form.name = "test";
	document.body.appendChild(form);

	// 生成隐藏表单中的内容
	itemArr
			.forEach(function(item, index) {

				var cid = generateHideElement("cartItemList[" + index + "]."
						+ "cid", item.cid), itemId = generateHideElement(
						"cartItemList[" + index + "]." + "itemId", item.itemId), skuId = generateHideElement(
						"cartItemList[" + index + "]." + "skuId", item.skuId), skuName = generateHideElement(
						"cartItemList[" + index + "]." + "skuName",
						item.skuName), skuPrice = generateHideElement(
						"cartItemList[" + index + "]." + "skuPrice",
						item.skuPrice), skuPicture = generateHideElement(
						"cartItemList[" + index + "]." + "skuPicture",
						item.skuPicture), skuNum = generateHideElement(
						"cartItemList[" + index + "]." + "skuNum", item.skuNum), id = generateHideElement(
						"cartItemList[" + index + "]." + "id", item.id);

				form.appendChild(cid);
				form.appendChild(itemId);
				form.appendChild(skuId);
				form.appendChild(skuName);
				form.appendChild(skuPrice);
				form.appendChild(skuPicture);
				form.appendChild(skuNum);
				form.appendChild(id);

			});

	form.method = "post";
	form.action = url;
	form.submit();

}

/*
 * 购物车：结算 将数据缓存在session中，改进为采用隐藏表单 itemArr:条目数组
 */
function settle_old(itemArr) {
	var logined = false;
	var urlStr = BASE_CONTEXT_PATH + "/front/cart/gosettle"; // 需要提交的 url

	// alert("url is:"+urlStr);
	$.ajax({
		type : "POST", // 提交方式 get/post
		url : urlStr,
		contentType : "application/json", // 必须有
		// dataType : "html", //表示返回值类型，不必须,如果返回的是面页，则必须
		data : JSON.stringify(itemArr),
		success : function(res) { // data 保存提交后返回的数据，一般为 json 数据
			console.log(res);
			if (res != null && res != "") {
				var obj = $.parseJSON(res);
				if (obj.result_code == "success") {
					// util.message(obj.result_msg);
					var urlTemp = obj.result_msg;
					var url = BASE_CONTEXT_PATH + urlTemp; // 需要提交的 url
					/*
					 * $("#settle-form").attr("action", url);
					 * $("#settle-form").submit();
					 */
				} else {
					util.message(obj.result_msg);
				}
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			/* 弹出jqXHR对象的信息 */
			alert(jqXHR.responseText);
			alert(jqXHR.status);
			alert(jqXHR.readyState);
			alert(jqXHR.statusText);
			/* 弹出其他两个参数的信息 */
			alert(textStatus);
			alert(errorThrown);

		}
	});
}

/*
 * 功能：选择其中的一项 参数：cartItemId 购物车行项目id
 */
function selectOne(cartItemId) {
	if (cartItemId == null) {
		return;
	}

	// 扫描用户己选SKU
	$(".cart_item_selector").each(function(index, elem) {
		var id = $(this).attr("data-id");
		if (id == cartItemId) {
			$(this).trigger("click");
			return false;
		}

	}); // end of .cart_item_selector

}

// page loaded ready
$(function() {

	// 当用户点击前面的checkbox时处理
	// checkbox默认值为未选状态
	$(".cart_item_selector").on('click', function(e) {
		var prefix = "cartitem_"
		var surfix = $(this).attr("data-bind");
		var rowId = prefix + surfix;

		if (this.checked) {
			$('#' + rowId).addClass("selected");
		} else {
			$('#' + rowId).removeClass("selected");
		} // end of else

		selectedCount(); // 己选商品计数

		var amount = calcAmount();
		displayAmount(amount);

	});

	selectOne(g_cartItemId); // 自动选择某项

	/*
	 * 有两个select all checkbox，需要做同步处理
	 */
	$(".select_all").on('click', function(e) {
		var prefix = "cartitem_";

		if (this.checked) {
			$(".cart_item_selector").each(function(index, elem) {
				this.checked = true;
				var surfix = $(this).attr("data-bind");
				var rowId = prefix + surfix;
				$('#' + rowId).addClass("selected");
			});

			// 置另一个全选checkbox为 true
			$(".select_all").each(function(index, elem) {
				this.checked = true;
			});
		} else {
			$(".cart_item_selector").each(function(index, elem) {
				this.checked = false;
				var surfix = $(this).attr("data-bind");
				var rowId = prefix + surfix;
				$('#' + rowId).removeClass("selected");
			});

			// 置另一个全选checkbox为 false
			$(".select_all").each(function(index, elem) {
				this.checked = false;
			});

		} // end of else

		selectedCount(); // 己选商品计数

		var amount = calcAmount();
		displayAmount(amount);

	});

	/*
	 * 【结算】按钮处理
	 */
	$("#BtnSettle").on('click', function(e) {
		var itemArr = new Array();

		// 扫描用户己选SKU
		$(".cart_item_selector").each(function(index, elem) {
			if (this.checked) { // 对于己选商品
				var surfix = $(this).attr("data-bind");
				var prefix = "num_";

				var dataOrder = $(this).attr("data-order");
				var dataArr = dataOrder.split("|");
				var item = new Object();
				item.cid = dataArr[0];
				item.itemId = dataArr[1];
				item.skuId = dataArr[2];
				item.skuName = dataArr[3];
				item.skuPrice = dataArr[4];
				item.skuPicture = $(this).attr("data-image");
				item.id = $(this).attr("data-id")

				// sku 个数
				var numId = prefix + surfix;
				item.skuNum = $('#' + numId).val();

				itemArr.push(item);
			}

		}); // end of .cart_item_selector

		settle(itemArr); // send request

	}); // end of settle

}); // end of document ready
