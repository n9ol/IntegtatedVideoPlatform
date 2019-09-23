package com.zzrenfeng.zhsx.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UdpSend {
	// 发送数据的套接字变量
	private DatagramSocket ds = null;
	// UDP的数据包变量
	private DatagramPacket dp = null;

	// 已经自行实例化
	private static final UdpSend single = new UdpSend();

	private UdpSend() {
		try {
			ds = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static UdpSend getInstance() {
		return single;
	}

	public void sendStartData(String ip, int port, String classcode) {
		String sendData = "publish_start|";
		sendData = sendData + classcode + "\n";
		byte[] sendStart = sendData.getBytes();
		// 指定需要发送的数据内容,数据长度,目的IP和目的端口号
		try {
			dp = new DatagramPacket(sendStart, sendStart.length, InetAddress.getByName(ip), port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 发送数据
		if (ds != null) {
			try {
				ds.send(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendStartData(String ip, int port) {
		byte[] sendStart = { 0x01, 0x68, 0x33, 0x04 };
		// 指定需要发送的数据内容,数据长度,目的IP和目的端口号
		try {
			dp = new DatagramPacket(sendStart, sendStart.length, InetAddress.getByName(ip), port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 发送数据
		if (ds != null) {
			try {
				ds.send(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendStopData(String ip, int port) {
		byte[] sendStart = { 0x01, 0x68, 0x33, 0x05 };
		// 指定需要发送的数据内容,数据长度,目的IP和目的端口号
		try {
			dp = new DatagramPacket(sendStart, sendStart.length, InetAddress.getByName(ip), port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 发送数据
		if (ds != null) {
			try {
				ds.send(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
