package com.zzrenfeng.zhsx.controller.admin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.LoClassTime;
import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.TpPractice;
import com.zzrenfeng.zhsx.model.TpPracticeLibrary;
import com.zzrenfeng.zhsx.model.TpPracticeOption;
import com.zzrenfeng.zhsx.model.TpPracticeQuestion;
import com.zzrenfeng.zhsx.model.TpPracticeResult;
import com.zzrenfeng.zhsx.model.TpPracticeSchedule;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.model.WebQuestionnaireLibrary;
import com.zzrenfeng.zhsx.service.LoClassTimeService;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.LoTermTimeService;
import com.zzrenfeng.zhsx.service.SysClassroomService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.TestWebQuestionAnswerService;
import com.zzrenfeng.zhsx.service.TestWebQuestionService;
import com.zzrenfeng.zhsx.service.TestWebTestService;
import com.zzrenfeng.zhsx.service.TpPracticeExecService;
import com.zzrenfeng.zhsx.service.TpPracticeLibraryService;
import com.zzrenfeng.zhsx.service.TpPracticeOptionService;
import com.zzrenfeng.zhsx.service.TpPracticeQuestionService;
import com.zzrenfeng.zhsx.service.TpPracticeResultService;
import com.zzrenfeng.zhsx.service.TpPracticeScheduleService;
import com.zzrenfeng.zhsx.service.TpPracticeService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.util.DateUtil;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * TODO 在此加入类描述 触控板随堂练习
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 */
@Controller
@RequestMapping(value = "/adminPractice")
public class AdminPracticeController extends BaseController {

	@Resource
	private TestWebQuestionService testWebQuestionService;
	@Resource
	private TestWebTestService testWebTestService;
	@Resource
	private TestWebQuestionAnswerService testWebQuestionAnswer;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private TpPracticeService tpPracticeService;
	@Resource
	private TpPracticeExecService tpPracticeExecService;
	@Resource
	private TpPracticeLibraryService tpPracticeLibraryService;
	@Resource
	private TpPracticeOptionService tpPracticeOptionService;
	@Resource
	private TpPracticeQuestionService tpPracticeQuestionService;
	@Resource
	private UserService userService;
	@Resource
	private SysClassroomService sysClassroomService;
	@Resource
	private SysSchoolService sysSchoolService;
	@Resource
	private LoTermTimeService loTermTimeService;
	@Resource
	private LoScheduleService loScheduleService;
	@Resource
	private LoClassTimeService loClassTimeService;
	@Resource
	private TpPracticeScheduleService tpPracticeScheduleService;
	@Resource
	private TpPracticeResultService tpPracticeResultService;

