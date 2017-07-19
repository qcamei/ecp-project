/*查询-订单 */


/**
 * 根据用户的输入条件查询（包括分页数据）
 * @returns
 */
function search(){
	parms=new Object(); //生成参数对象
	//分页数据
	parms.pageNum=$("#pageNum").val();
	parms.pageSize=$("#pageSize").val();
	
	
	/*parms.searchTypeValue=condType;
	parms.condValue=condStr;*/
	
	loadOrder(parms,null); // 加载页面
}

function search_normal() {
	//console.log("debug");
	search();
}

/**
 * 生成隐藏表单域函数
 * 
 * @param {Object}
 *            name input's name
 * @param {Object}
 *            value value:inptu's value
 */
var generateHideElement = function(name, value) {
	var tempInput = document.createElement("input");
	tempInput.type = "hidden";
	tempInput.name = name;
	tempInput.value = value;
	return tempInput;
}

/**
 * 创建form并提交,创建合同请求 *
 * 
 * @param {Object}
 *            url 提交的地址
 * @param {Object}
 *            id 订单id
 * @param {Object}
 *            orderId 订单号
 */
function createFormAndCommit(url, id, orderId) {
	var form = document.createElement("form");
	form.id = "test";
	form.name = "test";
	document.body.appendChild(form);

	// 生成隐藏表单中的内容
	var id = generateHideElement("id", id), orderId = generateHideElement(
			"orderId", orderId);

	form.appendChild(id);
	form.appendChild(orderId);

	form.method = "post";
	form.action = url;
	form.submit();
}

//==================page loaded ready======================
$(function() {

	/*
	 * 显示【详情】按钮-click
	 */
	$(".show-detail").on("click", function(e) {

	});

	/*
	 * 【创建合同】按钮
	 */
	$(".create-contract").on("click", function(e) {
		var url = BASE_CONTEXT_PATH + "/back/contract/add"; // 需要提交的 url
		var id = $(this).attr("data-id");
		var orderId = $(this).attr("data-orderid");

		createFormAndCommit(url, id, orderId);
	});

	/*
	 * 【分页】导航： 当点击页号时读取需要导航到的页码及每页记录数（pageNum,pageSize）
	 * data-bind:pageNum-pageSize形式 如果data-bind为空字符串，则不做动作 当用户点击某页时，则发送与筛选相同的请求
	 */

	$(".pagination li a").on('click', function(e) {
		// alert($(this).attr("data-bind"));
		//console.log("debug1");
		var dataBind = $(this).attr("data-bind");
		// 当dataBind有数据时处理
		if (dataBind != "") {
			var pageArr = new Array();
			pageArr = dataBind.split("-");
			// 置隐藏表单数据
			$("#pageNum").val(pageArr[0]);
			$("#pageSize").val(pageArr[1]);
			// 发送请求
			search_normal();
		}

	});

});