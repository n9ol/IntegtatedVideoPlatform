package com.zzrenfeng.zhsx.controller.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.TestWebQuestion;
import com.zzrenfeng.zhsx.model.TestWebQuestionAnswer;
import com.zzrenfeng.zhsx.model.TestWebStuTest;
import com.zzrenfeng.zhsx.model.TestWebStuTestQuestion;
import com.zzrenfeng.zhsx.model.TestWebStuTestQuestionAnswer;
import com.zzrenfeng.zhsx.model.TestWebTest;
import com.zzrenfeng.zhsx.model.TestWebTestQuestion;
import com.zzrenfeng.zhsx.service.TestWebQuestionAnswerService;
import com.zzrenfeng.zhsx.service.TestWebQuestionService;
import com.zzrenfeng.zhsx.service.TestWebStuTestQuestionAnswerService;
import com.zzrenfeng.zhsx.service.TestWebStuTestQuestionService;
import com.zzrenfeng.zhsx.service.TestWebStuTestService;
import com.zzrenfeng.zhsx.service.TestWebTestQuestionService;
import com.zzrenfeng.zhsx.service.TestWebTestService;
import com.zzrenfeng.zhsx.util.JsonUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-07-14 10:44:27
 * @see com.zzrenfeng.zhsx.controller.TestWebStuTestQuestionAnswer
 */
@Controller
@RequestMapping(value = "/testwebstutestquestionanswer")
public class TestWebStuTestQuestionAnswerController extends BaseController {

	@Resource
	private TestWebStuTestQuestionAnswerService testWebStuTestQuestionAnswerService;
	@Resource
	private TestWebStuTestQuestionService testWebStuTestQuestionService;
	@Resource
	private TestWebQuestionAnswerService testWebQuestionAnswerService;
	@Resource
	private TestWebTestService testWebTestService;
	@Resource
	private TestWebStuTestService testWebStuTestService;
	@Resource
	private TestWebQuestionService testWebQuestionService;
	@Resource
	private TestWebTestQuestionService testWebTestQuestionService;

	/**
	 * 我的错题库
	 * 
	 * @param model
	 * @param t
	 * @param p
	 * @return
	 */
	@RequestMapping("/findMyErrors")
	public String findMyErrors(HttpServletRequest request, Model model, TestWebStuTestQuestion t, Integer p) {
		if (p == null)
			p = 1;

		Integer questionNo = 0;
		// 获取错题
		t.setStuScore(0);
		t.setStudentId(getUserId());
		Page<TestWebStuTestQuestion> pageInfo = testWebStuTestQuestionService.findPageSelective(t, p, 4);
		int pages = pageInfo.getPages();
		List<TestWebStuTestQuestion> lists = pageInfo.getResult();
		int pageSize = pageInfo.getPageSize();
		long total = pageInfo.getTotal();

		model.addAttribute("pageSize", pageSize);
		model.addAttribute("total", total);
		// start
		for (int i = 0; i < lists.size(); i++) {
			// 获取题号
			questionNo = i + 1;
			lists.get(i).setQuestionNo(questionNo);
			// 根据questionId获取问题选项
			TestWebQuestionAnswer answer = new TestWebQuestionAnswer();
			answer.setQuestionId(lists.get(i).getQuestionId());
			List<TestWebQuestionAnswer> answerList = testWebQuestionAnswerService.findSelective(answer);
			lists.get(i).setAnswer(answerList);
			// 获取考生填写的答案
			TestWebStuTestQuestionAnswer stuAnswer = new TestWebStuTestQuestionAnswer();
			stuAnswer.setBak1(lists.get(i).getId());
			stuAnswer.setBak2("Y");
			List<TestWebStuTestQuestionAnswer> stuAnswerList = testWebStuTestQuestionAnswerService
					.findSelective(stuAnswer);

			lists.get(i).setStuAnswer(stuAnswerList);
		}
		// end
		model.addAttribute("lists", lists);
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("search", t.getSearch());

		return "/testManager/geren_couti";

	}

