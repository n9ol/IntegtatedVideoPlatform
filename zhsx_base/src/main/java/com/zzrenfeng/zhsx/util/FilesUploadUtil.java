package com.zzrenfeng.zhsx.util;

import java.io.File;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 * 
 * @author 田杰熠
 * @version 1.0
 */
public class FilesUploadUtil {

	/**
	 * 文件上传
	 * 
	 * @param uploadFile
	 *            上传文件
	 * @param uploadPath
	 *            保存上传文件的根路径
	 * @return 返回文件路径
	 * @throws Exception
	 */
	public static String fileUpload(MultipartFile uploadFile, String uploadPath) throws Exception {

		if (uploadFile != null && uploadFile.getSize() != 0) {
			/*
			 * 获得文件名
			 */
			String originaFilename = uploadFile.getOriginalFilename();
			String fileType = originaFilename.substring(originaFilename.lastIndexOf("."));
			String newFilename = new Date().getTime() + String.valueOf(originaFilename.hashCode()) + fileType;
			newFilename = newFilename.replaceAll("-", "").trim();

			/*
			 * 目录分级处理
			 */
			int rank = 2;
			int hashcode = originaFilename.hashCode();
			int a = hashcode & 0xf;
			int b = (a >>> 4) & 0xf;
			int c = (b >>> 4) & 0xf;
			int d = (c >>> 4) & 0xf;
			String randomDirectory = "";
			switch (rank) {
			case 1:
				randomDirectory = "/" + a;
				break;
			case 2:
				randomDirectory = "/" + a + "/" + b;
				break;
			case 3:
				randomDirectory = "/" + a + "/" + b + "/" + c;
				break;
			case 4:
				randomDirectory = "/" + a + "/" + b + "/" + c + "/" + d;
				break;
			default:
				randomDirectory = "";
				break;
			}

			/*
			 * 获得文件路径 如果路径不存在进行创建
			 */
			File filepath = new File(uploadPath, randomDirectory);
			if (!filepath.exists()) {
				filepath.mkdirs();
			}

			/*
			 * 上传文件 将文件从内存中写入到文件路径
			 */

			uploadFile.transferTo(new File(filepath, newFilename));

			return randomDirectory + "/" + newFilename;
		}
		return null;
	}

}
