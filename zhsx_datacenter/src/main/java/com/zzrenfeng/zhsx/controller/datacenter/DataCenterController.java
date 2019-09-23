package com.zzrenfeng.zhsx.controller.datacenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.downfile.CreateFile;
import com.zzrenfeng.zhsx.mapper.DataStatisticsMapper;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.model.WebQuestionnaireResult;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.WebPjService;
import com.zzrenfeng.zhsx.service.WebQuestionnaireResultService;
import com.zzrenfeng.zhsx.util.CommonConfigUtil;
import com.zzrenfeng.zhsx.util.DateUtil;
import com.zzrenfeng.zhsx.util.JsonUtil;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 设备管理
 * 
 * @copyright {@link zzrenfeng.com}
 * @author David
 * @version 2017-08-08 14:24:22
 * @see com.zzrenfeng.zhsx.controller.WebDeviceManage
 */
@Controller
@RequestMapping(value = "/datacenter")
public class DataCenterController extends BaseController {

	@Resource
	private DataStatisticsMapper dataStatisticsMapper;
	@Resource
	private UserService userService;

	@Resource
	private WebQuestionnaireResultService webQuestionnaireResultService;

	@Resource
	private WebPjService webPjService;

	@Resource
	private SysDictService sysDictService;
	
	@Resource
	private SysSchoolService sysSchoolService;
	
	@Resource
	private String fileWebPath;
	
	@Resource
	private String platformLevel;
	@Resource
	private String platformLevelId;

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index() {
		return "/web/datacenter/datacenter_index";
	}

	/**
	 * 数据中心-数据
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/dataCenterDetail")
	public String dataCenterDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
		// int state = userRole();
		// /*
		// * 根据登录的角色来选择进入到那个页面 1.教研员 2.老师 3.领导
		// */
		// if (state == 1) {
		//
		// return "/web/datacenter/datacenter_detail_staff";
		// } else if (state == 2) {
		//
		// return "/web/datacenter/datacenter_detail_teacher";
		// } else if(state == 3 ){
		//
		// return "/web/datacenter/datacenter_detail_admin";
		// }

