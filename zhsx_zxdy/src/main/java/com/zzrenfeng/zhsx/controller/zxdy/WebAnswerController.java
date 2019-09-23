package com.zzrenfeng.zhsx.controller.zxdy;

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
import com.zzrenfeng.zhsx.model.ShiroUser;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.model.WebAnswer;
import com.zzrenfeng.zhsx.model.WebQuestion;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.WebAnswerService;
import com.zzrenfeng.zhsx.service.WebQuestionService;
import com.zzrenfeng.zhsx.util.DateUtil;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 在线答疑前台
 * 
 * @author Wsb
 */
@Controller
@RequestMapping("/zxdy")
public class WebAnswerController extends BaseController {

	@Resource
	private WebAnswerService webAnswerService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private WebQuestionService webQuestionService;
	@Resource
	private UserService userService;

	/**
	 * 进入答疑界面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/online_dayi")
	public String live(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		return "/zxdy/online_dayi";
	}

	/**
	 * 获得最新的解答
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 *//*
		 * @RequestMapping("/getNew") public String getNew(HttpServletResponse
		 * response, HttpServletRequest request, Model model, WebAnswer w){
		 * List<WebAnswer> list = webAnswerService.getNew(w); for (int i = 0; i
		 * < list.size(); i++) { Date time = list.get(i).getTime(); DateUtil d =
		 * new DateUtil(); long tt = d.getDateBetween(time,new Date()); String
		 * dateTime = d.getTimeBy(tt); list.get(i).setDateTime(dateTime);
		 * 
		 * } model.addAttribute("lists" , list); model.addAttribute("search",
		 * w.getSearch()); return "/zxdy/new";
		 * 
		 * }
		 */
	@RequestMapping("/getResolve")
	public String getResolve(HttpServletResponse response, HttpServletRequest request, Model model, WebAnswer w,
			Integer p) {
		if (p == null) {
			p = 1;
		}
		String gradeId = request.getParameter("gradeId");
		if (!gradeId.equals("")) {
			w.setGradeId(gradeId);
		}
		String subjectId = request.getParameter("subjectId");
		if (!subjectId.equals("")) {
			w.setSubjectId(subjectId);
		}
		String area = request.getParameter("area");
		if (!area.equals("")) {
			w.setArea(area);
		}
		model.addAttribute("pageNum", p);
		Page<WebAnswer> pageInfo = webAnswerService.getResolve(w, p, 10);
		int pages = pageInfo.getPages();// 总页数
		List<WebAnswer> list = pageInfo.getResult();
		model.addAttribute("pages", pages);
		model.addAttribute("lists", list);
		model.addAttribute("search", w.getSearch());
		model.addAttribute("gradeId", gradeId);
		model.addAttribute("subjectId", subjectId);
		model.addAttribute("area", area);
		return "/zxdy/getResolve";

	}

	@RequestMapping("/getNotAnswer")
	public String getNotAnswer(HttpServletResponse response, HttpServletRequest request, Model model, WebQuestion w,
			Integer p) {
		if (p == null) {
			p = 1;
		}
		String gradeId = request.getParameter("gradeId");
		if (!gradeId.equals("")) {
			w.setGradeId(gradeId);
		}
		String subjectId = request.getParameter("subjectId");
		if (!subjectId.equals("")) {
			w.setSubjectId(subjectId);
		}
		String area = request.getParameter("area");
		if (!area.equals("")) {
			w.setArea(area);
		}
		model.addAttribute("pageNum", p);
		Page<WebQuestion> pageInfo = webQuestionService.getNotAnswer(w, p, 10);
		int pages = pageInfo.getPages();// 总页数
		List<WebQuestion> list = pageInfo.getResult();
		model.addAttribute("pages", pages);
		model.addAttribute("lists", list);
		model.addAttribute("search", w.getSearch());
		model.addAttribute("gradeId", gradeId);
		model.addAttribute("subjectId", subjectId);
		model.addAttribute("area", area);
		return "/zxdy/getNotAnswer";

	}

