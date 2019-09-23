var $;
var form;
var $form;
layui.use([ 'jquery', 'form' ], function() {
	$ = layui.jquery;
	form = layui.form;
	$form = $('form');
});



function addGrade() {
	LayerOpen('添加新闻', '900px', '850px', ctx+'/adminnew/addInfor');
}

function edit(id) {
	LayerOpen('编辑', '900px', '850px', ctx+'/adminnew/update?id=' + id);
}


function del(id) {
	layer.confirm('你确定要删除？', {shade : [ 0.2, '#FFFFFF' ]}, function() {
		$.ajax({
			type : "POST",
			url : ctx+"/adminnew/del",
			data : {id : id},
			success : function(data) {
				layer.msg(data, {time : 1200}, function() {
					if (data == "操作成功"){
						location.href = ctx+"/adminnew/newManage?p=" + currPage;
					}
				});
			}
		});
	});
}


function details(id) {
	self.parent.layer.open({
        type: 2,
        title: '查看详情',
        shade: 0.2,
        area: ['820px','750px'],
        resize :false,
        content:ctx+'/adminnew/findOne?id=' + id
      });
}