$(".anzhuo").click(function() {
	$("html,body").animate({
		scrollTop : 0
	}, 500)
})

// 一级筛选
$(".ddd li").click(function() {
	$(".ddd li").removeClass("shai_kuang");
	$(this).addClass("shai_kuang");
})
// 二级筛选
$(".eee li").click(function() {
	$(".eee li").removeClass("shai_kuang");
	$(this).addClass("shai_kuang");
})
// 三级筛选
$(".hhh li").click(function() {
	$(".hhh li").removeClass("shai_kuang");
	$(this).addClass("shai_kuang");
});
// 四级筛选
$(".lll li").click(function() {
	$(".lll li").removeClass("shai_kuang");
	$(this).addClass("shai_kuang");
})
// 五级筛选
$(".ppp li").click(function() {
	$(".ppp li").removeClass("shai_kuang");
	$(this).addClass("shai_kuang");
});


// 头部二维码的显示和隐藏
$(".ewmLogo").mouseover(function() {
	$(".erweima").fadeIn("slow");
});
$(".ewmLogo").mouseout(function() {
	$(".erweima").fadeOut("slow");
});

$('.logIn').mouseover(function() {
	$(this).children('.LIcon').show();
});
$('.logIn').mouseout(function() {
	$(this).children('.LIcon').hide();
});

// 筛选条件点击事件
$(function() {
	$(".filter3 li").click(function() {
		$(this).siblings("li").removeClass("filterActive");
		$(this).addClass("filterActive");
	});
	$(".unfolded").click(function() {
		$(this).parent().prev().attr("style", "height: auto");
		$(this).hide();
		$(this).siblings("button").show();
	})
	$(".putAway").click(function() {
		$(this).parent().prev().attr("style", "height: 47");
		$(this).hide();
		$(this).siblings("button").show();
	})
});

var layer;

layui.use(['element', 'layer'], function(){
	var element={}; 
	try {
		element = layui.element();
	} catch (e) {
		// TODO: handle exception
	}
	layer = layui.layer;
});

//筛选区域的    显示与隐藏
$(".text a").hover(function(){
	$(this).css("color","#4596e5")
},function(){
	$(this).css("color","#999")
}).click(function(){
	var aClass = $(this).attr("class");
	if(aClass=="isShowBtu"){
		$(".isShow").show();
		$(this).removeClass("isShowBtu");
		$(".filter1>li").eq(1).addClass("borderBottom");
	}else{
		$(".isShow").hide();
		$(this).addClass("isShowBtu");
		$(".filter1>li").eq(1).removeClass("borderBottom");
	}

});
