var bak1;
var layedit;
var index;
layui.use([ 'layedit', 'form', 'upload', 'jquery', 'layer' ], function() {
	layedit = layui.layedit;
	var layer = layui.layer;

	var form = layui.form;
	index = layedit.build('demo', {
		height: 300,
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
	}); // 建立编辑器
	
	//监听新闻类别选择
	form.on('radio(test)', function(data) {
		if (data.value === "W") {
			$("#shangchuanVideo").hide();
		} else if (data.value === "S") {
			$("#shangchuanVideo").show();
		}
	});

});

// 图片上传-文本编辑器添加图片
var uploaderimg = WebUploader.create({
	swf : ctx + '/common/webuploader/Uploader.swf',
	server : fileWebPath + '/FileUploadNotBlockServlet?path=newpic',
	pick : {
		id : '#uploadImg',
		multiple : false
	},
	auto : true,
	accept : {
		title : 'Images',
		extensions : 'bmp,jpg,png,gif',
		mimeTypes : 'image/bmp,image/jpg,image/png,image/gif'
	}
});

// 上传成功
uploaderimg.on('uploadSuccess', function(file, response) {
	$(window.frames["LAY_layedit_1"].document).find("body").append("<img src='" + fileWebPath +"/newpic"+ response.path + "'>");
});


// 表单验证
function chkForm() {
	var isok = true;
	
	
	var title = document.getElementById("title");
	if(!checkOk(title,checkString(title))){
		isok = false;
	}
	
	var writer = document.getElementById("writer");
	if(!checkOk(writer,checkString(writer))){
		isok = false;
	}
	
	var modelType = $("input[name='modelType']:checked").val();
	if(modelType === "S"){
		var bak2 = document.getElementById("bak2");
		if(!checkOk(bak2,checkString(bak2))){
			isok = false;
		}
	}

	return isok;
}

function submitForm() {
	if (chkForm()) {
		bak1 = layedit.getContent(index);
		$("#demo").val(bak1);
		$.ajax({
			type : 'post',
			url : ctx+"/adminnew/editNew",
			data : $("#form").serialize(),
			success : function(json) {
				
				var modelType = $("input[name='modelType']:checked").val();
				var oldfilePath = "${news.bak2!''}";
				var bak2 = $("input[name='bak2']").val();
				if(modelType === "S" && oldfilePath != bak2){
					videoTranscoding(json.id,json.filePath,json.webpath);
				}
				
				closeiframe(window.name);
				refresh();
				parent.layer.msg(json.msg, {time : 2000});
			}
		});
	}
};


window.onload = function() {
	var modelType = $("input[name='modelType']:checked").val();
	if (modelType === "W") {
		$("#shangchuanVideo").hide();
	}
};