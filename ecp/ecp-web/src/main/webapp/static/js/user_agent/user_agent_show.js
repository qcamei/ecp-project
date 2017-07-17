/*
			查询-签约客户
 */
function search_normal() {
	$("#searchform").submit(); // 提交表单
}

/*
 * 显示分配帐户窗口
 */
function displayWindow() {
	$("#modal-273078").click();

}

/*
 * 关闭窗口
 */
function closeWindow() {
	$("#btnClose").click();
}


//==================PAGE LOADED READY=====================
$(function() {

	/*
	 * 显示【详情】按钮-click
	 */
	$(".show-detail").on("click", function(e) {

	});

	/*
	 * 【分配帐户】按钮
	 */
	$(".dispatch-account").on(
			"click",
			function(e) {
				displayWindow();
				var agentId = $(this).attr("data-bind");
				var companyName = $(this).attr("data-company-name");
				var contactPhone = $(this).attr("data-contact-phone");
				var artificialPersonName = $(this).attr(
						"data-artificialPersonName");

				$("#agentId").val(agentId);

				$("#myModalLabel").text(
						"分配帐户(" + companyName + "_" + artificialPersonName
								+ ")");
				$("#loginName").val(contactPhone);
			});

	/*
	 * 【分页】导航： 当点击页号时读取需要导航到的页码及每页记录数（pageNum,pageSize）
	 * data-bind:pageNum-pageSize形式 如果data-bind为空字符串，则不做动作 当用户点击某页时，则发送与筛选相同的请求
	 */

	$(".pagination li a").on('click', function(e) {
		// alert($(this).attr("data-bind"));
		var dataBind = $(this).attr("data-bind");
		// 当dataBind有数据时处理
		if (dataBind != "") {
			var pageArr = new Array();
			pageArr = dataBind.split("-");
			// 置隐藏表单数据
			$("#pageNum").val(pageArr[0]);
			$("#pageSize").val(pageArr[1]);
			// 发送请求
			search_normal();
		}

	});

	/*
	 * 签约客户-帐户分配-【确定】按钮处理(AJAX方式)
	 */
	$("#btnDispatch").on("click", function(e) {
		var url = BASE_CONTEXT_PATH + "/back/agent/dispatch"; // 需要提交的 url

		var loginName = $('#loginName').val();
		var password = $('#password').val();
		var agentId = $("#agentId").val();

		closeWindow(); // 关闭窗口

		// 设置参数
		/*
		 * var params = { "loginName":loginName, "password":password,
		 * "agentId":agentId };
		 * 
		 * //util.loading(); //此种方式更加的简洁 $.post(url, params, function(res){
		 * console.log(res); if(res!=null && res!=""){ var obj =
		 * $.parseJSON(res); if(obj.result_code=="success"){
		 * util.message(obj.result_msg); }else{
		 * util.message(obj.result_err_msg); } }
		 * 
		 * });
		 */

		$.ajax({
			type : "post", // 提交方式 get/post
			url : url, // 需要提交的 url
			// dataType: "application/json",
			data : {
				'loginName' : loginName,
				'password' : password,
				'agentId' : agentId
			},
			success : function(res) { // data 保存提交后返回的数据，一般为 json 数据
				console.log(res);
				if (res != null && res != "") {
					var obj = $.parseJSON(res);
					if (obj.result_code == "success") {
						util.message(obj.result_msg);
					} else {
						util.message(obj.result_msg);
					}
				}
			}
		/*
		 * , error : function(jqXHR, textStatus, errorThrown) { //弹出jqXHR对象的信息
		 * alert(jqXHR.responseText); alert(jqXHR.status);
		 * alert(jqXHR.readyState); alert(jqXHR.statusText); //弹出其他两个参数的信息
		 * alert(textStatus); alert(errorThrown); }
		 */
		});

	});

});