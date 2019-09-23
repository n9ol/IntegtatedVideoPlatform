var layer;
layui.use(['layer'], function() {
			layer = layui.layer;
		});

function submit() {
	$.ajax({
		type : "POST",
		url : ctx + "/adminSysrole/updateRolePermission",
		data : $("#myform").serialize(),
		success : function(msg) {
			layer.msg(msg, {time : 2000},function(){
				closeiframe(window.name);
				refresh();
			});
		}
	});
}


$('.treeA').click(function() {
	$(this).parent().next().stop().slideToggle();
});
$(".a").click(function() {
	// 判断当前点击的复选框处于什么状态$(this).is(":checked") 返回的是布尔类型
	if ($(this).is(":checked")) {
		$(this).parent().next().find(".b").prop("checked",true);
	} else {
		$(this).parent().next().find(".b").prop("checked",false);
	}
});
$(".b").click(function() {
	var length = $(this).parent().parent().parent().find(".b:checked").length;
	if (length == 0) {
		$(this).parent().parent().parent().prev().find(".a").prop("checked", false);
	}
	if (length > 0) {
		$(this).parent().parent().parent().prev().find(".a").prop("checked", true);
	}
});