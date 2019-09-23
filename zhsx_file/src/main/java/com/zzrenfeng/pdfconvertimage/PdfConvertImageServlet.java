package com.zzrenfeng.pdfconvertimage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.zzrenfeng.util.Utils;

/**
 * 
 * @Description _Pdf 转换为图片 Servlet
 * @author 田杰熠
 * @copyright {@link zzrenfeng.com}
 * @version 2018年7月30日 下午6:05:40
 * @see com.zzrenfeng.pdfconvertimage.PdfConvertImageServlet
 *
 */
public class PdfConvertImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PdfConvertImageServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		int imageNum = 0;
		String path = request.getParameter("path");
		String pdfFilePath = request.getParameter("pdfFilePath");
		String dpi = request.getParameter("dpi");
		if (dpi == null || "".equals(dpi)) {
			dpi = "300";
		}

		// 上传到服务器的路径 根路径
		String uploadPath = request.getSession().getServletContext().getRealPath(path);

		// _pdf文件路径
		File pdfFileCompletePath = new File(uploadPath, pdfFilePath);

		// 图片保存路径
		String randomDirectory = pdfFilePath.substring(0, pdfFilePath.lastIndexOf("."));
		File imageFilePath = new File(uploadPath, randomDirectory);
		if (!imageFilePath.exists()) {
			imageFilePath.mkdirs();
		}

		// 获取图片总共多少张
		PDDocument doc = PDDocument.load(pdfFileCompletePath);
		PDFRenderer renderer = new PDFRenderer(doc);
		imageNum = doc.getNumberOfPages(); // 总个数

		// 返回数据
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("imagePath", randomDirectory);
		hashMap.put("imageNum", imageNum);
		String mapJSON = Utils.mapJSON(hashMap);
		response.getWriter().write(mapJSON);

		// _pdf转图片
		PdfConvertImage.pdf2Image(renderer, imageFilePath.toString(), imageNum, Integer.valueOf(dpi));
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
