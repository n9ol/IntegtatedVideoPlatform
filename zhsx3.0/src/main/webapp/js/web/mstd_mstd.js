$(function(){

	getListData();
});

var contrastIds=[];

function getListData(){
	$.ajax({
	   type: "POST",
	   url: ctx+"/teacher/getListData",
	   async:false,
	   data: $("#myform").serialize(),
	   success: function(msg){
	     $("#teacherList").html(msg);
	     $("input[type='checkbox']").each(function(){
	 		for (  var id in contrastIds) {
	 			if($(this).val()==contrastIds[id]){
	 				$(this).attr("checked",true);
	 				
	 				$('.you_xia').show();//("display","bolck");
	 			}
	 		}
	 		});	
	     
	   }
	});
}


$(".zonghe a").click(function() {
	$(".zonghe a").removeClass("duos");
	$(this).addClass("duos");
});


var myChart2 = echarts.init(document.getElementById("main2"));

var myChart5 = echarts.init(document.getElementById("main5"));
//图1



//查询成长曲线
function searchGrowthCurve(id){
	layer.open({
		type: 1, //此处以iframe举例
		title: '<i class = "layui-icon" style = "font-size:25px;">&#xe63c;</i> 教师成长曲线图',
		area: ['750px', '520px'],
		shade: 0.2,
		maxmin: true,
		content: $('#main4'),
		zIndex: layer.zIndex
	});
	$.ajax({
		   type: "POST",
		   url: ctx+"/teacher/getGrowthCurve",
		   data: {'id':id},
		   success: function(msg){
		     var option5 =eval('(' + msg + ')');
		     myChart5.setOption(option5);
		   }
		});
	
}

var twocheck ;
function showContrast(obj){

		  var x = $("input[type='checkbox']:checked").length;

		    if(x<2  && ! $(obj).is(':checked')){
		    	twocheck ="";
		    	$('#duibi').removeClass("layui-btn layui-btn-mini layui-btn-normal duibibutton");
		    	$('#duibi').addClass( "layui-btn layui-btn-primary layui-btn-mini  duibibutton");
		    	$('#duibi').removeAttr("onclick");
		    }
		    if(x == 2 && $(obj).is(':checked')){
		    	twocheck =obj;
		    	//$(this).attr("name","haveChecked");
		    	$('#duibi').removeClass("layui-btn layui-btn-primary layui-btn-mini  duibibutton");
		    	$('#duibi').addClass( "layui-btn layui-btn-mini layui-btn-normal duibibutton");
		    	$('#duibi').attr("onclick","contrast()");
		    }
		    if(x>2){

		    	$(twocheck).attr("checked",false);
		    	twocheck =obj;
		    }
	
}


//对比升级方法
function showContrastOverwrite(obj,id,imgurl, nickName ,currName){

	$('.you_xia').show();
	
	var isCheck = $(obj).is(':checked');
	 var len= contrastIds.length;
	 $('#c_sum').html(len+1);
	 var name = currName;
	 if(name==undefined||name==''){
		 name=nickName;
	 }

	if(isCheck){
		if(len<=0){
			
        	addBlock(id,imgurl, name );
        }else if(len>=4){
        	alert("最多只能选择4位教师进行对比");
        	 $('#c_sum').html(len);
        	$(obj).attr("checked",false);
        	return false;
        }else{
        	var fal=0;
        	for (  var n in contrastIds){
        		if(contrastIds[n]==id){
        			fal=1;
        		}
        	}
        	if(fal==1){
        		return false;
        	}else{
        		addBlock(id,imgurl, name );
        	}
        }
	}else{
		removeTeacher(id);
	}
	
        
	return false;
}

//对比按钮事件
function contrast(){
	setTop();
	var ids ="";
	$("input[type='checkbox']:checkbox:checked").each(function(){
		ids+=$(this).val()+';';
		});
	searchContrast(ids);
}

//对比按钮事件
function contrastOverwrite(){
	var len= contrastIds.length;
	if(len<1){
		return false;
	}
	
	setTop();
	var ids ="";
	for (  var id in contrastIds) {
		
		ids+=contrastIds[id]+';';
	}
	searchContrast(ids);
}

