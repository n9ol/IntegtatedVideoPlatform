var layer;
layui.use([ 'layer'], function() {
	layer = layui.layer;
});

//搜索事件
function searchCourRes(){
	var search = $("#searchval").val();
	$("input[name='search']").val(search);
	$("input[name='p']").val(1);
	$("#myform1").submit();
}

//审核
function auditCourRes(id){
	LayerOpen('审核', '964px', '830px', ctx+"/adminCourRes/auditCourRes?id="+id);
}

//全选
$("#selected-all-operation").click(function() {
	if (this.checked) {
		$("input[name='del_id']").prop("checked", true);
	} else {
		$("input[name='del_id']").prop("checked", false);
	}
});

//批量更新资源审核状态
function batchEdit(bak2){
	var ids = $("input[name='del_id']:checked").val();
	if (typeof (ids) != "undefined") {
		$.ajax({
		   type: "POST",
		   url: ctx+"/adminCourRes/batchUpdataCourRes?bak2="+bak2,
		   data: $("#myform").serialize(),
		   success: function(msg){
				layer.msg(msg,{time:1500},function(){
					 window.location.reload();
				});
		   }
		});
	} else {
		layer.msg("请选择要操作的对象");
	}
}

//删除
function del(id){
	layer.confirm('你确定要删除？',{shade: [0.2, '#FFFFFF']},function(){
		$.ajax({
			type : "POST",
			url : ctx+"/adminCourRes/delCourRes",
			data : {id:id},
			success : function(data) {
				layer.msg(data,{time : 1500},function() {
					if (data == "操作成功"){
						location.reload();
					}
				});
			}
		});
	});
}

//批量删除资源
function batchDel(){
	var ids = $("input[name='del_id']:checked").val();
	if (typeof (ids) != "undefined") {
		layer.confirm('你确定要删除？',{shade: [0.2, '#FFFFFF']},function(){
			$.ajax({
				type : "POST",
				url : ctx+"/adminCourRes/batchDelCourRes",
				data : $("#myform").serialize(),
				success : function(data) {
					layer.msg(data,{time : 1500},function() {
						if (data == "操作成功"){
							location.reload();
						}
					});
				}
			});
		});
	} else {
		layer.msg("请选择要操作的对象");
	}
}


function editCourRes(id){
	LayerOpen('编辑', '640px', '530px', ctx+'/adminCourRes/editCourRes?id=' + id+"&type=H");
}


