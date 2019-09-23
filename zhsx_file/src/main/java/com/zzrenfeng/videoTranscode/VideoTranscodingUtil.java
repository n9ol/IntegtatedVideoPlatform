package com.zzrenfeng.videoTranscode;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.zzrenfeng.util.Utils;

public class VideoTranscodingUtil {

	private static String ffmpegPath = "";
	private static String videoSave = "";

	// 获取转码工具存放路径,获得视频转码后存储路径
	public static void readPath() {
		if (ffmpegPath.isEmpty() || videoSave.isEmpty()) {
			InputStream in = VideoTranscodingUtil.class.getClassLoader().getResourceAsStream("commonConfig.properties");
			Properties prop = new Properties();
			try {
				prop.load(in);
			} catch (IOException e) {
				ffmpegPath = "/usr/bin/ffmpeg";
				videoSave = "/usr/local/rfmeetinglinux61/webapps/vod/streams";
			}
			if (prop.isEmpty()) {
				ffmpegPath = "/usr/bin/ffmpeg";
				videoSave = "/usr/local/rfmeetinglinux61/webapps/vod/streams";
			} else {
				ffmpegPath = prop.getProperty("ffmpeg.path");
				videoSave = prop.getProperty("video.save");
			}
		}
	}

	/**
	 * 视频转码截图上传 (转码工具 ffmpeg.exe)
	 * 
	 * @param upFilePath
	 *            用于指定要转换格式的原文件路径,要截图的视频原文件路径
	 * @param codcFilePath
	 *            格式转换后的的文件保存路径
	 * @param mediaPicPath
	 *            截图保存路径
	 * @return
	 */
	public static boolean videoTranscodeUpload(String upFilePath, String codcFilePath, String mediaPicPath, String id,
			String webpath) {

		readPath();

		codcFilePath = videoSave + codcFilePath;
		File file = new File(codcFilePath.substring(0, codcFilePath.lastIndexOf("/")));
		if (!file.exists()) {
			file.mkdirs();
		}

		// 创建一个List集合来保存转换视频文件为flv格式的命令
		List<String> convert = new ArrayList<String>();
		convert.add(ffmpegPath); // 添加转换工具路径
		convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
		convert.add(upFilePath); // 添加要转换格式的视频文件的路径
		convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
		convert.add("-ab"); // 设置音频码率
		convert.add("32");
		convert.add("-ar"); // 设置声音的采样频率
		convert.add("22050");
		convert.add("-qscale"); // 指定转换的质量 取值0.01-255，约小质量越好
		convert.add("7");
		convert.add("-r"); // 设置帧频
		convert.add("15");
		convert.add(codcFilePath);

		// 创建一个List集合来保存从视频中截取图片的命令
		List<String> cutpic = new ArrayList<String>();
		cutpic.add(ffmpegPath);
		cutpic.add("-y");
		cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
		cutpic.add("00:00:05");
		cutpic.add("-i");
		cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
		cutpic.add("-f");
		cutpic.add("image2");
		cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
		cutpic.add("320*240"); // 添加截取的图片大小为320*240
		cutpic.add(mediaPicPath); // 添加截取的图片的保存路径

		boolean mark = true;
		ProcessBuilder builder = new ProcessBuilder();
		try {
			builder.command(convert);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			doWaitPro(p, id, webpath);
			p.destroy();

			builder.command(cutpic);
			builder.redirectErrorStream(true);
			p = builder.start();
			doWaitProPic(p);
			p.destroy();

			// 删除原文件
			File f = new File(upFilePath);
			if (f.exists()) {
				f.delete();
			}

		} catch (Exception e) {
			mark = false;
			System.out.println(e);
			e.printStackTrace();
		}
		return mark;
	}

	/**
	 * 视频截图 (转码工具 ffmpeg.exe)
	 * 
	 * @param upFilePath
	 * @param mediaPicPath
	 * @return
	 */
	public static boolean videoScreenshot(String upFilePath, String mediaPicPath) {
		readPath();

		upFilePath = videoSave + upFilePath;
		File file = new File(mediaPicPath.substring(0, mediaPicPath.lastIndexOf("/")));
		if (!file.exists()) {
			file.mkdirs();
		}

		// 创建一个List集合来保存从视频中截取图片的命令
		List<String> cutpic = new ArrayList<String>();
		cutpic.add(ffmpegPath);
		cutpic.add("-y");
		cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
		cutpic.add("00:00:05");
		cutpic.add("-i");
		cutpic.add(upFilePath); // 同上（指定的文件即可以是转换为flv格式之前的文件，也可以是转换的flv文件）
		cutpic.add("-f");
		cutpic.add("image2");
		cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
		cutpic.add("320*240"); // 添加截取的图片大小为320*240
		cutpic.add(mediaPicPath); // 添加截取的图片的保存路径

		boolean mark = true;
		ProcessBuilder builder = new ProcessBuilder();
		try {
			builder.command(cutpic);
			builder.redirectErrorStream(true);

			Process p = builder.start();
			p = builder.start();
			doWaitProPic(p);
			p.destroy();
		} catch (Exception e) {
			mark = false;
			System.out.println(e);
			e.printStackTrace();
		}
		return mark;
	}

