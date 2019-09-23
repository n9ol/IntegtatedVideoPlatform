var layer;
layui.use(['layer'], function() {
			layer = layui.layer;
		});

function isAvailable(id, available) {
	var msg;
	if (available == 0) {
		msg = "您确定要禁止该角色么？";
	} else if (available == 1) {
		msg = "您确定要开启该角色么？";
	}
	layer.confirm(msg, {shade : [0.2, '#FFFFFF']}, function() {
		$.ajax({
			type : "post",
			url : ctx + "/adminSysrole/updateSysRole",
			data : {id : id,available : available},
			success : function(data) {
				layer.msg(data, {time : 1200}, function() {
					if (data == "操作成功") {
						refresh();
					}
				});
			}
		});
	});
}

//roleType 和 menuType 是保持一致的
function add() {
	LayerOpen('添加', '600px', '400px', ctx + '/adminSysrole/addSysRole?roleType='+menuType);
}

function edit(id) {
	LayerOpen('编辑', '600px', '400px', ctx + '/adminSysrole/editSysRole?id='+ id);
}

function del(id) {
	layer.confirm("你确定要删除么?", {shade : [0.2, '#FFFFFF']}, function() {
		$.ajax({
			type : "post",
			url : ctx + "/adminSysrole/delSysRole",
			data : {id : id},
			success : function(data) {
				layer.msg(data, {time : 1200}, function() {
					if (data == "操作成功") {
						refresh();
					}
				});
			}
		});
	});
}

function accredit(id) {
	self.parent.layer.open({
		type : 2,
		title : ['授权', 'z-index:auto;'],
		shade : 0.2,
		area : ['800px', '600px'],
		content : [ctx + '/adminSysrole/accreditTree?id=' + id+'&menuType='+menuType]
	});
}
