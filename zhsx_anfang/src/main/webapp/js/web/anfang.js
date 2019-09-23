$(function(){
	 $("#afTop").attr("class","has-sub active");
	 getAfSchool(1);
});

//选择区域点击事件
function selectDh(schoolarea){
	$("input[name='schoolarea']").val(schoolarea);
	getAfManager(1);
}

//选择学校点击事件
function chooseSchool(schoolid,src){
	$(".left_li li").css({
		"backgroundColor": "#fff",
		"border-left": " none",
		"color": "gray"
	});
	$(src).css({
		"backgroundColor": "#f4f7fc",
		"border-left": " 3px solid #4596E5",
		"color": "#000"
	});
	
	$("input[name='schoolarea']").val(null);
	$("input[name='schoolid']").val(schoolid);
	getAfManager(1);
	findDhBySchool(schoolid);
}


//获得学校
function getAfSchool(p){
	$(".left_li").empty();
	$.getJSON(ctx+"/af/getAfSchool", {p:p}, function(json){
		var pageNum = json.pageNum;
		var pages = json.pages;
		$("input[name='p']").val(pageNum);
		$("input[name='pages']").val(pages);
		
		if(pageNum==1){
			$(".shanglabiao").hide();
		}else{
			$(".shanglabiao").show();
		}
		
		if(pages>pageNum){
			$(".xialabiao").show();
		}else{
			$(".xialabiao").hide();
		}
		
		for (var int = 0; int < json.lists.length; int++) {
			var array_element = json.lists[int];
			$(".left_li").append("<li id='"+array_element.id+"' onclick = chooseSchool('"+array_element.id+"',this);>"+array_element.schoolName+"</li>");
		}
		
		var state = $("input[name='state']").val();
		if(state === '1'){
			$("input[name='state']").val(2);
			$("#"+json.lists[0].id).css({
				"backgroundColor": "#f4f7fc",
				"border-left": " 3px solid #4596E5",
				"color": "#000"
			});
			$("input[name='schoolid']").val(json.lists[0].id);
			getAfManager(1);
			//添加
			findDhBySchool(json.lists[0].id);
			
		}
	});
}

//学校上一页
$(".shanglabiao").click(function(){
	var pageNum = $("input[name='p']").val();
	var schoolId = $("input[name='schoolid']").val();
	if(pageNum>1){
		var p = Number(pageNum)-1;
		getAfSchool(p);
		findDhBySchool(schoolId);
		
	}
});


//学校下一页
$(".xialabiao").click(function(){
	var schoolId = $("input[name='schoolid']").val();
	var pageNum = $("input[name='p']").val();
	var pages = $("input[name='pages']").val();
	if(pages>pageNum){
		var p = Number(pageNum)+1;
		getAfSchool(p);
		findDhBySchool(schoolId);
	}
});

//获得安防列表
var t0;
var t1;
var t2;
var t3;
function getAfManager(p){
	var schoolid = $("input[name='schoolid']").val();
	var schoolarea = $("input[name='schoolarea']").val();
	
	 window.clearTimeout(t0);//去掉定时器 
	 window.clearTimeout(t1);//去掉定时器 
	 window.clearTimeout(t2);//去掉定时器 
	 window.clearTimeout(t3);//去掉定时器 
	$("#fenpingmu").empty();
	$.getJSON(ctx+"/af/findAll", { schoolId:schoolid,p:p,schoolarea:schoolarea}, function(json){
		if(json.lists.length>0){
			for (var int = 0; int < json.lists.length; int++) {
				var array_element = json.lists[int];
				if(int==0){
					t0= setTimeout(_addData(array_element),1000*(0));//1000为1秒钟 
				}
				if(int==1){
					t1= setTimeout(_addData(array_element),1000*(2));//1000为1秒钟
				}
				if(int==2){
					t2= setTimeout(_addData(array_element),1000*(3));//1000为1秒钟 
				}
				if(int==3){
					t3= setTimeout(_addData(array_element),1000*(4));//1000为1秒钟 
				}
			}
			pagehtml(json.pages, json.pageNum);
		}else{
			$(".fenpingmu").append('<li><div class="bofangkuang" style="text-align:center;font-size:20px;color:black;"><span>暂无数据</span></div></li>');
		}
	});
}

//定时器
function _addData(_l){
    return function(){
 	   addData(_l);
    }
}

//添加_vlc播放
function addData(l){
	var vlc = '<li>';
			vlc += '<div class="bofangkuang">';
				vlc += '<object classid="clsid:E23FE9C6-778E-49D4-B537-38FCDE4887D8" codebase="http://downloads.videolan.org/pub/videolan/vlc/latest/win32/axvlc.cab" width="450" height="240" events="True" id="vlc2">';
				vlc += '<param name="MRL" value="udp://@239.255.1.1:1234" />';   
				vlc += '<param name="ShowDisplay" value="True" />';   
				vlc += '<param name="Loop" value="False" />';     
			    vlc += '<param name="AutoPlay" value="True" />';     
			    vlc += '<embed type="application/x-vlc-plugin" pluginspage="http://www.videolan.org" width="450"  height="240"  src="'+l.videopath+'";  id="vlc"></embed>    ';           
			    vlc += '</object>';      
			vlc += '</div>';
			vlc += '<p >'+l.schoolName +'_'+l.dhname+'_'+l.camearname+'</p>';
		vlc += '</li>';
	$(".fenpingmu").append(vlc);
}


//分页器
function pagehtml(pages,pageNum){
	layui.use(['laypage'], function() {
		var laypage = layui.laypage;
		laypage({cont : "page",pages :pages,curr :pageNum, skip : false,skin : "#4596E5",groups : 10,
			jump : function(e, first) {
				if (!first) {
					getAfManager(e.curr);
				}
			}
		});
	});
}


function findDhBySchool(schoolId){
	$.ajax({
		type: "post",
		url: ctx+"/af/findDhBySchool",
		data: {schoolid:schoolId},
		 success: function(msg){
		   	$(".daohang").html(msg);
		   }
		
	});
}

//下载插件
function downloadPlugIn(){
	window.open("https://mirrors.ustc.edu.cn/videolan-ftp/vlc/2.2.4/win32/vlc-2.2.4-win32.exe");   
}