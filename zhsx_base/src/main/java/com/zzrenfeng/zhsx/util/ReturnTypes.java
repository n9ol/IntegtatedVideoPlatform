package com.zzrenfeng.zhsx.util;

public enum ReturnTypes {
	JSON("application/json;chartset=UTF-8"), HTML("application/html;charset=UTF-8"), TEXT("text/html; charset=UTF-8");

	private final String value;

	ReturnTypes(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

}
