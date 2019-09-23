package com.zzrenfeng.videoTranscode;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @Description 视频格式转换-red5路径_Servlet
 * @author 田杰熠
 * @copyright {@link zzrenfeng.com}
 * @version 2018年8月29日 下午5:30:47
 * @see com.zzrenfeng.videoTranscode.VideoFormatConvertingServlet
 *
 */
public class VideoFormatConvertingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String videoSave = "";

	public VideoFormatConvertingServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		String path = request.getParameter("path");
		if (path == null) {
			path = "/upload";
		}

		if (videoSave.isEmpty()) {
			InputStream in = VideoTranscodingUtil.class.getClassLoader().getResourceAsStream("commonConfig.properties");
			Properties prop = new Properties();
			try {
				prop.load(in);
			} catch (IOException e) {
				videoSave = "/usr/local/rfmeetinglinux62/webapps/vod/streams";
			}
			if (prop.isEmpty()) {
				videoSave = "/usr/local/rfmeetinglinux62/webapps/vod/streams";
			} else {
				videoSave = prop.getProperty("video.save");
			}
		}
		String filePath = request.getParameter("filePath");
		String uploadPath = request.getSession().getServletContext().getRealPath(path);
		String filePath1 = filePath.substring(0, filePath.lastIndexOf(".")); // 去后缀文件路径

		final String newVideoSave = request.getParameter("newVideoSave"); // 新视频路径
		final String originalVideoSave = videoSave + filePath; // 原视频存放路径
		final String photoSavePath = uploadPath + filePath1 + ".jpg"; // 封面保存路径
		final String id = request.getParameter("id");
		final String webpath = request.getParameter("webpath"); // 转码进度回调路径

		Thread thread = new Thread() {
			@Override
			public void run() {
				VideoTranscodingUtil.videoTranscodeUpload(originalVideoSave, newVideoSave, photoSavePath, id, webpath);
				super.run();
			}
		};
		thread.start();
		response.getWriter().append("" + true);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setHeader("Access-Control-Allow-Credentials", "false");
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setHeader("Access-Control-Allow-Methods", "*");
		resp.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		resp.setContentType("application/json");
		resp.setCharacterEncoding("utf-8");
		super.doOptions(req, resp);
	}

}
