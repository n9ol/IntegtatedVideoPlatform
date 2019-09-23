package com.zzrenfeng.officeConertPdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Office文档转换_pdf - 工具类
 * 
 * @author 田杰熠
 *
 */
public class OfficeConertPdfUtil {

	/**
	 * office转换PDF
	 * 
	 * @param fileName
	 *            需要转换的文件名
	 * @return
	 * @throws Exception
	 */
	public static void officeConversionPDF(final String filePath) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				// 获取文件后缀
				String suffix = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
				String filePaths = filePath.substring(0, filePath.lastIndexOf("."));

				// 获取需要转换文档的所在路径
				File officeFile = new File(filePath);
				File outputFile = new File(filePaths + ".pdf");// PDF保存文件路径

				try {
					if (!outputFile.exists()) {
						InputStream inputStream = new FileInputStream(officeFile);
						OutputStream outputStream = new FileOutputStream(outputFile);

						// 开始转换
						if (suffix.equalsIgnoreCase("docx") || suffix.equalsIgnoreCase("doc")) {
							wordConvert2Pdf(inputStream, outputStream);
						} else if (suffix.equalsIgnoreCase("pptx") || suffix.equalsIgnoreCase("ppt")) {
							pptConvert2Pdf(inputStream, outputStream);
						} else if (suffix.equalsIgnoreCase("xlsx") || suffix.equalsIgnoreCase("xls")) {
							excelConvert2Pdf(inputStream, outputStream);
						}
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				super.run();
			}
		};
		thread.start();
	}

	/**
	 * word 文档转换 _pdf
	 * 
	 * @param inputStream
	 * @param outputStream
	 */
	public static void wordConvert2Pdf(InputStream inputStream, OutputStream outputStream) {
		try {
			if (AsposeLicenseUtil.setWordsLicense()) {

				com.aspose.words.Document doc = new com.aspose.words.Document(inputStream);

				com.aspose.words.PdfSaveOptions pdfSaveOptions = new com.aspose.words.PdfSaveOptions();
				pdfSaveOptions.setSaveFormat(com.aspose.words.SaveFormat.PDF);
				pdfSaveOptions.getOutlineOptions().setHeadingsOutlineLevels(3); // 设置3级doc书签需要保存到pdf的heading中
				pdfSaveOptions.getOutlineOptions().setExpandedOutlineLevels(1); // 设置pdf中默认展开1级

				doc.save(outputStream, pdfSaveOptions);
				inputStream.close();
				outputStream.flush();
				outputStream.close();
			} else {

			}
		} catch (Exception e) {

		}
	}

	/**
	 * Excel 文档转换 _pdf
	 * 
	 * @param inputStream
	 * @param outputStream
	 */
	@SuppressWarnings("unused")
	public static void excelConvert2Pdf(InputStream inputStream, OutputStream outputStream) {
		try {
			if (AsposeLicenseUtil.setCellsLicense()) {
				long start = System.currentTimeMillis();

				com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook(inputStream);
				workbook.save(outputStream, com.aspose.cells.SaveFormat.PDF);
				inputStream.close();
				outputStream.flush();
				outputStream.close();
			} else {

			}
		} catch (Exception e) {

		}
	}

	/**
	 * PPT 文档转换 _pdf
	 * 
	 * @param inputStream
	 * @param outputStream
	 */
	public static void pptConvert2Pdf(InputStream inputStream, OutputStream outputStream) {
		try {
			if (AsposeLicenseUtil.setSlidesLicense()) {

				com.aspose.slides.Presentation presentation = new com.aspose.slides.Presentation(inputStream);
				presentation.save(outputStream, com.aspose.slides.SaveFormat.Pdf);

				inputStream.close();
				outputStream.flush();
				outputStream.close();
			} else {

			}
		} catch (Exception e) {

		}

	}

}
