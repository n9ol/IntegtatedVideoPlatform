$(function() {
	$("#shouyeTop").attr("class", "has-sub active");
});

var layer;
layui.use([ 'layer' ], function() {
	layer = layui.layer;
});

// 图片轮播
$('.flexslider').flexslider({
	directionNav : true,
	pauseOnAction : false,
	slideshowSpeed : 3000,
	animation : "slide"
});

// 观看直播视频
function watchZVideo(id, type, isGoClass) {
	if (isGoClass === "1") {
		switch (type) {
		case 'A':
			window.open(ctx + "/online/qualityPlay?id=" + id, "_blank");
			break;
		case 'G':
			window.open(ctx + "/online/inManyPlay?id=" + id, "_blank");
			break;
		case 'Z':
			window.open(ctx + "/online/livePlay?id=" + id, "_blank");
			break;
		}
	} else if (isGoClass === "2") {
		layer.msg("直播尚未开始!");
	}
}

// 观看离线视频
function watchofflineVideo(id, type) {
	window.open(ctx + "/offLine/videoPlayback?id=" + id + "&type=" + type,
			"_blank");
}

// 名师团队-轮播图
// var swiper = new Swiper('.swiper-container', {
// pagination: '.swiper-pagination',
// paginationClickable: true,
// spaceBetween: 30,
// centeredSlides: true,
// autoplay: 3500,
// autoplayDisableOnInteraction: false,
// loop: true
// });

// 质量评估和一校带多校的切换
$(".pc a").click(function() {
	$(".pc a").css({
		"color" : "black",
		"backgroundColor" : "#fff"
	});
	$(this).css({
		"color" : "#fff",
		"backgroundColor" : "#4596e5"
	})
})

// 名师团队-轮播图js
var _now = 0;
var oul = $(".myslidetwo");
var numl = $(".label li");
var wid = 290;

// 数字图标实现
numl.click(function() {
	var index = $(this).index();
	$(this).addClass("current").siblings().removeClass();
	oul.animate({
		'left' : -wid * index
	}, 500);
});

// 左右箭头轮播
$(".pre").click(function() {
	if (_now >= 1) {
		_now--;
	} else {
		_now = parseInt(teachersize) - 1;
	}
	ani();
});

$(".next").click(function() {
	if (_now == numl.size() - 1) {
		_now = 0;
	} else
		_now++;
	ani();
});

// 动画函数
function ani() {
	// numl.eq(_now).addClass("current").siblings().removeClass();
	// oul.animate({'left': -wid * _now}, 500);
}
// 以下代码如果不需要自动轮播可删除
// 自动动画
var _interval = setInterval(showTime, 2000);

function showTime() {
	if (_now == numl.size() - 1) {
		_now = 0;
	} else
		_now++;
	ani();
}

// 鼠标停留在画面时停止自动动画，离开恢复
$(".main").mouseover(function() {
	clearTimeout(_interval);
});
$(".main").mouseout(function() {
	_interval = setInterval(showTime, 2000);
});

$(".ul li a").click(function() {
	$(".ul li a").removeClass("borders");
	$(this).addClass("borders");
});

// 新闻中心点击事件
$('.bang_left ul li').click(function() {
	var id = $(this).attr('value');
	window.location.href = ctx + "/new/findOne?id=" + id;
});

// 课件资源点击事件
$(".bang_middle ul li").click(function() {
	var id = $(this).attr('value');
	window.location.href = ctx + "/courRes/watchCour?id=" + id;
});

// 在线答疑点击事件
$('.bnag_right ul li').click(function() {
	var id = $(this).attr('value');
	window.location.href = ctx + "/zxdy/getById?id=" + id;
});