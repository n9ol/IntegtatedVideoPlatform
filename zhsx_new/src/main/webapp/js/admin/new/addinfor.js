var bak1;
var index;
var layedit;
var layer;
layui.use([ 'layedit', 'form', 'layer' ], function() {
	layedit = layui.layedit;
	layer = layui.layer;
	var form = layui.form;
	
	//建立编辑器
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
	});
	$(".layedit-tool-image").remove();

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
			url : ctx+"/adminnew/addsave",
			data : $("#form").serialize(),
			dataType : 'json',
			success : function(json) {
				
				var modelType = $("input[name='modelType']:checked").val();
				if(modelType === "S"){
					videoTranscoding(json.id,json.filePath,json.webpath);
				}
				
				refresh();
				closeiframe(window.name);
				parent.layer.msg(json.msg, {time : 2000});
				
			}
		});
	}
};


