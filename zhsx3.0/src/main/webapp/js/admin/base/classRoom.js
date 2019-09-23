var layer;
layui.use([ 'layer' ], function() {
	layer = layui.layer;
});

function addclassRoom() {
	LayerOpen('添加教室', '640px', '620px', ctx+'/adminBaseData/addclassRoom');
}

function del(id) {
	layer.confirm('删除教室将会删除教室下的所有课程!', {btn: ['确定','取消'] }, function(){
		$.ajax({
			type : "POST",
			url : ctx+"/adminBaseData/delClassRoom",
			data : {id : id},
			success : function(data) {
				layer.msg(data,{time : 1000},function() {
					if (data == "操作成功")
						$("#myform1").submit();
				});
			}
		});
	});
}

function edit(id) {
	LayerOpen('编辑教室', '640px', '620px', ctx+'/adminBaseData/editClassRoom?id=' + id);
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
		layer.confirm('删除教室将会删除教室下的所有课程!', {btn: ['确定','取消'] }, function(){
			$.ajax({
				type : "POST",
				url : ctx+"/adminBaseData/batchDelClassRoom",
				data : $("#myform").serialize(),
				success : function(data) {
					refreshPage(data);
				}
			});
		});
	} else {
		layer.msg("请选择要操作的对象");
	}
}

function batchUpdateState(state){
	var ids = $("input[name='del_id']:checked").val();
	if (typeof (ids) != "undefined") {
		layer.confirm('确定要更改教室的状态么?', {btn: ['确定','取消'] }, function(){
			$.ajax({
				type : "POST",
				url : ctx+"/adminBaseData/batchUpdateState?bak="+state,
				data : $("#myform").serialize(),
				success : function(data) {
					refreshPage(data);
				}
			});
		});
	} else {
		layer.msg("请选择要操作的对象");
	}
}








//下载模板
function download() {
	location.href = ctx+"/adminBaseData/downloadTem?filename=classRoomTem.xlsx";
}

function batchAdd() {
	LayerOpen('批量添加', '600px', '450px', ctx+'/adminBaseData/batchAddClassRoom');
}


//搜索框键盘enter事件
$("#searchval").keyup(function(){
	if(event.keyCode == 13){
		searchClassRoom();
	} 
});

function searchClassRoom() {
	var search = $("input[name='searchval']").val();
	$("input[name='search']").val(search);
	$("#myform1").submit();
}

function refreshPage(data){
	layer.alert(data, {skin: 'layui-layer-molv',time:0}, function(index){
			$("#myform1").submit();
		});			
}