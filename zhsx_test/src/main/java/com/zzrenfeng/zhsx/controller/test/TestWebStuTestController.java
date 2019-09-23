package com.zzrenfeng.zhsx.controller.test;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.TestWebStuTest;
import com.zzrenfeng.zhsx.service.TestWebQuestionAnswerService;
import com.zzrenfeng.zhsx.service.TestWebStuTestQuestionAnswerService;
import com.zzrenfeng.zhsx.service.TestWebStuTestQuestionService;
import com.zzrenfeng.zhsx.service.TestWebStuTestService;
import com.zzrenfeng.zhsx.service.TestWebTestService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-07-13 18:01:31
 * @see com.zzrenfeng.zhsx.controller.TestWebStuTest
 */
@Controller
@RequestMapping(value = "/testwebstutest")
public class TestWebStuTestController extends BaseController {

	@Resource
	private TestWebStuTestService testWebStuTestService;
	@Resource
	private TestWebStuTestQuestionService testWebStuTestQuestionService;
	@Resource
	private TestWebStuTestQuestionAnswerService testWebStuTestQuestionAnswerService;
	@Resource
	private TestWebTestService testWebTestService;
	@Resource
	private TestWebQuestionAnswerService testWebQuestionAnswerService;

	/**
	 * 获取老师的试卷答题情况(我的学员试卷管理)
	 * 
	 * @param model
	 * @param twst
	 * @param p
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model, TestWebStuTest twst, Integer p, HttpServletRequest request) {
		if (p == null)
			p = 1;

		// String memberId= twst.getMemberId();
		twst.setMemberId(getUserId());
		Page<TestWebStuTest> pageInfo = testWebStuTestService.findPageSelective(twst, p, 10);
		List<TestWebStuTest> lists = pageInfo.getResult();
		int pages = pageInfo.getPages();
		// model.addAttribute("memberId", memberId);
		model.addAttribute("lists", lists);
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("search", twst.getSearch());
		return "/testManager/geren_kaopingguanli";

	}

	// /**
	// * 查看学生详细作答（我的学员试卷管理）
	// *
	// * @param model
	// * @param id
	// * @return
	// */
	// @RequestMapping("/findOne")
	// public String findOne(Model model, String id) {
	// TestWebStuTest twt = testWebStuTestService.findByKey(id);
	// model.addAttribute("test", twt);
	// return "";
	// }

	/**
	 * 通过学生id获得最近成绩 柱状图
	 * 
	 * @param model
	 * @param twst
	 * @return
	 */
	@RequestMapping("/histogram")
	public String histogram(Model model, TestWebStuTest twst) {
		twst.setStudentId(getUserId());
		List<TestWebStuTest> lists = testWebStuTestService.histogram(twst);
		if (lists != null && lists.size() < 10) {
			for (int i = lists.size(); i < 10; i++) {
				TestWebStuTest tws = new TestWebStuTest();
				tws.setStudentId(twst.getStudentId());
				tws.setScores("0");
				tws.setTotalScores("0");
				lists.add(tws);
			}
		}
		model.addAttribute("lists", lists);
		return "/testManager/geren_kaoping";

	}

	/**
	 * 通过学生id获取学生当前的答题试卷(我的考卷管理)
	 * 
	 * @param model
	 * @param twst
	 * @param p
	 * @return
	 */
	@RequestMapping("/findStuTest")
	public String findStuTest(HttpServletRequest request, Model model, TestWebStuTest twst, Integer p) {

		if (p == null)
			p = 1;

		twst.setStudentId(getUserId());
		twst.setIsComm(1);
		Page<TestWebStuTest> pageInfo = testWebStuTestService.findPageSelective(twst, p, 10);
		List<TestWebStuTest> lists = pageInfo.getResult();
		int pages = pageInfo.getPages();
		model.addAttribute("memberId", getUserId());
		model.addAttribute("lists", lists);
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("search", twst.getSearch());
		return "/testManager/geren_kaojuan";
	}

	/**
	 * 删除测试 - 学生
	 * 
	 * @param id
	 */
	@RequestMapping("/delStuTest")
	public void delStuTest(HttpServletResponse response, @RequestParam String id) {
		try {
			testWebStuTestService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

}
