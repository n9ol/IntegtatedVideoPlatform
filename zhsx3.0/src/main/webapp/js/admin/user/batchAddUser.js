var layer;
layui.use([ 'layer','form','upload'], function() {
	layer = layui.layer;
	var form = layui.form;
});



//文件上传
var uploader = WebUploader.create({
    swf: ctx+'/common/webuploader/Uploader.swf',
    server: ctx+'/uploadFile',
    pick: {
    	id:'.box_nav_chuan',
    	multiple: false
    },
    auto: true,
    accept:{
    	title: '',
        extensions: 'xls,xlsx',
        mimeTypes: '.xls,.xlsx'
    }
});


//上传成功
uploader.on('uploadSuccess',function(file,response){
	 $("#filePath").val(response.path);
	 $("#filePath_msg").css("color","#F7B824");
	 $("#filePath_msg").text(response.path);
});


//表单验证
function chkForm() {
	var isok = true;
	
//	var schoolId = document.getElementById("schoolId");
//	if(!(checkOk(schoolId,checkString(schoolId)))){
//		isok = false;
//	}
	
	var filePath = document.getElementById("filePath");
	if(!(checkOk(filePath,checkString(filePath)))){
		isok = false;
	}
	return isok;
}

//提交表单
function submitForm() {
	if(chkForm()){
		$.ajax({
			type : "POST",
			url : ctx+"/user/batchInsertUser",
			data : $("#myform").serialize(),
			success : function(data) {
				closeiframe(window.name);
				parent.$(".layui-show iframe")[0].contentWindow.refreshPage(data);
			}
		});
	}
}