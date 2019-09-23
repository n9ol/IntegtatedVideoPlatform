package com.zzrenfeng.zhsx.controller.online;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.LoClassTime;
import com.zzrenfeng.zhsx.model.LoFschedule;
import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.service.LoClassTimeService;
import com.zzrenfeng.zhsx.service.LoFscheduleService;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.LoTermTimeService;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
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
	private Environment env;

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
		model.addAttribute("type", type);
		// 获得年级
		SysDict dict = new SysDict();
		dict.setKeyname("G");
		List<SysDict> grades = sysDictService.findSelective(dict);
		model.addAttribute("grades", grades);

		// 获得科目 去重复
		List<SysDict> subjects = new ArrayList<SysDict>();
		dict.setKeyname("S");
		List<SysDict> subject = sysDictService.findSelective(dict);
		String tem = "1";
		for (SysDict o : subject) {
			if (!tem.contains(o.getValue())) {
				subjects.add(o);
			}
			tem += o.getValue();
		}
		model.addAttribute("subjects", subjects);

		// 获得热门推荐
		LoSchedule loSchedule = new LoSchedule();
		loSchedule.setTimeSorting("Q");
		loSchedule.setType(type);
		Page<LoSchedule> pageInfo = loScheduleService.findPage(loSchedule, 1, 4);
		List<LoSchedule> recommendVideos = pageInfo.getResult();
		model.addAttribute("recommendVideos", recommendVideos);
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
	public String getZbMessage(Model model, Integer p, LoSchedule loSchedule) {
		if (p == null) {
			p = 1;
		}
		String type = loSchedule.getType();
		if ("G".equals(type)) {
			loSchedule.setBak2(LoSchedule.PG_BAK2_N);
		} else {
			loSchedule.setType(null);
			loSchedule.setBak2(LoSchedule.PG_BAK2_G);
		}
		Page<LoSchedule> pageInfo = loScheduleService.findPage(loSchedule, p, 9);
		int pages = pageInfo.getPages(); // 总页数
		List<LoSchedule> lists = pageInfo.getResult();

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("search", loSchedule.getSearch());
		model.addAttribute("timeSorting", loSchedule.getTimeSorting());
		return "/web/online/zb_message";
	}

	/**
	 * 进入评估视频播放页
	 * 
	 * @return
	 */
	@RequestMapping("/qualityPlay")
	public String qualityPlay(String id, Model model, HttpServletRequest request) {
		model.addAttribute("id", id);
		LoSchedule schedule = loScheduleService.findByKey(id);
		model.addAttribute("schedule", schedule);
		SysClassroom classroom = sysClassroomService.findByKey(schedule.getClassId());
		if (classroom != null) {
			String classCode = classroom.getClassCode();
			String serviceIp = Utils.getAccessPathUrlOrIP(request, classroom.getServiceIp());
			String rtmpUrl = "rtmp://" + serviceIp;
			model.addAttribute("rtmpUrl", rtmpUrl);
			model.addAttribute("classCode", classCode);

			String clientIp = classroom.getClientIp();

			if (clientIp != null && !"".equals(clientIp)) {
				String[] ArrayclientIp = clientIp.split(":|：");
				String ip = ArrayclientIp[0].trim();
				Integer port = Integer.parseInt(ArrayclientIp[1].trim());
				UdpSend usend = UdpSend.getInstance();
				usend.sendStartData(ip, port);
				model.addAttribute("cip", ip);
				model.addAttribute("ciptype", "c");
			} else {
				String bublishCtrlPort = null;
				String[] split = serviceIp.split(":");
				try {
					bublishCtrlPort = env.getProperty("bublishCtrlService");
				} catch (Exception e1) {
					bublishCtrlPort = "9816";
				}

				String ip = split[0].trim();
				Integer port = Integer.parseInt(bublishCtrlPort);
				UdpSend usend = UdpSend.getInstance();
				usend.sendStartData(ip, port, classCode);
				model.addAttribute("cip", ip);
				model.addAttribute("ciptype", "s");
			}
		}
		String buffertime = env.getProperty("buffertime");
		model.addAttribute("buffertime", buffertime);

		// 获取是否具有评估权限
		String isPgAuthority = userService.isPgAuthority(getUserId(), getUserType(), id, schedule.getBak2());
		model.addAttribute("isPgAuthority", isPgAuthority);

		// 获得推荐课程
		List<LoSchedule> lists = loScheduleService.findRecommendedCourse(schedule);
		model.addAttribute("RCLists", lists);

		// 更新用户经验值
		loScheduleService.updateUserExp(schedule.getUserId(), schedule.getId());

		return "/web/online/qualityPlay";
	}

	/**
	 * 进入评估视频播放页（查看评估结果）
	 * 
	 * @return
	 */
	@RequestMapping("/getPgResult")
	public String getPgResult(String id, String state, Model model, HttpServletRequest request) {
		model.addAttribute("duke", state);

		model.addAttribute("id", id);
		LoSchedule schedule = loScheduleService.findByKey(id);
		model.addAttribute("schedule", schedule);
		SysClassroom classroom = sysClassroomService.findByKey(schedule.getClassId());
		String classCode = null;
		if (classroom != null) {

			String serviceIp = Utils.getAccessPathUrlOrIP(request, classroom.getServiceIp());
			String rtmpUrl = "rtmp://" + serviceIp;
			classCode = classroom.getClassCode();
			model.addAttribute("rtmpUrl", rtmpUrl);
			model.addAttribute("classCode", classCode);

			String clientIp = classroom.getClientIp();

			if (clientIp != null && !clientIp.equals("")) {
				String[] ArrayclientIp = clientIp.split(":|：");
				String ip = ArrayclientIp[0].trim();
				Integer port = Integer.parseInt(ArrayclientIp[1].trim());
				UdpSend usend = UdpSend.getInstance();
				usend.sendStartData(ip, port);
				model.addAttribute("cip", ip);
				model.addAttribute("ciptype", "c");
			} else {
				String bublishCtrlPort = null;
				String[] split = serviceIp.split(":");
				try {
					bublishCtrlPort = env.getProperty("bublishCtrlService");
				} catch (Exception e1) {
					bublishCtrlPort = "9816";
				}

				String ip = split[0].trim();
				Integer port = Integer.parseInt(bublishCtrlPort);
				UdpSend usend = UdpSend.getInstance();
				usend.sendStartData(ip, port, classCode);
				model.addAttribute("cip", ip);
				model.addAttribute("ciptype", "s");
			}
		}

		model.addAttribute("isPgAuthority", "Y");

		// 获得推荐课程
		List<LoSchedule> lists = loScheduleService.findRecommendedCourse(schedule);
		model.addAttribute("RCLists", lists);

		// 更新用户经验值
		loScheduleService.updateUserExp(schedule.getUserId(), schedule.getId());
		return "/web/online/getPgResult";
	}

	/**
	 * 进入一带多播放页
	 * 
	 * @return
	 */
	@RequestMapping("/inManyPlay")
	public String inManyPlay(String id, Model model, HttpServletRequest request) {
		String classCode = "";
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

		LoSchedule schedule = loScheduleService.findByKey(id);
		model.addAttribute("schedule", schedule);

		SysClassroom classroom = sysClassroomService.findByKey(schedule.getClassId());
		if (classroom != null) {
			classCode = classroom.getClassCode();
			String ipPort = Utils.getAccessPathUrlOrIP(request, classroom.getServiceIp());
			String[] ArrayServiceip = ipPort.split(":|：");

			ip = ArrayServiceip[0].trim();
			port1 = ArrayServiceip[1].trim();
			port2 = classroom.getWebPort();

			rid = classroom.getRoomId().trim();
			uid = classroom.getUid();
			title = "主教室：" + schedule.getSchoolName() + "-" + schedule.getUserName();

			LoFschedule fschedule = new LoFschedule();
			fschedule.setZId(id);
			List<LoFschedule> fschedules = loFscheduleService.findSelective(fschedule);
			if (fschedules != null && fschedules.size() > 0) {
				classroom = sysClassroomService.findByKey(fschedules.get(0).getClassId());
				uid1 = classroom.getUid();
				title1 = "辅教室I：" + fschedules.get(0).getSchoolName() + "-" + fschedules.get(0).getUserName();

				if (fschedules.size() >= 2) {
					classroom = sysClassroomService.findByKey(fschedules.get(1).getClassId());
					uid2 = classroom.getUid();
					title2 = "辅教室II：" + fschedules.get(1).getSchoolName() + "-" + fschedules.get(1).getUserName();
				}

				if (fschedules.size() >= 3) {
					classroom = sysClassroomService.findByKey(fschedules.get(2).getClassId());
					uid3 = classroom.getUid();
					title3 = "辅教室III：" + fschedules.get(2).getSchoolName() + "-" + fschedules.get(2).getUserName();
				}
			}
		}

		model.addAttribute("id", id);
		model.addAttribute("classCode", classCode);
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

		// 获取是否具有评估权限
		String isPgAuthority = userService.isPgAuthority(getUserId(), getUserType(), id, schedule.getBak2());
		model.addAttribute("isPgAuthority", isPgAuthority);

		// 获得推荐课程
		List<LoSchedule> lists = loScheduleService.findRecommendedCourse(schedule);
		model.addAttribute("RCLists", lists);

		// 更新用户经验值
		loScheduleService.updateUserExp(schedule.getUserId(), schedule.getId());

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

		// 平台等级筛选
		SysDict sysDict = new SysDict();
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

		String type = request.getParameter("type");

		model.addAttribute("type", type);
		model.addAttribute("provinceList", sysDicts);
		model.addAttribute("platformLevel", platformLevel);
		model.addAttribute("platformLevelId", platformLevelId);
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
	public String getschedule(String classId, String schoolId, String type, Model model) throws ParseException {
		Map<String, Object> s = loTermTimeService.getCurrTermTimeWeeks(schoolId);
		if (s != null && s.size() > 0) {
			// Integer weeks = (Integer) s.get("weeks");
			String termTimeId = (String) s.get("termTimeId");

			Map<String, Object> hm = DateUtil.getOneWeekDate(new Date(), "yyyy-MM-dd");
			// model.addAttribute("hm", hm);

			// 当后台修改 学校的日期时间时,周次 所对应的 时间将会改变,这里以,周次和时间同时作为查询条件将会出现bug,这里以时间为准,
			// 废除 weeks
			// loSchedule.setWeeks(weeks);
			LoSchedule loSchedule = new LoSchedule();
			loSchedule.setClassId(classId);
			if ("G".equals(type)) {
				loSchedule.setType(type);
				loSchedule.setBak2(LoSchedule.PG_BAK2_N);
			} else {
				loSchedule.setType(null);
				loSchedule.setBak2(LoSchedule.PG_BAK2_G);
			}
			loSchedule.setStartDate((String) hm.get("SundayDate"));
			loSchedule.setEndDate((String) hm.get("SaturdayDate"));
			List<LoSchedule> loSchedules = loScheduleService.findSelective(loSchedule);
			model.addAttribute("loSchedules", loSchedules);
			model.addAttribute("totalWeeks", s.get("totalWeeks"));
			if (termTimeId != null) {
				LoClassTime classTime = new LoClassTime();
				classTime.setTermTimeId(termTimeId);
				List<LoClassTime> classTimes = loClassTimeService.findSelective(classTime);
				model.addAttribute("classTimes", classTimes);
			}
		}
		return "/web/online/zb_kechengbiaoData";
	}

}
