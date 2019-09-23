$(function(){
	getOffLineData();
});


var layer;
layui.use([ 'layer','form'], function() {
	var form = layui.form;
	layer = layui.layer;

	form.on('select(gradeId)', function(data) {
		$("#subjectId").empty();
		$("#subjectId").append('<option value="">请选择科目</option>');
		$.getJSON(ctx+"/adminBaseData/getSubjects", {gradeId : data.value}, function(json) {
			for (var int = 0; int < json.length; int++) {
				var arrayJson = json[int];
				$("#subjectId").append('<option  value="' + arrayJson.id + '">'+ arrayJson.value + '</option>');
			}
			form.render('select');
		});
		
		$("input[name='gradeId']").val(data.value);
		$("input[name='subjectId']").val(null);
		$("input[name='search']").val(null);
		$("input[name='p']").val(1);
		getOffLineData();
	});
	
	
	form.on('select(subjectId)', function(data) {
		$("input[name='subjectId']").val(data.value);
		$("input[name='search']").val(null);
		$("input[name='p']").val(1);
		getOffLineData();
	});
	
});



/**
 * 得到离线评估数据
 */
function getOffLineData(){
	$.ajax({
	   type: "POST",
	   url: ctx+"/admintestOffLine/offLineData",
	   data: $("#form1").serialize(),
	   success: function(msg){
		  $(".clearfix").empty();
	      $(".clearfix").html(msg);
	   }
	});
}


//删除
function del(id) {
	layer.confirm('你确定要删除？', {
		shade : [ 0.2, '#FFFFFF' ]
	}, function() {
		$.ajax({
			type : "POST",
			url : ctx+"/admintestOffLine/del",
			data : {id : id},
			success : function(data) {
				layer.msg(data, {time : 1200}, function() {
					getOffLineData();
				});
			}
		});
	});
}


// 批量删除
function batchDel() {
	var ids = $("input[name='delTestWebOffLine_id']:checked").val();
	if (typeof (ids) != "undefined") {
		$.ajax({
			type : "POST",
			url : ctx+"/admintestOffLine/deleteBatch",
			data : $("#myform1").serialize(),
			success : function(msg) {
				layer.msg(msg, {time : 2000}, function() {
					getOffLineData();
				});
			}
		});
	} else {
		layer.msg("请选择要操作的对象");
	}
}


//搜索框键盘enter事件
$("#searchval").keyup(function(){
	if(event.keyCode == 13){
		searchData();
	} 
});

//搜索
function searchData() {
	var search = $("#searchval").val();
	$("input[name='p']").val(1);
	$("input[name='search']").val(search);
	getOffLineData();
}
