package com.zzrenfeng.zhsx.controller.admin;

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
import com.zzrenfeng.zhsx.model.WebQuestionnaire;
import com.zzrenfeng.zhsx.model.WebQuestionnaireLibrary;
import com.zzrenfeng.zhsx.model.WebQuestionnaireOption;
import com.zzrenfeng.zhsx.model.WebQuestionnaireQuestion;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.WebQuestionnaireLibraryService;
import com.zzrenfeng.zhsx.service.WebQuestionnaireOptionService;
import com.zzrenfeng.zhsx.service.WebQuestionnaireQuestionService;
import com.zzrenfeng.zhsx.service.WebQuestionnaireService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

@Controller
@RequestMapping(value = "/questionnaire")
public class AdminQuestionnaireController extends BaseController {

	@Resource
	private UserService userService;
	@Resource
	private WebQuestionnaireService webQuestionnaireService;
	@Resource
	private WebQuestionnaireLibraryService webQuestionnaireLibraryService;
	@Resource
	private WebQuestionnaireOptionService webQuestionnaireOptionService;

	@Resource
	private WebQuestionnaireQuestionService webQuestionnaireQuestionService;

	/**
	 * 问卷列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param p
	 * @param wq
	 * @return
	 */
	@RequestMapping(value = "/questionnaire_list")
	public String questionnaireList(HttpServletRequest request, HttpServletResponse response, Model model, Integer p,
			WebQuestionnaire wq) {
		if (p == null) {
			p = 1;
		}
		Page<WebQuestionnaire> pageInfo = webQuestionnaireService.findPageSelective(wq, p, 10);
		int pages = pageInfo.getPages();
		List<WebQuestionnaire> lists = pageInfo.getResult();

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("wq", wq);
		return "/admin/questionnaire/questionnaire_list";
	}

	/**
	 * 添加调查问卷
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param p
	 * @param wq
	 * @return
	 */
	@RequestMapping(value = "/addQuestionnaire")
	public String addQuestionnaire(HttpServletRequest request, HttpServletResponse response, Model model, Integer p,
			WebQuestionnaire wq) {
		if (p == null) {
			p = 1;
		}
		Page<WebQuestionnaire> pageInfo = webQuestionnaireService.findPageSelective(wq, p, 10);
		int pages = pageInfo.getPages();
		List<WebQuestionnaire> lists = pageInfo.getResult();

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		return "/admin/questionnaire/addQuestionnaire";
	}

	/**
	 * 保存调查问卷
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/addQuestionnaireData")
	public void addQuestionnaireData(HttpServletResponse response, WebQuestionnaire wq) {
		try {
			wq.setCreateId(getUserId());
			wq.setCreateTime(new Date());
			wq.setIsShown("N");
			webQuestionnaireService.insert(wq);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 编辑调查问卷
	 */
	@RequestMapping(value = "/eduQuestionnaire")
	public String eduQuestionnaire(HttpServletRequest request, HttpServletResponse response, Model model, Integer p,
			String id) {
		// if (p == null) {
		// p = 1;
		// }
		WebQuestionnaire que = webQuestionnaireService.findByKey(id);
		model.addAttribute("que", que);
		return "/admin/questionnaire/eduQuestionnaire";
	}

