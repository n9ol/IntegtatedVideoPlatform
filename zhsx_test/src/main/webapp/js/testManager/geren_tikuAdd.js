function setIframeH() {
	var body = $(document.body);
	var iframe = $(parent.document.getElementById('parentIframe'));
	iframe.height(body.height());
}


var layer;
layui.use([ 'layer', 'form'], function() {
	layer = layui.layer;
	var form = layui.form();
});



$(function(){
	setIframeH();
});


//表单验证
function chkForm() {
	var isok = true;
	
	
	var questionText = document.getElementById("questionText");
	if(!(checkOk(questionText,checkString(questionText)))){
		isok = false;
	}
	
	var questionType = document.getElementById("questionType");
	if(!(checkOk(questionType,checkString(questionType)))){
		isok = false;
	}
	
	
	var difficulty = document.getElementById("difficulty");
	if(!(checkOk(difficulty,checkString(difficulty)))){
		isok = false;
	}
	
	
	var gradeName = document.getElementById("gradeName");
	if(!(checkOk(gradeName,checkString(gradeName)))){
		isok = false;
	}
	
	
	var subjectName = document.getElementById("subjectName");
	if(!(checkOk(subjectName,checkString(subjectName)))){
		isok = false;
	}
	
	
	var version = document.getElementById("version");
	if(!(checkOk(version,checkString(version)))){
		isok = false;
	}
	
	var volume = document.getElementById("volume");
	if(!(checkOk(volume,checkString(volume)))){
		isok = false;
	}
	
	var stuType = document.getElementById("stuType");
	if(!(checkOk(stuType,checkString(stuType)))){
		isok = false;
	}

	return isok;
}

$(".layui-btn").click(function() {
	if (chkForm()) {
		$.ajax({
		   type: "POST",
		   url: ctx+"/testwebquestion/saveQuestion",
		   data: $("#form").serialize(),
		   dataType: "json",
		   success: function(json){
			  location.href = ctx+"/testwebquestionanswer/saveAnswer?questionId="+ json.questionId+ "&questionType="+ json.questionType;
		   }
		});
	}
});