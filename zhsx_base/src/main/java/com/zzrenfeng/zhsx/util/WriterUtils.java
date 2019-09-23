package com.zzrenfeng.zhsx.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author 田杰熠
 * @version 1.0
 */
public class WriterUtils {

	private static String contentTypeHtml = ReturnTypes.HTML.value();
	private static String contentTypeText = ReturnTypes.TEXT.value();
	private static String contentTypeJson = ReturnTypes.JSON.value();

	private static PrintWriter writer;

	private static PrintWriter getWriter(HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer;
	}

	private static void closeWriter(PrintWriter writer) {
		if (writer != null) {
			writer.flush();
			writer.close();
		}
	}

	/**
	 * 数据格式与编码 (application/json; chartset=utf-8)
	 * 
	 * @param response
	 * @param msg
	 */
	public static void toJson(HttpServletResponse response, String msg) {
		response.setContentType(contentTypeJson);
		writer = getWriter(response);
		writer.write(msg);
		closeWriter(writer);
	}

	/**
	 * 数据格式与编码 (application/html; charset=utf-8)
	 * 
	 * @param response
	 * @param msg
	 */
	public static void toHtml(HttpServletResponse response, String msg) {
		response.setContentType(contentTypeHtml);
		writer = getWriter(response);
		writer.write(msg);
		closeWriter(writer);
	}

	/**
	 * 数据格式与编码 (text/html; charset=UTF-8)
	 * 
	 * @param response
	 * @param msg
	 */
	public static void toText(HttpServletResponse response, String msg) {
		response.setContentType(contentTypeText);
		writer = getWriter(response);
		writer.write(msg);
		closeWriter(writer);
	}

}
