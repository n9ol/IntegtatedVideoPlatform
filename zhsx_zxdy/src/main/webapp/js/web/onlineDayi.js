
jQuery(function($) {
	$(".tiji li").click(function() {
		window.location.href = "onlineDay_xingqing.html";
	});

	$(".slide_con").click(function() {
		window.location.href = "onlineDay_xingqing.html";
	});
	//tap切换
	$(".xuan").click(function() {
		$(".xuan").css({
			"borderTop": "none",
			"color": "black"
		});
		$(this).css({
			"borderTop": "3px solid #4596e5",
			"color": "#4596e5"
		});
	})
});
