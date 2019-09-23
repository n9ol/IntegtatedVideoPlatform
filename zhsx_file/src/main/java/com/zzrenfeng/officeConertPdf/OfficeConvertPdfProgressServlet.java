package com.zzrenfeng.officeConertPdf;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzrenfeng.util.Utils;

/**
 * 
 * @Description Office文档转换_pdf进度 (该方法其实获取的是, 是否转换成功 .因为Office转换_pdf时
 *              只有当转换成功是才去生成文件)
 * @author 田杰熠
 * @copyright {@link zzrenfeng.com}
 * @version 2018年9月6日 上午10:54:34
 * @see com.zzrenfeng.officeConertPdf.OfficeConvertPdfProgressServlet
 *
 */
public class OfficeConvertPdfProgressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OfficeConvertPdfProgressServlet() {
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
		String fileSuffix = fileAllPath.substring(fileAllPath.lastIndexOf(".") + 1); // 源文件后缀
		String filePdfAllPath = fileAllPath.substring(0, fileAllPath.lastIndexOf(".")) + ".pdf"; // _pdf文件格式全路径

		double progress = 0;
		double sourceFilePages = 0;
		if ("ppt".equals(fileSuffix)) {
			sourceFilePages = Utils.pptPages(fileAllPath);
		}
		if ("pptx".equals(fileSuffix)) {
			sourceFilePages = Utils.pptxPages(fileAllPath);
		}
		if ("pdf".equals(fileSuffix)) {
			sourceFilePages = Utils.pdfPages(fileAllPath);
		}
		boolean b = "doc".equals(fileSuffix) || "docx".equals(fileSuffix) || "xls".equals(fileSuffix)
				|| "xlsx".equals(fileSuffix);

		double pdfPages = Utils.pdfPages(filePdfAllPath);
		System.out.println("pdfPages=" + pdfPages);
		if (sourceFilePages != 0) {
			progress = (pdfPages / sourceFilePages) * 100;
		}
		if (b && pdfPages > 0) {
			progress = 100;
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
