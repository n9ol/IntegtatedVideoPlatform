$(function(){
	getPgResultData();
});


function setiframeheight(){
	var body = $(document.body);
	var iframe = $(parent.document.getElementById('parentIframesjzx'));
	iframe.height(body.height()+10);
}


function getPgResultData(){
	$.ajax({
	   type: "POST",
	   url: ctx+"/pgResult/sjzx_evaluationResultData",
	   data: $("#myform").serialize(),
	   success: function(msg){
	     $(".ziyuan_zhong").html(msg);
	     setiframeheight();
	   }
	});
}


function selectGrade(id,value){
	$("input[name='gradeId']").val(value);
	getPgResultData();
}


function selectSubjects(value,src){
	$("input[name='subjectId']").val(value);
	getPgResultData();
}


//搜索框键盘enter事件
$("#seatchval").keyup(function(){
	if(event.keyCode == 13){
		search();
	} 
});
function search(){
	var search = $("#seatchval").val();
	$("input[name='search']").val(search);
	$("input[name='p']").val(null);
	getPgResultData();
}


function lookPgResult(id){
	var p = $("input[name='p']").val();
	var gradeId = $("input[name='gradeId']").val();
	var subjectId = $("input[name='subjectId']").val();
	var search = $("input[name='search']").val();
	
	location.href= ctx+"/pgResult/PgResult?id="+id+"&p="+p+"&gradeId="+gradeId+"&subjectId="+subjectId+"&search="+search;
}