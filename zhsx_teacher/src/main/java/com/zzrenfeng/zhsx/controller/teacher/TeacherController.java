package com.zzrenfeng.zhsx.controller.teacher;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.mapper.UserMapper;
import com.zzrenfeng.zhsx.model.OffLineVideoResources;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.OffLineVideoResourcesService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.TeacherService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.util.JsonUtil;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController extends BaseController {

	@Resource
	private UserService userService;

	@Resource
	private TeacherService teacherService;

	@Resource
	private SysDictService sysDictService;

	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private UserMapper userMapper;
	@Resource
	private OffLineVideoResourcesService offLineVideoResourcesService;

	/**
	 * 根据经验值换算等级
	 * 
	 * @param x
	 *            经验值
	 * @return
	 */
	public static int getGrade(double x) {
		double nn = Math.sqrt(x / 15 - 7 / 4) - 0.5;
		int n = (int) Math.floor(nn);
		int ex = (n * n + n + 2) * 15;
		if (ex > x) {
			n -= 1;
		}
		return n;

	}

	/**
	 * 名师团队列表
	 * 
	 * @param u
	 * @param p
	 * @return
	 */
	@RequestMapping("/teacherList")
	public String teacherList(Model model, User u, Integer p) {
		if (p == null)
			p = 1;

		// 获得年级
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("G");
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("gradeList", sysDicts);

		// 获得科目
		sysDict.setKeyname("S");
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
		model.addAttribute("subjectsList", subjects);

		// 最新名师
		Page<User> newT = teacherService.findNewTeacher(1, 6);
		// 关注度排行
		Page<Map<String, Object>> hotT = teacherService.findHotTheacher(1, 6);

		List<User> newTs = newT.getResult();
		List<Map<String, Object>> hotTs = hotT.getResult();

		model.addAttribute("newTeacher", newTs);
		model.addAttribute("hotTeacher", hotTs);
		model.addAttribute("pageNum", p);
		// model.addAttribute("pages", "");
		// model.addAttribute("gradeid", "");
		// model.addAttribute("kmid", "");

		return "/teacher/teacherList";
	}

	@RequestMapping("/getListData")
	public String getListData(HttpServletRequest request, User u, Model model, Integer p) {
		if (p == null)
			p = 1;
		model.addAttribute("pageNum", p);
		// u = new User();
		// u.setUserType(User.userType_teachers);
		// Page<User> pageInfo = userService.findPageSelective(u, p, 6);
		// int pages = pageInfo.getPages(); //总页数
		// long total = pageInfo.getTotal();
		// List<User> lists = pageInfo.getResult();

		Map<String, String> m = new HashMap<String, String>();
		String area = u.getBak2();// 地区
		String gradeid = u.getSchoolName();// 年级
		String kmid = u.getAuthority();// 科目

		if (isLogined()) {
			m.put("userId", getUserId());
		} else {
			m.put("userId", "");
		}
		m.put("userType", User.userType_teachers);
		m.put("currName", u.getCurrName());
		m.put("bak", "Y");
		m.put("area", u.getBak2());
		m.put("sort", u.getBak());// 排序查询
		if (StringUtils.isNotEmpty(gradeid)) {
			SysDict sysDicts = sysDictService.findByKey(gradeid);
			m.put("gradeName", sysDicts.getValue());// 年级
		}
		if (StringUtils.isNotEmpty(kmid)) {
			SysDict sysDicts2 = sysDictService.findByKey(kmid);
			m.put("subjectName", sysDicts2.getValue());// 科目
		}
		Page<Map<String, String>> pageInfo = teacherService.findTeacherList(m, p, 6);
		int pages = pageInfo.getPages(); // 总页数
		long total = pageInfo.getTotal();
		List<Map<String, String>> lists = pageInfo.getResult();

		model.addAttribute("pages", pages);
		model.addAttribute("total", total);
		model.addAttribute("lists", lists);
		model.addAttribute("gradeid", u.getSchoolName());// 年级
		model.addAttribute("kmid", u.getAuthority());// 科目

		return "/teacher/teacherListData";
	}

	/**
	 * 查看教师详情
	 * 
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/teacherDetails")
	public String teacherDetails(HttpServletResponse response, Model model, String id) {

		// User u = userService.findByKey(id);
		Map<String, String> param = new HashMap<String, String>();
		param.put("id", id);
		if (isLogined()) {
			param.put("userId", getUserId());
		} else {
			param.put("userId", null);
		}

		Map<String, Object> u = teacherService.findTeacherDetails(param);

		// 关注度排行
		Page<Map<String, Object>> hotT = teacherService.findHotTheacher(1, 6);
		List<Map<String, Object>> hotTs = hotT.getResult();

		model.addAttribute("hotTeacher", hotTs);
		model.addAttribute("id", id);

		Page<Map<String, Object>> gzd = userMapper.findHotTeacher(id);// 关注度
		if (gzd != null && gzd.size() == 1) {
			Map<String, Object> m = gzd.get(0);
			model.addAttribute("gzd", m.get("gzd"));
		} else {
			model.addAttribute("gzd", "0");
		}
		// 根据经验值换算等级
		Double exp = Double.parseDouble(u.get("EXP") != null ? u.get("EXP").toString() : "0.0");
		int d = getGrade(exp);
		u.put("grade", d);
		// --------

		model.addAttribute("teacher", u);

		String deveopmentSyetem = deveopmentSyetem(id);
		String growthCurve = growthCurve(id);
		// String afterSchoolDiscipline = afterSchoolDiscipline();
		model.addAttribute("deveopmentSyetem", deveopmentSyetem);
		model.addAttribute("growthCurve", growthCurve);
		// model.addAttribute("afterSchoolDiscipline", afterSchoolDiscipline);

		List<Map<String, Object>> relatedVideo = relatedVideo(id);
		model.addAttribute("relatedVideo", relatedVideo);

		return "/teacher/teacherDetails";

	}

	/**
	 * 收藏或关注
	 */
	@RequestMapping("/insterSysHistory")
	public void insterSysHistory(HttpServletResponse response, @Validated SysHistory sysHistory) {
		try {
			sysHistory.setUserId(getUserId());

			int res = teacherService.addCollection(sysHistory);
			if (res == 2) {
				WriterUtils.toHtml(response, "重复关注！");
			} else if (res == 1) {
				WriterUtils.toHtml(response, MessageUtils.SUCCESS);
			} else {
				WriterUtils.toHtml(response, MessageUtils.FAilURE);
			}

		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 取消关注
	 * 
	 * @param response
	 * @param pubFlag
	 * @param pubType
	 * @param pubId
	 */
	@RequestMapping("/delSysHistory")
	public void delSysHistory(HttpServletResponse response, String pubFlag, String pubType, String pubId) {
		try {
			teacherService.deleteCollectionByPub(pubFlag, pubType, pubId, getUserId());
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 获取对比信息
	 */
	@RequestMapping("/getContrast")
	public void getContrast(HttpServletResponse response, @Validated String id) {
		String[] ids = id.split(";");
		if (ids.length < 1) {
			WriterUtils.toHtml(response, "出错！");
			return;
		}

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// -----color-------
		List<String> color = new ArrayList<String>();
		color.add("#ffb981");
		color.add("#d97e84");
		color.add("#c36cf0");
		color.add("#4596e5");
		// -----title------
		Map<String, Object> title = new HashMap<String, Object>();
		title.put("text", "本次对比");
		title.put("x", "center");
		// -----tooltip----
		Map<String, Object> tooltip = new HashMap<String, Object>();
		tooltip.put("trigger", "axis");
		Map<String, Object> axisPointer = new HashMap<String, Object>();
		axisPointer.put("type", "cross");
		tooltip.put("axisPointer", axisPointer);
		// ---------legend--------
		Map<String, Object> legend = new HashMap<String, Object>();
		legend.put("name", "XXX老师");
		List<String> data = new ArrayList<String>();
		legend.put("data", data);
		legend.put("y", "bottom");
		// ----------xAxis----------------
		Map<String, Object> xAxis = new HashMap<String, Object>();
		List<String> xAxisdata = new ArrayList<String>();
		xAxisdata.add("课前备课");
		xAxisdata.add("课中授课");
		xAxisdata.add("课后督课");
		xAxis.put("data", xAxisdata);
		// ----------yAxis----------------
		Map<String, Object> yAxis = new HashMap<String, Object>();
		yAxis.put("type", "value");
		yAxis.put("min", 0);
		yAxis.put("max", 100);
		yAxis.put("name", "成绩");
		// ------series------
		List<Map<String, Object>> series = new ArrayList<Map<String, Object>>();

		for (String theId : ids) {
			User u1 = userService.findByKey(theId);
			Map<String, Object> gm1 = teacherService.findGrowth(theId);

			data.add(u1.getCurrName() == null ? u1.getNickName() : u1.getCurrName());

			Map<String, Object> seriesMap = new HashMap<String, Object>();
			List<Object> data1 = new ArrayList<Object>();
			seriesMap.put("name", u1.getCurrName() == null ? u1.getNickName() : u1.getCurrName());
			seriesMap.put("type", "bar");
			data1.add(gm1 != null ? gm1.get("q") : 0);
			data1.add(gm1 != null ? gm1.get("z") : 0);
			data1.add(gm1 != null ? gm1.get("h") : 0);
			seriesMap.put("data", data1);
			series.add(seriesMap);

		}

		jsonMap.put("color", color);
		jsonMap.put("title", title);
		jsonMap.put("tooltip", tooltip);
		jsonMap.put("legend", legend);
		jsonMap.put("xAxis", xAxis);
		jsonMap.put("yAxis", yAxis);
		jsonMap.put("series", series);

		WriterUtils.toHtml(response, JsonUtil.mapJSON(jsonMap));
	}

	/**
	 * 成长体系查询
	 */
	public String deveopmentSyetem(String id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// -----color-------
		List<String> color = new ArrayList<String>();
		color.add("#d2c8e6");
		color.add("#80c7d7");
		// -----title------
		Map<String, Object> title = new HashMap<String, Object>();
		title.put("text", "我的结构图VS标准教师");
		// -----tooltip----
		Map<String, Object> tooltip = new HashMap<String, Object>();
		// ----legend------
		Map<String, Object> legend = new HashMap<String, Object>();
		List<String> data = new ArrayList<String>();
		data.add("我的结构图");
		data.add("标准教师");
		legend.put("y", "bottom");
		legend.put("data", data);
		// ----radar-----
		Map<String, Object> gm = teacherService.findGrowth(id);

		Map<String, Object> radar = new HashMap<String, Object>();
		List<Map<String, Object>> indicator = new ArrayList<Map<String, Object>>();
		Map<String, Object> indicatorMap1 = new HashMap<String, Object>();
		indicatorMap1.put("name", "课前备课");
		indicatorMap1.put("max", 100);
		Map<String, Object> indicatorMap2 = new HashMap<String, Object>();
		indicatorMap2.put("name", "课中授课");
		indicatorMap2.put("max", 100);
		Map<String, Object> indicatorMap3 = new HashMap<String, Object>();
		indicatorMap3.put("name", "课后督课");
		indicatorMap3.put("max", 100);
		Map<String, Object> indicatorMap4 = new HashMap<String, Object>();
		indicatorMap4.put("name", "课余学课");
		indicatorMap4.put("max", 100);
		indicator.add(indicatorMap1);
		indicator.add(indicatorMap2);
		indicator.add(indicatorMap3);
		// indicator.add(indicatorMap4);
		radar.put("indicator", indicator);

		// ------series------
		List<Map<String, Object>> series = new ArrayList<Map<String, Object>>();
		Map<String, Object> seriesMap = new HashMap<String, Object>();
		List<Map<String, Object>> data1 = new ArrayList<Map<String, Object>>();

		Map<String, Object> data1Map = new HashMap<String, Object>();
		data1Map.put("name", "标准教师");
		List v1 = new ArrayList();
		v1.add(80);
		v1.add(80);
		v1.add(80);
		// v1.add(80);
		data1Map.put("value", v1);

		Map<String, Object> data1Map1 = new HashMap<String, Object>();
		data1Map1.put("name", "我的结构图");
		List v2 = new ArrayList();
		v2.add(gm != null ? gm.get("q") : 0);
		v2.add(gm != null ? gm.get("z") : 0);
		v2.add(gm != null ? gm.get("h") : 0);
		// v2.add(60);
		data1Map1.put("value", v2);

		data1.add(data1Map);
		data1.add(data1Map1);

		seriesMap.put("name", "我的结构图VS标准教师");
		seriesMap.put("type", "radar");

		Map<String, Object> areaStyleMap = new HashMap<String, Object>();
		Map<String, Object> normal = new HashMap<String, Object>();
		areaStyleMap.put("normal", normal);
		seriesMap.put("areaStyle", areaStyleMap);
		seriesMap.put("data", data1);
		series.add(seriesMap);
		// -----------------------------------------------------------
		jsonMap.put("color", color);
		jsonMap.put("title", title);
		jsonMap.put("tooltip", tooltip);
		jsonMap.put("legend", legend);
		jsonMap.put("radar", radar);
		jsonMap.put("series", series);

		return JsonUtil.mapJSON(jsonMap);

	}

	@RequestMapping("/getGrowthCurve")
	public void getGrowthCurve(HttpServletResponse response, @Validated String id) {
		String json = growthCurve(id);
		WriterUtils.toHtml(response, json);
	}

	/**
	 * 成长曲线
	 */
	public String growthCurve(String id) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// -----color-------
		List<String> color = new ArrayList<String>();
		color.add("#2ec7c9");
		color.add("#b6a2de");
		// -----title------
		Map<String, Object> title = new HashMap<String, Object>();
		title.put("text", "教师成长曲线");
		title.put("x", "left");
		// -----tooltip----
		Map<String, Object> tooltip = new HashMap<String, Object>();
		tooltip.put("trigger", "axis");
		// ----legend------
		Map<String, Object> legend = new HashMap<String, Object>();
		List<String> data = new ArrayList<String>();
		// data.add("标准成长曲线");
		data.add("我的成长曲线");
		legend.put("data", data);
		// -----toolbox-----
		Map<String, Object> toolbox = new HashMap<String, Object>();
		toolbox.put("show", true);
		// -------calculable-----------
		Boolean calculable = true;

		// ---------xAxis------------
		List<Map<String, Object>> xAxis = new ArrayList<Map<String, Object>>();
		Map<String, Object> xAxisMap = new HashMap<String, Object>();
		List<String> xAxisMapData = new ArrayList<String>();
		for (int i = 1; i < 13; i++) {
			xAxisMapData.add(i + "月");
		}

		xAxisMap.put("type", "category");
		xAxisMap.put("boundaryGap", true);
		xAxisMap.put("data", xAxisMapData);
		String year = new SimpleDateFormat("YYYY").format(new Date());
		xAxisMap.put("name", year + "年度");
		xAxis.add(xAxisMap);

		// // ---------yAxis------------
		// List<Map<String, Object>> yAxis = new ArrayList<Map<String,
		// Object>>();
		// Map<String, Object> yAxisMap = new HashMap<String, Object>();
		// yAxisMap.put("type", "value");
		// yAxisMap.put("min", "0");
		// yAxisMap.put("max", "1000");
		// yAxisMap.put("name", "成绩");
		// yAxis.add(yAxisMap);

		// ------series------
		List<Map<String, Object>> series = new ArrayList<Map<String, Object>>();

		Map<String, Object> data1Map1 = new HashMap<String, Object>();
		data1Map1.put("name", "我的成长曲线");
		data1Map1.put("type", "line");
		List v2 = new ArrayList(12);

		Double countExp = 0d;

		User user = userService.findByKey(id);
		Date regYear = user.getCreateTime();// 注册时间
		Date nowYear = new Date();
		int regMonth = regYear.getMonth() + 1;
		if (regYear.getYear() < nowYear.getYear()) {
			// 年前获得经验值
			List<Map<String, Object>> beforeExp = teacherService.findEXPBeforeThisYear(id);
			countExp = Double.parseDouble(beforeExp.get(0).get("exp").toString());
		}

		List<Map<String, Object>> t = teacherService.findGrowthCurve(id);
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		for (int i = 0; i < month; i++) {
			boolean fal = false;
			for (int j = 0; j < t.size(); j++) {
				Map<String, Object> m = t.get(j);
				if ((i + 1) == (Integer) m.get("mon")) {
					countExp += Double.parseDouble(m.get("exp").toString());
					if (regMonth == (i + 1) && regYear.getYear() == nowYear.getYear()) {
						countExp += 50;// 注册经验
					}
					v2.add(i, countExp);
					fal = true;
				}
			}
			if (!fal) {
				v2.add(i, countExp);
			}
		}
		data1Map1.put("data", v2);

		// Map<String, Object> data1Map = new HashMap<String, Object>();
		// data1Map.put("name", "标准成长曲线");
		// data1Map.put("type", "line");
		// List v1 = new ArrayList();
		// v1.add(countExp*2/12*1);
		// v1.add(countExp*2/12*2);
		// v1.add(countExp*2/12*3);
		// v1.add(countExp*2/12*4);
		// v1.add(countExp*2/12*5);
		// v1.add(countExp*2/12*6);
		// v1.add(countExp*2/12*7);
		// v1.add(countExp*2/12*8);
		// v1.add(countExp*2/12*9);
		// v1.add(countExp*2/12*10);
		// v1.add(countExp*2/12*11);
		// v1.add(countExp*2);
		// data1Map.put("data", v1);
		//
		// series.add(data1Map);
		series.add(data1Map1);

		// ---------yAxis------------
		List<Map<String, Object>> yAxis = new ArrayList<Map<String, Object>>();
		Map<String, Object> yAxisMap = new HashMap<String, Object>();
		yAxisMap.put("type", "value");
		yAxisMap.put("min", "0");
		// yAxisMap.put("max", countExp*2);
		yAxisMap.put("name", "经验值");
		yAxis.add(yAxisMap);
		// -----------------------------------------------------------
		jsonMap.put("color", color);
		jsonMap.put("title", title);
		jsonMap.put("tooltip", tooltip);
		jsonMap.put("legend", legend);
		jsonMap.put("toolbox", toolbox);
		jsonMap.put("calculable", calculable);
		jsonMap.put("xAxis", xAxis);
		jsonMap.put("yAxis", yAxis);
		jsonMap.put("series", series);

		return JsonUtil.mapJSON(jsonMap);
	}

	/**
	 * 课余学课
	 * 
	 * @return
	 */
	public String afterSchoolDiscipline() {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// -----color-------
		List<String> color = new ArrayList<String>();
		color.add("#4596e5");
		color.add("#b6a2de");
		color.add("#2ec7c9");
		// -----title------
		Map<String, Object> title = new HashMap<String, Object>();
		title.put("text", "课余学科调查问卷内容");
		title.put("x", "center");
		// -----tooltip----
		Map<String, Object> tooltip = new HashMap<String, Object>();
		tooltip.put("trigger", "item");
		tooltip.put("formatter", "{a} <br/>{b} : {c} ({d}%)");
		// ----legend------
		Map<String, Object> legend = new HashMap<String, Object>();
		List<String> data = new ArrayList<String>();
		data.add("用户活跃度");
		data.add("上传下载微课视频");
		data.add("组织学生活动次数");
		legend.put("data", data);
		legend.put("left", "50");
		legend.put("orient", "vertical");

		// ------series------
		List<Map<String, Object>> series = new ArrayList<Map<String, Object>>();
		Map<String, Object> seriesMap = new HashMap<String, Object>();

		seriesMap.put("name", "访问来源");
		seriesMap.put("type", "pie");
		seriesMap.put("radius", "55%");
		List<String> center = new ArrayList<String>();
		center.add("50%");
		center.add("60%");
		seriesMap.put("center", center);

		List<Map<String, Object>> seriesDataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> seriesDataMap1 = new HashMap<String, Object>();
		seriesDataMap1.put("value", 335);
		seriesDataMap1.put("name", "用户活跃度");
		Map<String, Object> seriesDataMap2 = new HashMap<String, Object>();
		seriesDataMap2.put("value", 100);
		seriesDataMap2.put("name", "上传下载微课视频");
		Map<String, Object> seriesDataMap3 = new HashMap<String, Object>();
		seriesDataMap3.put("value", 234);
		seriesDataMap3.put("name", "组织学生活动次数");

		seriesDataList.add(seriesDataMap1);
		seriesDataList.add(seriesDataMap2);
		seriesDataList.add(seriesDataMap3);
		seriesMap.put("data", seriesDataList);
		// --------itemStyle--------
		Map<String, Object> itemStyle = new HashMap<String, Object>();
		Map<String, Object> emphasis = new HashMap<String, Object>();
		emphasis.put("shadowBlur", 10);
		emphasis.put("shadowOffsetX", 0);
		emphasis.put("shadowColor", "rgba(0, 0, 0, 0.5)");
		itemStyle.put("emphasis", emphasis);
		seriesMap.put("itemStyle", itemStyle);

		series.add(seriesMap);

		// -----------------------------------------------------------
		jsonMap.put("color", color);
		jsonMap.put("title", title);
		jsonMap.put("tooltip", tooltip);
		jsonMap.put("legend", legend);
		jsonMap.put("series", series);

		return JsonUtil.mapJSON(jsonMap);
	}

	/**
	 * 相关视频
	 */
	public List<Map<String, Object>> relatedVideo(String id) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		OffLineVideoResources ovr = new OffLineVideoResources();
		ovr.setReleaseState("Y");
		ovr.setIsShow("Y");

		ovr.setUserId(id);
		Page<OffLineVideoResources> pageInfo = offLineVideoResourcesService.findPageSelective(ovr, 1, 10);

		int pages = pageInfo.getPages(); // 总页数
		List<OffLineVideoResources> lists = pageInfo.getResult();
		for (OffLineVideoResources l : lists) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", l.getId());
			m.put("type", l.getType());// 类型
			m.put("title", l.getTitle());// 标题
			m.put("gradeName", l.getGradeName());// 年级
			m.put("schoolName", l.getSchoolName());// 学校名
			m.put("teacherName", l.getTeacherName());// 教师姓名
			m.put("subjectName", l.getSubjectName());// 科目
			m.put("picPath", l.getPicPath());//
			m.put("timeLength", l.getTimeLength());//

			result.add(m);
		}

		return result;

	}

}