	/**
	 * 编辑保存调查问卷
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/eduQuestionnaireData")
	public void eduQuestionnaireData(HttpServletResponse response, WebQuestionnaire wq) {
		try {
			webQuestionnaireService.updateByKeySelective(wq);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 发布调查问卷
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/addFabu")
	public void addFabu(HttpServletResponse response, String qid) {
		try {
			WebQuestionnaire wq = webQuestionnaireService.findByKey(qid);
			wq.setModiyId(getUserId());
			wq.setModiyTime(new Date());
			wq.setIsShown("Y");
			webQuestionnaireService.updateByKeySelective(wq);
			webQuestionnaireService.updateState(wq);

			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 取消发布调查问卷
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/cancelFabu")
	public void cancelFabu(HttpServletResponse response, String qid) {
		try {
			WebQuestionnaire wq = webQuestionnaireService.findByKey(qid);
			wq.setModiyId(getUserId());
			wq.setModiyTime(new Date());
			wq.setIsShown("N");
			webQuestionnaireService.updateByKeySelective(wq);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	/**
	 * 删除调查问卷
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/delQuestionnair")
	public void delQuestionnair(HttpServletResponse response, String qid) {
		try {
			WebQuestionnaire wq = webQuestionnaireService.findByKey(qid);
			wq.setModiyId(getUserId());
			wq.setModiyTime(new Date());
			wq.setIsShown("Y");
			webQuestionnaireService.deleteByKey(qid);
			WebQuestionnaireQuestion question = new WebQuestionnaireQuestion();
			question.setQid(qid);
			webQuestionnaireQuestionService.deleteByCondition(question);
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
	public String findAllQues(HttpServletRequest request, HttpServletResponse response, Model model, Integer p,
			String qid, WebQuestionnaireQuestion wq) {
		if (p == null) {
			p = 1;
		}
		// WebQuestionnaireQuestion wq = new WebQuestionnaireQuestion();
		wq.setQid(qid);
		Page<WebQuestionnaireQuestion> pageInfo = webQuestionnaireQuestionService.findPageSelective(wq, p, 10);
		int pages = pageInfo.getPages();
		List<WebQuestionnaireQuestion> lists = pageInfo.getResult();
		for (WebQuestionnaireQuestion webQuestionnaireQuestion : lists) {
			StringBuffer op = new StringBuffer();
			StringBuffer op2 = new StringBuffer();
			WebQuestionnaireOption wq2 = new WebQuestionnaireOption();
			wq2.setQid(webQuestionnaireQuestion.getLid());
			List<WebQuestionnaireOption> opt = webQuestionnaireOptionService.findSelective(wq2);
			for (int i = 0; opt != null && i < opt.size(); i++) {
				char a = (char) (i + 65);
				op.append(a + "." + opt.get(i).getOption() + "<br/>");
				op2.append(a + "." + opt.get(i).getOption());
			}
			webQuestionnaireQuestion.setOption(op.toString());
			webQuestionnaireQuestion.setBak(op2.toString());
		}
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("qid", qid);
		model.addAttribute("wq", wq);
		return "/admin/questionnaire/question_list";
	}

	/**
	 * 问卷管理删除问题
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/deleteQues")
	public void deleteQues(HttpServletResponse response, String id) {
		try {
			webQuestionnaireQuestionService.deleteByKey(id);
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
	 * @param qid
	 *            问卷id
	 * @return
	 */
	@RequestMapping(value = "/questionLibrary2")
	public String questionLibrary2(HttpServletRequest request, HttpServletResponse response, Model model, Integer p,
			String qid, WebQuestionnaireLibrary wql) {
		if (p == null) {
			p = 1;
		}
		Page<WebQuestionnaireLibrary> pageInfo = webQuestionnaireLibraryService.findPageSelective(wql, p, 10);
		int pages = pageInfo.getPages();
		List<WebQuestionnaireLibrary> lists = pageInfo.getResult();
		for (WebQuestionnaireLibrary webQuestionnaireLibrary : lists) {
			StringBuffer op = new StringBuffer();
			StringBuffer op2 = new StringBuffer();
			WebQuestionnaireOption wq = new WebQuestionnaireOption();
			wq.setQid(webQuestionnaireLibrary.getId());
			List<WebQuestionnaireOption> opt = webQuestionnaireOptionService.findSelective(wq);
			for (int i = 0; opt != null && i < opt.size(); i++) {
				char a = (char) (i + 65);
				op.append(a + "." + opt.get(i).getOption() + "<br/>");
				op2.append(a + "." + opt.get(i).getOption());
			}
			webQuestionnaireLibrary.setOption(op.toString());
			webQuestionnaireLibrary.setBak(op2.toString());
		}

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("qid", qid);
		model.addAttribute("wq", wql);
		return "/admin/questionnaire/questionLibrary2";
	}

