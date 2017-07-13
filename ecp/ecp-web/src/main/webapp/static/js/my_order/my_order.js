//=================CRUD==========================


/**
 * 在页面中将订单删除
 * @param id  订单id(primary id)
 * @returns
 */
function removeDeletedOrder(id){
	var tbody=$("#tb-"+id);
	tbody.remove();
}

/**
 * 功能： 删除订单(ajax请求) 
 * @param id 订单id（primary key）
 * @returns  
 */
function deleteOrder(id) {
	var url = BASE_CONTEXT_PATH + "/front/order/delete"; // 需要提交的 url

	$.ajax({
		type : "post", // 提交方式 get/post
		url : url, // 需要提交的 url
		// dataType: "application/json",
		data : {
			'id' : id,
		},
		success : function(res) { // data 保存提交后返回的数据，一般为 json 数据
			console.log(res);
			if (res != null && res != "") {
				var obj = $.parseJSON(res);
				if (obj.result_code == "success") {
					// util.message(obj.result_msg); //显示删除成功能对话框，此处省略
					//loadOrder(); // 操作成功后重新加载购物车列表
					removeDeletedOrder(id);
				} else {
					util.message(obj.result_err_msg);
				}
			}
		}

	});
}

/**
 * 取消订单
 * @param id 订单id(primary key)
 * @returns
 */
function cancelOrder(id){
	var url = BASE_CONTEXT_PATH + "/front/order/cancel"; // 需要提交的 url

	$.ajax({
		type : "post", // 提交方式 get/post
		url : url, // 需要提交的 url
		// dataType: "application/json",
		data : {
			'id' : id,
		},
		success : function(res) { // data 保存提交后返回的数据，一般为 json 数据
			console.log(res);
			if (res != null && res != "") {
				var obj = $.parseJSON(res);
				if (obj.result_code == "success") {
					// util.message(obj.result_msg); //显示删除成功能对话框，此处省略
					loadOrder(); // 操作成功后重新加载					
				} else {
					util.message(obj.result_err_msg);
				}
			}
		}

	});
}


/**
 * 恢复订单
 * @param id 订单id(primary key)
 * @returns
 */
function buyAgainOrder(id){
	var url = BASE_CONTEXT_PATH + "/front/order/buyagain"; // 需要提交的 url

	$.ajax({
		type : "post", // 提交方式 get/post
		url : url, // 需要提交的 url
		// dataType: "application/json",
		data : {
			'id' : id,
		},
		success : function(res) { // data 保存提交后返回的数据，一般为 json 数据
			console.log(res);
			if (res != null && res != "") {
				var obj = $.parseJSON(res);
				if (obj.result_code == "success") {
					// util.message(obj.result_msg); //显示删除成功能对话框，此处省略
					loadOrder(); // 操作成功后重新加载					
				} else {
					util.message(obj.result_err_msg);
				}
			}
		}

	});
}

/**
 * 
 * @param id 订单id(primary key)
 * @returns
 */
/**
 * （agent)确认订单
 * @param orderId  订单ID（primary key）
 * @param contractId 合同ID
 * @returns
 */
function agentConfirmOrder(orderId,contractId){
	var url = BASE_CONTEXT_PATH + "/front/order/agentconfirm"; // 需要提交的 url

	$.ajax({
		type : "post", // 提交方式 get/post
		url : url, // 需要提交的 url
		// dataType: "application/json",
		data : {
			'orderId' : orderId,
			'contractId':contractId
		},
		success : function(res) { // data 保存提交后返回的数据，一般为 json 数据
			console.log(res);
			if (res != null && res != "") {
				var obj = $.parseJSON(res);
				if (obj.result_code == "success") {
					// util.message(obj.result_msg); //显示删除成功能对话框，此处省略
					loadOrder(); // 操作成功后重新加载					
				} else {
					util.message(obj.result_err_msg);
				}
			}
		}

	});
}


//===============page loaded ready====================

$(function(){
	
	/*【删除】订单 -click */
	$(".order-del").on('click',function(){		
		var orderId=$(this).attr("data-id");  //获取订单ID
		util.delConfirm("确认删除？", orderId, "deleteOrder");		
	});
	
	/*【取消】订单 -click */
	$(".btn-cancel").on('click',function(){		
		var orderId=$(this).attr("data-id");  //获取订单ID
		cancelOrder(orderId);		
	});
	
	/*【恢复】订单 -click */
	$(".btn-buy-again").on('click',function(){		
		var orderId=$(this).attr("data-id");  //获取订单ID
		buyAgainOrder(orderId);		
	});
	
	$(".btn-confirm").on('click',function(){		
		var orderId=$(this).attr("data-id");  //获取订单ID
		var contractId=$(this).attr("data-contract-id");
		confirmOrder(orderId,contractId);		
	});
	
	
})