var zongIndex = 10;
	var index = 1
	for (var i = 1; i <= zongIndex; i++) {

		$(".tiji").append('<li><p class="paiming">' + index + '</p><span class="timu1">“浅谈用音乐塑造幼和沟通和法国恢复规划”</span><span><span style="color: orangered;">61人</span>评价</span></li>');

		index++;
	}
	$(".tiji li").click(function() {
		window.location.href = "onlineDay_xingqing.html";
	});
	$(".tiwenhuanjie img").click(function() {
		window.location.href = "onlineDayi_tiwen.html";
	});