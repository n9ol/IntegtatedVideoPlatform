var layer;
layui.use([ 'layer','form' ], function() {
	var form = layui.form;
	layer = layui.layer;
});

//表单验证
function chkForm() {
	var isok = true;
	
	var role = document.getElementById("role");
	if(!(checkOk(role,checkString(role)))){
		isok = false;
	}

	var description = document.getElementById("description");
	if(!(checkOk(description,checkString(description)))){
		isok = false;
	}

	return isok;
}

//表单提交
function submitForm() {
	if(chkForm()){
		$.ajax({
			type : "POST",
			url : ctx+"/adminSysrole/updateSysRole",
			data : $("#form").serialize(),
			success : function(data) {
				closeiframe(window.name);
				refresh();
				parent.layer.msg(data,{time:2000});
			}
		});
	}
}