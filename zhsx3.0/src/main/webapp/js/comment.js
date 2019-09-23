layui.use(['layedit'], function() {
	var	layedit = layui.layedit;
	
	var index0 = layedit.build('bianji', {
		height: 100,
		tool: [
			'strong' //加粗
			, 'italic' //斜体
			, 'underline' //下划线
			, 'del' //删除线
			, '|' //分割线
			, 'left' //左对齐
			, 'center' //居中对齐
			, 'right' //右对齐
			, 'face' //表情
		]
	}); //建立编辑器
	

	
	$("#submitComment").click(function(){
		var contextMo = layedit.getContent(index0);
		var context = layedit.getText(index0);
		if(context != ""){
			var contextType = $("#contextType").val();
			$.ajax({
			   type: "POST",
			   url: ctx + "/webComments/insterComment",
			   data: {contextType:contextType,contextId:receive.id,contextMo:contextMo,context:context},
			   success: function(msg){
				   getCommentData(1);
			   }
			});
		}else{
			layer.msg("评论内容不能为空");
		}
	});
	
	
});

//差评
function chaPing(src,id){
	var num = $(src).next("span").text();
	$(src).next("span").text(Number(num)+1);
	$.ajax({
	   type: "GET",
	   url: ctx + "/webComments/updataComment",
	   data: { id: id, thumbsDown: Number(num)+1 }
	});
}

//好评
function haoPing(src,id){
	var num = $(src).next("span").text();
	$(src).next("span").text(Number(num)+1);
	$.ajax({
	   type: "GET",
	   url: ctx + "/webComments/updataComment",
	   data: { id: id, thumbsUp: Number(num)+1 }
	});
}

