package com.zzrenfeng.pdfconvertimage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.rendering.PDFRenderer;

/**
 * 
 * @Description _Pdf 转换为图片 工具类
 * @author 田杰熠
 * @copyright {@link zzrenfeng.com}
 * @version 2018年7月30日 上午11:09:42
 * @see com.zzrenfeng.pdftoimage.Pdf2Image
 *
 */
public class PdfConvertImage {

	/**
	 * 
	 * @param renderer
	 *            PDFRenderer
	 * @param imageFilePath
	 *            图片保存路径
	 * @param imageNum
	 *            图片共多少张
	 * @param dpi
	 *            越大转换后越清晰，相对转换速度越慢(单位像素)
	 */
	public static void pdf2Image(final PDFRenderer renderer, final String imageFilePath, final int imageNum,
			final int dpi) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < imageNum; i++) {
						// 方式1,第二个参数是设置缩放比(即像素)
						BufferedImage image = renderer.renderImageWithDPI(i, dpi);
						ImageIO.write(image, "PNG", new File(imageFilePath + "/" + (i + 1) + ".png"));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				super.run();
			}
		};
		thread.start();
	}
}