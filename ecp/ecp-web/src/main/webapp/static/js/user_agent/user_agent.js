/*
	查询-签约客户
 */
function search_normal() {
	// $("#searchform").submit(); // 提交表单
	loadUserAgent(); // 重新加载页面
}

// ===============分配帐户对话框 open/close==================
/*
 * 显示分配帐户窗口
 */
function displayWindow() {
	$("#modal-273078").click();
}

/* 关闭分配帐户窗口 */
function closeWindow() {
	$("#btnClose").click();
}

// ===================设置帐号状态====================
function setAccountState(agentId, userId, accountState) {
	var url = BASE_CONTEXT_PATH + "/back/agent/setstate"; // 需要提交的 url
	$.ajax({
		type : "post", // 提交方式 get/post
		url : url, // 需要提交的 url
		// dataType: "application/json",
		data : {
			'agentId' : agentId,
			'userId' : userId,
			'accountState' : accountState
		},
		success : function(res) { // data 保存提交后返回的数据，一般为 json 数据
			console.log(res);
			if (res != null && res != "") {
				var obj = $.parseJSON(res);
				if (obj.result_code == "success") {
					util.message(obj.result_msg);
					loadUserAgent();
				} else {
					util.message(obj.result_msg);
				}
			}
		}

	});
}

//==================PAGE LOADED READY===================
$(function() {

	// ====================分配帐户=====================
	/* 【分配帐户】按钮-显示对话框 */
	$(".dispatch-account").on(
			"click",
			function(e) {
				var defaultPassword="123456";
				
				var agentId = $(this).attr("data-bind");
				var companyName = $(this).attr("data-company-name");
				var contactPhone = $(this).attr("data-contact-phone");
				var artificialPersonName = $(this).attr("data-artificialPersonName");
				
				var userId=$(this).attr("data-user-id");
				if(userId!=0){
					util.message("已经分配帐户");
				}
				else{
					displayWindow();
					$("#agentId").val(agentId);
					$("#myModalLabel").text("分配帐户(" + companyName + "_" + artificialPersonName	+ ")");
					$("#loginName").val(contactPhone);    //默认的帐户名称为手机号码
					$("#password").val(defaultPassword);  //默认的帐户密码是：123456
				}
				
				
	});

	// ===================设置帐号状态（有效、无效）======================
	/* 设置帐号为有效 */
	$(".set-valid").on("click", function(e) {
		var agentId = $(this).attr("data-bind");
		var userId = $(this).attr("data-user-id");
		var accountState = $(this).attr("data-account-state");
		if (userId == 0) {
			util.message("尚未分配帐号！");
		} else {
			if (accountState == 1) {
				util.message("此帐号己为有效！");
			} else {
				setAccountState(agentId, userId, 1);
			}
		}

	});

	/* 设置帐号为无效 */
	$(".set-invalid").on("click", function(e) {
		var agentId = $(this).attr("data-bind");
		var userId = $(this).attr("data-user-id");
		var accountState = $(this).attr("data-account-state");
		if (userId == 0) {
			util.message("尚未分配帐号！");
		} else {
			if (accountState == 2) {
				util.message("此帐号己为无效！");
			} else {
				setAccountState(agentId, userId, 2);
			}
		}

	});

	// ====================翻页=====================
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
	

});   //end of page loaded