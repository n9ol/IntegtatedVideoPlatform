$(function() {
	$("#testTop").attr("class", "has-sub active");
	$("#testTopZ").attr("class", "active2");

	testpaper();
});

$(".collection").click(function() {
	if ($(this).html == "收藏") {
		$(this).html('收藏')
	}
});

// 年级查询
function selectGrade(gradeId, gradeName) {
	$("input[name='gradeName']").val(gradeName);
	agradeSubject(gradeId);
	testpaper();
}

// 科目查询
function selectSubjects(subiectName, src) {
	$("#hhh li").removeClass("shai_kuang");
	src.setAttribute("class", "shai_kuang");
	$("input[name='subiectName']").val(subiectName);
	testpaper();
}

// 版本查询
function selectVersion(version) {
	$(".lll li").removeClass("shai_kuang");
	$("input[name='version']").val(version);
	testpaper();
}

// 地区查询
// 地区搜索事件
function updateData(Cityname) {
	$("input[name='p']").val(1);
	$("input[name='area']").val(Cityname);
	testpaper();
}

// 取消地区筛选
$(".quxiao").click(function() {
	$(".area-danxuan").text("选择地区");
	$("input[name='p']").val(1);
	$("input[name='area']").val(null);
	testpaper();
});


// 获得科目
function agradeSubject(id) {
	$("#hhh").empty();
	$("#hhh").append("<li class='shai_kuang' onclick=selectSubjects('',this)>全部</li>");
	$.getJSON(ctx + "/adminBaseData/getSubjects", {gradeId : id}, function(json) {
		for (var int = 0; int < json.length; int++) {
			var arrayJson = json[int];
			$("#hhh").append("<li onclick=selectSubjects('" + arrayJson.id + "','"+ arrayJson.value + "',this)>" + arrayJson.value+ "</li>");
		}
	});
}


function testpaper() {
	$.ajax({
		type : "POST",
		url : ctx + "/test/testpaper",
		data : $("#form").serialize(),
		success : function(msg) {
			$(".testpaper").html(msg);
		}
	});
}

// 收藏试卷
function collection(id) {
	if (principal != "") {
		$.ajax({
			type : "POST",
			url : ctx + "/sysHistory/insterSysHistory",
			data : {
				pubType : "E",
				pubFlag : "C",
				pubId : id
			},
			success : function(msg) {
				testpaper();
				layer.msg(msg);
			}
		});
	} else {
		layer.msg("请先登录!");
	}
}
// 取消收藏
function cancelCollection(id) {
	if (principal != "") {
		$.ajax({
			type : "POST",
			url : ctx + "/sysHistory/delSysHistory",
			data : {
				pubType : "E",
				pubFlag : "C",
				pubId : id
			},
			success : function(msg) {
				testpaper();
				layer.msg(msg);
			}
		});
	} else {
		layer.msg("请先登录!");
	}
}

// 参加测试
function testDetail(id, times) {

	if (principal != "") {

		$.ajax({
			type : "post",
			url : ctx + "/testwebtestquestion/verificationtType",
			data : {},
			success : function(msg) {
				if (msg == "操作成功") {
					layer.msg("该角色不能参加测试!");
				} else {
					$("input[name='testId']").val(id);
					$("input[name='times']").val(times);
					$("#myformkaoshi").submit();
				}
			}
		});
		
	} else {
		layer.msg("请先登录!");
	}

}




//搜索框键盘enter事件
$("#searchval").keyup(function(){
	if(event.keyCode == 13){
		search();
	} 
});
function search(){
	var search=$("#searchval").val();
	$("input[name='search']").val(search);
	testpaper();
}