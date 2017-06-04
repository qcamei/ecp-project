// JavaScript Document
//支持Enter键登录
document.onkeydown = function(e) {
	if ($(".bac").length == 0) {
		if (!e)
			e = window.event;
		if ((e.keyCode || e.which) == 13) {
			var obtnLogin = document.getElementById("submit_btn")
			obtnLogin.focus();
		}
	}
}

$(function() {
	// 提交表单
	$('#submit_btn').click(function() {
		show_loading();
		var myReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; // 邮件正则
		if ($('#login-name').val() == '') {
			show_err_msg('登录名还没填呢！');
			$('#login-name').focus();
		}/*
			 * else if(!myReg.test($('#email').val())){
			 * show_err_msg('您的邮箱格式错咯！'); $('#login-name').focus(); }
			 */else if ($('#login-pass').val() == '') {
			show_err_msg('密码还没填呢！');
			$('#login-pass').focus();
		} else if ($('#kaptcha').val() == '') {
			show_err_msg('验证码还没填呢！');
			$('#kaptcha').focus();
		} else {
			// window.location.href="index.html";
			var loginName = $('#login-name').val();
			var loginPass = $('#login-pass').val();
			var kaptcha = $('#kaptcha').val();
			// ajax提交表单，#login_form为表单的ID。
			// 如：$('#login_form').ajaxSubmit(function(data) { ... });
			$('#login_form').ajaxSubmit({
				type : "post", // 提交方式 get/post
				url : "user/login", // 需要提交的 url
				// dataType: "json",
				data : {
					'loginName' : loginName,
					'loginPass' : loginPass,
					'kaptcha' : kaptcha
				},
				success : function(res) { // data 保存提交后返回的数据，一般为 json 数据
					console.log(res);
					if (res != null) {
						var obj = $.parseJSON(res);
						if (obj.result_code == "success") {
							show_msg('登录成功咯！  正在为您跳转...', 'front/main');
						} else {
							show_err_msg(obj.result_err_msg);
						}
					}
				}
			});

		}
	});
});