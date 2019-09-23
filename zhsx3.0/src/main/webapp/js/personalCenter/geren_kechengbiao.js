$(function(){
	setIframeH();
})


function setIframeH() {
	var body = $(document.body);
	var iframe = $(parent.document.getElementById('parentIframe'));
	iframe.height(body.height()+310);
}

//设置评估资源
function setPgCour(id,isGoClass){
	if(isGoClass != 0){
		LayerOpen('设置评估课件', '850px', '400px', ctx+'/personalCenter/setPgCour?zId='+id);
	}else{
		layer.msg("该课程已结束!");
	}
}