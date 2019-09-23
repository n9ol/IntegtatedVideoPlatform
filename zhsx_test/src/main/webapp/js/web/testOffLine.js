$(function(){
	setIframeH();
});
var memberId = uid;
function setIframeH() {
	var body = $(document.body);
	var iframe = $(parent.document.getElementById('parentIframe'));
	iframe.height(body.height()+300);
}

var layer;

layui.use([ 'layer','form'], function() {
	layer = layui.layer;
	var form = layui.form;
	form.on('select(gradeId)', function(data){
		$("#subjectId").empty();
		$("#subjectId").append('<option value="">请选择科目</option>');
		$.getJSON(ctx+"/adminBaseData/getSubjects", { gradeId: data.value}, function(json){
			for (var int = 0; int < json.length; int++) {
				var arrayJson=json[int];
			$("#subjectId").append('<option  value="'+arrayJson.id+'">'+arrayJson.value+'</option>');
				
			}
			form.render('select');
		});  
	});
});

//表单提交验证
function chkForm() {
	var isok = true;
	var  gradeId=$("#gradeId").find("option:selected").val();
	if(gradeId=="" ||gradeId=="请选择考试年级"){
		isok = false;
		$("#gradeId_msg").css("color","#f00");
		$("#gradeId_msg").text("请选择考试年级!");
	}else{
		$("#gradeId_msg").css("color","#060");
		$("#gradeId_msg").html("<img src='${ctx}/images/chk_ok.jpg' border=0>");
	}
	
	var  subjectId=$("#subjectId").find("option:selected").val();
	if(subjectId=="" || subjectId=="请选择科目"){
		isok = false;
		$("#subjectId_msg").css("color","#f00");
		$("#subjectId_msg").text("请选择科目!");
	}else{
		$("#subjectId_msg").css("color","#060");
		$("#subjectId_msg").html("<img src='${ctx}/images/chk_ok.jpg' border=0>");
	}
	
	
	
	
	var title = document.getElementById("title");
	var classId = document.getElementById("classId");
	var num = document.getElementById("sum");
	var passNum = document.getElementById("passNum");
	var failNum = document.getElementById("failNum");
	var averScore = document.getElementById("averScore");
	var score = document.getElementById("score");
	 if (!checkString(title)) {
			isok = false;
		}else  if (!checkString(classId)) {
			isok = false;
		}else if(!(num,checkString(num)&&checkNum(num))){
			isok = false;
		}else if(!(passNum,checkString(passNum)&&checkNum(passNum))){
			isok = false;
		}else if(!(failNum,checkString(failNum)&&checkNum(failNum))){
			isok = false;
		}else if(!(averScore,checkString(averScore)&&checkNum(averScore))){
			isok = false;
		}else if(!(score,checkString(score)&&checkNum(score))){
			isok = false;
		}
	return isok;
}

//表单提交
function submitForm(){
	
	if(chkForm()){
		$.ajax({
			type:"post",
			url:ctx+"/testOffLine/save",
			data:$("#form").serialize(),
			success: function(data){
				layer.msg(data,{time : 1200},function() {
					if (data == "操作成功"){
						
						$("#form").submit();
						location.href=ctx+"/testOffLine/managerOffLine?memberId="+memberId;
					}
				});
			}
			
		});
	}
}
