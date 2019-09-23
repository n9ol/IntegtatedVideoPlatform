package com.zzrenfeng.zhsx.controller.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.TestWebOffLine;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.TestWebOffLineService;
import com.zzrenfeng.zhsx.service.TestWebTestService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-08-14 15:33:19
 * @see com.zzrenfeng.zhsx.controller.TestWebOffLine
 */
@Controller
@RequestMapping(value = "/testOffLine")
public class TestWebOffLineController extends BaseController {

	@Resource
	private TestWebOffLineService testWebOffLineService;

	@Resource
	private UserService userService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private TestWebTestService testWebTestService;

	/**
	 * 进入主界面
	 * 
	 * @param request
	 * @param offLine
	 * @param p
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) {
		SysDict dict = new SysDict();
		// 获得年级
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
		dict.setKeyname("V");
		List<SysDict> versions = sysDictService.findSelective(dict);
		model.addAttribute("versions", versions);
		return "/test/offLine_test";

	}

	/**
	 * 进入主界面
	 * 
	 * @param request
	 * @param offLine
	 * @param p
	 * @param model
	 * @return
	 */
	@RequestMapping("/getAll")
	public String getAll(HttpServletRequest request, TestWebOffLine offLine, Integer p, Model model) {
		if (p == null)
			p = 1;
		String gradeId = request.getParameter("gradeId");
		String subjectId = request.getParameter("subjectId");
		String area = request.getParameter("area");
		String version = request.getParameter("version");
		if (version != null && !version.equals("")) {
			offLine.setVersion(version);
		}
		if (gradeId != null && !gradeId.equals("")) {
			offLine.setGradeId(gradeId);
		}
		if (subjectId != null && !subjectId.equals("")) {
			offLine.setSubjectId(subjectId);
		}
		if (area != null && !area.equals("")) {
			offLine.setArea(area);
		}
		model.addAttribute("subjectId", subjectId);
		model.addAttribute("gradeId", gradeId);
		model.addAttribute("area", area);
		model.addAttribute("version", version);
		Page<TestWebOffLine> pageInfo = testWebOffLineService.findPageSelective(offLine, p, 5);
		List<TestWebOffLine> lists = pageInfo.getResult();
		int page = 0;
		int sum = 1;
		for (int i = 0; i < lists.size(); i++) {
			page = lists.get(i).getPassNum();
			sum = lists.get(i).getSum();
			if (sum == 0) {
				lists.get(i).setPercentage("0%");
			} else {
				String x = String.valueOf((page * 100 / sum));
				// x= x.substring(4);
				lists.get(i).setPercentage(x + "%");
			}
		}
		int pages = pageInfo.getPages();
		int pageSize = pageInfo.getPageSize();
		long total = pageInfo.getTotal();

		model.addAttribute("pageSize", pageSize);
		model.addAttribute("total", total);
		model.addAttribute("lists", lists);
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		return "/test/offLineAll";

	}

	/**
	 * 进入离线考试（个人中心）
	 */
	@RequestMapping("/managerOffLine")
	public String managerOffLine(HttpServletRequest request, Model model, Integer p, TestWebOffLine offLine) {
		SysDict dict = new SysDict();
		// 获得年级
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
		if (p == null) {
			p = 1;
		}
		String uid = request.getParameter("memberId");
		String gradeId = request.getParameter("gradeId");
		String subjectId = request.getParameter("subjectId");
		String search = request.getParameter("search");
		if (gradeId != null && !gradeId.equals("")) {
			offLine.setGradeId(gradeId);
		}
		if (subjectId != null && !subjectId.equals("")) {
			offLine.setSubjectId(subjectId);
		}
		if (search != null && !search.equals("")) {
			offLine.setSearch(search);
		}
		User u = userService.findByKey(uid);
		String schoolId = u.getSchoolId();// 获得在线老师的学校id

		Page<TestWebOffLine> pageInfo = testWebOffLineService.findPageSelective(offLine, p, 10);
		int pages = pageInfo.getPages();
		List<TestWebOffLine> lists = pageInfo.getResult();
		int page = 0;
		int sum = 1;
		for (int i = 0; i < lists.size(); i++) {
			page = lists.get(i).getPassNum();
			sum = lists.get(i).getSum();
			if (sum == 0) {
				lists.get(i).setPercentage("0%");
			} else {
				String x = String.valueOf((page * 100 / sum));
				lists.get(i).setPercentage(x + "%");
			}
		}

		int pageSize = pageInfo.getPageSize();
		long total = pageInfo.getTotal();

		model.addAttribute("pageSize", pageSize);
		model.addAttribute("total", total);
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("schoolId", schoolId);
		model.addAttribute("uid", uid);
		model.addAttribute("gradeId", gradeId);
		model.addAttribute("subjectId", subjectId);
		model.addAttribute("search", search);
		return "/testManager/geren_offLine";
	}

