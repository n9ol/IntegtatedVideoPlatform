layui.use([ 'jquery', 'layer', 'form' ], function() {
	window.jQuery = window.$ = layui.jquery;
	window.layer = layui.layer;
	var form = layui.form();
	
	// 全选
	$("#selected-all-operation").click(function() {
		if (this.checked) {
			$("input[name='del_id']").prop("checked", true);
		} else {
			$("input[name='del_id']").prop("checked", false);
		}
	});
	
	
	
	window.add = function(pgGroupId) {
		var userIds =[]; 
		$('input[name="userIds"]').each(function(){ 
			userIds.push($(this).val()); 
		}); 
		layer.open({
	        type: 2,
	        title: ['添加小组成员','z-index:auto;'],
	        shade: 0.2,
	        area: ['540px','300px'],
	        resize :false,
	        offset: 'rb',
	        content:[ctx+'/adminLopgGroupUser/addPgGroupUser?pgGroupId='+pgGroupId+'&userIds='+userIds, 'yes']
	      });
	}
	
	
	
	window.del = function(id) {
		$.post(ctx+"/adminLopgGroupUser/delPgGroupUser?id="+id,function(data){
			location.reload();
		});
	}
	
	
	window.batchDel = function() {
		var ids = $("input[name='del_id']:checked").val();
		if (typeof (ids) != "undefined") {
			$.post(
				ctx + "/adminLopgGroupUser/batchDelPgGroupUser"
				, $("#myform").serialize()
				, function(data) {
					location.reload();
				}
			);
		} else {
			layer.msg("请选择要操作的对象");
		}
	}
	
	
	// 搜索框键盘enter事件
	$("#searchval").keyup(function() {
		if (event.keyCode == 13) {
			searchPgGroupUser();
		}
	});
	window.searchPgGroupUser = function() {
		var searchval = $("#searchval").val();
		var pgGroupId = $("#pgGroupId").val();
		location.href = ctx + "/adminLopgGroupUser/pgGroupUser?search=" + searchval+"&pgGroupId="+pgGroupId;
	}
	
	
	
	
});