	/**
	 * 添加试卷
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping("/addTest")
	public Map<String, Object> addTest(HttpServletRequest request, Model model) {

		Map<String, Object> returnMap = new HashMap<String, Object>();
		Date date = new Date();
		String a = request.getParameter("answer");
		String qcont = request.getParameter("questionCon");// 总题数
		String testId = request.getParameter("testId");// 试卷id
		String stuId = getUserId();
		// 根据试卷id查询试卷
		TestWebTest test = testWebTestService.findByKey(testId);// 获取试卷详细信息
		// 添加考生试卷
		TestWebStuTest twst = new TestWebStuTest();
		twst.setStudentId(getUserId());
		twst.setTestId(testId);
		twst.setMemberId(test.getMemberId());
		twst.setTestTitle(test.getTestTitle());
		twst.setStuType(test.getStuType());
		;
		twst.setGradeName(test.getGradeName());
		twst.setSubjectName(test.getSubiectName());
		twst.setVersion(test.getVersion());
		twst.setVolume(test.getVolume());
		twst.setScores("0");
		twst.setTotalScores(test.getTotalScore());
		twst.setQuestions(test.getQuestions());
		twst.setTimes(test.getTimes());
		twst.setAddTime(new Date());
		twst.setIsComm(0);
		twst.setDoneTime(new Date());
		twst.setCommTime(new Date());
		testWebStuTestService.insert(twst);
		String stuTestId = twst.getId();
		// end

		int theScore = 0;
		List<LinkedHashMap<String, Object>> json = JsonUtils.json2List(a);// json转换成list

		if (json == null || json.size() < 1) {

			returnMap.put("rightNum", "0");
			returnMap.put("errorNum", "0");
			returnMap.put("questionCon", qcont);
			returnMap.put("notDoNum", qcont);
			returnMap.put("score", theScore);
			returnMap.put("rate", 0.00);// 正确率
			returnMap.put("msg", "未作答，考试成绩不做记录!");
			returnMap.put("success", "true");// 成功
			return returnMap;
		}
		String tId = json.get(0).get("tId") + "";// 试卷id
		int rightNum = 0;// 答对题数
		int errorNum = 0;// 答错题数
		for (LinkedHashMap<String, Object> lm : json) {
			String allOption = lm.get("allOption") + "";
			String questionAnswer = lm.get("questionAnswer") + "";
			String questionId = lm.get("questionId") + "";
			String score = lm.get("score") + "";
			String sortOrder = lm.get("sortOrder") + "";
			int sco = getScore(allOption, questionId, score);
			if (sco > 0) {
				rightNum++;
			} else {
				errorNum++;
			}
			theScore += sco;
			TestWebStuTestQuestion stuQue = new TestWebStuTestQuestion();// 学生试卷问题
			// 根据问题id查询问题详细信息
			TestWebQuestion que = testWebQuestionService.findByKey(questionId);
			stuQue.setQuestionId(questionId);
			stuQue.setStudentId(stuId);
			stuQue.setStuType(que.getStuType());
			stuQue.setQuestionType(que.getQuestionType());
			stuQue.setGradeName(que.getGradeName());
			stuQue.setSubjectName(que.getSubjectName());
			stuQue.setVersion(que.getVersion());
			stuQue.setVolume(que.getVolume());
			stuQue.setQuestionText(que.getQuestionText());
			stuQue.setSortCode(Integer.parseInt(sortOrder));
			stuQue.setVolume(que.getVolume());
			stuQue.setGradeName(que.getGradeName());
			stuQue.setScore(Integer.parseInt(score));
			stuQue.setStuScore(sco);
			stuQue.setCommTime(date);
			stuQue.setTestId(stuTestId);// 插入学生作答试卷表id
			testWebStuTestQuestionService.insert(stuQue);// 插入学生问题表
			String stuQueId = stuQue.getId();// 获得插入后学生试卷问题表主键
			// 获取问题选项答案表
			TestWebQuestionAnswer twta = new TestWebQuestionAnswer();
			twta.setQuestionId(questionId);
			List<TestWebQuestionAnswer> AnswerList = testWebQuestionAnswerService.findSelective(twta);
			List<TestWebStuTestQuestionAnswer> listQa = new ArrayList<TestWebStuTestQuestionAnswer>();
			for (int i = 0; i < AnswerList.size(); i++) {
				TestWebStuTestQuestionAnswer tanswer = new TestWebStuTestQuestionAnswer();
				tanswer.setStuTestId(stuTestId);
				tanswer.setStuId(getUserId());
				tanswer.setQuestionId(questionId);
				tanswer.setAnswerId(AnswerList.get(i).getId());
				tanswer.setIsRight(AnswerList.get(i).getIsRight());
				tanswer.setStuIsRight(0);
				tanswer.setBak1(stuQueId);
				tanswer.setBak2("N");
				String[] str = allOption.split(";");
				// 修改选的选项为Y
				String answerId = AnswerList.get(i).getId();
				for (int j = 0; j < str.length; j++) {

					if (str[j].equals(answerId)) {
						tanswer.setBak2("Y");
					}
				}

				listQa.add(tanswer);

			}
			// 插入学生答案表
			testWebStuTestQuestionAnswerService.insertBetch(listQa);
		}
		// 修改学生试卷得分
		TestWebStuTest tt = new TestWebStuTest();
		tt.setId(stuTestId);
		tt.setScores(String.valueOf(theScore));
		tt.setIsComm(1);
		tt.setCommTime(new Date());
		tt.setDoneTime(new Date());
		testWebStuTestService.updateByKeySelective(tt);
		// end

		returnMap.put("errorNum", errorNum);
		returnMap.put("rightNum", rightNum);
		returnMap.put("questionCon", qcont);
		// BigDecimal b = new BigDecimal(
		// (double)rightNum/((double)rightNum+(double)errorNum));
		// double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		// returnMap.put("rate", f1*100);//正确率
		returnMap.put("notDoNum", Integer.parseInt(qcont) - errorNum - rightNum);// 未答题数
		returnMap.put("score", theScore);
		returnMap.put("msg", "考试成绩");
		returnMap.put("success", "true");// 成功
		returnMap.put("testId", testId);// 成功

		return returnMap;
	}

	// 分数
	public int getScore(String allOption, String questionId, String score) {

		int j = 0;

		if (StringUtils.isEmpty(allOption))
			return j;
		TestWebQuestionAnswer an = new TestWebQuestionAnswer();
		an.setQuestionId(questionId);
		an.setIsRight(1);
		List<TestWebQuestionAnswer> list = testWebQuestionAnswerService.findSelective(an);
		String[] str = allOption.split(";");
		String[] str1 = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			String sid = list.get(i).getId();
			str1[i] = sid;
		}
		Arrays.sort(str);
		Arrays.sort(str1);
		if (Arrays.equals(str, str1)) {
			try {
				j = Integer.parseInt(score);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		return j;

	}

	@RequestMapping("/findOne")
	public String findOne(HttpServletRequest request, String stuTestId, String testId, Model model, Integer p) {
		if (p == null)
			p = 1;
		// 查询考试试卷详细信息
		TestWebStuTest stuTest = testWebStuTestService.findByKey(stuTestId);
		model.addAttribute("stuTest", stuTest);
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
			// 获取学生作答题分值
			TestWebStuTestQuestion stuQue = new TestWebStuTestQuestion();
			String questionId = twqL.get(i).getQuestionId();
			stuQue.setQuestionId(questionId);
			stuQue.setTestId(stuTestId);
			TestWebStuTestQuestion tq = testWebStuTestQuestionService.findOneByqId(stuQue);
			twqL.get(i).setStuTestQue(tq);

			if (tq != null) {
				// 获得学生答题情况
				TestWebStuTestQuestionAnswer stuAns = new TestWebStuTestQuestionAnswer();
				stuAns.setStuTestId(stuTestId);
				stuAns.setBak1(tq.getId());
				stuAns.setBak2("Y");
				List<TestWebStuTestQuestionAnswer> stuAnsL = testWebStuTestQuestionAnswerService.findSelective(stuAns);
				twqL.get(i).setStuAnswer(stuAnsL);
			}

		}
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", twqL);
		model.addAttribute("stuTestId", stuTestId);
		model.addAttribute("testId", testId);
		return "/testManager/geren_test";

	}

}
