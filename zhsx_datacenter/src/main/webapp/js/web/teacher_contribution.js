/**
 *教师角色贡献数据
 */


var myChart12 = echarts.init(document.getElementById("main12"));
var myChart122 = echarts.init(document.getElementById("main122"));
var myChart13 = echarts.init(document.getElementById("main13"));
var myChart133 = echarts.init(document.getElementById("main133"));
var myChart17 = echarts.init(document.getElementById("main17"));
var myChart18 = echarts.init(document.getElementById("main18"));
var color3 = ['#4596e5','#2ec7c9', '#b6a2de','#5c9ded','#36bd9b','#22b7e5','#7266bb','#fe9331'];


//var option12 = eval('(' + teacherClassTimesOfDay + ')');
var option12 = {
		color: color3,
		title: {
			text:  "课程次数/天"
		},
		tooltip: {
			trigger: 'axis', //弹框的显示与否
			axisPointer: {
				type: 'cross'
			}
		},
		legend: {
			data: ['上课次数']
		},
		xAxis: {
			data: ["星期一", "星期二", "星期三", "星期四", "星期五"],
			name: '时间'
		},
		yAxis: {

			type: 'value',
			name: '上课次数'

		},
		series: [{
			name: '上课次数',
			type: 'bar',
			data: teacherClassTimesOfDay
		}]

	}

	//使用刚指定的配置项和数据显示图表
	myChart12.setOption(option12);

	var option122 = {
		color: color3,
		title: {
			text:  "课程次数/周（最近五周）"
		},
		tooltip: {
			trigger: 'axis', //弹框的显示与否
			axisPointer: {
				type: 'cross'
			}
		},
		legend: {
			data: ['上课次数', '被评估课程数']
		},
		xAxis: {
			data: ["前四周", "前三周", "前二周", "前一周", "本周"],
			name: '时间'
		},
		yAxis: {

			type: 'value',
			name: '数量（节）' 

		},
		series: [{
			name: '上课次数',
			type: 'bar',
			data: findTeacherContributionOfWeek
		}, {
			name: '被评估课程数',
			type: 'bar',
			data: findTeacherContributionOfWeek_pg
		}]

	}

	//使用刚指定的配置项和数据显示图表
	myChart122.setOption(option122);

	//每月的上课次数
	//指定图表的配置项和数据2
	var option13 = {
		color: color3,
		title: {
			text:  "课程次数/月（最近四月）"
		},
		tooltip: {
			trigger: 'axis', //弹框的显示与否
			axisPointer: {
				type: 'cross'
			}
		},
		legend: {
			data: ['上课次数', '被评估课程数']
		},
		xAxis: {
			data: ["前三月", "前二月", "前一月", "本月"],
			name: '时间'
		},
		yAxis: {

			type: 'value',
			name: '数量（节）' 

		},
		series: [{
			name: '上课次数',
			type: 'bar',
			data: findTeacherContributionOfMonth
		}, {
			name: '被评估课程数',
			type: 'bar',
			data: findTeacherContributionOfMonth_pg
		}]

	}

	//使用刚指定的配置项和数据显示图表
	myChart13.setOption(option13);

	//每学期的上课次数
	//指定图表的配置项和数据2
	var option133 = {
		color: color3,
		title: {
			text:  "课程次数/学期"
		},
		tooltip: {
			trigger: 'axis', //弹框的显示与否
			axisPointer: {
				type: 'cross'
			}
		},
		legend: {
			data: ['上课次数', '被评估课程数']
		},
		xAxis: {
			data: ["上学期", "本学期"],
			name: '时间'
		},
		yAxis: {

			type: 'value',
			name: '数量（节）' 

		},
		series: [{
			name: '上课次数',
			type: 'bar',
			data: findTeacherContributionOfSemester
		}, {
			name: '被评估课程数',
			type: 'bar',
			data: findTeacherContributionOfSemester_pg
		}]

	}

	//使用刚指定的配置项和数据显示图表
	myChart133.setOption(option133);


	var option17 = {
		color: color3,
		title: {
			text: "本节课-课前备课评估得分"
		},
		
		tooltip: {
			trigger: 'axis', //弹框的显示与否
			axisPointer: {
				type: 'cross'
			}
		},
		xAxis: scoreOfLastOnce_xAxis,
		yAxis: {

			type: 'value',
			name: '得分',
			min: 0,
			max: 100

		},
		series:scoreOfLastOnce_series

	}

	//使用刚指定的配置项和数据显示图表
	myChart17.setOption(option17);

var option18 = {
	color: color3,
	title: {
		text: "本周-课前备课评估得分"
	},
	legend: {
		orient: 'vertical',
		left: '50'
	},
	tooltip: {
		trigger: 'axis', //弹框的显示与否
		axisPointer: {
			type: 'cross'
		}
	},
	xAxis: {
		name:"时间",
		data: ["星期一", "星期二", "星期三", "星期四", "星期五"]
	},
	yAxis: {
		type: 'value',
		name:"得分",
		min: 0,
		max: 100
	},
	series: [{
		type: 'line',
		data: averageScoreOfThisWeek
	}]

}
myChart18.setOption(option18);