	/**
	 * 等待转码线程处理完成
	 * 
	 * @param p
	 * @param id
	 * @param webpath
	 */
	public static void doWaitPro(Process p, String id, String webpath) {
		try {
			String errorMsg = readInputStream(p.getErrorStream(), "error", id, webpath);
			String outputMsg = readInputStream(p.getInputStream(), "out", id, webpath);
			int c = p.waitFor();
			if (c != 0) {// 如果处理进程在等待
				System.out.println("处理失败：" + errorMsg);
			} else {
				String size = outputMsg
						.substring(outputMsg.lastIndexOf("Lsize=") + 6, outputMsg.lastIndexOf("kB time=")).trim();
				size = Utils.sizeToString(Double.valueOf(size));
				String timeLength = outputMsg
						.substring(outputMsg.lastIndexOf("time=") + 5, outputMsg.lastIndexOf("bitrate=")).trim();
				timeLength = timeLength.substring(0, timeLength.lastIndexOf(".")).trim();
				Map<String, Object> hm = new HashMap<>();
				hm.put("id", id);
				hm.put("timeLength", timeLength);
				hm.put("progress", 100);
				hm.put("transcodingState", "O");
				hm.put("size", size);
				String _url = webpath + "?id=" + hm.get("id") + "&timeLength=" + hm.get("timeLength") + "&progress="
						+ hm.get("progress") + "&transcodingState=" + hm.get("transcodingState") + "&size="
						+ hm.get("size");
				Utils.remoteUpdateDatabase(_url);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取转码线程 @Title: readInputStream @Description: 完成进度百分比 @param @return
	 * String @throws
	 */
	private static String readInputStream(InputStream is, String f, String id, String webpath) throws IOException {
		int COMPLETE = 0;
		// 将进程的输出流封装成缓冲读者对象
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer lines = new StringBuffer();// 构造一个可变字符串
		long totalTime = 0;

		Map<String, Object> hm = new HashMap<>();
		hm.put("id", id);
		hm.put("transcodingState", "C");
		// 对缓冲读者对象进行每行循环
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			lines.append(line);// 将每行信息字符串添加到可变字符串中
			int positionDuration = line.indexOf("Duration:");// 在当前行中找到第一个"Duration:"的位置
			int positionTime = line.indexOf("time=");
			if (positionDuration > 0) {// 如果当前行中有"Duration:"
				String dur = line.replace("Duration:", "");// 将当前行中"Duration:"替换为""
				dur = dur.trim().substring(0, 8);// 将替换后的字符串去掉首尾空格后截取前8个字符
				int h = Integer.parseInt(dur.substring(0, 2));// 封装成小时
				int m = Integer.parseInt(dur.substring(3, 5));// 封装成分钟
				int s = Integer.parseInt(dur.substring(6, 8));// 封装成秒
				totalTime = h * 3600 + m * 60 + s;// 得到总共的时间秒数
			}
			if (positionTime > 0) {// 如果所用时间字符串存在
				// 截取包含time=的当前所用时间字符串
				String time = line.substring(positionTime, line.indexOf("bitrate") - 1);
				time = time.substring(time.indexOf("=") + 1, time.indexOf("."));// 截取当前所用时间字符串
				int h = Integer.parseInt(time.substring(0, 2));// 封装成小时
				int m = Integer.parseInt(time.substring(3, 5));// 封装成分钟
				int s = Integer.parseInt(time.substring(6, 8));// 封装成秒
				long hasTime = h * 3600 + m * 60 + s;// 得到总共的时间秒数
				float t = (float) hasTime / (float) totalTime;// 计算所用时间与总共需要时间的比例
				COMPLETE = (int) Math.floor(t * 100);// 计算完成进度百分比
			}
			hm.put("progress", COMPLETE);
			String _url = webpath + "?id=" + hm.get("id") + "&timeLength=" + hm.get("timeLength") + "&progress="
					+ hm.get("progress") + "&transcodingState=" + hm.get("transcodingState") + "&size="
					+ hm.get("size");
			Utils.remoteUpdateDatabase(_url);
		}
		br.close();// 关闭进程的输出流
		return lines.toString();
	}

	/**
	 * 等待截图线程 结束
	 * 
	 * @param p
	 * @throws IOException
	 */
	public static void doWaitProPic(Process p) throws IOException {
		try {
			String errorMsg = readInputStreamPic(p.getErrorStream(), "error");
			String outputMsg = readInputStreamPic(p.getInputStream(), "out");
			int c = p.waitFor();
			if (c != 0) {// 如果处理进程在等待
				System.out.println("处理失败" + errorMsg);
			} else {
				System.out.println("处理成功" + outputMsg);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取截图线程
	 * 
	 * @param is
	 * @param f
	 * @return
	 * @throws IOException
	 */
	private static String readInputStreamPic(InputStream is, String f) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuffer lines = new StringBuffer();// 构造一个可变字符串
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			lines.append(line);// 将每行信息字符串添加到可变字符串中
		}
		br.close();// 关闭进程的输出流
		return lines.toString();
	}

}
