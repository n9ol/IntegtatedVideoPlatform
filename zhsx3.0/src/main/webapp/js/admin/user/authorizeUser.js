var layer;
layui.use([ 'layer','form'], function() {
	layer = layui.layer;
	var form = layui.form();
	
	
	form.on('radio(isadmin)', function(data){
		if(data.value == 1){
			$("#isadmin").show();
		}else{
			$("#isadmin").hide();
		}
	});
	
	form.on('radio(isSaAdmin)', function(data){
		if(data.value == "SA"){
			$(".school").show();
		}else{
			$(".school").hide();
		}
	});
	
});

//表单提交验证
function chkForm() {
	var isok = true;
	var bak1=$("input[name='bak1']:checked").val(); 
	if(bak1 === "SA"){
		var schoolId = document.getElementById("schoolId");
		if(!(checkOk(schoolId,checkString(schoolId)))){
			isok = false;
		}
	}
	return isok;
}



//表单提交
function submitForm(){
	if(chkForm()){
		$.ajax({
			type : "POST",
			url : ctx+"/user/updateUserIsadmin",
			data : $("#myform").serialize(),
			success : function(data) {
				layer.msg(data,{time:2000},function(){
					if(data == "操作成功"){
						closeiframe(window.name);
						refresh();
					}
				});
			}
		});
	}
}