	/**
	 * 进入添加界面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request, Model model) {
		SysDict dict = new SysDict();
		// 获得年级
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
		// 获得版本
		dict.setKeyname("V");
		List<SysDict> version = sysDictService.findSelective(dict);
		model.addAttribute("version", version);
		String schoolId = getUserSchoolId();
		String memeberId = getUserId();
		model.addAttribute("schoolId", schoolId);
		model.addAttribute("memeberId", memeberId);
		return "/testManager/geren_addOffLine";
	}

	/**
	 * 进入编辑界面
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, Model model, String id) {
		TestWebOffLine offLine = testWebOffLineService.findByKey(id);

		SysDict dict = new SysDict();
		// 获得年级
		dict.setKeyname("G");
		List<SysDict> grades = sysDictService.findSelective(dict);
		model.addAttribute("grades", grades);

		// 获得科目 去重复
		List<SysDict> subjects = new ArrayList<SysDict>();
		dict.setKeyname("S");
		dict.setPid(offLine.getGradeId());
		List<SysDict> subject = sysDictService.findSelective(dict);
		String tem = "1";
		for (SysDict o : subject) {
			if (!tem.contains(o.getValue())) {
				subjects.add(o);
			}
			tem += o.getValue();
		}
		model.addAttribute("subjects", subjects);

		// 获得版本
		dict.setKeyname("V");
		dict.setPid(null);
		List<SysDict> version = sysDictService.findSelective(dict);
		model.addAttribute("version", version);

		String schoolId = getUserSchoolId();
		String memeberId = getUserId();
		model.addAttribute("schoolId", schoolId);
		model.addAttribute("memeberId", memeberId);
		model.addAttribute("subjectChk", offLine.getSubjectId());
		model.addAttribute("gradeChk", offLine.getGradeId());
		model.addAttribute("offLine", offLine);

		return "/testManager/geren_editOffLine";
	}

	/**
	 * 编辑保存
	 */
	@RequestMapping("/editsave")
	public void editsave(HttpServletResponse response, TestWebOffLine offLine) {
		try {
			offLine.setMemberId(getUserId());
			offLine.setAddTime(new Date());
			testWebOffLineService.updateByKeySelective(offLine);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public void save(HttpServletResponse response, TestWebOffLine offLine) {
		try {
			offLine.setMemberId(getUserId());
			offLine.setAddTime(new Date());
			testWebOffLineService.insert(offLine);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping("/del")
	public void del(HttpServletResponse response, String id) {
		try {

			testWebOffLineService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 离线统计图详细信息
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/detail")
	public String detail(HttpServletRequest request, String id, Model model) {
		TestWebOffLine offLine = testWebOffLineService.findByKey(id);
		model.addAttribute("offLine", offLine);
		TestWebOffLine t = new TestWebOffLine();
		String schoolId = offLine.getSchoolId();
		String grade = offLine.getGradeId();
		t.setSchoolId(schoolId);
		t.setGradeId(grade);

		// 图三 各年级的平均分
		List<TestWebOffLine> list2 = testWebOffLineService.getAvgByGrade(schoolId);
		String nianji = "";
		String str3 = "[";
		if (null != list2 && list2.size() > 0) {
			for (int i = 0; i < list2.size(); i++) {
				nianji += "'" + list2.get(i).getGradeName() + "',";// 年级名称
				str3 += "{value:" + list2.get(i).getSumAver() + ",";
				str3 += "name:" + "'" + list2.get(i).getGradeName() + "'},";
			}
			str3 += "]";
		}
		// 图五图六整体平均分数
		List<TestWebOffLine> list5 = testWebOffLineService.getAvgBySchool();
		String schoolname = "";
		String str5 = "[";
		String str6 = "[";
		for (int i = 0; i < list5.size(); i++) {
			schoolname += "'" + list5.get(i).getSchoolName() + "',";
			str5 += "{value:" + list5.get(i).getSumAver() + ",";
			str5 += "name:" + "'" + list5.get(i).getSchoolName() + "'},";
			if (i < 5) {

				str6 += " {type : 'pie', center : ['" + (10 + 20 * i) + "%', '30%'], radius : radius, x: '" + i * 20
						+ "%',   itemStyle : labelFromatter, ";
			}
			if (i >= 5) {

				str6 += " {type : 'pie', center : ['" + (10 + 20 * (i - 5))
						+ "%', '70%'], radius : radius, y: '55%',  x: '" + (i - 5) * 20
						+ "%',   itemStyle : labelFromatter, ";
			}
			str6 += " data : [{name:'other',value:" + (100 - list5.get(i).getPassRatio())
					+ ", itemStyle : labelBottom},";
			str6 += "{name:'" + list5.get(i).getSchoolName() + "', value:" + list5.get(i).getPassRatio()
					+ ",itemStyle : labelTop}]},";
		}
		str5 += "]";
		str6 += "]";
		// 图四
		// 根据学校id 年级id搜索所有班级(去重)
		TestWebOffLine two = new TestWebOffLine();
		two.setSchoolId(schoolId);
		two.setGradeId(grade);
		List<TestWebOffLine> list4 = testWebOffLineService.findClassRoom(two);
		String banji2 = "";
		String str4 = "[";
		String gradeList = "";
		List<TestWebOffLine> lis4 = new ArrayList<TestWebOffLine>();
		for (int i = 0; i < list4.size(); i++) {
			gradeList = "";
			two.setClassId(list4.get(i).getClassId());
			lis4 = testWebOffLineService.getByMonth(two);
			banji2 += "'" + list4.get(i).getClassId() + "',";
			str4 += "{name: '" + list4.get(i).getClassId() + "',";
			str4 += "type: 'bar',";
			for (int j = 0; j < lis4.size(); j++) {
				gradeList += lis4.get(j).getSumAver() + ",";
			}
			str4 += "data: [" + gradeList + "]},";

		}

		str4 += "]";
		// 图2
		List<TestWebOffLine> list1 = testWebOffLineService.getAllBysubj(t);// 得到科目
		String[] banji = banji2.split(",");// 班级字符串
		String subj = "[";// 科目字符串
		String fen = "";
		String name = "";
		String className = "";
		String str = "[";
		if (list1 != null && list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				subj += "'" + list1.get(i).getSubjectName() + "',";
			}

		}
		subj += "]";
		for (int j = 0; j < banji.length; j++) {
			fen = "";
			for (int i = 0; i < list1.size(); i++) {
				TestWebOffLine tt = new TestWebOffLine();
				tt.setSchoolId(list1.get(i).getSchoolId());
				tt.setGradeId(list1.get(i).getGradeId());
				tt.setSubjectId(list1.get(i).getSubjectId());
				tt.setClassId(banji[j].replaceAll("\'", ""));
				TestWebOffLine offList = testWebOffLineService.getClassScore(tt);

				fen += offList.getSumAver() + ",";
			}
			str += "{";
			str += "name:";
			str += banji[j];
			str += ",type: 'bar',";
			str += "data:";
			str += "[" + fen + "]";
			str += "},";
		}

		model.addAttribute("str4", str4);
		model.addAttribute("banji2", banji2);
		model.addAttribute("subj", subj);
		model.addAttribute("className", className);
		model.addAttribute("jsonList", str + "]");
		model.addAttribute("nianji", nianji);
		model.addAttribute("str3", str3);
		model.addAttribute("schoolname", schoolname);
		model.addAttribute("str6", str6);
		model.addAttribute("str5", str5);
		return "/test/offLine";

	}
}
