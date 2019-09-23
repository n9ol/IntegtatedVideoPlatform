var layer;
layui.use("layer", function() {
	layer = layui.layer;
});

$(function() {
	getVideoResData();
});

$(".box_nav_pai").mouseover(function() {
	$(".sDwvAgb").show();
	$(".box_nav_pai").mouseleave(function() {
		$(".sDwvAgb").hide();
	});
});

// 全选效果
$(".name input").click(
		function() {
			var className = $(this).attr("class");

			if (this.checked) {
				$("input[name='del_id']").prop("checked", true);
				$(".zongTiao1").addClass("border_color");
				if (className === 'Tall') {
					$(".yiru").attr("class",
							"clear zongTiao yiru mouseover pcicon");
					$("span.EOGexf1").css("display", "block");
					$("span.EOGexf1").find("img").attr("src",
							ctx + "/img/select_fill.png");
				}
			} else {
				$("input[name='del_id']").prop("checked", false);
				$(".zongTiao1").removeClass("border_color")
				if (className === 'Tall') {
					$(".yiru").attr("class", "clear zongTiao yiru");
					$("span.EOGexf1").css("display", "none");
				}
			}
		});

$(".span_input input").click(function() {
	if (!(this.checked)) {
		$(".name input").prop("checked", false);
	} else {
		var isok = true;
		$(".span_input input").each(function() {
			if (!(this.checked)) {
				isok = false;
			}
		});
		if (isok) {
			$(".name input").prop("checked", true);
		}
	}
});

// 切换模式
// 点击事件
$(".box_nav_bian img").click(function() {
	var pageType = $("#pageType").val();
	if (pageType === "square") {
		pageType = "cross"
	} else {
		pageType = "square"
	}
	styleTransformation(pageType);
});

// 切换模式
function styleTransformation(pageType) {
	if (pageType === 'square') {
		$("#pageType").val("square");
		$(".box_nav_bian img").attr("src", ctx + "/img/one_way_list.png");
		$(".size").hide();
		$(".time").hide();
		$(".text1").html("全选");
		$(".name input").attr("class", "Tall");
		$(".rkwXRO").hide();
		$(".nq8L3J").hide();
		$(".showBianji").hide();
		$(".span_input").hide();
		$(".cEefyz1").attr("class", "cEefyz");
		$(".emhb27mw1").attr("class", "emhb27mw");
		$(".file-name1").attr("class", "file-names");
		$(".EOGexf1").show();
		$(".yiru")
				.each(
						function() {
							var checkboxs = $(this).find("span.span_input")
									.find("input").is(":checked");
							if (checkboxs) {
								$(this).attr("class",
										"clear zongTiao yiru mouseover pcicon");
								$(this).find("span.EOGexf1").find("img").attr(
										"src", ctx + "/img/select_fill.png");
							} else {
								$(this).attr("class", "clear zongTiao yiru");
								$(this)
										.find("span.EOGexf1")
										.find("img")
										.attr("src",
												"data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==");
							}
						});
	} else {
		$("#pageType").val("cross");
		$(".box_nav_bian img").attr("src", ctx + "/img/two_way_list.png");
		$(".size").show();
		$(".time").show();
		$(".text1").html("标题");
		$(".name input").attr("class", "Oall");
		$(".rkwXRO").show();
		$(".nq8L3J").show();
		$(".showBianji").show();
		$(".span_input").show();
		$(".cEefyz").attr("class", "cEefyz1 videoName");
		$(".emhb27mw").attr("class", "emhb27mw1");
		$(".file-names").attr("class", "file-name1");
		$(".EOGexf1").hide();
		$(".yiru").each(
				function() {
					var checkboxs = $(this).find("span.span_input").find(
							"input").is(":checked");
					if (checkboxs) {
						$(this).attr("class",
								"clear zongTiao1 yiru border_color");
					} else {
						$(this).attr("class", "clear zongTiao1 yiru");
					}
				});
	}
}

// 隐藏上传框
$(".dialog-min").click(function() {
	$(".dialog").animate({
		"bottom" : "-375"
	}, 500);
	$(".dialog-header").hide();
	$(".dialog-min-header").show();
});

