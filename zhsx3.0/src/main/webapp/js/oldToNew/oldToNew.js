var layer;
layui.use([ 'layer','form','upload'], function() {
	layer = layui.layer;
	var form = layui.form();
});



//文件上传 - 用户
var uploaderUser = WebUploader.create({
    swf: ctx+'/common/webuploader/Uploader.swf',
    server: ctx+'/uploadFile',
    pick: {
    	id:'.box_nav_chuanUser',
    	multiple: false
    },
    auto: true,
    accept:{
    	title: '',
        extensions: 'xls,xlsx',
        mimeTypes: '.xls,.xlsx'
    }
});
uploaderUser.on('uploadSuccess',function(file,response){
	 $("#filePathUser").val(response.path);
	 $("#filePathUser_msg").text(response.path);
});



//文件上传 - 学校
var uploaderSchool = WebUploader.create({
    swf: ctx+'/common/webuploader/Uploader.swf',
    server: ctx+'/uploadFile',
    pick: {
    	id:'.box_nav_chuanSchool',
    	multiple: false
    },
    auto: true,
    accept:{
    	title: '',
        extensions: 'xls,xlsx',
        mimeTypes: '.xls,.xlsx'
    }
});
uploaderSchool.on('uploadSuccess',function(file,response){
	 $("#filePathSchool").val(response.path);
	 $("#filePathSchool_msg").text(response.path);
});



//文件上传 - 教室
var uploaderClass = WebUploader.create({
    swf: ctx+'/common/webuploader/Uploader.swf',
    server: ctx+'/uploadFile',
    pick: {
    	id:'.box_nav_chuanClass',
    	multiple: false
    },
    auto: true,
    accept:{
    	title: '',
        extensions: 'xls,xlsx',
        mimeTypes: '.xls,.xlsx'
    }
});
uploaderClass.on('uploadSuccess',function(file,response){
	 $("#filePathClass").val(response.path);
	 $("#filePathClass_msg").text(response.path);
});



//提交表单 - 用户
function submitFormUser() {
	if(true){
		$.ajax({
			type : "POST",
			url : ctx+"/oldToNew/batchInsertUser",
			data : $("#myformUser").serialize(),
			success : function(data) {
				$("#filePathUser_msg").text(data);
			}
		});
	}
}

//提交表单 - 学校
function submitFormSchool() {
	if(true){
		$.ajax({
			type : "POST",
			url : ctx+"/oldToNew/batchInsertSchool",
			data : $("#myformSchool").serialize(),
			success : function(data) {
				$("#filePathSchool_msg").text(data);
			}
		});
	}
}


//提交表单 - 教室
function submitFormClass() {
	if(true){
		$.ajax({
			type : "POST",
			url : ctx+"/oldToNew/batchInsertClassRoom",
			data : $("#myformClass").serialize(),
			success : function(data) {
				$("#filePathClass_msg").text(data);
			}
		});
	}
}