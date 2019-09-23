package com.zzrenfeng.zhsx.controller.androidios;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzrenfeng.zhsx.util.DownloadUtils;

@Controller
public class AndroidiosDownloadApk {

	@Resource
	private String fileWebPath;

	/**
	 * 下载批量添加模板
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/DownloadApk")
	public void downloadSchoolTem(HttpServletRequest request, HttpServletResponse response, String filename)
			throws UnsupportedEncodingException {
		String filepath = fileWebPath + "/apk/" + filename;
		DownloadUtils.downloadInternet(response, request, filepath, filename);
	}

}
