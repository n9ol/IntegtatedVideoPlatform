package com.zzrenfeng.zhsx.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UserNamePasswordUserTypeToken extends UsernamePasswordToken {

	/**
	 * 
	 * 用于接收用户名，密码，类型的token
	 */
	private static final long serialVersionUID = 1L;
	/** 接收验证码 */
	private String usertype;

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public UserNamePasswordUserTypeToken() {
		super();
	}

	/** 重写shiro的token */
	public UserNamePasswordUserTypeToken(String username, String password) {
		super(username, password);
	}

	public UserNamePasswordUserTypeToken(String username, char[] password, boolean remeberme, String host,
			String usertype) {
		super(username, password, remeberme, host);
		this.usertype = usertype;
	}

}
