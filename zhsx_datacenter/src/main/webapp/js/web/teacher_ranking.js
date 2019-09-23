/**
 * 教师角色排行榜数据
 */

var myChart7 = echarts.init(document.getElementById("main7"));
var myChart8 = echarts.init(document.getElementById("main8"));
var myChart9 = echarts.init(document.getElementById("main9"));
var myChart11 = echarts.init(document.getElementById("main11"));
var myChart14 = echarts.init(document.getElementById("main14"));

var color3 = ['#4596e5','#2ec7c9', '#b6a2de','#5c9ded','#36bd9b','#22b7e5','#7266bb','#fe9331'];
var option7 = {
		color: color3,
			
		tooltip: {
			trigger: 'axis', //弹框的显示与否
			axisPointer: { // 坐标轴指示器，坐标轴触发有效
				type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend: legend,
		xAxis: {
			data: ['本周', '前一周', '前二周','前三周', '前四周', '前五周', '前六周', '前七周'],
			name: '时间'
		},
		yAxis: {
			name: '得分',
			type: 'value',
			min: 0,
			max: 100

		},
		series : series

	}

	//使用刚指定的配置项和数据显示图表
	myChart7.setOption(option7);

	var option8 = {
		color: color3,
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'shadow'
			}
		},
		legend: {
			data: ['站起来时长', '站起来次数']
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		xAxis: {
			type: 'value',
			boundaryGap: [0, 0.01]
		},
		yAxis:yAxis,
		series: series_d
	};
	myChart8.setOption(option8);

	//图9
	var option9 = {
		color: color3,
		title: {
			text: "本周课程教师得分",
			x: 'center'
		},
		tooltip: {
			trigger: 'axis', //弹框的显示与否
			axisPointer: { // 坐标轴指示器，坐标轴触发有效
				type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		xAxis:xAxis,
		yAxis: {
			name: '得分',
			type: 'value',
			min: 0,
			max: 100

		},
		series: [{
			name: '得分',
			type: 'bar',
			data: twsData
		}]

	}

	//使用刚指定的配置项和数据显示图表
	myChart9.setOption(option9);
	var option11 = {
			color: color3,
			tooltip: {
				trigger: 'axis',
				axisPointer: { // 坐标轴指示器，坐标轴触发有效
					type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend: {
				data: ['初级教师', '三级教师', '二级教师', '一级教师', '高级教师', '特级教师']
			},
			grid: {
				left: '3%',
				right: '4%',
				bottom: '3%',
				containLabel: true
			},
			xAxis: {
				type: 'value'
			},
			yAxis: {
				type: 'category',
				data: ['周一', '周二', '周三', '周四', '周五']
			},
			series: [{
				name: '初级教师',
				type: 'bar',
				stack: '总量',
				label: {
					normal: {
						show: true,
						position: 'insideRight'
					}
				},
				data: rankingAverageScoreOfKz_1
			}, {
				name: '三级教师',
				type: 'bar',
				stack: '总量',
				label: {
					normal: {
						show: true,
						position: 'insideRight'
					}
				},
				data: rankingAverageScoreOfKz_2
			}, {
				name: '二级教师',
				type: 'bar',
				stack: '总量',
				label: {
					normal: {
						show: true,
						position: 'insideRight'
					}
				},
				data: rankingAverageScoreOfKz_3
			}, {
				name: '一级教师',
				type: 'bar',
				stack: '总量',
				label: {
					normal: {
						show: true,
						position: 'insideRight'
					}
				},
				data: rankingAverageScoreOfKz_4
			}, {
				name: '高级教师',
				type: 'bar',
				stack: '总量',
				label: {
					normal: {
						show: true,
						position: 'insideRight'
					}
				},
				data: rankingAverageScoreOfKz_5
			}, {
				name: '特级教师',
				type: 'bar',
				stack: '总量',
				label: {
					normal: {
						show: true,
						position: 'insideRight'
					}
				},
				data: rankingAverageScoreOfKz_6
			}]
		};

		myChart11.setOption(option11);
var option14 = {
		color: color3,
		tooltip: {
			trigger: 'axis',
			axisPointer: { // 坐标轴指示器，坐标轴触发有效
				type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
			}
		},
		legend: {
			data: ['初级教师', '三级教师', '二级教师', '一级教师', '高级教师', '特级教师']
		},
		grid: {
			left: '3%',
			right: '4%',
			bottom: '3%',
			containLabel: true
		},
		xAxis: {
			type: 'value'
		},
		yAxis: {
			type: 'category',
			data: ['周一', '周二', '周三', '周四', '周五']
		},
		series: [{
			name: '初级教师',
			type: 'bar',
			stack: '总量',
			label: {
				normal: {
					show: true,
					position: 'insideRight'
				}
			},
			data: rankingAverageScoreOfKq_1
		}, {
			name: '三级教师',
			type: 'bar',
			stack: '总量',
			label: {
				normal: {
					show: true,
					position: 'insideRight'
				}
			},
			data: rankingAverageScoreOfKq_2
		}, {
			name: '二级教师',
			type: 'bar',
			stack: '总量',
			label: {
				normal: {
					show: true,
					position: 'insideRight'
				}
			},
			data: rankingAverageScoreOfKq_3
		}, {
			name: '一级教师',
			type: 'bar',
			stack: '总量',
			label: {
				normal: {
					show: true,
					position: 'insideRight'
				}
			},
			data: rankingAverageScoreOfKq_4
		}, {
			name: '高级教师',
			type: 'bar',
			stack: '总量',
			label: {
				normal: {
					show: true,
					position: 'insideRight'
				}
			},
			data: rankingAverageScoreOfKq_5
		}, {
			name: '特级教师',
			type: 'bar',
			stack: '总量',
			label: {
				normal: {
					show: true,
					position: 'insideRight'
				}
			},
			data: rankingAverageScoreOfKq_6
		}]
	};

	myChart14.setOption(option14);