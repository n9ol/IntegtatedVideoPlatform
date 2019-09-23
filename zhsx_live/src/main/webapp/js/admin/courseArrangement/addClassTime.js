var layer;
layui.use([ 'layer','form'], function() {
	layer = layui.layer;
	var form = layui.form();
});

//表单验证
function chkForm() {
	var isok = true;
	for (var int = 0; int <= 7; int++) {
		 var starttime = $("#starttime"+int).val();
		 var endtime = $("#endtime"+int).val();
		 if(starttime != "" && endtime != ""){
			 if(endtime < starttime){
				 layer.alert('第'+(int+1)+'节,结束时间小于开始时间!', {skin: 'layui-layer-molv',closeBtn: 0});
				 isok = false;
			  }
		 }else{
			 $(".del"+int).empty();
		 }
	}
	return isok;
}

//表单提交
function submitForm() {
	if(chkForm()){
		$.ajax({
			type : "POST",
			url : ctx+"/adminCourseArrangement/insertClassTime",
			data : $("#form").serialize(),
			success : function(data) {
				closeiframe(window.name);
				parent.$(".layui-show iframe")[0].contentWindow.refreshPage(data.res);
			}
		});
	}
}