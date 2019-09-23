package com.zzrenfeng.zhsx.controller.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.WebNews;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.OffLineVideoResourcesService;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.WebNewsService;

/**
 * 获得视频播放路径
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/webVideoResourcesPath")
public class WebVideoResourcesPathController {

	@Resource
	private OffLineVideoResourcesService videoResourcesService;
	@Resource
	private WebNewsService webNewsService;
	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private SysClassroomService sysClassroomService;

	private String videoUrl = "";

	/**
	 * 获得视频播放路径-点播
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/url")
	public void url(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("videoId");
		// 获取视频发布路径
		if (videoUrl.isEmpty()) {
			Properties props = new Properties();
			InputStream in;
			in = getClass().getResourceAsStream("/commonConfig.properties");
			try {
				props.load(in);
			} catch (Exception e) {
				videoUrl = "rtmp://127.0.0.1/vod/";
			}
			if (props.isEmpty()) {
				videoUrl = "rtmp://127.0.0.1/vod/";
			} else {
				videoUrl = props.get("web.videourl").toString();
			}
		}

		String videoPath = videoResourcesService.findVideoPathById(id);
		response.reset();
		response.encodeURL("utf-8");
		String url = videoUrl + videoPath;
		URLEncoder.encode(url, "utf-8");
		response.getOutputStream().write(url.getBytes("utf-8"));
		response.getOutputStream().flush();
	}

	/**
	 * 获得视频播放路径新闻
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/urlnew")
	public void urlnew(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("videoId");
		// 获取视频发布路径
		if (videoUrl.isEmpty()) {
			Properties props = new Properties();
			InputStream in;
			in = getClass().getResourceAsStream("/commonConfig.properties");
			try {
				props.load(in);
			} catch (Exception e) {
				videoUrl = "rtmp://127.0.0.1/vod/";
			}
			if (props.isEmpty()) {
				videoUrl = "rtmp://127.0.0.1/vod/";
			} else {
				videoUrl = props.get("web.videourl").toString();
			}
		}

		WebNews videoResources = webNewsService.findByKey(id);
		response.reset();
		response.encodeURL("utf-8");
		String url = videoUrl + videoResources.getBak2();
		URLEncoder.encode(url, "utf-8");
		response.getOutputStream().write(url.getBytes("utf-8"));
		response.getOutputStream().flush();
	}

	/**
	 * 获得视频播放路径 - 直播
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/urlLive")
	public void urlLive(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("videoId");
		LoSchedule schedule = loScheduleService.findByKey(id);
		SysClassroom classroom = sysClassroomService.findByKey(schedule.getClassId());
		if (classroom != null) {
			String classCode = classroom.getClassCode();
			String url = "rtmp://" + classroom.getServiceIp() + "/live/lubo" + classCode;

			response.reset();
			response.encodeURL("utf-8");
			URLEncoder.encode(url, "utf-8");
			response.getOutputStream().write(url.getBytes("utf-8"));
			response.getOutputStream().flush();
		}
	}

}
