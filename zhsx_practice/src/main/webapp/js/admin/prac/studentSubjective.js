/**
 * 根据手写板参数绘制笔迹
 * @param pointList
 */
function drawing(pointList) {
	var w = 0.00000;
	var h = 0.00000;
	w = 1024 / 22600;
	h = 768 / 16650;

	console.log(222222222);
	var canvas = document.getElementById("myCanvas");
	// 检测当前浏览器是否支持Canvas对象
	if (canvas.getContext) {
		var ctx = canvas.getContext("2d");

		// 定义直线的起点坐标为
		// 定义直线的终点坐标为
		// 沿着坐标点顺序的路径绘制直线
		// 关闭当前的绘制路径

		ctx.translate(0, 1024);
		ctx.rotate(-Math.PI / 2);
		ctx.beginPath();
		for (var i = 0; i < pointList.length; i++) {
			var point = pointList[i];
			if (point.us == 17) {
				ctx.moveTo(point.x * w, point.y * h);
				if ((i + 1) < pointList.length) {
					var point1 = pointList[(i + 1)];
					ctx.lineTo(point1.x * w, point1.y * h);
					// if(point1.us==17){
					// ctx.lineTo(point1.x*w, point1.y*h);
					// }
				}
			}
		}
		ctx.stroke();
		ctx.closePath();
	}
}
