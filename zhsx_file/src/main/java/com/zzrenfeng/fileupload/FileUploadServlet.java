package com.zzrenfeng.fileupload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

/**
 * 文件上传_Servlet
 * 
 * @author 田杰熠
 *
 */
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileUploadServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		// 上传到服务器的路径 根路径
		String path = request.getParameter("path");
		if (path == null)
			path = "/upload";
		String uploadPath = request.getSession().getServletContext().getRealPath(path);

		// 创建DiskFileItemFactory对象,配置缓存信息
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("utf-8");
		String fileMd5 = null;
		String chunk = null;

		try {
			List<FileItem> items = sfu.parseRequest(request);
			for (FileItem item : items) {
				if (item.isFormField()) {

					String fileName = item.getFieldName();
					if (fileName.equals("fileMd5")) {
						fileMd5 = item.getString("utf-8");
					}
					if (fileName.equals("chunk")) {
						chunk = item.getString("utf-8");
					}
				} else {

					File file = new File(uploadPath + "/" + fileMd5);
					if (!file.exists()) {
						file.mkdir();
					}

					InputStream is = item.getInputStream();
					File chunkFile = new File(uploadPath + "/" + fileMd5 + "/" + chunk);

					FileUtils.copyInputStreamToFile(is, chunkFile);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
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
