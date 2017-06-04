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
		    <label for="loginPass" class="col-sm-2 control-label">登录密码:</label>
		    <div class="col-sm-10">
		      <input type="password" class="form-control" id="loginPass" placeholder="Password">
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="passSecond" class="col-sm-2 control-label">重复密码:</label>
		    <div class="col-sm-10">
		      <input type="password" class="form-control" id="passSecond" placeholder="Password">
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
	if($("#email").val()==null || $("#email").val().length<1){
		alert("邮箱不能为空！");
		return;
	}
	if($("#loginPass").val()==null || $("#loginPass").val().length<1){
		alert("密码不能为空！");
		return;
	}
	if($("#loginPass").val()!=$("#passSecond").val()){
		alert("两遍密码不一致请重新输入！");
		return;
	}
 	$.ajax({
 		type:"POST",
 		data:{
 			loginId:$("#loginId").val(),
 			loginPass:$("#loginPass").val(),
 			email:$("#email").val(),
 		},
 		url:'front/user/editUser',
 		dataType:'json',
 		success:function(data){
 			if(data){
 				show_msg("保存成功！", "front/user/msg");
 			}
 		},
 		error:function(){
 			alert("请求异常！！！");
 		}
 	});
 }
</script>
</html>
