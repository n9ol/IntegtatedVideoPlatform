$(function(){
	getListData();
    setIframeH();
});


function getListData(){
	$.ajax({
	   type: "POST",
	   url: ctx+"/personalCenter/getRecord",
	   data: $("#myform").serialize(),
	   success: function(msg){
	     $("#pjRecord").html(msg);
	   }
	});
}


function setIframeH() {
	var body = $(document.body);
	var iframe = $(parent.document.getElementById('parentIframe'));
	iframe.height(body.height()+900);
}

var layer;
layui.use(['layer'], function() {
	layer = layui.layer;
})

