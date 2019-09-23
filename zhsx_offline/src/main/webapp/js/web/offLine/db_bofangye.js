$(function(){
	if(videoType == "P"){
		$("#zbTop").attr("class","has-sub active");
		$("#dianboTopp").attr("class","active2");
	}else {
		$("#ziyuanTop").attr("class","has-sub active");
		if(videoType == "B"){
			$("#dianboTopB").attr("class","active2");
		}else if(videoType == "S"){
			$("#dianboTopS").attr("class","active2");
		}else if(videoType == "H"){
			$("#dianboTopH").attr("class","active2");
		}
	}
	
	if(videoType === 'P' && userType != 'S'){
		getpgPagetData();
	}
	if(videoType != 'P' || userType === 'S'){
		$(".tap11").css("display","none");
		$(".tap22").css("display","none");
		$(".tap33").css("display","block");
	}
	getCommentData(1);
});

//视频下方 信息框切换
$(".ddd li").click(function(){
	if(videoType == "P"){
		$(".ddd li").attr("class","kkk");
		$(this).attr("class","pitchOn");
		if ($(this).index() === 0) {
			$(".tap11").css("display","block");
			$(".tap22").css("display","none");
			$(".tap33").css("display","none");
		}
		if ($(this).index() === 1) {
			$(".tap11").css("display","none");
			$(".tap22").css("display","block");
			$(".tap33").css("display","none");
			insterwebpj();
		}
		if ($(this).index() === 2) {
			$(".tap11").css("display","none");
			$(".tap22").css("display","none");
			$(".tap33").css("display","block");
		}
	}
});

var layer;
layui.use(['layer', 'element'], function() {
	var element = layui.element();
	layer = layui.layer;

});

//轮播
var viewnum = $("#hotListsLength").val();
var isLoop = false;
if(viewnum>4){
	isLoop = true;
}
var swiper = new Swiper('.swiper-container', {
	slidesPerView: 4,
	paginationClickable: false,
	spaceBetween: 1,
	direction: 'vertical',
	autoplay: 2000,
	loop: isLoop
});
$(".swiper-container").mouseenter(function () {
    swiper.stopAutoplay()
}).mouseleave(function () {
    swiper.startAutoplay()
});



//获得评论内容
function getCommentData(p){
	$.ajax({
	   type: "POST",
	   url: ctx +"/timelyReview/timelyWindow",//ctx + "/webComments/comment",
	   data: {contextType:'V',contextId:receive.id,p:p},
	   success: function(msg){
	     $(".tap33").html(msg);
	   }
	});
}


//获得质量评估项
function getpgPagetData(){
	$.ajax({
	   type: "POST",
	   url: ctx + "/pgInfo/getPjInfo",
	   data: {type:'I',pgId:receive.id,onOff:'OFF'},
	   success: function(msg){
	     $(".tap11").html(msg);
	   }
	});
}


//刷新页面
function refreshPage(data){
	layer.msg(data,{time:1500},function(){
		getpgPagetData();
	});
}


//查看结果
function viewResults(){
	$.ajax({
	   type: "POST",
	   url: ctx + "/pgInfo/viewResults",
	   data: {type:'I',pgId:receive.id,onOff:'OFF'},
	   success: function(msg){
	     $(".tap22").html(msg);
	   }
	});
}

//收藏视频
function addCollect(){
	$.ajax({
	   type: "POST",
	   url: ctx+"/sysHistory/insterSysHistory",
	   data: {pubType:"V",pubFlag:"C",pubId:receive.id},
	   success: function(msg){
		   $(".collectImg").html('<img  src="'+ctx+'/img/collect2.png" onclick="cancelCollect();">');
	   }
	});
}


//取消收藏
function cancelCollect(){
	$.ajax({
	   type: "POST",
	   url: ctx+"/sysHistory/delSysHistory",
	   data: {pubType:"V",pubFlag:"C",pubId:receive.id},
	   success: function(msg){
		  $(".collectImg").html('<img  src="'+ctx+'/img/collect1.png" onclick="addCollect();">');
	   }
	});
}



function downloadRes(id,filename){
	$.ajax({
	   type: "POST",
	   url: ctx + "/offLine/downloadVideoResources",
	   data: {id:id},
	   success: function(msg){
		   location.href = fileWebPath+"/VideoFileDownloadServlet?filepath="+msg+"&filename="+filename;
	   }
	});
}
