/***************************************************************************
 * 实例化编辑器
 **************************************************************************/

// 实例化编辑器
// 建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
//window.UEDITOR_HOME_URL = '/aida/tools/'; //一定要用这句话，否则你需要去ueditor.config.js修改路径的配置信息

//alert("home_url:"+UEDITOR_CONFIG.UEDITOR_HOME_URL);

//实例化编辑器并设置编辑器高度和宽度
var editorOption = {
		initialFrameHeight : 200,
		//initialFrameWidth : 300,
		enableAutoSave: false, //启用自动保存
        catchRemoteImageEnable: false, //远程图片抓取,当开启本插件时所有不符合本地域名的图片都将被抓取成为本地服务器上的图片
		toolbars : [ 
			[
				'fullscreen', //全屏
				'source', //源代码
				'|',
				'undo', //撤销
		        'redo', //重做
		        '|',
		        'bold', //加粗
		        'italic', //斜体
		        'underline', //下划线
		        'strikethrough', //删除线
		        'fontborder', //字符边框
		        'superscript', //上标
		        'subscript', //下标
		        'removeformat', //清除格式
		        'formatmatch', //格式刷
		        'autotypeset', //自动排版
		        'blockquote', //引用
		        'pasteplain', //纯文本粘贴模式
		        '|',
		        'forecolor', //字体颜色
		        'backcolor', //背景色
		        'insertorderedlist', //有序列表
		        'insertunorderedlist', //无序列表
		        'selectall', //全选
		        'cleardoc', //清空文档
		        '|',
		        'rowspacingtop', //段前距
		        'rowspacingbottom', //段后距
		        'lineheight', //行间距
		        '|',
		        'customstyle', //自定义标题
		        'paragraph', //段落格式
		        'fontfamily', //字体
		        'fontsize', //字号
		        '|',
		        'directionalityltr', //从左向右输入
		        'directionalityrtl', //从右向左输入
		        'indent', //首行缩进
		        '|',
		        'justifyleft', //居左对齐
		        'justifycenter', //居中对齐
		        'justifyright', //居右对齐
		        'justifyjustify', //两端对齐
		        '|',
		        'touppercase', //字母大写
		        'tolowercase', //字母小写
		        '|',
		        'link', //超链接
		        'unlink', //取消链接
				'anchor', //锚点
				'|',
				'imagenone', //默认
		        'imageleft', //左浮动
		        'imageright', //右浮动
		        'imagecenter', //居中
		        '|',
		        'simpleupload', //单图上传
		        'insertimage', //多图上传
		        'emotion', //表情
		        'scrawl', //涂鸦
		        //'insertvideo', //视频
		        //'music', //音乐
		        //'attachment', //附件
		        'map', //Baidu地图
		        //'gmap', //Google地图
		        'insertframe', //插入Iframe
		        'insertcode', //代码语言
		        //'webapp', //百度应用
		        'pagebreak', //分页
		        'template', //模板
		        'background', //背景
		        '|',
		        'horizontal', //分隔线
		        'time', //时间
		        'date', //日期
		        'spechars', //特殊字符
		        //'snapscreen', //截图
		        //'wordimage', //图片转存
		        '|',
		        'inserttable', //插入表格
		        'deletetable', //删除表格
		        'insertparagraphbeforetable', //"表格前插入行"
		        //'insertrow', //前插入行
		        'deleterow', //删除行
		        //'insertcol', //前插入列
		        'deletecol', //删除列
		        'mergecells', //合并多个单元格
		        'mergeright', //右合并单元格
		        'mergedown', //下合并单元格
		        'splittocells', //完全拆分单元格
		        'splittorows', //拆分成行
		        'splittocols', //拆分成列
		        /*'charts', // 图表
		        '|',
		        'edittip ', //编辑提示
		        'deletecaption', //删除表格标题
		        'inserttitle', //插入标题
		        'edittable', //表格属性
		        'edittd', //单元格属性*/
		        '|',
		        'print', //打印
		        'preview', //预览
		        'searchreplace', //查询替换
		        'drafts', // 从草稿箱加载
		        'help', //帮助
		    ]
		]
	};

