$(function(){
	$("#zbTop").attr("class","has-sub active");
//	if(duke==='D'){
//		getPjInfoDuke();
//	}
	viewResults();
});


function setiframeheight(){
	var body = $(document.body);
	var iframe = $(parent.document.getElementById('parentIframesjzx'));
	iframe.height(body.height()+100);
}



var layer;
layui.use(['layer', 'element'], function() {
	 layer = layui.layer;
	 var element = layui.element;
});


//点击切换
$(".uuu li").click(function(){
	if ($(this).index() === 0) {
		$(".tap22").css("display","block");
		$(".tap44").css("display","none");
	}
	if ($(this).index() === 1) {
		$(".tap22").css("display","none");
		$(".tap44").css("display","block");
	}
});


//获得质量评估项-课后督课
function getPjInfoDuke(){
	$.ajax({
	   type: "POST",
	   url: ctx + "/pgInfo/getPjInfoDuke",
	   data: {type:'I',pgId:receive.id,onOff:'ON'},
	   success: function(msg){
	     $(".tap44").html(msg);
	     setiframeheight();
	   }
	});
}


//查看结果
function viewResults(){
	$.ajax({
	   type: "POST",
	   url: ctx + "/pgInfo/viewResults",
	   data: {type:'I',pgId:receive.id,onOff:'ON',addTime:addTime},
	   success: function(msg){
	     $(".tap22").html(msg);
	     getzidongpg();
	     setiframeheight();
	   }
	});
}


//获得自动评估结果
function getzidongpg(){
	$.ajax({
	   type: "POST",
	   url: ctx + "/pgSelfMotion/zidongpg",
	   data: {pgId:receive.id},
	   success: function(msg){
	     $(".tap22").append(msg);
	     setiframeheight();
	   }
	});
}


function returns(){
	location.href=ctx+"/pgResult/sjzx_evaluationResult?p="+p+"&gradeId="+gradeId+"&subjectId="+subjectId+"&search="+search;
}