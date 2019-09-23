var layer;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	var form = layui.form;

	form.on('select(pgtype)', function(data) {
		$("input[name='type']").val(data.value);
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
	LayerOpen('添加评估项', '640px', '350px', ctx + '/pg/addPjinfo');
}

function del(id) {
	$.ajax({
		type : "POST",
		url : ctx + "/pg/delPjInfo",
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

function edit(id) {
	LayerOpen('编辑评估项', '640px', '350px', ctx + '/pg/editPjInfo?id=' + id);
}