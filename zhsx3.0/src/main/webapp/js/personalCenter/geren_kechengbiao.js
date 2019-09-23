$(function(){
	setIframeH();
})


function setIframeH() {
	var body = $(document.body);
	var iframe = $(parent.document.getElementById('parentIframe'));
	iframe.height(body.height()+210);
}

//设置评估资源
function setPgCour(id,isGoClass){
	if(isGoClass != 0){
		self.parent.layer.open({
	        type: 2,
	        title: ['设置评估课件','z-index:auto;'],
	        shade: 0.2,
	        area: ['850px','600px'],
	        maxmin: true, //开启最大化最小化按钮
	        scrollbar: false,
	        resize :false,
	        shadeClose: true,
	        content:[ctx+'/personalCenter/setPgCour?zId='+id, 'yes']
	      });
	}else{
		layer.msg("该课程已结束!");
	}
}