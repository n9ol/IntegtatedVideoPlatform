var layer;
layui.use([ 'layer' ], function() {
	layer = layui.layer;
});

function add() {
	LayerOpen('添加教学楼', '640px', '300px', ctx + '/baseTeachingBuilding/addBuild');
}

function del(id) {
	$.ajax({
		type : "POST",
		url : ctx + "/baseTeachingBuilding/deleteBuild",
		data : {
			id : id
		},
		success : function(data) {
			layer.msg(data, {
				time : 1500
			}, function() {
				location.reload();
			});
		}
	});
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
			url : ctx + "/baseTeachingBuilding/batchDelBuild",
			data : $("#myform").serialize(),
			success : function(data) {
				layer.msg(data, {
					time : 1500
				}, function() {
					location.reload();
				});
			}
		});
	} else {
		layer.msg("请选择要操作的对象");
	}
}


function edit(id, buildName) {
	LayerOpen('编辑教学楼', '640px', '300px', ctx
			+ '/baseTeachingBuilding/editBuild?id=' + id + '&buildName='
			+ encodeURI(encodeURI(buildName)));
}