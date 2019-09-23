var layer;
layui.use([ 'layer','form' ], function() {
	layer = layui.layer;
	var form = layui.form();
});

//审核页面
function review(isShow){
	$("input[name='isShow']").val(isShow);
	var bak = $("input[name='bak']").val();
	if(isShow === "N" && bak === ""){
		layer.msg("驳回原因不能为空");
	}else{
		if(isShow === "Y"){
			$("input[name='bak']").val("通过");
		}
		$.ajax({
			type : "POST",
			url : ctx+"/adminOffLine/updateVideoRes1",
			data :$("#form").serialize(),
			success : function(data) {
				layer.msg(data,{time:2000},function(){
					closeiframe(window.name);
					refresh();
				});
			}
		});
	}
}