var layer;

layui.use([ 'layer', 'form', 'upload' ], function() {
	layer = layui.layer;
	var form = layui.form();

	// 获得科目
	form.on('select(grade)', function(data) {
		$("#subjectId").empty();
		$("#subjectId").append('<option value="">请选择科目</option>');
		var gradeId = $(data.elem).find("option:selected").attr("id");
		$.getJSON(ctx + "/adminBaseData/getSubjects", {
			gradeId : gradeId
		}, function(json) {
			for (var int = 0; int < json.length; int++) {
				var arrayJson = json[int];
				$("#subjectId").append(
						'<option value="' + arrayJson.value + '">'
								+ arrayJson.value + '</option>');
			}
			form.render('select');
		});
	});

	// 节次选择监听 - 设置 上课节次ID (_bak)
	form.on('select(sectionofday)', function(data) {
		var bak = $(data.elem).find("option:selected").attr("id");
		$("input[name='bak']").val(bak);
	});

	// 上课模式监听
	form.on('radio(vtype)', function(data) {
		var type = data.value;
		if (type == "G") {
			$(".inMany").css("display", "block");
			$(".onlinePg").css("display", "block");
		} else if (type == "A" || type == "Z") {
			$(".inMany").css("display", "none");
			$(".onlinePg").css("display", "none");
		}
	});

	form.on('select(school1)', function(data) {
		$("#f1cid").empty();
		$("#f1cid").append('<option value="">请选择教室</option>');
		$.getJSON(ctx + "/getClassRoomBySchoolId", {
			schoolId : data.value
		}, function(json) {
			for (var int = 0; int < json.length; int++) {
				var arrayJson = json[int];
				$("#f1cid").append(
						'<option value="' + arrayJson.id + '">'
								+ arrayJson.className + '</option>');
			}
			form.render('select');
		});

		$("#f1uid").empty();
		$("#f1uid").append('<option value="">请选择教师</option>');
		$.getJSON(ctx + "/getUserBySchoolId", {
			schoolId : data.value
		}, function(json) {
			for (var int = 0; int < json.length; int++) {
				var arrayJson = json[int];
				$("#f1uid").append(
						'<option value="' + arrayJson.id + '">'
								+ arrayJson.nickName + '</option>');
			}
			form.render('select');
		});
	});

	form.on('select(school2)', function(data) {
		$("#f2cid").empty();
		$("#f2cid").append('<option value="">请选择教室</option>');
		$.getJSON(ctx + "/getClassRoomBySchoolId", {
			schoolId : data.value
		}, function(json) {
			for (var int = 0; int < json.length; int++) {
				var arrayJson = json[int];
				$("#f2cid").append(
						'<option value="' + arrayJson.id + '">'
								+ arrayJson.className + '</option>');
			}
			form.render('select');
		});

		$("#f2uid").empty();
		$("#f2uid").append('<option value="">请选择教师</option>');
		$.getJSON(ctx + "/getUserBySchoolId", {
			schoolId : data.value
		}, function(json) {
			for (var int = 0; int < json.length; int++) {
				var arrayJson = json[int];
				$("#f2uid").append(
						'<option value="' + arrayJson.id + '">'
								+ arrayJson.nickName + '</option>');
			}
			form.render('select');
		});
	});

	form.on('select(school3)', function(data) {
		$("#f3cid").empty();
		$("#f3cid").append('<option value="">请选择教室</option>');
		$.getJSON(ctx + "/getClassRoomBySchoolId", {
			schoolId : data.value
		}, function(json) {
			for (var int = 0; int < json.length; int++) {
				var arrayJson = json[int];
				$("#f3cid").append(
						'<option value="' + arrayJson.id + '">'
								+ arrayJson.className + '</option>');
			}
			form.render('select');
		});

		$("#f3uid").empty();
		$("#f3uid").append('<option value="">请选择教师</option>');
		$.getJSON(ctx + "/getUserBySchoolId", {
			schoolId : data.value
		}, function(json) {
			for (var int = 0; int < json.length; int++) {
				var arrayJson = json[int];
				$("#f3uid").append(
						'<option value="' + arrayJson.id + '">'
								+ arrayJson.nickName + '</option>');
			}
			form.render('select');
		});
	});

	form.on('select(classRoom)', function(data) {
		var f1cval = $("#f1cid").find("option:selected").val();
		var f2cval = $("#f2cid").find("option:selected").val();
		var f3cval = $("#f3cid").find("option:selected").val();

		var domId = $(data.elem).attr("id");
		if (domId === "f1cid") {
			if (data.value === f2cval || data.value === f3cval) {
				$("#fs_msg").css("color", "#f00");
				$("#fs_msg").text("辅讲教室不能相同!");
			}
		} else if (domId === "f2cid") {
			if (data.value === f1cval || data.value === f3cval) {
				$("#fs_msg").css("color", "#f00");
				$("#fs_msg").text("辅讲教室不能相同!");
			}
		} else {
			if (data.value === f1cval || data.value === f2cval) {
				$("#fs_msg").css("color", "#f00");
				$("#fs_msg").text("辅讲教室不能相同!");
			}
		}
	});

});

