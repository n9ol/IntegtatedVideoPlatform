var layer;
layui.use([ 'layer', 'element' ], function() {
	var element = layui.element;
	layer = layui.layer;
});

// //更新评估内容分数
// function updataPjDetailScore(id,src){
// var score = src.value;
// if(!$.isNumeric(score) || score > 100 || score < 0 ){
// layer.msg("分值必须在0到100之间");
// $(src).val(0);
// }else{
// $.getJSON(ctx+"/pgInfo/updataPjDetailScore", { id: id, score: score });
// }
// }

// 更新评估项
function updataWebPjInfo() {
	$.ajax({
		type : "POST",
		url : ctx + "/pgInfo/updataWebPjInfo",
		data : $("#myformPjDetail").serialize(),
		success : function(data) {
			closeiframe(window.name);
			window.parent.refreshPage(data);
		}
	});
}

layui.use('rate', function() {
	var rate = layui.rate;

	// 渲染
	$(function() {
		for (var int = 0; int < listSize; int++) {
			rate.render({
				elem : '#totals' + int,
				length : 6,
				half : true,
				text : true,
				value : Number($(".totals" + int).val()),
				setText : function(value) {
					var arrs = {
						'0.5' : 'D-',
						'1' : 'D',
						'1.5' : 'D+',
						'2' : 'C-',
						'2.5' : 'C',
						'3' : 'C+',
						'3.5' : 'B-',
						'4' : 'B',
						'4.5' : 'B+',
						'5' : 'A-',
						'5.5' : 'A',
						'6' : 'A+'
					};
					this.span.text(arrs[value] || (value));
				},
				choose : function(value) {
					var iId = this.elem.attr("id")
					var id = $("." + iId).attr("id");
					$("." + iId).val(value);
					$.getJSON(ctx + "/pgInfo/updataPjDetailScore", {
						id : id,
						score : value
					});
				}
			});
		}
	});

});