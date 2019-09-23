package com.zzrenfeng.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.text.DecimalFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xslf.XSLFSlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.xmlbeans.XmlException;
import org.codehaus.jackson.map.ObjectMapper;

import com.itextpdf.text.pdf.PdfReader;

/**
 * 工具类
 * 
 * @author 田杰熠
 *
 */
public class Utils {

	/**
	 * 将MAP转换成JSON字符串
	 * 
	 * @param map
	 * @return
	 */
	public static String mapJSON(Map<String, Object> map) {
		String json = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			json = objectMapper.writeValueAsString(map);
			System.gc();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 转换视频大小格式
	 * 
	 * @param length
	 * @return
	 */
	public static String sizeToString(double length) {
		String size = null;
		DecimalFormat df = new DecimalFormat("#.##");
		if (length == 0) {
			size = "0M";
		} else if (length < 1024) {
			size = df.format(length) + "K";
		} else if (length < Math.pow(1024, 2)) {
			size = df.format(length / 1024) + "M";
		} else if (length < Math.pow(1024, 3)) {
			size = df.format(length / Math.pow(1024, 2)) + "G";
		}
		return size;
	}

	/**
	 * 远程更新数据库
	 * 
	 * @param hm
	 */
	public static void remoteUpdateDatabase(String _url) {
		try {
			java.net.URL url = new java.net.URL(_url);
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			if (conn.getResponseCode() == 200) {
				System.out.println("连接成功");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断是否是IE浏览器
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isMSBrowser(HttpServletRequest request) {
		String[] IEBrowserSignals = { "MSIE", "Trident", "Edge" };
		String userAgent = request.getHeader("User-Agent");
		for (String signal : IEBrowserSignals) {
			if (userAgent.contains(signal)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 读取指定_pdf文件页码数
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static int pdfPages(String filePath) {
		try {
			PdfReader reader = new PdfReader(filePath);
			return reader.getNumberOfPages();
		} catch (IOException e) {
			return 0;
		}
	}

	/**
	 * 获取指定_ppt文件页码数
	 * 
	 * @return
	 * @throws IOException
	 */
	public static int pptPages(String filePath) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			SlideShow pptfile = new SlideShow(new HSLFSlideShow(fis));
			return pptfile.getSlides().length;
		} catch (IOException e) {
			return 0;
		}
	}

	/**
	 * 获取指定_pptx文件页码数
	 * 
	 * @param filePath
	 * @return
	 * @throws OpenXML4JException
	 * @throws IOException
	 * @throws XmlException
	 */
	public static int pptxPages(String filePath) {
		try {
			XSLFSlideShow fis = new XSLFSlideShow(filePath);
			XMLSlideShow pptxfile = new XMLSlideShow(fis);
			return pptxfile.getSlides().length;
		} catch (OpenXML4JException | IOException | XmlException e) {
			return 0;
		}
	}

	/**
	 * 获取指定目录下的文件数量
	 * 
	 * @param filePath
	 * @return
	 */
	public static int fileCount(String filePath) {
		File f = new File(filePath);
		File[] fl = null;
		int iNum = 0;
		if (f.isDirectory()) { // 判断是不是目录
			fl = f.listFiles();
			for (int i = 0; i < fl.length; i++) {
				File f2 = fl[i];
				if (f2.isFile()) {
					iNum = iNum + 1;
				}
			}
			return iNum;
		} else {
			return 0;
		}
	}

}