package com.zzrenfeng.zhsx.controller.clientside;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.LoFschedule;
import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.LoClassTimeService;
import com.zzrenfeng.zhsx.service.LoFscheduleService;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.LoTermTimeService;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.shiro.UserNamePasswordUserTypeToken;
import com.zzrenfeng.zhsx.util.DateUtil;
import com.zzrenfeng.zhsx.util.Utils;

@Controller
@RequestMapping("/client")
public class ClientController extends BaseController {

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
	private LoFscheduleService loFscheduleService;

	/**
	 * 客户端登录接口
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		String loginState = "fail";
		String tmMemberId = "000";
		String username = request.getParameter("loginName");
		String password = request.getParameter("pwd");
		String usertype = request.getParameter("usertype");
		if (StringUtils.isBlank(usertype)) {
			usertype = "1";
		}
		if (usertype.equals("2")) {
			Md5Hash md5 = new Md5Hash(password);
			password = md5.toString();
		} else {
			Md5Hash md5 = new Md5Hash(password, username, 2);
			password = md5.toString();
		}
		Subject subject = SecurityUtils.getSubject();
		UserNamePasswordUserTypeToken token = new UserNamePasswordUserTypeToken(username, password);
		try {
			subject.login(token);
			loginState = "success";
			tmMemberId = "100";
		} catch (AuthenticationException e) {
			token.clear();
		}

		model.addAttribute("loginState", loginState);
		model.addAttribute("tmMemberId", tmMemberId);
		return "/cxml/interactive/loginResult";
	}

	/**
	 * 获得地区学校
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/getCountySchool")
	public String getCounty(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		return "/cxml/interactive/cOnlineVideoList2";
	}

	/**
	 * 获得课表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 */
	@RequestMapping("/getInformationTable")
	public String getInformationTable(HttpServletRequest request, HttpServletResponse response, Model model)
			throws UnsupportedEncodingException, ParseException {
		String classId = request.getParameter("schoolid");

		// 获取在线课程
		Date currentDate = DateUtil.NowDateShort();
		LoSchedule loSchedule = new LoSchedule();
		loSchedule.setZ_date(currentDate);
		loSchedule.setClassId(classId);
		loSchedule.setType("G");
		loSchedule.setTimeSorting("Q");
		List<LoSchedule> psilist = loScheduleService.listLoscheduleIncludeLoFschedule(loSchedule);
		model.addAttribute("psilist", psilist);
		return "/cxml/interactive/informationTable";
	}

	/**
	 * 进入直播页面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/live")
	public String live(HttpServletRequest request, HttpServletResponse response, Model model) {
		String zid = request.getParameter("zid");

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

		LoSchedule schedule = loScheduleService.findByKey(zid);
		model.addAttribute("schedule", schedule);

		SysClassroom classroom = sysClassroomService.findByKey(schedule.getClassId());
		if (classroom != null) {
			// String ipPort = classroom.getServiceIp();
			String ipPort = Utils.getAccessPathUrlOrIP(request, classroom.getServiceIp());
			String[] ArrayServiceip = ipPort.split(":|：");

			ip = ArrayServiceip[0].trim();
			port1 = ArrayServiceip[1].trim();
			port2 = classroom.getWebPort();

			rid = classroom.getRoomId().trim();
			uid = classroom.getUid();
			title = "主教室：" + schedule.getSchoolName() + " " + schedule.getClassName() + "    主讲人:"
					+ schedule.getUserName();

			LoFschedule fschedule = new LoFschedule();
			fschedule.setZId(zid);
			List<LoFschedule> fschedules = loFscheduleService.findSelective(fschedule);
			if (fschedules != null && fschedules.size() > 0) {
				classroom = sysClassroomService.findByKey(fschedules.get(0).getClassId());
				uid1 = classroom.getUid();
				title1 = "辅教室1：" + fschedules.get(0).getSchoolName() + " " + fschedules.get(0).getClassName()
						+ "     辅教老师：" + fschedules.get(0).getUserName();

				if (fschedules.size() >= 2) {
					classroom = sysClassroomService.findByKey(fschedules.get(1).getClassId());
					uid2 = classroom.getUid();
					title2 = "辅教室2：" + fschedules.get(1).getSchoolName() + " " + fschedules.get(1).getClassName()
							+ "     辅教老师：" + fschedules.get(1).getUserName();
				}

				if (fschedules.size() >= 3) {
					classroom = sysClassroomService.findByKey(fschedules.get(2).getClassId());
					uid3 = classroom.getUid();
					title3 = "辅教室3：" + fschedules.get(2).getSchoolName() + " " + fschedules.get(2).getClassName()
							+ "     辅教老师：" + fschedules.get(2).getUserName();
				}
			}
		}

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
		return "/cxml/interactive/live";
	}

}