	/**
	 * 批量保存问题
	 * 
	 * @param response
	 * @param del_id
	 */
	@RequestMapping("saveBatchQuestion")
	public void saveBatchQuestion(HttpServletResponse response, String[] lid, String qid) {
		try {
			List<String> lidslist = Arrays.asList(lid);

			webQuestionnaireQuestionService.saveBatBylId(lidslist, qid, getUserId());

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
	@RequestMapping(value = "/questionLibrary")
	public String questionLibrary(HttpServletRequest request, HttpServletResponse response, Model model, Integer p,
			WebQuestionnaireLibrary wq) {
		if (p == null) {
			p = 1;
		}

		Page<WebQuestionnaireLibrary> pageInfo = webQuestionnaireLibraryService.findPageSelective(wq, p, 10);
		int pages = pageInfo.getPages();
		List<WebQuestionnaireLibrary> lists = pageInfo.getResult();

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("wq", wq);
		return "/admin/questionnaire/questionLibrary";
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
	public String addQuestion(HttpServletRequest request, HttpServletResponse response, Model model, Integer p,
			WebQuestionnaireLibrary wq) {
		// if (p == null) {
		// p = 1;
		// }
		// Page<WebQuestionnaireLibrary> pageInfo =
		// webQuestionnaireLibraryService.findPageSelective(wq, p, 10);
		// int pages = pageInfo.getPages();
		// List<WebQuestionnaireLibrary> lists = pageInfo.getResult();
		//
		//
		// model.addAttribute("pageNum", p);
		// model.addAttribute("pages", pages);
		// model.addAttribute("lists", lists);
		return "/admin/questionnaire/addQuestion";
	}

	/**
	 * 保存问题
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/addQuestionData")
	public void addQuestionData(HttpServletResponse response, WebQuestionnaireLibrary wq) {
		try {
			wq.setCreateId(getUserId());
			wq.setCreateTime(new Date());
			wq.setIsShown("Y");
			webQuestionnaireLibraryService.insert(wq);
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
	public String eduQuestion(HttpServletRequest request, HttpServletResponse response, Model model, Integer p,
			String id) {
		// if (p == null) {
		// p = 1;
		// }
		WebQuestionnaireLibrary ques = webQuestionnaireLibraryService.findByKey(id);
		model.addAttribute("testQues", ques);
		return "/admin/questionnaire/eduQuestion";
	}

	/**
	 * 编辑保存问题
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/eduQuestionData")
	public void eduQuestionData(HttpServletResponse response, WebQuestionnaireLibrary wq) {
		try {
			webQuestionnaireLibraryService.updateByKeySelective(wq);
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
			webQuestionnaireLibraryService.deleteByKey(id);
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
			webQuestionnaireLibraryService.delBatchLibrary(ids);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	// ------------------------------选项管理-------------------------------------
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
	public String findAllOption(HttpServletRequest request, HttpServletResponse response, Model model, Integer p,
			String qid) {
		if (p == null) {
			p = 1;
		}
		WebQuestionnaireOption wq = new WebQuestionnaireOption();
		wq.setQid(qid);
		Page<WebQuestionnaireOption> pageInfo = webQuestionnaireOptionService.findPageSelective(wq, p, 10);
		int pages = pageInfo.getPages();
		List<WebQuestionnaireOption> lists = pageInfo.getResult();

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("qid", qid);
		return "/admin/questionnaire/option_list";
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
	public String addOption(HttpServletRequest request, HttpServletResponse response, Model model, Integer optSum,
			String qid) {

		model.addAttribute("qid", qid);
		model.addAttribute("optSum", optSum);
		return "/admin/questionnaire/addOption";
	}

	/**
	 * 保存问题选项
	 * 
	 * @param response
	 * @param wq
	 */
	@RequestMapping("/addOptionData")
	public void addOptionData(HttpServletResponse response, WebQuestionnaireOption wq) {
		try {
			wq.setCreateId(getUserId());
			wq.setCreateTime(new Date());
			wq.setIsShown("Y");
			wq.setSort("0");
			webQuestionnaireOptionService.insert(wq);
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
	public String eduOption(HttpServletRequest request, HttpServletResponse response, Model model, Integer p,
			String qid, String id) {
		// if (p == null) {
		// p = 1;
		// }
		WebQuestionnaireOption opt = webQuestionnaireOptionService.findByKey(id);
		model.addAttribute("qid", qid);
		model.addAttribute("id", id);
		model.addAttribute("opt", opt);
		return "/admin/questionnaire/eduOption";
	}

	/**
	 * 编辑选项 保存
	 */
	@RequestMapping("/eduOptionData")
	public void eduOptionData(HttpServletResponse response, WebQuestionnaireOption wq) {
		try {
			webQuestionnaireOptionService.updateByKeySelective(wq);
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
			webQuestionnaireOptionService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}
}
