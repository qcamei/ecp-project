
$(function(){
	
	bootstrapValidateFun();//启用验证
	
});

/*
 * bootstrap验证
 */
function bootstrapValidateFun(){
	/*
	 * bootstrapValidator验证
	 */
	$("#save-attr-value-form").bootstrapValidator({
	    message: "This value is not valid",
	    feedbackIcons: {
	        valid: "glyphicon glyphicon-ok",
	        invalid: "glyphicon glyphicon-remove",
	        validating: "glyphicon glyphicon-refresh"
	    },
	    
	    fields: {
	    	valueName: {
	            validators: {
	                notEmpty: {
	                    message: "属性值名称不能为空"
	                },
	                stringLength: {
                        max: 20,
                        message: '长度不能超过20个字符'
                    },
	            }
	        },
	        sortNumber: {
	            validators: {
	                notEmpty: {
	                    message: "属性值排序不能为空"
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
	    saveAttrValueFun();//验证通过保存内容
	});
}

/*
 * 保存内容提交
 */
$("#save-attr-value-submit-btn").click(function(){
	$("#save-attr-value-form").submit();
});
/*
 * 保存内容
 */
function saveAttrValueFun(){
	var url = null;
	var id = $("#attr-value-form-cid").val();
	//if(id==null || id==""){
		url = "back/attr/saveCategoryAttrValue";
	//}else{
		//url = "back/attr/updateById";
	//}
	
	//util.loading();
	$("#save-attr-value-form").ajaxSubmit({
		type:"post",
		url:url,
		data:{
		},
		success : function(res) {
			console.log(res);
			if(res!=null){
				var obj = $.parseJSON(res);
				if(obj.result_code=="success"){
					$('#edit-category-attr-value-modal').modal('hide');
					$('#edit-category-attr-value-modal').on('hidden.bs.modal', function(){
						//操作成功后重新加载当前菜单内容
						reloadCategoryAttrValItem();
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
function selectAttrValDetails(valueId){
	var url = "back/attr/selectAttrValUpdateById";
	var params = {"valueId":valueId};
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null){
			var resp = $.parseJSON(res);
			if(resp.result_code=="success"){
				var attrValue =resp.attrValue;
				var categoryAttrVal =resp.categoryAttrVal;
				
				$("#attr-value-form-attr-value-id").val(attrValue.valueId);
				$("#attr-value-form-attr-value-name").val(attrValue.valueName);
				
				$("#attr-value-form-cate-attr-value-id").val(categoryAttrVal.id);
				$("#attr-value-form-attr-value-sort").val(categoryAttrVal.sortNumber);
				
				$("#attr-value-form-cid").val(categoryAttrVal.cid);
				$("#attr-value-form-attr-id").val(categoryAttrVal.attrId);
				
				$("#attr-value-modal-title").text("修改属性值");
				$('#edit-category-attr-value-modal').modal('show');
				return;
			}
		}
		util.message("查询异常");
		
	});
}

/*
 * 删除信息AJAX请求（物理删除）
 */
function deleteCategoryAttrValAjaxRequest(valueId){
	var url = "back/attr/delCategoryAttrValue";
	var params = {"valueId":valueId};
	//util.loading();
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null && res!=""){
			var obj = $.parseJSON(res);
			if(obj.result_code=="success"){
				reloadCategoryAttrValItem();
			}else{
				util.message(obj.result_err_msg);
			}
		}
		
	});
}

/*
 * 单个删除
 */
function deleteCategoryAttrVal(valueId){
	util.delConfirm("确认删除？", valueId, "deleteCategoryAttrValAjaxRequest");
}

/*
 * 重新加载
 */
function reloadCategoryAttrValItem(){
	//操作成功后重新加载
	var params = new Object();
	selectCategoryAttrValItemByPagehelper(params);//获取类目ID查询类目属性
}

/*
 * 点击列表中某个复选框时，全选或反选
 */
function checkAttrValOne(){
    
    var flag = true;
    $("#category-attr-value-table tbody input[type='checkbox']").each(function(){
    	if(!$(this).prop("checked")){
    		flag = false;
    	}
    });
    if(flag){
    	$("#category-attr-value-table thead input[type='checkbox']").prop('checked', true);
    }else{
    	$("#category-attr-value-table thead input[type='checkbox']").prop('checked', false);
    }
}
/*
 * 点击列表中All复选框时，列表全选或反选
 */
function checkAttrValAll(obj){
	$("#category-attr-value-table tbody input[type='checkbox']").prop('checked', $(obj).prop('checked'));
}

/*
 * 重置form表单
 */
function resetAttrValueForm(){
	$("#save-attr-value-form").data('bootstrapValidator').destroy();//销毁bootstrapValidator验证
	bootstrapValidateFun();//启用验证
	$('#save-attr-value-form')[0].reset();
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