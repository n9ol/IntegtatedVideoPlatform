package com.zzrenfeng.fileupload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 文件上传,不分块 _servlet
 * 
 * @author 田杰熠
 *
 */
public class FileUploadNotBlockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileUploadNotBlockServlet() {
		super();
	}

	@Override
	@SuppressWarnings("rawtypes")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					System.out.println("是一个普通文本表单字段");
				} else {
					// 文件重命名
					String originaFilename = item.getName();
					String fileType = originaFilename.substring(originaFilename.lastIndexOf("."));
					String newFilename = new Date().getTime() + String.valueOf(originaFilename.hashCode()) + fileType;
					newFilename = newFilename.replaceAll("-", "").trim();

					// 目录分级处理
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
					String a = sdf.format(new Date());
					SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
					String b = sdf1.format(new Date());
					String randomDirectory = "/" + a + "/" + b;

					// 上传到服务器的路径 根路径
					String path = request.getParameter("path");
					if (path == null)
						path = "/upload";
					String uploadPath = request.getSession().getServletContext().getRealPath(path);

					// 获得文件路径,如果路径不存在进行创建
					File filepath = new File(uploadPath, randomDirectory);
					if (!filepath.exists()) {
						filepath.mkdirs();
					}

					// 上传文件
					BufferedInputStream bis = new BufferedInputStream(item.getInputStream());
					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(new File(filepath, newFilename)));
					int len = 0;
					byte[] buffer = new byte[1024];
					while ((len = bis.read(buffer)) > 0) {
						bos.write(buffer, 0, len);
					}
					bos.flush();
					bis.close();
					bos.close();

					String json = "{\"path\":\"" + randomDirectory + "/" + newFilename + "\"}";

					response.setCharacterEncoding("utf-8");
					response.setContentType("application/json;charset=utf-8");
					response.getWriter().write(json);
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

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
