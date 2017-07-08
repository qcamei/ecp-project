/* 删除购物车中的条目 */
function deleteFunc(e) {
	// console.log("delete cart item!");
	var cartItemId = $(e).attr("data-bind"); // 获取需要删除的购物车条目
	// util.delConfirm("确认删除？", cartItemId, "deleteInfoAjaxRequest");
	deleteInfoAjaxRequest(cartItemId);

	// console.log("delete cart item after confirm!");
}

/*
 * 采用ajax 删除购物车条目
 */
function deleteInfoAjaxRequest(id) {
	var url = BASE_CONTEXT_PATH + "/front/cart/delete"; // 需要提交的 url

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
					loadQuickCart(); // 操作成功后重新加载购物车列表
				} else {
					util.message(obj.result_err_msg);
				}
			}
		}

	});
}

$(function() {

});