$(function(){
	$("#ziyuanTop").attr("class","has-sub active");
	getCommentData(1);
});


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