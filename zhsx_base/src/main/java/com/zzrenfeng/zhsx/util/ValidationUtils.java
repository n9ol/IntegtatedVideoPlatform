package com.zzrenfeng.zhsx.util;

import java.util.regex.Pattern;

/**
 * 验证工具
 * 
 * @author 田杰熠
 *
 */
public class ValidationUtils {

	/**
	 * 验证是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 验证字符串长度
	 * 
	 * @return
	 */
	public static boolean checkStringLength(String str, int min, int max) {
		boolean res = true;
		int length = str.trim().length();
		if (length < min || length > max)
			res = false;
		return res;
	}

	/**
	 * 验证手机号
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkPhone(String str) {
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 验证邮箱
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkEmail(String str) {
		Pattern pattern = Pattern
				.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 验证不能出现特殊符号
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkSymbol(String str) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 验证由 3到16位字母和数字组成
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkLetterAndDigit(String str) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{3,16}$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 验证字符串是否为手机号或邮箱
	 * 
	 * @param str
	 * @return 2代表为手机号,3代表为邮箱
	 */
	public static int checkCodePhoneEmail(String str) {
		int res = 1;
		if (checkPhone(str))
			res = 2;
		else if (checkEmail(str))
			res = 3;
		return res;
	}

}
