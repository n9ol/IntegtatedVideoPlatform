var layer;
layui.use("layer",function(){
	layer = layui.layer;
});

$(function(){
	getCourResData();
});


$(".box_nav_pai").mouseover(function() {
	$(".sDwvAgb").show();
	$(".box_nav_pai").mouseleave(function() {
		$(".sDwvAgb").hide();
	});

});

//全选效果
$(".name input").click(function() {
	var className = $(this).attr("class");
	
	if (this.checked) {
		$("input[name='del_id']").prop("checked", true);
		$(".zongTiao1").addClass("border_color");
		if(className === 'Tall'){
			$(".yiru").attr("class","clear zongTiao yiru mouseover pcicon");
			$("span.EOGexf1").css("display","block");
			$("span.EOGexf1").find("img").attr("src", ctx+"/img/select_fill.png");
		}
	} else {
		$("input[name='del_id']").prop("checked", false);
		$(".zongTiao1").removeClass("border_color")
		if(className === 'Tall'){
			$(".yiru").attr("class","clear zongTiao yiru");
			$("span.EOGexf1").css("display","none");
		}
	}
});

$(".span_input input").click(function(){
	if(!(this.checked)){
		$(".name input").prop("checked", false);
	}else{
		var isok = true;
		$(".span_input input").each(function(){
			if(!(this.checked)){
				isok = false;
			}
		});
		if(isok){
			$(".name input").prop("checked", true);
		}
	}
});


//切换模式
//点击事件
$(".box_nav_bian img").click(function() {
	var pageType = $("#pageType").val();
	if(pageType === "square"){
		pageType = "cross"
	}else{
		pageType = "square"
	}
	styleTransformation(pageType);
});

//切换模式
function styleTransformation(pageType){
	if (pageType === 'square') {
		$("#pageType").val("square");
		$(".box_nav_bian img").attr("src", ctx+"/img/one_way_list.png");
		$(".size").hide();
		$(".time").hide();
		$(".text1").html("全选");
		$(".name input").attr("class","Tall");
		$(".rkwXRO").hide();
		$(".nq8L3J").hide();
		$(".showBianji").hide();
		$(".span_input").hide();
		$(".cEefyz1").attr("class", "cEefyz");
		$(".emhb27mw1").attr("class", "emhb27mw");
		$(".file-name1").attr("class", "file-names");
		$(".EOGexf1").show();
		$(".yiru").each(function(){
			var checkboxs = $(this).find("span.span_input").find("input").is(":checked");
			if(checkboxs){
				$(this).attr("class", "clear zongTiao yiru mouseover pcicon");
				$(this).find("span.EOGexf1").find("img").attr("src", ctx+"/img/select_fill.png");
			}else{
				$(this).attr("class", "clear zongTiao yiru");
				$(this).find("span.EOGexf1").find("img").attr("src", "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==");
			}  
		});
	} else {
		$("#pageType").val("cross");
		$(".box_nav_bian img").attr("src", ctx+"/img/two_way_list.png");
		$(".size").show();
		$(".time").show();
		$(".text1").html("文件名");
		$(".name input").attr("class","Oall");
		$(".rkwXRO").show();
		$(".nq8L3J").show();
		$(".showBianji").show();
		$(".span_input").show();
		$(".cEefyz").attr("class", "cEefyz1");
		$(".emhb27mw").attr("class", "emhb27mw1");
		$(".file-names").attr("class", "file-name1");
		$(".EOGexf1").hide();
		$(".yiru").each(function(){
			var checkboxs = $(this).find("span.span_input").find("input").is(":checked");
			if(checkboxs){
				$(this).attr("class", "clear zongTiao1 yiru border_color");
			}else{
				$(this).attr("class", "clear zongTiao1 yiru");
			}  
		});
	}
}

//隐藏上传框
$(".dialog-min").click(function() {
	$(".dialog").animate({"bottom": "-375"}, 500);
	$(".dialog-header").hide();
	$(".dialog-min-header").show();
});

//显示上传框
$(".dialog-back").click(function(){
	$(".dialog").animate({"bottom": "0"}, 500);
	$(".dialog-header").show();
	$(".dialog-min-header").hide();
});

//搜索框键盘enter事件
$("#searchval").keyup(function(){
	if(event.keyCode == 13){
		var search = $("#searchval").val();
		$("input[name='search']").val(search);
		$("input[name='p']").val(1);
		getCourResData();
	} 
});

//搜索点击事件
$("#search").click(function(){
	var search = $("#searchval").val();
	$("input[name='search']").val(search);
	$("input[name='p']").val(1);
	getCourResData();
});

//排序点击事件
$(".aaaa").click(function(){
	$(".aaaa").removeAttr("style");
	$(this).css("backgroundColor","#f2f2f2");
	var sortord = $(this).attr("id");
	$("input[name='sortord']").val(sortord);
	$("input[name='p']").val(1);
	getCourResData();
});

//删除资源
function del(id){
	layer.confirm('你确定要删除？',{shade: [0.2, '#FFFFFF']},function(){
		$.ajax({
			type : "POST",
			url : ctx+"/adminCourRes/delCourRes",
			data : {id:id},
			success : function(data) {
				layer.msg(data,{time : 1500},function() {
					if (data == "操作成功"){
						getCourResData();
					}
				});
			}
		});
	});
}

//进入编辑页面
function edit(id) {
	LayerOpen('编辑', '640px', '530px', ctx+'/adminCourRes/editCourRes?id=' + id+"&type=Q");
}

//下载资源
function downloadRes(id){
	location.href = ctx+"/adminCourRes/downloadCourRes?id="+id;
}

//批量下载文件(未实现,-可尝试在后台将文件打成一个压缩包提供下载)
function batchDownload(){
	var ids = $("input[name='del_id']:checked").val();
	if (typeof (ids) != "undefined") {
		var dow_id = new Array();
		var i = 0;
		$("input[name='del_id']:checkbox:checked").each(function(){
			downloadRes($(this).val());
			dow_id[i] = ctx+"/adminCourRes/downloadCourRes?id="+$(this).val();
			i++;
		});
	} else {
		layer.msg("请选择要操作的对象");
	}
}

//获得课件资源
function getCourResData(){
	$.ajax({
		type : "POST",
		url : ctx+"/adminCourRes/getCourRes",
		data : $("#myform1").serialize(),
		success : function(data) {
			$("#resData").html(data);
			var pageType = $("#pageType").val();
			styleTransformation(pageType);
		}
	});
}

//批量删除资源
function batchDel(){
	var ids = $("input[name='del_id']:checked").val();
	if (typeof (ids) != "undefined") {
		layer.confirm('你确定要删除？',{shade: [0.2, '#FFFFFF']},function(){
			$.ajax({
				type : "POST",
				url : ctx+"/adminCourRes/batchDelCourRes",
				data : $("#myform").serialize(),
				success : function(data) {
					layer.msg(data,{time : 1500},function() {
						if (data == "操作成功"){
							getCourResData();
						}
					});
				}
			});
		});
	} else {
		layer.msg("请选择要操作的对象");
	}
}

//批量修改资源基本信息
function batchEdit(){
	var ids = $("input[name='del_id']:checked").val();
	if (typeof (ids) != "undefined") {
		var edit_id = new Array();
		var i = 0;
		$("input[name='del_id']:checkbox:checked").each(function(){
			edit_id[i] = $(this).val();
			i++;
		});
		LayerOpen('编辑', '640px', '450px', ctx+'/adminCourRes/batchEdit?edit_id='+edit_id);
	} else {
		layer.msg("请选择要操作的对象");
	}
}