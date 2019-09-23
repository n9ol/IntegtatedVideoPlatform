package com.zzrenfeng.fileDownload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzrenfeng.videoTranscode.VideoTranscodingUtil;

/**
 * Servlet implementation class VideoFileDownloadServlet
 */
public class VideoFileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String videoSave = "";

	public VideoFileDownloadServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		String filepath = request.getParameter("filepath");
		String filename = request.getParameter("filename");

		if (videoSave.isEmpty()) {
			InputStream in = VideoTranscodingUtil.class.getClassLoader().getResourceAsStream("commonConfig.properties");
			Properties prop = new Properties();
			try {
				prop.load(in);
			} catch (IOException e) {
				videoSave = "/usr/local/rfmeetinglinux61/webapps/vod/streams";
			}
			if (prop.isEmpty()) {
				videoSave = "/usr/local/rfmeetinglinux61/webapps/vod/streams";
			} else {
				videoSave = prop.getProperty("video.save");
			}
		}

		filepath = videoSave + filepath;
		filename = filename + ".flv";
		DownloadUtils.downloadLocal(response, request, filepath, filename);
	}

	@Override
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
