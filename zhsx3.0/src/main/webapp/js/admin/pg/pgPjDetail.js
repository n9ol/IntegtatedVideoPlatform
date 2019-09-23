var layer;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	var form = layui.form;

	form.on('select(pgtype)', function(data) {
		$("input[name='type']").val(data.value);
		$("input[name='pjinfoId']").val(null);
		refreshPage();
	});

	form.on('select(pjinfo)', function(data) {
		$("input[name='pjinfoId']").val(data.value);
		refreshPage();
	});

});

function turnPage(p) {
	$("input[name='p']").val(p);
	refreshPage();
}

function refreshPage() {
	$("#myform1").submit();
}

function add() {
	LayerOpen('添加评估内容', '640px', '400px', ctx + '/pg/addPjDetail');
}

function del(id) {
	$.ajax({
		type : "POST",
		url : ctx + "/pg/delPjDetail",
		data : {
			id : id
		},
		success : function(data) {
			layer.msg(data, {
				time : 1200
			}, function() {
				if (data == "操作成功")
					refreshPage();
			});
		}
	});
}

function edit(id, type) {
	LayerOpen('编辑评估内容', '640px', '400px', ctx + '/pg/editPjDetail?id=' + id
			+ '&type=' + type);
}