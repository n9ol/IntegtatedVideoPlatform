var layer;
layui.use([ 'layer','form'], function() {
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
	
	
	
   //验证是否为空
   function checkString(src) {
		var srcname = src.id;
		var srctitle = src.title;
 		if (src.value=="") {
		$("#"+srcname+"_msg").css("color","#f00");
		$("#"+srcname+"_msg").text(srctitle+"不能空！");
		return false;
	}
	return true;
}
   
//验证学校是否已存在
function validationSchool(src){
   var srcname = src.id;
   var schoolName=src.value;
	$.getJSON(ctx+"/adminBaseData/validationSchool",{schoolName:schoolName},function(data){
		if(!data){
			$("#"+srcname+"_msg").css("color","#f00");
			$("#"+srcname+"_msg").text("学校名称已存在！");
		}else{
			checkOk(src, true);
		}
	});
}
   

//表单验证
function chkForm() {
	var isok = true;
	
	var schoolName = document.getElementById("schoolName");
	if(!(checkOk(schoolName,checkString(schoolName)))){
		isok = false;
	}

	var address = document.getElementById("address");
	if(!(checkOk(address,checkString(address)))){
		isok = false;
	}
	
	var countyId = document.getElementById("countyId");
	if(!(checkOk(countyId,checkString(countyId)))){
		isok = false;
	}

	return isok;
}

//表单提交
function submitForm() {
	if(chkForm()){
		var src = document.getElementById("schoolName");
		var schoolName = src.value;
		var srcname = src.name;
		$.getJSON(ctx+"/adminBaseData/validationSchool",{schoolName:schoolName},function(data){
			if(!data){
				$("#"+srcname+"_msg").css("color","#f00");
				$("#"+srcname+"_msg").text("学校名称已存在！");
			}else{
				checkOk(src, true);
				insterschool();
			}
		});
	}
}

//添加学校到数据库
function insterschool(){
	$.ajax({
		type : "POST",
		url : ctx+"/adminBaseData/insertSchool",
		data : $("#form").serialize(),
		success : function(data) {
			closeiframe(window.name);
			refresh();
			parent.layer.msg(data,{time:2000});
		}
	});
}

