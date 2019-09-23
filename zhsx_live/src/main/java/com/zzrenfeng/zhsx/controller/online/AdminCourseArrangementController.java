package com.zzrenfeng.zhsx.controller.online;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.LoClassTime;
import com.zzrenfeng.zhsx.model.LoClassTimeExtend;
import com.zzrenfeng.zhsx.model.LoFschedule;
import com.zzrenfeng.zhsx.model.LoPgGroup;
import com.zzrenfeng.zhsx.model.LoPgGroupSchedule;
import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.LoTermTime;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.LoClassTimeService;
import com.zzrenfeng.zhsx.service.LoFscheduleService;
import com.zzrenfeng.zhsx.service.LoPgGroupScheduleService;
import com.zzrenfeng.zhsx.service.LoPgGroupService;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.LoTermTimeService;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.util.DateUtil;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 课程时间、课表安排管理
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/adminCourseArrangement")
public class AdminCourseArrangementController extends BaseController {

	@Resource
	private SysSchoolService sysSchoolService;
	@Resource
	private LoTermTimeService loTermTimeService;
	@Resource
	private LoClassTimeService loClassTimeService;
	@Resource
	private SysClassroomService sysClassroomService;
	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private UserService userService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private LoFscheduleService loFscheduleService;
	@Resource
	private LoPgGroupService loPgGroupService;
	@Resource
	private LoPgGroupScheduleService loPgGroupScheduleService;

	/**
	 * 进入时间管理页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/timeArrangement")
	public String timeArrangement(HttpServletRequest request, HttpServletResponse response) {
		return "/admin/courseArrangement/timeArrangement";
	}

	/**
	 * 获得学校信息
	 * 
	 * @param request
	 * @param p
	 * @return
	 */
	@RequestMapping("/getSchool")
	public @ResponseBody Map<String, Object> getSchool(HttpServletRequest request, Integer p) {
		Map<String, Object> hm = new HashMap<String, Object>();
		if (p == null)
			p = 1;
		SysSchool sysSchool = new SysSchool();
		String search = request.getParameter("search");
		if (search != null && search != "") {
			sysSchool.setSearch(search);
		}

		String bak1 = getUserBak1();
		String bak2 = getUserBak2();
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			sysSchool.setAuthority(bak1);
			List<String> ids = userService.getUserSchoolIds(bak1, bak2, getUserSchoolId());
			if (ids != null && ids.size() > 0)
				sysSchool.setIds(ids);
		}

		Page<SysSchool> pageInfo = sysSchoolService.findPageSelective(sysSchool, p, 20);
		int pages = pageInfo.getPages();// 总页数
		List<SysSchool> lists = pageInfo.getResult();

