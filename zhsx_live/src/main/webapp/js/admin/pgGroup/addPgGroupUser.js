layui.use([ 'jquery', 'layer', 'form' ], function() {
	window.jQuery = window.$ = layui.jquery;
	window.layer = layui.layer;
	var form = layui.form;
	
	// 全选
	$("#selected-all-operation").click(function() {
		if (this.checked) {
			$("input[name='pgUserIds']").prop("checked", true);
		} else {
			$("input[name='pgUserIds']").prop("checked", false);
		}
	});
	
	$(function(){
		getPgGroupUser();
	});
	
	
	
	// 搜索框键盘enter事件
	$("#searchval").keyup(function() {
		if (event.keyCode == 13) {
			getPgGroupUser();
		}
	});
	window.searchPgGroupUser = function() {
		getPgGroupUser();
	}
	
	
	
	function getPgGroupUser(){
		var userIds =[]; 
		$("input[name='user_id']").each(function(){ 
			userIds.push($(this).val()); 
		});
		var search = $("#searchval").val();
		$.post(ctx+"/adminLopgGroupUser/getPgGroupUser?userIds="+userIds+"&search="+search,function(data){
			$("#pgUserList").html(data);
		});
	}
	
	
	window.inster = function() {
		$.post(ctx+"/adminLopgGroupUser/insterPgGroupUser",$("#myform").serialize(),function(data){
			parent.location.reload();
		});
	}
	
	
	
	
	
	
	
	
	
});