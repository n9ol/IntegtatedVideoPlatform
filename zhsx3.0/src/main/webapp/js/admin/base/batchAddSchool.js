var layer;
layui.use([ 'layer','form','upload'], function() {
	layer = layui.layer;
	var form = layui.form();
	
	form.on('select(province)', function(data){
		$("#cityId").empty();
		$("#cityId").append('<option value="">请选择市</option>');
		$("#countyId").empty();
		$("#countyId").append("<option value=''>请选择县/区</option>");
		$.getJSON(ctx+"/adminBaseData/getCity", { provinceId: data.value}, function(json){
 			for (var int = 0; int < json.length; int++) {
				var arrayJson=json[int];
				$("#cityId").append('<option value="'+arrayJson.id+'">'+arrayJson.value+'</option>');
 			}
 			form.render('select');
		}); 
	
	});
	
	form.on('select(city)', function(data){
		$("#countyId").empty();
		$("#countyId").append("<option value=''>请选择县/区</option>");
		$.getJSON(ctx+"/adminBaseData/getCounty", { cityId: data.value}, function(json){
 			for (var int = 0; int < json.length; int++) {
				var arrayJson=json[int];
				$("#countyId").append('<option value="'+arrayJson.id+'">'+arrayJson.value+'</option>');
 			}
 			form.render('select');
		}); 
	});
	
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
	
	var countyId = document.getElementById("countyId");
	if(!(checkOk(countyId,checkString(countyId)))){
		isok = false;
	}
	
	var filePath = document.getElementById("filePath");
	if(!(checkOk(filePath,checkString(filePath)))){
		isok = false;
	}
	
	return isok;
}

//表单提交
function submitForm() {
	if(chkForm()){
		$.ajax({
			type : "POST",
			url : ctx+"/adminBaseData/batchInsertSchool",
			data : $("#myform").serialize(),
			success : function(data) {
				closeiframe(window.name);
				parent.$(".layui-show iframe")[0].contentWindow.refreshPage(data);
			}
		});
	}
}