var detail = new UE.ui.Editor(editorOption);  
detail.render("item-ueditor");

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
	$("#save-form").bootstrapValidator({
	    message: "This value is not valid",
	    feedbackIcons: {
	        valid: "glyphicon glyphicon-ok",
	        invalid: "glyphicon glyphicon-remove",
	        validating: "glyphicon glyphicon-refresh"
	    },
	    
	    fields: {
	        itemName: {
	            validators: {
	                notEmpty: {
	                    message: "商品名称不能为空"
	                },
	                regexp: {
		                regexp: "^[\u4e00-\u9fa5A-Za-z0-9_\\s+\\\\\/]+$",
		                message: "请勿输入特殊符号"
	                },
	                stringLength: {
                        max: 100,
                        message: '长度不能超过100个字符'
                    }
	            }
	        },
	        keywords: {
	        	validators: {
	                notEmpty: {
	                    message: "关键字不能为空"
	                },
	                regexp: {
		                regexp: "^[\u4e00-\u9fa5A-Za-z0-9_\\s+\\\\\/]+$",
		                message: "请勿输入特殊符号"
	                },
	                stringLength: {
                        max: 700,
                        message: '长度不能超过700个字符'
                    }
	            }
	        }
	        
	    }
	}).on('success.form.bv',function(e){
	    e.preventDefault();
	    saveFun();//验证通过保存内容
	});
}

/*
 * 查看详细信息
 */
function selectDetails(id){
	var url = "back/item/selectUpdateById";
	var params = {"id":id};
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null){
			var resp = $.parseJSON(res);
			if(resp.result_code=="success"){
				var item =resp.item;
				$("#item-id").val(item.itemId);//ID
				$("#item-name").val(item.itemName);//商品名称
				/*$("#categoryid").val(product.categoryid);//商品分类
				$("#introduction").val(product.introduction);//商品简介
				$("#price").val(product.price);//商品价格
				$("#inventory").val(product.inventory);//商品库存数量
				$("#state").val(product.state);//商品上架状态
				$("#isrecommend").val(product.isrecommend);//是否为推荐商品
				$("#isnew").val(product.isnew);//是否新品
				$("#ishot").val(product.ishot);//是否热卖商品
				$("#imgurl").val(product.imgurl);//缩略图
				$("#create-time-str").val(new Date(product.createtime).format('yyyy-MM-dd HH:mm:ss'));//创建时间*/
				
				$('#tabs-243687 a[href="#tab-2"]').tab('show');
				return;
			}
		}
		util.message("查询异常");
		
	});
}

/*
 * 根据父ID查询类目
 * 		参数1：父ID
 * 		参数2：第n级类目
 */
function selectCategoryByPid(pid, index){
	
	index++;
	var url = "back/category/selectByPid";
	var params = {"pid":pid};
	//util.loading();
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null && res!=""){
			var obj = $.parseJSON(res);
			if(obj.result_code=="success"){
				var categoryList = obj.categoryList;
				console.log(JSON.stringify(categoryList));
				if(categoryList!=null){
					var htmlStr = "";
					$.each(obj.categoryList,function(i,n){
					    //console.log("索引"+i+"对应的值"+n);
					    htmlStr += createCategoryHtmlStr(this, index);//创建类目HTML字符串
					});
					console.log(htmlStr);
					if(index==2){
				    	$("#second-category").html(htmlStr);
				    	$("#third-category").html("");
				    	bindClickEven(2);//给二级类目绑定点击事件，选择后把当前选择的突出显示
				    }else if(index==3){
				    	$("#third-category").html(htmlStr);
				    	bindClickEven(3);//给三级类目绑定点击事件，选择后把当前选择的突出显示
				    }else{
				    	console.log("异常");
				    }
				}else{
					util.message("查询类目列表为null");
				}
			}else{
				util.message(obj.result_err_msg);
			}
		}
		
	});
}
/*
 * 创建类目HTML字符串
 */
function createCategoryHtmlStr(category, index){	//debugger;
	var htmlStr = "";
	if(index>=3){
		htmlStr += "<li id='third-"+category.cid+"' onclick='javascript:loadAttrPage("+category.cid+", &apos;"+category.cName+"&apos;)'>";
		htmlStr += category.cName;
	}else{
		htmlStr += "<li id='second-"+category.cid+"' onclick='javascript:selectCategoryByPid("+category.cid+", "+index+");'>";
		htmlStr += category.cName + "<b class='pull-right'>></b>";
	}
	
	htmlStr += "</li>";
	return htmlStr;
}

/*
 * 加载属性页面
 */
function loadAttrPage(cid, cname){
	//alert("选择的类目是 "+cname);
	$("#item-cid").val(cid);
	var url = "back/category/selectAttrAndValue";
	var params = {"cid": cid};
	$("#attr-page").load(url, params, function(){
		console.log("加载属性页面完成");
	});
}

/*
 * 给类目绑定点击事件，选择后把当前选择的突出显示
 */
