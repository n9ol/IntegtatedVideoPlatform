package com.zzrenfeng.zhsx.controller.web.pgselfmotion;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.util.Properties;

public class UDPRece implements Runnable {

	private byte[] buf = new byte[1024];// 接受内容的大小，注意不要溢出
	private String UDP_PORT = "";
	private String webPortUrl = "";

	@Override
	public void run() {
		DatagramSocket ds = null;
		try {
			if (UDP_PORT.isEmpty()) {
				Properties props = new Properties();
				InputStream in;
				in = getClass().getResourceAsStream("/commonConfig.properties");
				try {
					props.load(in);
				} catch (Exception e1) {
					UDP_PORT = "8007";
				}
				if (props.isEmpty()) {
					UDP_PORT = "8007";
				} else {
					UDP_PORT = props.get("UDP.PORT").toString();
				}
			}
			ds = new DatagramSocket(Integer.valueOf(UDP_PORT));
		} catch (SocketException e) {
			e.printStackTrace();
		}

		while (ds != null) {
			DatagramPacket dp = new DatagramPacket(buf, 0, buf.length);// 定义一个接收的包

			try {
				ds.receive(dp); // 将接受内容封装到包中
			} catch (IOException e) {
				e.printStackTrace();
			}

			String data = new String(dp.getData(), 0, dp.getLength());// 利用getData()方法取出内容
			System.out.println(data);
			String[] datas = data.split("&");
			String classCode = datas[0].toString();
			String type = datas[1].toString();
			String timelength = datas[2].toString();

			// 获取网站跟路径
			if (webPortUrl.isEmpty()) {
				Properties props = new Properties();
				InputStream in;
				in = getClass().getResourceAsStream("/commonConfig.properties");
				try {
					props.load(in);
				} catch (Exception e) {
					webPortUrl = "8000/zhsx";
				}
				if (props.isEmpty()) {
					webPortUrl = "8000/zhsx";
				} else {
					webPortUrl = props.get("web.portUrl").toString();
				}
			}
			// 获取网站跟路径
			String _url = "http://127.0.0.1:" + webPortUrl + "/pgSelfMotion/insteraqc?classCode=" + classCode
					+ "&timelength=" + timelength + "&type=" + type;
			System.out.println(_url);
			try {
				java.net.URL url = new java.net.URL(_url);
				java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setDoOutput(true); // 使用 URL 连接进行输出，则将 DoOutput标志设置为 true
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

}
