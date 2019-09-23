
var myChart1 = echarts.init(document.getElementById("main1"));
var myChart2 = echarts.init(document.getElementById("main2"));
var myChart3 = echarts.init(document.getElementById("main3"));
var myChart4 = echarts.init(document.getElementById("main4"));
var myChart5 = echarts.init(document.getElementById("main5"));
var myChart6 = echarts.init(document.getElementById("main6"));

//图1
var color3 = ['#b7a3df','#5696e5','#2bc7c9'];
var option1 = {
	color:color3,
	title: {
				text: '考试人数及格人数占比',
				x: 'center'
			},
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data:['考试不及格人数','考试及格人数','考试人数']
    },
    series: [
        {
            name:'访问来源',
            type:'pie',
            selectedMode: 'single',
            radius: [0, '30%'],

            label: {
                normal: {
                    position: 'inner'
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:75100817.1254119, name:'考试人数', selected:true}
            ]
        },
        {
            name:'访问来源',
            type:'pie',
            radius: ['40%', '55%'],

            data:[
                {value:35880113, name:'考试不及格人数'},
                {value:37846064, name:'考试及格人数'}
            ]
        }
    ]
};

myChart1.setOption(option1)

//图2
var color2 = ["#ffb981", "#d97e84","#8d99b3","#2ec7c9"]
var option2 = {
	color: color2,
	title: {
		text: "四个班级各科成绩对比图",
		x:'center'
	},
	tooltip: {
		trigger: 'axis',//弹框的显示与否
		axisPointer: {
			type: 'cross'
		}
	},
	legend: {
		data: ['一班', '二班','三班','四班'],
		y:'bottom'
	},
	xAxis: {
		data: ["语文", "数学", "英语", "音乐", "生物","历史","舞蹈"],
		name:'时间'
	},
	yAxis: {
		
			type: 'value',
			min: 0,
			max: 100
		
	},
	series: [{
		name: '一班',
		type: 'bar',
		data: [50, 80, 70, 50, 90,85,36]
	}, {
		name: '二班',
		type: 'bar',
		data: [70, 80, 70, 80, 80,87,90]
	},
	{
		name: '三班',
		type: 'bar',
		data: [80, 70, 70, 80, 80,88,58]
	},{
		name: '四班',
		type: 'bar',
		data: [60, 90, 80, 60, 60,85,47]
	}]

}

//使用刚指定的配置项和数据显示图表
myChart2.setOption(option2);



//图3

		var colors = ["#4596e5", "#b6a2de", "#2ec7c9"];
		var option3 = {
			color: colors,
			title: {
				text: '各年级的平均分',
				x: 'center'
			},
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient: 'vertical',
				left: 'left',
				data: ['课后作业', '课中练习', '备课材料']
			},
			series: [{
				name: '访问来源',
				type: 'pie',
				radius: '55%',
				center: ['50%', '60%'],
				data: [{
					value: 335,
					name: '课后作业'
				}, {
					value: 310,
					name: '课中练习'
				}, {
					value: 234,
					name: '备课材料'
				}],
				itemStyle: {
					emphasis: {
						shadowBlur: 10,
						shadowOffsetX: 0,
						shadowColor: 'rgba(0, 0, 0, 0.5)'
					}
				}
			}]
		};
		myChart3.setOption(option3);
		
//图4
var color2 = ["#ffb981", "#d97e84","#8d99b3","#2ec7c9"]
var option4 = {
	color: color2,
	title: {
		text: "每个月份的各科成绩对比图",
		x:'center'
	},
	tooltip: {
		trigger: 'axis',//弹框的显示与否
		axisPointer: {
			type: 'cross'
		}
	},
	legend: {
		data: ['一班', '二班','三班','四班'],
		y:'bottom'
	},
	xAxis: {
		data: ["一月", "二月", "三月", "四月", "五月","六月","七月","八月","九月","十月","十一月","十二月"],
		name:'时间'
	},
	yAxis: {
		
			type: 'value',
			min: 0,
			max: 100
		
	},
	series: [{
		name: '一班',
		type: 'bar',
		data: [50, 80, 70, 50, 90,85,36,80,95,45,99,20]
	}, {
		name: '二班',
		type: 'bar',
		data: [70, 80, 70, 80, 80,87,90,36,80,95,45,99]
	},
	{
		name: '三班',
		type: 'bar',
		data: [80, 70, 70, 80, 80,88,58,36,80,95,45,99]
	},{
		name: '四班',
		type: 'bar',
		data: [60, 90, 80, 60, 60,85,47,36,80,95,45,99]
	}]

}

//使用刚指定的配置项和数据显示图表
myChart4.setOption(option4);


//图5

		var colors = ["#5ab1ef", "#ffb981", "#d97e84","#8d99b3","#2ec7c9","#b7a3df"];
		var option5 = {
			color: colors,
			title: {
				text: '各个学校的考试分数对比',
				x: 'center'
			},
			tooltip: {
				trigger: 'item',
				formatter: "{a} <br/>{b} : {c} ({d}%)"
			},
			legend: {
				orient: 'vertical',
				left: 'left',
				data: ['商水范楼小学', '商水豆庄小学', '商水着慌庄小学','商水县之一小','商水路庙小学','商水周庄小学']
			},
			series: [{
				name: '访问来源',
				type: 'pie',
				radius: '55%',
				center: ['50%', '60%'],
				data: [{
					value: 335,
					name: '商水范楼小学'
				}, {
					value: 310,
					name: '商水豆庄小学'
				}, {
					value: 524,
					name: '商水着慌庄小学'
				},
				{
					value: 65,
					name: '商水县之一小'
				},
				{
					value: 124,
					name: '商水路庙小学'
				},
				{
					value: 525,
					name: '商水周庄小学'
				}],
				itemStyle: {
					emphasis: {
						shadowBlur: 10,
						shadowOffsetX: 0,
						shadowColor: 'rgba(0, 0, 0, 0.5)'
					}
				}
			}]
		};
		myChart5.setOption(option5);
		

