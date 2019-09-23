package com.zzrenfeng.zhsx.controller.androidios;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.AndroidiosModel;
import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.OffLineVideoResources;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.OffLineVideoResourcesService;

/**
 * 移动端接口 - 首页
 * 
 * @author 田杰熠
 */
@Controller
@RequestMapping("/androidiosIndex")
public class AndroidiosIndexController extends BaseController {

	@Resource
	private OffLineVideoResourcesService offLineVideoResourcesService;
	@Resource
	private LoScheduleService loScheduleService;

	/**
	 * 精品课程
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/videoResources")
	public AndroidiosModel videoResources() {

		OffLineVideoResources videoResources = new OffLineVideoResources();
		videoResources.setReleaseState("Y");
		videoResources.setTranscodingState("O");
		videoResources.setIsShow("Y");
		videoResources.setSortord("view");

		Page<OffLineVideoResources> pageInfo = offLineVideoResourcesService.findPageSelective(videoResources, 1, 10);
		List<OffLineVideoResources> lists = pageInfo.getResult();

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(lists);
		return androidiosModel;
	}

	/**
	 * 全局搜索
	 * 
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/globalSearch")
	public AndroidiosModel globalSearch(HttpServletRequest request, @RequestParam String search, Integer videoP,
			Integer liveP) {

		// 获得点播视频
		if (videoP == null)
			videoP = 1;
		OffLineVideoResources videoResources = new OffLineVideoResources();
		videoResources.setReleaseState("Y");
		videoResources.setTranscodingState("O");
		videoResources.setIsShow("Y");
		videoResources.setSearch(search);
		Page<OffLineVideoResources> videoPageInfo = offLineVideoResourcesService.findPageSelective(videoResources,
				videoP, 10);
		List<OffLineVideoResources> videolists = videoPageInfo.getResult();
		int videoTotalPage = videoPageInfo.getPages(); // 总页数
		Map<String, Object> videos = new HashMap<String, Object>();
		videos.put("data", videolists);
		videos.put("totalPage", videoTotalPage);
		videos.put("currPage", videoP);

		// 获得直播视频信息
		String path = "http://" + request.getServerName() + ":" + request.getServerPort() + "/"
				+ request.getRequestURI().split("/")[1];

		if (liveP == null)
			liveP = 1;
		LoSchedule loSchedule = new LoSchedule();
		loSchedule.setSearch(search);
		Page<LoSchedule> livePageInfo = loScheduleService.findPage(loSchedule, liveP, 10);
		List<LoSchedule> livelists = livePageInfo.getResult();
		for (LoSchedule loSchedule2 : livelists) {
			if (loSchedule2.getType().equals("G")) {
				loSchedule2.setCoverpath(path + "/images/zhuandi267.png");
			} else {
				loSchedule2.setCoverpath(path + "/images/pinggu267.png");
			}
		}

		int liveTotalPage = livePageInfo.getPages(); // 总页数
		Map<String, Object> lives = new HashMap<String, Object>();
		lives.put("data", livelists);
		lives.put("totalPage", liveTotalPage);
		lives.put("currPage", liveP);

		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("videos", videos);
		hm.put("lives", lives);
		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(hm);
		return androidiosModel;
	}

	/**
	 * 轮播图路径
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/carouselPath")
	public AndroidiosModel carouselPath(HttpServletRequest request) {
		String path = "http://" + request.getServerName() + ":" + request.getServerPort() + "/"
				+ request.getRequestURI().split("/")[1];
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("path1", path + "/img/banner01.png");
		hm.put("path2", path + "/img/banner02.png");
		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(hm);
		return androidiosModel;
	}

}
