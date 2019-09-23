package com.zzrenfeng.zhsx.controller.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.model.TestWebQuestion;
import com.zzrenfeng.zhsx.model.TestWebQuestionAnswer;
import com.zzrenfeng.zhsx.model.TestWebStuTest;
import com.zzrenfeng.zhsx.model.TestWebTest;
import com.zzrenfeng.zhsx.model.TestWebTestQuestion;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysHistoryService;
import com.zzrenfeng.zhsx.service.TestWebQuestionAnswerService;
import com.zzrenfeng.zhsx.service.TestWebQuestionService;
import com.zzrenfeng.zhsx.service.TestWebStuTestQuestionAnswerService;
import com.zzrenfeng.zhsx.service.TestWebStuTestService;
import com.zzrenfeng.zhsx.service.TestWebTestQuestionService;
import com.zzrenfeng.zhsx.service.TestWebTestService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-07-05 15:34:13
 * @see com.zzrenfeng.zhsx.controller.TestWebTest
 */
@Controller
@RequestMapping(value = "/test")
public class TestWebTestController extends BaseController {

	@Resource
	private TestWebTestService testWebTestService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private TestWebStuTestService testWebStuTestService;
	@Resource
	private TestWebQuestionService testWebQusetionService;
	@Resource
	private TestWebQuestionAnswerService testWebQusetionAnswerService;
	@Resource
	private TestWebStuTestQuestionAnswerService testWebStuService;
	@Resource
	private SysHistoryService sysHistoryService;// 收藏service
	@Resource
	private TestWebTestQuestionService testWebTestQuestionService;
	@Resource
	private TestWebQuestionAnswerService testWebQuestionAnswerService;

	/**
	 * 进入考试中心
	 * 
	 * @param model
	 * @param request
	 * @param p
	 * @param t
	 * @return
	 */
	@RequestMapping("/test")
	public String test(Model model, HttpServletRequest request) {
		// 获得省
		SysDict dict = new SysDict();
		dict.setKeyname("P");
		List<SysDict> province = sysDictService.findSelective(dict);
		model.addAttribute("province", province);
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
		// 获得版本
		model.addAttribute("subjects", subjects);
		dict.setKeyname("V");
		List<SysDict> version = sysDictService.findSelective(dict);
		model.addAttribute("versions", version);

		return "/test/exam";

	}

	/**
	 * 前台显示
	 * 
	 * @param request
	 * @param model
	 * @param test
	 * @param p
	 * @return
	 */
	@RequestMapping("/testpaper")
	public String testpaper(HttpServletRequest request, Model model, TestWebTest t, Integer p) {
		if (p == null) {
			p = 1;
		}
		String gradeName = request.getParameter("gradeName");
		String subiectName = request.getParameter("subiectName");
		String area = request.getParameter("area");
		String version = request.getParameter("version");
		if (gradeName != null && !gradeName.equals("")) {
			t.setGradeName(gradeName);
		}
		if (subiectName != null && !subiectName.equals("")) {
			t.setSubiectName(subiectName);
		}
		if (area != null && !area.equals("")) {
			t.setArea(area);
		}
		if (version != null && !version.equals("")) {
			t.setVersion(version);
		}
		t.setPublicType("Y");// 公开的试卷
		t.setLock("Y");//

		Page<TestWebTest> pageInfo = testWebTestService.findPageSelective(t, p, 6);
		int pages = pageInfo.getPages();
		List<TestWebTest> lists = pageInfo.getResult();
		int pageSize = pageInfo.getPageSize();
		long total = pageInfo.getTotal();

		model.addAttribute("pageSize", pageSize);
		model.addAttribute("total", total);
		// 获取登录用户id
		if (isLogined()) {
			String userId = getUserId();
			// 获取该用户的收藏试卷
			for (TestWebTest testWebTest : lists) {
				SysHistory sh = new SysHistory();
				String id = testWebTest.getId();
				sh.setPubId(id);
				sh.setUserId(userId);
				sh.setPubType("E");
				sh.setPubFlag("C");
				List<SysHistory> history = sysHistoryService.findSelective(sh);
				testWebTest.setHistory(history);
			}
		}
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("area", area);
		model.addAttribute("version", version);
		model.addAttribute("subiectName", subiectName);
		model.addAttribute("gradeName", gradeName);
		return "/test/testpaper";
	}

