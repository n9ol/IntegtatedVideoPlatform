$(function() {
	$("#zbTop").attr("class", "has-sub active");
	if (type == "G") {
		$("#zbTopG").attr("class", "active2");
	} else if (type == "A") {
		$("#zbTopA").attr("class", "active2");
	} else if (type == "Z") {
		$("#zbTopZ").attr("class", "active2");
	}
	getZbMessage();
});

// 选择年级事件
function selectGrade(gradeId, gradeName) {
	$("input[name='subjectName']").val(null);
	$("input[name='specialtyName']").val(gradeName);
	agradeSubject(gradeId);
	getZbMessage();
}

// 选择科目事件
function selectSubjects(subjectId, src) {
	$("input[name='subjectName']").val(subjectId);
	getZbMessage();
}

// 选择直播状态事件
function timeSorting(state, src) {
	$("input[name='timeSorting']").val(state);
	getZbMessage();
}

// 搜索框键盘enter事件
$("#searchval").keyup(function() {
	if (event.keyCode == 13) {
		search();
	}
});
function search() {
	$("input[name='p']").val(null);
	$("input[name='specialtyName']").val(null);
	$("input[name='subjectName']").val(null);
	var search = $("#searchval").val();
	$("input[name='search']").val(search);
	getZbMessage();
}

// 获得直播数据
function getZbMessage() {
	$.ajax({
		type : "POST",
		url : ctx + "/online/getZbMessage",
		data : $("#myform").serialize(),
		success : function(msg) {
			$(".news").html(msg);
		}
	});
}



// 进入播放页
function enterPlay(type, id, isGoClass) {
	if (isGoClass === "1") {
		switch (type) {
		case 'A':
			window.open(ctx + "/online/qualityPlay?id=" + id, "_blank");
			break;
		case 'G':
			window.open(ctx + "/online/inManyPlay?id=" + id, "_blank");
			break;
		case 'Z':
			window.open(ctx + "/online/livePlay?id=" + id, "_blank");
			break;
		}
	} else if (isGoClass === "2") {
		layer.msg("直播尚未开始!");
	}
}