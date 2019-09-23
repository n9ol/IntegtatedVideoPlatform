$(function(){
	$("#ziyuanTop").attr("class","has-sub active");
	$("#ziyuanTopC").attr("class","active2");
	getCommentData(1);
});


var t1 = window.setTimeout(setbackgroundColor,800);
function setbackgroundColor(){
	var filePlayPage = document.getElementById('filePlay').contentWindow.document.getElementById('toolbarViewer');
	var dropdownBox = document.getElementById('filePlay').contentWindow.document.getElementById('scaleSelect');
	switch (skinName) {
		case "springtime":
			filePlayPage.style.backgroundColor="#1cc8d2";
			$(dropdownBox).find("option").css("background-color","#1cc8d2");
			break;
		case "summer":
			filePlayPage.style.backgroundColor="#ee336e";
			$(dropdownBox).find("option").css("background-color","#ee336e");
			break;
		case "autumn":
			filePlayPage.style.backgroundColor="#ff9900";
			$(dropdownBox).find("option").css("background-color","#ff9900");
			break;
		case "winter":
			filePlayPage.style.backgroundColor="#1ca5d2";
			$(dropdownBox).find("option").css("background-color","#1ca5d2");
			break;
		default:
			filePlayPage.style.backgroundColor="#4596E5";
			$(dropdownBox).find("option").css("background-color","#4596E5");
			break;
	}
}
//获得评论内容
function getCommentData(p){
	$.ajax({
	   type: "POST",
	   url: ctx + "/webComments/comment",
	   data: {contextType:'C',contextId:receive.id,p:p},
	   success: function(msg){
	     $(".sanCA").html(msg);
	   }
	});
}