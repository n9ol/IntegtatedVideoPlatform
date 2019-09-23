
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