
var myChart_class = echarts.init(document.getElementById('tongji_info_class'));
/*取得班级数据*/
var classData=[];
$("#classcount tr").find('td:eq(0)').each(function(){
	classData.push($(this).text());
})
if(otherDevice!='A'){
	classData.push("其它班级");//手动添加	
}
	
var valueName=[];
$("#classcount tr").each(function(){
	var temp = {};
    temp.value = $(this). find('td:eq(2)').text();
    temp.name =  $(this).find('td:eq(0)').text();
    valueName.push(temp);
}) 
if(otherDevice!='A'){
	var tempT = {};
	tempT.value = otherDevice;
	tempT.name =  "其它班级";
	valueName.push(tempT);
}

var option_class = {
    title : {
        text: '班级使用统计',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: classData
    },
    series : [
        {
            name: '班级使用统计',
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
myChart_class.setOption(option_class);
