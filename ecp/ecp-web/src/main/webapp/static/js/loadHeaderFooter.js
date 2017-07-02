//============加载页面通用部分===============
/* 
	功能描述：加载页
	参数说明：containerId 窗口id
		   url:请求加载的页面地址
		   parms:参数
		   callbackFunc:加载成功后的回调函数
 */
function loadPage(containerId, url, parms, callbackFunc) {
	var parms = parms;
	url = BASE_CONTEXT_PATH + url;
	if (callbackFunc == null) {
		callbackFunc = defaultCallbackFunc();
	}
	$(containerId).load(url, parms, callbackFunc);
}

/* 默认的回调函数 */
function defaultCallbackFunc() {
	// alert("load page success!");
}

// =================业务部分===============

/* 加载页头 */
function loadHeader() {
	var url = "/front/home/header";
	var parms = null;
	var callbackFunc = loadFooter; //header load 完毕后再load footer
	var containerId = "#header";
	loadPage(containerId, url, parms, callbackFunc);
}

/* 加载页尾 */
function loadFooter() {
	var url = "/front/home/footer";
	var parms = null;
	var callbackFunc = null;
	var containerId = "#footer";
	loadPage(containerId, url, parms, callbackFunc);
}


// =============页面加载初始化部分==================
function initPage() {
	loadHeader();
	//loadFooter();
}

// =============current page loaded ready===============
$(function() {
	initPage();
});