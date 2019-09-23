package com.zzrenfeng.zhsx.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.ShiroUser;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.model.TestWebQuestionAnswer;
import com.zzrenfeng.zhsx.model.TestWebTest;
import com.zzrenfeng.zhsx.model.TestWebTestQuestion;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.SysHistoryService;
import com.zzrenfeng.zhsx.service.TestWebQuestionAnswerService;
import com.zzrenfeng.zhsx.service.TestWebStuTestService;
import com.zzrenfeng.zhsx.service.TestWebTestQuestionService;
import com.zzrenfeng.zhsx.service.TestWebTestService;
import com.zzrenfeng.zhsx.util.JsonUtil;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-07-13 14:53:43
 * @see com.zzrenfeng.zhsx.controller.TestWebTestQuestion
 */
@Controller
@RequestMapping(value = "/testwebtestquestion")
public class TestWebTestQuestionController extends BaseController {

	@Resource
	private TestWebTestQuestionService testWebTestQuestionService;
	@Resource
	private TestWebQuestionAnswerService testWebQuestionAnswerService;
	@Resource
	private TestWebTestService testWebTestService;// 试卷service;
	@Resource
	private TestWebStuTestService TestWebStuTestService;

	@Resource
	private SysHistoryService sysHistoryService;

	/**
	 * 验证角色是否是学生
	 * 
	 * @param response
	 * @param request
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/verificationtType")
	public void testpa(HttpServletResponse response, HttpServletRequest request) {
		try {
			ShiroUser u = getShiroUser();
			if (!getUserType().equals(User.userType_students)) {
				WriterUtils.toHtml(response, MessageUtils.SUCCESS);
			} else {
				WriterUtils.toHtml(response, MessageUtils.FAilURE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

	@RequestMapping("/getTestpaper")
	public String getTestpaper(HttpServletRequest request, Model model, TestWebTestQuestion tq,
			HttpServletResponse response) {

		String type = request.getParameter("type");
		if (type == null) {
			type = "";
		}
		String testId = request.getParameter("testId");
		String testTime = request.getParameter("times");

		List<TestWebTestQuestion> tqLists = null;
		StringBuffer jsonstr = null;
		// 修改浏览次数
		TestWebTest tt = testWebTestService.findByKey(testId);
		if (tt != null) {

			tt.setViews(tt.getViews() + 1);
			testWebTestService.updateByKeySelective(tt);

			tq.setTestId(testId);
			tqLists = testWebTestQuestionService.findSelective(tq);
			// 选项
			TestWebQuestionAnswer answer = new TestWebQuestionAnswer();
			jsonstr = new StringBuffer();
			if (tqLists != null && tqLists.size() > 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (TestWebTestQuestion t : tqLists) {
					SysHistory sh = new SysHistory();
					String id = t.getQuestionId();
					sh.setPubId(id);
					sh.setUserId(getUserId());
					sh.setPubType("Q");
					sh.setPubFlag("C");
					List<SysHistory> history = sysHistoryService.findSelective(sh);
					String his = "";
					if (history != null && history.size() > 0) {
						his = "Y";
					}
					String tId = t.getTestId();// 试卷id
					String qtype = t.getQuestionType();// 问题类型
					String questionId = t.getQuestionId();// 问题ID
					String questionTitle = t.getQuestionTitle(); // 问题标题
					String sortOrder = String.valueOf(t.getSortCode());// 题排序
					String questionItems = "";
					StringBuffer sb = new StringBuffer();
					// 获得问题选项
					answer.setQuestionId(questionId);
					List<TestWebQuestionAnswer> toLists = testWebQuestionAnswerService.findSelective(answer);
					Map<String, String> opt = new HashMap<String, String>();
					for (TestWebQuestionAnswer testOption : toLists) {
						sb.append(testOption.getAnswerText() + ";");
						opt.put(testOption.getId(), testOption.getAnswerText());
					}
					if (sb.length() > 1)
						sb.deleteCharAt(sb.length() - 1);
					questionItems = sb.toString();
					map.put("tId", tId);// 试卷id
					map.put("qtype", qtype);// 试卷类型
					map.put("questionId", questionId);// 问题id
					map.put("questionTitle", questionTitle);// 问题标题
					map.put("questionItems", questionItems);// 选项
					map.put("options", opt);
					map.put("score", t.getScores());// 每题分数
					map.put("sortOrder", sortOrder);
					map.put("his", his);
					jsonstr.append(JsonUtil.mapJSON(map) + ",");

				}

				if (jsonstr.length() > 1) {
					jsonstr.deleteCharAt(jsonstr.length() - 1);
				}
				model.addAttribute("questionCon", tqLists.size());// 共几题

			}

		}

		model.addAttribute("tId", testId);// 共几题
		model.addAttribute("times", testTime);// 时间
		if (jsonstr != null) {
			model.addAttribute("jsonList", "[" + jsonstr.toString() + "]");
		}
		return "/test/exam_online";
		// if(type.equals("Y")){
		// return "/test/geren_exam_online";
		// }else{
		// return "/test/exam_online";
		// }
	}

	/**
	 * 删除试卷试题
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/del")
	public void del(HttpServletResponse response, String id) {
		try {
			testWebTestQuestionService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

	/**
	 * 修改排序，分值
	 * 
	 * @param response
	 * @param ttq
	 */
	@RequestMapping("/update")
	public void update(HttpServletResponse response, TestWebTestQuestion ttq) {
		try {
			testWebTestQuestionService.updateByKeySelective(ttq);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

	/**
	 * 为卷子添加试题(添加过判断)（单个添加）
	 * 
	 * @param response
	 * @param ttq
	 */
	@RequestMapping("/add")
	public void add(HttpServletResponse response, TestWebTestQuestion ttq) {
		try {
			// 查询该题是否添加过
			TestWebTestQuestion tt = new TestWebTestQuestion();
			tt.setTestId(ttq.getTestId());
			tt.setQuestionId(ttq.getQuestionId());
			List<TestWebTestQuestion> lis = testWebTestQuestionService.findSelective(tt);
			if (lis != null && lis.size() > 0) {
				String msg = "该题已经存在！请不要重复添加！";
				WriterUtils.toHtml(response, msg);
			} else {
				// 查询题序
				TestWebTestQuestion t = new TestWebTestQuestion();
				t.setTestId(ttq.getTestId());
				List<TestWebTestQuestion> li = testWebTestQuestionService.findSelective(t);
				int max = 0, min = 0;
				for (int i = 0; i < li.size(); i++) {

					max = li.get(i).getSortCode();
					min = li.get(i).getSortCode();
					if (max >= min) {
						max = li.get(i).getSortCode();
					}
				}
				int sortCode = max + 1;

				ttq.setSortCode(sortCode);
				ttq.setScores("0");
				testWebTestQuestionService.insert(ttq);
				WriterUtils.toHtml(response, MessageUtils.SUCCESS);

			}
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

	/**
	 * 为卷子添加试题(添加过判断)（批量添加）
	 * 
	 * @param response
	 * @param ttq
	 */
	@RequestMapping("/batchAdd")
	public void batchAdd(HttpServletResponse response, String[] questionId, String[] testId) {
		try {
			// 查询该题是否添加过
			List<TestWebTestQuestion> list = new ArrayList<TestWebTestQuestion>();
			String test = "";
			for (int i = 0; i < testId.length; i++) {
				test = testId[i];
			}
			List<TestWebTestQuestion> lis = new ArrayList<TestWebTestQuestion>();
			for (int i = 0; i < questionId.length; i++) {
				TestWebTestQuestion tt = new TestWebTestQuestion();
				tt.setTestId(test);
				tt.setQuestionId(questionId[i]);
				lis = testWebTestQuestionService.findSelective(tt);

			}
			if (lis != null && lis.size() > 0) {
				String msg = "该题已经存在！请不要重复添加！";
				WriterUtils.toHtml(response, msg);
			} else {
				// 查询题序
				TestWebTestQuestion t = new TestWebTestQuestion();
				t.setTestId(test);
				List<TestWebTestQuestion> li = testWebTestQuestionService.findSelective(t);
				int max = 0, min = 0;
				for (int i = 0; i < li.size(); i++) {

					max = li.get(i).getSortCode();
					min = li.get(i).getSortCode();
					if (max >= min) {
						max = li.get(i).getSortCode();
					}
				}
				int sortCode = max + 1;// 目前需要插入的题号
				for (int i = 0; i < questionId.length; i++) {
					TestWebTestQuestion tt = new TestWebTestQuestion();
					tt.setTestId(test);
					tt.setScores("0");
					tt.setQuestionId(questionId[i]);
					lis = testWebTestQuestionService.findSelective(tt);
					tt.setSortCode(sortCode + i);
					;
					list.add(tt);
				}

				testWebTestQuestionService.batchAdd(list);
				WriterUtils.toHtml(response, MessageUtils.SUCCESS);

			}
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

}
