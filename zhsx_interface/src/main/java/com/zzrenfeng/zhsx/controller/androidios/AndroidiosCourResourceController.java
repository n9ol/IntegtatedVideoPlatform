package com.zzrenfeng.zhsx.controller.androidios;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.AndroidiosModel;
import com.zzrenfeng.zhsx.model.CourResource;
import com.zzrenfeng.zhsx.service.CourResourceService;

/**
 * 课件资源控制器
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/androidiosCourResource")
public class AndroidiosCourResourceController extends BaseController {

	@Resource
	private CourResourceService courResourceService;

	/**
	 * 
	 * @param courId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findCourResourceByKey")
	public AndroidiosModel findCourResourceByKey(@RequestParam String courId) {
		AndroidiosModel androidiosModel = new AndroidiosModel();
		CourResource cour = courResourceService.findByKey(courId);
		androidiosModel.setData(cour);
		return androidiosModel;
	}

}
