$(function(){
	ReviewVideoResData();
});

var layer;
layui.use([ 'layer','form' ], function() {
	layer = layui.layer;
	var form = layui.form();
	
	//选择视频类型事件
	form.on('select(type)', function(data){
		  $("input[name='type']").val(data.value);
		  $("input[name='p']").val(1);
		  ReviewVideoResData();
		  
	}); 
	
	//选择审核状态事件
	form.on('select(isShow)', function(data){
		  $("input[name='isShow']").val(data.value);
		  $("input[name='p']").val(1);
		  ReviewVideoResData();
	});
	
});


//获得审核数据
function ReviewVideoResData() {
	$.ajax({
		type : "POST",
		url : ctx+"/adminOffLine/reviewVideoResData",
		data :$("#myform1").serialize(),
		success : function(data) {
			$("#videoconferenceData").html(data);
		}
	});
}

//搜索事件
function searchData() {
	var search=$("#searchval").val();
	$("input[name='search']").val(search);
	$("input[name='p']").val(1);
	ReviewVideoResData();
}

//搜索框键盘enter事件
$("#searchval").keyup(function(){
	if(event.keyCode == 13){
		searchData();
	} 
});

//进入审核页面
function review(id,picPath,bak){
	LayerOpen('审核视频', '800px', '750px',ctx+'/adminOffLine/reviewVideoPlay?id='+id+"&picPath="+picPath+"&bak="+bak);
}


//删除资源
function del(id){
	layer.confirm('你确定要删除？',{shade: [0.2, '#FFFFFF']},function(){
		$.ajax({
			type : "POST",
			url : ctx+"/adminOffLine/delVideoRes",
			data : {id:id},
			success : function(data) {
				layer.msg(data,{time : 1500},function() {
					if (data == "操作成功"){
						ReviewVideoResData();
					}
				});
			}
		});
	});
}


//进入编辑页面
function edit(id) {
	LayerOpen('编辑', '640px', '550px', ctx+'/adminOffLine/editVideoRes?id=' + id+"&type=H");
}



function batchDel(){
	var ids = $("input[name='del_id']:checked").val();
	if (typeof (ids) != "undefined") {
		layer.confirm('你确定要删除？',{shade: [0.2, '#FFFFFF']},function(){
			$.ajax({
				type : "POST",
				url : ctx+"/adminOffLine/batchDelVideoRes",
				data : $("#myform").serialize(),
				success : function(data) {
					layer.msg(data,{time : 1500},function() {
						if (data == "操作成功"){
							location.reload();
						}
					});
				}
			});
		});
	} else {
		layer.msg("请选择要操作的对象");
	}
}


