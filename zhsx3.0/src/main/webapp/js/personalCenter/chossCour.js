
//确定选择的课件
function confirm(courId){
	$.ajax({
	   type: "POST",
	   url: ctx+"/personalCenter/insterAndUpdate",
	   data: {courId:courId,pjInfoId:pjInfoId,zId:zId},
	   success: function(msg){
		   parent.location.reload(); 
	   }
	});
}

/**
 * 跳转到上传课件页面
 */
function gotoupload(){
	var iframe = $(parent.parent.document.getElementById('parentIframe'));
	iframe.attr("src",ctx + "/adminCourRes/courRes");
	parent.layer.closeAll();
	parent.parent.layer.closeAll();
}