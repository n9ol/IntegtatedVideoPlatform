var layer;
layui.use([ 'layer' ], function() {
	layer = layui.layer;
});

function refreshPage(){
	$("input[name='search']").val(null);
	$("#myform1").submit();
}

//全选
$("#selected-all-operation").click(function() {
	if (this.checked) {
		$("input[name='del_id']").prop("checked", true);
	} else {
		$("input[name='del_id']").prop("checked", false);
	}
});


//批量删除
function batchDel() {
	var ids = $("input[name='del_id']:checked").val();
	if (typeof (ids) != "undefined") {
		$.ajax({
			type : "POST",
			url : ctx+"/log/batchDelLog",
			data : $("#myform").serialize(),
			success : function(data) {
				layer.msg(data,{time:1200},function(){
					$("#myform1").submit();
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
		searchLog();
	} 
});


//搜索
function searchLog() {
	var search = $("input[name='searchval']").val();
	$("input[name='search']").val(search);
	$("#myform1").submit();
}

//清空
function emptyLog(){
	layer.confirm('你确定要清空？',{shade: [0.2, '#FFFFFF']},function(){
		$.ajax({
			type : "POST",
			url : ctx+"/log/emptyLog",
			success : function(data) {
				layer.msg(data,{time:1200},function(){
					$("#myform1").submit();
				});
			}
		});
	});
}