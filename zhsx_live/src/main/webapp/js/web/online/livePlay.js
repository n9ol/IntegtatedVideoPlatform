$(function(){
	$("#zbTop").attr("class","has-sub active");
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
	   url:ctx +"/timelyReview/timelyWindow",
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
		case 'Z':
			window.open(ctx+"/online/livePlay?id="+id,"_blank");
			break;
		}
	}else if(isGoClass === "2"){
		layer.msg("直播尚未开始!");
	}
}