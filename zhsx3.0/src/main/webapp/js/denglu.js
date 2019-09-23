function check() {
	var res = true;
	var username = $("input[name='username']").val();
	if (username === "") {
		res = false;
	}
	var password = $("input[name='password']").val();
	if (password === "") {
		res = false;
	}
	return res;
}

$('#qrcode').qrcode({
	render : "canvas",
	size : 100,
	text : '{"channel" : "' + channel + '","url":"' + webUrl + '"}'
});
$(".saoma").click(function() {
	connect();
	$(".deng_kuang").hide();
	$(".er_deng_kuang").show();
});
$(".mmdl").click(function() {
	disConnect();
	$(".deng_kuang").show();
	$(".er_deng_kuang").hide();
})

// 扫码登录
var stompClient = null;

// 订阅
function connect() {
	var socket = new SockJS(base + "/endpointWisely");
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		console.log("---" + frame);
		stompClient.subscribe("/topic/getResponse/" + channel, function(
				respnose) {
			PCLoginPlatform(JSON.parse(respnose.body).responseMessage);
		});
	});
}

function disConnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
}

function PCLoginPlatform(message) {
	var result = message.split(",");
	$("input[name='username']").val(result[0]);
	$("input[name='password']").val(result[1]);
	$("#myform").submit();
}