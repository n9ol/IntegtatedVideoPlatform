package com.zzrenfeng.fileupload;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzrenfeng.util.Utils;

/**
 * 文件操作_Servlet
 * 
 * @author 田杰熠
 *
 */
public class FileUploadActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileUploadActionServlet() {
		super();
	}

	@Override
	@SuppressWarnings("resource")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

		String action = request.getParameter("action");
		if (action.equals("mergeChunks")) {
			String fileMd5 = request.getParameter("fileMd5");
			File f = new File(uploadPath + "/" + fileMd5);
			// 读取目录下所有文件
			File[] fileArray = f.listFiles(new FileFilter() {

				// 排除目录,只要文件
				@Override
				public boolean accept(File pathname) {
					if (pathname.isDirectory()) {
						return false;
					}
					return true;
				}

			});

			// 从小到大排序
			if (fileArray == null) {
				return;
			}
			List<File> fileList = Arrays.asList(fileArray);
			Collections.sort(fileList, new Comparator<File>() {

				@Override
				public int compare(File o1, File o2) {
					if (Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())) {
						return -1;
					}
					return 1;
				}

			});

			// 合并文件
			String ext = request.getParameter("ext").toLowerCase();
			String uuidString = UUID.randomUUID().toString().replaceAll("-", "");

			// 目录分级处理
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
			String a = sdf.format(new Date());
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
			String b = sdf1.format(new Date());
			String randomDirectory = "/" + a + "/" + b;

			// 获得文件路径,如果路径不存在进行创建
			File filepath = new File(uploadPath, randomDirectory);
			if (!filepath.exists()) {
				filepath.mkdirs();
			}

			File outputFile = new File(filepath + "/" + uuidString + "." + ext);
			outputFile.createNewFile();
			FileChannel outChannel = new FileOutputStream(outputFile).getChannel();
			FileChannel inChannel;
			for (File file : fileList) {
				inChannel = new FileInputStream(file).getChannel();
				inChannel.transferTo(0, inChannel.size(), outChannel);
				inChannel.close();
				file.delete();
			}

			// 清除文件夹
			File tempFile = new File(uploadPath + "/" + fileMd5);
			if (tempFile.isDirectory() && tempFile.exists()) {
				tempFile.delete();
			}

			// 返回数据
			String filePath = randomDirectory + "/" + uuidString + "." + ext;
			Map<String, Object> hm = new HashMap<>();
			hm.put("filePath", filePath);
			String json = Utils.mapJSON(hm);
			response.getWriter().write(json);

		} else if (action.equals("checkChunk")) {
			String fileMd5 = request.getParameter("fileMd5");
			String chunk = request.getParameter("chunk");
			String chunkSize = request.getParameter("chunkSize");
			// 检查文件是否存在,且大小是否一致
			File chunkFile = new File(uploadPath + "/" + fileMd5 + "/" + chunk);
			if (chunkFile.exists() && chunkFile.length() == Integer.parseInt(chunkSize)) {
				response.getWriter().append("{\"ifExist\":1}");
			} else {
				response.getWriter().append("{\"ifExist\":0}");
			}
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
