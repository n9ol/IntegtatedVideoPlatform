package com.zzrenfeng.zhsx.model.webSocket;

/**
 * 服务端向浏览器发送消息的model类-webSocket.
 * 
 * @author 田杰熠
 *
 */
public class WiselyResponse {

	private String responseMessage;

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public WiselyResponse(String responseMessage) {
		super();
		this.responseMessage = responseMessage;
	}

}
