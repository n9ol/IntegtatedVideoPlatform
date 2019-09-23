$(".mstd_tap .imgss").mouseover(function() {
	if ($(this).attr("src") == ctx+"/img/pagers" + ($(this).index() + 1) + ".png") {
		return false
	} else {
		$(this).attr("src", ctx+"/img/pagers" + ($(this).index() + 1) + ".png");
		$(".mstd_tap .imgss").mouseout(function() {
			$(this).attr("src",ctx+"/img/pager" + ($(this).index() + 1) + ".png");
		});
	}

})
$(".mstd_tap .imgss").click(function() {
	$(".imgss").eq(0).attr("src", ctx+"/img/pager1.png");
	$(".imgss").eq(1).attr("src", ctx+"/img/pager2.png");
	$(".imgss").eq(2).attr("src", ctx+"/img/pager3.png");
	$(".imgss").eq(3).attr("src", ctx+"/img/pager4.png");
	$(this).attr("src", ctx+"/img/pagers" + ($(this).index() + 1) + ".png");
	$(".mstd_tap .imgss").mouseout(function() {
		$(this).attr("src", ctx+"/img/pagers" + ($(this).index() + 1) + ".png");
	});
});
$(".imgss").click(function() {
	$(".xian").removeClass("borderss");
	$(".ppp").removeClass("colorsss");
	$(".sss").removeClass("colorsss");
	$(".xian").eq($(this).index()).addClass("borderss");
	$(".ppp").eq($(this).index()).addClass("colorsss");
	$(".sss").eq($(this).index()).addClass("colorsss");
	$(".xiangqing").css("display", "none");
	$(".xiangqing").eq($(this).index()).css("display", "block");
});
var myChart1 = echarts.init(document.getElementById("main1"));
var myChart2 = echarts.init(document.getElementById("main2"));
var myChart3 = echarts.init(document.getElementById("main3"));

//var option1= eval('(' + afterSchoolDiscipline + ')');
//myChart1.setOption(option1);


var option2 = eval('(' + deveopmentSyetem + ')');
myChart2.setOption(option2);


var option3 =eval('(' + growthCurve + ')');
myChart3.setOption(option3);

//轮播图js

var _now = 0;
var oul = $(".myslidetwo");
var numl = $(".label li");
var wid = 290;
//数字图标实现
numl.click(function() {
		var index = $(this).index();
		$(this).addClass("current").siblings().removeClass();
		oul.animate({
			'left': -wid * index
		}, 500);
	})
	//左右箭头轮播
$(".pre").click(function() {
	if (_now >= 1) {
		_now--;
	}else{
		_now = parseInt(relatedVideosize)-1;
	} 
	ani();
});
$(".next").click(function() {
	if (_now == numl.size() - 1) {
		_now = 0;
	} else _now++;
	ani();
});
//动画函数
function ani() {
	numl.eq(_now).addClass("current").siblings().removeClass();
	oul.animate({
		'left': -wid * _now
	}, 500);
}
//以下代码如果不需要自动轮播可删除
//自动动画
var _interval = setInterval(showTime, 2000);

function showTime() {
	if (_now == numl.size() - 1) {
		_now = 0;
	} else _now++;
	ani();
}
//鼠标停留在画面时停止自动动画，离开恢复
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

//$(".biaoti li").eq(0).click(function() {
//	window.location.href = "mstd_people5.html"
//});
//$(".biaoti li").eq(1).click(function() {
//	window.location.href = "mstd_people.html"
//});
//$(".biaoti li").eq(2).click(function() {
//	window.location.href = "mstd_people8.html"
//});
//$(".biaoti li").eq(3).click(function() {
//	window.location.href = "mstd_people4.html"
//});
//$(".biaoti li").eq(4).click(function() {
//	window.location.href = "mstd_people11.html"
//});
//$(".biaoti li").eq(5).click(function() {
//	window.location.href = "mstd_people3.html"
//});