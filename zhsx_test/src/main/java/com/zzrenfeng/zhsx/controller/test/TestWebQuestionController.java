package com.zzrenfeng.zhsx.controller.test;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.zzrenfeng.zhsx.model.TestWebQuestion;
import com.zzrenfeng.zhsx.model.TestWebQuestionAnswer;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.TestWebQuestionAnswerService;
import com.zzrenfeng.zhsx.service.TestWebQuestionService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-07-05 15:24:05
 * @see com.zzrenfeng.zhsx.controller.TestWebQuestion
 */
@Controller
@RequestMapping(value = "/testwebquestion")
public class TestWebQuestionController extends BaseController {

	@Resource
	private TestWebQuestionService testWebQuestionService;

	@Resource
	private SysDictService sysDictService;

	@Resource
	private TestWebQuestionAnswerService testWebQuestionAnswerService;

	/**
	 * 进入我的题库
	 * 
	 * @param response
	 * @param model
	 * @param p
	 * @param twq
	 * @return
	 */
	@RequestMapping("/findQuestionByUid")
	public String findQuestionByUid(Model model) {

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

		return "/testManager/geren_tiku";
	}

	/**
	 * 获得题库数据
	 * 
	 * @param model
	 * @param p
	 * @param twq
	 * @return
	 */
	@RequestMapping("/findQuestionData")
	public String findQuestionData(Model model, Integer p, TestWebQuestion twq) {
		if (p == null) {
			p = 1;
		}

		Page<TestWebQuestion> pageInfo = testWebQuestionService.findPageSelective(twq, p, 10);
		int pages = pageInfo.getPages();
		List<TestWebQuestion> lists = pageInfo.getResult();
		model.addAttribute("memberId", twq.getMemberId());
		model.addAttribute("pageNum", p);
		model.addAttribute("lists", lists);
		model.addAttribute("pages", pages);

		return "/testManager/geren_tikuData";
	}

	/**
	 * 获取老师题库(添加试卷试题)
	 * 
	 * @param response
	 * @param model
	 * @param p
	 * @param twq
	 * @return
	 */
	@RequestMapping("/findQuestionByMemberId")
	public String findQuestionBymemberId(HttpServletResponse response, HttpServletRequest request, Model model,
			Integer p, TestWebQuestion twq) {

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

		if (p == null) {
			p = 1;
		}
		String search = request.getParameter("search");// 搜索字段
		String gradeName = request.getParameter("gradeName");// 搜索字段
		String subjectName = request.getParameter("subjectName");// 搜索字段
		String testId = request.getParameter("testId");// 搜索字段
		String testTitle = request.getParameter("testTitle");// 搜索字段
		String totalScore = request.getParameter("totalScore");// 搜索字段
		if (search != null) {
			twq.setSearch(search);
		}
		if (gradeName != null && !gradeName.equals("选择年级") && !gradeName.equals("")) {
			twq.setGradeName(gradeName);
		}
		if (subjectName != null && !subjectName.equals("选择科目") && !subjectName.equals("")) {
			twq.setSubjectName(subjectName);
		}
		model.addAttribute("search", search);
		model.addAttribute("gradeName", gradeName);
		model.addAttribute("subjectName", subjectName);

		Page<TestWebQuestion> pageInfo = testWebQuestionService.findPageSelective(twq, p, 8);
		int pages = pageInfo.getPages();
		List<TestWebQuestion> lists = pageInfo.getResult();
		model.addAttribute("memberId", twq.getMemberId());
		model.addAttribute("pageNum", p);
		model.addAttribute("lists", lists);
		model.addAttribute("pages", pages);
		model.addAttribute("testId", testId);
		model.addAttribute("totalScore", totalScore);
		model.addAttribute("testTitle", testTitle);
		return "/testManager/geren_tiku1";
	}

	/**
	 * 进入添加题库题问题界面
	 * 
	 * @param response
	 * @param twq
	 */
	@RequestMapping("/addQuestion")
	public String addQuestion(Model model, TestWebQuestion twq) {

		// 获得年级
		SysDict dict = new SysDict();
		dict.setKeyname("G");
		List<SysDict> grades = sysDictService.findSelective(dict);
		model.addAttribute("grades", grades);

		// 获得科目 - 去重复
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

		return "/testManager/geren_tikuAdd";
	}

	/**
	 * 添加题库题问题
	 * 
	 * @param response
	 * @param twq
	 */
	@ResponseBody
	@RequestMapping("/saveQuestion")
	public Map<String, Object> saveQuestion(HttpServletResponse response, Model model, TestWebQuestion twq) {
		Map<String, Object> hm = new HashMap<>();

		String memberId = getUserId();// 获得登录用户id
		twq.setMemberId(memberId);
		twq.setIsDrop(0);
		twq.setAddTime(new Date());
		testWebQuestionService.insert(twq);

		// 如果是判断题 直接添加选项
		int questionType = twq.getQuestionType();
		if (questionType == 0) {
			TestWebQuestionAnswer answer = new TestWebQuestionAnswer();
			answer.setQuestionId(twq.getId());
			answer.setQuestionType(questionType);
			answer.setAnswerCode(1);
			answer.setAnswerText("正确");
			answer.setIsRight(0);
			testWebQuestionAnswerService.insert(answer);
			answer.setAnswerCode(2);
			answer.setAnswerText("错误");
			testWebQuestionAnswerService.insert(answer);
		}

		model.addAttribute("questionId", twq.getId());
		hm.put("questionId", twq.getId());
		hm.put("questionType", questionType);
		return hm;
	}

	/**
	 * 进入问题编辑题目页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editQuestion")
	public String editQuestion(Model model, String id) {

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

		// 获得版本
		dict.setKeyname("V");
		List<SysDict> version = sysDictService.findSelective(dict);
		model.addAttribute("version", version);

		TestWebQuestion twq = testWebQuestionService.findByKey(id);

		model.addAttribute("question", twq);
		return "/testManager/geren_tikuEdit";
	}

	/**
	 * 修改问题题目及基本信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateQuestion")
	public void updateQuestion(HttpServletResponse response, TestWebQuestion twq) {
		try {
			testWebQuestionService.updateByKeySelective(twq);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 批量删除
	 * 
	 * @param response
	 * @param del_id
	 */
	@RequestMapping("delBatch")
	public void deleteBatch(HttpServletResponse response, String[] del_id) {
		try {
			List<String> ids = Arrays.asList(del_id);
			testWebQuestionService.delBatchQuestion(ids);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 删除问题题目
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delQuestion")
	public void delQuestion(HttpServletResponse response, String id) {

		try {
			TestWebQuestionAnswer twqa = new TestWebQuestionAnswer();
			twqa.setQuestionId(id);
			// 删除问题下的答案选项
			testWebQuestionService.deleteByKey(id);
			testWebQuestionAnswerService.delByQuestionId(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 根据试卷获得下的试题(管理显示两用升序排列)
	 * 
	 * @param model
	 * @param twq
	 * @param p
	 * @return
	 */
	@RequestMapping("/findTestQuestion")
	public String findTestQuestion(Model model, TestWebQuestion twq, Integer p) {
		if (p == null)
			p = 1;
		Page<TestWebQuestion> pageInfo = testWebQuestionService.findTestQuestion(twq, p, 1);
		int pages = pageInfo.getPages();
		List<TestWebQuestion> lists = pageInfo.getResult();
		model.addAttribute("lists", lists);
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		return "";
	}

}
