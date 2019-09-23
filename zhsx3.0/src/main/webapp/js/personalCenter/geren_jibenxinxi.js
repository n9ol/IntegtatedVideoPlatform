var element;
layui.use(['form', 'element'], function() {
	var form = layui.form;
	element = layui.element;
});

$(function(){
	if(userType != "T"){
		$("#userInfo").css("display","block");
	}else{
		teacherGradeEx();
		gradeSystem();
		teacherGradeRank();
	}
	userInfo();
	setIframeH();
});

function setIframeH() {
	var body = $(document.body);
	var iframe = $(parent.document.getElementById('parentIframe'));
	iframe.height(body.height()+500);
}


//获得用户基本信息
function userInfo(){
	$.ajax({
	   type: "POST",
	   url: ctx+"/personalCenterUser/userInfo",
	   success: function(msg){
	      $("#userInfo").html(msg);
	   }
	});
}

//获得教师成长信息
function teacherGradeEx(){
	$.ajax({
	   type: "POST",
	   url: ctx+"/personalCenterUser/teacherGradeEx",
	   success: function(msg){
	      $("#teacherGradeEx").html(msg);
	   }
	});
}

//获得成长等级说明规则
function gradeSystem(){
	$.ajax({
	   type: "POST",
	   url: ctx+"/personalCenterUser/gradeSystem",
	   success: function(msg){
	      $("#gradeSystem").html(msg);
	   }
	});
}

//获得教师等级排行榜
function teacherGradeRank(){
	$.ajax({
	   type: "POST",
	   url: ctx+"/personalCenterUser/teacherGradeRank",
	   success: function(msg){
	      $("#teacherGradeRank").html(msg);
	   }
	});
}

//查看排行榜
function viewRank(){
	$(".layui-tab-title").find("li").removeAttr("class");
	$(".layui-tab-title").find("li").eq(3).attr("class","layui-this");
	$("layui-tab-content").find("div").attr("class","layui-tab-item");
	$("#teacherGradeEx").attr("class","layui-tab-item clear");
	$("#teacherGradeRank").attr("class","layui-tab-item layui-show");
	element.init();
}

//完善资料
function perfectInfo(){
	$(".layui-tab-title").find("li").removeAttr("class");
	$(".layui-tab-title").find("li").eq(1).attr("class","layui-this");
	$("layui-tab-content").find("div").attr("class","layui-tab-item");
	$("#teacherGradeEx").attr("class","layui-tab-item clear");
	$("#userInfo").attr("class","layui-tab-item layui-show");
	element.init();
}

//发布视频 - 进入我的点播资源页面
function issueVideo(){
	//样式渲染
	parent.$(".biaoshi li").removeClass("qing");
	parent.$("#iVideoRes").addClass("qing");
	
	location.href = ctx+"/adminOffLine/videoRes";
}

//发布课件 - 进入我的课件资源页面
function issueCourRes(){
	//样式渲染
	parent.$(".biaoshi li").removeClass("qing");
	parent.$("#iCourRes").addClass("qing");
	
	location.href = ctx+"/adminCourRes/courRes";
}

//发布试卷 - 进入发布试卷页面
function issueTestPaper(){
	//样式渲染
	parent.$(".biaoshi li").removeClass("qing");
	parent.$("#iKaopingCenter").addClass("qing");
	
	location.href = ctx+"/test/paperlist?memberId="+principalId;
}

//回答问题 - 进入在线答疑 页面
function answerQuestion(){
	parent.location.href = ctx+"/zxdy/online_dayi";
}

