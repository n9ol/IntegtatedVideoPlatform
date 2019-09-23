layui.use([ 'layedit' ], function() {
	var layedit = layui.layedit;

	var index0 = layedit.build('bianji', {
		height : 100,
		tool : [ 'strong' // 加粗
		, 'italic' // 斜体
		, 'underline' // 下划线
		, 'del' // 删除线
		, '|' // 分割线
		, 'left' // 左对齐
		, 'center' // 居中对齐
		, 'right' // 右对齐
		, 'face' // 表情
		]
	}); // 建立编辑器

	$('#sent').click(function() {
		var msg = layedit.getContent(index0);
		var mm = layedit.getText(index0);
		if (!$.trim(mm)) {
			return false;
		} else {
			if (mm.length > 3000) {
				alert("输入过长");
				return false;
			}
			var data = JSON.stringify({
				userId : u_id,
				photo : u_filePath,
				mType : mType,
				userName : u_name,
				noTagMsg:mm,
				msg : msg
			});

			sendMsg(data);
		}
	});

	$('#reset').click(function() {
		clearMsg();
	});

});

var cacheData = new Array();
$(function() {
	var ca = window.localStorage.getItem(sourceId);
	if (ca)
		cacheData = ca.split('_;_');
	for (da in cacheData) {
		var data = JSON.parse(cacheData[da]);
		showMag(data.photo, data.userName, data.msg, data.timeStamp);
	}
});

var url = 'ws://' + ip_port + ctx + '/ws/' + sourceId;

var webSocket = null;

if ('WebSocket' in window) {
	webSocket = new WebSocket(url);
} else {
	alert('当前浏览器不支持websocket');
}

webSocket.onopen = function(event) {

};

// 收到服务端消息
webSocket.onmessage = function(msg) {
	var data = JSON.parse(msg.data);
	showMag(data.photo, data.userName, data.msg, data.timeStamp);
	cacheData.push(msg.data);
	window.localStorage.setItem(sourceId, cacheData.join("_;_"));
};

// 异常
webSocket.onerror = function(event) {
	console.log(event);
};
// 断线
webSocket.onclose = function() {
	console.log("连接断开");
};

// 初始化按钮点击事件函数
function sendMsg(message) {
	webSocket.send(message);
	try {
		$("#LAY_layedit_1")[0].contentDocument.body.innerHTML = "";
	} catch (e) {
		console.error(e);
	}
};

function showMag(photo, userName, msg, timeStamp) {
	var html = '<li> <div class="liuyan_touxiang"> <span id="dengji">'
			+ '<img src="' + fileWebPath + '/userheadPortrait/' + photo
			+ '"  onerror="this.src=\'' + ctx + '/img/user80.jpg\'" />'
			+ '</span> </div>'
			+ '<div class="liuyan_con">  <div class="liuyan_name">'
			+ '    <span id="yonghu">' + userName + '</span>' + msg
			+ '  </div>' + '   <div class="liuyan_time"><span id="times">'
			+ timeStamp + '</span></div>' + '</div> </li>';
	$('#baiban').append(html);
	var div = document.getElementById('baiban');
	div.scrollTop = div.scrollHeight;
}

function clearMsg() {
	window.localStorage.clear();
	$('#baiban').empty();
}