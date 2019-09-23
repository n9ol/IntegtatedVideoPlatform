package com.zzrenfeng.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.text.DecimalFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 工具类
 * 
 * @author 田杰熠
 *
 */
public class Utils {

	/**
	 * 将MAP转换成JSON字符串
	 * 
	 * @param map
	 * @return
	 */
	public static String mapJSON(Map<String, Object> map) {
		String json = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			json = objectMapper.writeValueAsString(map);
			System.gc();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 转换视频大小格式
	 * 
	 * @param length
	 * @return
	 */
	public static String sizeToString(double length) {
		String size = null;
		DecimalFormat df = new DecimalFormat("#.##");
		if (length == 0) {
			size = "0M";
		} else if (length < 1024) {
			size = df.format(length) + "K";
		} else if (length < Math.pow(1024, 2)) {
			size = df.format(length / 1024) + "M";
		} else if (length < Math.pow(1024, 3)) {
			size = df.format(length / Math.pow(1024, 2)) + "G";
		}
		return size;
	}

	/**
	 * 远程更新数据库
	 * 
	 * @param hm
	 */
	public static void remoteUpdateDatabase(String _url) {
		try {
			java.net.URL url = new java.net.URL(_url);
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			if (conn.getResponseCode() == 200) {
				System.out.println("连接成功");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

}
