var colors = ['#4596e5','orangered','yellowgreen','gold','salmon']
var myChart_fixed = echarts.init(document.getElementById('tongji_info_fixed'));
 var option_fixed = {
 	color:colors,
    title : {
        text: '数据统计',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['非常满意','满意','一般','不满意','非常不满意']
    },
    series : [
        {
            name: '数据统计',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:[
                {value:135, name:'非常满意'},
                {value:310, name:'满意'},
                {value:234, name:'一般'},
                {value:549, name:'不满意'},
                {value:336, name:'非常不满意'}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
myChart_fixed.setOption(option_fixed);
