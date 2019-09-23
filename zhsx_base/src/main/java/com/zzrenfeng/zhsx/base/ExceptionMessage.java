package com.zzrenfeng.zhsx.base;

/**
 * 异常信息类
 * 
 * @author 田杰熠
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ExceptionMessage extends Exception {

	private String message;

	public ExceptionMessage(String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
