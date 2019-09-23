package com.zzrenfeng.videoTranscode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 视频格式转换-file工程文件路径_Servlet
 * 
 * @author 田杰熠
 *
 */
public class FormatConversionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FormatConversionServlet() {
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

		String path = request.getParameter("path");
		if (path == null) {
			path = "/upload";
		}
		final String uploadPath = request.getSession().getServletContext().getRealPath(path);
		final String filePath = request.getParameter("filePath");
		final String id = request.getParameter("id");
		final String webpath = request.getParameter("webpath");
		final String filePath1 = filePath.substring(0, filePath.lastIndexOf("."));

		Thread thread = new Thread() {
			@Override
			public void run() {
				VideoTranscodingUtil.videoTranscodeUpload(uploadPath + filePath, filePath1 + ".flv",
						uploadPath + filePath1 + ".jpg", id, webpath);
				super.run();
			}
		};
		thread.start();
		response.getWriter().append("" + true);
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
