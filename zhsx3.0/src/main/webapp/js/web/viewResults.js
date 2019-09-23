//查看评估项细则
function checkPjInfoResult(userId,pgId,onOff,allResult){
	LayerOpen('<i class = "layui-icon" style = "font-size:25px;">&#xe63c;</i> 分评细则', '860px', '620px',ctx+"/pgInfo/checkPjInfoResult?userId="+userId+"&pgId="+pgId+"&onOff="+onOff+"&allResult="+encodeURI(encodeURI(allResult)));
}