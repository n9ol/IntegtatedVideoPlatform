$(function(){
	$("input[name='memberId']").val(memberId);
	getdata();
});


var layer;
layui.use([ 'form', 'element', 'layer' ], function() {
	var form = layui.form;
	var element = layui.element;
	layer = layui.layer;
});


function setIframeH() {
	var body = $(document.body);
	var iframe = $(parent.document.getElementById('parentIframe'));
	iframe.height(body.height()+115);
}


function getdata(){
	$.ajax({
	   type: "POST",
	   url: ctx+ "/testwebquestion/findQuestionData",
	   data: $("#form").serialize(),
	   success: function(msg){
	     $(".Data").html(msg);
	     setIframeH();
	   }
	});
}


function selectGrade() {
	var gradeId = $("#gradeName").find("option:selected").attr("name");
	var gradeName = $("#gradeName").find("option:selected").val();
	
	$(".subjectName").empty();
	$(".subjectName").append('<option value="">请选择科目</option>');
	$.getJSON(ctx+"/adminBaseData/getSubjects", {gradeId : gradeId}, function(json) {
		for (var int = 0; int < json.length; int++) {
			var arrayJson = json[int];
			$(".subjectName").append('<option  value="' + arrayJson.value + '">'+ arrayJson.value + '</option>');
		}
	});
	
	
	$("input[name='gradeName']").val(gradeName);
	getdata();
}



function selectSubject(){
	var subjectName = $("#subjectName").find("option:selected").val();
	$("input[name='subjectName']").val(subjectName);
	getdata();
}

function selectquestionType(){
	var questionType = $("#questionType").find("option:selected").val();
	$("input[name='questionType']").val(questionType);
	getdata();
}


//搜索框键盘enter事件
$("#search1").keyup(function(){
	if(event.keyCode == 13){
		searchData();
	} 
});
function searchData() {
	var search = $("input[name='search1']").val();
	$("input[name='search']").val(search);
	getdata();
}


function add() {
    location.href = ctx+"/testwebquestion/addQuestion";
}

function del(id) {
	$.ajax({
		type : "POST",
		url : ctx+"/testwebquestion/delQuestion",
		data : {id : id},
		success : function(data) {
			getdata();
		}
	});
}

//批量删除
function batchDel() {
	var ids = $("input[name='del_id']:checked").val();
	if (typeof (ids) != "undefined") {
		layer.confirm('你确定要删除？', {shade : [ 0.2, '#FFFFFF' ]}, function() {
			$.ajax({
				type : "POST",
				url : ctx+"/testwebquestion/delBatch",
				data : $("#myform").serialize(),
				success : function(data) {
					layer.msg(data, {time : 2000}, function() {
						if (data == "操作成功"){
							getdata();
						}
					});
				}
			});
		});
	} else {
		layer.msg("请选择要操作的对象");
	}
}


//查看试题详情
function findTi(id) {
	parent.$(".zhong_right iframe").attr("src",ctx+"/testwebquestionanswer/QueAnswer?questionId=" + id);
}


function edit(id) {
	location.href = ctx+"/testwebquestion/editQuestion?id=" + id;
}