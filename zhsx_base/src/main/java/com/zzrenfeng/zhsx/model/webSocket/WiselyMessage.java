package com.zzrenfeng.zhsx.model.webSocket;

/**
 * 接受浏览器向服务端发送的消息-webSocket.
 * 
 * @author 田杰熠
 *
 */
public class WiselyMessage {

	private String message;

	private String channel;

	public String getMessage() {
		return message;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "WiselyMessage [message=" + message + ", channel=" + channel + "]";
	}

}
