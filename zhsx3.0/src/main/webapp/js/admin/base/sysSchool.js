var layer;
layui.use([ 'layer' ], function() {
	layer = layui.layer;
});

function addSchool() {
	LayerOpen('添加学校', '640px', '500px',ctx+'/adminBaseData/addSchool');
}

function del(id) {
	$.ajax({
		type : "POST",
		url : ctx+"/adminBaseData/delSchool",
		data : {id : id},
		success : function(data) {
			layer.msg(data,{time : 1000},function() {
				if (data == "操作成功")
					$("#myform1").submit();
			});
		}
	});
}


function ChangeState(id,isaf){
	var msg;
	if(isaf=="N"){
		msg="您确定要关闭安防功能么？";
	}
	if(isaf=="Y"){
		msg="您确定要开启安防功能么？";
	}
	if(isaf==null){
		msg="您确定要开启安防功能么？";
	};
	layer.confirm(msg, {shade: [0.2, '#FFFFFF']}, function(){
		$.ajax({
			type:"post",
			url: ctx+"/adminBaseData/updateisaf",
			data:{id:id,isaf:isaf},
			success: function(data){
				layer.msg(data,{time : 1200},function() {
					if (data == "操作成功")
						$("#myform1").submit();
				});
			}
		});
	});

}


function edit(id) {
	LayerOpen('编辑学校', '640px', '500px', ctx+'/adminBaseData/editSchool?id=' + id);
}

//全选
$("#selected-all-operation").click(function() {
	if (this.checked) {
		$("input[name='del_id']").prop("checked", true);
	} else {
		$("input[name='del_id']").prop("checked", false);
	}
});

//批量删除
function batchDel() {
	var ids = $("input[name='del_id']:checked").val();
	if (typeof (ids) != "undefined") {
		$.ajax({
			type : "POST",
			url : ctx+"/adminBaseData/batchDelSchool",
			data : $("#myform").serialize(),
			success : function(data) {
				refreshPage(data);
			}
		});
	} else {
		layer.msg("请选择要操作的对象");
	}
}

//下载模板
function download() {
	location.href = ctx+"/adminBaseData/downloadTem?filename=schoolTem.xlsx";
}

function batchAdd() {
	LayerOpen('批量添加', '640px', '450px', ctx+'/adminBaseData/batchAddSchool');
}

//搜索框键盘enter事件
$("#searchval").keyup(function(){
	if(event.keyCode == 13){
		searchSchool();
	} 
});

function searchSchool() {
	var search = $("input[name='searchval']").val();
	$("input[name='search']").val(search);
	$("#myform1").submit();
}


function refreshPage(data){
	layer.alert(data, {skin: 'layui-layer-molv',time:0}, function(index){
			$("#myform1").submit();
		});			
}