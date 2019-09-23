var layer;
layui.use([ 'layer' ], function() {
	layer = layui.layer;
});

// 添加用户界面
function add() {
	LayerOpen('添加用户', '880px', '740px', ctx + '/user/addUser');
}

// 删除用户
function del(id) {
	layer.confirm('你确定要删除？', {
		shade : [ 0.2, '#FFFFFF' ]
	}, function() {
		$.ajax({
			type : "POST",
			url : ctx + "/user/delUser",
			data : {
				id : id
			},
			success : function(data) {
				layer.msg(data, {
					time : 1200
				}, function() {
					if (data == "操作成功")
						$("#myform1").submit();
				});
			}
		});
	});
}

// 禁止或启用用户
function bal(id, bak) {
	var msg = "";
	if (bak == "N") {
		msg = "你确定要禁用么？";
	}
	if (bak == "Y") {
		msg = "你确定要启用么？";
	}
	layer.confirm(msg, {
		shade : [ 0.2, '#FFFFFF' ]
	}, function() {
		$.ajax({
			type : "POST",
			url : ctx + "/user/balUser",
			data : {
				id : id,
				bak : bak
			},
			success : function(data) {
				layer.msg(data, {
					time : 1200
				}, function() {
					if (data == "操作成功")
						$("#myform1").submit();
				});
			}
		});
	});
}

// 初始化用户密码
function initializePassword(id, userCode) {
	layer.confirm("你确定要初始化密码么?", {
		shade : [ 0.2, '#FFFFFF' ]
	}, function() {

		$.ajax({
			type : "POST",
			url : ctx + "/user/initializePassword",
			data : {
				id : id,
				userCode : userCode
			},
			success : function(data) {
				layer.msg(data)
			}
		});
		
	});
}

// 进入用户授权页面
function authorize(id) {
	LayerOpen('授权', '900px', '500px', ctx + '/user/authorizeUser?id=' + id);
}

// 搜索框键盘enter事件
$("#searchval").keyup(function() {
	if (event.keyCode == 13) {
		searchUser();
	}
});

// 搜索按钮事件
function searchUser() {
	var search = $("input[name='searchval']").val();
	$("input[name='search']").val(search);
	$("#myform1").submit();
}

// 下载模板
function download() {
	location.href = ctx + "/adminBaseData/downloadTem?filename=userTem.xlsx";
}

// 批量添加页面
function batchAdd() {
	LayerOpen('批量添加', '600px', '450px', ctx + '/user/batchAddUser');
}

// 刷新页面
function refreshPage(data) {
	layer.alert(data, {
		skin : 'layui-layer-molv',
		time : 0
	}, function(index) {
		$("#myform1").submit();
	});
}