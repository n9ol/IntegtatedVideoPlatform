var layer;
layui.use([ 'layer' ], function() {
	layer = layui.layer;
});

function submitForm() {
	var va = $("#buildName").val();
	if (va == "") {
		layer.msg("教学楼名称不能为空");
		return;
	}

	$.ajax({
		type : "POST",
		url : ctx + "/baseTeachingBuilding/insterBuild",
		data : $("#form").serialize(),
		success : function(data) {
			closeiframe(window.name);
			refresh();
			parent.layer.msg(data);
		}
	});
}