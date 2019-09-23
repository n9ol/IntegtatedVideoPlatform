$(function(){
	if(rapidTAG == 'W'){
		//样式渲染
		$(".biaoshi li").removeClass("qing");
		$("#watchRecord").addClass("qing");
		
		$(".zhong_right iframe").attr("src", ctx+"/personalCenter/geren_guankanjilu");
		
	}else if(rapidTAG == 'E'){
		$(".zhong_right iframe").attr("src", ctx+"/personalCenterUser/editHeadPhoto");
	}else if(rapidTAG == 'P'){
		//样式渲染
		$(".biaoshi li").removeClass("qing");
		$("#modifyPassword").addClass("qing");
		
		$(".zhong_right iframe").attr("src", ctx+"/personalCenterUser/modifyPassword");
	}
	
});

//左侧菜单 点击事件
$(".biaoshi li").click(function() {
	//样式渲染
	$(".biaoshi li").removeClass("qing");
	
	var classname = $(this).attr("class");
	if(classname != "kaopingCenter" && classname != "geren_shoucang" && classname !="myPingGu"){
		$(this).siblings("div").slideUp();
	}else{
		$(this).next("div").slideToggle().siblings("div").slideUp();
	}
	$(this).addClass("qing")
	
	
	//iframe 路径替换
	var data_url = $(this).attr("data_url");
	$(".zhong_right iframe").attr("src", data_url);
	
});


//左侧菜单——考评中心点击事件
$(".kaopingCenter").click(function() {
	
	$(".kaoping").addClass("sonColor")
	$(".kaoping div").click(function() {
		$(".kaoping div").removeClass("xianshi");
		$(".kaoping div").eq($(this).index()).addClass("xianshi");
		var data_url = $(this).attr("data_url");
		$(".zhong_right iframe").attr("src", data_url);
	});

});

//左侧菜单——我的收藏点击事件
$(".geren_shoucang").click(function() {
	
	$(".shoucang").addClass("sonColor")
	$(".shoucang div").click(function() {
		$(".shoucang div").removeClass("xianshi");
		$(".shoucang div").eq($(this).index()).addClass("xianshi");
		var data_url = $(this).attr("data_url");
		$(".zhong_right iframe").attr("src", data_url);
	});

});


//左侧菜单——我的评估
$(".myPingGu").click(function() {
	
	$(".pingGu").addClass("sonColor");
	$(".pingGu").show();
	$(".pingGu div").click(function() {
		$(".pingGu div").removeClass("xianshi");
		$(".pingGu div").eq($(this).index()).addClass("xianshi")
		var data_url = $(this).attr("data_url");
		$(".zhong_right iframe").attr("src", data_url);
	});

});


//进入编辑头像页面
function editHeadPhoto(){
	$(".zhong_right iframe").attr("src", ctx+"/personalCenterUser/editHeadPhoto");
}