	/**
	 * 进入练习试卷列表
	 * 
	 * @return
	 */
	@RequestMapping("/practiceList")
	public String list(HttpServletRequest request, TpPractice tp, Integer p, Model model) {
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

		String gradeName = request.getParameter("gradeNam");// 搜索字段
		String subjectName = request.getParameter("subjectName");// 搜索字段

		if (p == null)
			p = 1;
		Page<TpPractice> pageInfo = tpPracticeService.findPageSelective(tp, p, 10);
		int pages = pageInfo.getPages();
		List<TpPractice> lists = pageInfo.getResult();

		model.addAttribute("wq", tp);
		model.addAttribute("gradeName", gradeName);
		model.addAttribute("subjectName", subjectName);
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		return "/admin/prac/practiceList";
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPractice")
	public String addPractice(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, TpPractice wq) {
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
		return "/admin/prac/addPractice";
	}

	/**
	 * 保存
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/addPracticeData")
	public void addPracticeData(HttpServletResponse response, TpPractice wq) {
		try {
			wq.setCreateId(getUserId());
			wq.setCreateTime(new Date());
			wq.setIsShown("N");
			tpPracticeService.insert(wq);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 编辑练习
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param p
	 * @param wq
	 * @return
	 */
	@RequestMapping(value = "/practiceEdu")
	public String practiceEdu(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String id) {

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
		TpPractice ques = tpPracticeService.findByKey(id);
		model.addAttribute("testQues", ques);
		return "/admin/prac/practiceEdu";
	}

	/**
	 * 编辑保存
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/practiceEduSave")
	public void practiceEduSave(HttpServletResponse response, TpPractice wq) {
		try {
			tpPracticeService.updateByKeySelective(wq);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 调查问卷管理---问题列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param p
	 * @param qid
	 * @return
	 */
	@RequestMapping(value = "/findAllQues")
	public String findAllQues(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String pid, TpPracticeQuestion wq) {
		if (p == null) {
			p = 1;
		}
		wq.setPid(pid);
		Page<TpPracticeQuestion> pageInfo = tpPracticeQuestionService.findPageSelective(wq, p, 10);
		int pages = pageInfo.getPages();
		List<TpPracticeQuestion> lists = pageInfo.getResult();
		for (TpPracticeQuestion webQuestionnaireQuestion : lists) {
			String op = webQuestionnaireQuestion.getOptions();
			String op2 = op.replaceAll("<br/>+", "");
			webQuestionnaireQuestion.setBak(op2.toString());
		}

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("pid", pid);
		model.addAttribute("wq", wq);
		return "/admin/prac/questionList";
	}

	/**
	 * 删除
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/delPractice")
	public void delPractice(HttpServletResponse response, String qid) {
		try {
			tpPracticeService.deleteByKey(qid);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 问卷管理添加问题列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param p
	 * @param pid
	 *            问卷id
	 * @return
	 */
	@RequestMapping(value = "/practiceLibrary2")
	public String practiceLibrary2(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String pid, TpPracticeLibrary wql) {
		if (p == null) {
			p = 1;
		}
		Page<TpPracticeLibrary> pageInfo = tpPracticeLibraryService.findPageSelective(wql, p, 10);
		int pages = pageInfo.getPages();
		List<TpPracticeLibrary> lists = pageInfo.getResult();
		for (TpPracticeLibrary webQuestionnaireLibrary : lists) {
			StringBuffer op = new StringBuffer();
			StringBuffer op2 = new StringBuffer();
			StringBuffer rightOpt = new StringBuffer();
			TpPracticeOption wq = new TpPracticeOption();
			wq.setQid(webQuestionnaireLibrary.getId());
			List<TpPracticeOption> opt = tpPracticeOptionService.findSelective(wq);
			if("C".equals(webQuestionnaireLibrary.getType())){
				for (int i = 0; opt != null && i < opt.size(); i++) {
					char a = (char) (i + 65);
					if("正确".equals(opt.get(i).getOption())){
						op.append("A." + opt.get(i).getOption() + "<br/>");
						op2.append("A." + opt.get(i).getOption());
					}
					if("错误".equals(opt.get(i).getOption())){
						op.append("B." + opt.get(i).getOption() + "<br/>");
						op2.append("B." + opt.get(i).getOption());
					}

					if ("Y".equals(opt.get(i).getIsRight())) {
						rightOpt.append(a + ",");
					}
				}
			}else{
				for (int i = 0; opt != null && i < opt.size(); i++) {
					char a = (char) (i + 65);
					op.append(a + "." + opt.get(i).getOption() + "<br/>");
					op2.append(a + "." + opt.get(i).getOption());

					if ("Y".equals(opt.get(i).getIsRight())) {
						rightOpt.append(a + ",");
					}
				}
			}
			
			webQuestionnaireLibrary.setOption(op.toString());
			webQuestionnaireLibrary.setBak(op2.toString());

			String str = rightOpt.toString();
			if (str.length() > 0) {
				webQuestionnaireLibrary.setRights(str.substring(0, (str.length() - 1)));
			}
		}

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("pid", pid);
		model.addAttribute("wq", wql);
		return "/admin/prac/practiceLibrary2";
	}

	/**
	 * 批量保存问题
	 * 
	 * @param response
	 * @param del_id
	 */
	@RequestMapping("saveBatchQuestion")
	public void saveBatchQuestion(HttpServletResponse response, String[] lid, String pid) {
		try {
			List<String> lidslist = Arrays.asList(lid);
			tpPracticeQuestionService.saveBatBylId(lidslist, pid, getUserId());

			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}
	/**
	 * 移除问题
	 * 
	 * @param response
	 * @param del_id
	 */
	@RequestMapping("deleteQues")
	public void deleteQues(HttpServletResponse response, String id) {
		try {
			tpPracticeQuestionService.deleteByKey(id);

			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}
	
	// ------------------------------问题管理------------------------------------

	/**
	 * 问题列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param p
	 * @param wq
	 * @return
	 */
	@RequestMapping(value = "/practiceLibrary")
	public String practiceLibrary(HttpServletRequest request, Model model, Integer p, TpPracticeLibrary wq) {
		if (p == null) {
			p = 1;
		}

		Page<TpPracticeLibrary> pageInfo = tpPracticeLibraryService.findPageSelective(wq, p, 10);
		int pages = pageInfo.getPages();
		List<TpPracticeLibrary> lists = pageInfo.getResult();

		for (TpPracticeLibrary webQuestionnaireLibrary : lists) {
			StringBuffer op = new StringBuffer();
			StringBuffer op2 = new StringBuffer();
			StringBuffer rightOpt = new StringBuffer();
			TpPracticeOption wq0 = new TpPracticeOption();
			wq0.setQid(webQuestionnaireLibrary.getId());
			List<TpPracticeOption> opt = tpPracticeOptionService.findSelective(wq0);
			
			if("C".equals(webQuestionnaireLibrary.getType())){
				for (int i = 0; opt != null && i < opt.size(); i++) {
					char a = (char) (i + 65);
					if("正确".equals(opt.get(i).getOption())){
						op.append("A." + opt.get(i).getOption() + "<br/>");
						op2.append("A." + opt.get(i).getOption());
					}
					if("错误".equals(opt.get(i).getOption())){
						op.append("B." + opt.get(i).getOption() + "<br/>");
						op2.append("B." + opt.get(i).getOption());
					}

					if ("Y".equals(opt.get(i).getIsRight())) {
						rightOpt.append(a + ",");
					}
				}
			}else{
				for (int i = 0; opt != null && i < opt.size(); i++) {
					char a = (char) (i + 65);
					op.append(a + "." + opt.get(i).getOption() + "<br/>");
					op2.append(a + "." + opt.get(i).getOption());
					
					if ("Y".equals(opt.get(i).getIsRight())) {
						rightOpt.append(a + ",");
					}
				}
			}
			
			webQuestionnaireLibrary.setOption(op.toString());
			webQuestionnaireLibrary.setBak(op2.toString());
			String str = rightOpt.toString();
			if (str.length() > 0) {
				webQuestionnaireLibrary.setRights(str.substring(0, (str.length() - 1)));
			}
		}

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("wq", wq);
		return "/admin/prac/practiceLibrary";
	}

	/**
	 * 添加问题页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param p
	 * @param wq
	 * @return
	 */
	@RequestMapping(value = "/addQuestion")
	public String addQuestion(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, WebQuestionnaireLibrary wq) {
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
		return "/admin/prac/addQuestion";
	}

	/**
	 * 保存问题
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/addQuestionData")
	public void addQuestionData(HttpServletResponse response, TpPracticeLibrary wq) {
		try {
			wq.setCreateId(getUserId());
			wq.setCreateTime(new Date());
			tpPracticeLibraryService.insert(wq);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 问题管理删除问题
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/deleteLibraryQues")
	public void deleteLibraryQues(HttpServletResponse response, String id) {
		try {
			tpPracticeLibraryService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 问题管理批量删除问题
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/delBatchLibrary")
	public void delBatchLibrary(HttpServletResponse response, String[] del_id) {
		try {
			List<String> ids = Arrays.asList(del_id);
			tpPracticeLibraryService.delBatchLibrary(ids);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 编辑问题页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param p
	 * @param wq
	 * @return
	 */
	@RequestMapping(value = "/eduQuestion")
	public String eduQuestion(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String id) {
		// if (p == null) {
		// p = 1;
		// }

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
		TpPracticeLibrary ques = tpPracticeLibraryService.findByKey(id);
		model.addAttribute("testQues", ques);
		return "/admin/prac/eduQuestion";
	}

	/**
	 * 编辑保存问题
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/eduQuestionData")
	public void eduQuestionData(HttpServletResponse response, TpPracticeLibrary wq) {
		try {
			tpPracticeLibraryService.updateByKeySelective(wq);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	// ------------------------------选项管理-------------------------------------
	/**
	 * 进入选项管理页(判断题)
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param p
	 * @param qid
	 * @return
	 */
	@RequestMapping(value = "/findAllOptionC")
	public String findAllOptionC(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String qid) {
		if (p == null) {
			p = 1;
		}
		TpPracticeOption wq = new TpPracticeOption();
		wq.setQid(qid);
		Page<TpPracticeOption> pageInfo = tpPracticeOptionService.findPageSelective(wq, p, 10);
		int pages = pageInfo.getPages();
		List<TpPracticeOption> lists = pageInfo.getResult();

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("qid", qid);
		return "/admin/prac/optionListC";
	}
	
	
	/**
	 * 进入选项管理页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param p
	 * @param qid
	 * @return
	 */
	@RequestMapping(value = "/findAllOption")
	public String findAllOption(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String qid) {
		if (p == null) {
			p = 1;
		}
		TpPracticeOption wq = new TpPracticeOption();
		wq.setQid(qid);
		Page<TpPracticeOption> pageInfo = tpPracticeOptionService.findPageSelective(wq, p, 10);
		int pages = pageInfo.getPages();
		List<TpPracticeOption> lists = pageInfo.getResult();

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("qid", qid);
		return "/admin/prac/optionList";
	}

	/**
	 * 添加选项页
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param p
	 * @param wq
	 * @return
	 */
	@RequestMapping(value = "/addOption")
	public String addOption(HttpServletRequest request, HttpServletResponse response, Model model, Integer optSum, String qid) {

		model.addAttribute("qid", qid);
		model.addAttribute("optSum", optSum);
		return "/admin/prac/addOption";
	}

	/**
	 * 保存问题选项
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/addOptionData")
	public void addOptionData(HttpServletResponse response, TpPracticeOption wq) {
		try {
			wq.setCreateId(getUserId());
			wq.setCreateTime(new Date());
			wq.setSort("0");
			tpPracticeOptionService.insert(wq);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 编辑选项页
	 */
	@RequestMapping(value = "/eduOption")
	public String eduOption(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String qid, String id) {
		// if (p == null) {
		// p = 1;
		// }
		TpPracticeOption opt = tpPracticeOptionService.findByKey(id);
		model.addAttribute("qid", qid);
		model.addAttribute("id", id);
		model.addAttribute("opt", opt);
		return "/admin/prac/eduOption";
	}

	/**
	 * 编辑选项 保存
	 */
	@RequestMapping("/eduOptionData")
	public void eduOptionData(HttpServletResponse response, TpPracticeOption wq) {
		try {
			tpPracticeOptionService.updateByKeySelective(wq);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}
	/**
	 * 编辑选项 保存(判断题)
	 */
	@RequestMapping("/eduOptionDataC")
	public void eduOptionDataC(HttpServletResponse response, TpPracticeOption wq) {
		try {
			tpPracticeOptionService.updateBySelective(wq);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}
	/**
	 * 删除选项
	 */
	@RequestMapping("/deleteOption")
	public void deleteOption(HttpServletResponse response, String id) {
		try {
			tpPracticeOptionService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 进入课表安排页面
	 * 
	 * @return
	 */
	@RequestMapping("/timetable")
	public String timetable(HttpServletRequest request, String pid, Model model) {

		SysClassroom classroom = new SysClassroom();
		SysSchool school = new SysSchool();

		classroom.setBak("Y");
		String search = request.getParameter("search");
		if (search != null && search != "") {
			school.setSearch(search);
			classroom.setSearch(search);
		}

		String bak1 = getUserBak1();
		String bak2 = getUserBak2();
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			classroom.setAuthority(bak1);
			school.setAuthority(bak1);
			List<String> schoolIds = userService.getUserSchoolIds(bak1, bak2, getUserSchoolId());
			if (schoolIds != null && schoolIds.size() > 0) {
				classroom.setSchoolIds(schoolIds);
				school.setIds(schoolIds);
			}
		}

		List<SysSchool> schoolList = sysSchoolService.findSchoolClassNotNull(school);
		List<SysClassroom> lists = sysClassroomService.findSelective(classroom);

		String treeString = "{name: '学校',spread: true,children: [";
		for (SysSchool sysSchool : schoolList) {
			treeString += "{name:'" + sysSchool.getSchoolName() + "',children: [";
			for (SysClassroom sysClassroom : lists) {
				if (sysClassroom.getSchoolId().equals(sysSchool.getId())) {
					treeString += "{ name: '" + sysClassroom.getClassName() + "',id:'" + sysClassroom.getId() + "',schoolId: '" + sysClassroom.getSchoolId() + "',schoolName:'"
							+ sysSchool.getSchoolName() + "'},";
				}
			}
			treeString += "]},";
		}
		treeString += "]}";

		model.addAttribute("treeString", treeString);
		model.addAttribute("pid", pid);
		return "/admin/prac/timetable";
	}

	/**
	 * 获得课程表
	 * 
	 * @param classId
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/getschedule")
	public String getschedule(String classId, String schoolId, Integer weeks, String MondayDate, Integer lastandnext, String pid, Model model) throws ParseException {
		String termTimeId = null;
		Map<String, Object> s = loTermTimeService.getCurrTermTimeWeeks(schoolId);
		if (s != null && s.size() > 0) {
			if (weeks == null) {
				weeks = (Integer) s.get("weeks");
			}
			termTimeId = (String) s.get("termTimeId");
			model.addAttribute("totalWeeks", s.get("totalWeeks"));
		}
		model.addAttribute("weeks", weeks);
		Map<String, Object> hm;
		if (MondayDate != null) {
			if (lastandnext == 1) {
				MondayDate = DateUtil.getNextDay(java.sql.Date.valueOf(MondayDate), 7, "yyyy-MM-dd");
			}
			if (lastandnext == -1) {
				MondayDate = DateUtil.getNextDay(java.sql.Date.valueOf(MondayDate), -7, "yyyy-MM-dd");
			}
			hm = DateUtil.getOneWeekDate(java.sql.Date.valueOf(MondayDate), "yyyy-MM-dd");
		} else
			hm = DateUtil.getOneWeekDate(new Date(), "yyyy-MM-dd");
		model.addAttribute("hm", hm);

		LoSchedule loSchedule = new LoSchedule();
		loSchedule.setClassId(classId);
		// loSchedule.setWeeks(weeks);
		loSchedule.setStartDate((String) hm.get("SundayDate"));
		loSchedule.setEndDate((String) hm.get("SaturdayDate"));
		List<LoSchedule> loSchedules = loScheduleService.findSelective(loSchedule);
		model.addAttribute("loSchedules", loSchedules);

		if (termTimeId != null) {
			model.addAttribute("state", 1);
			LoClassTime classTime = new LoClassTime();
			classTime.setTermTimeId(termTimeId);
			List<LoClassTime> classTimes = loClassTimeService.findSelective(classTime);
			model.addAttribute("classTimes", classTimes);
		} else {
			model.addAttribute("state", 0);
		}
		model.addAttribute("termTimeId", termTimeId);
		model.addAttribute("pid", pid);
		return "/admin/prac/schedule";
	}

	/**
	 * 添加到课程表
	 */
	@RequestMapping("/intoSchedule")
	public void intoSchedule(HttpServletResponse response, TpPracticeSchedule tps) {
		try {

			tps.setCreateId(getUserId());
			tps.setCreateTime(new Date());
			tpPracticeScheduleService.insert(tps);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	// ---------------------------课堂统计-------------------------------------------
 
	
	/**
	 * 获得上课练习记录
	 * 
	 * @param classId
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/examRecord")
	public String examRecord(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String qid, TpPracticeResult wql) {
		if (p == null) {
			p = 1;
		}
		String userId = getUserId();

		Map<String, Object> t = new HashMap<String, Object>();
		t.put("userId", userId);
		if (wql.getSearchClass() != null && wql.getSearchClass().length() > 0) {
			t.put("searchClass", wql.getSearchClass());
		}
		Page<Map<String, Object>> pageInfo = tpPracticeResultService.findPageClassRecord(t, p, 10);
		int pages = pageInfo.getPages();
		List<Map<String, Object>> lists = pageInfo.getResult();

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("qid", qid);
		model.addAttribute("wq", wql);
		return "/admin/prac/examRecord";
	}

	/**
	 * 获得上课练习记录
	 * 
	 * @param classId
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/detailedRecord")
	public String detailedRecord(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String lId, TpPracticeResult wql) {
		if (p == null) {
			p = 1;
		}
		Map<String, Object> t = new HashMap<String, Object>();
		t.put("lId", lId);// 课程表Id
		if (!StringUtils.isEmpty(wql.getSearchType())) {
			t.put("searchType", wql.getSearchType());
		}
		if (!StringUtils.isEmpty(wql.getSearchQuestion())) {
			t.put("searchQuestion", wql.getSearchQuestion());
		}

		Page<Map<String, Object>> pageInfo = tpPracticeResultService.findDetailedRecord(t, p, 5);

		int pages = pageInfo.getPages();
		List<Map<String, Object>> lists = pageInfo.getResult();

		for (Map<String, Object> map : lists) {
			String ops = map.get("options") + "";
			String[] arr = ops.split("<br/>");
			List<String> x = new ArrayList<String>();
			List<Integer> y = new ArrayList<Integer>();
			for (int i = 0; i < arr.length; i++) {
				x.add(arr[i].charAt(0) + "");
				y.add(0);
			}

			double rig = 0;// 答对的人数
			double all = 0;// 所有答题人数

			t.put("questionId", map.get("id") + "");
			List<Map<String, Object>> li = tpPracticeResultService.findAnswerStatistics(t);
			for (int i = 0; i < li.size(); i++) {
				Map<String, Object> m = li.get(i);
				if (m.get("userAnswer") == null) {
					break;// 没有学生答过该题
				}

				// ----计算正确率
				Object obj = m.get("rights");
				if (obj != null) {
					String rights = obj.toString();
					if (equelseTheAnswer(rights,m.get("userAnswer").toString())) {
						rig = Double.parseDouble(m.get("ansNum") + "");
					}
				}
				if (m.get("ansNum") != null) {
					all += Double.parseDouble(m.get("ansNum") + "");
				}

				// ----统计选项选择人数
				if("B".equals(map.get("type")+"")){//多选题
					String xs = m.get("userAnswer") + "";
					char[] xsch = xs.toCharArray();
					for (int j = 0; j < xsch.length; j++) {
						int inx = x.indexOf(String.valueOf(xsch[j]));
						if (inx != -1) {
							int yy= y.get(inx)+Integer.parseInt(m.get("ansNum") + "");
							y.set(inx, yy);
						}
					}
					
				}else{
					String xs = m.get("userAnswer") + "";
					int inx = x.indexOf(xs);
					if (inx != -1) {
						y.set(inx, Integer.parseInt(m.get("ansNum") + ""));
					}
				}
				
			}

			map.put("correctRate", all < 1 ? 0 : ((rig / all) * 100));
			map.put("xAxisData", x.toString());
			map.put("yAxisData", y.toString());
		}
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("lId", lId);
		// model.addAttribute("qid", qid);
		model.addAttribute("wq", wql);
		return "/admin/prac/detailedRecord";
	}

	/**
	 * 获得上课练习记录
	 * 
	 * @param classId
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/findSutdentRecord")
	public String findSutdentRecord(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String lId, TpPracticeResult wql) {
		if (p == null) {
			p = 1;
		}

		/**
		 * 1.查找学生，（客观题正确率） 详细（主观题答题展示） 2.查找学生答题情况 3. 4.SELECT t.id, t.userAnswer, t.boardId, t.userName, tt.rights, tt.type FROM tp_practice_result t LEFT JOIN tp_practice_question
		 * tt ON tt.id = t.questionId WHERE t.bak1 = '08a86cae-bcba-11e8-8e64-001c25d63ebd'
		 * 
		 */
		Map<String, Object> t = new HashMap<String, Object>();
		t.put("lId", lId);
		if (wql.getSearchUserName() != null && wql.getSearchUserName().length() > 0) {
			t.put("searchUserName", wql.getSearchUserName());
		}

		Page<Map<String, Object>> pageInfo = tpPracticeResultService.findSutdentRecord(t, p, 8);
		int pages = pageInfo.getPages();
		List<Map<String, Object>> lists = pageInfo.getResult();

		for (Map<String, Object> map : lists) {
//			String boardId = map.get("boardId") + "";
//			String scheduleId = map.get("scheduleId") + "";
			// 查客观题正确率
			map.put("searchType", 0);
			List<Map<String, Object>> zql = tpPracticeResultService.findSutdentAnswerInfo(map);
			double a = 0;
			double b = zql.size() > 0 ? zql.size() : 1;
			for (Map<String, Object> map2 : zql) {
				Object obj = map2.get("rights");
				if (obj != null) {
					String rights = obj.toString();
					if (equelseTheAnswer(rights,map2.get("userAnswer").toString())) {
						a++;
					}
				}
			}
			map.put("zql", (a / b) * 100);// 正确率
		}

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("lId", lId);
		// model.addAttribute("qid", qid);
		model.addAttribute("wq", wql);
		return "/admin/prac/sutdentRecord";
	}
	
	 
/**
 * 判断答题是否正确
 * @param rights 正确答案
 * @param userAnswer 学生答案
 * @return
 */
private static boolean equelseTheAnswer(String rights,String userAnswer){
	if(StringUtils.isEmpty(rights)||StringUtils.isEmpty(userAnswer))
		return false;
	String r = rights.replaceAll(",+", "");
	if(r.length()!=userAnswer.length())
		return false;
	userAnswer.compareToIgnoreCase(rights);
	char[] r2 = r.toCharArray();
	for (char c : r2) {
		if(!userAnswer.contains(String.valueOf(c))){
			return false;
		}
	}
	return true;
}

 
	@RequestMapping("/sutdentSubjective")
	public String findSutdentSubjective(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String lId, String boardId, TpPracticeResult wql) {
		if (p == null) {
			p = 1;
		}

		Map<String, Object> t = new HashMap<String, Object>();
		t.put("lId", lId);
		t.put("searchType", 1);
		t.put("boardId", boardId);
		List<Map<String, Object>> list = tpPracticeResultService.findSutdentAnswerInfo(t);

		if (list != null && list.size() > 0) {
			Map<String, Object> map = list.get(0);
			model.addAttribute("ansPoint", map.get("rightAnswer"));
		}

		model.addAttribute("pageNum", p);
		model.addAttribute("lists", list);
		model.addAttribute("lId", lId);
		model.addAttribute("wq", wql);
		model.addAttribute("boardId", boardId);
		return "/admin/prac/sutdentSubjective";
	}

	/**
	 * 主观题查看
	 * 
	 * @return
	 */
	@RequestMapping("/showSubjective")
	public String showSubjective(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String rId, TpPracticeResult wql) {
		if (p == null) {
			p = 1;
		}

		//
		// Map<String, Object> t = new HashMap<String, Object>();
		// t.put("lId", lId);
		// t.put("searchType", 1);
		// t.put("boardId", boardId);
		// List<Map<String, Object>> list =
		// tpPracticeResultService.findSutdentAnswerInfo(t);
		//
		//
		// model.addAttribute("pageNum", p);
		// model.addAttribute("lists", list);
		// model.addAttribute("lId", lId);
		// model.addAttribute("wq", wql);
		return "/admin/prac/showSubjective";
	}
	/**
	 * 错误分析
	 * 
	 * @return
	 */
	@RequestMapping("/studentAnswerHistory")
	public String studentAnswerHistory(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String lId, String boardId, TpPracticeResult wql) {
		if (p == null) {
			p = 1;
		}

		Map<String, Object> t = new HashMap<String, Object>();
		t.put("lId", lId);
		t.put("searchType", 0);
		t.put("boardId", boardId);
		List<Map<String, Object>> list = tpPracticeResultService.findSutdentAnswerInfo(t);

		if (list != null && list.size() > 0) {
			Map<String, Object> map = list.get(0);
			model.addAttribute("ansPoint", map.get("rightAnswer"));
		}

		model.addAttribute("pageNum", p);
		model.addAttribute("lists", list);
		model.addAttribute("lId", lId);
		model.addAttribute("wq", wql);
		model.addAttribute("boardId", boardId);
		return "/admin/prac/studentAnswerHistory";
	}
	/**
	 * 获得上课练习记录
	 * 
	 * @param classId
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/detailedRecordBySutdent")
	public String detailedRecordBySutdent(HttpServletRequest request, HttpServletResponse response, Model model, Integer p, String lId, TpPracticeResult wql) {
		if (p == null) {
			p = 1;
		}
		Map<String, Object> t = new HashMap<String, Object>();
		t.put("lId", lId);
		Page<Map<String, Object>> pageInfo = tpPracticeResultService.findDetailedRecord(t, p, 5);

		int pages = pageInfo.getPages();
		List<Map<String, Object>> lists = pageInfo.getResult();

		for (Map<String, Object> map : lists) {
			String ops = map.get("options") + "";
			String[] arr = ops.split("<br/>");
			Queue<String> q = new ConcurrentLinkedQueue<String>();
			for (int i = 0; i < arr.length; i++) {
				q.offer(arr[i].charAt(0) + "");
			}

			double rig = 0;// 答对的人数
			double all = 1;// 所有答题人数

			t.put("questionId", map.get("id") + "");
			List<Map<String, Object>> li = tpPracticeResultService.findAnswerStatistics(t);
			List<String> x = new ArrayList<String>();
			List<Integer> y = new ArrayList<Integer>();
			for (int i = 0; i < li.size(); i++) {
				Map<String, Object> m = li.get(i);
				if (m.get("userAnswer") == null) {
					break;// 没有学生答过该题
				}
				Object obj = m.get("rights");
				if (obj != null) {
					String rights = obj.toString();

					if (equelseTheAnswer(rights,m.get("userAnswer").toString())) {
						rig = Double.parseDouble(m.get("ansNum") + "");
					}
				}
				if (m.get("ansNum") != null) {
					all += Double.parseDouble(m.get("ansNum") + "");
				}

				String xs = m.get("userAnswer") + "";
				x.add(m.get("userAnswer") + "");
				y.add(Integer.parseInt(m.get("ansNum") + ""));

				if (q.contains(xs)) {
					q.remove(xs);
				}
			}
			if (q.size() > 0) {
				for (String string : q) {
					x.add(string);
					y.add(0);
				}
			}
			map.put("correctRate", (rig / all) * 100);
			map.put("xAxisData", x.toString());
			map.put("yAxisData", y.toString());
		}
		System.out.println(lists.get(0).get("xAxisData"));
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("lId", lId);
		// model.addAttribute("qid", qid);
		model.addAttribute("wq", wql);
		return "/admin/prac/detailedRecord";
	}

	// -------------------------------------------------------------------------------***********************************

}