		hm.put("pageNum", p);
		hm.put("pages", pages);
		hm.put("lists", lists);
		hm.put("search", search);
		return hm;
	}

	/**
	 * 获得学校当前学期和上课时间的信息
	 * 
	 * @param model
	 * @param schoolId
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/getTime")
	public @ResponseBody Map<String, Object> getTime(String schoolId) throws ParseException {
		Map<String, Object> hm = new HashMap<String, Object>();
		LoTermTime loTermTime = loTermTimeService.getCurrTermTime(schoolId);
		if (loTermTime == null) {
			hm.put("state", 0);
		} else {
			hm.put("state", 1);
			String firstDay = DateUtil.getStringDate(loTermTime.getFirstDay(), "yyyy-MM-dd");
			String lastDay = DateUtil.getStringDate(loTermTime.getLastDay(), "yyyy-MM-dd");
			hm.put("loTermTime", loTermTime);
			hm.put("firstDay", firstDay);
			hm.put("lastDay", lastDay);
			if (loTermTime != null) {
				LoClassTime loClassTime = new LoClassTime();
				loClassTime.setTermTimeId(loTermTime.getId());
				List<LoClassTime> loClassTimes = loClassTimeService.findSelective(loClassTime);
				for (LoClassTime loClassTime2 : loClassTimes) {
					loClassTime2.setStarttimef(DateUtil.getStringDate(loClassTime2.getStarttime(), "HH:mm"));
					loClassTime2.setEndtimef(DateUtil.getStringDate(loClassTime2.getEndtime(), "HH:mm"));
				}
				hm.put("loClassTimes", loClassTimes);
			}
		}
		return hm;
	}

	/**
	 * 添加学期时间到数据库
	 * 
	 * @param loTermTime
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/insertTermTime")
	public @ResponseBody Map<String, Object> insertTermTime(LoTermTime loTermTime) throws ParseException {
		Map<String, Object> hm = new HashMap<String, Object>();

		Date firstDay = loTermTime.getFirstDay();
		Date lastDay = loTermTime.getLastDay();
		int j = DateUtil.getIntWeekDay(firstDay);
		if (j != 1) {
			String firstDay1 = DateUtil.getNextDay(firstDay, 1 - j, "yyyy-MM-dd");
			firstDay = java.sql.Date.valueOf(firstDay1);
		}

		int i = DateUtil.getdaysBetween(firstDay, lastDay);
		if (i <= 0) {
			hm.put("res", "最后一天时间必须大于第一天时间");
		} else {
			int totalweek = i / 7 + 1;
			loTermTime.setFirstDay(firstDay);
			loTermTime.setTotalWeeks(totalweek);
			int res = loTermTimeService.insert(loTermTime);
			if (res > 0) {
				hm.put("id", loTermTime.getId());
				hm.put("res", "S");
			} else {
				hm.put("res", "F");
			}
		}
		return hm;
	}

	/**
	 * 修改学期时间
	 * 
	 * @throws ParseException
	 */
	@RequestMapping("/updateTermTime")
	public @ResponseBody Map<String, Object> updateTermTime(HttpServletResponse response, LoTermTime loTermTime)
			throws ParseException {
		Map<String, Object> hm = new HashMap<String, Object>();
		Date firstDay = loTermTime.getFirstDay();
		Date lastDay = loTermTime.getLastDay();
		int j = DateUtil.getIntWeekDay(firstDay);
		if (j != 1) {
			String firstDay1 = DateUtil.getNextDay(firstDay, 1 - j, "yyyy-MM-dd");
			firstDay = java.sql.Date.valueOf(firstDay1);
		}

		int i = DateUtil.getdaysBetween(firstDay, lastDay);
		if (i <= 0) {
			hm.put("res", "最后一天时间必须大于第一天时间");
		} else {
			int totalweek = i / 7 + 1;
			loTermTime.setFirstDay(firstDay);
			loTermTime.setTotalWeeks(totalweek);
			int res = loTermTimeService.updateByKeySelective(loTermTime);
			if (res > 0) {
				hm.put("res", "S");
			} else {
				hm.put("res", "F");
			}
		}
		return hm;
	}

	/**
	 * 进入添加上课时间页面
	 * 
	 * @param termTimeId
	 * @return
	 */
	@RequestMapping("/addClassTime")
	public String addClassTime(String termTimeId, Model model) {
		model.addAttribute("termTimeId", termTimeId);

		LoClassTime loClassTime = new LoClassTime();
		loClassTime.setTermTimeId(termTimeId);
		List<LoClassTime> loClassTimes = loClassTimeService.findSelective(loClassTime);
		model.addAttribute("loClassTimes", loClassTimes);
		return "/admin/courseArrangement/addClassTime";
	}

	/**
	 * 添加上课时间到数据库
	 * 
	 * @param loClassTime
	 * @return
	 */
	@RequestMapping("/insertClassTime")
	public @ResponseBody Map<String, Object> insertClassTime(LoClassTimeExtend loClassTimeExtend) {
		Map<String, Object> hm = new HashMap<String, Object>();
		try {
			List<LoClassTime> loClassTimes = loClassTimeExtend.getLoClassTimeList();
			loClassTimeService.insertBatch(loClassTimes);
			hm.put("res", MessageUtils.SUCCESS);
		} catch (Exception e) {
			hm.put("res", MessageUtils.FAilURE);
			e.printStackTrace();
		}
		return hm;
	}

	/**
	 * 通过主键删除时间
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delClassTime")
	public void delClassTime(HttpServletResponse response, String id) {
		try {
			loClassTimeService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
			e.printStackTrace();
		}
	}

	/**
	 * 跳转到修改上课时间页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editClassTime")
	public String editClassTime(String id, Model model) {
		LoClassTime classTime = loClassTimeService.findByKey(id);
		model.addAttribute("classTime", classTime);
		return "/admin/courseArrangement/editClassTime";
	}

	/**
	 * 修改上课时间数据库信息
	 * 
	 * @param loClassTime
	 * @param model
	 */
	@RequestMapping("updateClassTime")
	public void updateClassTime(HttpServletResponse response, LoClassTime loClassTime) {
		try {
			loClassTimeService.updateByKeySelective(loClassTime);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入课表安排页面
	 * 
	 * @return
	 */
	@RequestMapping("/timetable")
	public String timetable(HttpServletRequest request, Model model) {

		SysClassroom classroom = new SysClassroom();
		SysSchool school = new SysSchool();

		classroom.setBak("Y");
		String search = request.getParameter("search");
		if (search != null && search != "") {
			school.setSearch(search);
			classroom.setSearch(search);
		}

		String bak1 = getUserBak1();
		String bak2 = getUserBak2();
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			classroom.setAuthority(bak1);
			school.setAuthority(bak1);
			List<String> schoolIds = userService.getUserSchoolIds(bak1, bak2, getUserSchoolId());
			if (schoolIds != null && schoolIds.size() > 0) {
				classroom.setSchoolIds(schoolIds);
				school.setIds(schoolIds);
			}
		}

		List<SysSchool> schoolList = sysSchoolService.findSchoolClassNotNull(school);
		List<SysClassroom> lists = sysClassroomService.findSelective(classroom);

		String treeString = "{name: '学校',spread: true,children: [";
		for (SysSchool sysSchool : schoolList) {
			treeString += "{name:'" + sysSchool.getSchoolName() + "',children: [";
			for (SysClassroom sysClassroom : lists) {
				if (sysClassroom.getSchoolId().equals(sysSchool.getId())) {
					treeString += "{ name: '" + sysClassroom.getClassName() + "',id:'" + sysClassroom.getId()
							+ "',schoolId: '" + sysClassroom.getSchoolId() + "',schoolName:'"
							+ sysSchool.getSchoolName() + "'},";
				}
			}
			treeString += "]},";
		}
		treeString += "]}";

		model.addAttribute("treeString", treeString);
		return "/admin/courseArrangement/timetable";
	}

	/**
	 * 获得课程表
	 * 
	 * @param classId
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/getschedule")
	public String getschedule(String classId, String schoolId, Integer weeks, String MondayDate, Integer lastandnext,
			Model model) throws ParseException {
		String termTimeId = null;
		Map<String, Object> s = loTermTimeService.getCurrTermTimeWeeks(schoolId);
		if (s != null && s.size() > 0) {
			if (weeks == null) {
				weeks = (Integer) s.get("weeks");
			}
			termTimeId = (String) s.get("termTimeId");
			model.addAttribute("totalWeeks", s.get("totalWeeks"));
		}
		model.addAttribute("weeks", weeks);
		Map<String, Object> hm;
		if (MondayDate != null) {
			if (lastandnext == 1) {
				MondayDate = DateUtil.getNextDay(java.sql.Date.valueOf(MondayDate), 7, "yyyy-MM-dd");
			}
			if (lastandnext == -1) {
				MondayDate = DateUtil.getNextDay(java.sql.Date.valueOf(MondayDate), -7, "yyyy-MM-dd");
			}
			hm = DateUtil.getOneWeekDate(java.sql.Date.valueOf(MondayDate), "yyyy-MM-dd");
		} else
			hm = DateUtil.getOneWeekDate(new Date(), "yyyy-MM-dd");
		model.addAttribute("hm", hm);

		LoSchedule loSchedule = new LoSchedule();
		loSchedule.setClassId(classId);
		// loSchedule.setWeeks(weeks);
		loSchedule.setStartDate((String) hm.get("SundayDate"));
		loSchedule.setEndDate((String) hm.get("SaturdayDate"));
		List<LoSchedule> loSchedules = loScheduleService.findSelective(loSchedule);
		model.addAttribute("loSchedules", loSchedules);

		if (termTimeId != null) {
			model.addAttribute("state", 1);
			LoClassTime classTime = new LoClassTime();
			classTime.setTermTimeId(termTimeId);
			List<LoClassTime> classTimes = loClassTimeService.findSelective(classTime);
			model.addAttribute("classTimes", classTimes);
		} else {
			model.addAttribute("state", 0);
		}
		model.addAttribute("termTimeId", termTimeId);
		return "/admin/courseArrangement/schedule";
	}

	/**
	 * 进入添加页面
	 * 
	 * @param model
	 * @param classId
	 * @param schoolId
	 * @param dayofweek
	 * @param sectionofday
	 * @param z_date
	 * @param weeks
	 * @param classTimeId
	 * @param termTimeId
	 * @return
	 */
	@RequestMapping("/addSchedule")
	public String addSchedule(Model model, String classId, String schoolId, Integer dayofweek, Integer sectionofday,
			Date z_date, Integer weeks, String classTimeId, String termTimeId) {
		model.addAttribute("classId", classId);
		model.addAttribute("schoolId", schoolId);
		model.addAttribute("dayofweek", dayofweek);
		model.addAttribute("sectionofday", sectionofday);
		model.addAttribute("z_date", z_date);
		model.addAttribute("weeks", weeks);
		model.addAttribute("classTimeId", classTimeId);
		model.addAttribute("termTimeId", termTimeId);

		// 获得教师
		List<User> userList = userService.findTeacherBySchoolId(schoolId);
		model.addAttribute("userList", userList);

		// 获得年级
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("G");
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("sysDicts", sysDicts);

		// 获得学校 - 用于辅教室的选择(可选择所有学校)
		SysSchool school = new SysSchool();
		List<SysSchool> schoolList = sysSchoolService.findSelective(school);
		model.addAttribute("schoolList", schoolList);

		// 获取评估小组
		LoPgGroup loPgGroup = new LoPgGroup();
		loPgGroup.setCreaterId(getUserId());
		List<LoPgGroup> loPgGroups = loPgGroupService.findSelective(loPgGroup);
		model.addAttribute("loPgGroups", loPgGroups);

		return "/admin/courseArrangement/addSchedule";
	}

	/**
	 * 添加直播信息到数据库
	 * 
	 * @param loSchedule
	 */
	@RequestMapping("insertSchedule")
	public void insertSchedule(HttpServletResponse response, LoSchedule loSchedule, String[] pgGroupIds) {
		LoSchedule loSchedule2 = new LoSchedule();
		loSchedule2.setUserId(loSchedule.getUserId());
		loSchedule2.setZ_date(loSchedule.getZ_date());
		loSchedule2.setSectionofday(loSchedule.getSectionofday());
		List<LoSchedule> findSelective = loScheduleService.findSelective(loSchedule2);
		if (findSelective != null && findSelective.size() > 0) {
			WriterUtils.toHtml(response, "该教师在同一时间已经安排过课程,不可重复安排");
			return;
		}

		try {

			String type = loSchedule.getType();
			switch (type) {
			case "G":
				loScheduleService.insertScheduleInMany(loSchedule);
				break;
			case "Z":
				loScheduleService.insert(loSchedule);
				break;
			case "A":

				// 如果选择的评估小组为空则设置为公开评估课
				if (pgGroupIds == null || pgGroupIds.length <= 0) {
					loSchedule.setBak2("G");
				}
				loScheduleService.insert(loSchedule);
				if (loSchedule.getBak2().equals("S")) {
					loPgGroupScheduleService.batchInster(loSchedule.getId(), pgGroupIds);
				}

				break;
			}

			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}

	}

	/**
	 * 删除课程信息
	 * 
	 * @param response
	 * @param id
	 * @param type
	 */
	@RequestMapping("/delSchedule")
	public void delSchedule(HttpServletResponse response, String id, String type) {
		try {
			loScheduleService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 批量删除课程
	 * 
	 * @param response
	 * @param loSchedule
	 */
	@RequestMapping("/delScheduleBatch")
	public void delOneWeekSchedule(HttpServletResponse response, LoSchedule loSchedule) {
		try {
			loScheduleService.delScheduleBatch(loSchedule);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入编辑页面
	 * 
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping("/editSchedule")
	public String editSchedule(String id, String type, Model model) {
		LoSchedule loSchedule = loScheduleService.findByKey(id);
		model.addAttribute("loSchedule", loSchedule);
		model.addAttribute("type", type);
		// 获得年级
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("G");
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("sysDicts", sysDicts);

		// 获得科目
		sysDict.setValue(loSchedule.getGradeId());
		sysDicts = sysDictService.findSelective(sysDict);
		if (sysDicts != null && sysDicts.size() > 0) {
			sysDict.setPid(sysDicts.get(0).getId());
		}
		sysDict.setKeyname("S");
		sysDict.setValue(null);
		sysDicts = sysDictService.findSelective(sysDict);
		// 科目去重复
		List<SysDict> subjects = new ArrayList<SysDict>();
		String tem = "1";
		for (SysDict o : sysDicts) {
			if (!tem.contains(o.getValue())) {
				subjects.add(o);
			}
			tem += o.getValue();
		}
		model.addAttribute("sysDictsList", subjects);

		// 获得教师
		User user = new User();
		user.setSchoolId(loSchedule.getSchoolId());
		List<User> userList = userService.findSelective(user);
		model.addAttribute("userList", userList);

		// 获得学校
		SysSchool school = new SysSchool();
		List<SysSchool> schoolList = sysSchoolService.findSelective(school);
		model.addAttribute("schoolList", schoolList);

		// 获取评估小组
		LoPgGroup loPgGroup = new LoPgGroup();
		loPgGroup.setCreaterId(getUserId());
		List<LoPgGroup> loPgGroups = loPgGroupService.findSelective(loPgGroup);
		model.addAttribute("loPgGroups", loPgGroups);
		// 获取已选择评估小组
		if (type.equals("A")) {
			LoPgGroupSchedule loPgGroupSchedule = new LoPgGroupSchedule();
			loPgGroupSchedule.setLoscheduleId(id);
			List<LoPgGroupSchedule> loPgGroupSchedules = loPgGroupScheduleService.findSelective(loPgGroupSchedule);
			model.addAttribute("loPgGroupSchedules", loPgGroupSchedules);
		}

		if (type.equals("G")) {
			LoFschedule loFschedule = new LoFschedule();
			loFschedule.setZId(id);
			List<LoFschedule> loFscheduleList = loFscheduleService.findSelective(loFschedule);

			// 获得辅教室1
			LoFschedule LoFschedule1 = loFscheduleList.get(0);
			model.addAttribute("LoFschedule1", LoFschedule1);
			// 获得教室
			SysClassroom classroom = new SysClassroom();
			classroom.setSchoolId(LoFschedule1.getSchoolId());
			List<SysClassroom> classroomList1 = sysClassroomService.findSelective(classroom);
			model.addAttribute("classroomList1", classroomList1);
			// 获得教师
			user.setSchoolId(LoFschedule1.getSchoolId());
			List<User> f1userList = userService.findSelective(user);
			model.addAttribute("f1userList", f1userList);

			if (loFscheduleList.size() >= 2) {
				LoFschedule LoFschedule2 = loFscheduleList.get(1);
				model.addAttribute("LoFschedule2", LoFschedule2);

				classroom.setSchoolId(loFscheduleList.get(1).getSchoolId());
				List<SysClassroom> classroomList2 = sysClassroomService.findSelective(classroom);
				model.addAttribute("classroomList2", classroomList2);

				user.setSchoolId(loFscheduleList.get(1).getSchoolId());
				List<User> f2userList = userService.findSelective(user);
				model.addAttribute("f2userList", f2userList);
			}
			if (loFscheduleList.size() >= 3) {
				LoFschedule LoFschedule3 = loFscheduleList.get(2);
				model.addAttribute("LoFschedule3", LoFschedule3);

				classroom.setSchoolId(loFscheduleList.get(2).getSchoolId());
				List<SysClassroom> classroomList3 = sysClassroomService.findSelective(classroom);
				model.addAttribute("classroomList3", classroomList3);

				user.setSchoolId(loFscheduleList.get(2).getSchoolId());
				List<User> f3userList = userService.findSelective(user);
				model.addAttribute("f3userList", f3userList);
			}
		}

		return "/admin/courseArrangement/editSchedule";
	}

	/**
	 * 修改数据库课表信息
	 * 
	 * @param response
	 * @param loSchedule
	 */
	@RequestMapping("/uploadSchedule")
	public void uploadSchedule(HttpServletResponse response, LoSchedule loSchedule, String[] pgGroupIds) {
		try {

			String type = loSchedule.getType();
			switch (type) {
			case "G":
				loScheduleService.uploadScheduleInMany(loSchedule);
				break;
			case "Z":
				loFscheduleService.deleteByzId(loSchedule.getId());
				loSchedule.setJohn_num(0);
				loScheduleService.updateByKeySelective(loSchedule);
				break;
			case "A":
				// 如果选择的评估小组为空则设置为公开评估课
				if (pgGroupIds == null || pgGroupIds.length <= 0) {
					loSchedule.setBak2("G");
				}
				loFscheduleService.deleteByzId(loSchedule.getId());
				loSchedule.setJohn_num(0);
				loScheduleService.updateByKeySelective(loSchedule);
				if (loSchedule.getBak2().equals("S")) {
					loPgGroupScheduleService.batchInster(loSchedule.getId(), pgGroupIds);
				}
				break;
			}
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE + ".可能原因,该教师在同一时间已经安排过课程,不可重复安排.");
			e.printStackTrace();
		}

	}

	/**
	 * 进入批量添加页面
	 * 
	 * @param model
	 * @param classId
	 * @param schoolId
	 * @param termTimeId
	 * @return
	 */
	@RequestMapping("/batchAddSchedule")
	public String batchAddSchedule(Model model, String classId, String schoolId, String termTimeId,
			Integer totalWeeks) {
		model.addAttribute("classId", classId);
		model.addAttribute("schoolId", schoolId);
		model.addAttribute("termTimeId", termTimeId);
		model.addAttribute("totalWeeks", totalWeeks);

		// 获得年级
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("G");
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("sysDicts", sysDicts);

		// 获得教师
		List<User> userList = userService.findTeacherBySchoolId(schoolId);
		model.addAttribute("userList", userList);

		// 获得节次
		LoClassTime loClassTime = new LoClassTime();
		loClassTime.setTermTimeId(termTimeId);
		List<LoClassTime> loClassTimes = loClassTimeService.findSelective(loClassTime);
		model.addAttribute("loClassTimes", loClassTimes);

		// 获得学校- 辅教室学校选择开放为所有学校都可以选择
		SysSchool school = new SysSchool();
		List<SysSchool> schoolList = sysSchoolService.findSelective(school);
		model.addAttribute("schoolList", schoolList);

		return "/admin/courseArrangement/batchAddSchedule";
	}

	/**
	 * 批量添加直播信息到数据库
	 * 
	 * @param loSchedule
	 */
	@RequestMapping("insertScheduleBatch")
	public void insertScheduleBatch(HttpServletResponse response, LoSchedule loSchedule, Integer startWeek,
			Integer endWeek) {
		try {
			loScheduleService.insertScheduleBatch(loSchedule, startWeek, endWeek);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

}
