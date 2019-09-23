$(function(){
	if(type == "G"){
		$("#dianboTop").attr("class","has-sub active");
		$("#zbTopKG").attr("class","active2");
	}else if(type == "A"){
		$("#zbTop").attr("class","has-sub active");
		$("#zbTopKA").attr("class","active2");
	}
});

var layer;
layui.use(['form', 'layer'], function() {
	layer = layui.layer;
	var form = layui.form();
	
	form.on('select(province)', function(data){
		$("#city").empty();
		$("#city").append('<option value="">请选择市</option>');
		$("#county").empty();
		$("#county").append("<option value=''>请选择区（县）</option>");
		$.getJSON(ctx+"/adminBaseData/getCity", { provinceId: data.value}, function(json){
			for (var int = 0; int < json.length; int++) {
				var arrayJson=json[int];
				$("#city").append('<option value="'+arrayJson.id+'">'+arrayJson.value+'</option>');
			}
			form.render('select');
		}); 
	});
	
	form.on('select(city)', function(data){
		$("#county").empty();
		$("#county").append("<option value=''>请选择区（县）</option>");
		$.getJSON(ctx+"/adminBaseData/getCounty", { cityId: data.value}, function(json){
 			for (var int = 0; int < json.length; int++) {
				var arrayJson=json[int];
				$("#county").append('<option value="'+arrayJson.id+'">'+arrayJson.value+'</option>');
 			}
 			form.render('select');
		}); 
	});
	
	form.on('select(county)', function(data){
		$("#school").empty();
		$("#school").append("<option value=''>请选择学校</option>");
		$.getJSON(ctx+"/getSchoolByCountyId", { countyId: data.value}, function(json){
 			for (var int = 0; int < json.length; int++) {
				var arrayJson=json[int];
				$("#school").append('<option value="'+arrayJson.id+'">'+arrayJson.schoolName+'</option>');
 			}
 			form.render('select');
		}); 
	});
	
	form.on('select(school)', function(data){
		$("#classroom").empty();
		$("#classroom").append("<option value=''>请选择教室</option>");
		$.getJSON(ctx+"/getClassRoomBySchoolId", { schoolId: data.value}, function(json){
 			for (var int = 0; int < json.length; int++) {
				var arrayJson=json[int];
				$("#classroom").append('<option value="'+arrayJson.id+'">'+arrayJson.className+'</option>');
 			}
 			form.render('select');
		}); 
	});
	
	form.on('select(classroom)', function(data){
		var schoolId = $("#school").find("option:selected").val();
		getSchedule(schoolId, data.value);
	});
	
});

//获得课程表
function getSchedule(schoolId,classId){
	$.ajax({
	   type: "POST",
	   url: ctx+"/online/getSchedule",
	   data: {schoolId:schoolId,classId:classId,type:type},
	   success: function(msg){
		   $(".zhong_right").html(msg);
	   }
	});
}

//观看视频
function watchVidoe(id,type,isGoClass){
	if(isGoClass === "1"){
		switch (type) {
		case 'A':
			window.open(ctx+"/online/qualityPlay?id="+id,"_blank");
			break;
		case 'G':
			window.open(ctx+"/online/inManyPlay?id="+id,"_blank");
			break;
		}
	}
}

//弹出辅讲教师信息
function alertf(id,type,src){
	if(type === 'G'){
		$(src).next("div.plan_course_info").find("div.plan_course_otherteach_mid").empty();
		$.getJSON(ctx+"/getLoFscheduleByScheduleId", { id: id}, function(json){
 			for (var int = 0; int < json.length; int++) {
				var arrayJson=json[int];
				var aaa = '';
				aaa += '<span class="blue">辅讲教师：</span></br>';
				aaa += '<p class="fuzi">';
					aaa += '<span id="plan_course_teach_name">'+arrayJson.userName+'</span>';
					aaa += '<span id="plan_course_teach_contact">'+arrayJson.schoolName+'</span>';
				aaa += '</p>';
				$(src).next("div.plan_course_info").find("div.plan_course_otherteach_mid").append(aaa);
 			}
		});
	}
}