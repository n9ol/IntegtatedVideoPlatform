//编辑、保存按钮事件
$(".bj").click(function(){
	if ($(this).html()==="编辑") {
		$(this).html("保存");
		$(".base").show();
		$(".base_view").hide();
	} else{
		savedata();
	}
});

//获得市
function getcity(src){
	var  provinceId = $(src).find("option:selected").val();
	$("#cityId").empty();
	$("#cityId").append('<option value="">请选择市</option>');
	$("#countyId").empty();
	$("#countyId").append("<option value=''>请选择县/区</option>");
	$("#schoolId").empty();
	$("#schoolId").append("<option value=''>请选择学校</option>");
	$.getJSON(ctx+"/adminBaseData/getCity", { provinceId: provinceId}, function(json){
		for (var int = 0; int < json.length; int++) {
			var arrayJson=json[int];
			$("#cityId").append('<option value="'+arrayJson.id+'">'+arrayJson.value+'</option>');
		}
	}); 
}

//获得区县
function getcounty(src){
	var  cityId = $(src).find("option:selected").val();
	$("#countyId").empty();
	$("#countyId").append("<option value=''>请选择县/区</option>");
	$("#schoolId").empty();
	$("#schoolId").append("<option value=''>请选择学校</option>");
	$.getJSON(ctx+"/adminBaseData/getCounty", { cityId: cityId}, function(json){
		for (var int = 0; int < json.length; int++) {
			var arrayJson=json[int];
			$("#countyId").append('<option value="'+arrayJson.id+'">'+arrayJson.value+'</option>');
		}
	}); 
}

//获得学校
function getschool(src){
	var  countyId = $(src).find("option:selected").val();
	$("#schoolId").empty();
	$("#schoolId").append("<option value=''>请选择学校</option>");
	$.getJSON(ctx+"/getSchoolByCountyId", { countyId: countyId}, function(json){
		for (var int = 0; int < json.length; int++) {
			var arrayJson=json[int];
			$("#schoolId").append('<option value="'+arrayJson.id+'">'+arrayJson.schoolName+'</option>');
		}
	});
}

//表单提交验证
function chkForm() {
	var isok = true;
	
	var nickName = document.getElementById("nickName");
	if(!(checkOk(nickName,checkString(nickName)))){
		isok = false;
	}

	if(userType === "T"||userType === "S"){
		var schoolId = document.getElementById("schoolId");
		if(!(checkOk(schoolId,checkString(schoolId)))){
			isok = false;
		}
	}
	
	var countyId = document.getElementById("countyId");
	if(!(checkOk(countyId,checkString(countyId)))){
		isok = false;
	}
	
	var email = document.getElementById("email");
	if(email.value != null && email.value != ""){
		if(!(checkOk(email,checkEmail(email)))){
			isok = false;
		}
	}
	
	var phone = document.getElementById("phone");
	if(phone.value != null && phone.value != ""){
		if(!(checkOk(phone,checkPhone(phone)))){
			isok = false;
		}
	}
	
	return isok;
}

//验证邮箱
function checkEmail1(){
	var src = document.getElementById("email");
	var srcname = src.id;
	var email=src.value;
	if(email != null && email != "" && email != oldEmail){
		$.getJSON(ctx+"/personalCenterUser/checkEmail",{email:email},function(data){
			if(!data){
				$("#"+srcname+"_msg").css("color","#f00");
				$("#"+srcname+"_msg").text("邮箱已被占用！");
			}else{
				checkOk(src, true);
				checkPhone1();
			}
		});
	}else{
		checkPhone1();
	}
}

//验证手机号
function checkPhone1(){
	var src = document.getElementById("phone");
	var srcname = src.id;
	var phone=src.value;
	if(phone != null && phone != "" && phone != oldPhone){
		$.getJSON(ctx+"/personalCenterUser/checkPhone",{phone:phone},function(data){
			if(!data){
				$("#"+srcname+"_msg").css("color","#f00");
				$("#"+srcname+"_msg").text("手机号已被占用！");
			}else{
				checkOk(src, true);
				submitForm();
			}
		});
	}else{
		submitForm();
	}
}


//提交表单
function submitForm(){
	$.ajax({
	   type: "POST",
	   url: ctx+"/personalCenterUser/updateUserInfo",
	   data: $("#myformUserInfo").serialize(),
	   success: function(date){
		   	userInfo();
			layer.msg(date);
	   }
	});
}



//保存事件
function savedata(){
	if(chkForm()){
		checkEmail1();
	}
}
