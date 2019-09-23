package com.zzrenfeng.zhsx.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.zzrenfeng.zhsx.model.TestWebQuestion;
import com.zzrenfeng.zhsx.model.TestWebQuestionAnswer;
import com.zzrenfeng.zhsx.model.TestWebTest;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.TestWebQuestionAnswerService;
import com.zzrenfeng.zhsx.service.TestWebQuestionService;
import com.zzrenfeng.zhsx.service.TestWebTestService;
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
@RequestMapping(value = "/admintestquestion")
public class AdminTestWebQuestionController extends BaseController {

	@Resource
	private TestWebQuestionService testWebQuestionService;
	@Resource
	private TestWebTestService testWebTestService;
	@Resource
	private TestWebQuestionAnswerService testWebQuestionAnswer;
	@Resource
	private SysDictService sysDictService;

	/**
	 * 进入试卷管理
	 * 
	 * @param request
	 * @param que
	 * @param p
	 * @return
	 */
	@RequestMapping("/testpaper")
	public String list(HttpServletRequest request, TestWebTest twt, Integer p, Model model) {
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

		String search = request.getParameter("search");
		String gradeName = request.getParameter("gradeNam");// 搜索字段
		String subjectName = request.getParameter("subjectName");// 搜索字段
		String volume = request.getParameter("volume");// 搜索字段
		String addTim = request.getParameter("addTim");// 搜索字段
		String lockTim = request.getParameter("lockTime");// 搜索字段
		// DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (volume == null) {
			volume = "";
		}
		if (search != null && search != "") {
			twt.setSearch(search);
		}

		if (gradeName != null && gradeName != "" && !gradeName.equals("选择年级")) {
			twt.setGradeName(gradeName);
		}

		if (subjectName != null && !subjectName.equals("")) {
			twt.setSubiectName(subjectName);
		}
		if (volume != null && !volume.equals("")) {
			twt.setVolume(Integer.parseInt(volume));
		}
		if (addTim != null && !addTim.equals("")) {
			twt.setAddTim(addTim);
		}
		if (lockTim != null && !lockTim.equals("")) {
			twt.setLockTim(lockTim);
		}

		if (p == null)
			p = 1;

		Page<TestWebTest> pageInfo = testWebTestService.findPageSelective(twt, p, 12);
		int pages = pageInfo.getPages();
		List<TestWebTest> lists = pageInfo.getResult();
		Date date = new Date();
		for (int i = 0; i < lists.size(); i++) {
			if (date.getTime() > lists.get(i).getLockTime().getTime()) {
				lists.get(i).setLock("Y");
			} else {
				lists.get(i).setLock("");
			}
			if (lists.get(i).getPublicType() == null) {
				lists.get(i).setPublicType("");
			}
		}

		model.addAttribute("search", search);
		model.addAttribute("gradeName", gradeName);
		model.addAttribute("subjectName", subjectName);
		model.addAttribute("volume", volume);
		model.addAttribute("addTime", addTim);
		model.addAttribute("lockTime", lockTim);
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		return "/admin/test/testpaper";

	}

	@RequestMapping("/delTest")
	public void del(HttpServletResponse response, String id) {

		try {
			testWebTestService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

	@RequestMapping("delBatchTest")
	public void deleteBatch(HttpServletResponse response, String[] del_id) {
		try {
			List<String> ids = Arrays.asList(del_id);
			testWebTestService.delBatchTest(ids);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入考题管理
	 * 
	 * @param request
	 * @param que
	 * @param p
	 * @return
	 */
	@RequestMapping("/testquestion")
	public String testquestion(HttpServletRequest request, TestWebQuestion twq, Integer p, Model model) {
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

		String search = request.getParameter("search");
		String gradeName = request.getParameter("grade");// 搜索字段
		String subjectName = request.getParameter("subjectName");// 搜索字段
		String volume = request.getParameter("volume");// 搜索字段
		String questionType = request.getParameter("questionType");// 搜索字段
		if (volume == null) {
			volume = "";
		}
		if (questionType == null) {
			questionType = "";
		}
		if (search != null && search != "") {
			twq.setSearch(search);
		}

		if (gradeName != null && gradeName != "" && !gradeName.equals("选择年级")) {
			twq.setGradeName(gradeName);
		}

		if (subjectName != null && !subjectName.equals("")) {
			twq.setSubjectName(subjectName);
		}
		if (volume != null && !volume.equals("")) {
			twq.setVolume(Integer.parseInt(volume));
		}
		if (questionType != null && !questionType.equals("")) {
			twq.setQuestionType(Integer.valueOf(questionType));
		}

		if (p == null)
			p = 1;

		Page<TestWebQuestion> pageInfo = testWebQuestionService.findPageSelective(twq, p, 12);
		int pages = pageInfo.getPages();
		List<TestWebQuestion> lists = pageInfo.getResult();
		String[] arr = { "A", "B", "C", "D", "E", "F" };
		for (int i = 0; i < lists.size(); i++) {
			TestWebQuestionAnswer twqa = new TestWebQuestionAnswer();
			twqa.setQuestionId(lists.get(i).getId());
			List<TestWebQuestionAnswer> answers = testWebQuestionAnswer.findSelective(twqa);
			for (int j = 0; j < answers.size(); j++) {
				answers.get(j).setXuanxiang(arr[j]);

			}
			lists.get(i).setAnswers(answers);
		}
		model.addAttribute("search", search);
		model.addAttribute("gradeName", gradeName);
		model.addAttribute("subjectName", subjectName);
		model.addAttribute("volume", volume);
		model.addAttribute("questionType", questionType);
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);

		return "/admin/test/testquestion";

	}

	@RequestMapping("/delQuestion")
	public void delQuestion(HttpServletResponse response, String id) {

		try {
			testWebQuestionService.deleteByKey(id);
			testWebQuestionAnswer.delByQuestionId(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}

	@RequestMapping("delBatchQuestion")
	public void delBatchQuestion(HttpServletResponse response, String[] del_id) {
		try {
			List<String> ids = Arrays.asList(del_id);
			testWebQuestionService.delBatchQuestion(ids);
			testWebQuestionAnswer.delBatch(ids);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

}
