function LayerOpen(title,width,heigth,src){
	self.parent.layer.open({
        type: 2,
        title: [title,'z-index:auto;'],
        shade: 0.2,
        area: [width,heigth],
        resize :false,
        content:[src, 'no']
      });
}

function closeiframe(windowname){
   var index = parent.layer.getFrameIndex(windowname); //先得到当前iframe层的索引
   parent.layer.close(index); //再执行关闭 */
}