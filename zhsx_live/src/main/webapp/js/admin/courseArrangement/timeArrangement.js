var layer;
layui.use(['layer','laydate'],function(){
	layer=layui.layer;
	var laydate = layui.laydate;
});

//得到学校
function getSchool(p,search){
	$(".school").empty();
	$.ajax({
		   type: "POST",
		   url: ctx+"/adminCourseArrangement/getSchool",
		   data: {p:p,search:search},
		   dataType: "json", 
		   success: function(json){
				var pageNum=json.pageNum;
				var pages=json.pages;
				$("#pageNum").val(pageNum);
				$("#pages").val(pages);
				
				if(pageNum==1)
					$("#syy").attr("class","layui-btn  layui-btn-small layui-btn-normal layui-btn-disabled");
				else
					$("#syy").attr("class","layui-btn  layui-btn-small layui-btn-normal");
				
				if(pages>pageNum){
					$("#xyy").attr("class","layui-btn  layui-btn-small layui-btn-normal");
				}else{
					$("#xyy").attr("class","layui-btn  layui-btn-small layui-btn-normal layui-btn-disabled");
				}
				
				var search=json.search;
				$("#search").val(search);
				
				for (var int = 0; int < json.lists.length; int++) {
					var arrayJson=json.lists[int];
					$(".school").prepend("<tr style='cursor:pointer'><td colspan='2' onclick=getTime('"+arrayJson.id+"','"+arrayJson.schoolName+"');>"+arrayJson.schoolName+"</td></tr>");
				}
		   }
		});
}


$(document).ready(function(){
	getSchool(1,null);
});


//搜索框键盘enter事件
$("#searchval").keyup(function(){
	if(event.keyCode == 13){
		var search = $("#searchval").val();
		getSchool(1,search);
	} 
});

//搜索点击事件
$("#searchSchool").click(function(){
	var search = $("#searchval").val();
	getSchool(1,search);
});

//上一页
function onpage(){
	var search = $("#searchval").val();
	var pageNum=$("#pageNum").val();
	if(pageNum>1){
		var p=Number(pageNum)-1;
		getSchool(p,search);
	}
}

//下一页
function underpage(){
	var search = $("#searchval").val();
	var pageNum=$("#pageNum").val();
	var pages=$("#pages").val();
	if(pages>pageNum){
		var p=Number(pageNum)+1;
		getSchool(p,search);
	}
}

//得到时间
function getTime(schoolId,schoolName){
	$("#schoolName").text(schoolName);
	$("#schoolId").val(schoolId);
	$("#termTimeId").val("");
	$("#firstDay").val("");
	$("#lastDay").val("");
	$.ajax({
	   type: "POST",
	   url: ctx+"/adminCourseArrangement/getTime",
	   data: {schoolId:schoolId},
	   dataType: "json", 
	   success: function(json){
			var state =json.state;
			if(state==1){
				$("#termTimeId").val(json.loTermTime.id);
				$("#firstDay").val(json.firstDay);
				$("#lastDay").val(json.lastDay);
				$("#uTT").css("display","block");
				$("#iTT").css("display","none");
			}else{
				$("#uTT").css("display","none");
				$("#iTT").css("display","block");
			}
			
			$("tr").remove("#ct");
			var loClassTimes=json.loClassTimes;
			for (var int = 0; int < loClassTimes.length; int++) {
				var arrayJson=loClassTimes[int];
				$(".classTime").append("<tr id='ct'><td>"+arrayJson.sectionofDay+"</td><td>"+arrayJson.starttimef+"</td><td>"+arrayJson.endtimef+"</td><td><a href='javascript:;' class='check_look' onclick=updateClassTime('"+arrayJson.id+"')><i class='layui-icon'>&#xe642;</i>修改</a><a href='javascript:;' class='check_look' onclick=delClassTime('"+arrayJson.id+"',this)><i class='layui-icon'>&#xe640;</i>&nbsp;删除</a></td></tr>");
			}
	   }
	});
}

//添加学期时间
function insterTermTime(){
	var schoolId=$("#schoolId").val();
	if(schoolId==""){
		layer.msg("请选择学校！");
		return;
	}
	
	var firstDay=$("#firstDay").val();
	if(firstDay==""){
		layer.msg("开始时间不能为空！");
		return;
	}
	
	var lastDay=$("#lastDay").val();
	if(lastDay==""){
		layer.msg("结束时间不能为空！");
		return;
	}
	
	$.getJSON(ctx+"/adminCourseArrangement/insertTermTime",{schoolId:schoolId,firstDay:firstDay,lastDay:lastDay},function(json){
		var res=json.res;
		if(res=='S'){
			$("#uTT").css("display","block");
			$("#iTT").css("display","none");
			$("#termTimeId").val(json.id);
			layer.msg("添加成功");
		}else if(res=='F'){
			layer.msg("添加失败");
		}else{
			layer.msg(res);
		}
	}); 
}

//修改时间
function updateTermTime(){
	var id=$("#termTimeId").val();
	var firstDay=$("#firstDay").val();
	var lastDay=$("#lastDay").val();
	$.getJSON(ctx+"/adminCourseArrangement/updateTermTime",{id:id,firstDay:firstDay,lastDay:lastDay},function(json){
		var res=json.res;
		if(res=='S'){
			layer.msg("修改成功");
		}else if(res=='F'){
			layer.msg("修改失败");
		}else{
			layer.msg(res);
		}
	}); 
}

//上课时间
function addClassTime(){
	var schoolId=$("#schoolId").val();
	if(schoolId==""){
		layer.msg("请选择学校！");
		return;
	}
	var termTimeId=$("#termTimeId").val();
	if(termTimeId==""){
		layer.msg("请先添加学期时间！");
		return;
	}
	LayerOpen('添加上课时间', '640px', '550px', ctx+'/adminCourseArrangement/addClassTime?termTimeId='+termTimeId);		
}

//修改上课时间
function updateClassTime(id){
	LayerOpen('修改上课时间', '640px', '300px', ctx+'/adminCourseArrangement/editClassTime?id='+id);
}

//删除上课时间
function delClassTime(id,src){
	layer.confirm('你确定要删除？',{shade: [0.2, '#FFFFFF']},function(){
		$.ajax({
		   type: "POST",
		   url: ctx+"/adminCourseArrangement/delClassTime",
		   data: {id:id},
		   success: function(msg){
		     refreshPage(msg);
		   }
		});
	});
}

//刷新页面
function refreshPage(data){
	layer.msg(data,{time:1500},function(){
		var schoolId =$("#schoolId").val();
		getTime(schoolId);
	}); 
}