//对比栏添加教师
function addBlock(id,imgurl, nickName ){
	contrastIds.push(id);
	
	 var html= "<li id='li_"+id+"'>"
	    +"<div  class='contrastImg'>"
	    +"<figure class='imghvr-slide-down2'>"
		+" <img id='img_"+id+"'  src='"+imgurl+"' onerror=\"this.src='"+ctx+"/img/user126.jpg'\"/>"
//		+"<figcaption style='background-color: rgba(0,0,0,0.7);'>"
//		+"<div class='see_xiangqing'>查看详情</div>"
//		+"</figcaption>"	
		+"<a href=\"javascript:getDetail('"+id+"')\"></a>  "
		+"</figure>"		
		+" <input id='input_"+id+"' type='hidden'  value='"+id+"'>"
		+" <p id='p_"+id+"'  class='teach_name2'>"+nickName+"</p>"
		+" <span class='closeX' onclick=\"removeTeacher('"+id+"')\">X</span>"
		+"</div>"
		+"</li>";
								
		$('#theContrast').append(html);
}


function removeTeacher(id){
	 var len= contrastIds.length;
	 $('#c_sum').html(len-1);
	 if((len-1)==0){
		 $('.you_xia').hide();
	 }
	removeFromArray(id);
	removeCheckbox(id);
	var a="#li_"+id;
	$(a).remove();
}

//取消复选框选择
function removeCheckbox(id){
	$("input[type='checkbox']:checkbox:checked").each(function(){
		if($(this).val()==id){
			$(this).attr("checked",false);
		}
		});	
}
//从已选数组中移除
function removeFromArray(id){
	for ( var n in contrastIds) {
		if(contrastIds[n]==id){
			contrastIds.splice(n,1);
		}
	}
}
//隐藏对比栏
function closeContrast(){
	 $('.you_xia').hide();
}

//对比
function searchContrast(ids){
	$.ajax({
		   type: "POST",
		   url: ctx+"/teacher/getContrast",
		   data: {'id':ids},
		   success: function(msg){
		     var option2 =eval('(' + msg + ')');
		     myChart2.clear();
		     myChart2.setOption(option2);
		   }
		});
	
}



//筛选功能的逻辑跳转
function searchByNj(id){
//	$(".teach_jieshao").removeClass("news");
//	$(".teach_jieshao").hide();
//	$("#"+id+"nj").addClass("news");
//	$("#"+id+"nj").show();
	$("input[name='p']").val('1');
	$("#teacherList").empty();
	$("input[name='schoolName']").val(id);
	getListData();
}

function searchByKm(id){
	$("input[name='p']").val('1');
	$("#teacherList").empty();
	$("input[name='authority']").val(id);
	getListData();
}

function searchAllByNj(){
//	$(".teach_jieshao").removeClass("news");
//	$(".teach_jieshao").hide();
//	$("#"+id+"nj").addClass("news");
//	$("#"+id+"nj").show();
	$("input[name='p']").val('1');
	$("#teacherList").empty();
	$("input[name='schoolName']").val('');
	getListData();
}

function searchAllByKm(){
	$("input[name='p']").val('1');
	$("#teacherList").empty();
	$("input[name='authority']").val('');
	getListData();
}

function searchByName(){
	$("input[name='p']").val('1');
	$("#teacherList").empty();

	getListData();
}


//地区搜索事件
function updateData(Cityname){
	$("input[name='p']").val(1);
	$("input[name='bak2']").val(Cityname);
	getListData();
}
//取消地区筛选
$(".quxiao").click(function(){
	var v = $(".area-danxuan").val();
	if('请选择地区'==v||v=='')
		return false;
	$(".area-danxuan").val("请选择地区");
	$("input[name='p']").val(1);
	$("input[name='bak2']").val(null);
	getListData();
});

function listSort(v){
	$("input[name='p']").val(1);
	if(v==0){
		$("input[name='bak']").val('0');
	}else if(v==1){
		$("input[name='bak']").val('1');
	}
	getListData();
}