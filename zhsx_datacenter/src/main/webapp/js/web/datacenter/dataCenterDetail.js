$(function(){
	$("#datacenterTop").attr("class","has-sub active");
});


$(".tap_nei img").click(function() {
	$(".tap_nei img").eq(0).attr("src", ctx+"/skin/"+skinName+"/img/b0.png");
	$(".tap_nei img").eq(1).attr("src", ctx+"/skin/"+skinName+"/img/b1.png");
	$(".tap_nei img").eq(2).attr("src", ctx+"/skin/"+skinName+"/img/b2.png");
	$(".tap_nei img").eq(3).attr("src", ctx+"/skin/"+skinName+"/img/b3.png");
	$(this).attr("src", ctx+"/skin/"+skinName+"/img/a" + ($(this).index()) + ".png");
	
	
	$(".tap_zi p").removeClass('tap_zi_color');
	$(".tap_zi p").eq($(this).index()).addClass('tap_zi_color');
	
	
	if ($(this).index()==0) {
		$(".big_pic img").attr("src",ctx+"/img/gongxian.png");
		$(".mixing iframe").attr("src",ctx+"/pgResult/sjzx_evaluationResult")
	}else if ($(this).index()==1) {
		$(".big_pic img").attr("src",ctx+"/img/gongxian.png");
		$(".mixing iframe").attr("src",ctx+"/datacenter/teacheContributionList")
	}else if ($(this).index()==2) {
		$(".big_pic img").attr("src",ctx+"/img/paihang.png");
		$(".mixing iframe").attr("src",ctx+"/datacenter/teacherRankings")
	}else if ($(this).index()==3) {
		$(".big_pic img").attr("src",ctx+"/img/bit_pic.png")
		$(".mixing iframe").attr("src",ctx+"/datacenter/questionnaire")
	}
});