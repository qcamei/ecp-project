/**
 * 
 * @authors Wang
 * @date    2017-6-16
 * @version 
 **/

$(function(){
	//购物车
	$("#myCart").hover(function() {
    	$(".dropdownCart").stop(true, true).slideDown();
	}, function() {
    	$(".dropdownCart").stop(true, true).delay(200).slideUp();
	});

	//分类
	$("#sort .all-sort").hover(function() {
    	$("#classification").slideDown();
	}, function() {
    	$("#classification").stop(true, true).slideUp();
    	$(".item-sub").hide();
	});

	$(".firstClass").each(function(index){
		$(this).hover(function(){
			$(".item-sub").hide();
			$("#details").show();
			$("#classList"+index).show();
		},function(){
			
		});
	});

	$("#classList1").hover(function(){
		$("#detail").show();
		$("#classList1").show();
	},function(){
		$("#classList1").hide();
	});

	
	$(".footerContent .row>div").each(function(){$(this).height($(".footerContent .row").height())});

});


