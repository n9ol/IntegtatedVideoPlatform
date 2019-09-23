package com.zzrenfeng.zhsx.controller.test;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.TestWebQuestion;
import com.zzrenfeng.zhsx.model.TestWebQuestionAnswer;
import com.zzrenfeng.zhsx.model.TestWebStuVo;
import com.zzrenfeng.zhsx.service.TestWebQuestionAnswerService;
import com.zzrenfeng.zhsx.service.TestWebQuestionService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-07-05 15:29:26
 * @see com.zzrenfeng.zhsx.controller.TestWebQuestionAnswer
 */
@Controller
@RequestMapping(value = "/testwebquestionanswer")
public class TestWebQuestionAnswerController extends BaseController {

	@Resource
	private TestWebQuestionAnswerService testWebQuestionAnswerService;
	@Resource
	private TestWebQuestionService testWebQuestionService;

	/**
	 * 进入设置问题答案选项页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/editAnswer")
	public String editAnswer(HttpServletRequest request, Model model, TestWebQuestionAnswer twqa) {
		String questionId = request.getParameter("questionId");
		String questionType = request.getParameter("questionType");
		twqa.setQuestionId(questionId);
		List<TestWebQuestionAnswer> lists = testWebQuestionAnswerService.findSelective(twqa);
		model.addAttribute("lists", lists);
		model.addAttribute("questionId", questionId);
		model.addAttribute("questionType", Integer.parseInt(questionType));
		return "/testManager/geren_five";
	}

	/**
	 * 修改答案选项
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateAnswer")
	public void updateAnswer(HttpServletResponse response, TestWebQuestionAnswer twqa) {

		try {
			testWebQuestionAnswerService.updateByKeySelective(twqa);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 删除一个选项
	 * 
	 * @param response
	 * @param twqa
	 */
	@RequestMapping("/delAnswer")
	public void delAnswer(HttpServletResponse response, String id) {

		try {
			testWebQuestionAnswerService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 进入添加问题答案选项页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/saveAnswer")
	public String saveAnswer(HttpServletRequest request, Model model, TestWebQuestionAnswer twqa) {
		String questionId = request.getParameter("questionId");
		String questionType = request.getParameter("questionType");
		twqa.setQuestionId(questionId);
		List<TestWebQuestionAnswer> lists = testWebQuestionAnswerService.findSelective(twqa);
		model.addAttribute("lists", lists);
		model.addAttribute("questionId", questionId);
		model.addAttribute("questionType", Integer.parseInt(questionType));
		return "/testManager/geren_addfive";
	}

	/**
	 * 添加或更新
	 * 
	 * @param response
	 * @param twqa
	 */
	@RequestMapping("/addAnswer")
	public void addAnswer(HttpServletResponse response, TestWebQuestionAnswer twqa) {
		try {
			twqa.setIsRight(0);
			TestWebQuestionAnswer ta = new TestWebQuestionAnswer();
			ta.setQuestionId(twqa.getQuestionId());
			String id = twqa.getId();
			if (id != null && !id.equals("")) {
				testWebQuestionAnswerService.updateByKeySelective(twqa);
			} else {

				List<TestWebQuestionAnswer> lists = testWebQuestionAnswerService.findSelective(ta);
				int max = 0, min = 0;
				for (int i = 0; i < lists.size(); i++) {
					max = lists.get(i).getAnswerCode();
					min = lists.get(i).getAnswerCode();
					if (max >= min) {
						max = lists.get(i).getAnswerCode();
					}
				}
				int ansCode = max + 1;
				twqa.setAnswerCode(ansCode);
				testWebQuestionAnswerService.insert(twqa);
			}

			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

	/**
	 * 批量修改选项答案状态
	 * 
	 * @param response
	 * @param lists
	 */
	@RequestMapping("/update")
	public void update(HttpServletResponse response, TestWebStuVo vo, String[] isRight, String questionId) {
		try {

			List<TestWebQuestionAnswer> list = vo.getAnswerList();
			for (int i = 0; i < list.size(); i++) {
				if (isRight != null && isRight.length > 0) {

					for (int j = 0; j < isRight.length; j++) {
						if (list.get(i).getId().equals(isRight[j])) {
							list.get(i).setIsRight(1);
						}
					}
				}

				if (list.get(i).getIsRight() == null) {
					list.get(i).setIsRight(0);
				}
			}
			testWebQuestionAnswerService.updateBatch(list);

			// 修改试题的添加状态
			TestWebQuestion testwebquestion = new TestWebQuestion();
			testwebquestion.setId(questionId);
			testwebquestion.setIsDrop(1);
			testWebQuestionService.updateByKeySelective(testwebquestion);

			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

	@RequestMapping("/end")
	public String end(Model model) {
		String memberId = getUserId();
		model.addAttribute("memberId", memberId);
		return "/testManager/geren_finally";
	}

	/**
	 * 问题详细信息
	 */

	@RequestMapping("/QueAnswer")
	public String QueAnswer(HttpServletRequest request, Model model, TestWebQuestionAnswer twqa) {
		String questionId = request.getParameter("questionId");
		TestWebQuestion question = testWebQuestionService.findByKey(questionId);
		twqa.setQuestionId(questionId);
		List<TestWebQuestionAnswer> lists = testWebQuestionAnswerService.findSelective(twqa);
		String[] xuanxiang = { "A", "B", "C", "D", "E", "F", "G" };
		for (int i = 0; i < lists.size(); i++) {
			lists.get(i).setXuanxiang(xuanxiang[i]);

		}
		model.addAttribute("lists", lists);
		model.addAttribute("question", question);
		return "/testManager/geren_questionDetail";
	}

}