// 验证是否为空
function checkString(src) {
	var srcname = src.id;
	var srctitle = src.title;
	if (src.value == "") {
		$("#" + srcname + "_msg").css("color", "#f00");
		$("#" + srcname + "_msg").text(srctitle + "不能空！");
		return false;
	}
	return true;
}

// 表单验证
function chkForm() {
	var isok = true;
	var subjectId = $("#subjectId").find("option:selected").val();
	if (subjectId == "") {
		isok = false;
		$("#gradeId_msg").css("color", "#f00");
		$("#gradeId_msg").text("请选择年级科目!");
	} else {
		$("#gradeId_msg").css("color", "#060");
		$("#gradeId_msg").html(
				"<img src=" + ctx + "/images/chk_ok.jpg border=0>");
	}

	var userId = $("#userId").find("option:selected").val();
	if (userId == "") {
		isok = false;
		$("#userId_msg").css("color", "#f00");
		$("#userId_msg").text("请选择主讲教师!");
	} else {
		$("#userId_msg").css("color", "#060");
		$("#userId_msg").html(
				"<img src=" + ctx + "/images/chk_ok.jpg border=0>");
	}

	var startWeek = $("#startWeek").find("option:selected").val();
	var endWeek = $("#endWeek").find("option:selected").val();
	if (parseInt(startWeek) > parseInt(endWeek)) {
		isok = false;
		$("#week_msg").css("color", "#f00");
		$("#week_msg").text("课程结束周必须大于开始周 ！");
	} else {
		$("#week_msg").css("color", "#060");
		$("#week_msg").html("<img src=" + ctx + "/images/chk_ok.jpg border=0>");
	}

	var dayofweek = $("#dayofweek").find("option:selected").val();
	if (dayofweek === "") {
		isok = false;
		$("#dayofweek_msg").css("color", "#f00");
		$("#dayofweek_msg").text("请选择星期!");
	} else {
		$("#dayofweek_msg").css("color", "#060");
		$("#dayofweek_msg").html(
				"<img src=" + ctx + "/images/chk_ok.jpg border=0>");
	}

	var sectionofday = $("#sectionofday").find("option:selected").val();
	if (sectionofday === "") {
		isok = false;
		$("#sectionofday_msg").css("color", "#f00");
		$("#sectionofday_msg").text("请选择节次!");
	} else {
		$("#sectionofday_msg").css("color", "#060");
		$("#sectionofday_msg").html(
				"<img src=" + ctx + "/images/chk_ok.jpg border=0>");
	}

	var type = $("input[name='type']:checked").val();
	if (type == "G") {
		var f1sid = $("#f1sid").find("option:selected").val();
		var f1cid = $("#f1cid").find("option:selected").val();
		var f1uid = $("#f1uid").find("option:selected").val();
		if (f1sid == "" || f1cid == "" || f1uid == "") {
			isok = false;
			$("#fs_msg").css("color", "#f00");
			$("#fs_msg").text("请补充完整辅讲教室1的信息");
		} else {
			$("#fs_msg").css("color", "#060");
			$("#fs_msg").html(
					"<img src=" + ctx + "/images/chk_ok.jpg border=0>");
		}

		var f2sid = $("#f2sid").find("option:selected").val();
		var f2cid = $("#f2cid").find("option:selected").val();
		var f2uid = $("#f2uid").find("option:selected").val();
		if (f2sid == "" || f2cid == "" || f2uid == "") {
			$(".ftwo").empty();
		} else if (f1cid === f2cid) {
			isok = false;
			$("#fs_msg").css("color", "#f00");
			$("#fs_msg").text("辅讲教室不能相同!");
		}

		var f3sid = $("#f3sid").find("option:selected").val();
		var f3cid = $("#f3cid").find("option:selected").val();
		var f3uid = $("#f3uid").find("option:selected").val();
		if (f3sid == "" || f3cid == "" || f3uid == "") {
			$(".fthree").empty();
		} else if (f1cid === f3cid) {
			isok = false;
			$("#fs_msg").css("color", "#f00");
			$("#fs_msg").text("辅讲教室不能相同!");
		}

		if (f2sid != "" && f2cid != "" && f2uid != "" && f3sid != ""
				&& f3cid != "" && f3uid != "") {
			if (f2cid === f3cid) {
				isok = false;
				$("#fs_msg").css("color", "#f00");
				$("#fs_msg").text("辅讲教室不能相同!");
			}
		}
	}
	if (type == "A" || type == "Z") {
		$(".inMany").empty();
	}
	return isok;
}

// 表单提交
function submitForm() {
	if (chkForm()) {
		$.ajax({
			type : "POST",
			url : ctx + "/adminCourseArrangement/insertScheduleBatch",
			data : $("#form").serialize(),
			success : function(data) {
				closeiframe(window.name);
				parent.$(".layui-show iframe")[0].contentWindow.refreshPage(data);
			}
		});
	}
}