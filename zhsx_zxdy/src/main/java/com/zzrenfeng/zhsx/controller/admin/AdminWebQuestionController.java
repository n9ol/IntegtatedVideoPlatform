package com.zzrenfeng.zhsx.controller.admin;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.WebQuestion;
import com.zzrenfeng.zhsx.service.WebAnswerService;
import com.zzrenfeng.zhsx.service.WebQuestionService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 在线答疑管理
 * 
 * @author Wsb
 */
@Controller
@RequestMapping("/webquestion")
public class AdminWebQuestionController extends BaseController {

	@Resource
	private WebQuestionService WebQuestionService;
	@Resource
	private WebAnswerService WebAnswerService;

	/**
	 * 在线答疑列表
	 */
	@RequestMapping("/findAll")
	public String findAll(HttpServletResponse response, HttpServletRequest request, Model model, Integer p,
			WebQuestion w) {

		if (p == null)
			p = 1;
		String search = request.getParameter("search");
		if (search != null && !search.equals("")) {
			w.setSearch(search);
		}
		model.addAttribute("search", search);
		Page<WebQuestion> pageInfo = WebQuestionService.findPageSelective(w, p, 10);
		int pages = pageInfo.getPages();
		List<WebQuestion> lists = pageInfo.getResult();
		int pageSize = pageInfo.getPageSize();
		long total = pageInfo.getTotal();

		model.addAttribute("pageSize", pageSize);
		model.addAttribute("total", total);
		model.addAttribute("pageNum", p);// 当前页
		model.addAttribute("pages", pages);// 总页数
		model.addAttribute("lists", lists);
		return "/admin/webquestion/findAll";
	}

	/**
	 * 查看某一问题内容
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectContent")
	public String selectContent(Model model, String id) {

		WebQuestion WebQuestion = WebQuestionService.findByKey(id);
		String contentTag = "";
		if (WebQuestion != null) {
			contentTag = WebQuestion.getContentTag();
		}
		model.addAttribute("contentTag", contentTag);
		return "/admin/webquestion/selectContent";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@RequestMapping("/del")
	public void deleteByPrimaryKey(HttpServletResponse response, String id) {

		try {
			List<String> ids = Arrays.asList(id);
			WebQuestionService.deleteByKey(id);
			WebAnswerService.deleteAnswer(ids);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 显示 禁止
	 * 
	 * @param WebQuestion
	 */
	@RequestMapping("/updateStatus")
	public void updateStatus(HttpServletResponse response, WebQuestion w) {

		try {
			WebQuestionService.updateByKeySelective(w);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 批量修改状态
	 */
	@RequestMapping("updateBatch")
	public void updateBatch(HttpServletResponse response, String[] del_id, String ifShow) {
		try {
			List<String> ids = Arrays.asList(del_id);
			WebQuestionService.updateBatch(ids, ifShow);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);

		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 批量删除
	 */
	@RequestMapping("deleteBatch")
	public void deleteBatch(HttpServletResponse response, String[] del_id) {
		try {
			List<String> ids = Arrays.asList(del_id);
			WebQuestionService.deleteBatch(ids);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

}
