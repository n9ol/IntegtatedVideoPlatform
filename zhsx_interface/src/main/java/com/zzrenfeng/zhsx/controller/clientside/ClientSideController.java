package com.zzrenfeng.zhsx.controller.clientside;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zzrenfeng.zhsx.constant.Constant;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseSchedule;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseScheduleBigTime;
import com.zzrenfeng.zhsx.service.LoClassTimeService;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.LoTermTimeService;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleBigTimeService;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleService;
import com.zzrenfeng.zhsx.util.DateUtil;
import com.zzrenfeng.zhsx.util.UdpSend;
import com.zzrenfeng.zhsx.util.Utils;

/**
 * 客户端接口
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/onLpg")
public class ClientSideController extends BaseController {

	@Resource
	private SysSchoolService sysSchoolService;
	@Resource
	private UserService userService;
	@Resource
	private SysClassroomService sysClassroomService;
	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private LoTermTimeService loTermTimeService;
	@Resource
	private LoClassTimeService loClassTimeService;
	@Resource
	private CourseScheduleService courseScheduleService;
	@Resource
	private CourseScheduleBigTimeService courseScheduleBigTimeService;
	@Resource
	private Environment environment;

	/**
	 * flex客户端获取在线课程，学校班级
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/cOnlineVideoList2")
	public String cOnlineVideoList2(HttpServletRequest request, HttpServletResponse response, Model model) {

		List<SysSchool> schlist = new ArrayList<SysSchool>();
		SysSchool school = new SysSchool();

		// 根据登录人的账号类型控制前台显示
		String bak1 = getUserBak1();
		String bak2 = getUserBak2();
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			school.setAuthority(bak1);
			List<String> schoolIds = userService.getUserSchoolIds(bak1, bak2, getUserSchoolId());
			if (schoolIds != null && schoolIds.size() > 0) {
				school.setIds(schoolIds);
			}
		}

		schlist = sysSchoolService.findSchoolClassNotNull(school);

		if (schlist != null) {
			List<SysClassroom> classList = null;
			SysClassroom sysclass = new SysClassroom();
			for (SysSchool sysSchool : schlist) {
				sysclass.setSchoolId(sysSchool.getId());
				classList = sysClassroomService.findSelective(sysclass);
				sysSchool.setClassRoomList(classList);
			}
		}
		model.addAttribute("schlist", schlist);

		return "/cxml/alone/cOnlineVideoList2";

	}

	/**
	 * flex客户端获取在线课程
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/cOnlineVideoList3")
	public String cOnlineVideoList3(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam String id) throws ParseException {
		CourseSchedule courseSchedule = new CourseSchedule();
		courseSchedule.setClassroomId(id);
		courseSchedule.setTimeSorting(Constant.COURSE_SCHEDULE_TIMESORTING_Z);
		courseSchedule.setDayOfWeek(DateUtil.getIntWeekDay());
		courseSchedule.setType(Constant.COURSESCHEDULE_TYPE_A);
		List<CourseSchedule> listCourseSchedule = courseScheduleService.listCourseSchedule(courseSchedule);
		model.addAttribute("psilist", listCourseSchedule);
		return "/cxml/alone/cOnlineVideoList3";
	}

	/**
	 * flex客户端 - 进入视频播放
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/cOnlineVideo1")
	public String cOnlineVideo1(HttpServletRequest request, HttpServletResponse response, Model model)
			throws ParseException {
		String classCode = null;
		String rtmpUrl = null;
		String ip = null;
		String ciptype = null;

		String psiid = request.getParameter("id");
		CourseSchedule courseSchedule = courseScheduleService.getCourseSchedule(psiid);
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
				UdpSend usend = UdpSend.getInstance();
				usend.sendStartData(ip, port);
				ciptype = "c";
			} else {
				String bublishCtrlService = environment.getProperty("bublishCtrlService");
				String[] ArraybublishCtrlServiceIp = bublishCtrlService.split(":|：");
				ip = ArraybublishCtrlServiceIp[0].trim();
				Integer port = Integer.parseInt(ArraybublishCtrlServiceIp[1].trim());
				UdpSend usend = UdpSend.getInstance();
				usend.sendStartData(ip, port, classCode);
				ciptype = "s";
			}
		}
		String buffertime = environment.getProperty("buffertime");

		model.addAttribute("rtmpUrl", rtmpUrl);
		model.addAttribute("cip", ip);
		model.addAttribute("ciptype", ciptype);
		model.addAttribute("classcode", classCode);
		model.addAttribute("buffertime", buffertime);

		model.addAttribute("classname", classroom.getClassName());
		model.addAttribute("rlive", courseSchedule);
		return "/cxml/alone/cOnlineVideo1";
	}

	/**
	 * 获取课程表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/admcheduleitemlist")
	public String admcheduleitemlist(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam String id, String type) throws ParseException {
		List<CourseSchedule> listCourseSchedule = courseScheduleService.listClassroomCourseSchedule(id);
		List<CourseScheduleBigTime> listCourseScheduleBigTime = courseScheduleBigTimeService.findAll();
		courseScheduleBigTimeService.bigSectionOfDayStringFrombigSectionOfDay(listCourseScheduleBigTime);
		model.addAttribute("psilist", listCourseSchedule);
		model.addAttribute("psslist", listCourseScheduleBigTime);
		model.addAttribute("classid", id);
		return "/cxml/alone/scheduleItemList";
	}

}