// 显示上传框
$(".dialog-back").click(function() {
	$(".dialog").animate({
		"bottom" : "0"
	}, 500);
	$(".dialog-header").show();
	$(".dialog-min-header").hide();
});

// 搜索框键盘enter事件
$("#searchval").keyup(function() {
	if (event.keyCode == 13) {
		var search = $("#searchval").val();
		$("input[name='search']").val(search);
		$("input[name='p']").val(1);
		getVideoResData();
	}
});

// 搜索点击事件
$("#search").click(function() {
	var search = $("#searchval").val();
	$("input[name='search']").val(search);
	$("input[name='p']").val(1);
	getVideoResData();
});

// 排序点击事件
$(".aaaa").click(function() {
	$(".aaaa").removeAttr("style");
	$(this).css("backgroundColor", "#f2f2f2");
	var sortord = $(this).attr("id");
	$("input[name='sortord']").val(sortord);
	$("input[name='p']").val(1);
	getVideoResData();
});

// 删除资源
function del(id) {
	layer.confirm('你确定要删除？', {
		shade : [ 0.2, '#FFFFFF' ]
	}, function() {
		$.ajax({
			type : "POST",
			url : ctx + "/adminOffLine/delVideoRes",
			data : {
				id : id
			},
			success : function(data) {
				layer.msg(data, {
					time : 1500
				}, function() {
					if (data == "操作成功") {
						getVideoResData();
					}
				});
			}
		});
	});
}

// 进入编辑页面
function edit(id) {
	LayerOpen('编辑', '640px', '550px', ctx + '/adminOffLine/editVideoRes?id='
			+ id + "&type=Q");
}

// 获得课件资源
function getVideoResData() {
	$.ajax({
		type : "POST",
		url : ctx + "/adminOffLine/getVideoRes?isRecord=" + isRecord,
		data : $("#myform1").serialize(),
		success : function(data) {
			$("#resData").html(data);
			var pageType = $("#pageType").val();
			styleTransformation(pageType);
		}
	});
}

// 批量删除资源
function batchDel() {
	var ids = $("input[name='del_id']:checked").val();
	if (typeof (ids) != "undefined") {
		layer.confirm('你确定要删除？', {
			shade : [ 0.2, '#FFFFFF' ]
		}, function() {
			$.ajax({
				type : "POST",
				url : ctx + "/adminOffLine/batchDelVideoRes",
				data : $("#myform").serialize(),
				success : function(data) {
					layer.msg(data, {
						time : 1500
					}, function() {
						if (data == "操作成功") {
							getVideoResData();
						}
					});
				}
			});
		});
	} else {
		layer.msg("请选择要操作的对象");
	}
}

// 刷新转码进度
window.onload = funcTest;
function funcTest() {
	window.setInterval("timeRefresh()", 2000);
}
function timeRefresh() {
	$(".progressbar").each(
			function(index, item) {
				var resId = $(this).attr("id");
				var transcodingState = $(this).find("input#transcodingState")
						.val();
				if (transcodingState != "O" && transcodingState != "D") {

					$.ajax({
						type : "POST",
						url : ctx + "/adminOffLine/getVideoResProgress",
						data : {
							id : resId
						},
						dataType : "json",
						success : function(json) {
							$("#" + resId).find("input#transcodingState").val(
									json.transcodingState);
							$("#" + resId).find("div.bgBorder")
									.find("div.bgBg").css("width",
											json.progress + "%");
							var transcodingState = "等待转码" + "	" + json.progress
									+ "%";
							if (json.transcodingState === "C") {
								transcodingState = "正在转码" + "	" + json.progress
										+ "%";
							} else if (json.transcodingState === "O") {
								transcodingState = "转码完成" + "	" + json.progress
										+ "%";
							} else if (json.transcodingState === "D") {
								transcodingState = "已删除" + "	" + json.progress
										+ "%";
							}
							$("#" + resId).find("div.bgBorder").find(
									"span.progerss").text(transcodingState);
						}
					});
				}
			});
}

function downloadRes(id, filename) {
	$.ajax({
		type : "POST",
		url : ctx + "/offLine/downloadVideoResources",
		data : {
			id : id
		},
		success : function(msg) {
			location.href = fileWebPath + "/VideoFileDownloadServlet?filepath="
					+ msg + "&filename=" + filename;
		}
	});
}