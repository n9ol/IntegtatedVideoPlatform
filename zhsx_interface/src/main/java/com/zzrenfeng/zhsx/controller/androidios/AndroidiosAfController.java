package com.zzrenfeng.zhsx.controller.androidios;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.AfManager;
import com.zzrenfeng.zhsx.model.AndroidiosModel;
import com.zzrenfeng.zhsx.service.AfDhService;
import com.zzrenfeng.zhsx.service.AfManagerService;
import com.zzrenfeng.zhsx.service.SysSchoolService;

/**
 * 移动端接口 - 安防
 * 
 * @author 田杰熠
 */
@Controller
@RequestMapping("/androidiosAf")
public class AndroidiosAfController extends BaseController {

	@Resource
	private AfManagerService afManagerService;
	@Resource
	private AfDhService afDhService;
	@Resource
	private SysSchoolService sysSchoolService;

	/**
	 * 获得开通安防学校 - 区域
	 * 
	 * @param af
	 *            (需要参数 schoolid)
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findDhBySchool")
	public AndroidiosModel findDhBySchool(AfManager af, Model model) {

		af.setStatus("Y");
		List<AfManager> lists = afManagerService.findAfSchoolArea(af);

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setIsNeedLogin(1);
		androidiosModel.setData(lists);
		return androidiosModel;
	}

	/**
	 * 获取安防数据
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/af")
	public AndroidiosModel af(String schoolId, String schoolarea, Integer p) {
		if (p == null) {
			p = 1;
		}
		AfManager af = new AfManager();
		af.setSchoolid(schoolId);
		af.setSchoolarea(schoolarea);
		Page<AfManager> pageInfo = afManagerService.findPageSelective(af, p, 10);
		int totalPage = pageInfo.getPages();
		List<AfManager> lists = pageInfo.getResult();

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setIsNeedLogin(1);
		androidiosModel.setData(lists);
		androidiosModel.setCurrPage(p);
		androidiosModel.setTotalPage(totalPage);
		return androidiosModel;
	}

}
