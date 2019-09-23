var layer;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	var form = layui.form();
});

// 验证教室编号是否存在
function classCodeIsSole(src) {
	var index = layer.load();
	var srcname = src.id;
	var classCode = src.value;
	var serviceIp = $("#serviceIp").val();
	$.ajax({
		type : "GET",
		url : ctx + "/adminBaseData/checkClassCode",
		data : {
			serviceIp : serviceIp,
			classCode : classCode
		},
		cache : false,
		dataType : "json",
		success : function(data) {
			if (!data) {
				 $("#"+srcname+"_msg").html("<img src="+ctx+"/images/chk_no.png  border=0>");
				 $("#ipClassCode_msg").css("color","#f00");
				 $("#ipClassCode_msg").text("该直播服务器下 " + classCode + " 的教室编号已存在！");
				layer.close(index);
			} else {
				checkOk(src, true);
				insertClassRoom();
			}
		}
	});
}

// //验证 服务器ip,教学编号,用户编号
// function checkIpRoomUid(src){
// var srcname = src.id;
// var serviceIp=$("#serviceIp").val();
// var roomId=$("#roomId").val();
// var uid=src.value;
// $.getJSON(ctx+"/adminBaseData/checkIpRoomUid",{serviceIp:serviceIp,roomId:roomId,uid:uid},function(data){
// if(!data){
// res=1;
// $("#ipRoomUid_msg").css("color","#f00");
// $("#ipRoomUid_msg").text("注意：已存在与服务器ip,教学编号,用户编号三者的完成相同的教室！");
// }else{
// res=0;
// checkOk(src, true);
// }
// });
//    
// if(res==1){
// return false;
// }
// return true;
// }

// 表单验证
function chkForm() {
	var isok = true;
	var schoolId = $("#schoolId").find("option:selected").val();
	if (schoolId == "") {
		isok = false;
		$("#schoolId_msg").css("color", "#f00");
		$("#schoolId_msg").text("请选择学校!");
	} else {
		$("#schoolId_msg").css("color", "#060");
		$("#schoolId_msg").html("<img src='" + ctx + "/images/chk_ok.jpg' border=0>");
	}

	var uid = document.getElementById("uid");
	var className = document.getElementById("className");
	var classCode = document.getElementById("classCode");
	var serviceIp = document.getElementById("serviceIp");
	var webPort = document.getElementById("webPort");
	var roomId = document.getElementById("roomId");
	var clientIp = document.getElementById("clientIp");

	if (!checkString(uid)) {
		isok = false;
	} else if (!checkString(className)) {
		isok = false;
	} else if (!(checkString(classCode) && checkNum(classCode))) {
		isok = false;
	} else if (!checkString(serviceIp)) {
		isok = false;
	} else if (!(checkString(webPort) && checkNum(webPort))) {
		isok = false;
	} else if (!(checkString(roomId) && checkNum(roomId))) {
		isok = false;
	} else if (clientIp.value != "") {
		isok = checkIpPort(clientIp);
	}
	return isok;
}

// 表单提交
function submitForm() {
	if (chkForm()) {
		var classCode = document.getElementById("classCode");
		classCodeIsSole(classCode);
	} 
}


function insertClassRoom(){
	$.ajax({
		type : "POST",
		url : ctx + "/adminBaseData/insertClassRoom",
		data : $("#form").serialize(),
		success : function(data) {
			closeiframe(window.name);
			refresh();
			parent.layer.msg(data, {
				time : 2000
			});
		}
	});
}





