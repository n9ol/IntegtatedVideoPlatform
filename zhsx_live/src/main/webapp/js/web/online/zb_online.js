$(function(){
	if(type == "G"){
		$("#dianboTop").attr("class","has-sub active");
		$("#zbTopG").attr("class","active2");
	}else if(type == "A"){
		$("#zbTop").attr("class","has-sub active");
		$("#zbTopA").attr("class","active2");
	}else if(type == "Z"){
		$("#zbTopZ").attr("class","active2");
	}
	getZbMessage();
});	


//选择年级事件
function selectGrade(gradeId,gradeName){
	$("input[name='subjectId']").val(null);
	$("input[name='gradeId']").val(gradeName);
	agradeSubject(gradeId);
	getZbMessage();
}

//选择科目事件
function selectSubjects(subjectId,src){
	$("input[name='subjectId']").val(subjectId);
	getZbMessage();
}


//选择直播状态事件
function timeSorting(state,src){
	$("input[name='timeSorting']").val(state);
	getZbMessage();
}


//搜索框键盘enter事件
$("#searchval").keyup(function(){
	if(event.keyCode == 13){
		search();
	} 
});
function search(){
	var search=$("#searchval").val();
	$("input[name='search']").val(search);
	getZbMessage();
}


//获得直播数据
function getZbMessage(){
	$.ajax({
		   type: "POST",
		   url: ctx+"/online/getZbMessage",
		   data: $("#myform").serialize(),
		   success: function(msg){
		   		$(".news").html(msg);
		   }
		});			
}


//获得科目
function agradeSubject(id){
	$(".hhh").empty();
	$(".hhh").append("<li class='shai_kuang' onclick=selectSubjects('',this)>全部</li>");
	$.getJSON(ctx+"/adminBaseData/getSubjects", { gradeId: id}, function(json){
		for (var int = 0; int < json.length; int++) {
			var arrayJson=json[int];
			$(".hhh").append("<li onclick=selectSubjects('"+arrayJson.value+"',this)>"+arrayJson.value+"</li>");
		} 
	});
}


//进入播放页
function enterPlay(type,id,isGoClass){
	if(isGoClass === "1"){
		switch (type) {
		case 'A':
			window.open(ctx+"/online/qualityPlay?id="+id,"_blank");
			break;
		case 'G':
			window.open(ctx+"/online/inManyPlay?id="+id,"_blank");
			break;
		case 'Z':
			window.open(ctx+"/online/livePlay?id="+id,"_blank");
			break;
		}
	}else if(isGoClass === "2"){
		layer.msg("直播尚未开始!");
	}
}


//地区搜索事件
function updateData(Cityname){
	$("input[name='p']").val(1);
	$("input[name='area']").val(Cityname);
	getZbMessage();
}


//取消地区筛选
$(".quxiao").click(function(){
	$(".area-danxuan").text("选择地区");
	$("input[name='p']").val(1);
	$("input[name='area']").val(null);
	getZbMessage();
});


