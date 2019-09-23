layui.use([ 'laypage' ], function() {
	laypage = layui.laypage;
	laypage({
		cont : "page",
		pages : "${pages!'1'}",
		curr : "${pageNum!'1'}",
		skip : false,
		skin : "molv",
		groups : 10,
		jump : function(e, first) {
			if (!first) {
				location.href = ctx + "/adminLoPgGroup/pgGroup?p=" + e.curr;
			}
		}
	});
});

layui.use([ 'jquery', 'layer', 'form' ], function() {
	window.jQuery = window.$ = layui.jquery;
	window.layer = layui.layer;
	var form = layui.form();

	// 全选
	$("#selected-all-operation").click(function() {
		if (this.checked) {
			$("input[name='del_id']").prop("checked", true);
		} else {
			$("input[name='del_id']").prop("checked", false);
		}
	});

	// 添加评估小组
	window.add = function add() {
		layer.prompt({
			title : '请输入小组名称',
			maxlength : 255
		}, function(value, index, elem) {
			insert(value, index);
		});
	}
	function insert(name, index) {
		$.post(ctx + "/adminLoPgGroup/insertPgGroup", {
			name : name
		}, function(data) {
			refresh();
			parent.layer.msg(data);
		});
	}

	// 搜索框键盘enter事件
	$("#searchval").keyup(function() {
		if (event.keyCode == 13) {
			searchPgGroup();
		}
	});
	window.searchPgGroup = function() {
		var searchval = $("#searchval").val();
		location.href = ctx + "/adminLoPgGroup/pgGroup?search=" + searchval;
	}

	window.del = function(id,obj) {
		$.post(ctx + "/adminLoPgGroup/delPgGroup", {
			id : id
		}, function(data) {
			$(obj).parent().parent().remove();
			layer.msg(data);
		});
	}

	window.batchDel = function() {
		var ids = $("input[name='del_id']:checked").val();
		if (typeof (ids) != "undefined") {
			$.post(ctx + "/adminLoPgGroup/batchDelPgGroup", $("#myform")
					.serialize(), function(data) {
				refresh();
				parent.layer.msg(data);
			});
		} else {
			layer.msg("请选择要操作的对象");
		}
	}

	window.editPgGroupUser = function(id) {
		self.parent.layer.open({
	        type: 2,
	        title: ['编辑小组成员','z-index:auto;'],
	        shade: 0.2,
	        area: ['640px','500px'],
	        resize :false,
	        content:[ctx + '/adminLopgGroupUser/pgGroupUser?pgGroupId=' + id, 'yes']
	      });
	}

	window.edit = function(id, oldName) {
		layer.prompt({
			title : '请输入小组名称',
			maxlength : 255,
			value : oldName,
		}, function(value, index, elem) {
			update(id, value, index);
		});
	}
	function update(id, newName, index) {
		$.post(ctx + "/adminLoPgGroup/updatePgGroup", {
			id : id,
			name : newName
		}, function(data) {
			refresh();
			parent.layer.msg(data);
		});
	}

});