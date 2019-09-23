$(function(){
	setIframeH();
});

function setIframeH() {
	var body = $(document.body);
	var iframe = $(parent.document.getElementById('parentIframe'));
	iframe.height(body.height()+300);
}

var layer;
layui.use([ 'layer','form'], function() {
	layer = layui.layer;
	var form = layui.form;
});

//表单提交验证
function chkForm() {
	var isok = true;

	var oldPassword = document.getElementById("oldPassword");
	if(!(checkOk(oldPassword,checkString(oldPassword)))){
		isok = false;
	}

	var password = document.getElementById("password");
	if(!(checkOk(password,checkString(password)&&checkDigit1(password)))){
		isok = false;
	}

	return isok;
}

//表单提交
function submitForm(){
	if(chkForm()){
		var src = document.getElementById("oldPassword");
		var srcname = src.id;
		var oldPassword=src.value;
		$.getJSON(ctx+"/personalCenterUser/validationOldPassword",{oldPassword:oldPassword},function(data){
			if(!data){
				$("#"+srcname+"_msg").css("color","#f00");
				$("#"+srcname+"_msg").text("旧密码不正确！");
			}else{
				checkOk(src, true);
				$.ajax({
					type : "POST",
					url : ctx+"/personalCenterUser/updateUserPassword",
					data : $("#form").serialize(),
					success : function(data) {
						layer.msg(data+",3秒后需要重新验证登录",{time:3000},function(){
							if(data === "操作成功"){
								parent.location.href=ctx+"/logout.action";
							}
						});
					}
				});
			}
		});
	}
}