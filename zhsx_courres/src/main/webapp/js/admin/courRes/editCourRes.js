var layer;
layui.use([ 'layer','form'], function() {
	layer = layui.layer;
	var form = layui.form();
	
	//获得科目
	form.on('select(gradeName)', function(data){
		$("#subjectsName").empty();
		$("#subjectsName").append('<option value="">请选择科目</option>');
		var gradeId = $(data.elem).find("option:selected").attr("id");
		$.getJSON(ctx+"/adminBaseData/getSubjects", { gradeId: gradeId}, function(json){
 			for (var int = 0; int < json.length; int++) {
				var arrayJson=json[int];
				$("#subjectsName").append('<option value="'+arrayJson.value+'">'+arrayJson.value+'</option>');
 			}
 			form.render('select');
		}); 
	});
	
});


function checkForm(){
	var isok = true;
	
	var name = document.getElementById("name");
	if (!checkOk(name,checkString(name))) {
		isok = false;
	} 
	
	var subjectsName = document.getElementById("subjectsName");
	if (!checkOk(subjectsName,checkString(subjectsName))) {
		isok = false;
	} 
	
	var bak1 = document.getElementById("bak1");
	if (!checkOk(bak1,checkString(bak1))) {
		isok = false;
	} 
	
	var  bak=$("#bak").find("option:selected").val();
	if(bak==""){
		isok = false;
		$("#bak1_msg").css("color","#f00");
		$("#bak1_msg").text("版本册次不能为空!");
	}else{
		$("#bak1_msg").css("color","#060");
		$("#bak1_msg").html("<img src='"+ctx+"/images/chk_ok.jpg' border=0>");
	}
	
	return isok;
}

function submitForm(){
	if(checkForm()){
		$.ajax({
			type : "POST",
			url : ctx+"/adminCourRes/updataCourRes",
			data : $("#form").serialize(),
			success : function(data) {
				layer.msg(data,{time:2000},function(){
					if(type === "Q"){
						parent.document.getElementById("parentIframe").contentWindow.location.reload(true);
					}else if(type === "H"){
						refresh();
					}
					closeiframe(window.name);
				});
			}
		});
	}
}