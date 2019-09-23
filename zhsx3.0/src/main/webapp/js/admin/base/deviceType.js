var layer;
layui.use([ 'layer' ], function() {
	layer = layui.layer;
});

function addDeviceType() {
	LayerOpen('添加设备类型', '640px', '300px', ctx + '/adminBaseData/addDeviceType');
}

function del(id) {
	$.ajax({
		type : "POST",
		url : ctx + "/adminBaseData/delDeviceType",
		data : {id : id},
		success : function(data) {
			layer.msg(data, {time : 1500}, function() {
				location.reload();
			});
		}
	});
}




/*****/

function edit(id,value,sort) {
	LayerOpen('编辑版本', '640px', '300px', ctx + '/adminBaseData/editDeviceType?id='+ id+'&value='+value+'&sort='+sort);
}

// 全选
$("#selected-all-operation").click(function() {
	if (this.checked) {
		$("input[name='del_id']").prop("checked", true);
	} else {
		$("input[name='del_id']").prop("checked", false);
	}
});

// 批量删除
function batchDel() {
	var ids = $("input[name='del_id']:checked").val();
	if (typeof (ids) != "undefined") {
		$.ajax({
			type : "POST",
			url : ctx + "/adminBaseData/batchDelVersion",
			data : $("#myform").serialize(),
			success : function(data) {
				layer.msg(data, {time : 1500}, function() {
					location.reload();
				});
			}
		});
	} else {
		layer.msg("请选择要操作的对象");
	}
}