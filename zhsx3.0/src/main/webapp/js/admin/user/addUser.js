var layer;
layui.use([ 'layer','form'], function() {
	layer = layui.layer;
	var form = layui.form;
	
	//角色选择监听
	 form.on('radio(lType)', function(data){
		$("#schoolId_msg").text("");
	 }); 
	 form.on('radio(rType)', function(data){
		$("#schoolId_msg").text("");
	 }); 
	 form.on('radio(tType)', function(data){
		 $("#schoolId_msg").text("* 必填");
	 });
	 form.on('radio(sType)', function(data){
		 $("#schoolId_msg").text("* 必填");
	 });
	 
	 
	 form.on('select(province)', function(data){
		$("#cityId").empty();
		$("#cityId").append('<option value="">请选择市</option>');
		$("#countyId").empty();
		$("#countyId").append("<option value=''>请选择县/区</option>");
		$("#schoolId").empty();
		$("#schoolId").append("<option value=''>请选择学校</option>");
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
		$("#schoolId").empty();
		$("#schoolId").append("<option value=''>请选择学校</option>");
		$.getJSON(ctx+"/adminBaseData/getCounty", { cityId: data.value}, function(json){
 			for (var int = 0; int < json.length; int++) {
				var arrayJson=json[int];
				$("#countyId").append('<option value="'+arrayJson.id+'">'+arrayJson.value+'</option>');
 			}
 			form.render('select');
		}); 
	});
	
	
	form.on('select(county)', function(data){
		$("#schoolId").empty();
		$("#schoolId").append("<option value=''>请选择学校</option>");
		$.getJSON(ctx+"/getSchoolByCountyId", { countyId: data.value}, function(json){
 			for (var int = 0; int < json.length; int++) {
				var arrayJson=json[int];
				$("#schoolId").append('<option value="'+arrayJson.id+'">'+arrayJson.schoolName+'</option>');
 			}
 			form.render('select');
		}); 
	});
	
	
	
	
	
});


//验证密码是否一致
function checkPasswordFit(src){
	var srcname = src.id;
	var srctitle = src.title;
	var password1=src.value;
	var password=$("#password").val();
	if(password1!=password){
		$("#"+srcname+"_msg").css("color","#f00");
		$("#"+srcname+"_msg").text(srctitle+"不一致");
		return false;
	}
	return true;		
}


//验证账号是否唯一
  function checkUserCodeSole(src){
	   var srcname = src.id;
	   var userCode=src.value;
		$.getJSON(ctx+"/user/checkUserCodeSole",{userCode:userCode},function(data){
			if(!data){
				$("#"+srcname+"_msg").css("color","#f00");
				$("#"+srcname+"_msg").text("账号已存在！");
			}else{
				checkOk(src, true);
			}
		});
   }
	
	
	
   
//表单提交验证
function chkForm() {
	var isok = true;
	var userCode = document.getElementById("userCode");
	if (!(checkString(userCode)&&checkSymbol(userCode)&&checkUnderline(userCode)&&checkAllNum(userCode)&&checkDigit(userCode)&&checkChinese(userCode))) {
		isok = false;
	} 
	var nickName = document.getElementById("nickName");
	if(!(checkOk(nickName,checkString(nickName)))){
		isok = false;
	}

	var password = document.getElementById("password");
	if(!(checkOk(password,checkString(password)&&checkDigit1(password)))){
		isok = false;
	}

	var password1 = document.getElementById("password1");
	if(!(checkOk(password1,checkString(password1)&&checkPasswordFit(password1)))){
		isok = false;
	}
	var userType=$("input[name='userType']:checked").val();  
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
	return isok;
}

function submitForm(){
	if(chkForm()){
		var src = document.getElementById("userCode");
		var srcname = src.id;
		var userCode=src.value;
		$.getJSON(ctx+"/user/checkUserCodeSole",{userCode:userCode},function(data){
			if(!data){
				$("#"+srcname+"_msg").css("color","#f00");
				$("#"+srcname+"_msg").text("账号已存在！");
			}else{
				checkOk(src, true);
				$.ajax({
					type : "POST",
					url : ctx+"/user/insertUser",
					data : $("#form").serialize(),
					success : function(data) {
						closeiframe(window.name);
						refresh();
						parent.layer.msg(data,{time:2000});
					}
				});
			}
		});
	}
}
