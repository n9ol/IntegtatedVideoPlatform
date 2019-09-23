$(function(){
	$("#ziyuanTop").attr("class","has-sub active");
	getziyuanData();
});

//得到资源
function getziyuanData(){
	$.ajax({
	   type: "POST",
	   url: ctx+"/courRes/getziyuanData",
	   data: $("#myform").serialize(),
	   success: function(msg){
	     $(".news").html(msg);
	   }
	});
}

//年级选择事件
$("#grade").find("li").click(function(){
	var gradeName = $(this).attr("value");
	$("input[name='p']").val(1);
	$("input[name='gradeName']").val(gradeName);
	getziyuanData();
});

//科目选择事件
$("#subject").find("li").click(function(){
	var subjectsName = $(this).attr("value");
	$("input[name='p']").val(1);
	$("input[name='subjectsName']").val(subjectsName);
	getziyuanData();
});

//版本选择事件
$("#version").find("li").click(function(){
	var version = $(this).attr("value");
	$("input[name='p']").val(1);
	$("input[name='bak']").val(version);
	getziyuanData();
});

//上下册选择事件
$("#bak1").find("li").click(function(){
	var bak1 = $(this).attr("value");
	$("input[name='p']").val(1);
	$("input[name='bak1']").val(bak1);
	getziyuanData();
});

//收藏资源
function collection(id){
	if(principal != ""){
		$.ajax({
		   type: "POST",
		   url: ctx+"/sysHistory/insterSysHistory",
		   data: {pubType:"R",pubFlag:"C",pubId:id},
		   success: function(msg){
			   getziyuanData();
			   layer.msg(msg);
		   }
		});
	}else{
		layer.msg("请先登录!");
	}
}

//取消收藏
function cancelCollection(id){
	if(principal != ""){
		$.ajax({
		   type: "POST",
		   url: ctx+"/sysHistory/delSysHistory",
		   data: {pubType:"R",pubFlag:"C",pubId:id},
		   success: function(msg){
			  getziyuanData();
			  layer.msg(msg);
		   }
		});
	}else{
		layer.msg("请先登录!");
	}
}

//下载资源
function downloadCourRes(id,src){
	if(principal != ""){
		$.ajax({
		   type: "POST",
		   url: ctx+"/sysHistory/insterSysHistory",
		   data: {pubType:"R",pubFlag:"D",pubId:id},
		   success: function(msg){
			   location.href = ctx+"/adminCourRes/downloadCourRes?id="+id;
			   var num = $(src).prev("span.xia_shu").text();
			   $(src).prev("span.xia_shu").text(num*1+1)
		   }
		});
	}else{
		layer.msg("请先登录!");
	}
}

//搜索框键盘enter事件
$("#seatchval").keyup(function(){
	if(event.keyCode == 13){
		search();
	} 
});

//搜索内容
function search(){
	var seatchval = $("#seatchval").val();
	$("input[name='p']").val(1);
	$("input[name='search']").val(seatchval);
	getziyuanData();
}

//地区搜索事件
function updateData(Cityname){
	$("input[name='p']").val(1);
	$("input[name='area']").val(Cityname);
	getziyuanData();
}

//取消地区筛选
$(".quxiao").click(function(){
	$(".area-danxuan").text("选择地区");
	$("input[name='p']").val(1);
	$("input[name='area']").val(null);
	getziyuanData();
});


