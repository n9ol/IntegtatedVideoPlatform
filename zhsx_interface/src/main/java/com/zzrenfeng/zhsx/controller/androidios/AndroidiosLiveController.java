package com.zzrenfeng.zhsx.controller.androidios;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.constant.Constant;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.AndroidiosModel;
import com.zzrenfeng.zhsx.model.LoFschedule;
import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseSchedule;
import com.zzrenfeng.zhsx.service.LoFscheduleService;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleBigTimeService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleService;
import com.zzrenfeng.zhsx.util.BaseHttpService;
import com.zzrenfeng.zhsx.util.DateUtil;
import com.zzrenfeng.zhsx.util.Utils;

/**
 * 移动端接口 - 直播
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/androidiosLive")
public class AndroidiosLiveController extends BaseController {

	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private SysClassroomService sysClassroomService;
	@Resource
	private LoFscheduleService loFscheduleService;
	@Resource
	private CourseScheduleService courseScheduleService;
	@Resource
	private CourseScheduleBigTimeService courseScheduleBigTimeService;

	private String HttpUrl = "";

	/**
	 * 获得直播课程信息
	 * 
	 * @param request
	 * @param response
	 * @param p
	 * @param loSchedule
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/loSchedule")
	public AndroidiosModel LoSchedule(HttpServletRequest request, HttpServletResponse response, Integer p,
			CourseSchedule courseSchedule, String gradeId, String subjectId) throws ParseException {
		if (p == null) {
			p = 1;
		}
		String timeSorting = courseSchedule.getTimeSorting();
		if (timeSorting == null || "".equals(timeSorting)) {
			timeSorting = Constant.COURSE_SCHEDULE_TIMESORTING_Q;
		}
		courseSchedule.setTimeSorting(timeSorting);
		courseSchedule.setDayOfWeek(DateUtil.getIntWeekDay());
		courseSchedule.setSpecialtyName(gradeId);
		courseSchedule.setSubjectName(subjectId);
		Page<CourseSchedule> pageInfo = courseScheduleService.getPageInfo(courseSchedule, p, 6);
		List<CourseSchedule> listCourseSchedule = pageInfo.getResult();
		List<LoSchedule> lists = loScheduleService.listLoschedule(request, listCourseSchedule);
		int pages = pageInfo.getPages(); // 总页数
		long total = pageInfo.getTotal();

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setData(lists);
		androidiosModel.setCurrPage(p);
		androidiosModel.setTotalPage(pages);
		androidiosModel.setTotalNum(total);
		return androidiosModel;
	}

	/**
	 * 获得视频播放路径 - 质量评估
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getURLPg")
	public AndroidiosModel getURLA(@RequestParam String id, HttpServletRequest request) {
		Map<String, Object> hm = new HashMap<>();
		int flag = 2;
		// LoSchedule schedule = loScheduleService.findByKey(id);
		CourseSchedule courseSchedule = courseScheduleService.getCourseSchedule(id);
		SysClassroom classroom = sysClassroomService.findByKey(courseSchedule.getClassroomId());
		if (classroom != null) {

			// String ipPort = classroom.getServiceIp();
			String ipPort = Utils.getAccessPathUrlOrIP(request, classroom.getServiceIp());

			String classcode = classroom.getClassCode().trim();
			String rtmpUrl = "rtmp://" + ipPort + "/live/lubo" + classcode + "-";

			List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();

			Map<String, String> mapURL1 = new HashMap<String, String>();
			mapURL1.put("title", "教师");
			mapURL1.put("url", rtmpUrl + "1");
			Map<String, String> mapURL2 = new HashMap<String, String>();
			mapURL2.put("title", "学生");
			mapURL2.put("url", rtmpUrl + "2");
			Map<String, String> mapURL3 = new HashMap<String, String>();
			mapURL3.put("title", "白板");
			mapURL3.put("url", rtmpUrl + "3");

			listMap.add(mapURL1);
			listMap.add(mapURL2);
			listMap.add(mapURL3);
			hm.put("videoURL", listMap);

			hm.put("soundURL", rtmpUrl + "a");
			flag = 1;// 成功
		}

		hm.put("state", flag);// 2不成功

		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setIsNeedLogin(1);
		androidiosModel.setData(hm);
		return androidiosModel;
	}

	/**
	 * 获得视频播放路径 - 专题课堂
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getURLInteractive")
	public AndroidiosModel getURLInteractive(@RequestParam String id, HttpServletRequest request) {
		AndroidiosModel androidiosModel = new AndroidiosModel();
		androidiosModel.setIsNeedLogin(1);

		if (HttpUrl.isEmpty()) {
			Properties props = new Properties();
			InputStream in;
			in = getClass().getResourceAsStream("/commonConfig.properties");
			try {
				props.load(in);
			} catch (Exception e1) {
				HttpUrl = "http://192.168.1.201:8081/meetingserv/useridinfo";
			}
			if (props.isEmpty()) {
				HttpUrl = "http://192.168.1.201:8081/meetingserv/useridinfo";
			} else {
				HttpUrl = props.get("Http.url").toString();
			}
		}

		Map<String, Object> hm = new HashMap<>();
		int flag = 0;
		LoSchedule schedule = loScheduleService.findByKey(id);
		if (schedule == null) {
			hm.put("state", flag);
			androidiosModel.setData(hm);
			return androidiosModel;
		}
		SysClassroom classroom = sysClassroomService.findByKey(schedule.getClassId());
		if (classroom == null) {
			hm.put("state", flag);
			androidiosModel.setData(hm);
			return androidiosModel;
		}

		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		// 获得基本路径
		// String ipPort = classroom.getServiceIp();
		String ipPort = Utils.getAccessPathUrlOrIP(request, classroom.getServiceIp());
		String rid = classroom.getRoomId().trim();
		String rtmpVideoUrl = "rtmp://" + ipPort + "/meetingserv/" + rid + "/vd";
		String rtmpSoundUrl = "rtmp://" + ipPort + "/meetingserv/" + rid + "/mic_";

		// 设置请求动态路径基本参数
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roomId", rid);

		// 获得主教室动态路径标识
		String uid = classroom.getUid();
		paramMap.put("userId", uid);
		Map<String, Object> resMap0 = BaseHttpService.getResponseResultXml(paramMap, HttpUrl);
		if (resMap0 == null || resMap0.size() == 0) {
			hm.put("state", flag);
			androidiosModel.setData(hm);
			return androidiosModel;
		}
		int exists0 = Integer.valueOf((String) resMap0.get("exists"));
		Map<String, Object> mapURL0 = new HashMap<String, Object>();
		mapURL0.put("title", "主教室");
		mapURL0.put("exists", exists0);
		mapURL0.put("videoURL", rtmpVideoUrl + resMap0.get("rid"));
		mapURL0.put("soundUrl", rtmpSoundUrl + resMap0.get("rid"));
		listMap.add(mapURL0);

		// 获取辅教室信息
		LoFschedule fschedule = new LoFschedule();
		fschedule.setZId(id);
		List<LoFschedule> fschedules = loFscheduleService.findSelective(fschedule);
		if (fschedules != null && fschedules.size() > 0) {
			classroom = sysClassroomService.findByKey(fschedules.get(0).getClassId());

			// 获取辅教室1的动态路径
			String uid1 = classroom.getUid();
			paramMap.put("userId", uid1);
			Map<String, Object> resMap1 = BaseHttpService.getResponseResultXml(paramMap, HttpUrl);
			int exists1 = 0;
			if (resMap1 != null && resMap1.size() > 0) {
				exists1 = Integer.valueOf((String) resMap1.get("exists"));
			}
			Map<String, Object> mapURL1 = new HashMap<String, Object>();
			mapURL1.put("title", "辅教室1");
			mapURL1.put("exists", exists1);
			mapURL1.put("videoURL", rtmpVideoUrl + resMap1.get("rid"));
			mapURL1.put("soundUrl", rtmpSoundUrl + resMap1.get("rid"));
			listMap.add(mapURL1);

			// 获取辅教室2的动态路径
			if (fschedules.size() >= 2) {
				classroom = sysClassroomService.findByKey(fschedules.get(1).getClassId());
				String uid2 = classroom.getUid();
				paramMap.put("userId", uid2);
				Map<String, Object> resMap2 = BaseHttpService.getResponseResultXml(paramMap, HttpUrl);
				int exists2 = 0;
				if (resMap2 != null && resMap2.size() > 0) {
					exists2 = Integer.valueOf((String) resMap2.get("exists"));
				}
				Map<String, Object> mapURL2 = new HashMap<String, Object>();
				mapURL2.put("title", "辅教室2");
				mapURL2.put("exists", exists2);
				mapURL2.put("videoURL", rtmpVideoUrl + resMap2.get("rid"));
				mapURL2.put("soundUrl", rtmpSoundUrl + resMap2.get("rid"));
				listMap.add(mapURL2);
			}

			// 获取辅教室3的动态路径
			if (fschedules.size() >= 3) {
				classroom = sysClassroomService.findByKey(fschedules.get(2).getClassId());
				String uid3 = classroom.getUid();
				paramMap.put("userId", uid3);
				Map<String, Object> resMap3 = BaseHttpService.getResponseResultXml(paramMap, HttpUrl);
				int exists3 = 0;
				if (resMap3 != null && resMap3.size() > 0) {
					exists3 = Integer.valueOf((String) resMap3.get("exists"));
				}
				Map<String, Object> mapURL3 = new HashMap<String, Object>();
				mapURL3.put("title", "辅教室3");
				mapURL3.put("exists", exists3);
				mapURL3.put("videoURL", rtmpVideoUrl + resMap3.get("rid"));
				mapURL3.put("soundUrl", rtmpSoundUrl + resMap3.get("rid"));
				listMap.add(mapURL3);
			}
		}
		flag = 1;
		hm.put("urls", listMap);
		hm.put("state", flag);
		androidiosModel.setData(hm);
		return androidiosModel;
	}

}
