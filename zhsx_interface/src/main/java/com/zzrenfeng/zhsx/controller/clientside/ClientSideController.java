package com.zzrenfeng.zhsx.controller.clientside;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.LoClassTime;
import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.LoClassTimeService;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.LoTermTimeService;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
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
	private Environment env;

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
		// 获取在线课程
		Date date = DateUtil.NowDateShort();
		LoSchedule loSchedule = new LoSchedule();
		loSchedule.setClassId(id);
		loSchedule.setZ_date(date);
		loSchedule.setTimeSorting("Q");
		List<LoSchedule> psilist = loScheduleService.findSelective(loSchedule);
		model.addAttribute("psilist", psilist);
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
		String psiid = request.getParameter("id");

		LoSchedule schedule = loScheduleService.findByKey(psiid);
		model.addAttribute("rlive", schedule);
		SysClassroom classroom = sysClassroomService.findByKey(schedule.getClassId());
		if (classroom != null) {
			String serviceIp = Utils.getAccessPathUrlOrIP(request, classroom.getServiceIp());
			String rtmpUrl = "rtmp://" + serviceIp;
			model.addAttribute("rtmpUrl", rtmpUrl);

			// 开启课程发布
			String ipPort = classroom.getClientIp();
			String classcode = classroom.getClassCode();
			model.addAttribute("classcode", classcode);

			if (StringUtils.isNotBlank(ipPort)) {
				String ip = ipPort.substring(0, ipPort.indexOf(":"));
				Integer port = Integer.parseInt(ipPort.substring(ipPort.indexOf(":") + 1));
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
				usend.sendStartData(ip, port, classcode);
				model.addAttribute("cip", ip);
				model.addAttribute("ciptype", "s");
			}
			model.addAttribute("classname", classroom.getClassName());
		}
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
		SysClassroom sysclasses = sysClassroomService.findByKey(id);
		String voidType = type;
		Map<String, Object> s = loTermTimeService.getCurrTermTimeWeeks(sysclasses.getSchoolId());
		if (s != null && s.size() > 0) {
			String termTimeId = (String) s.get("termTimeId");

			Map<String, Object> hm = DateUtil.getOneWeekDate(new Date(), "yyyy-MM-dd");

			LoSchedule loSchedule = new LoSchedule();
			loSchedule.setClassId(id);
			loSchedule.setType(voidType);
			loSchedule.setStartDate((String) hm.get("SundayDate"));
			loSchedule.setEndDate((String) hm.get("SaturdayDate"));
			List<LoSchedule> loSchedules = loScheduleService.findSelective(loSchedule);
			model.addAttribute("psilist", loSchedules);
			if (termTimeId != null) {
				LoClassTime classTime = new LoClassTime();
				classTime.setTermTimeId(termTimeId);
				List<LoClassTime> classTimes = loClassTimeService.findSelective(classTime);
				model.addAttribute("psslist", classTimes);
			}
		}
		model.addAttribute("classid", id);

		return "/cxml/alone/scheduleItemList";
	}

}
