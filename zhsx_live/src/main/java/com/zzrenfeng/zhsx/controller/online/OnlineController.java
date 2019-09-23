package com.zzrenfeng.zhsx.controller.online;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.constant.Constant;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.LoFschedule;
import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseSchedule;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseScheduleBigTime;
import com.zzrenfeng.zhsx.service.LoClassTimeService;
import com.zzrenfeng.zhsx.service.LoFscheduleService;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.LoTermTimeService;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleBigTimeService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleTimeService;
import com.zzrenfeng.zhsx.util.DateUtil;
import com.zzrenfeng.zhsx.util.UdpSend;
import com.zzrenfeng.zhsx.util.Utils;

/**
 * 直播模块
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/online")
public class OnlineController extends BaseController {

	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private SysClassroomService sysClassroomService;
	@Resource
	private LoFscheduleService loFscheduleService;
	@Resource
	private LoClassTimeService loClassTimeService;
	@Resource
	private LoTermTimeService loTermTimeService;
	@Resource
	private UserService userService;
	@Resource
	private SysSchoolService schoolService;
	@Resource
	private String platformLevel;
	@Resource
	private String platformLevelId;
	@Resource
	private CourseScheduleService courseScheduleService;
	@Resource
	private CourseScheduleTimeService courseScheduleTimeService;
	@Resource
	private Environment environment;
	@Resource
	private CourseScheduleBigTimeService courseScheduleBigTimeService;

	private final String DEFAULTVALUE = "3";

	/**
	 * 进入直播首页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/zb_online")
	public String live(HttpServletRequest request, HttpServletResponse response, Model model, String type) {
		// 获得专业
		List<SysDict> listSpecialty = sysDictService.listSpecialty();
		// 获得科目 去重复
		List<SysDict> listSubject = sysDictService.listSubject();
		// 获得热门推荐
		CourseSchedule courseSchedule = new CourseSchedule();
		courseSchedule.setType(type);
		List<CourseSchedule> listRecommendVideos = courseScheduleService.listRecommendVideos(courseSchedule, 4);

		model.addAttribute("type", type);
		model.addAttribute("grades", listSpecialty);
		model.addAttribute("subjects", listSubject);
		model.addAttribute("recommendVideos", listRecommendVideos);
		return "/web/online/zb_online";
	}

	/**
	 * 获得直播的信息列表
	 * 
	 * @param model
	 * @param p
	 * @param loSchedule
	 * @return
	 */
	@RequestMapping("/getZbMessage")
	public String getZbMessage(Model model, Integer p, CourseSchedule courseSchedule) {
		if (p == null) {
			p = 1;
		}
		String timeSorting = courseSchedule.getTimeSorting();
		if (timeSorting == null || "".equals(timeSorting)) {
			timeSorting = Constant.COURSE_SCHEDULE_TIMESORTING_Q;
		}
		courseSchedule.setTimeSorting(timeSorting);
		courseSchedule.setDayOfWeek(DateUtil.getIntWeekDay());
		Page<CourseSchedule> pageInfo = courseScheduleService.getPageInfo(courseSchedule, p, 9);
		List<CourseSchedule> lists = pageInfo.getResult();
		int pages = pageInfo.getPages(); // 总页数
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("search", courseSchedule.getSearch());
		model.addAttribute("timeSorting", timeSorting);
		return "/web/online/zb_message";
	}

	/**
	 * 进入评估视频播放页
	 * 
	 * 20190304-zjc modified; 由于郑师要求：按课程表时间自动开始和结束录制，这里就不需要再次手动触发；
	 * 
	 * @return
	 */
	@RequestMapping("/qualityPlay")
	public String qualityPlay(String id, Model model, HttpServletRequest request) {
		String classCode = null;
		String rtmpUrl = null;
		String ip = null;
		String ciptype = null;
		CourseSchedule courseSchedule = courseScheduleService.getCourseSchedule(id);
		SysClassroom classroom = sysClassroomService.findByKey(courseSchedule.getClassroomId());
		if (classroom != null) {
			String serviceIp = Utils.getAccessPathUrlOrIP(request, classroom.getServiceIp());
			rtmpUrl = "rtmp://" + serviceIp;
			classCode = classroom.getClassCode();
			String clientIp = classroom.getClientIp();
			if (clientIp != null && !"".equals(clientIp)) {
				String[] ArrayclientIp = clientIp.split(":|：");
				ip = ArrayclientIp[0].trim();
				Integer port = Integer.parseInt(ArrayclientIp[1].trim());
//				UdpSend usend = UdpSend.getInstance();    //20190304-zjc modified;
//				usend.sendStartData(ip, port);
				ciptype = "c";
			} else {
				String bublishCtrlService = environment.getProperty("bublishCtrlService");
				String[] ArraybublishCtrlServiceIp = bublishCtrlService.split(":|：");
				ip = ArraybublishCtrlServiceIp[0].trim();
				Integer port = Integer.parseInt(ArraybublishCtrlServiceIp[1].trim());
//				UdpSend usend = UdpSend.getInstance();    //20190304-zjc modified;
//				usend.sendStartData(ip, port, classCode);
				ciptype = "s";
			}
		}

		// 获取是否具有评估权限
		String isPgAuthority = userService.isPgAuthority(getUserId(), getUserType(), id, LoSchedule.PG_BAK2_G);
		// 获得推荐课程
		List<CourseSchedule> listRecommendVideos = listRecommendVideo(id, courseSchedule);
		// 视频缓存
		String buffertime = environment.getProperty("buffertime");
		String svc = environment.getProperty("video.split.screen", DEFAULTVALUE);

		model.addAttribute("rtmpUrl", rtmpUrl);
		model.addAttribute("classCode", classCode);
		model.addAttribute("cip", ip);
		model.addAttribute("ciptype", ciptype);
		model.addAttribute("buffertime", buffertime);
		model.addAttribute("svc", svc);

		model.addAttribute("id", id);
		model.addAttribute("schedule", courseSchedule);
		model.addAttribute("isPgAuthority", isPgAuthority);
		model.addAttribute("RCLists", listRecommendVideos);

		return "/web/online/qualityPlay";
	}

	/**
	 * 获得推荐课程 - 去除当前视频
	 * 
	 * @param id
	 * @param courseSchedule
	 * @return
	 */
	private List<CourseSchedule> listRecommendVideo(String id, CourseSchedule courseSchedule) {
		CourseSchedule courseSchedule2 = new CourseSchedule();
		courseSchedule2.setType(courseSchedule.getType());
		courseSchedule2.setNotId(id);
		List<CourseSchedule> listRecommendVideos = courseScheduleService.listRecommendVideos(courseSchedule2, 8);
		return listRecommendVideos;
	}

	/**
	 * 进入一带多播放页
	 * 
	 * @return
	 */
	@RequestMapping("/inManyPlay")
	public String inManyPlay(String id, Model model, HttpServletRequest request) {
		String ip = "";
		String port1 = "";
		String port2 = "";
		String rid = "";
		String uid = "";
		String uid1 = "";
		String uid2 = "";
		String uid3 = "";
		String title = "";
		String title1 = "";
		String title2 = "";
		String title3 = "";

		CourseSchedule courseSchedule = courseScheduleService.getCourseSchedule(id);
		SysClassroom classroom = sysClassroomService.findByKey(courseSchedule.getClassroomId());
		if (classroom != null) {
			String ipPort = Utils.getAccessPathUrlOrIP(request, classroom.getServiceIp());
			String[] ArrayServiceip = ipPort.split(":|：");

			ip = ArrayServiceip[0].trim();
			port1 = ArrayServiceip[1].trim();
			port2 = classroom.getWebPort();

			rid = classroom.getRoomId().trim();
			uid = classroom.getUid();
			title = "主教室：" + courseSchedule.getClassroomName() + "	主讲人:" + courseSchedule.getTeacherName();

			LoFschedule fschedule = new LoFschedule();
			fschedule.setZId(id);
			List<LoFschedule> fschedules = loFscheduleService.findSelective(fschedule);
			if (fschedules != null && fschedules.size() > 0) {
				classroom = sysClassroomService.findByKey(fschedules.get(0).getClassId());
				uid1 = classroom.getUid();
				title1 = "辅教室1：" + fschedules.get(0).getClassName() + "     辅教老师：" + fschedules.get(0).getUserName();

				if (fschedules.size() >= 2) {
					classroom = sysClassroomService.findByKey(fschedules.get(1).getClassId());
					uid2 = classroom.getUid();
					title2 = "辅教室2：" + fschedules.get(1).getClassName() + "     辅教老师："
							+ fschedules.get(1).getUserName();
				}

				if (fschedules.size() >= 3) {
					classroom = sysClassroomService.findByKey(fschedules.get(2).getClassId());
					uid3 = classroom.getUid();
					title3 = "辅教室3：" + fschedules.get(2).getClassName() + "     辅教老师："
							+ fschedules.get(2).getUserName();
				}
			}
		}
		// 获得推荐课程
		List<CourseSchedule> listRecommendVideos = listRecommendVideo(id, courseSchedule);

		model.addAttribute("id", id);
		model.addAttribute("ip", ip);
		model.addAttribute("port1", port1);
		model.addAttribute("port2", port2);
		model.addAttribute("rid", rid);
		model.addAttribute("uid", uid);
		model.addAttribute("uid1", uid1);
		model.addAttribute("uid2", uid2);
		model.addAttribute("uid3", uid3);
		model.addAttribute("title", title);
		model.addAttribute("title1", title1);
		model.addAttribute("title2", title2);
		model.addAttribute("title3", title3);
		model.addAttribute("schedule", courseSchedule);
		model.addAttribute("RCLists", listRecommendVideos);
		return "/web/online/inManyPlay";
	}

	/**
	 * 进入直播课堂的播放页
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/livePlay")
	public String livePlay(@RequestParam String id, Model model) {
		LoSchedule schedule = loScheduleService.findByKey(id);
		model.addAttribute("schedule", schedule);

		// 获得推荐课程
		List<LoSchedule> lists = loScheduleService.findRecommendedCourse(schedule);
		model.addAttribute("RCLists", lists);

		// 更新用户经验值
		loScheduleService.updateUserExp(schedule.getUserId(), schedule.getId());

		model.addAttribute("id", id);
		return "/web/online/livePlay";
	}

	/**
	 * 进入课表管理页面
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/zb_kechengbiao")
	public String zb_kechengbiao(HttpServletResponse response, HttpServletRequest request, Model model) {
		SysDict sysDict = new SysDict();
		// 平台等级筛选
		if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N")) {
			sysDict.setKeyname("P");
		} else if (platformLevel.equals("P")) {
			sysDict.setKeyname("C");
			sysDict.setPid(platformLevelId);
		} else if (platformLevel.equals("C")) {
			sysDict.setKeyname("A");
			sysDict.setPid(platformLevelId);
		} else if (platformLevel.equals("A")) {
			sysDict.setKeyname("T");
			sysDict.setPid(platformLevelId);

			SysSchool sysSchool = new SysSchool();
			sysSchool.setCountyId(platformLevelId);
			List<SysSchool> sysSchools = schoolService.findSelective(sysSchool);
			model.addAttribute("sysSchools", sysSchools);
		}
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);

		// 是否为校级
		String schoolLevel = environment.getProperty("school.level");
		String schoolId = environment.getProperty("school.id");

		model.addAttribute("provinceList", sysDicts);
		model.addAttribute("platformLevel", platformLevel);
		model.addAttribute("platformLevelId", platformLevelId);
		model.addAttribute("schoolLevel", schoolLevel);
		model.addAttribute("schoolId", schoolId);
		return "/web/online/zb_kechengbiao";
	}

	/**
	 * 获得课程表
	 * 
	 * @param classId
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/getSchedule")
	public String getschedule(String classId, String schoolId, Model model) throws ParseException {
		List<CourseSchedule> listCourseSchedule = courseScheduleService.listClassroomCourseSchedule(classId);
		List<CourseScheduleBigTime> listCourseScheduleBigTime = courseScheduleBigTimeService.findAll();
		courseScheduleBigTimeService.bigSectionOfDayStringFrombigSectionOfDay(listCourseScheduleBigTime);
		model.addAttribute("listCourseSchedule", listCourseSchedule);
		model.addAttribute("listCourseScheduleBigTime", listCourseScheduleBigTime);
		return "/web/online/zb_kechengbiaoData";
	}
	

}