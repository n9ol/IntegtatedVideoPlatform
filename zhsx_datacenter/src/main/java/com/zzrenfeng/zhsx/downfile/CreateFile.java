package com.zzrenfeng.zhsx.downfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class CreateFile {

	private Configuration configure = null;

	public String TEMPPATH_FTL = "/ftl";// "template";

	public CreateFile() {
		configure = new Configuration();
		configure.setDefaultEncoding("utf-8");
	}

	/**
	 * 
	 * @param param
	 * @param tempName(XXX.ftl)
	 * @param savePath
	 *            临时文件保存位置
	 * @throws Exception
	 */
	public String createDocFile(Map<String, Object> param, String tempName, String savePath, ServletContext webcontext)
			throws Exception {

		String filePath = null;
		Template template = null;
		Writer out = null;

		try {

			// String savePath =
			// "E:\\aa\\";//BpmConfig.getInstance().getValue("EndWordFile.path");//文件的保存位置
			String fileUrl = savePath + "/" + UUID.randomUUID().toString() + ".doc";
			File filepath = new File(savePath);
			filepath.setWritable(true, false);
			if (!filepath.exists()) {
				filepath.mkdirs();
			}
			// configure.setClassForTemplateLoading(this.getClass(), tempPath);
			configure.setServletContextForTemplateLoading(webcontext, TEMPPATH_FTL);
			configure.setObjectWrapper(new DefaultObjectWrapper());
			configure.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
			template = configure.getTemplate(tempName, "utf-8");
			File outFile = new File(fileUrl);// "E:\\关于通知\\aa\\ss.doc");
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
			template.process(param, out);
			// outFile.delete();
			filePath = fileUrl;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("文件【" + tempName + "】生成失败！" + e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return filePath;

	}

}
