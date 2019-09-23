var myChart_school = echarts.init(document.getElementById('tongji_info_school'));
/*取得学校数据  */
var schoolData=[];
$("#schoolcount tr").find('td:eq(0)').each(function(){
    schoolData.push($(this).text());
})
if(otherDevice!='A'){
	schoolData.push("其它学校");//手动添加	
}

var valueName=[];
$("#schoolcount tr").each(function(){
	var temp = {};
    temp.value = $(this). find('td:eq(2)').text();
    temp.name =  $(this).find('td:eq(0)').text();
    valueName.push(temp);
})
if(otherDevice!='A'){
	var tempT = {};
	tempT.value = otherDevice;
	tempT.name =  "其它学校";
	valueName.push(tempT);
}

var option_school = {
    title : {
        text: '学校使用统计',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {//去掉左侧图标显示
        orient: 'vertical',
        left: 'left',
        data: schoolData
    },
    series : [
        {
            name: '学校使用统计',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:valueName,
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
myChart_school.setOption(option_school);
