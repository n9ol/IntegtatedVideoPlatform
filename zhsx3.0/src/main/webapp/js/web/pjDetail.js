var layer;
layui.use(['layer', 'element'], function() {
	var element = layui.element();
	layer = layui.layer;
});

//更新评估内容分数
function updataPjDetailScore(id,src){
	var score = src.value;
	if(!$.isNumeric(score) ||  score > 100 || score < 0 ){
		layer.msg("分值必须在0到100之间");
		$(src).val(0);
	}else{
		$.getJSON(ctx+"/pgInfo/updataPjDetailScore", { id: id, score: score });
	}
}

//更新评估项
function updataWebPjInfo(){
	$.ajax({
		type : "POST",
		url : ctx+"/pgInfo/updataWebPjInfo",
		data : $("#myformPjDetail").serialize(),
		success : function(data) {
			closeiframe(window.name);
			window.parent.refreshPage(data);
		}
	});
}