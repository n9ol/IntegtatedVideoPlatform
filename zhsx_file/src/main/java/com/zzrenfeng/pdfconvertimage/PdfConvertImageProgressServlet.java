package com.zzrenfeng.pdfconvertimage;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzrenfeng.util.Utils;

/**
 * 
 * @Description _pdf 转换 image 进度
 * @author 田杰熠
 * @copyright {@link zzrenfeng.com}
 * @version 2018年9月6日 下午4:43:15
 * @see com.zzrenfeng.pdfconvertimage.PdfConvertImageProgressServlet
 *
 */
public class PdfConvertImageProgressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PdfConvertImageProgressServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		String filePath = request.getParameter("filePath"); // 源文件路径
		String fileAllPath = request.getSession().getServletContext().getRealPath(filePath); // 源文件全路径
		String filePdfAllPath = fileAllPath.substring(0, fileAllPath.lastIndexOf(".")) + ".pdf"; // _pdf文件格式全路径
		String imagePath = fileAllPath.substring(0, fileAllPath.lastIndexOf(".")); // image存储目录

		double progress = 0;
		double fileCount = Utils.fileCount(imagePath);
		double pdfPages = Utils.pdfPages(filePdfAllPath);
		if (pdfPages != 0) {
			progress = (fileCount / pdfPages) * 100;
		}

		DecimalFormat df = new DecimalFormat("#.00");
		response.getWriter().write(df.format(progress));
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
