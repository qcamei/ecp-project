<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<html >
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title></title>
</head>
<body>
  	
  	 <div class="header"> 
         <h1 class="page-header">
             Tables Page
         </h1>
	<ol class="breadcrumb">
  <li><a href="#">Home</a></li>
  <li><a href="#">Tables</a></li>
  <li class="active">Data</li>
</ol> 
									
	</div>
	<div id="page-inner"> 
	    <div class="row">
	        <div class="col-md-12">
	            <!-- Advanced Tables -->
	            <div class="card">
	                <div class="card-action">
	                   		客户管理
	                </div>
	                <div class="card-content">
	                    <div class="table-responsive">
	                        <table class="table table-striped table-bordered table-hover" id="dataTables-example">
	                            <thead>
	                                <tr>
	                                    <th>ID</th>
	                                    <th>客户名</th>
	                                    <th>客户类型</th>
	                                    <th>客户电话</th>
	                                    <th>创建时间</th>
	                                    <th>修改时间</th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                            	<c:forEach items="${results}" var="customer">
		                                <tr>
		                                   <td>${customer.customerId}</td>
		                                   <td>${customer.cusName}</td>
		                                   <td>${customer.cusType==1?"企业":"个人"}</td>
		                                   <td>${customer.mobile}</td>
		                                   <td><fmt:formatDate type="both" value="${customer.createTime}"/></td>
		                                   <td><fmt:formatDate type="both" value="${customer.updateTime}"/></td>
		                               </tr>
	                            	</c:forEach>
	                            </tbody>
	                        </table>
	                    </div>
	                    
	                </div>
	            </div>
	            <!--End Advanced Tables -->
	        </div>
	    </div>
            
        <footer><p>Copyright &copy; 2016.Company name All rights reserved.<a target="_blank" href="http://www.mycodes.net/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p></footer>
    </div>
     
 

    
    <script src="pages/front/assets/js/jquery-1.10.2.js"></script>
    <script src="pages/front/assets/js/bootstrap.min.js"></script>
	
	 <!-- DATA TABLE SCRIPTS -->
    <script src="pages/front/assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="pages/front/assets/js/dataTables/dataTables.bootstrap.js"></script>
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
    </script>
    <!-- Custom Js -->
    <script src="pages/front/assets/js/custom-scripts.js"></script> 
 

</body>

</html>
