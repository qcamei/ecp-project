/* reset dialog */
function resetDialog_change_password() {
	$("#change-password-form")[0].reset();
}

/*
 * 显示增加用户地址对话框
 */
function showDialog_change_password() {
	$('#modal-container-change-password').modal({
		backdrop : 'static',
		keyboard : false
	});
}

/* close dialog */
function closeDialog_change_password() {
	$("#modal-container-change-password").modal("hide");
}

$(function(){
	
	/* 显示修改用户口令对话框*/
	$("#change-password").on("click",function(){
		resetDialog_change_password();
		showDialog_change_password();
	});
	
	
	/*
	 * 地址编辑对话框：【保存】用户地址
	 */
	$("#btnSaveChangePassword").on('click', function(e) {
		/*if(hasSelectedProvAndCity()){
			if(requiredFieldInputed()){
				closeDialog(); // close dialog(add buyer's delivery address dialog)
				saveFun();
			}
			else{
				util.message("请填写必输入字段！");
			}
			
		}
		else{
			//console.log("请选择省份、城市、区县！");
			util.message("请选择省份、城市、区县！");
		}*/
		
	});
	
	
});