	/**
	 * 回答列表
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @param w
	 * @param p
	 * @return
	 */
	@RequestMapping("/getByQid")
	public String getByQid(HttpServletResponse response, HttpServletRequest request, Model model, WebAnswer w,
			Integer p) {
		if (p == null) {
			p = 1;
		}
		model.addAttribute("pageNum", p);
		Page<WebAnswer> pageInfo = webAnswerService.getByQid(w, p, 5);
		int pages = pageInfo.getPages();
		List<WebAnswer> list = pageInfo.getResult();
		WebQuestion q = webQuestionService.findByKey(w.getQid());
		// 如果登录用户和问题id相等
		ShiroUser sh = getShiroUser();
		if (sh != null) {
			String uId = sh.getId();// 得到登录用户的id
			if (uId.equals(q.getFromId())) {
				String isBest = "";
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getIfBest() == 1) {
						isBest = "IS";
					}
				}
				model.addAttribute("isBest", isBest);
				model.addAttribute("pages", pages);
				model.addAttribute("lists", list);
				return "/zxdy/myAnswer";
			} else {
				model.addAttribute("pages", pages);
				model.addAttribute("lists", list);
				return "/zxdy/answer";
			}
		} else {
			model.addAttribute("pages", pages);
			model.addAttribute("lists", list);
			return "/zxdy/answer";
		}
	}

	@RequestMapping("/getBest")
	public String getBest(HttpServletResponse response, HttpServletRequest request, Model model, WebQuestion w) {

		List<WebQuestion> list = webQuestionService.getBest(w);
		model.addAttribute("lists", list);
		model.addAttribute("search", w.getSearch());
		return "/zxdy/getBest";

	}

	// 得到某一问题详情
	@RequestMapping("/getById")
	public String getById(HttpServletResponse response, HttpServletRequest request, Model model, String id) {
		WebQuestion question = webQuestionService.findByKey(id);
		model.addAttribute("question", question);
		model.addAttribute("search", question.getSearch());
		return "/zxdy/onlineDay_xingqing";
	}

	// 插入问题
	@RequestMapping("/insert")
	public @ResponseBody Map<String, Object> insert(HttpServletResponse response, HttpServletRequest request,
			WebQuestion w, Model model) {
		String uid = getUserId();// 获得登录用户id
		String content = w.getContent();
		if (content == null || content.equals("")) {
			content = "点击查看图片";
		}
		w.setContent(content);
		w.setSendTime(new Date());
		w.setFromId(uid);
		w.setIfRespone(0);
		w.setIfShow(1);
		int i = webQuestionService.insert(w);
		Map<String, Object> hm = new HashMap<String, Object>();
		String id = w.getId();
		if (i > 0) {
			hm.put("msg", "添加成功");
			hm.put("id", id);
		} else {
			hm.put("msg", "添加失败");
		}
		return hm;

	}

	// 插入一条回答
	@RequestMapping("/insertAnwser")
	public void insertAnwser(HttpServletResponse response, HttpServletRequest request, WebAnswer w, WebQuestion wq) {
		try {
			String uid = getUserId();// 获得登录用户id
			w.setTime(new Date());
			w.setAnswerId(uid);
			w.setIfShow(1);
			w.setIfBest(0);
			String answerContent = w.getAnswerContent();
			if (answerContent == null) {
				answerContent = "点击查看图片";
			}
			w.setAnswerContent(answerContent);
			// 回答获取经验
			WebAnswer an = new WebAnswer();
			an.setQid(w.getQid());
			an.setAnswerId(uid);
			List<WebAnswer> l = webAnswerService.findSelective(an);
			webAnswerService.insert(w);
			if (l == null || l.size() <= 0) {
				String type = "";
				if (getUserType().equals(User.userType_teachers)) {
					type = "R";
					webAnswerService.addUserExp(uid, w.getId(), type);
				}
			}
			// 此处修改状态(修改问题已回答)
			wq.setId(w.getQid());
			wq.setIfRespone(1);
			webQuestionService.updateByKeySelective(wq);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);

		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 提问
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */

	@RequestMapping("/tiwen")
	public String tiwen(HttpServletRequest request, HttpServletResponse response, Model model) {
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

		return "/zxdy/onlineDayi_tiwen";
	}

	/**
	 * 最新提问
	 * 
	 * @param request
	 * @param model
	 * @param memberId
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/findNewQue")
	public String findNewQue(HttpServletRequest request, Model model, WebQuestion que, Integer p) {
		String gradeId = request.getParameter("gradeId");
		if (gradeId != "") {
			que.setGradeId(gradeId);
		}
		String subjectId = request.getParameter("subjectId");
		if (subjectId != "") {
			que.setSubjectId(subjectId);
		}
		String area = request.getParameter("area");
		if (area != "") {
			que.setArea(area);
		}
		String memberId = request.getParameter("memberId");
		if (memberId != null && memberId != "") {
			memberId = getUserId();
		}

		String search1 = request.getParameter("search1");
		if (search1.equals("S")) {
			memberId = getUserId();
		}

		if (!memberId.equals("")) {
			que.setFromId(memberId);
		}
		que.setIfShow(1);
		if (p == null) {
			p = 1;
		}
		Page<WebQuestion> pageInfo = webQuestionService.findNewQue(que, p, 4);
		int pages = pageInfo.getPages();
		List<WebQuestion> lists = pageInfo.getResult();
		if (lists != null) {
			for (int i = 0; i < lists.size(); i++) {
				Date time = lists.get(i).getSendTime();
				DateUtil d = new DateUtil();
				long tt = d.getDateBetween(time, new Date());
				String dateTime = d.getTimeBy(tt);
				lists.get(i).setDateTime(dateTime);

			}
		}
		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("gradeId", gradeId);
		model.addAttribute("subjectId", subjectId);
		model.addAttribute("area", area);
		model.addAttribute("memberId", memberId);
		model.addAttribute("search2", search1);
		return "/zxdy/new";

	}

	/**
	 * 设置最佳问题（id qid answerId）
	 * 
	 * @param response
	 * @param w
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/setBest")
	public void setBest(HttpServletResponse response, WebAnswer w) {
		try {
			// 一个问题值能设置一个最佳
			String uId = getUserId();
			String msg = "";
			WebAnswer an = new WebAnswer();
			an.setIfBest(1);
			an.setIfShow(1);
			an.setQid(w.getQid());
			List<WebAnswer> l = webAnswerService.findSelective(an);
			if (l != null && l.size() > 0) {
				msg = "只能有一个最佳答案";
				WriterUtils.toHtml(response, msg);
			} else {
				w.setIfBest(1);
				webAnswerService.updateByKeySelective(w);
				String type = "";
				User u = userService.findByKey(w.getAnswerId());
				if (u.getUserType().equals(User.userType_teachers)) {
					type = "B";
					webAnswerService.addUserExp(w.getAnswerId(), w.getId(), type);
				}
				WriterUtils.toHtml(response, MessageUtils.SUCCESS);
			}

		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}

	}
}