function bindClickEven(index){
	if(index==1){
		$(".item-category ul#first-category").on("click", "li", function(){
			console.log("click first category");
			$(".item-category ul#first-category li").removeClass("current");
			$(this).addClass("current");
			$("#current-select-category #first").text($(this).text().replace(">", ""));
			$("#current-select-category #second").text("");
			$("#current-select-category #third").text("");
		});
	}else if(index==2){
		$(".item-category ul#second-category").on("click", "li", function(){
			console.log("click second category");
			$(".item-category ul#second-category li").removeClass("current");
			$(this).addClass("current");
			$("#current-select-category #second").text("   >   "+$(this).text().replace(">", ""));
			$("#current-select-category #third").text("");
		});
	}else if(index==3){
		$(".item-category ul#third-category").on("click", "li", function(){
			console.log("click third category");
			$(".item-category ul#third-category li").removeClass("current");
			$(this).addClass("current");
			$("#current-select-category #third").text("   >   "+$(this).text().replace(">", ""));
		});
	}else{
		console.log("设置当前选中异常");
	}
}
bindClickEven(1);//给一级类目绑定点击事件，选择后把当前选择的突出显示

/*
 * 保存内容提交
 */
$("#save-submit-btn").click(function(){
	$("#save-form").submit();
});
/*
 * 保存内容
 */
function saveFun(){
	var url = null;
	var id = $("#item-id").val();
	if(id==null || id==""){
		url = "back/item/insert";
	}else{
		url = "back/item/updateById";
	}
	
	var createTimeStr = $("#create-time-str").val();
	var createtime = null;
	if(createTimeStr==null || createTimeStr==""){
		createtime = new Date();
	}else{
		createtime = parserDate(createTimeStr);
	}
	console.log("创建时间（毫秒）："+createtime.getTime());
	var params = new Object();
	params.createstr = createtime.getTime();
	params.describeUrl = getContent("item-ueditor");
	try{
		params.attributes = getItemAttr().toString();
		params.attrSale = getSaleAttr().toString();
	}catch(err){
		params.attributes = "";
		params.attrSale = "";
		console.log(err);
	}
	
	
	//util.loading();
	$("#save-form").ajaxSubmit({
		type:"post",
		url:url,
		data:params,
		success : function(res) {
			console.log(res);
			if(res!=null){
				var obj = $.parseJSON(res);
				if(obj.result_code=="success"){
					//操作成功后重新加载当前菜单内容
					reloadInfoFun();
				}else{
					util.message(obj.result_err_msg);
				}
			}
		},
	});
}

/*
 * 删除信息AJAX请求（物理删除）
 */
function deleteInfoAjaxRequest(id){
	var url = "back/item/deleteById";
	var params = {"id":id};
	//util.loading();
	$.post(url, params, function(res){
		console.log(res);
		if(res!=null && res!=""){
			var obj = $.parseJSON(res);
			if(obj.result_code=="success"){
				reloadInfoFun();
			}else{
				util.message(obj.result_err_msg);
			}
		}
		
	});
}

/*
 * 单个删除
 */
function deleteInfoFun(id){
	util.delConfirm("确认删除？", id, "deleteInfoAjaxRequest");
}

/*
 * 重新加载当前菜单内容
 */
function reloadInfoFun(){
	//操作成功后重新加载
	var href = "back/item/selectItems?pagehelperFun=clickPageBtnRequestFun";
	parent.window.iframeLoading(href);//调用主页面中的在iframe中加载内容的方法
}

/*
 * 点击页面中的页码执行此函数
 * 		函数功能：根据页码数请求当前页内容
 */
function clickPageBtnRequestFun(params){
	var action = "back/item/selectItems";
	params.clickPageBtn = true;
	//util.loading();
	$("#item-div").load(action, params, function(){
		
	});
}

/*
 * 点击列表中某个复选框时，全选或反选
 */
function checkOne(){
    
    var flag = true;
    $("#product-table tbody input[type='checkbox']").each(function(){
    	if(!$(this).prop("checked")){
    		flag = false;
    	}
    });
    if(flag){
    	$("#product-table thead input[type='checkbox']").prop('checked', true);
    }else{
    	$("#product-table thead input[type='checkbox']").prop('checked', false);
    }
}
/*
 * 点击列表中All复选框时，列表全选或反选
 */
function checkAll(obj){
	$("#product-table tbody input[type='checkbox']").prop('checked', $(obj).prop('checked'));
}

/*
 * 重置form表单
 */
function resetFun(){
	$("#save-form").data('bootstrapValidator').destroy();//销毁bootstrapValidator验证
	bootstrapValidateFun();//启用验证
	$('#save-form')[0].reset();
}

/*
 * 日期字符串转Date
 */
var parserDate = function (dateStr) {  
    var t = Date.parse(dateStr);  
    if (!isNaN(t)) {  
        return new Date(Date.parse(dateStr.replace(/-/g, "/")));  
    } else {  
        return new Date();  
    }  
}

/*
 * 日期转字符串格式函数
 * 调用方法：date.format('yyyy-MM-dd HH:mm:ss');
 */
Date.prototype.format = function(format) {
    var date = {
           "M+": this.getMonth() + 1,
           "d+": this.getDate(),
           "H+": this.getHours(),
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