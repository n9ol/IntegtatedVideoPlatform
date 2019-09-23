var layer;
layui.use([ 'layer' ], function() {
	layer = layui.layer;
});

function submitForm() {
	var va = $("#value").val();
	if (va == "") {
		layer.msg("名称不能为空");
		return;
	}
	var sort = $("#sort").val();
	if (sort == "") {
		layer.msg("序号不能为空");
		return;
	}

	$.ajax({
		type : "POST",
		url : ctx+"/adminBaseData/validationArea",
		data : {value : va,keyName :'V'},
		success : function(data) {
			if (data == "F") {
				$.ajax({
					type : "POST",
					url : ctx+"/adminBaseData/insertVersion",
					data : $("#form").serialize(),
					success : function(data) {
						closeiframe(window.name);
						refresh();
					}
				});
			} else {
				layer.msg("该版本已存在!");
			}
		}
	});

}