$(function(){
	if(isPgAuthority == "Y"){
		$("#zbTop").attr("class","has-sub active");
		$("#zbTopA").attr("class","active2");
	}else{
		$("#dianboTop").attr("class","has-sub active");
		$("#zbTopG").attr("class","active2");
	}
	
	if(userType === 'S'){
		$(".tap00").css("display","none");
		$(".tap11").css("display","none");
		$(".tap22").css("display","none");
		$(".tap33").css("display","block");
	}else{
		getKeQianPg();
		getpgPagetData();
	}
	getCommentData(1);
});

var layer;
layui.use(['layer', 'element'], function() {
	 layer = layui.layer;
	 var element = layui.element();
});

var isLoop = false;
var RCListsLength = $("#RCListsLength").val();
if(RCListsLength>4){
	isLoop = true;
}

var swiper = new Swiper('.swiper-container', {
	slidesPerView: 4,
	paginationClickable: true,
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
	   url:ctx +"/timelyReview/timelyWindow",// ctx + "/webComments/comment",
	   data: {contextType:'L',contextId:receive.id,p:p},
	   success: function(msg){
	     $(".tap33").html(msg);
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
		}
	}
	if(isGoClass === "2"){
		layer.msg("直播尚未开始!");
	}
}


//点击切换
$(".uuu li").click(function(){
	var liNum = $(".uuu li").size();
	if(liNum<4){return;}
	
	if ($(this).index() === 0) {
		$(".tap00").css("display","block");
		$(".tap11").css("display","none");
		$(".tap22").css("display","none");
		$(".tap33").css("display","none");
	}
	if ($(this).index() === 1) {
		$(".tap00").css("display","none");
		$(".tap11").css("display","block");
		$(".tap22").css("display","none");
		$(".tap33").css("display","none");
	}
	if ($(this).index() === 2) {
		$(".tap00").css("display","none");
		$(".tap11").css("display","none");
		$(".tap22").css("display","block");
		$(".tap33").css("display","none");
		insterwebpj();
	}
	if ($(this).index() === 3) {
		$(".tap00").css("display","none");
		$(".tap11").css("display","none");
		$(".tap22").css("display","none");
		$(".tap33").css("display","block");
		var div = document.getElementById('baiban');
		div.scrollTop = div.scrollHeight;
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