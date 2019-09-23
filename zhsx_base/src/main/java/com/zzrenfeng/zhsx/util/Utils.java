package com.zzrenfeng.zhsx.util;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 工具类
 * 
 * @author 田杰熠
 *
 */
public class Utils {

	/**
	 * 文件大小转换
	 * 
	 * @param length
	 * @return
	 */
	public static String sizeToString(long length) {
		String size = null;
		DecimalFormat df = new DecimalFormat("#.##");
		if (length == 0)
			return "0M";
		else if (length < 1024) {
			size = df.format(length) + "B";
		} else if (length < Math.pow(1024, 2)) {
			size = df.format(length / 1024) + "K";
		} else if (length < Math.pow(1024, 3)) {
			size = df.format(length / Math.pow(1024, 2)) + "M";
		} else {
			size = df.format(length / Math.pow(1024, 3)) + "G";
		}
		return size;
	}

	/**
	 * 判断是否是IE浏览器
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isMSBrowser(HttpServletRequest request) {
		String[] IEBrowserSignals = { "MSIE", "Trident", "Edge" };
		String userAgent = request.getHeader("User-Agent");
		for (String signal : IEBrowserSignals) {
			if (userAgent.contains(signal)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获得访问_ip对应的播放路径
	 * 
	 * @param request
	 * @param str
	 * @return
	 */
	public static String getAccessPathUrlOrIP(HttpServletRequest request, String urlOrIPs) {
		String urlOrIP = null;
		String ip = request.getServerName();
		String ipBlock = ip.substring(0, ip.lastIndexOf("."));

		String[] urlOrIPArray = urlOrIPs.split(",|，");
		if (urlOrIPArray.length <= 1) {
			urlOrIP = urlOrIPs;
		} else {
			for (int i = 0; i < urlOrIPArray.length; i++) {
				String s = urlOrIPArray[i];
				if (s.indexOf(ipBlock) != -1) {
					urlOrIP = s;
					break;
				}
			}
		}
		if (urlOrIP == null || urlOrIP.isEmpty()) {
			urlOrIP = urlOrIPArray[0];
		}
		return urlOrIP;
	}

	/**
	 * 获取Ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAdrress(HttpServletRequest request) {
		String Xip = request.getHeader("X-Real-IP");
		String XFor = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = XFor.indexOf(",");
			if (index != -1) {
				return XFor.substring(0, index);
			} else {
				return XFor;
			}
		}
		XFor = Xip;
		if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
			return XFor;
		}
		if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
			XFor = request.getRemoteAddr();
		}
		return XFor;
	}

}
