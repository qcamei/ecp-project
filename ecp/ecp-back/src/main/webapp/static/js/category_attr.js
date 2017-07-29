
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
	$("#save-attr-form").bootstrapValidator({
	    message: "This value is not valid",
	    feedbackIcons: {
	        valid: "glyphicon glyphicon-ok",
	        invalid: "glyphicon glyphicon-remove",
	        validating: "glyphicon glyphicon-refresh"
	    },
	    
	    fields: {
	    	attrName: {
	            validators: {
	                notEmpty: {
	                    message: "属性名称不能为空"
	                },
	                stringLength: {
                        max: 20,
                        message: '长度不能超过20个字符'
                    },
	            }
	        },
	        attrType: {
	            validators: {
	                notEmpty: {
	                    message: "请选择属性类型"
	                },
	            }
	        },
	        inputType: {
	            validators: {
	                notEmpty: {
	                    message: "请选择属性类型"
	                },
	            }
	        },
	        sortNumber: {
	            validators: {
	                notEmpty: {
	                    message: "排列序号"
	                },
	                stringLength: {
                        max: 10,
                        message: '长度不能超过10个字符'
                    },
	            }
	        },
	    }
	}).on('success.form.bv',function(e){
	    e.preventDefault();
	    saveAttrFun();//验证通过保存内容
	});
}

/*
 * 保存内容提交
 */
$("#save-attr-submit-btn").click(function(){
	$("#save-attr-form").submit();
});
/*
 * 保存内容
 */
function saveAttrFun(){
	var url = "back/attr/savecategoryattr";
	
	//util.loading();
	$("#save-attr-form").ajaxSubmit({
		type:"post",
		url:url,
		data:{
		},
		success : function(res) {
			console.log(res);
			if(res!=null){
				var obj = $.parseJSON(res);
				if(obj.result_code=="success"){
					$('#edit-category-attr-modal').modal('hide');
					$('#edit-category-attr-modal').on('hidden.bs.modal', function(){
						//操作成功后重新加载当前菜单内容
						reloadCategoryAttrItem();
					});
					
				}else{
					util.message(obj.result_err_msg);
				}
			}
		},
	});
}

/*
 * 查看详细信息
 */
function selectAttrDetails(cid, attrId){
	var url = "back/attr/selectAttrUpdateById";
	var params = {"attrId":attrId};
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null){
			var resp = $.parseJSON(res);
			if(resp.result_code=="success"){
				var attribute =resp.attribute;//属性
				var categoryAttr = resp.categoryAttr;//类目属性
				
				$("#attr-form-attr-id").val(attribute.attrId);//属性ID
				$("#attr-form-attr-name").val(attribute.attrName);//属性名称
				
				$("#attr-form-cate-attr-id").val(categoryAttr.id);//类目属性ID
				$("#attr-form-attr-type").val(categoryAttr.attrType);//属性类型
				console.log(categoryAttr.optionType);
				$("#attr-form-option-type input:radio[name='optionType'][value='"+categoryAttr.optionType+"']").attr("checked",true);//是否必填
				$("#attr-form-input-type").val(categoryAttr.inputType);//属性输入类型
				$("#attr-form-sort").val(categoryAttr.sortNumber);//排列序号
				
				$("#attr-form-cid").val(categoryAttr.cid);
				
				$("#attr-modal-title").text("修改属性");
				$('#edit-category-attr-modal').modal('show');
				return;
			}
		}
		util.message("查询异常");
		
	});
}

/*
 * 删除信息AJAX请求（物理删除）
 */
function deleteCategoryAttrAjaxRequest(attrId){
	var url = "back/attr/delCategoryAttr";
	var params = {"attrId":attrId};
	//util.loading();
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null && res!=""){
			var obj = $.parseJSON(res);
			if(obj.result_code=="success"){
				reloadCategoryAttrItem();
			}else{
				util.message(obj.result_err_msg);
			}
		}
		
	});
}

/*
 * 单个删除
 */
function deleteCategoryAttr(cid, attrId){
	util.delConfirm("确认删除？", attrId, "deleteCategoryAttrAjaxRequest");
}

/*
 * 重新加载
 */
function reloadCategoryAttrItem(){
	//操作成功后重新加载
	var params = new Object();
	selectCategoryAttrItemByPagehelper(params);//获取类目ID查询类目属性
}

/*
 * 点击列表中某个复选框时，全选或反选
 */
function checkAttrOne(){
    
    var flag = true;
    $("#category-attr-table tbody input[type='checkbox']").each(function(){
    	if(!$(this).prop("checked")){
    		flag = false;
    	}
    });
    if(flag){
    	$("#category-attr-table thead input[type='checkbox']").prop('checked', true);
    }else{
    	$("#category-attr-table thead input[type='checkbox']").prop('checked', false);
    }
}
/*
 * 点击列表中All复选框时，列表全选或反选
 */
function checkAttrAll(obj){
	$("#category-attr-table tbody input[type='checkbox']").prop('checked', $(obj).prop('checked'));
}

/*
 * 重置form表单
 */
function resetAttrForm(){
	$("#save-attr-form").data('bootstrapValidator').destroy();//销毁bootstrapValidator验证
	bootstrapValidateFun();//启用验证
	$('#save-attr-form')[0].reset();
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