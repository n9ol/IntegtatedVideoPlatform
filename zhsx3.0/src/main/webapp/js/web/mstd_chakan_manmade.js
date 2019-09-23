var myChart1 = echarts.init(document.getElementById("main1"));
var colors = ['#ff7f50'];
var option1 = {
	color: colors,
	title: {
		text: ["本节课课堂评估分数展示图"],
		x: 'center'
	},
	tooltip: {
		trigger: 'axis', //弹框的显示与否
		axisPointer: {
			type: 'cross'
		}
	},
    toolbox: {
    	itemGap : 10,
    	right : 120,
        show : true,
        feature : {
            magicType : {show: true, type: ['line', 'bar']},
            saveAsImage : {show: true}
        }
    },
	xAxis: {
		data: []
	},
	yAxis: [{
		type: 'value',
		name: '得分	',
		min: 0,
		max: 100
	}],
	series: [{
		name: '平均分',
		type: 'bar',
		data: []
	}]
}
//使用刚指定的配置项和数据显示图表
myChart1.setOption(option1);
function getPgAverageDraw(){
	
	$.ajax({
	   type: "POST",
	   url: ctx+"/pgInfo/getPgAverageDraw",
	   data: { pgId: pgId,onOff:onOff},
	   dataType: "json",
	   success: function(json){
			if(json.xDataArray == "总分" || json.xDataArray == "课前总分,课中总分"){
				$(".xiangqingtu").empty();
				return;
			}
			myChart1.setOption({
				xAxis: {
					data: json.xDataArray
				},
				series: [{
					name: '平均分',
					data: json.seriesDataArray
				}]
			});
	   }
	});
	
}
$(function(){
	getPgAverageDraw();
});