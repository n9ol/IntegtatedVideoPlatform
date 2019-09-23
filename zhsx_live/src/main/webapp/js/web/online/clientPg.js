$(function(){
	$("#zbTop").attr("class","has-sub active");
	if(userType === 'S'){
		$(".tap00").css("display","none");
		$(".tap11").css("display","none");
		$(".tap22").css("display","none");
		$(".tap33").css("display","block");
		$(".tap44").css("display","none");
	}else{
		getKeQianPg();
		getpgPagetData();
	}
	if(duke==='D'){
		getPjInfoDuke();
	}
	
	getCommentData(1);
});

var layer;
layui.use(['layer', 'element'], function() {
	 layer = layui.layer;
	 var element = layui.element;
});



//获得评论内容
function getCommentData(p){
	$.ajax({
	   type: "POST",
	   url: ctx + "/webComments/comment",
	   data: {contextType:'L',contextId:receive.id,p:p},
	   success: function(msg){
	     $(".tap33").html(msg);
	   }
	});
}



//点击切换
$(".uuu li").click(function(){
	if ($(this).index() === 0) {
		$(".tap00").css("display","block");
		$(".tap11").css("display","none");
		$(".tap22").css("display","none");
		$(".tap33").css("display","none");
		$(".tap44").css("display","none");
	}
	if ($(this).index() === 1) {
		$(".tap00").css("display","none");
		$(".tap11").css("display","block");
		$(".tap22").css("display","none");
		$(".tap33").css("display","none");
		$(".tap44").css("display","none");
	}
	if ($(this).index() === 2) {
		$(".tap00").css("display","none");
		$(".tap11").css("display","none");
		$(".tap22").css("display","block");
		$(".tap33").css("display","none");
		$(".tap44").css("display","none");
		insterwebpj();
	}
	if ($(this).index() === 3) {
		$(".tap00").css("display","none");
		$(".tap11").css("display","none");
		$(".tap22").css("display","none");
		$(".tap33").css("display","block");
		$(".tap44").css("display","none");
	}
	
	if ($(this).index() === 4) {
		$(".tap00").css("display","none");
		$(".tap11").css("display","none");
		$(".tap22").css("display","none");
		$(".tap33").css("display","none");
		$(".tap44").css("display","block");
	}
});

//获得质量评估项
function getpgPagetData(){
	$.ajax({
	   type: "POST",
	   url: ctx + "/pgInfo/getPjInfo",
	   data: {type:'I',pgId:receive.id,onOff:'ON'},
	   success: function(msg){
	     $(".tap11").html(msg);
	   }
	});
}

//评估细则-提交刷新页面
function refreshPage(data){
	layer.msg(data,{time:1500},function(){
		getpgPagetData();
	});
}


//获得质量评估项-课后督课
function getPjInfoDuke(){
	$.ajax({
	   type: "POST",
	   url: ctx + "/pgInfo/getPjInfoDuke",
	   data: {type:'I',pgId:receive.id,onOff:'ON'},
	   success: function(msg){
	     $(".tap44").html(msg);
	   }
	});
}


//查看结果
function viewResults(){
	$.ajax({
	   type: "POST",
	   url: ctx + "/pgInfo/viewResults",
	   data: {type:'I',pgId:receive.id,onOff:'ON'},
	   success: function(msg){
	     $(".tap22").html(msg);
	     getzidongpg();
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
	   }
	});
}


//获得课前质量评估
function getKeQianPg(){
	$.ajax({
	   type: "POST",
	   url: ctx + "/pgInfo/getKeQianPg",
	   data: {type:'F',pgId:receive.id,onOff:'ON'},
	   success: function(msg){
	     $(".tap00").html(msg);
	   }
	});
}