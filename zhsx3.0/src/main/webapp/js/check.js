//失去焦点验证
function checkOk(src, isok) {
	var srcname = src.name;
	if (isok) {
		$("#" + srcname + "_msg").css("color", "#060");
		$("#" + srcname + "_msg").html(
				"<img src=" + ctx + "/images/chk_ok.jpg  border=0>");
	}
	return isok;
}

// 验证是否为空
function checkString(src) {
	var srcname = src.name;
	var srctitle = src.title;
	if (src.value == "") {
		$("#" + srcname + "_msg").css("color", "#f00");
		$("#" + srcname + "_msg").text(srctitle + "不能为空值！");
		return false;
	}
	return true;
}

// 验证是否为数字
function checkNum(src) {
	var srcname = src.name;
	var srctitle = src.title;
	if (isNaN(src.value)) {
		$("#" + srcname + "_msg").css("color", "#f00");
		$("#" + srcname + "_msg").text(srctitle + "必须为数字！");
		return false;
	}
	return true;
}

// 验证是否为正整数
function checkPositiveInteger(src) {
	var srcname = src.name;
	var srctitle = src.title;
	var regexp = /^[0-9]+$/;
	if (!regexp.test(src.value)) {
		$("#" + srcname + "_msg").css("color", "#f00");
		$("#" + srcname + "_msg").text(srctitle + "必须为正整数！");
		return false;
	}
	return true;
}

// 验证不能出现特殊符号
function checkSymbol(src) {
	var srcname = src.name;
	var srctitle = src.title;
	if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(src.value)) {
		$("#" + srcname + "_msg").css("color", "#f00");
		$("#" + srcname + "_msg").text(srctitle + "不能有特殊字符！");
		return false;

	}
	return true;
}

// 验证首尾不能出现下划线
function checkUnderline(src) {
	var srcname = src.name;
	var srctitle = src.title;
	if (/(^\_)|(\__)|(\_+$)/.test(src.value)) {
		$("#" + srcname + "_msg").css("color", "#f00");
		$("#" + srcname + "_msg").text(srctitle + "首尾不能出现下划线！");
		return false;
	}
	return true;
}

// 验证不能全为数字
function checkAllNum(src) {
	var srcname = src.name;
	var srctitle = src.title;
	if (/^\d+\d+\d$/.test(src.value)) {
		$("#" + srcname + "_msg").css("color", "#f00");
		$("#" + srcname + "_msg").text(srctitle + "不能全为数字！");
		return false;
	}
	return true;
}

// 验证字符长度(3到16位)
function checkDigit(src) {
	var srcname = src.name;
	var srctitle = src.title;
	if (!(/^[\S]{3,16}$/.test(src.value))) {
		$("#" + srcname + "_msg").css("color", "#f00");
		$("#" + srcname + "_msg").text(srctitle + "必须在3到16位,且不能出现空格!");
		return false;
	}
	return true;
}

// 验证字符长度(6到16位)
function checkDigit1(src) {
	var srcname = src.name;
	var srctitle = src.title;
	if (!(/^[\S]{6,16}$/.test(src.value))) {
		$("#" + srcname + "_msg").css("color", "#f00");
		$("#" + srcname + "_msg").text(srctitle + "必须在6到16位之间!");
		return false;
	}
	return true;
}

// 验证不能有中文
function checkChinese(src) {
	var srcname = src.name;
	var srctitle = src.title;
	if (!(/^[^\u4e00-\u9fa5]{0,}$/.test(src.value))) {
		$("#" + srcname + "_msg").css("color", "#f00");
		$("#" + srcname + "_msg").text(srctitle + "不能有中文!");
		return false;
	}
	return true;
}

// 验证IP：Port
function checkIpPort(src) {
	var regexp = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\:)([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/;
	var srcname = src.name;
	var srctitle = src.title
	if (!regexp.test(src.value)) {
		$("#" + srcname + "_msg").css("color", "#f00");
		$("#" + srcname + "_msg").text(srctitle + "格式不正确!");
		return false;
	}
	return true;
}

// 验证电话号码
function checkPhone(src) {
	var regexp = new RegExp(
			"^((1[3,5,8][0-9])|(14[5,7])|(17[0,1,3,6,7,8]))\\d{8}$")
	var srcname = src.name;
	var srctitle = src.title
	if (!regexp.test(src.value)) {
		$("#" + srcname + "_msg").css("color", "#f00");
		$("#" + srcname + "_msg").text(srctitle + "格式不正确!");
		return false;
	}
	return true;
}

// 验证邮箱
function checkEmail(src) {
	var regexp = new RegExp(
			"^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")
	var srcname = src.name;
	var srctitle = src.title
	if (!regexp.test(src.value)) {
		$("#" + srcname + "_msg").css("color", "#f00");
		$("#" + srcname + "_msg").text(srctitle + "格式不正确!");
		return false;
	}
	return true;
}