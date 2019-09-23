var myChart12 = echarts.init(document.getElementById("main12"));
var myChart122 = echarts.init(document.getElementById("main122"));
var myChart13 = echarts.init(document.getElementById("main13"));
var myChart133 = echarts.init(document.getElementById("main133"));
var color3 = ['#4596e5', '#2ec7c9', '#b6a2de', '#5c9ded', '#36bd9b', '#22b7e5', '#7266bb', '#fe9331'];

//某县/市前一周在平台上课前备课的不同学校的任务

//新插入的表格
//指定图表的配置项和数据
//每天的上课次数
var option12 = {
	color: color3,
	title: {
		text: "本周-管理范围内教研员平均评课数量"
	},
	tooltip: {
		trigger: 'axis', //弹框的显示与否
		axisPointer: {
			type: 'cross'
		}
	},
	legend: {
		data: ['实际评课总数量', '标准评估总数量 / 天'],
		bottom: "bottom"
	},
	xAxis: {
		data: ["周一", "周二", "周三", "周四", "周五"],
		name: '时间'
	},
	yAxis: {
		type: 'value',
		name: '数量（节）'/*,
		min: 0,
		max: 10*/
	},
	series: [{
		name: '实际评课总数量',
		type: 'bar',
		data: [weekscount1, weekscount2, weekscount3, weekscount4, weekscount5]
	}, {
		name: '标准评估总数量 / 天',
		type: 'bar',
		data: [standardDay, standardDay, standardDay, standardDay, standardDay]
	}]

}

//使用刚指定的配置项和数据显示图表
myChart12.setOption(option12);

var option122 = {
	color: color3,
	title: {
		text: "管理范围内教研员平均评课数量（近四周）"
	},
	tooltip: {
		trigger: 'axis', //弹框的显示与否
		axisPointer: {
			type: 'cross'
		}
	},
	legend: {
		data: ['实际评课总数量', '标准评估总数量 / 周'],
		bottom: "bottom"
	},
	xAxis: {
		data: ["前三周", "前二周", "前一周", "本周"],
		name: '时间'
	},
	yAxis: {
		type: 'value',
		name: '数量（节）'/*,
		min: 0,
		max: 80*/
	},
	series: [{
		name: '实际评课总数量',
		type: 'bar',
		data: [weekc1, weekc2, weekc3, weekc4]
	}, {
		name: '标准评估总数量 / 周',
		type: 'bar',
		data: [standardWeek, standardWeek, standardWeek, standardWeek]
	}]

}

//使用刚指定的配置项和数据显示图表
myChart122.setOption(option122);

//每月的上课次数
//指定图表的配置项和数据2
var option13 = {
	color: color3,
	title: {
		text: "管理范围内教研员平均评课数量（近四个月）"
	},
	tooltip: {
		trigger: 'axis', //弹框的显示与否
		axisPointer: {
			type: 'cross'
		}
	},
	legend: {
		data: ['实际评课总数量', '标准评估总数量 / 月'],
		bottom: "bottom"
	},
	xAxis: {
		data: ["前三月", "前二月", "前一月", "本月"],
		name: '时间'
	},
	yAxis: {
		type: 'value',
		name: '数量（节）'/*,
		min: 0,
		max: 200.0*/
	},
	series: [{
		name: '实际评课总数量',
		type: 'bar',
		data: [monthc1, monthc2, monthc3, monthc4]
	}, {
		name: '标准评估总数量',
		type: 'bar',
		data: [standardMonth, standardMonth, standardMonth, standardMonth]
	}]

}

//使用刚指定的配置项和数据显示图表
myChart13.setOption(option13);

//每学期的上课次数
//指定图表的配置项和数据2
var option133 = {
	color: color3,
	title: {
		text: "管理范围内教研员平均评课总数量与标准评估对比/学期（两学期）"
		/*	text: "管理范围内实际评课总数量对比 / 学期（两学期）"*/
	},
	tooltip: {
		trigger: 'axis', //弹框的显示与否
		axisPointer: {
			type: 'cross'
		}
	},
	legend: {
		data: ['实际评课总数量', '标准评估总数量'],
		bottom: "bottom"
	},
	xAxis: {
		data: ["上学期", "本学期"],
		name: '时间'
	},
	yAxis: {
		type: 'value',
		name: '数量（节）'/*,
		min: 0,
		max: 680.0*/
	},
	series: [{
		name: '实际评课总数量',
		type: 'bar',
		data: [firstNum, nextNum]
	}, {
		name: '标准评估总数量',
		type: 'bar',
		data: [standardSemester, standardSemester]
	}]

}

//使用刚指定的配置项和数据显示图表
myChart133.setOption(option133);