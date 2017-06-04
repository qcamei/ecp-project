
//$(function(){
	
	bootstrapValidateFun();//启用验证
	
//});

/*
 * bootstrap验证
 */
function bootstrapValidateFun(){
	/*
	 * bootstrapValidator验证
	 */
	$("#save-form").bootstrapValidator({
	    message: "This value is not valid",
	    feedbackIcons: {
	        valid: "glyphicon glyphicon-ok",
	        invalid: "glyphicon glyphicon-remove",
	        validating: "glyphicon glyphicon-refresh"
	    },
	    
	    fields: {
	        username: {
	            validators: {
	                notEmpty: {
	                    message: "用户名不能为空"
	                },
	                regexp: {
		                regexp: "^[\u4e00-\u9fa5A-Za-z0-9_\\s+\\\\\/]+$",
		                message: "请勿输入特殊符号"
	                },
	                stringLength: {
                        max: 20,
                        message: '长度不能超过20个字符'
                    }
	            }
	        },
	        password: {
	            validators: {
	                notEmpty: {
	                    message: "密码不能为空"
	                },
	                stringLength: {
                        max: 20,
                        message: '长度不能超过20个字符'
                    }
	            }
	        },
	    }
	}).on('success.form.bv',function(e){
	    e.preventDefault();
	    saveFun();//验证通过保存内容
	});
}

/*
 * 查看详细信息
 */
function selectDetails(userId){
	var url = "front/user/selectUpdateById";
	var params = {"userId":userId};
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null){
			var resp = $.parseJSON(res);
			if(resp.result_code=="success"){
				var user =resp.user;
				$("#user-id").val(user.userId);//ID
				$("#user-name").val(user.username);//用户名
				$("#password").attr("disabled", true);
				$("#full-name").val(user.fullName);//真实姓名
				$("#nick-name").val(user.nickName);//昵称
				$("#state").val(user.state);//性别
				
				$("#edit-tab").text("编辑用户");//修改选项卡标题为编辑用户
				
				$('#tabs-243687 a[href="#tab-2"]').tab('show');
				return;
			}
		}
		util.message("查询异常");
		
	});
}

/*
 * 点击会员列表选项卡时把编辑选项卡标题改为新增
 */
function updateTabTitle(){
	$("#edit-tab").text("新增用户");//修改选项卡标题为编辑用户
}

/*
 * 保存内容提交
 */
$("#save-submit-btn").click(function(){
	$("#save-form").submit();
});
/*
 * 保存内容
 */
function saveFun(){
	var url = null;
	var userId = $("#user-id").val();
	if(userId==null || userId==""){
		url = "front/user/insert";
	}else{
		url = "front/user/updateById";
	}
	
	//util.loading();
	$("#save-form").ajaxSubmit({
		type:"post",
		url:url,
		success : function(res) {
			console.log(res);
			if(res!=null){
				var obj = $.parseJSON(res);
				if(obj.result_code=="success"){
					//操作成功后重新加载当前菜单内容
					reloadInfoFun();
				}else{
					util.message(obj.result_err_msg);
				}
			}
		},
	});
}

/*
 * 删除信息AJAX请求（物理删除）
 */
function deleteInfoAjaxRequest(id){
	var url = "front/user/deleteById";
	var params = {"userId":id};
	//util.loading();
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null && res!=""){
			var obj = $.parseJSON(res);
			if(obj.result_code=="success"){
				reloadInfoFun();
			}else{
				util.message(obj.result_err_msg);
			}
		}
		
	});
}

/*
 * 单个删除
 */
function deleteInfoFun(id){
	util.delConfirm("确认删除？", id, "deleteInfoAjaxRequest");
}

/*
 * 重新加载当前菜单内容
 */
function reloadInfoFun(){
	//操作成功后重新加载
	var href = "front/user/selectItem?pagehelperFun=clickPageBtnRequestFun";
	parent.window.iframeLoading(href);//调用主页面中的在iframe中加载内容的方法
}

/*
 * 点击页面中的页码执行此函数
 * 		函数功能：根据页码数请求当前页内容
 */
function clickPageBtnRequestFun(params){
	var action = "front/user/selectItem";
	params.clickPageBtn = true;
	//util.loading();
	$("#item-div").load(action, params, function(){
		var totals = $("#total-s").val();
		$("#filter-total-s").text(totals);
	});
}

/*
 * 点击列表中某个复选框时，全选或反选
 */
function checkOne(){
    
    var flag = true;
    $("#user-table tbody input[type='checkbox']").each(function(){
    	if(!$(this).prop("checked")){
    		flag = false;
    	}
    });
    if(flag){
    	$("#user-table thead input[type='checkbox']").prop('checked', true);
    }else{
    	$("#user-table thead input[type='checkbox']").prop('checked', false);
    }
}
/*
 * 点击列表中All复选框时，列表全选或反选
 */
function checkAll(obj){
	$("#user-table tbody input[type='checkbox']").prop('checked', $(obj).prop('checked'));
}

/*
 * 重置form表单
 */
function resetFun(){
	$("#save-form").data('bootstrapValidator').destroy();//销毁bootstrapValidator验证
	bootstrapValidateFun();//启用验证
	$('#save-form')[0].reset();
	$("#password").attr("disabled", false);
}

/*
 * 日期转字符串格式函数
 * 调用方法：date.format('yyyy-MM-dd HH:mm:ss');
 */
Date.prototype.format = function(format) {
    var date = {
           "M+": this.getMonth() + 1,
           "d+": this.getDate(),
           "h+": this.getHours(),
           "m+": this.getMinutes(),
           "s+": this.getSeconds(),
           "q+": Math.floor((this.getMonth() + 3) / 3),
           "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
           format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
           if (new RegExp("(" + k + ")").test(format)) {
                  format = format.replace(RegExp.$1, RegExp.$1.length == 1
                         ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
           }
    }
    return format;
}