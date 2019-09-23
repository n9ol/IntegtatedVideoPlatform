package com.zzrenfeng.zhsx.controller.androidios;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.AndroidiosModel;
import com.zzrenfeng.zhsx.model.OffLineVideoResources;
import com.zzrenfeng.zhsx.service.OffLineVideoResourcesService;

/**
 * 
 * 移动端接口 - 点播
 * 
 * @author 田杰熠
 */
@Controller
@RequestMapping("/androidiosVideo")
public class AndroidiosVideoController extends BaseController {

	@Resource
	private OffLineVideoResourcesService offLineVideoResourcesService;

	private String videoUrl = "";

	/**
	 * 获得点播视频
	 * 
	 * @param videoResources
	 * @param p
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/videoResources")
	public AndroidiosModel videoResources(OffLineVideoResources videoResources, Integer p) {
		if (p == null)
			p = 1;

		videoResources.setReleaseState("Y");
		videoResources.setTranscodingState("O");
		videoResources.setIsShow("Y");

		Page<OffLineVideoResources> pageInfo = offLineVideoResourcesService.findPageSelective(videoResources, p, 10);
		List<OffLineVideoResources> lists = pageInfo.getResult();
		int totalPage = pageInfo.getPages(); // 总页数

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(lists);
		androidiosModel.setCurrPage(p);
		androidiosModel.setTotalPage(totalPage);
		return androidiosModel;
	}

	/**
	 * 获得点播视频播放路径
	 * 
	 * @param videoId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getVideoPlayUrl")
	public AndroidiosModel getVideoPlayUrl(@RequestParam String id) {

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

		String videoPath = offLineVideoResourcesService.findVideoPathById(id);

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(videoUrl + videoPath);
		return androidiosModel;
	}

}
