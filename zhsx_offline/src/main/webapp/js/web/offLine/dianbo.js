$(function(){
	switch (type) {
	case 'P':
		$("#zbTop").attr("class","has-sub active");
		$("#dianboTopp").attr("class","active2");
		break;
	case 'B':
		$("#ziyuanTop").attr("class","has-sub active");
		$("#dianboTopB").attr("class","active2");
		break;
	case 'S':
		$("#ziyuanTop").attr("class","has-sub active");
		$("#dianboTopS").attr("class","active2");
		break;
	case 'H':
		$("#ziyuanTop").attr("class","has-sub active");
		$("#dianboTopH").attr("class","active2");
		break;
	}
	getdianboData();
});

$(".sa1 span a").click(function() {
	$(".sa1 span a").removeClass("duos");
	$(this).addClass("duos");
});

//获得点播视频数据
function getdianboData(){
	$.ajax({
	   type: "POST",
	   url: ctx+"/offLine/dianboData",
	   data: $("#myForm").serialize(),
	   success: function(msg){
	     $(".news").html(msg);
	   }
	});
}

//年级点击事件
function choosegrade(gradeName){
	$("input[name='p']").val(1);
	$("#gradeName").val(gradeName);
	getdianboData();
}

//科目点击事件
function choosesubjects(subjectName){
	$("input[name='p']").val(1);
	$("#subjectName").val(subjectName);
	getdianboData();
}

//搜索框键盘enter事件
$("#searchval").keyup(function(){
	if(event.keyCode == 13){
		sousuo();
	} 
});


//搜索事件
function sousuo(){
	var search = $("#searchval").val();
	$("input[name='p']").val(1);
	$("#search").val(search);
	getdianboData();
}

//进入视频播放页
function videoPlayback(id){
	var type = $("input[name='type']").val();
	location.href = ctx+"/offLine/videoPlayback?id="+id+"&type="+type;
}


//地区搜索事件
function updateData(Cityname){
	$("input[name='p']").val(1);
	$("input[name='area']").val(Cityname);
	getdianboData();
}


//取消地区筛选
$(".quxiao").click(function(){
	$(".area-danxuan").text("选择地区");
	$("input[name='p']").val(1);
	$("input[name='area']").val(null);
	getdianboData();
});


