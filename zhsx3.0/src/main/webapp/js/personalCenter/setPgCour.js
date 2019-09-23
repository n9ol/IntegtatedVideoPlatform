var layer;
layui.use([ 'layer' ], function() {
	layer = layui.layer;
});

function chooseCour(id) {
	layer.open({
        type: 2,
        title: '选择课件',
        shadeClose: true,
        shade: 0.2,
        maxmin: true, //开启最大化最小化按钮
        scrollbar: false,
        resize :false,
        area: ['550px', '200px'],
        offset: 'rb',
        content: ctx+'/personalCenter/chooseCour?pjInfoId='+id+"&zId="+zId
      });
}