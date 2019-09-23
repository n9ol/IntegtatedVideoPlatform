var layer;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	var form = layui.form;
});

//表单提交验证
function chkForm() {
	var isok = true;
	var title = document.getElementById("title");
	if (!checkString(title)) {
		isok = false;
	}
	var weight = document.getElementById("weight");
	if (!(checkString(weight) && checkPositiveInteger(weight))) {
		isok = false;
	}
	var bak = document.getElementById("bak");
	if (!(checkString(bak) && checkPositiveInteger(bak))) {
		isok = false;
	}
	return isok;
}

function submitForm() {
	if (chkForm()) {
		$.ajax({
			type : "POST",
			url : ctx+"/pg/updatePjinfo",
			data : $("#form").serialize(),
			success : function(data) {
				closeiframe(window.name);
				refresh();
				parent.layer.msg(data, {
					time : 2000
				});
			}
		});
	}
}