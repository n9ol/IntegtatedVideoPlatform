var layer;
layui.use([ 'layer','element'], function() {
	var layer = layui.layer,
	element = layui.element;
});


$(function(){
	setIframeH();
});

function setIframeH() {
	var body = $(document.body);
	var iframe = $(parent.document.getElementById('parentIframe'));
	iframe.height(body.height()+300);
}

var uploader = WebUploader.create({
    swf: ctx+'/common/webuploader/Uploader.swf',
    server: fileWebPath+'/FileUploadNotBlockServlet?path=userheadPortrait',
    pick: {
    	id:'.box_nav_chuan',
    	multiple: false
    },
    auto: true,
    accept:{
    	title: 'Images',
        extensions: 'bmp,jpg,png,gif',
        mimeTypes: 'image/bmp,image/jpg,image/png,image/gif'
    }
});

//上传成功
uploader.on('uploadSuccess',function(file,response){
	$("input[name='filePath']").val(response.path);
	$("#userHeadPhoto").attr("src",fileWebPath+"/userheadPortrait"+response.path);
});

//修改图片提交
function submitForm(){
	var filePath = $("input[name='filePath']").val();
	if(filePath === ""){
		layer.msg("请先选择图片");
	}else{
		 $.ajax({
		   type: "POST",
		   url: ctx+"/personalCenterUser/updateUserHeadPhoto",
		   data: {filePath:filePath},
		   success: function(data){
			   $(parent.document.getElementById("headPortrait")).attr("src",fileWebPath+"/userheadPortrait"+filePath);
			   layer.msg(data);
		   }
		});
	}
}



//上传图片选中样式
$(".uploadImg").click(function(){
	$("input[name='filePath']").val("/"+this.id);
	$(".uploadImg").removeClass("checkeddd");
	$(this).addClass("checkeddd");
})
