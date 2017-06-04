<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中粮邀请函系统</title>
<%@ include file="common/headCss.jsp"%>
<style type="text/css">
body {
    font-family: 'Open Sans', sans-serif;
    background: #2c445a;
}
</style>
</head>

<body>
	<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle waves-effect waves-dark" data-toggle="collapse" data-target=".sidebar-collapse">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand waves-effect waves-dark" href="front/main"><i class="large material-icons">insert_chart</i> <strong>邀请函系统</strong></a>

				<div id="sideNav" title="隐藏/显示左侧菜单" href="">
					<i class="material-icons dp48">toc</i>
				</div>
			</div>

			<ul class="nav navbar-top-links navbar-right">
				<!--
				<li><a title="信息" class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown4"><i class="fa fa-envelope fa-fw"></i> <i class="material-icons right">arrow_drop_down</i></a></li>				
				<li><a title="任务" class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown3"><i class="fa fa-tasks fa-fw"></i> <i class="material-icons right">arrow_drop_down</i></a></li>
				<li><a title="提示" class="dropdown-button waves-effect waves-dark" href="#!" data-activates="dropdown2"><i class="fa fa-bell fa-fw"></i> <i class="material-icons right">arrow_drop_down</i></a></li>
				-->
				<li><a title="个人" class="dropdown-button waves-effect waves-dark" href="javascript:void();" data-activates="dropdown1"><i class="fa fa-user fa-fw"></i> <b>${empty user.nickName ? "用户名" : user.nickName}</b> <i class="material-icons right">arrow_drop_down</i></a></li>
			</ul>
		</nav>
		<!-- Dropdown Structure 顶端导航栏 菜单项 相关结构上下文-->
		<!--
        	作者：David
        	时间：2017-03-02
        	描述：顶端导航栏[个人-登陆用户]菜单点击后下拉框菜单信息
        -->
		<ul id="dropdown1" class="dropdown-content" title="个人信息">
			<li title="个人资料"><a href="javascript:iframeLoading('front/user/personInfo');"><i class="fa fa-user fa-fw"></i>个人资料</a></li>
			<li title="修改密码"><a href="javascript:iframeLoading('front/user/editPass');"><i class="fa fa-gear fa-fw"></i>修改密码</a></li>
			<li title="退出系统"><a href="logout"><i class="fa fa-sign-out fa-fw"></i>退出</a></li>
		</ul>

		<!--/. NAV TOP  顶端导航栏 结束 -->

		<!--
        	\. NAV LEFT SIDE 左侧导航栏开始：
        	作者：offline
        	时间：2017-03-03
        	描述：
        -->
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="main-menu">

					<!--
                    <li>
                        <a class="active-menu waves-effect waves-dark" href="index.html"><i class="fa fa-dashboard"></i> Dashboard</a>
                    </li>
                    -->

					<li class="menu-li-1 active"><a href="#" class="active-menu waves-effect waves-dark"><i class="fa fa-sitemap"></i> 客户管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="javascript:iframeLoading('front/customer/manager');">客户信息</a></li>
							<li><a href="javascript:iframeLoading('front/import/goImportPage');">批量导入客户</a></li>
						</ul></li>

					<li class="menu-li-1"><a href="#" class=" waves-effect waves-dark"><i class="fa fa-sitemap"></i> 邀请函管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="javascript:iframeLoading('front/invitation/selectItem');">邀请函管理</a></li>
							<li><a href="javascript:iframeLoading('front/invUrl/goPage');">生成客户邀请函</a></li>
							<!-- <li><a href="#">发送邀请函</a></li>
							<li><a href="#">历史邀请函</a></li> -->
						</ul></li>
					<li class="menu-li-1"><a href="#" class="active-menu waves-effect waves-dark"><i class="fa fa-sitemap"></i>登录帐号管理<span class="fa arrow"></span></a>
						<ul class="nav nav-second-level">
							<li><a href="javascript:iframeLoading('front/user/manager');">登陆账号信息</a></li>
						</ul>
					</li>
				</ul>

			</div>
		</nav>
		<!-- /. NAV LEFT SIDE  左侧导航栏结束-->
		<!-- 内容区域 -->
		<div id="page-wrapper">
			<div id="page-inner" >
				<iframe onload="javascript:iframeLoaded();" id="inner-iframe" src="pages/front/welcome.jsp" scrolling="no" frameborder="0" seamless style="width:100%;"></iframe>
			</div>
		</div>
		<!-- /. PAGE WRAPPER  -->
	</div>
	<!-- /. WRAPPER  -->
	
	<!-- JS Scripts-->
	<%@ include file="common/headJs.jsp"%>
	
	<script type="text/javascript">
		/*
		 * 设置当前选中一级菜单的样式
		 */
		$("#main-menu .menu-li-1").click(function() {
			//$("#main-menu .menu-li-1").removeClass("active");
			$("#main-menu .menu-li-1 a").removeClass("active-menu");
			//$(this).addClass("active");
			$(this).children('a').addClass("active-menu");
		});
		/*
		 * 设置当前选中二级菜单的样式
		 */
		$("#main-menu .menu-li-1 ul li").click(function(event) {
			//$("#main-menu .menu-li-1 ul li").removeClass("active");
			$("#main-menu .menu-li-1 ul li a").removeClass("active-menu");
			//$(this).addClass("active");
			$(this).children('a').addClass("active-menu");
			event.stopPropagation();
		});
		/*
		 * 加载页面函数
		 */
		function iframeLoading(href) {
			show_loading();
			$("#inner-iframe").attr("src", href);
		}
		/*
		 * iframe加载页面完成函数
		 */
		function iframeLoaded(){
			console.log("loaded");
			var ifm= document.getElementById("inner-iframe"); 
		    ifm.height=document.documentElement.clientHeight;
			show_loaded();
		}
		
	</script>

</body>

</html>