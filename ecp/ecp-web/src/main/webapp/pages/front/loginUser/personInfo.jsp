<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html >
<head>
    <title></title>
    <%@ include file="../common/headCss.jsp"%>
</head>
<body>

	<form class="form-horizontal">
		  <div class="form-group">
		   <!--  <label for="inputEmail3" class="col-sm-2 control-label">用户ID:</label> -->
		    <div class="col-sm-10">
		      <input type="hidden" class="form-control" id="loginId" placeholder="Email"  value="${userbean.loginId}">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="loginName" class="col-sm-2 control-label">登录名:</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="loginName" disabled="disabled" value="${userbean.loginName}">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="fullName" class="col-sm-2 control-label">用户名:</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="fullName" value="${userbean.fullName}">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="nickName" class="col-sm-2 control-label">用户昵称:</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="nickName" value="${userbean.nickName}">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="mobile" class="col-sm-2 control-label">联系电话:</label>
		    <div class="col-sm-10">
		      <input type="text" class="form-control" id="mobile" value="${userbean.mobile}">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="email" class="col-sm-2 control-label">电子邮箱:</label>
		    <div class="col-sm-10">
		      <input type="email" class="form-control" id="email" placeholder="电子邮箱" value="${userbean.email}">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
		    	<center>
		     		 <button type="button" class="btn btn-default" onclick="saveOrUpdate();">提交</button>
		        </center>
		    </div>
		  </div>
    </form> 
</body>

<!-- JS Scripts-->
<%@ include file="../common/headJs.jsp"%>

<script type="text/javascript">
function saveOrUpdate(){
	if($("#fullName").val()==null || $("#fullName").val().length<1){
		alert("用户名不能为空！");
		return;
	}
	
	if($("#mobile").val()==null || $("#mobile").val().length<1){
		alert("联系电话不能为空！");
		return;
	}
	
	if($("#email").val()==null || $("#email").val().length<1){
		alert("邮箱不能为空！");
		return;
	}
	
 	$.ajax({
 		type:"POST",
 		data:{
 			loginId:$("#loginId").val(),
 			fullName:$("#fullName").val(),
 			nickName:$("#nickName").val(),
 			mobile:$("#mobile").val(),
 			email:$("#email").val(),
 		},
 		url:'front/user/editUser',
 		dataType:'json',
 		success:function(data){
 			if(data){
 				alert("修改资料成功！！");
 				window.location.reload();
 			}
 		},
 		error:function(){
 			alert("请求异常！！！");
 		}
 	});
 }
</script>
</html>