	/**
	 * 我的试卷库
	 * 
	 * @param request
	 * @param model
	 * @param test
	 * @param p
	 * @return
	 * @throws ParseException
	 */

	@SuppressWarnings("unused")
	@RequestMapping("/paperlist")
	public String paperlist(HttpServletRequest request, Model model, TestWebTest test, Integer p) {
		// 获得省
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

		if (p == null) {
			p = 1;
		}
		String search = request.getParameter("search");
		String gradeName = request.getParameter("gradeName");// 搜索字段
		String subjectName = request.getParameter("subjectName");// 搜索字段
		String addTim = request.getParameter("addTim");// 搜索字段
		String status = request.getParameter("status");// 搜索字段
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		if (addTim != null && !addTim.equals("")) {
			try {
				date1 = fmt.parse(addTim);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (search != null && !search.equals("")) {
			test.setSearch(search);
		}

		if (gradeName != null && !gradeName.equals("")) {
			test.setGradeName(gradeName);
		}

		if (subjectName != null && !subjectName.equals("")) {
			test.setSubiectName(subjectName);
		}
		if (addTim != null && !addTim.equals("")) {
			// test.setAddTime(date1);
			test.setAddTim(addTim);
		}
		if (status != null && !status.equals("")) {
			test.setStatus(status);
		}
		model.addAttribute("pageNum", p);
		model.addAttribute("search", search);
		model.addAttribute("gradeName", gradeName);
		model.addAttribute("subjectName", subjectName);
		model.addAttribute("status", status);
		model.addAttribute("memberId", test.getMemberId());
		model.addAttribute("addTim", addTim);

		test.setBak1("T");
		Page<TestWebTest> pageInfo = testWebTestService.findPageSelective(test, p, 10);
		int pages = pageInfo.getPages();
		model.addAttribute("pages", pages);
		List<TestWebTest> lists = pageInfo.getResult();
		int pageSize = pageInfo.getPageSize();
		long total = pageInfo.getTotal();

		model.addAttribute("pageSize", pageSize);
		model.addAttribute("total", total);
		System.out.println(lists.size());
		Date date = new Date();
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).getLockTime() != null && date.getTime() > lists.get(i).getLockTime().getTime()) {
				lists.get(i).setLock("Y");
			} else {
				lists.get(i).setLock("");
			}
			if (lists.get(i).getPublicType() == null) {
				lists.get(i).setPublicType("");
			}
		}
		model.addAttribute("lists", lists);
		return "/testManager/geren_shijuanku";
	}

	@RequestMapping("/del")
	public void del(HttpServletResponse response, String id) {
		try {
			testWebTestService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 进入试卷编辑界面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editTest")
	public String editTest(Model model, String id) {
		// 获得省
		SysDict dict = new SysDict();
		dict.setKeyname("P");
		List<SysDict> province = sysDictService.findSelective(dict);
		model.addAttribute("province", province);
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

		TestWebTest t = testWebTestService.findByKey(id);
		String subjectChk = t.getSubiectName();
		String gradeChk = t.getGradeName();
		String ver = t.getVersion();
		model.addAttribute("test", t);
		model.addAttribute("testId", id);
		model.addAttribute("gradeChk", gradeChk);
		model.addAttribute("subjectChk", subjectChk);
		model.addAttribute("ver", ver);
		return "/testManager/geren_testEdit";

	}

	/**
	 * 修改试卷信息
	 * 
	 * @param response
	 * @param test
	 */
	@RequestMapping("/updateTest")
	public @ResponseBody Map<String, Object> updateTest(HttpServletResponse response, Model model, TestWebTest test) {

		testWebTestService.updateByKeySelective(test);
		String id = test.getId();
		String testTitle = test.getTestTitle();
		String totalScore = test.getTotalScore();
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("testTitle", testTitle);
		hm.put("testId", id);
		hm.put("totalScore", totalScore);
		return hm;

	}

	/**
	 * 添加试卷页面
	 * 
	 * @param response
	 * @param twq
	 */
	@RequestMapping("/addTest")
	public String addTest(HttpServletResponse response, Model model, TestWebTest test) {

		// 获得省
		SysDict dict = new SysDict();
		dict.setKeyname("P");
		List<SysDict> province = sysDictService.findSelective(dict);
		model.addAttribute("province", province);
		// 获得年级
		dict.setKeyname("G");
		List<SysDict> grades = sysDictService.findSelective(dict);
		model.addAttribute("grades", grades);
		String gradeChk = "";
		gradeChk = grades.get(0).getId();
		model.addAttribute("gradeChk", gradeChk);
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
		// 获得版本
		model.addAttribute("subjects", subjects);
		dict.setKeyname("V");
		List<SysDict> version = sysDictService.findSelective(dict);
		model.addAttribute("version", version);

		return "/testManager/geren_tianjia";

	}

	/**
	 * 添加试卷（普通试卷）
	 * 
	 * @param response
	 * @param twq
	 * @throws ParseException
	 */
	@RequestMapping("/saveTest")
	public @ResponseBody Map<String, Object> saveTest(HttpServletResponse response, Model model, TestWebTest test)
			throws ParseException {

		String memberId = getUserId();// 获得登录用户id
		test.setMemberId(memberId);
		test.setAddTime(new Date());
		test.setTests(0);
		test.setViews(0);
		test.setQuestions(0);
		// 区分标志符号
		test.setBak1("T");
		testWebTestService.insert(test);// 添加试卷
		if (getUserType().equals(User.userType_teachers) && !test.getPublicType().equals("")) {
			testWebTestService.addUserExp(getUserId(), test.getId());
		}
		String id = test.getId();
		String testTitle = test.getTestTitle();
		String totalScore = test.getTotalScore();
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("testTitle", testTitle);
		hm.put("testId", id);
		hm.put("totalScore", totalScore);
		return hm;

	}

	/**
	 * 进入到试卷试题（个人中心）
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/addTi")
	public String addTi(HttpServletRequest request, Model model, Integer p, TestWebTestQuestion tq) {
		String testId = request.getParameter("testId");
		String testTitle = request.getParameter("testTitle");
		String totalScore = request.getParameter("totalScore");
		model.addAttribute("testId", testId);
		model.addAttribute("testTitle", testTitle);
		model.addAttribute("totalScore", totalScore);
		// 统计数量以及计算已经设置的分数
		List<TestWebTestQuestion> li = testWebTestQuestionService.findSelective(tq);
		int size = 0;// 题数
		int num = 0;// 已设置的分值
		if (li != null && li.size() > 0) {
			size = li.size();
			for (int i = 0; i < li.size(); i++) {
				num += Integer.parseInt(li.get(i).getScores());
			}
		}
		if (p == null) {
			p = 1;
		}
		Page<TestWebTestQuestion> pageInfo = testWebTestQuestionService.findPageSelective(tq, p, 10);
		List<TestWebTestQuestion> lists = pageInfo.getResult();
		int pages = pageInfo.getPages();
		int pageSize = pageInfo.getPageSize();
		long total = pageInfo.getTotal();

		model.addAttribute("pageSize", pageSize);
		model.addAttribute("total", total);
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("size", size);
		model.addAttribute("num", num);
		return "/testManager/geren_addTestTi";
	}

	/**
	 * 编辑添加下一步
	 * 
	 * @param test
	 * @return
	 */
	@RequestMapping("/xiayibu")
	public String xiayibu(HttpServletRequest request) {
		String id = request.getParameter("id");
		String ispub = request.getParameter("ispub");
		int size = Integer.parseInt(request.getParameter("questions"));
		TestWebTest t = new TestWebTest();
		t.setId(id);
		t.setQuestions(size);
		t.setPublicType(ispub);
		testWebTestService.updateByKeySelective(t);
		return "/testManager/geren_finally2";
	}

	/**
	 * 修改题分值
	 * 
	 * @param response
	 * @param ttq
	 */
	@RequestMapping("/editScores")
	public void editScores(HttpServletResponse response, TestWebTestQuestion ttq) {
		try {
			testWebTestQuestionService.updateByKeySelective(ttq);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

	/**
	 * 删除试卷问题
	 * 
	 * @param response
	 * @param ttq
	 */
	@RequestMapping("/delTestQue")
	public void delTestQue(HttpServletResponse response, HttpServletRequest request) {
		try {
			String id = request.getParameter("id");
			testWebTestQuestionService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

	@RequestMapping("/findOne")
	public String findOne(HttpServletRequest request, Model model, Integer p) {
		if (p == null)
			p = 1;
		// 查询试卷详细信息
		String testId = request.getParameter("testId");
		TestWebTest test = testWebTestService.findByKey(testId);
		model.addAttribute("test", test);
		// 根据试卷id获得试卷问题表
		TestWebTestQuestion twq = new TestWebTestQuestion();
		twq.setTestId(testId);
		Page<TestWebTestQuestion> pageInfo = testWebTestQuestionService.findPageSelective(twq, p, 4);
		int pages = pageInfo.getPages();
		List<TestWebTestQuestion> twqL = pageInfo.getResult();
		int pageSize = pageInfo.getPageSize();
		long total = pageInfo.getTotal();

		model.addAttribute("pageSize", pageSize);
		model.addAttribute("total", total);
		String[] xuanxiang = { "A", "B", "C", "D", "E", "F", "G" };
		for (int i = 0; i < twqL.size(); i++) {
			// 根据questionId获得选项
			TestWebQuestionAnswer ans = new TestWebQuestionAnswer();
			ans.setQuestionId(twqL.get(i).getQuestionId());
			List<TestWebQuestionAnswer> ansList = testWebQuestionAnswerService.findSelective(ans);
			for (int j = 0; j < ansList.size(); j++) {

				ansList.get(j).setXuanxiang(xuanxiang[j]);

			}
			twqL.get(i).setQuestionAnswer(ansList);
			// 试卷详情获取完毕

		}
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", twqL);
		model.addAttribute("testId", testId);
		return "/testManager/geren_testDetail";

	}

	/**
	 * 随机测试 添加试卷
	 * 
	 * @param response
	 * @param model
	 * @param test
	 * @return
	 */
	@RequestMapping("/addRanDomTest")
	public String addRanDomTest(HttpServletResponse response, Model model, TestWebTest test) {
		String memberId = getUserId();// 获得登录用户的id
		test.setMemberId(memberId);
		test.setAddTime(new Date());
		// 区分标识符
		test.setBak1("S");
		// 插入试卷
		testWebTestService.insert(test);
		String id = test.getId();
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		String testTitle = "随机测试" + time;
		TestWebStuTest twst = new TestWebStuTest();
		// 插入学生测试试卷表 start
		twst.setStudentId(memberId);// 插入
		twst.setTestId(id);
		twst.setMemberId(test.getMemberId());
		twst.setTestTitle(testTitle);
		twst.setStuType(test.getStuType());
		twst.setGradeName(test.getGradeName());
		twst.setSubjectName(test.getSubiectName());
		twst.setVolume(test.getVolume());
		twst.setVersion(test.getVersion());
		twst.setTotalScores(test.getTotalScore());
		twst.setTimes(test.getTimes());
		twst.setQuestions(test.getQuestions());
		twst.setAddTime(new Date());
		testWebStuTestService.insert(twst);
		// 获取该试卷id
		String stuTestId = twst.getId();
		// end
		TestWebQuestion twq = new TestWebQuestion();
		twq.setVolume(Integer.parseInt(test.getVersion()));
		// 随机获取多少个问题
		List<TestWebQuestion> lists = testWebQusetionService.findRandom(twq);
		// 将问题选项与题目综合到一起
		for (TestWebQuestion testWebQuestion : lists) {
			TestWebQuestionAnswer twqa = new TestWebQuestionAnswer();
			twqa.setQuestionId(testWebQuestion.getId());
			List<TestWebQuestionAnswer> answers = testWebQusetionAnswerService.findSelective(twqa);
			testWebQuestion.setAnswers(answers);
		}
		// end
		model.addAttribute("lists", lists);
		model.addAttribute("testTitle", testTitle);
		model.addAttribute("id", id);
		model.addAttribute("stuTestId", stuTestId);// 生成的学生试卷id
		model.addAttribute("stuId", memberId);// 学生id传过去

		return "";
	}

}
