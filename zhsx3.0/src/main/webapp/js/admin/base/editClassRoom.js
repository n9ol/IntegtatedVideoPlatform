var layer;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	var form = layui.form();
});

// 表单验证
function chkForm() {
	var isok = true;
	var schoolId = document.getElementById("schoolId");
	if (!(checkOk(schoolId, checkString(schoolId)))) {
		isok = false;
	}
	var className = document.getElementById("className");
	if (!(checkOk(className, checkString(className)))) {
		isok = false;
	}
	var classCode = document.getElementById("classCode");
	if (!(checkOk(classCode, checkString(classCode) && checkNum(classCode)))) {
		isok = false;
	}
	var serviceIp = document.getElementById("serviceIp");
	if (!(checkOk(serviceIp, checkString(serviceIp)))) {
		isok = false;
	}
	var clientIp = document.getElementById("clientIp");
	if (clientIp.value != "") {
		if (!(checkOk(clientIp, checkString(clientIp)))) {
			isok = false;
		}
	}
	var webPort = document.getElementById("webPort");
	if (!(checkOk(webPort, checkString(webPort) && checkNum(webPort)))) {
		isok = false;
	}
	var roomId = document.getElementById("roomId");
	if (!(checkOk(roomId, checkString(roomId) && checkNum(roomId)))) {
		isok = false;
	}
	var uid = document.getElementById("uid");
	if (!(checkOk(uid, checkString(uid) && checkNum(uid)))) {
		isok = false;
	}
	return isok;
}

// 表单提交
function submitForm() {
	if (chkForm()) {
		var index = layer.load();
		var classCode = document.getElementById("classCode");
		var serviceIp = $("#serviceIp").val();
		if (oldClassCode != classCode.value) {
			$.getJSON(ctx + "/adminBaseData/checkClassCode", {
				serviceIp : serviceIp,
				classCode : classCode.value
			}, function(data) {
				if (!data) {
					$("#classCode_msg").html("<img src=" + ctx + "/images/chk_no.png  border=0>");
					$("#ipClassCode_msg").css("color", "#f00");
					$("#ipClassCode_msg").text("该直播服务器下 " + classCode.value + " 的教室编号已存在！");
					layer.close(index);
				} else {
					checkOk(classCode, true);
					updateClassRoom(index);
				}
			});
		} else {
			updateClassRoom(index);
		}
	}
}


//更新数据库
function updateClassRoom(index) {
	$.ajax({
		type : "POST",
		url : ctx + "/adminBaseData/updateClassRoom",
		data : $("#form").serialize(),
		success : function(data) {
			layer.close(index);
			layer.msg(data, {
				time : 2000
			}, function() {
				if (data === "操作成功") {
					closeiframe(window.name);
					refresh();
				}
			});
		}
	});
}


// //验证
// function checkIpRoomUid(){
// var serviceIp = $("#serviceIp").val();
// var roomId = $("#roomId").val();
// var uid = document.getElementById("uid");
// if(serviceIp != oldServiceIp || uid.value != oldUid || roomId != oldRoomId){
// $.getJSON(ctx+"/adminBaseData/checkIpRoomUid",{serviceIp:serviceIp,roomId:roomId,uid:uid.value},function(data){
// if(!data){
// $("#ipRoomUid_msg").css("color","#f00");
// $("#ipRoomUid_msg").text("已存在与服务器ip,教学编号,用户编号三者的完成相同的教室！");
// }else{
// checkOk(uid, true);
// updateClassRoom();
// }
// });
// }else{
// updateClassRoom();
// }
// }


