$.idcode.setCode();
$("#btns").click(function() {
	var IsBy = $.idcode.validateCode();
	if(!IsBy){
		$(".verificationCode").text("验证码错误");
	}else{
		validationUserCode();
	}
});

//验证账号
function validationUserCode(){
	var userCode = $("#userCode").val();
	$.ajax({
		   type: "POST",
		   url: ctx+"/validationUserCode",
		   data: {userCode:userCode},
		   dataType: "json",
		   success: function(json){
			   if(json.isok){
				   $("#recordUserCode").val(userCode);
				   forgetPwd2(json.userEmail);
			   }else{
				   $(".verificationCode").text("账号不存在！"); 
			   }
		   }
		});
}


//进入邮箱验证
function forgetPwd2(userEmail){
	$.ajax({
	   type: "POST",
	   url: ctx+"/forgetPwd2",
	   data: {userEmail:userEmail},
	   success: function(msg){
		   $(".for-liucheng").find("div").eq(1).attr("class","liulist for-cur");
		   $(".liutextbox").find("div").eq(1).attr("class","liutext for-cur");
		   $(".forget-pwd").html(msg);
	   }
	});
}

//获得邮箱验证码
function getEmailCode(src){
	window.setTimeout(function(){return timer(src)},500);
	var userCode = $("#recordUserCode").val();
	$.ajax({
	   type: "POST",
	   url: ctx+"/sendEmailCode",
	   data: {userCode:userCode},
	   success: function(msg){
		   $(".verificationCode").text(msg); 
	   }
	});
}


//邮箱发送按钮效果
var wait = 60;
function timer(src){
  if (wait === 0) {
	   src.removeAttribute("disabled");
	   $(src).text("重新发送");
	   wait = 60;
  } else { 
	   src.setAttribute("disabled", true);
	   wait--;
	   $(src).text(wait + "秒重新发送");
	   window.setTimeout(function(){return timer(src)},1000);
  }
}


//验证邮箱验证码
function verifyEmailCode(){
	var emailCode = $("#emailCode").val();
	if(emailCode === ""){
		$(".verificationCode").text("验证码不能为空"); 
	}else{
		$.ajax({
		   type: "POST",
		   url: ctx+"/verifyEmailCode",
		   data: {emailCode:emailCode},
		   dataType: "json",
		   success: function(data){
			   if(data){
				   forgetPwd3();
			   }else{
				   $(".verificationCode").text("验证码错误"); 
			   }
		   }
		});
	}
}

//进入重置密码页面
function forgetPwd3(){
	$.ajax({
	   type: "POST",
	   url: ctx+"/forgetPwd3",
	   success: function(msg){
		   $(".for-liucheng").find("div").eq(2).attr("class","liulist for-cur");
		   $(".liutextbox").find("div").eq(2).attr("class","liutext for-cur");
		   $(".forget-pwd").html(msg);
	   }
	});
}


//验证表单
function chkForm(){
	var isok = true;
	var password = document.getElementById("newPasseord");
	if(!(checkOk(password,checkString(password)&&checkDigit1(password)))){
		isok = false;
	}
	var newPasseord = $("#newPasseord").val();
	var affirmPasseord = $("#affirmPasseord").val();
	if(newPasseord != affirmPasseord){
		$("#affirmPasseord_msg").css("color","#f00");
		$("#affirmPasseord_msg").text("两次密码不一致");
		isok = false;
	}
	return isok;
}


//重置密码
function resetPassword(){
	if(chkForm()){
		var userCode = $("#recordUserCode").val();
		var newPasseord = $("#newPasseord").val();
		$.ajax({
		   type: "POST",
		   url: ctx+"/resetPassword",
		   data: {userCode:userCode,newPasseord:newPasseord},
		   success: function(msg){
			   $(".for-liucheng").find("div").eq(3).attr("class","liulist for-cur");
			   $(".liutextbox").find("div").eq(3).attr("class","liutext for-cur");
			   $(".forget-pwd").html(msg);
		   }
		});
	}
}


