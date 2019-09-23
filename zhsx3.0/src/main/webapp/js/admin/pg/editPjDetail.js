var layer;
layui.use([ 'layer', 'form' ], function() {
	layer = layui.layer;
	var form = layui.form;

	form.on('select(pgtype)', function(data) {
		$("input[name='type']").val(data.value);
		$("#pjinfoId").empty();
		$("#pjinfoId").append('<option value="">请选择评估项</option>');
		$.getJSON(ctx + "/getPjInfoByType", {
			type : data.value
		}, function(json) {
			for (var int = 0; int < json.length; int++) {
				var arrayJson = json[int];
				$("#pjinfoId").append(
						'<option value="' + arrayJson.id + '">'
								+ arrayJson.title + '</option>');
			}
			form.render('select');
		});
	});

});

// 表单提交验证
function chkForm() {
	var isok = true;
	var pjinfoId = $("#pjinfoId").find("option:selected").val();
	if (pjinfoId == "") {
		isok = false;
		$("#pjinfoId_msg").css("color", "#f00");
		$("#pjinfoId_msg").text("请选择评估项!");
	} else {
		$("#pjinfoId_msg").css("color", "#060");
		$("#pjinfoId_msg").html(
				"<img src='" + ctx + "/images/chk_ok.jpg' border=0>");
	}
	var content = document.getElementById("content");
	if (!checkString(content)) {
		isok = false;
	}
	var weight = document.getElementById("weight");
	if (!(checkString(weight) && checkNum(weight))) {
		isok = false;
	}
	var bak = document.getElementById("bak");
	if (!(checkString(bak) && checkPositiveInteger(bak))) {
		isok = false;
	}
	return isok;
}

function submitForm() {
	if (chkForm()) {
		$.ajax({
			type : "POST",
			url : ctx + "/pg/updatePjDetail",
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
}