'use strict';
layui.use(['jquery','layer','element'],function(){
	window.jQuery = window.$ = layui.jquery;
	window.layer = layui.layer;
    var element = layui.element;

  
// larry-side-menu向左折叠
$('.larry-side-menu').click(function() {
  var sideWidth = $('#larry-side').width();
  if(sideWidth === 200) {
      $('#larry-body').animate({
        left: '0'
      }); //admin-footer
      $('#larry-footer').animate({
        left: '0'
      });
      $('#larry-side').animate({
        width: '0'
      });
  } else {
      $('#larry-body').animate({
        left: '200px'
      });
      $('#larry-footer').animate({
        left: '200px'
      });
      $('#larry-side').animate({
        width: '200px'
      });
  }
});

 
$(function(){
   // 刷新iframe操作
    $("#refresh_iframe").click(function(){
       $(".layui-tab-content .layui-tab-item").each(function(){
          if($(this).hasClass('layui-show')){
             $(this).children('iframe')[0].contentWindow.location.reload(true);
          }
       });
    });
});

//左侧菜单切换
$(function() {
	listSysPermissionsMenu("base");
});

$("#menu").find("li").click(function(){
	$(this).addClass("layui-this").siblings().removeClass("layui-this");
	var liId = $(this).attr("id");
	switch (liId) {
	case 'baseMenu':
		listSysPermissionsMenu("base");
		break;
	case 'scheduleMenu':
		listEclassBrandSysPermissionsMenu("schedule");
		break;
	case 'EClassBrandMenu':
		listEclassBrandSysPermissionsMenu("EClassBrand");
		break;
	case 'zhsxMenu':
		listSysPermissionsMenu("zhsx");
		break;
	default:
		listSysPermissionsMenu("base");
		break;
	}
});

function listSysPermissionsMenu(menuType) {
	$.ajax({
		type : "POST",
		url : ctx + "/listSysPermissionsMenu",
		data : "menuType=" + menuType,
		success : function(date) {
			$("#listMenu").html(date);
			element.init();
		}
	});
}


function listEclassBrandSysPermissionsMenu(menuType){
	$.ajax({
		type : "POST",
		url : ctx + "/listEclassBrandSysPermissionsMenu",
		data : "menuType=" + menuType,
		success : function(date) {
			$("#listMenu").html(date);
			element.init();
		}
	});
}


});