		return "/web/datacenter/dataCenterDetail";
	}
	
	/**
	 * 教师贡献榜
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacheContributionList")
	public String teacheContributionList(HttpServletRequest request, HttpServletResponse response, Model model) {
		int state = userRole();
		/*
		 * 根据登录的角色来选择进入到那个页面 1.教研员 2.老师 3.领导
		 */

		String userId = "";
		if (isLogined()) {
			userId = getUserId();
			User u = userService.findByKey(userId);
			model.addAttribute("userName", u.getCurrName() == null ? u.getNickName() : u.getCurrName());
			if (state == 1) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("userId", userId);
				paramMap.put("standardDay", 0);
				paramMap.put("standardWeek", 0);
				paramMap.put("standardMonth", 0);
				paramMap.put("standardSemester", 0);

				paramMap.put("weekscount1", 0);
				paramMap.put("weekscount2", 0);
				paramMap.put("weekscount3", 0);
				paramMap.put("weekscount4", 0);
				paramMap.put("weekscount5", 0);

				paramMap.put("weekc1", 0);
				paramMap.put("weekc2", 0);
				paramMap.put("weekc3", 0);
				paramMap.put("weekc4", 0);

				paramMap.put("monthc1", 0);
				paramMap.put("monthc2", 0);
				paramMap.put("monthc3", 0);
				paramMap.put("monthc4", 0);

				paramMap.put("firstNum", 0);
				paramMap.put("nextNum", 0);
				HashMap<String, Integer> resultMap = webPjService.getStaffCountContributionEvaluation(paramMap);
				model.addAttribute("resultMap", resultMap);
				return "/web/datacenter/staff_contribution";
			} else if (state == 2) {
				// 本周上课次数
				model.addAttribute("teacherClassTimesOfDay", teacherClassTimesOfDay().toString());

				//
				Map<String, Object> kz = scoreOfLastOnce();
				model.addAttribute("scoreOfLastOnce_xAxis", kz.get("xAxisData").toString());
				model.addAttribute("scoreOfLastOnce_series", kz.get("seriesData").toString());
				//

				// model.addAttribute("scoreOfLastOnce",
				// scoreOfLastOnce().toString());
				model.addAttribute("averageScoreOfThisWeek", averageScoreOfThisWeek().toString());

				model.addAttribute("findTeacherContributionOfWeek", findTeacherContributionOfWeek(null).toString());
				model.addAttribute("findTeacherContributionOfWeek_pg", findTeacherContributionOfWeek("pg").toString());

				model.addAttribute("findTeacherContributionOfMonth", findTeacherContributionOfMonth(null).toString());
				model.addAttribute("findTeacherContributionOfMonth_pg",
						findTeacherContributionOfMonth("pg").toString());

				model.addAttribute("findTeacherContributionOfSemester",
						findTeacherContributionOfSemester(null).toString());
				model.addAttribute("findTeacherContributionOfSemester_pg",
						findTeacherContributionOfSemester("pg").toString());

				return "/web/datacenter/teacher_contribution";
			} else if (state == 3) {//
				Map<String, Object> paramMap = new HashMap<String, Object>();

				String regionId = "";
				String areaId = getUserBak2(); // 区县id
				/* 获取登录用户的信息 */
				String isadmin = getUserBak1(); // 管理员的种类
				
				String province = "";
				String city = "";
				SysDict areaDict = sysDictService.findByKey(areaId);// 区县信息
				
				paramMap = getparameterMap(areaDict,paramMap,province,city,areaId,regionId,isadmin);
				
				// else if (User.bak1_operator.equalsIgnoreCase(isadmin)) {//
				// 运营商
				// // 查看所有的，不要区域
				//
				// }

				paramMap.put("isadmin", isadmin);
				paramMap.put("standardDay", 0);
				paramMap.put("standardWeek", 0);
				paramMap.put("standardMonth", 0);
				paramMap.put("standardSemester", 0);

				paramMap.put("weekscount1", 0);
				paramMap.put("weekscount2", 0);
				paramMap.put("weekscount3", 0);
				paramMap.put("weekscount4", 0);
				paramMap.put("weekscount5", 0);

				paramMap.put("weekc1", 0);
				paramMap.put("weekc2", 0);
				paramMap.put("weekc3", 0);
				paramMap.put("weekc4", 0);

				paramMap.put("monthc1", 0);
				paramMap.put("monthc2", 0);
				paramMap.put("monthc3", 0);
				paramMap.put("monthc4", 0);

				paramMap.put("firstNum", 0);
				paramMap.put("nextNum", 0);

				// 重新写存储过程
				HashMap<String, Integer> resultMap = webPjService.getLeaderCountContributionEvaluation(paramMap);
				model.addAttribute("resultMap", resultMap);
				return "/web/datacenter/leader_contribution";
			}
		}
		return "";
	}

	/**
	 * 本周上课次数
	 * 
	 * @return
	 */
	public List<Object> teacherClassTimesOfDay() {
		String userId = "";
		if (isLogined()) {
			userId = getUserId();
		}
		// 本周上课次数
		List<Map<String, Object>> classTimes = dataStatisticsMapper.findTeacherContributionOfDay(userId);
		List<Object> s = new ArrayList<Object>();
		for (int i = 0; i < 5; i++) {
			s.add(0);
		}
		for (Map<String, Object> map : classTimes) {
			int index = Integer.parseInt(map.get("weekNum").toString());
			s.set(index - 1, map.get("classtimes"));
		}

		return s;
	}

	/**
	 * 最几周上课和评估次数近
	 * 
	 * @return
	 */
	public List<Object> findTeacherContributionOfWeek(String pg) {
		String userId = "";
		if (isLogined()) {
			userId = getUserId();
		}

		List<Object> s = new ArrayList<Object>();
		for (int i = 0; i < 5; i++) {
			s.add(0);
		}

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("userId", userId);
		if (pg != null) {
			m.put("isPg", "ture");
		}
		int j = 0;
		for (int i = 4; i >= 0; i--) {
			m.put("week", i);
			List<Map<String, Object>> dataList = dataStatisticsMapper.findTeacherContributionOfWeek(m);
			if (dataList != null && dataList.size() > 0) {
				dataList.get(0).get("classtimes");
				s.set(j, dataList.get(0).get("classtimes"));
			}
			j++;
		}

		return s;
	}

	/**
	 * 最近几月上课和评估次数
	 * 
	 * @return
	 */
	public List<Object> findTeacherContributionOfMonth(String pg) {
		String userId = "";
		if (isLogined()) {
			userId = getUserId();
		}

		List<Object> s = new ArrayList<Object>();
		for (int i = 0; i < 4; i++) {
			s.add(0);
		}

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("userId", userId);
		if (pg != null) {
			m.put("isPg", "ture");
		}
		int j = 0;
		for (int i = 3; i >= 0; i--) {
			m.put("month", i);
			List<Map<String, Object>> dataList = dataStatisticsMapper.findTeacherContributionOfMonth(m);
			if (dataList != null && dataList.size() > 0) {
				dataList.get(0).get("classtimes");
				s.set(j, dataList.get(0).get("classtimes"));
			}
			j++;
		}

		return s;
	}

	/**
	 * 学期上课和评估次数
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<Object> findTeacherContributionOfSemester(String pg) {
		String userId = "";
		if (isLogined()) {
			userId = getUserId();
		}

		List<Object> s = new ArrayList<Object>();
		for (int i = 0; i < 2; i++) {
			s.add(0);
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("userId", userId);
		if (pg != null) {
			m.put("isPg", "ture");
		}

		int month = new Date().getMonth() + 1;
		/**
		 * 本学期
		 */
		if (month >= 8) {// 第一学期[8,12]
			m.put("pama", 0);
			m.put("month", month - 8);
		} else if (month < 2) {// 第一学期[1,2)
			m.put("pama", 1);
			m.put("month", 5);
			m.put("month_", -1);
		} else {// 第二学期[2,8)
			m.put("pama", 1);
			m.put("month", month - 2);
			m.put("month_", month - 8);
		}
		List<Map<String, Object>> dataList = dataStatisticsMapper.findTeacherContributionOfSemester(m);
		if (dataList != null && dataList.size() > 0) {
			dataList.get(0).get("classtimes");
			s.set(1, dataList.get(0).get("classtimes"));
		}
		/**
		 * 上学期
		 */
		if (month >= 8) {// 第一学期[8,12]
			m.put("pama", 1);
			m.put("month", month - 2);
			m.put("month_", month - 8);
		} else if (month < 2) {// 第一学期[1,2)
			m.put("pama", 1);
			m.put("month", 11);
			m.put("month_", 5);
		} else {// 第二学期[2,8)
			m.put("pama", 1);
			m.put("month", month + 4);
			m.put("month_", month - 2);
		}
		List<Map<String, Object>> dataList2 = dataStatisticsMapper.findTeacherContributionOfSemester(m);
		if (dataList2 != null && dataList2.size() > 0) {
			dataList2.get(0).get("classtimes");
			s.set(0, dataList2.get(0).get("classtimes"));
		}

		return s;
	}

	/**
	 * 本节课-最近上课得分
	 * 
	 * @return
	 */
	public Map<String, Object> scoreOfLastOnce() {
		String userId = "";
		if (isLogined()) {
			userId = getUserId();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		List<Object> series = new ArrayList<Object>();
		List<Object> data = new ArrayList<Object>();
		Map<String, Object> seriesMap = new HashMap<String, Object>();

		Map<String, Object> xAxis = new HashMap<String, Object>();
		List<Object> datalist = new ArrayList<Object>();

		List<Map<String, Object>> dataList = dataStatisticsMapper.scoreOfLastOnce(userId);
		if (dataList != null && dataList.size() == 1) {
			String id = dataList.get(0).get("id").toString();// 最近一节课程资源id
			List<Map<String, Object>> list = dataStatisticsMapper.scoreOfLastOnceKeqian(id);
			for (Map<String, Object> map : list) {
				data.add(map.get("score"));
				datalist.add(map.get("title"));
			}

		}
		xAxis.put("name", "类型");
		xAxis.put("data", datalist);

		seriesMap.put("type", "bar");
		seriesMap.put("data", data);
		series.add(JsonUtil.mapJSON(seriesMap));

		result.put("xAxisData", JsonUtil.mapJSON(xAxis));
		result.put("seriesData", series);

		return result;
	}

	/**
	 * 本周平均分
	 * 
	 * @return
	 */
	public List<Object> averageScoreOfThisWeek() {
		String userId = "";
		if (isLogined()) {
			userId = getUserId();
		}

		List<Object> s = new ArrayList<Object>();
		for (int i = 0; i < 5; i++) {
			s.add(null);
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("userId", userId);
		int w = DateUtil.getIntWeekDay() > 5 ? 5 : DateUtil.getIntWeekDay();
		for (int i = 0; i < w; i++) {
			m.put("week", i);
			List<Map<String, Object>> dataList = dataStatisticsMapper.averageScoreOfThisWeek(m);
			if (dataList != null && dataList.size() > 0) {

				s.set(i, dataList.get(0).get("q"));
			}
		}

		return s;
	}

	/**
	 * 教师排行榜
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacherRankings")
	public String teacherRankings(HttpServletRequest request, HttpServletResponse response, Model model) {

		int state = userRole();
		/*
		 * 根据登录的角色来选择进入到那个页面 1.教研员 2.老师
		 */
		if (state == 1) {

			return "/web/datacenter/staff_ranking";
		} else if (state == 2) {

			String userId = "";
			String schoolId = "";
			String userName = "";
			if (isLogined()) {
				userId = getUserId();
				User u = userService.findByKey(userId);
				schoolId = u.getSchoolId();
				userName = u.getCurrName() == null ? u.getNickName() : u.getCurrName();
			}

			model.addAttribute("rankingAverageScoreOfKq_1", rankingAverageScoreOfKq("1", schoolId).toString());
			model.addAttribute("rankingAverageScoreOfKq_2", rankingAverageScoreOfKq("2", schoolId).toString());
			model.addAttribute("rankingAverageScoreOfKq_3", rankingAverageScoreOfKq("3", schoolId).toString());
			model.addAttribute("rankingAverageScoreOfKq_4", rankingAverageScoreOfKq("4", schoolId).toString());
			model.addAttribute("rankingAverageScoreOfKq_5", rankingAverageScoreOfKq("5", schoolId).toString());
			model.addAttribute("rankingAverageScoreOfKq_6", rankingAverageScoreOfKq("6", schoolId).toString());

			model.addAttribute("rankingAverageScoreOfKz_1", rankingAverageScoreOfKz("1", schoolId).toString());
			model.addAttribute("rankingAverageScoreOfKz_2", rankingAverageScoreOfKz("2", schoolId).toString());
			model.addAttribute("rankingAverageScoreOfKz_3", rankingAverageScoreOfKz("3", schoolId).toString());
			model.addAttribute("rankingAverageScoreOfKz_4", rankingAverageScoreOfKz("4", schoolId).toString());
			model.addAttribute("rankingAverageScoreOfKz_5", rankingAverageScoreOfKz("5", schoolId).toString());
			model.addAttribute("rankingAverageScoreOfKz_6", rankingAverageScoreOfKz("6", schoolId).toString());

			Map<String, Object> kz = KzScoreFormTheSameSchoolTeacher(userId, userName);
			model.addAttribute("series", kz.get("series").toString());
			model.addAttribute("legend", kz.get("teacherNames").toString());

			Map<String, Object> dwos = differentWaysOnScoring(userId, userName);
			model.addAttribute("series_d", dwos.get("series").toString());
			model.addAttribute("yAxis", dwos.get("yAxis").toString());

			Map<String, Object> tws = thisWeekZhScore(userId, userName);
			model.addAttribute("twsData", tws.get("score").toString());
			model.addAttribute("xAxis", tws.get("xAxis").toString());

			return "/web/datacenter/teacher_ranking";
		} else if (state == 3) {

			return "/web/datacenter/leader_ranking";
		}
		return "";
	}

	/**
	 * 本周课程教师综合得分结果对比
	 * 
	 * @param userId
	 * @param userName
	 * @return
	 */
	public Map<String, Object> thisWeekZhScore(String userId, String userName) {
		Map<String, Object> ret = new HashMap<String, Object>();
		List<Object> datalist = new ArrayList<Object>();

		Map<String, Object> xAxis = new HashMap<String, Object>();
		xAxis.put("name", "教师");
		List<Object> xAxisData = new ArrayList<Object>();
		xAxisData.add(userName);

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("teacherId", userId);
		m.put("yearweek", "yearweek");
		// 获得最近被评课程科目
		List<Map<String, Object>> dataList = dataStatisticsMapper.lastZxPjTeacherInfo(m);
		String subjectName = null;
		String schoolId = null;
		if (dataList != null && dataList.size() > 0) {
			schoolId = (String) dataList.get(0).get("schoolId");
			subjectName = (String) dataList.get(0).get("subjectName");
			m.put("schoolId", schoolId);
			m.put("subjectName", subjectName);
		}

		// 当前登录用户综合得分
		List<Map<String, Object>> theUserData = dataStatisticsMapper.thisWeekZhScore(m);
		if (theUserData != null && theUserData.size() > 0) {
			datalist.add(theUserData.get(0) == null ? 0 : theUserData.get(0).get("zhscore"));
		}
		// 其余相同科目老师综合得分
		List<Map<String, Object>> dataList2 = dataStatisticsMapper.thisWeekZhScoreTeachers(m);
		for (Map<String, Object> map : dataList2) {
			m.put("teacherId", map.get("userId"));
			List<Map<String, Object>> data = dataStatisticsMapper.thisWeekZhScore(m);
			if (data != null && data.size() > 0) {
				datalist.add(data.get(0).get("zhscore"));
			}
			xAxisData.add(map.get("userName"));
		}

		xAxis.put("data", xAxisData);
		ret.put("score", datalist);
		ret.put("xAxis", JsonUtil.mapJSON(xAxis));

		return ret;
	}

	/**
	 * 不同活跃度方式对教师课中评估得分的影响 / 教师
	 */
	public Map<String, Object> differentWaysOnScoring(String userId, String userName) {
		Map<String, Object> yAxis = new HashMap<String, Object>();

		List<String> teacherNames = new ArrayList<String>();
		teacherNames.add(userName);

		List<Object> series = new ArrayList<Object>();
		List<Object> seriesData1 = new ArrayList<Object>();
		List<Object> seriesData2 = new ArrayList<Object>();

		String subjectName = null;
		String schoolId = null;
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("teacherId", userId);
		List<Map<String, Object>> dataList = dataStatisticsMapper.lastZxPjTeacherInfo(m);

		if (dataList != null && dataList.size() > 0) {
			schoolId = (String) dataList.get(0).get("schoolId");
			subjectName = (String) dataList.get(0).get("subjectName");
			String lId = (String) dataList.get(0).get("lId");
			m.put("lId", lId);
			List<Map<String, Object>> dataList2 = dataStatisticsMapper.differentWaysOnScoring(m);
			if (dataList2 != null && dataList2.size() > 0) {
				seriesData1.add(dataList2.get(0).get("avgTimelength"));
				seriesData2.add(dataList2.get(0).get("counts"));
			}

		}

		m.put("schoolId", schoolId);
		m.put("subjectName", subjectName);
		List<Map<String, Object>> dataList2 = dataStatisticsMapper.lastZxPjTeacher(m);

		for (Map<String, Object> map : dataList2) {
			teacherNames.add(map.get("userName").toString());

			m.put("lId", map.get("lId"));
			List<Map<String, Object>> data = dataStatisticsMapper.differentWaysOnScoring(m);
			if (data != null && data.size() > 0) {
				seriesData1.add(data.get(0).get("avgTimelength"));
				seriesData2.add(data.get(0).get("counts"));
			}
		}
		yAxis.put("type", "category");
		yAxis.put("data", teacherNames);

		Map<String, Object> seriesMap1 = new HashMap<String, Object>();
		seriesMap1.put("name", "站起来时长");
		seriesMap1.put("type", "bar");
		seriesMap1.put("data", seriesData1);
		Map<String, Object> seriesMap2 = new HashMap<String, Object>();
		seriesMap2.put("name", "站起来次数");
		seriesMap2.put("type", "bar");
		seriesMap2.put("data", seriesData2);
		series.add(JsonUtil.mapJSON(seriesMap1));
		series.add(JsonUtil.mapJSON(seriesMap2));

		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("series", series);
		ret.put("yAxis", JsonUtil.mapJSON(yAxis));
		return ret;
	}

	/**
	 * 不同等级教师的课前备课平均评估得分 / 学校
	 * 
	 * @return
	 */
	public List<Object> rankingAverageScoreOfKq(String grade, String schoolId) {

		List<Object> s = new ArrayList<Object>();
		for (int i = 0; i < 5; i++) {
			s.add(0);
		}

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("schoolId", schoolId);
		int exp = 0;
		int exp_ = 0;
		if ("1".equals(grade)) {
			exp = 0;
			exp_ = getExp(11);
		} else if ("2".equals(grade)) {
			exp = getExp(11);
			exp_ = getExp(31);
		} else if ("3".equals(grade)) {
			exp = getExp(31);
			exp_ = getExp(61);
		} else if ("4".equals(grade)) {
			exp = getExp(61);
			exp_ = getExp(81);
		} else if ("5".equals(grade)) {
			exp = getExp(81);
			exp_ = getExp(91);
		} else if ("6".equals(grade)) {
			exp = getExp(91);
			exp_ = getExp(101);
		}
		m.put("exp", exp);
		m.put("exp_", exp_);

		for (int i = 0; i < 5; i++) {
			m.put("week", i);
			List<Map<String, Object>> dataList = dataStatisticsMapper.rankingAverageScoreOfKq(m);
			if (dataList != null && dataList.size() > 0) {
				s.set(i, dataList.get(0).get("score"));
			}
		}

		return s;
	}

	/**
	 * 不同等级教师的课中评估得分 / 学校
	 * 
	 * @return
	 */
	public List<Object> rankingAverageScoreOfKz(String grade, String schoolId) {

		List<Object> s = new ArrayList<Object>();
		for (int i = 0; i < 5; i++) {
			s.add(0);
		}

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("schoolId", schoolId);
		int exp = 0;
		int exp_ = 0;
		if ("1".equals(grade)) {
			exp = 0;
			exp_ = getExp(11);
		} else if ("2".equals(grade)) {
			exp = getExp(11);
			exp_ = getExp(31);
		} else if ("3".equals(grade)) {
			exp = getExp(31);
			exp_ = getExp(61);
		} else if ("4".equals(grade)) {
			exp = getExp(61);
			exp_ = getExp(81);
		} else if ("5".equals(grade)) {
			exp = getExp(81);
			exp_ = getExp(91);
		} else if ("6".equals(grade)) {
			exp = getExp(91);
			exp_ = getExp(101);
		}
		m.put("exp", exp);
		m.put("exp_", exp_);

		for (int i = 0; i < 5; i++) {
			m.put("week", i);
			List<Map<String, Object>> dataList = dataStatisticsMapper.rankingAverageScoreOfKz(m);
			if (dataList != null && dataList.size() > 0) {
				s.set(i, dataList.get(0).get("score"));
			}
		}

		return s;
	}

	/**
	 * 同一学校不同教师课中评估得分对比 / 周
	 * 
	 * @return
	 */
	public Map<String, Object> KzScoreFormTheSameSchoolTeacher(String userId, String userName) {
		Map<String, Object> ret = new HashMap<String, Object>();

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("teacherId", userId);

		String subjectName = null;
		String schoolId = null;
		for (int i = 0; i < 7; i++) {
			m.put("week", i);
			List<Map<String, Object>> dataList = dataStatisticsMapper.KzScoreFormTheSameSchoolTeacher(m);
			if (dataList != null && dataList.size() > 0) {
				// 获得最近七周最近被评课目
				schoolId = dataList.get(0).get("schoolId").toString();
				subjectName = dataList.get(0) == null ? null : dataList.get(0).get("subjectName").toString();
				m.put("subjectName", subjectName);
				m.put("schoolId", schoolId);
				break;
			}
		}
		List<String> teacherNames = new ArrayList<String>();
		List<Object> series = new ArrayList<Object>();

		// 获得7位老师
		List<Map<String, Object>> teachers = dataStatisticsMapper.lastPjTeacher(m);

		for (Map<String, Object> map : teachers) {
			teacherNames.add(map.get("userName") + "");

			List<Object> data = new ArrayList<Object>();

			for (int i = 0; i < 8; i++) {
				data.add(0);
			}
			
			m.put("teacherId", map.get("id"));
			for (int i = 0; i < 8; i++) {
				m.put("week", i);
				List<Map<String, Object>> dataList = dataStatisticsMapper.KzScoreFormTheSameSchoolTeacher(m);
				if (dataList != null && dataList.size() > 0) {
					data.set(i,dataList.get(0).get("score"));
				}
			}

			Map<String, Object> seriesMap = new HashMap<String, Object>();
			seriesMap.put("name", map.get("userName"));
			seriesMap.put("type", "bar");
			seriesMap.put("data", data);
			series.add(JsonUtil.mapJSON(seriesMap));
		}

		// 登录老师信息
		m.put("teacherId", userId);
		List<Object> data = new ArrayList<Object>();
		for (int i = 0; i < 8; i++) {
			m.put("week", i);
			List<Map<String, Object>> dataList = dataStatisticsMapper.KzScoreFormTheSameSchoolTeacher(m);
			if (dataList != null && dataList.size() > 0) {
				data.add(dataList.get(0).get("score"));
			} else {
				data.add(0);
			}
		}
		Map<String, Object> seriesMap = new HashMap<String, Object>();
		seriesMap.put("name", userName);
		seriesMap.put("type", "bar");
		seriesMap.put("data", data);
		series.add(JsonUtil.mapJSON(seriesMap));
		teacherNames.add(userName);

		ret.put("series", series);

		Map<String, Object> legend = new HashMap<String, Object>();
		legend.put("data", teacherNames);
		legend.put("y", "bottom");
		ret.put("teacherNames", JsonUtil.mapJSON(legend));
		return ret;
	}

	public int getExp(int n) {
		return 15 * (n * n + n + 2);
	}

	/**
	 * 排行榜统计信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacherRankingsData")
	public String teacherRankingsData(HttpServletRequest request, HttpServletResponse response, Model model) {

		int state = userRole();

		String searchType = request.getParameter("searchType");
		if (state == 1) {

			return "/web/datacenter/staff_ranking_" + searchType;
		} else if (state == 2) {

			return "/web/datacenter/teacher_ranking_" + searchType;
		}
		return "";
	}

	/**
	 * 排行榜统计信息（教研员） NO1（课前备课） 该功能包含3张图： 1.本教研员所评 -- 最近一周 - 不同活跃度分析 / 教师 2.本教研员所评
	 * -- 不同等级教师的课前备课评估平均得分 3.本教研员所评 -- 课前备课平均评估得分 / 学校（不同学校）
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacherRankingsDataNo1")
	public String teacherRankingsDataNo1(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 登录者id（即教研员登录到id）
		String userId = getUserId();
		/* 本教研员所评 -- 最近一周 - 不同活跃度分析 / 教师 */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);// 登录者
		paramMap.put("day7", "day7");// 保证其不为空

		List<Map<String, String>> teacherIdList = webPjService.getStaffCommonentAllTeacher(paramMap);
		List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
		/*
		 * 学生回答问题次数 ，学生回答问题时长
		 */
		for (int i = 0; i < teacherIdList.size(); i++) {
			Map<String, Object> tempMap = webPjService
					.getStaffCommonentTeacherCountsAndTimelength(teacherIdList.get(i).get("userId"));
			listMaps.add(tempMap);
		}
		model.addAttribute("listMaps", listMaps);

		/* 本教研员所评 -- 不同等级教师的课前备课评估平均得分 */
		/*
		 * 新手老师：周一 到 周五数据
		 * 
		 */
		Map<String, List<Integer>> resultMap = new TreeMap<String, List<Integer>>();
		
		/*
		 * 周一：新手老师 - 特级教师
		 * EG：周一：新手的平均得分,普通的平均得分,一级的平均得分,二级的平均得分,三级的平均得分,特级的平均得分
		 */
		Map<String, List<Integer>> tempMap = new HashMap<String, List<Integer>>();
		// 周几
		Map<String, Object> hm = DateUtil.getOneWeekDate(new Date(), "yyyy-MM-dd");
		// 周一至周五的数据 共有5组数据
		List<String> weeksDay = new ArrayList<String>();
		weeksDay.add(hm.get("MondayDate").toString());
		weeksDay.add(hm.get("TuesdayDate").toString());
		weeksDay.add(hm.get("WednesdayDate").toString());
		weeksDay.add(hm.get("ThursdayDate").toString());
		weeksDay.add(hm.get("FridayDate").toString());
		
		/*weeksDay.add(hm.get("SaturdayDate").toString());
		weeksDay.add(hm.get("SundayDate").toString());*/

		for (int j = 0; j < weeksDay.size(); j++) {
			int avg1 = 0;// 新手老师的平均值
			int avg2 = 0;// 普通教师的平均值
			int avg3 = 0;// 一级老师的平均值
			int avg4 = 0;// 二级老师的平均值 
			int avg5 = 0;// 三级老师的平均值
			int avg6 = 0;// 特级老师的平均值

			paramMap.put("dates", weeksDay.get(j));
			List<Map<String, Object>> listAVGEXP = webPjService.getStaffCommonentAVGAndEXP(paramMap);// 获取所有老师的课前备课评估平均分以及EXP
			if (listAVGEXP != null && listAVGEXP.size() > 0) {

				List<String> teacherList1 = new ArrayList<>();// 初级教师
				List<String> teacherList2 = new ArrayList<>();// 三级教师
				List<String> teacherList3 = new ArrayList<>();// 二级教师
				List<String> teacherList4 = new ArrayList<>();// 一级教师
				List<String> teacherList5 = new ArrayList<>();// 高级教师
				List<String> teacherList6 = new ArrayList<>();// 特级教师

				for (int i = 0; i < listAVGEXP.size(); i++) {
					Object oneTeacherAvg = listAVGEXP.get(i).get("oneTeacherAvg");
					Object expObject = listAVGEXP.get(i).get("exp");
					if (expObject != null) {
						int exp = Integer.parseInt(expObject.toString());// 获取这个老师的exp的值
						int lev = userService.getUserGrade(exp);// 如何判断是那个 老师
						String gradeGlory = userService.getUserGradeGlory(lev);

						if (oneTeacherAvg != null && (!oneTeacherAvg.toString().equalsIgnoreCase(""))) {
							if (gradeGlory.equalsIgnoreCase("初级教师")) {
								teacherList1.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("三级教师")) {
								teacherList2.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("二级教师")) {
								teacherList3.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("一级教师")) {
								teacherList4.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("高级教师")) {
								teacherList5.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("特级教师")) {
								teacherList6.add(oneTeacherAvg.toString());
							}
						}
					}
				}

				// 求周一的各个等级老师的平均值
				if (teacherList1.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList1.size(); i++) {
						sum += Double.valueOf(teacherList1.get(i));
					}
					avg1 = (int) (sum / teacherList1.size());
				}
				if (teacherList2.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList2.size(); i++) {
						sum += Double.valueOf(teacherList2.get(i));
					}
					avg2 = (int) (sum / teacherList2.size());
				}
				if (teacherList3.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList3.size(); i++) {
						sum += Double.valueOf(teacherList3.get(i));
					}
					avg3 = (int) (sum / teacherList3.size());
				}
				if (teacherList4.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList4.size(); i++) {
						sum += Double.valueOf(teacherList4.get(i));
					}
					avg4 = (int) (sum / teacherList4.size());
				}
				if (teacherList5.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList5.size(); i++) {
						sum += Double.valueOf(teacherList5.get(i));
					}
					avg5 = (int) (sum / teacherList5.size());
				}
				if (teacherList6.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList6.size(); i++) {
						sum += Double.valueOf(teacherList6.get(i));
					}
					avg6 = (int) (sum / teacherList6.size());
				}
			}
			List<Integer> tempList = new ArrayList<>();// 教师
			tempList.add(avg1);
			tempList.add(avg2);
			tempList.add(avg3);
			tempList.add(avg4);
			tempList.add(avg5);
			tempList.add(avg6);
			tempMap.put("周" + (j + 1), tempList);
		}
		
		/*
		 * 将数据格式转换成为前台所需要的格式
		 * 将“周几对应各个等级老师的数据”转换成为“一个等级老师对应周一到周日的数据”
		 * */
		List<Integer> week1 = tempMap.get("周1");
		List<Integer> week2 = tempMap.get("周2");
		List<Integer> week3 = tempMap.get("周3");
		List<Integer> week4 = tempMap.get("周4");
		List<Integer> week5 = tempMap.get("周5");
		
		/*List<Integer> week6 = tempMap.get("周6");
		List<Integer> week7 = tempMap.get("周7");*/
		
		/*6个等级老师对应的数据，故循环6次*/
		for (int k = 0; k < 6; k++) {
			List<Integer> teacher = new ArrayList<>();// 教师
			teacher.add(week1.get(k));
			teacher.add(week2.get(k));
			teacher.add(week3.get(k));
			teacher.add(week4.get(k));
			teacher.add(week5.get(k));
			
			/*teacher.add(week6.get(k));
			teacher.add(week7.get(k));*/
			
			resultMap.put("teacher" + (k + 1), teacher);
		}
		model.addAttribute("resultMap", resultMap);

		/* 本教研员所评 -- 课前备课平均评估得分 / 学校（不同学校） */
		paramMap.put("beginDate", hm.get("MondayDate").toString());
		paramMap.put("endDate", hm.get("FridayDate").toString());
		List<Map<String, Object>> schoolIdNames = webPjService.getStaffCommentSchool(paramMap);// 查询所有的学校

		/*
		 * //测试数据 List<Map<String,Object>> schoolIdNames = new
		 * ArrayList<Map<String,Object>>(); for (int i = 1; i < 4; i++) {
		 * Map<String, Object> test = new HashMap<String, Object>();
		 * test.put("schoolName", "实验"+i+"中"); schoolIdNames.add(test); }
		 */
		model.addAttribute("schoolIdNames", schoolIdNames);

		Map<String, List<Double>> schoolDataMap = new TreeMap<String, List<Double>>();
		for (int j = 0; j < weeksDay.size(); j++) {
			paramMap.put("weekDay" + (j + 1), weeksDay.get(j));
		}
		for (int k = 0; k < schoolIdNames.size(); k++) {// schoolIdNames.size()
			Object schoolId = schoolIdNames.get(k).get("schoolId");
			Object schoolName = schoolIdNames.get(k).get("schoolName");
			paramMap.put("schoolId", schoolId);

			Map<String, Double> tMap = webPjService.getAVGBySchool(paramMap);
			// 对取出的数据进行排序
			List<Double> listAVGSchool = new ArrayList<Double>();

			// 当前天是一周的第几天
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;

			for (int i = 1; i < (dayOfWeek + 1); i++) {
				listAVGSchool.add(tMap.get("weekDay" + i));
			}
			schoolDataMap.put(schoolName.toString(), listAVGSchool);
		}

		model.addAttribute("schoolDataMap", schoolDataMap);

		return "/web/datacenter/staff_ranking_no1";
	}

	/**
	 * 排行榜统计信息（教研员） NO3（课中评估） 该功能包含2张表： 教师排行榜-课中评估 1.本教研员所评 -- 不同等级教师的课中评估平均得分
	 * 2.本教研员所评 -- 不同教师课中评估得分对比 / 周
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacherRankingsDataNo3")
	public String teacherRankingsDataNo3(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 登录者id（即教研员登录到id）
		String userId = getUserId();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);// 登录者
		/* 本教研员所评 -- 不同等级教师的课中评估平均得分 */
		/*
		 * 新手老师：周一 到 周五数据
		 * 
		 */
		Map<String, List<Integer>> resultMap = new TreeMap<String, List<Integer>>();
		/*
		 * 周一：新手老师 - 特级教师
		 * 
		 */
		Map<String, List<Integer>> tempMap = new HashMap<String, List<Integer>>();
		// 周几
		Map<String, Object> hm = DateUtil.getOneWeekDate(new Date(), "yyyy-MM-dd");
		// 周一至周五的数据 共有5组数据
		List<String> weeksDay = new ArrayList<String>();
		weeksDay.add(hm.get("MondayDate").toString());
		weeksDay.add(hm.get("TuesdayDate").toString());
		weeksDay.add(hm.get("WednesdayDate").toString());
		weeksDay.add(hm.get("ThursdayDate").toString());
		weeksDay.add(hm.get("FridayDate").toString());

		for (int j = 0; j < weeksDay.size(); j++) {
			int avg1 = 0;// 新手老师的平均值
			int avg2 = 0;// 普通教师的平均值
			int avg3 = 0;// 一级老师的平均值
			int avg4 = 0;// 二级老师的平均值
			int avg5 = 0;// 三级老师的平均值
			int avg6 = 0;// 特级老师的平均值

			paramMap.put("dates", weeksDay.get(j));
			List<Map<String, Object>> listAVGEXP = webPjService.getStaffCommonentAVGAndEXPMiddle(paramMap);// 获取所有老师的课中评估平均分以及EXP
																											// TODO
			if (listAVGEXP != null && listAVGEXP.size() > 0) {

				List<String> teacherList1 = new ArrayList<>();// 初级教师
				List<String> teacherList2 = new ArrayList<>();// 三级教师
				List<String> teacherList3 = new ArrayList<>();// 二级教师
				List<String> teacherList4 = new ArrayList<>();// 一级教师
				List<String> teacherList5 = new ArrayList<>();// 高级教师
				List<String> teacherList6 = new ArrayList<>();// 特级教师

				for (int i = 0; i < listAVGEXP.size(); i++) {
					Object oneTeacherAvg = listAVGEXP.get(i).get("oneTeacherAvg");
					Object expObject = listAVGEXP.get(i).get("exp");
					if (expObject != null) {
						int exp = Integer.parseInt(expObject.toString());// 获取这个老师的exp的值
						int lev = userService.getUserGrade(exp);// 如何判断是那个 等级
						String gradeGlory = userService.getUserGradeGlory(lev);// 荣誉

						if (oneTeacherAvg != null && (!oneTeacherAvg.toString().equalsIgnoreCase(""))) {
							if (gradeGlory.equalsIgnoreCase("初级教师")) {
								teacherList1.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("三级教师")) {
								teacherList2.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("二级教师")) {
								teacherList3.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("一级教师")) {
								teacherList4.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("高级教师")) {
								teacherList5.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("特级教师")) {
								teacherList6.add(oneTeacherAvg.toString());
							}
						}
					}
				}

				// 求周一的各个等级老师的平均值
				if (teacherList1.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList1.size(); i++) {
						sum += Double.valueOf(teacherList1.get(i));
					}
					avg1 = (int) (sum / teacherList1.size());
				}
				if (teacherList2.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList2.size(); i++) {
						sum += Double.valueOf(teacherList2.get(i));
					}
					avg2 = (int) (sum / teacherList2.size());
				}
				if (teacherList3.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList3.size(); i++) {
						sum += Double.valueOf(teacherList3.get(i));
					}
					avg3 = (int) (sum / teacherList3.size());
				}
				if (teacherList4.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList4.size(); i++) {
						sum += Double.valueOf(teacherList4.get(i));
					}
					avg4 = (int) (sum / teacherList4.size());
				}
				if (teacherList5.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList5.size(); i++) {
						sum += Double.valueOf(teacherList5.get(i));
					}
					avg5 = (int) (sum / teacherList5.size());
				}
				if (teacherList6.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList6.size(); i++) {
						sum += Double.valueOf(teacherList6.get(i));
					}
					avg6 = (int) (sum / teacherList6.size());
				}
			}
			List<Integer> tempList = new ArrayList<>();// 教师
			tempList.add(avg1);
			tempList.add(avg2);
			tempList.add(avg3);
			tempList.add(avg4);
			tempList.add(avg5);
			tempList.add(avg6);
			tempMap.put("周" + (j + 1), tempList);
		}

		List<Integer> week1 = tempMap.get("周1");
		List<Integer> week2 = tempMap.get("周2");
		List<Integer> week3 = tempMap.get("周3");
		List<Integer> week4 = tempMap.get("周4");
		List<Integer> week5 = tempMap.get("周5");

		for (int k = 0; k < 6; k++) {
			List<Integer> teacher = new ArrayList<>();// 教师
			teacher.add(week1.get(k));
			teacher.add(week2.get(k));
			teacher.add(week3.get(k));
			teacher.add(week4.get(k));
			teacher.add(week5.get(k));
			resultMap.put("teacher" + (k + 1), teacher);
		}
		model.addAttribute("resultMap", resultMap);

		/* 本教研员所评 -- 不同教师课中评估得分对比 / 周 */
		/* 查询最近7周改教研员评论的所有老师 */
		paramMap.put("week7", "week7");// 保证其不为空
		List<Map<String, String>> teacherIdList = webPjService.getStaffCommonentAllTeacher(paramMap);
		model.addAttribute("teacherIdList", teacherIdList);
		/*
		 * 测试数据 Map<String,String> ttt = new TreeMap<String,String>();
		 * ttt.put("a3fd085e-9925-11e7-a425-001c25d63ebd", "开发人员测试账号");
		 * teacherIdList.add(ttt); teacherIdList.add(ttt);
		 * teacherIdList.add(ttt); teacherIdList.add(ttt);
		 * teacherIdList.add(ttt);
		 */

		// 获取当前时间是第几周 TODO
		Map<String, List<Integer>> reMap = new TreeMap<String, List<Integer>>();

		for (int k = 0; k < 7; k++) {// 循环7周
			List<Integer> avgScores = new ArrayList<Integer>();
			for (int i = 0; i < teacherIdList.size(); i++) {//
				paramMap.put("k", k);// 标识
				paramMap.put("teacherUserId", teacherIdList.get(i).get("userId"));
				Integer avgScore = webPjService.getStaffCommentAVGInCourse(paramMap);
				if (avgScore == null) {// 为null 设定为0
					avgScore = 0;
				}
				avgScores.add(avgScore);
			}

			if (k != 0) {
				reMap.put("前" + k + "周", avgScores);
			} else {
				reMap.put("本周", avgScores);
			}
		}
		model.addAttribute("reMap", reMap);

		return "/web/datacenter/staff_ranking_no3";
	}

	/**
	 * 排行榜统计信息（教研员） NO5（综合得分） 该功能包含2张表： 1.本教研员所评 -- 本周课程教师综合得分结果对比 2.本教研员所评 --
	 * 本周教师平均得分 / 学校（不同学校）
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacherRankingsDataNo5")
	public String teacherRankingsDataNo5(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 登录者id（即教研员登录到id）
		String userId = getUserId();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);// 登录者
		/* 查询当前周该教研员评论的所有老师 */
		paramMap.put("currentWeek", "currentWeek");// 保证其不为空
		List<Map<String, String>> teacherIdList = webPjService.getStaffCommonentAllTeacher(paramMap);

		Map<String, Object> reMaps = new HashMap<String, Object>();
		Map<String, Object> tempMap = new TreeMap<String, Object>();
		for (int i = 0; i < teacherIdList.size(); i++) {//
			String teacherUserId = teacherIdList.get(i).get("userId");
			String teacherName = teacherIdList.get(i).get("teacherName");
			paramMap.put("teacherUserId", teacherUserId);
			/* 根据教研员评论的老师 查询本周 */
			tempMap = webPjService.getStaffCommentAllTeacherScoreByCurrentWeek(paramMap);

			Object beforeCourse = null;
			Object inCourse = null;
			Object afterCourse = null;
			if (tempMap == null) {
				beforeCourse = 0;
				inCourse = 0;
				afterCourse = 0;
			} else {
				// 计算
				beforeCourse = tempMap.get("beforeCourse");
				inCourse = tempMap.get("inCourse");
				afterCourse = tempMap.get("afterCourse");
			}

			Double beforeCourseValue = 0.0;
			Double inCourseValue = 0.0;
			Double afterCourseValue = 0.0;
			// 赋值
			if (beforeCourse != null) {
				beforeCourseValue = Double.valueOf(beforeCourse.toString());
			}
			if (inCourse != null) {
				inCourseValue = Double.valueOf(inCourse.toString());
			}
			if (afterCourse != null) {
				afterCourseValue = Double.valueOf(afterCourse.toString());
			}

			String teacherGradeBIAScoreScale = CommonConfigUtil.getConf("teacherGradeBIAScoreScale");
			String[] scoreScaleAttr = teacherGradeBIAScoreScale.split(":");
			int beforeScale = Integer.parseInt(scoreScaleAttr[0]);
			int inScale = Integer.parseInt(scoreScaleAttr[1]);
			int afterScale = Integer.parseInt(scoreScaleAttr[2]);

			// 计算 不要小数位
			int tempValue = (int) (beforeCourseValue * beforeScale / 10 + inCourseValue * inScale / 10
					+ afterCourseValue * afterScale / 10);

			reMaps.put(teacherName, tempValue);
		}
		/*
		 * reMaps.put("张老师", "82"); reMaps.put("shao老师", "10");
		 * reMaps.put("bai老师", "57"); reMaps.put("xie老师", "87");
		 * reMaps.put("shi老师", "80");
		 */

		model.addAttribute("reMaps", reMaps);

		/* 本教研员所评 -- 本周教师平均得分 / 学校（不同学校） */
		Map<String, Object> hm = DateUtil.getOneWeekDate(new Date(), "yyyy-MM-dd");// 周几
		// 周一至周五的数据 共有5组数据
		List<String> weeksDay = new ArrayList<String>();
		weeksDay.add(hm.get("MondayDate").toString());
		weeksDay.add(hm.get("TuesdayDate").toString());
		weeksDay.add(hm.get("WednesdayDate").toString());
		weeksDay.add(hm.get("ThursdayDate").toString());
		weeksDay.add(hm.get("FridayDate").toString());
		// 周一至周五教研员评论了多少学校
		paramMap.put("beginDate", hm.get("MondayDate").toString());
		paramMap.put("endDate", hm.get("FridayDate").toString());
		List<Map<String, Object>> schoolIdNames = webPjService.getStaffCommentSchool(paramMap);// 查询所有的学校
		model.addAttribute("schoolIdNames", schoolIdNames);

		// 某个学校周一到周五的数据
		Map<String, List<Integer>> schoolDataMap = new TreeMap<String, List<Integer>>();
		paramMap.put("userId", userId);// 登录者
		for (int k = 0; k < schoolIdNames.size(); k++) {
			Object schoolId = schoolIdNames.get(k).get("schoolId");
			Object schoolName = schoolIdNames.get(k).get("schoolName");
			paramMap.put("schoolId", schoolId);

			List<Integer> listAVGSchool = new ArrayList<Integer>();
			// 一个学校周一的分数
			for (int j = 0; j < weeksDay.size(); j++) {// 周几
				paramMap.put("weekDay", weeksDay.get(j));
				Map<String, Object> tMap = webPjService.getAVGAllBySchool(paramMap);
				Object beforeCourse = null;
				Object inCourse = null;
				Object afterCourse = null;
				if (tMap == null) {
					beforeCourse = 0;
					inCourse = 0;
					afterCourse = 0;
				} else {
					// 计算
					beforeCourse = tMap.get("beforeCourse");
					inCourse = tMap.get("inCourse");
					afterCourse = tMap.get("afterCourse");
				}
				Double beforeCourseValue = 0.0;
				Double inCourseValue = 0.0;
				Double afterCourseValue = 0.0;

				// 赋值
				if (beforeCourse != null) {
					beforeCourseValue = Double.valueOf(beforeCourse.toString());
				}
				if (inCourse != null) {
					inCourseValue = Double.valueOf(inCourse.toString());
				}
				if (afterCourse != null) {
					afterCourseValue = Double.valueOf(afterCourse.toString());
				}

				String teacherGradeBIAScoreScale = CommonConfigUtil.getConf("teacherGradeBIAScoreScale");
				String[] scoreScaleAttr = teacherGradeBIAScoreScale.split(":");
				int beforeScale = Integer.parseInt(scoreScaleAttr[0]);
				int inScale = Integer.parseInt(scoreScaleAttr[1]);
				int afterScale = Integer.parseInt(scoreScaleAttr[2]);

				// 计算 不要小数位
				int tempValue = (int) (beforeCourseValue * beforeScale / 10 + inCourseValue * inScale / 10
						+ afterCourseValue * afterScale / 10);

				// 将计算的结果添加到 listAVGSchool 中
				listAVGSchool.add(tempValue);
			}

			schoolDataMap.put(schoolName.toString(), listAVGSchool);

		}
		model.addAttribute("schoolDataMap", schoolDataMap);
		model.addAttribute("schoolDataMapSize", schoolDataMap.size());

		return "/web/datacenter/staff_ranking_no5";
	}

	/**
	 * 排行榜统计信息（教研员） NO8（教师等级） 该功能包含2张表： 1.本教研员所评 -- 上三个月教师等级上升幅度排行榜 2.教师等级人数占比变化
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacherRankingsDataNo8")
	public String teacherRankingsDataNo8(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 登录者id（即教研员登录到id）
		String userId = getUserId();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);// 登录者
		/* 1.查询该教研员评论的老师 2.该老师在前3个月等级上升幅度（上升幅度大的老师） */
		List<Map<String, Object>> teacherIdList = webPjService.getStaffCommonentTopTenTeacherByBigRise(paramMap);
		// 计算的等级
		for (int i = 0; i < teacherIdList.size(); i++) {
			Object EXP = teacherIdList.get(i).get("EXP");
			Object preThreeMonthValue = teacherIdList.get(i).get("preThreeMonthValue");
			int EXPInt = 0;
			Double preThreeMonthValueInt = 0.0;
			if (EXP != null) {
				if (Integer.parseInt(EXP.toString()) > 0) {
					EXPInt = Integer.parseInt(EXP.toString());
				}
			}
			int levNow = userService.getUserGrade(EXPInt);// 如何判断是那个 等级
			teacherIdList.get(i).put("EXP", levNow);

			// 转换成double，在进行运算；因为在数据库中，两种不同的数据进行了运算，导致preThreeMonthValue的类型带小数点
			if (preThreeMonthValue != null) {
				if (Double.valueOf(preThreeMonthValue.toString()) > 0) {
					preThreeMonthValueInt = Double.valueOf(preThreeMonthValue.toString());
				}
			}
			int levPre = userService.getUserGrade(preThreeMonthValueInt.intValue());// 如何判断是那个
																					// 等级
			teacherIdList.get(i).put("preThreeMonthValue", levPre);
		}
		model.addAttribute("teacherIdList", teacherIdList);

		/*
		 * 判断当前时间是在哪个学期 03.01-09.01 上学期是相对于本学期而言的 以时间点来计算：
		 * 假如今天是09.29，上学期的计算点是09.01 假如今天是08.01，上学期的计算点是03.01
		 * 
		 * 本学期是什么时间 上学期是什么时间
		 */
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate); // setTime():使用给定的Date设置此 Calendar 的时间
		int year = c.get(Calendar.YEAR);
		String preSection = year + "-03-01";
		String nextSection = year + "-09-01";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = sdf.parse(preSection);
			Date endDate = sdf.parse(nextSection);
			Calendar pre = Calendar.getInstance();
			Calendar next = Calendar.getInstance();
			pre.setTime(startDate);
			next.setTime(endDate);
			/*
			 * c < pre 返回-1 c = pre 返回0 c > pre 返回1
			 */
			int state = c.compareTo(pre);
			/*
			 * 小于3月份 EG：以2017年为例 本学期是2016-09-01 至 2017-03-01 时间节点是 2017-03-01
			 * 上学期是2016-03-01 至2016-09-01 时间节点是 2016-09-01
			 */
			if (state == -1 || state == 0) {
				// 上学期
				String nowSection = (year - 1) + "-09-01";
				List<Integer> preEXPs = userService.selectEXPs(nowSection);
				// 根据成长值获得相关的等级对应的数据
				Map<String, Integer> preResultMap = caculateTeacherRank(preEXPs);
				model.addAttribute("preResultMap", preResultMap);
				// 本学期
				List<Integer> nowEXPs = userService.selectEXPs(preSection);
				Map<String, Integer> nowResultMap = caculateTeacherRank(nowEXPs);
				model.addAttribute("nowResultMap", nowResultMap);
			} else if (state == 1) {// 大于2017-03-01
				int state2 = c.compareTo(next);
				if (state2 == -1 || state2 == 0) {// 小于等于 本学期 2017-09-01
													// 上学期2017-03-01
					// 上学期
					List<Integer> preEXPs = userService.selectEXPs(preSection);
					Map<String, Integer> preResultMap = caculateTeacherRank(preEXPs);
					model.addAttribute("preResultMap", preResultMap);
					// 本学期
					List<Integer> nowEXPs = userService.selectEXPs(nextSection);
					Map<String, Integer> nowResultMap = caculateTeacherRank(nowEXPs);
					model.addAttribute("nowResultMap", nowResultMap);
				} else if (state2 == 1) {// 大于 本学期 2018-03-01 上学期2017-09-01
					// 上学期
					List<Integer> preEXPs = userService.selectEXPs(nextSection);
					Map<String, Integer> preResultMap = caculateTeacherRank(preEXPs);
					model.addAttribute("preResultMap", preResultMap);
					// 本学期
					String nowSection = (year + 1) + "-03-01";
					List<Integer> nowEXPs = userService.selectEXPs(nowSection);
					Map<String, Integer> nowResultMap = caculateTeacherRank(nowEXPs);
					model.addAttribute("nowResultMap", nowResultMap);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "/web/datacenter/staff_ranking_no8";
	}

	public Map<String, Integer> caculateTeacherRank(List<Integer> EXPs) {
		// 放到外面防止数据为空的情况发生
		Map<String, Integer> resultMap = new TreeMap<String, Integer>();
		int juniorTeacher = 0;
		int threeTeacher = 0;
		int twoTeacher = 0;
		int oneTeacher = 0;
		int highTeacher = 0;
		int superfineTeacher = 0;

		if (EXPs != null && EXPs.size() > 0) {
			for (int i = 0; i < EXPs.size(); i++) {
				int lev = userService.getUserGrade(EXPs.get(i));// 如何判断是那个 等级
				String gradeGlory = userService.getUserGradeGlory(lev);
				if ("初级教师".equalsIgnoreCase(gradeGlory)) {
					juniorTeacher++;
				} else if ("三级教师".equalsIgnoreCase(gradeGlory)) {
					threeTeacher++;
				} else if ("二级教师".equalsIgnoreCase(gradeGlory)) {
					twoTeacher++;
				} else if ("一级教师".equalsIgnoreCase(gradeGlory)) {
					oneTeacher++;
				} else if ("高级教师".equalsIgnoreCase(gradeGlory)) {
					highTeacher++;
				} else if ("特级教师".equalsIgnoreCase(gradeGlory)) {
					superfineTeacher++;
				}
			}
		}
		resultMap.put("初级教师", juniorTeacher);
		resultMap.put("三级教师", threeTeacher);
		resultMap.put("二级教师", twoTeacher);
		resultMap.put("一级教师", oneTeacher);
		resultMap.put("高级教师", highTeacher);
		resultMap.put("特级教师", superfineTeacher);

		return resultMap;
	}

	/**
	 * 今年省份之内各个地方教师使用平台人数 NO9（区域划分） 该功能包含1张表： 1.今年教师使用平台人数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacherRankingsDataNo9")
	public String teacherRankingsDataNo9(HttpServletRequest request, HttpServletResponse response, Model model) {
		/* 暂时不用改方法 */
		Map<String, Object> paramMap = new TreeMap<String, Object>();
		List<Map<String, Object>> resultMapList = userService.countTeacherUsedThisYear(paramMap);
		model.addAttribute("resultMapList", resultMapList);

		return "/web/datacenter/staff_ranking_no9";
	}

	/**
	 * 历年使用平台人数的变化 NO10（区域划分） 该功能包含1张表： 1.今年教师使用平台人数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacherRankingsDataNo10")
	public String teacherRankingsDataNo10(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> resultMap = userService.countOverYearsUsed();

		/*
		 * Map<String,Object> resultMap = new TreeMap<String,Object>();
		 * resultMap.put("pre3Up", 100); resultMap.put("pre3Down", 200);
		 * resultMap.put("pre2Up", 300); resultMap.put("pre2Down", 400);
		 * resultMap.put("pre1Up", 500); resultMap.put("pre1Down", 600);
		 */

		model.addAttribute("resultMap", resultMap);
		return "/web/datacenter/staff_ranking_no10";
	}

	/**
	 * 调查问卷
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/questionnaire")
	public String questionnaire(HttpServletRequest request, HttpServletResponse response, Model model) {
		int state = userRole();
		/*
		 * 根据登录的角色来选择进入到那个页面 1.教研员 2.老师
		 */
		model.addAttribute("role", state);
		return "/web/datacenter/wj_questionnaire";

	}

	@RequestMapping(value = "/wj_questionnaire")
	public String wj_questionnaire(HttpServletRequest request, HttpServletResponse response, Model model) {
		int state = userRole();
		/*
		 * 根据登录的角色来选择进入到那个页面 1.教研员 2.老师
		 */
		String searchType = request.getParameter("searchType");
		model.addAttribute("role", state);

		if ("wenjuan".equals(searchType)) {
			List<Map<String, Object>> questionnaire = dataStatisticsMapper.findQuestionnaire(null);
			List<Map<String, Object>> question = null;
			String id = "";
			String title = "";
			String remarks = "";
			if (questionnaire != null && questionnaire.size() > 0) {
				id = (String) questionnaire.get(0).get("id");
				title = (String) questionnaire.get(0).get("title");
				remarks = (String) questionnaire.get(0).get("remarks");
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("qId", id);
				question = dataStatisticsMapper.findQuestionnaireQuestion(m);
				for (Map<String, Object> map : question) {
					// m.put("qId", map.get("id"));
					m.put("lId", map.get("lid"));
					List<Map<String, Object>> option = dataStatisticsMapper.findQuestionnaireOption(m);
					map.put("options", option);
				}
			}
			model.addAttribute("id", id);
			model.addAttribute("title", title);
			model.addAttribute("remarks", remarks);
			model.addAttribute("questions", question);
		} else if ("survey".equals(searchType)) {
			List<Map<String, Object>> questionnaire = dataStatisticsMapper.findQuestionnaire(null);
			List<Map<String, Object>> question = null;
			String id = "";
			String title = "";
			String remarks = "";
			if (questionnaire != null && questionnaire.size() > 0) {
				id = (String) questionnaire.get(0).get("id");
				title = (String) questionnaire.get(0).get("title");
				remarks = (String) questionnaire.get(0).get("remarks");
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("qId", id);
				question = dataStatisticsMapper.findQuestionnaireQuestion(m);
				for (Map<String, Object> map : question) {
					m.put("qqId", id);
					m.put("qId", map.get("id"));
					m.put("method", 1);
					List<Map<String, Object>> survey = dataStatisticsMapper.findQuestionnaireSurvey(m);
					map.put("survey", survey);

					m.put("method", 2);
					List<Map<String, Object>> anwernum = dataStatisticsMapper.findQuestionnaireSurvey(m);// 调查人数
					map.put("anwernum", anwernum != null && anwernum.size() > 0 ? anwernum.get(0).get("num") : 0);
				}
			}
			model.addAttribute("id", id);
			model.addAttribute("title", title);
			model.addAttribute("remarks", remarks);
			model.addAttribute("questions", question);
		} else {
			return "/web/datacenter/wj_questionnaire";
		}

		return "/web/datacenter/wj_questionnaire_" + searchType;

	}

	@RequestMapping("/addQuestionnaireResult")
	public void addQuestionnaireResult(HttpServletRequest req, HttpServletResponse response,
			WebQuestionnaireResult webQuestionnaireResult) {
		try {

			if (isLogined()) {
				String userId = getUserId();
				String qId = req.getParameter("qId");
				String ids = req.getParameter("ids");
				String opinion = req.getParameter("opinion");

				if (StringUtils.isNotEmpty(ids)) {
					WebQuestionnaireResult wr = new WebQuestionnaireResult();
					wr.setUserId(userId);
					wr.setBak(qId);
					List<WebQuestionnaireResult> res = webQuestionnaireResultService.findSelective(wr);
					for (WebQuestionnaireResult webQuestionnaireResult2 : res) {
						webQuestionnaireResultService.deleteByKey(webQuestionnaireResult2.getId());
					}

					String[] idss = ids.split(";");
					for (String id : idss) {

						WebQuestionnaireResult w = new WebQuestionnaireResult();
						w.setUserId(userId);
						w.setOptionId(id);
						// w.setOpinion(opinion);
						w.setCreateTime(new Date());
						w.setType("A");
						w.setBak(qId);
						webQuestionnaireResultService.insert(w);
					}
				}
				if (StringUtils.isNotBlank(opinion)) {
					WebQuestionnaireResult optinion = new WebQuestionnaireResult();
					optinion.setBak(qId);
					optinion.setUserId(userId);
					optinion.setOpinion(opinion);
					optinion.setCreateTime(new Date());
					optinion.setType("B");
					webQuestionnaireResultService.insert(optinion);
				}

				WriterUtils.toHtml(response, MessageUtils.SUCCESS);
			} else {
				WriterUtils.toHtml(response, "请登录");
			}
		} catch (Exception e) {

			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);

		}
	}

	public int userRole() {

		if (isLogined()) {
			String userType = getUserType();
			if (User.userType_teachers.equals(userType)) {
				return 2;
			} else if (User.userType_research.equals(userType)) {
				return 1;
			} else if (User.userType_leader.equals(userType)) {// 是领导角色
				return 3;
			}
		}
		return -1;
	}

	/**
	 * 下载
	 * 
	 * @param id
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/downloadWord")
	public void downloadCourRes(HttpServletRequest request, HttpServletResponse response, String type)
			throws UnsupportedEncodingException {
		CreateFile cf = new CreateFile();
		ServletContext webcontext = request.getServletContext();
		if ("wenjuan".equals(type)) {
			List<Map<String, Object>> questionnaire = dataStatisticsMapper.findQuestionnaire(null);
			List<Map<String, Object>> question = null;
			String id = "";
			String title = "";
			String remarks = "";

			if (questionnaire != null && questionnaire.size() > 0) {
				id = (String) questionnaire.get(0).get("id");
				title = (String) questionnaire.get(0).get("title");
				remarks = (String) questionnaire.get(0).get("remarks");
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("qId", id);
				question = dataStatisticsMapper.findQuestionnaireQuestion(m);
				for (Map<String, Object> map : question) {
					m.put("lId", map.get("lid"));
					List<Map<String, Object>> option = dataStatisticsMapper.findQuestionnaireOption(m);
					map.put("options", option);
				}
			}

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("title", title);
			param.put("remarks", remarks);
			param.put("questions", question);
			try {
				String savePath = request.getSession().getServletContext().getRealPath("/upload");

				// String uploadPath =
				// request.getSession().getServletContext().getRealPath("/upload");
				String fileUrl = cf.createDocFile(param, "survey.ftl", savePath, webcontext);
				downFile("调查问卷.doc", fileUrl, request, response);
				// DownloadUtils.downloadInternet(response, fileUrl,
				// "调查问卷.doc");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("analysis".equals(type)) {
			List<Map<String, Object>> questionnaire = dataStatisticsMapper.findQuestionnaire(null);
			List<Map<String, Object>> question = null;
			String id = "";
			String title = "";
			if (questionnaire != null && questionnaire.size() > 0) {
				id = (String) questionnaire.get(0).get("id");
				title = (String) questionnaire.get(0).get("title");
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("qId", id);
				question = dataStatisticsMapper.findQuestionnaireQuestion(m);
				for (Map<String, Object> map : question) {
					m.put("qqId", id);
					m.put("qId", map.get("id"));
					m.put("method", 1);
					List<Map<String, Object>> survey = dataStatisticsMapper.findQuestionnaireSurvey(m);
					map.put("survey", survey);

					m.put("method", 2);
					List<Map<String, Object>> anwernum = dataStatisticsMapper.findQuestionnaireSurvey(m);// 调查人数
					map.put("anwernum", anwernum != null && anwernum.size() > 0 ? anwernum.get(0).get("num") : 0);
				}
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("title", title);
			param.put("questions", question);
			try {
				String savePath = request.getSession().getServletContext().getRealPath("/upload");
				// String uploadPath =
				// request.getSession().getServletContext().getRealPath("/upload");
				String fileUrl = cf.createDocFile(param, "analysis.ftl", savePath, webcontext);
				downFile("调查结果.doc", fileUrl, request, response);
				// DownloadUtils.downloadInternet(response, fileUrl,
				// "调查问卷.doc");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void downFile(String fileName, String filepath, HttpServletRequest req, HttpServletResponse res) {
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		File oldFile = new File(filepath);
		// String fileName = FileUploadUtil.encodeFileName(req, filename);
		OutputStream outPut = null;
		try {

			// 设置文件的类型：不限制任何类型
			String contentType = fileNameMap.getContentTypeFor(filepath);
			if (contentType == null) {
				contentType = "application/unknown";
			}

			String name = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
			res.reset();
			res.setContentType(contentType);
			res.setHeader("Content-Disposition",
					"attachment;filename=" + new String(name.getBytes("gb2312"), "iso8859-1"));

			// res.setContentType("application/octet-stream;charset=UTF-8");

			// }
			outPut = res.getOutputStream();
			if (oldFile.exists()) {

				FileInputStream fis = null;
				byte[] b = null;
				fis = new FileInputStream(oldFile);
				b = new byte[fis.available()];
				fis.read(b);
				outPut.write(b);
				fis.close();
			}
			outPut.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (outPut != null) {
					outPut.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	/**
	 * 排行榜统计信息（领导） NO1（课前备课） 该功能包含1张图：
	 * 
	 * 1.本教研员所评 -- 不同等级教师的课前备课评估平均得分
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacherLeaderRankingsDataNo1")
	public String teacherLeaderRankingsDataNo1(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 登录者id（即教研员登录到id）
		Map<String, Object> paramMap = new HashMap<String, Object>();

		/* 本教研员所评 -- 不同等级教师的课前备课评估平均得分 */
		/*
		 * 新手老师：周一 到 周五数据
		 * 
		 */
		Map<String, List<Integer>> resultMap = new TreeMap<String, List<Integer>>();
		/*
		 * 周一：新手老师 - 特级教师
		 * 
		 */
		Map<String, List<Integer>> tempMap = new HashMap<String, List<Integer>>();
		// 周几
		Map<String, Object> hm = DateUtil.getOneWeekDate(new Date(), "yyyy-MM-dd");
		// 周一至周五的数据 共有5组数据
		List<String> weeksDay = new ArrayList<String>();
		weeksDay.add(hm.get("MondayDate").toString());
		weeksDay.add(hm.get("TuesdayDate").toString());
		weeksDay.add(hm.get("WednesdayDate").toString());
		weeksDay.add(hm.get("ThursdayDate").toString());
		weeksDay.add(hm.get("FridayDate").toString());

		for (int j = 0; j < weeksDay.size(); j++) {
			int avg1 = 0;// 新手老师的平均值
			int avg2 = 0;// 普通教师的平均值
			int avg3 = 0;// 一级老师的平均值
			int avg4 = 0;// 二级老师的平均值
			int avg5 = 0;// 三级老师的平均值
			int avg6 = 0;// 特级老师的平均值

			paramMap.put("dates", weeksDay.get(j));

			List<Map<String, Object>> listAVGEXP = new ArrayList<Map<String, Object>>();

			String regionId = "";
			String areaId = getUserBak2(); // 区县id
			String province = "";
			String city = "";
			/* 获取登录用户的信息 */
			String isadmin = getUserBak1(); // 管理员的种类
			paramMap.put("isadmin", isadmin);
			
			SysDict areaDict = sysDictService.findByKey(areaId);// 区县信息
			
			paramMap = getparameterMap(areaDict,paramMap,province,city,areaId,regionId,isadmin);
			
			listAVGEXP = webPjService.getLeaderCommonentAVGAndEXP(paramMap);// 获取省级区域所有老师的课前备课评估平均分以及EXP
			
			if (listAVGEXP != null && listAVGEXP.size() > 0) {

				List<String> teacherList1 = new ArrayList<>();// 初级教师
				List<String> teacherList2 = new ArrayList<>();// 三级教师
				List<String> teacherList3 = new ArrayList<>();// 二级教师
				List<String> teacherList4 = new ArrayList<>();// 一级教师
				List<String> teacherList5 = new ArrayList<>();// 高级教师
				List<String> teacherList6 = new ArrayList<>();// 特级教师

				for (int i = 0; i < listAVGEXP.size(); i++) {
					Object oneTeacherAvg = listAVGEXP.get(i).get("oneTeacherAvg");
					Object expObject = listAVGEXP.get(i).get("exp");
					if (expObject != null) {
						int exp = Integer.parseInt(expObject.toString());// 获取这个老师的exp的值
						int lev = userService.getUserGrade(exp);// 如何判断是那个 老师
						String gradeGlory = userService.getUserGradeGlory(lev);

						if (oneTeacherAvg != null && (!oneTeacherAvg.toString().equalsIgnoreCase(""))) {
							if (gradeGlory.equalsIgnoreCase("初级教师")) {
								teacherList1.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("三级教师")) {
								teacherList2.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("二级教师")) {
								teacherList3.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("一级教师")) {
								teacherList4.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("高级教师")) {
								teacherList5.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("特级教师")) {
								teacherList6.add(oneTeacherAvg.toString());
							}
						}
					}
				}

				// 求周一的各个等级老师的平均值
				if (teacherList1.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList1.size(); i++) {
						sum += Double.valueOf(teacherList1.get(i));
					}
					avg1 = (int) (sum / teacherList1.size());
				}
				if (teacherList2.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList2.size(); i++) {
						sum += Double.valueOf(teacherList2.get(i));
					}
					avg2 = (int) (sum / teacherList2.size());
				}
				if (teacherList3.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList3.size(); i++) {
						sum += Double.valueOf(teacherList3.get(i));
					}
					avg3 = (int) (sum / teacherList3.size());
				}
				if (teacherList4.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList4.size(); i++) {
						sum += Double.valueOf(teacherList4.get(i));
					}
					avg4 = (int) (sum / teacherList4.size());
				}
				if (teacherList5.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList5.size(); i++) {
						sum += Double.valueOf(teacherList5.get(i));
					}
					avg5 = (int) (sum / teacherList5.size());
				}
				if (teacherList6.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList6.size(); i++) {
						sum += Double.valueOf(teacherList6.get(i));
					}
					avg6 = (int) (sum / teacherList6.size());
				}
			}
			List<Integer> tempList = new ArrayList<>();// 教师
			tempList.add(avg1);
			tempList.add(avg2);
			tempList.add(avg3);
			tempList.add(avg4);
			tempList.add(avg5);
			tempList.add(avg6);
			tempMap.put("周" + (j + 1), tempList);
		}

		List<Integer> week1 = tempMap.get("周1");
		List<Integer> week2 = tempMap.get("周2");
		List<Integer> week3 = tempMap.get("周3");
		List<Integer> week4 = tempMap.get("周4");
		List<Integer> week5 = tempMap.get("周5");

		for (int k = 0; k < 6; k++) {
			List<Integer> teacher = new ArrayList<>();// 教师
			teacher.add(week1.get(k));
			teacher.add(week2.get(k));
			teacher.add(week3.get(k));
			teacher.add(week4.get(k));
			teacher.add(week5.get(k));
			resultMap.put("teacher" + (k + 1), teacher);
		}
		model.addAttribute("resultMap", resultMap);

		return "/web/datacenter/leader_ranking_no1";
	}

	/**
	 * 排行榜统计信息（领导） NO3（课中评估） 该功能包含1张表： 教师排行榜-课中评估 1.本教研员所评 -- 不同等级教师的课中评估平均得分
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacherLeaderRankingsDataNo3")
	public String teacherLeaderRankingsDataNo3(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 登录者id（即教研员登录到id）
		Map<String, Object> paramMap = new HashMap<String, Object>();
		/* 本教研员所评 -- 不同等级教师的课中评估平均得分 */
		/*
		 * 新手老师：周一 到 周五数据
		 * 
		 */
		Map<String, List<Integer>> resultMap = new TreeMap<String, List<Integer>>();
		/*
		 * 周一：新手老师 - 特级教师
		 * 
		 */
		Map<String, List<Integer>> tempMap = new HashMap<String, List<Integer>>();
		// 周几
		Map<String, Object> hm = DateUtil.getOneWeekDate(new Date(), "yyyy-MM-dd");
		// 周一至周五的数据 共有5组数据
		List<String> weeksDay = new ArrayList<String>();
		weeksDay.add(hm.get("MondayDate").toString());
		weeksDay.add(hm.get("TuesdayDate").toString());
		weeksDay.add(hm.get("WednesdayDate").toString());
		weeksDay.add(hm.get("ThursdayDate").toString());
		weeksDay.add(hm.get("FridayDate").toString());

		for (int j = 0; j < weeksDay.size(); j++) {
			int avg1 = 0;// 新手老师的平均值
			int avg2 = 0;// 普通教师的平均值
			int avg3 = 0;// 一级老师的平均值
			int avg4 = 0;// 二级老师的平均值
			int avg5 = 0;// 三级老师的平均值
			int avg6 = 0;// 特级老师的平均值

			paramMap.put("dates", weeksDay.get(j));

			List<Map<String, Object>> listAVGEXP = new ArrayList<Map<String, Object>>();// 获取所有老师的课中评估平均分以及EXP
			String regionId = "";
			String areaId = getUserBak2(); // 区县id
			String province = "";
			String city = "";
			/* 获取登录用户的信息 */
			String isadmin = getUserBak1(); // 管理员的种类
			paramMap.put("isadmin", isadmin);
			
			SysDict areaDict = sysDictService.findByKey(areaId);// 区县信息
			
			paramMap = getparameterMap(areaDict,paramMap,province,city,areaId,regionId,isadmin);

			listAVGEXP = webPjService.getLeaderCommonentAVGAndEXPMiddle(paramMap);
			
			if (listAVGEXP != null && listAVGEXP.size() > 0) {

				List<String> teacherList1 = new ArrayList<>();// 初级教师
				List<String> teacherList2 = new ArrayList<>();// 三级教师
				List<String> teacherList3 = new ArrayList<>();// 二级教师
				List<String> teacherList4 = new ArrayList<>();// 一级教师
				List<String> teacherList5 = new ArrayList<>();// 高级教师
				List<String> teacherList6 = new ArrayList<>();// 特级教师

				for (int i = 0; i < listAVGEXP.size(); i++) {
					Object oneTeacherAvg = listAVGEXP.get(i).get("oneTeacherAvg");
					Object expObject = listAVGEXP.get(i).get("exp");
					if (expObject != null) {
						int exp = Integer.parseInt(expObject.toString());// 获取这个老师的exp的值
						int lev = userService.getUserGrade(exp);// 如何判断是那个 等级
						String gradeGlory = userService.getUserGradeGlory(lev);// 荣誉

						if (oneTeacherAvg != null && (!oneTeacherAvg.toString().equalsIgnoreCase(""))) {
							if (gradeGlory.equalsIgnoreCase("初级教师")) {
								teacherList1.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("三级教师")) {
								teacherList2.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("二级教师")) {
								teacherList3.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("一级教师")) {
								teacherList4.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("高级教师")) {
								teacherList5.add(oneTeacherAvg.toString());
							} else if (gradeGlory.equalsIgnoreCase("特级教师")) {
								teacherList6.add(oneTeacherAvg.toString());
							}
						}
					}
				}

				// 求周一的各个等级老师的平均值
				if (teacherList1.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList1.size(); i++) {
						sum += Double.valueOf(teacherList1.get(i));
					}
					avg1 = (int) (sum / teacherList1.size());
				}
				if (teacherList2.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList2.size(); i++) {
						sum += Double.valueOf(teacherList2.get(i));
					}
					avg2 = (int) (sum / teacherList2.size());
				}
				if (teacherList3.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList3.size(); i++) {
						sum += Double.valueOf(teacherList3.get(i));
					}
					avg3 = (int) (sum / teacherList3.size());
				}
				if (teacherList4.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList4.size(); i++) {
						sum += Double.valueOf(teacherList4.get(i));
					}
					avg4 = (int) (sum / teacherList4.size());
				}
				if (teacherList5.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList5.size(); i++) {
						sum += Double.valueOf(teacherList5.get(i));
					}
					avg5 = (int) (sum / teacherList5.size());
				}
				if (teacherList6.size() > 0) {
					double sum = 0.0;
					for (int i = 0; i < teacherList6.size(); i++) {
						sum += Double.valueOf(teacherList6.get(i));
					}
					avg6 = (int) (sum / teacherList6.size());
				}
			}
			List<Integer> tempList = new ArrayList<>();// 教师
			tempList.add(avg1);
			tempList.add(avg2);
			tempList.add(avg3);
			tempList.add(avg4);
			tempList.add(avg5);
			tempList.add(avg6);
			tempMap.put("周" + (j + 1), tempList);
		}

		List<Integer> week1 = tempMap.get("周1");
		List<Integer> week2 = tempMap.get("周2");
		List<Integer> week3 = tempMap.get("周3");
		List<Integer> week4 = tempMap.get("周4");
		List<Integer> week5 = tempMap.get("周5");

		for (int k = 0; k < 6; k++) {
			List<Integer> teacher = new ArrayList<>();// 教师
			teacher.add(week1.get(k));
			teacher.add(week2.get(k));
			teacher.add(week3.get(k));
			teacher.add(week4.get(k));
			teacher.add(week5.get(k));
			resultMap.put("teacher" + (k + 1), teacher);
		}
		model.addAttribute("resultMap", resultMap);

		return "/web/datacenter/leader_ranking_no3";
	}

	/**
	 * 排行榜统计信息（领导） NO8（教师等级） 该功能包含1张表： 1.教师等级人数占比变化
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacherLeaderRankingsDataNo8")
	public String teacherLeaderRankingsDataNo8(HttpServletRequest request, HttpServletResponse response, Model model) {
		// 登录者id（即教研员登录到id）
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String regionId = "";
		String areaId = getUserBak2(); // 区县id
		String province = "";
		String city = "";
		/* 获取登录用户的信息 */
		String isadmin = getUserBak1(); // 管理员的种类
		paramMap.put("isadmin", isadmin);
		
		
		SysDict areaDict = sysDictService.findByKey(areaId);// 区县信息
		
		paramMap = getparameterMap(areaDict,paramMap,province,city,areaId,regionId,isadmin);
		
		/*
		 * 判断当前时间是在哪个学期 03.01-09.01 上学期是相对于本学期而言的 以时间点来计算：
		 * 假如今天是09.29，上学期的计算点是09.01 假如今天是08.01，上学期的计算点是03.01
		 * 
		 * 本学期是什么时间 上学期是什么时间
		 */
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(currentDate); // setTime():使用给定的Date设置此 Calendar 的时间
		int year = c.get(Calendar.YEAR);
		String preSection = year + "-03-01";
		String nextSection = year + "-09-01";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = sdf.parse(preSection);
			Date endDate = sdf.parse(nextSection);
			Calendar pre = Calendar.getInstance();
			Calendar next = Calendar.getInstance();
			pre.setTime(startDate);
			next.setTime(endDate);
			/*
			 * c < pre 返回-1 c = pre 返回0 c > pre 返回1
			 */
			int state = c.compareTo(pre);
			/*
			 * 小于3月份 EG：以2017年为例 本学期是2016-09-01 至 2017-03-01 时间节点是 2017-03-01
			 * 上学期是2016-03-01 至2016-09-01 时间节点是 2016-09-01
			 */
			if (state == -1 || state == 0) {
				// 上学期
				String nowSection = (year - 1) + "-09-01";
				paramMap.put("dates", nowSection);
				List<Integer> preEXPs = userService.selectEXPsByLeader(paramMap);// TODO
				// 根据成长值获得相关的等级对应的数据
				Map<String, Integer> preResultMap = caculateTeacherRank(preEXPs);
				model.addAttribute("preResultMap", preResultMap);
				// 本学期
				paramMap.put("dates", preSection);
				List<Integer> nowEXPs = userService.selectEXPsByLeader(paramMap);
				Map<String, Integer> nowResultMap = caculateTeacherRank(nowEXPs);
				model.addAttribute("nowResultMap", nowResultMap);
			} else if (state == 1) {// 大于2017-03-01
				int state2 = c.compareTo(next);
				if (state2 == -1 || state2 == 0) {// 小于等于 本学期 2017-09-01
													// 上学期2017-03-01
					// 上学期
					paramMap.put("dates", preSection);
					List<Integer> preEXPs = userService.selectEXPsByLeader(paramMap);
					Map<String, Integer> preResultMap = caculateTeacherRank(preEXPs);
					model.addAttribute("preResultMap", preResultMap);
					// 本学期
					paramMap.put("dates", nextSection);
					List<Integer> nowEXPs = userService.selectEXPsByLeader(paramMap);
					Map<String, Integer> nowResultMap = caculateTeacherRank(nowEXPs);
					model.addAttribute("nowResultMap", nowResultMap);
				} else if (state2 == 1) {// 大于 本学期 2018-03-01 上学期2017-09-01
					// 上学期
					paramMap.put("dates", nextSection);
					List<Integer> preEXPs = userService.selectEXPsByLeader(paramMap);
					Map<String, Integer> preResultMap = caculateTeacherRank(preEXPs);
					model.addAttribute("preResultMap", preResultMap);
					// 本学期
					String nowSection = (year + 1) + "-03-01";
					paramMap.put("dates", nowSection);
					List<Integer> nowEXPs = userService.selectEXPsByLeader(paramMap);
					Map<String, Integer> nowResultMap = caculateTeacherRank(nowEXPs);
					model.addAttribute("nowResultMap", nowResultMap);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "/web/datacenter/leader_ranking_no8";
	}

	/**
	 * 今年省份之内各个地方教师使用平台人数 NO9（区域划分） 该功能包含1张表： 1.今年教师使用平台人数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacherLeaderRankingsDataNo9")
	public String teacherLeaderRankingsDataNo9(HttpServletRequest request, HttpServletResponse response, Model model) {
		/* 暂时不用改方法 */
		Map<String, Object> paramMap = new TreeMap<String, Object>();
		List<Map<String, Object>> resultMapList = userService.countTeacherUsedThisYear(paramMap);
		model.addAttribute("resultMapList", resultMapList);

		return "/web/datacenter/leader_ranking_no9";
	}

	/**
	 * 历年使用平台人数的变化 NO10（区域划分） 该功能包含1张表： 1.今年教师使用平台人数
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacherLeaderRankingsDataNo10")
	public String teacherLeaderRankingsDataNo10(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> resultMap = userService.countOverYearsUsed();

		/*
		 * Map<String,Object> resultMap = new TreeMap<String,Object>();
		 * resultMap.put("pre3Up", 100); resultMap.put("pre3Down", 200);
		 * resultMap.put("pre2Up", 300); resultMap.put("pre2Down", 400);
		 * resultMap.put("pre1Up", 500); resultMap.put("pre1Down", 600);
		 */

		model.addAttribute("resultMap", resultMap);
		return "/web/datacenter/leader_ranking_no10";
	}
	
	/**
	 * 获得处理过后的map信息   
	 * 可以根据配置文件中的相关信息获取 不同级别平台信息，进而在平台控制相关显示
	 * @param areaDict
	 * @param paramMap
	 * @param province
	 * @param city
	 * @param areaId
	 * @param regionId
	 * @param isadmin
	 * @return
	 */
	private Map<String,Object> getparameterMap(SysDict areaDict,Map<String,Object> paramMap,String province,String city,String areaId,String regionId,String isadmin){
		if(areaDict==null){//说明用户记录中的地区信息是不对的，不能通过这个地区信息来查看
			if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N")) {//当是国家级别时
				//有省、市、县、校管理员
				// 判断有哪些权限     最终是获得区域级别的id
				
				/*
				第一种方式：
				SysDict sysDict1 = new SysDict();
				sysDict1.setValue("河南省");
				List<SysDict> sysDict1L = sysDictService.findSelective(sysDict1);
				province = sysDict1L.get(0).getId();//410000
				
				sysDict1.setValue("郑州市");
				List<SysDict> sysDict2L = sysDictService.findSelective(sysDict1);
				city = sysDict2L.get(0).getId();//410100
				
				sysDict1.setValue("金水区");
				List<SysDict> sysDict3L = sysDictService.findSelective(sysDict1);
				areaId = sysDict3L.get(0).getId();//410105
				
				第二种方式：
				province = "410000";//河南省
				city = "410100";//郑州市
				areaId = "410105";//金水区
				*/
				province = CommonConfigUtil.getConf("province"); 
				city = CommonConfigUtil.getConf("city"); 
				areaId = CommonConfigUtil.getConf("area"); 
				
				if (User.bak1_province.equalsIgnoreCase(isadmin)) {// 省级管理员
					
					paramMap.put("regionId",province);//
				} else if (User.bak1_city.equalsIgnoreCase(isadmin)) {// 市级管理员
					
					paramMap.put("regionId", city);

				} else if (User.bak1_county.equalsIgnoreCase(isadmin) || User.bak1_no.equalsIgnoreCase(isadmin)) {
					// 1.区县级管理员
					// 2.如果没有权限就看区县的（规定）
					paramMap.put("regionId", areaId);
					
				} else if (User.bak1_schoool.equalsIgnoreCase(isadmin)) {// 学校管理员
					SysSchool sysSchool = new SysSchool();
					sysSchool.setCountyId(areaId);
					/*方便统计其它   而查询*/
					List<SysSchool> otherSysSchoolList = sysSchoolService.findSelective(sysSchool);
					
					regionId = otherSysSchoolList.get(0).getId();
					paramMap.put("regionId", regionId);
				} else{//OA  是运营商时 默认给最高级  2018-04-17添加
					paramMap.put("regionId",province);
				}
				
				
			}else if(platformLevel.equals("P")){
				//计算省id
				province = platformLevelId ;
				//有省、市、县、校管理员
				// 判断有哪些权限     最终是获得区域级别的id
				if (User.bak1_province.equalsIgnoreCase(isadmin)) {// 省级管理员
					paramMap.put("regionId", province);//
				} else if (User.bak1_city.equalsIgnoreCase(isadmin)) {// 市级管理员
					SysDict sysDict1 = new SysDict();
					sysDict1.setPid(province);
					List<SysDict> sysDict1C = sysDictService.findSelective(sysDict1);
					city = sysDict1C.get(0).getId();
					
					paramMap.put("regionId", city);

				} else if (User.bak1_county.equalsIgnoreCase(isadmin) || User.bak1_no.equalsIgnoreCase(isadmin)) {
					// 1.区县级管理员
					// 2.如果没有权限就看区县的（规定）
					SysDict sysDict1 = new SysDict();
					sysDict1.setPid(city);
					List<SysDict> sysDict1C = sysDictService.findSelective(sysDict1);
					areaId = sysDict1C.get(0).getId();
					
					paramMap.put("regionId", areaId);
				} else if (User.bak1_schoool.equalsIgnoreCase(isadmin)) {// 学校管理员
					SysSchool sysSchool = new SysSchool();
					sysSchool.setCountyId(areaId);
					/*方便统计其它   而查询*/
					List<SysSchool> otherSysSchoolList = sysSchoolService.findSelective(sysSchool);
					
					regionId = otherSysSchoolList.get(0).getId();
					paramMap.put("regionId", regionId);
				} else{//OA  是运营商时 默认给最高级  2018-04-17添加
					paramMap.put("regionId", province);
				}
				
			}else if(platformLevel.equals("C")){
				//计算市id
				city = platformLevelId;
				//有市、县、校的管理员
				// 判断有哪些权限     最终是获得区域级别的id
				if (User.bak1_city.equalsIgnoreCase(isadmin)) {// 市级管理员
					
					paramMap.put("regionId", city);

				} else if (User.bak1_county.equalsIgnoreCase(isadmin) || User.bak1_no.equalsIgnoreCase(isadmin)) {// 1.区县级管理员
																													// 2.如果没有权限就看区县的（规定）
					// 2.如果没有权限就看区县的（规定）
					SysDict sysDict1 = new SysDict();
					sysDict1.setPid(city);
					List<SysDict> sysDict1C = sysDictService.findSelective(sysDict1);
					areaId = sysDict1C.get(0).getId();
					
					paramMap.put("regionId", areaId);
				} else if (User.bak1_schoool.equalsIgnoreCase(isadmin)) {// 学校管理员
					SysSchool sysSchool = new SysSchool();
					sysSchool.setCountyId(areaId);
					/*方便统计其它   而查询*/
					List<SysSchool> otherSysSchoolList = sysSchoolService.findSelective(sysSchool);
					
					regionId = otherSysSchoolList.get(0).getId();
					paramMap.put("regionId", regionId);
				} else{//OA  是运营商时 默认给最高级   2018-04-17添加
					paramMap.put("regionId", city);
				}
				
			}else if(platformLevel.equals("A")){
				//计算县id
				areaId = platformLevelId;
				//有校管理员
				
				if (User.bak1_county.equalsIgnoreCase(isadmin) || User.bak1_no.equalsIgnoreCase(isadmin)) {// 1.区县级管理员
																													// 2.如果没有权限就看区县的（规定）
					paramMap.put("regionId", areaId);
				} else if (User.bak1_schoool.equalsIgnoreCase(isadmin)) {// 学校管理员
					SysSchool sysSchool = new SysSchool();
					sysSchool.setCountyId(areaId);
					/*方便统计其它   而查询*/
					List<SysSchool> otherSysSchoolList = sysSchoolService.findSelective(sysSchool);
					
					regionId = otherSysSchoolList.get(0).getId();
					paramMap.put("regionId", regionId);
				} else{//OA  是运营商时 默认给最高级   2018-04-17添加
					paramMap.put("regionId", areaId);
				}
				
			}
			
		}else{
			if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N")|| platformLevel.equals("P")) {//当是国家级别 或者省级别时
				
				if (User.bak1_province.equalsIgnoreCase(isadmin)) {// 省级管理员
					
					SysDict cityDict = sysDictService.findByKey(areaDict.getPid());// 区县信息
					paramMap.put("regionId", cityDict.getPid());//
				} else if (User.bak1_city.equalsIgnoreCase(isadmin)) {// 市级管理员
					
					paramMap.put("regionId", areaDict.getPid());

				} else if (User.bak1_county.equalsIgnoreCase(isadmin) || User.bak1_no.equalsIgnoreCase(isadmin)) {// 1.区县级管理员
																													// 2.如果没有权限就看区县的（规定）
					paramMap.put("regionId", areaId);
				} else if (User.bak1_schoool.equalsIgnoreCase(isadmin)) {// 学校管理员
					regionId = getUserSchoolId();
					paramMap.put("regionId", regionId);
				}else{//OA  是运营商时 默认给最高级   2018-04-17添加
					SysDict cityDict = sysDictService.findByKey(areaDict.getPid());// 区县信息
					paramMap.put("regionId", cityDict.getPid());//
				}

			}else if(platformLevel.equals("C")){//
				if (User.bak1_city.equalsIgnoreCase(isadmin)) {// 市级管理员
					
					paramMap.put("regionId", areaDict.getPid());

				} else if (User.bak1_county.equalsIgnoreCase(isadmin) || User.bak1_no.equalsIgnoreCase(isadmin)) {// 1.区县级管理员
																													// 2.如果没有权限就看区县的（规定）
					paramMap.put("regionId", areaId);
				} else if (User.bak1_schoool.equalsIgnoreCase(isadmin)) {// 学校管理员
					regionId = getUserSchoolId();
					paramMap.put("regionId", regionId);
				}else{//OA  是运营商时 默认给最高级   2018-04-17添加
					paramMap.put("regionId", areaDict.getPid());
				}

			}else if(platformLevel.equals("A")){
				if (User.bak1_county.equalsIgnoreCase(isadmin) || User.bak1_no.equalsIgnoreCase(isadmin)) {// 1.区县级管理员
																													// 2.如果没有权限就看区县的（规定）
					paramMap.put("regionId", areaId);
				} else if (User.bak1_schoool.equalsIgnoreCase(isadmin)) {// 学校管理员
					regionId = getUserSchoolId();
					paramMap.put("regionId", regionId);
				}else{//OA  是运营商时 默认给最高级   2018-04-17添加
					paramMap.put("regionId", areaId);
				}
			}
		}
		
		return paramMap;
	}

	
	
}
