package com.zzrenfeng.officeConertPdf;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * _Aspose 监听
 * 
 * @author 田杰熠
 *
 */
public class AsposeLicenseUtil {

	private static InputStream inputStream = null;

	/**
	 * 获取License的输入流
	 *
	 * @return
	 */
	private static InputStream getLicenseInput() {
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		try {
			String path = contextClassLoader.getResource("license.xml").toURI().getPath();
			inputStream = new FileInputStream(path);
		} catch (Exception e) {

		}
		return inputStream;
	}

	/**
	 * 设置License
	 * 
	 * @return true表示已成功设置License, false表示失败
	 */
	public static boolean setWordsLicense() {
		InputStream licenseInput = getLicenseInput();
		if (licenseInput != null) {
			try {
				com.aspose.words.License aposeLic = new com.aspose.words.License();
				aposeLic.setLicense(licenseInput);
				return aposeLic.getIsLicensed();
			} catch (Exception e) {

			}
		}
		return false;
	}

	/**
	 * 设置License
	 *
	 * @return true表示已成功设置License, false表示失败
	 */
	public static boolean setCellsLicense() {
		InputStream licenseInput = getLicenseInput();
		if (licenseInput != null) {
			try {
				com.aspose.cells.License aposeLic = new com.aspose.cells.License();
				aposeLic.setLicense(licenseInput);
				return true;
			} catch (Exception e) {
			}
		}
		return false;
	}

	/**
	 * 设置License
	 *
	 * @return true表示已成功设置License, false表示失败
	 */
	public static boolean setSlidesLicense() {
		InputStream licenseInput = getLicenseInput();
		if (licenseInput != null) {
			try {
				com.aspose.slides.License aposeLic = new com.aspose.slides.License();
				aposeLic.setLicense(licenseInput);
				return aposeLic.isLicensed();
			} catch (Exception e) {

			}
		}
		return false;
	}

	/**
	 * 设置Aspose PDF的license
	 * 
	 * @return true表示设置成功，false表示设置失败
	 */
	public static boolean setPdfLicense() {
		InputStream licenseInput = getLicenseInput();
		if (licenseInput != null) {
			try {
				com.aspose.pdf.License aposeLic = new com.aspose.pdf.License();
				aposeLic.setLicense(licenseInput);
				return true;
			} catch (Exception e) {

			}
		}
		return false;
	}

}