//图6
labelTop = {
    normal : {
        label : {
            show : true,
            position : 'center',
            formatter : '{b}',
            textStyle: {
                baseline : 'bottom'
            }
        },
        labelLine : {
            show : false
        }
    }
};
var labelFromatter = {
    normal : {
        label : {
            formatter : function (params){
                return 100 - params.value + '%'
            },
            textStyle: {
                baseline : 'top'
            }
        }
    },
}
var labelBottom = {
    normal : {
        color: '#ccc',
        label : {
            show : true,
            position : 'center'
        },
        labelLine : {
            show : false
        }
    },
    emphasis: {
        color: 'rgba(0,0,0,0)'
    }
};
var radius = [40, 55];
var color6 = ["#5ab1ef", "#ffb981", "#d97e84","#8d99b3","#2ec7c9","#b7a3df","#ffb981", "#d97e84","#8d99b3","#2ec7c9"]
var option6 = {
	color:color6,
    legend: {
        x : 'center',
        y : 'center',
        data:[
            '商水豆庄小学', '商水范楼小学', '商水周庄大学', '商水路庙小学', '商水着慌庄小学', '商水周庄小学', '商水豆庄大学', '商水范楼大学','商水路庙小学','商水范楼大学'
        ]
    },
    title : {
        text: '各校考试合格占比',
        x: 'center'
    },
    toolbox: {
        show : true,
        feature : {
            magicType : {
                show: true, 
                type: ['pie', 'funnel'],
                option: {
                    funnel: {
                        width: '20%',
                        height: '30%',
                        itemStyle : {
                            normal : {
                                label : {
                                    formatter : function (params){
                                        return 'other\n' + params.value + '%\n'
                                    },
                                    textStyle: {
                                        baseline : 'middle'
                                    }
                                }
                            },
                        } 
                    }
                }
            }
        }
    },
   series : [
        {
            type : 'pie',
            center : ['10%', '30%'],
            radius : radius,
            x: '0%', // for funnel
            itemStyle : labelFromatter,
            data : [
                {name:'other', value:75, itemStyle : labelBottom},
                {name:'商水豆庄小学', value:25,itemStyle : labelTop}
            ]
        },
        {
            type : 'pie',
            center : ['30%', '30%'],
            radius : radius,
            x:'20%', // for funnel
            itemStyle : labelFromatter,
            data : [
                {name:'other', value:76, itemStyle : labelBottom},
                {name:'商水范楼小学', value:24,itemStyle : labelTop}
            ]
        },
        {
            type : 'pie',
            center : ['50%', '30%'],
            radius : radius,
            x:'40%', // for funnel
            itemStyle : labelFromatter,
            data : [
                {name:'other', value:86, itemStyle : labelBottom},
                {name:'商水周庄小学', value:14,itemStyle : labelTop}
            ]
        },
        {
            type : 'pie',
            center : ['70%', '30%'],
            radius : radius,
            x:'60%', // for funnel
            itemStyle : labelFromatter,
            data : [
                {name:'other', value:89, itemStyle : labelBottom},
                {name:'商水路庙小学', value:11,itemStyle : labelTop}
            ]
        },
        {
            type : 'pie',
            center : ['90%', '30%'],
            radius : radius,
            x:'80%', // for funnel
            itemStyle : labelFromatter,
            data : [
                {name:'other', value:73, itemStyle : labelBottom},
                {name:'商水着慌庄小学', value:27,itemStyle : labelTop}
            ]
        },
        {
            type : 'pie',
            center : ['10%', '70%'],
            radius : radius,
            y: '55%',   // for funnel
            x: '0%',    // for funnel
            itemStyle : labelFromatter,
            data : [
                {name:'other', value:85, itemStyle : labelBottom},
                {name:'商水周庄大学', value:15,itemStyle : labelTop}
            ]
        },
        {
            type : 'pie',
            center : ['30%', '70%'],
            radius : radius,
            y: '55%',   // for funnel
            x:'20%',    // for funnel
            itemStyle : labelFromatter,
            data : [
                {name:'other', value:46, itemStyle : labelBottom},
                {name:'商水豆庄小学', value:54,itemStyle : labelTop}
            ]
        },
        {
            type : 'pie',
            center : ['50%', '70%'],
            radius : radius,
            y: '55%',   // for funnel
            x:'40%', // for funnel
            itemStyle : labelFromatter,
            data : [
                {name:'other', value:74, itemStyle : labelBottom},
                {name:'商水范楼大学', value:26,itemStyle : labelTop}
            ]
        },
        {
            type : 'pie',
            center : ['70%', '70%'],
            radius : radius,
            y: '55%',   // for funnel
            x:'60%', // for funnel
            itemStyle : labelFromatter,
            data : [
                {name:'other', value:75, itemStyle : labelBottom},
                {name:'商水路庙大学', value:25,itemStyle : labelTop}
            ]
        },
        {
            type : 'pie',
            center : ['90%', '70%'],
            radius : radius,
            y: '55%',   // for funnel
            x:'80%', // for funnel
            itemStyle : labelFromatter,
            data : [
                {name:'other', value:72, itemStyle : labelBottom},
                {name:'商水范楼小学', value:28,itemStyle : labelTop}
            ]
        }
    ]
};

myChart6.setOption(option6);