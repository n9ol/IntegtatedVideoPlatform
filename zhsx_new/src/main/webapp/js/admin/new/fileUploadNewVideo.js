var fileMd5;
var ext;
WebUploader.Uploader.register({
	"before-send-file":"beforeSendFile",
	"before-send":"beforeSend",
	"after-send-file":"afterSendFile"
},{ 
	beforeSendFile:function(file){
		var deferred = WebUploader.Deferred(); 
		(new WebUploader.Uploader()).md5File(file,0,5*1024*1024)
		.progress(function(percentage){
			$(".uploader-list").find("ul#"+file.id).find("span.waiting").text("正在获取文件信息……");
		})
		.then(function(val){
			fileMd5 = val;
			$(".uploader-list").find("ul#"+file.id).find("span.waiting").text("成功获取文件信息");
			
			//只有成功获取文件，才进行下一步
			deferred.resolve();
		});
		return deferred.promise();
	}, 
	beforeSend:function(block){
		var deferred = WebUploader.Deferred(); 
		 $.ajax({
			   type: "POST",
			   url: fileWebPath+"/FileUploadActionServlet?action=checkChunk&path=videoRes",
			   data: {
				   fileMd5:fileMd5,
				   //分块下标
				   chunk:block.chunk,
				   //分块大小
				   chunkSize:block.end-block.start	   
			   },
			   dataType:"json",
			   success: function(response){
			     if(response.ifExist){
			    	 //分块存在,跳过
			    	 deferred.reject();
			     }else{
			    	 //分块不存在或不完整,重新发送
			    	 deferred.resolve();
			     }
			   }
		});
		this.owner.options.formData.fileMd5 = fileMd5;
		return deferred.promise();
	},
	afterSendFile:function(file){
		 ext = file.ext;
		 $.ajax({
			   type: "POST",
			   url: fileWebPath+"/FileUploadActionServlet?action=mergeChunks&path=videoRes",
			   data: {fileMd5:fileMd5,ext:ext},
			   success: function(msg){
				   $("#bak2_msg").text(file.name);
				   $("input[name='bak2']").val(msg.filePath);
			   }
			});
	}
});


//转码
function videoTranscoding(id,videoPath,webpath){
	 $.ajax({
		   type: "POST",
		   url: fileWebPath+"/FormatConversionServlet?path=videoRes",
		   data: {id:id,filePath:videoPath,webpath:webpath},
		   success: function(msg){
			   
		   }
	 });
}

	
var uploader = WebUploader.create({
    swf: ctx+'/common/webuploader/Uploader.swf',
    server: fileWebPath+'/FileUploadServlet?path=videoRes',
    pick: {
    	id:'#shangchuanbtu',
    	multiple: true
    },
    auto: true,
    chunked: true,
    chunkSize: 5*1024*1024,
    accept:{
    	title: 'Videos',
        extensions: 'mpeg,mpg,rm,rmvb,avi,mov,wmv,3gp,mp4,flv',
        mimeTypes: 'video/mpeg,video/mpg,video/rm,video/rmvb,video/avi,video/mov,video/wmv,video/3gp,video/mp4,.flv'
    }
});

//显示上传弹框
uploader.on( 'filesQueued', function(files) {
	for (var int = 0; int < files.length; int++) {
		var file = files[int];
		 $.ajax({
			   type: "POST",
			   url: ctx+"/adminnew/fileUpload",
			   data: {id:file.id,name:file.name,size:file.size,ext:file.ext},
			   success: function(msg){
				   $(".uploader-list").append(msg);
				   $(".dialog").show();
			   }
			});
	}
});


//关闭上传框
$(".icon-close").click(function(){
	var files = uploader.getFiles("progress");
	if(files != "" && files.length>0){
		uploader.stop( true );
		var index = layer.confirm('还有文件正在上传,你确定要关闭？', {
			  shade: [0.2, '#FFFFFF'],
			  btn: ['确定','取消'] 
			}, function(){
				$(".dialog").hide();
				layer.close(index);
			},function(){
				uploader.upload();
			});
	}else{
		$(".dialog").hide();
	}
});

//上传进度
uploader.on( 'uploadProgress', function(file,percentage) {
	
	$(".uploader-list").find("ul#"+file.id).find("span.waiting").text("上传中");
	$(".uploader-list").find("ul#"+file.id).find("span.uploading").find("em.precent") .text(Math.round(percentage*100)+"%");
	$(".uploader-list").find("ul#"+file.id).find("div.process").css("width",Math.round(percentage*100)+"%");
});

//上传成功
uploader.on('uploadSuccess',function(file,response){
	$(".uploader-list").find("ul#"+file.id).find("span.waiting").text("上传完成");
	$(".uploader-list").find("ul#"+file.id).find("div.file-operate").find("a").hide();
});


//删除正在上传文件
function delFile(id){
	uploader.removeFile( id, true );
	$("ul#"+id).remove();
}

//暂停与开始
function pauseAndStart(id,src){
	var state = $(src).attr("id");
	if(state === "pause"){
		$(src).attr("id","start");
		$(src).find("img").attr("src",ctx+"/img/kaishi.png");
		uploader.stop(id);
	}else{
		$(src).attr("id","pause");
		$(src).find("img").attr("src",ctx+"/img/zanting.png");
		uploader.upload(id);
	}
}

