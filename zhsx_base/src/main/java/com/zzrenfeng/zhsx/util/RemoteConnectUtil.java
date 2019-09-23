package com.zzrenfeng.zhsx.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

/**
 * 远程连接 工具类.
 * 
 * @author 田杰熠
 *
 */
public class RemoteConnectUtil {

	/**
	 * 远程get 连接.
	 * 
	 * @param hm
	 */
	public static void getRemoteConnect(String _url) {
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

}
