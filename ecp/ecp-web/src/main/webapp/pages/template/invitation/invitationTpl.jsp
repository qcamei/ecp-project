<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="format-detection" content="telephone=no">
<title>2017年春季成都糖酒会邀请函</title>
<meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0' />
<link rel="stylesheet" href="pages/template/invitation/css/swiper.min.css">
<link rel="stylesheet" href="pages/template/invitation/css/animate.min.css">
<link rel="stylesheet" type="text/css" href="pages/template/invitation/css/common.css">
<script src="pages/template/invitation/js/swiper.min.js"></script>
<script src="pages/template/invitation/js/swiper.animate.min.js"></script>
<style>
	* {margin: 0;padding: 0;}
	html,
	body {height: 100%;}
	body {font-family: "microsoft yahei";}
	.swiper-container {/*  width: 320px;height: 480px;*/width: 100%;height: 100%;background: #000;}
	.swiper-slide {width: 100%;height: 100%;}
	img {display: block;}
	.swiper-pagination-bullet {width: 6px;height: 6px;background: #fff;opacity: .4;}
	.swiper-pagination-bullet-active {opacity: 1;}
	@-webkit-keyframes start {0%,30% {opacity: 0;-webkit-transform: translate(0, 10px);}60% {opacity: 1;-webkit-transform: translate(0, 0);}100% {opacity: 0;-webkit-transform: translate(0, -8px);}}		
	@-moz-keyframes start {0%,30% {opacity: 0;-moz-transform: translate(0, 10px);}60% {opacity: 1;-moz-transform: translate(0, 0);}100% {opacity: 0;-moz-transform: translate(0, -8px);}}
	@keyframes start {0%,30% {opacity: 0;transform: translate(0, 10px);}60% {opacity: 1;transform: translate(0, 0);}100% {opacity: 0;transform: translate(0, -8px);}}	
	.ani {	position: absolute;}
	.txt {position: absolute;}
	#array {position: absolute;z-index: 999;-webkit-animation: start 1.5s infinite ease-in-out;-moz-animation: start 1.5s infinite ease-in-out;}
</style>

</head>
<div style="display:none"><img src="pages/template/invitation/images/p1.png" alt=""></div>
<body>
	<div class="swiper-container">
		<!--音乐按钮开始-->
		<section class="musicpage">
		<div id="soundButton" class="soundButton openSound"></div>
		</section>
		<!--音乐按钮结束-->
		<div class="swiper-wrapper">
			<!-------------slide1----------------->
			<section class="swiper-slide swiper-slide1 slide1" style="background-image:url(pages/template/invitation/images/p1.png);"> </section>
			<!---------------slide2-------------->
			<section class="swiper-slide swiper-slide2 slide2" style="background-image:url(pages/template/invitation/images/p2.png);">
			<div class="neirong">
				<h4 class="name_title">
					<span class="name_left">尊敬的${customer.cusName}:</span>
				</h4>
				<p class="content">${invitation.invContent}</p>
			</div>
			</section>
			<!----------------slide3-------------->
			<section class="swiper-slide swiper-slide3 slide3" style="background-image:url(pages/template/invitation/images/p3.png);"> </section>
			<!-------------slide4----------------->
			<section class="swiper-slide swiper-slide4 slide4" style="background-image:url(pages/template/invitation/images/p4.png);">
			<div class="neirong4">
				<p class="content4">时间：${invitation.actDate}</p>
				<p class="content4">地点：${invitation.actAddress}</p>
				<p class="content4">联系人：${invitation.linkMan}</p>
				<p class="content4">电话：${invitation.phone}</p>
			</div>
			</section>
		</div>
		<!--箭头img-->
		<img src="pages/template/invitation/images/P1_19.png" style="width: 28px; height: 15px; bottom: 5%; right: 45%;" id="array" class="resize">
	</div>
	<script>
		scaleW = window.innerWidth / 320;
		scaleH = window.innerHeight / 480;
		var resizes = document.querySelectorAll('.resize');
		for (var j = 0; j < resizes.length; j++) {
			resizes[j].style.width = parseInt(resizes[j].style.width) * scaleW + 'px';
			resizes[j].style.height = parseInt(resizes[j].style.height) * scaleH + 'px';
			resizes[j].style.top = parseInt(resizes[j].style.top) * scaleH + 'px';
			resizes[j].style.left = parseInt(resizes[j].style.left) * scaleW + 'px';
		}

		var mySwiper = new Swiper('.swiper-container', {
			direction : 'vertical',
			pagination : '.swiper-pagination',
			//virtualTranslate : true,
			mousewheelControl : true,
			onInit : function(swiper) {
				swiperAnimateCache(swiper);
				swiperAnimate(swiper);
			},
			onSlideChangeEnd : function(swiper) {
				swiperAnimate(swiper);
			},
			onTransitionEnd : function(swiper) {
				swiperAnimate(swiper);
			},

			watchSlidesProgress : true,

			onProgress : function(swiper) {
				for (var i = 0; i < swiper.slides.length; i++) {
					var slide = swiper.slides[i];
					var progress = slide.progress;
					var translate = progress * swiper.height / 4;
					scale = 1 - Math.min(Math.abs(progress * 0.5), 1);
					var opacity = 1 - Math.min(Math.abs(progress / 2), 0.5);
					slide.style.opacity = opacity;
					es = slide.style;
					es.webkitTransform = es.MsTransform = es.msTransform = es.MozTransform = es.OTransform = es.transform = 'translate3d(0,' + translate + 'px,-' + translate + 'px) scaleY(' + scale + ')';

				}
			},

			onSetTransition : function(swiper, speed) {
				for (var i = 0; i < swiper.slides.length; i++) {
					es = swiper.slides[i].style;
					es.webkitTransitionDuration = es.MsTransitionDuration = es.msTransitionDuration = es.MozTransitionDuration = es.OTransitionDuration = es.transitionDuration = speed + 'ms';

				}
			},

		})
	</script>

	<script type="text/javascript" src="pages/template/invitation/js/jquery.min.js"></script>
	<script type="text/javascript" src="pages/template/invitation/js/jquery.lazyload.min.js"></script>
	<script src="pages/template/invitation/js/init.mix.js" type="text/javascript" charset="utf-8"></script>
	<script src="pages/template/invitation/js/main.js" type="text/javascript" charset="utf-8"></script>
	<script src="pages/template/invitation/js/tx.js" type="text/javascript" charset="utf-8"></script>
	<script src="pages/template/invitation/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript">
		//初始化贵宾为空
		var guest = "0";
		/*蒙版开关*/
		var ismengban = 'cmoren';
		//特效类型
		var texiao = 'meteor';
		/*倒计时*/
		var sstime = '13113127';
		/**微信分享js*/
		var desc = "击查看详情...";
		var title = "-案例";
		var url = location.href;
		var imgurl = 'http://yin.we6.com';
		/*特效图片路径*/
		var tximgurl = 'http://yin.we6.com';
		$(document).ready(function() {
			/*初始化对象函数*/
			car2.init();
			/*微信分享函数*/
			car2.weixinfenxiang(desc, title, url, imgurl, "");
			/**相册控制初始化*/
			$("img.lazy").lazyload({
				effect : "fadeIn",
				event : "sporty"
			});
			qjpic.photo_init();
			/*倒计时*/
			car2.djs(sstime);
		});
		$(window).bind("load", function() {
			var timeout = setTimeout(function() {
				$("img.lazy").trigger("sporty")
			}, 2500);
		});
	</script>
	<!-- 背景音乐开始 -->
	<audio src="pages/template/invitation/music/Annie's Wonderland.mp3" id="audio_play" class="audio" loop=""></audio>
</body>

</html>