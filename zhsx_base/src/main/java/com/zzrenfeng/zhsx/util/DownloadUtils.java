package com.zzrenfeng.zhsx.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 下载
 * 
 * @author 田杰熠
 * @version 1.0
 */
public class DownloadUtils {

	/**
	 * Internet下载
	 * 
	 * @param response
	 * @param filepath
	 * @param filename
	 * @throws UnsupportedEncodingException
	 */
	public static void downloadInternet(HttpServletResponse response, HttpServletRequest request, String filepath,
			String filename) throws UnsupportedEncodingException {
		response.setContentType("multipart/form-data");
		if (Utils.isMSBrowser(request)) {
			filename = URLEncoder.encode(filename.trim(), "UTF-8");
		} else {
			filename = new String(filename.trim().getBytes("UTF-8"), "ISO-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		HttpURLConnection httpUrl = null;
		URL url = null;

		try {
			url = new URL(filepath);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();

			bis = new BufferedInputStream(httpUrl.getInputStream());
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, len);
			}
			bos.flush();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					if (bis != null)
						bis.close();
					if (bos != null)
						bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 本地下载
	 * 
	 * @param response
	 * @param filepath
	 * @param filename
	 * @throws UnsupportedEncodingException
	 */
	public static void downloadLocal(HttpServletResponse response, HttpServletRequest request, String filepath,
			String filename) throws UnsupportedEncodingException {
		response.setContentType("multipart/form-data");
		if (Utils.isMSBrowser(request)) {
			filename = URLEncoder.encode(filename.trim(), "UTF-8");
		} else {
			filename = new String(filename.trim().getBytes("UTF-8"), "ISO-8859-1");
		}
		response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(filepath));
			bos = new BufferedOutputStream(response.getOutputStream());
			int len = 0;
			byte[] buffer = new byte[1024];
			while ((len = bis.read(buffer)) > 0) {
				bos.write(buffer, 0, len);
			}
			bos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					if (bis != null)
						bis.close();
					if (bos != null)
						bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
