package com.zzrenfeng.zhsx.controller.androidios;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.AndroidiosModel;
import com.zzrenfeng.zhsx.model.LoClassTime;
import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.OffLineVideoResources;
import com.zzrenfeng.zhsx.model.ShiroUser;
import com.zzrenfeng.zhsx.service.LoClassTimeService;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.LoTermTimeService;
import com.zzrenfeng.zhsx.service.OffLineVideoResourcesService;
import com.zzrenfeng.zhsx.service.WebPjService;
import com.zzrenfeng.zhsx.util.DateUtil;

/**
 * 移动端接口 - 个人中心
 * 
 * @author 田杰熠
 */
@Controller
@RequestMapping("/androidiosPersonalCenter")
public class AndroidiosPersonalCenterController extends BaseController {

	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private WebPjService webPjService;
	@Resource
	private LoTermTimeService loTermTimeService;
	@Resource
	private LoClassTimeService loClassTimeService;
	@Resource
	private OffLineVideoResourcesService offLineVideoResourcesService;

	@ResponseBody
	@RequestMapping("/principal")
	public AndroidiosModel principal() {
		if (!isLogined())
			return null;

		ShiroUser shiroUser = getShiroUser();
		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(shiroUser);
		return androidiosModel;
	}

	/**
	 * 获得我的直播课程
	 * 
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/myLiveCourse")
	public AndroidiosModel myLiveCourse() throws ParseException {
		Map<String, Object> map = new HashMap<>();

		Map<String, Object> s = loTermTimeService.getCurrTermTimeWeeks(getUserSchoolId());
		if (s != null && s.size() > 0) {
			// Integer weeks = (Integer) s.get("weeks");
			String termTimeId = (String) s.get("termTimeId");

			Map<String, Object> hm = DateUtil.getOneWeekDate(new Date(), "yyyy-MM-dd");

			LoSchedule loSchedule = new LoSchedule();
			// loSchedule.setWeeks(weeks);
			loSchedule.setStartDate((String) hm.get("SundayDate"));
			loSchedule.setEndDate((String) hm.get("SaturdayDate"));
			loSchedule.setUserId(getUserId());
			List<LoSchedule> loSchedules = loScheduleService.findSelective(loSchedule);

			map.put("loSchedules", loSchedules);
			map.put("totalWeeks", s.get("totalWeeks"));

			if (termTimeId != null) {
				LoClassTime classTime = new LoClassTime();
				classTime.setTermTimeId(termTimeId);
				List<LoClassTime> classTimes = loClassTimeService.findSelective(classTime);
				map.put("classTimes", classTimes);
			}
		}

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(map);
		return androidiosModel;
	}

	/**
	 * 获得我的点播课程
	 * 
	 * @param videoResources
	 * @param p
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/myVideoResources")
	public AndroidiosModel myVideoResources(OffLineVideoResources videoResources, Integer p) {
		if (p == null)
			p = 1;

		// hm.put("currPageNum", p);
		// hm.put("search", videoResources.getSearch());
		// hm.put("sortord", videoResources.getSortord());

		videoResources.setUserId(getUserId());
		Page<OffLineVideoResources> pageInfo = offLineVideoResourcesService.findPageSelective(videoResources, p, 10);
		int totalPage = pageInfo.getPages(); // 总页数
		List<OffLineVideoResources> lists = pageInfo.getResult();

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(lists);
		androidiosModel.setCurrPage(p);
		androidiosModel.setTotalPage(totalPage);
		return androidiosModel;
	}

	/**
	 * 获得的我的评估课程 列表
	 */
	@ResponseBody
	@RequestMapping("/myPgCourseList")
	public AndroidiosModel myPgCourseList(Integer p) {

		if (p == null)
			p = 1;

		String userId = getShiroUser().getId();
		Page<Map<String, String>> pageInfo = webPjService.findPjRecord(userId, p, 10);
		int totalPage = pageInfo.getPages(); // 总页数
		List<Map<String, String>> lists = pageInfo.getResult();

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(lists);
		androidiosModel.setCurrPage(p);
		androidiosModel.setTotalPage(totalPage);
		return androidiosModel;
	}

}
