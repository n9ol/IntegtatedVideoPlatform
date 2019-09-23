var layer;
layui.use([ 'layer','form'], function() {
	layer = layui.layer;
	var form = layui.form();
	
	//获得教师
	form.on('select(schoolId)', function(data){
		$("#teacherId").empty();
		$("#teacherId").append('<option value="">请选择教师</option>');
		$.getJSON(ctx+"/getUserBySchoolId", { schoolId:data.value}, function(json){
			for (var int = 0; int < json.length; int++) {
				var arrayJson=json[int];
				$("#teacherId").append('<option value="'+arrayJson.id+'">'+arrayJson.nickName+'</option>');
			}
			form.render('select');
		});
	});
	
	
	//获得科目
	form.on('select(gradeName)', function(data){
		$("#subjectName").empty();
		$("#subjectName").append('<option value="">请选择科目</option>');
		var gradeId = $(data.elem).find("option:selected").attr("id");
		$.getJSON(ctx+"/adminBaseData/getSubjects", { gradeId: gradeId}, function(json){
 			for (var int = 0; int < json.length; int++) {
				var arrayJson=json[int];
				$("#subjectName").append('<option value="'+arrayJson.value+'">'+arrayJson.value+'</option>');
 			}
 			form.render('select');
		}); 
	});
	
});


function checkForm(){
	var isok = true;
	
	var title = document.getElementById("title");
	if (!checkOk(title,checkString(title))) {
		isok = false;
	} 
	
	var teacherId = document.getElementById("teacherId");
	if (!checkOk(teacherId,checkString(teacherId))) {
		isok = false;
	} 
	
	var subjectName = document.getElementById("subjectName");
	if (!checkOk(subjectName,checkString(subjectName))) {
		isok = false;
	} 
	
	var type = document.getElementById("type");
	if (!checkOk(type,checkString(type))) {
		isok = false;
	} 
	
	return isok;
}

function submitForm(){
	if(checkForm()){
		$.ajax({
			type : "POST",
			url : ctx+"/adminOffLine/updateVideoRes1",
			data : $("#form").serialize(),
			success : function(data) {
				layer.msg(data,{time:2000},function(){
					if(type === "Q"){
						parent.document.getElementById("parentIframe").contentWindow.getVideoResData();
					}else if(type === "H"){
						parent.$(".layui-show iframe")[0].contentWindow.ReviewVideoResData();
					}
					closeiframe(window.name);
				});
			}
		});
	}
}