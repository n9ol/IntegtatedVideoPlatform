package com.zzrenfeng.videoTranscode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 格式转换_Servlet
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
		if (path == null)
			path = "/upload";
		String uploadPath = request.getSession().getServletContext().getRealPath(path);
		String filePath = request.getParameter("filePath");
		String id = request.getParameter("id");
		String webpath = request.getParameter("webpath");
		String filePath1 = filePath.substring(0, filePath.lastIndexOf("."));
		boolean isok = VideoTranscodingUtil.videoTranscodeUpload(uploadPath + filePath, filePath1 + ".flv",
				uploadPath + filePath1 + ".jpg", id, webpath);
		response.getWriter().append("" + isok);
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
