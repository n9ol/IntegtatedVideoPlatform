package com.zzrenfeng.zhsx.controller.admin;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.WebComments;
import com.zzrenfeng.zhsx.service.WebCommentsService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 评论信息管理
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/comments")
public class AdminCommentsController extends BaseController {

	@Resource
	private WebCommentsService webCommentsService;

	/**
	 * 进入评论管理页面
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("/commentsManagement")
	public String commentsManagement(HttpServletResponse response, HttpServletRequest request, Model model, Integer p,
			WebComments comments) {
		if (p == null)
			p = 1;
		model.addAttribute("pageNum", p);// 当前页

		// String search = request.getParameter("search");
		// if (search != null && !search.equals("")) {
		// comments.setSearch(search);
		// }
		model.addAttribute("search", comments.getSearch());
		Page<WebComments> pageInfo = webCommentsService.findPageSelective(comments, p, 12);
		int pages = pageInfo.getPages();// 总页数
		List<WebComments> lists = pageInfo.getResult();
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("contextType", comments.getContextType());
		return "/admin/comments/commentsManagement";
	}

	/**
	 * 修改评论状态
	 * 
	 * @param comments
	 * @return
	 */
	@RequestMapping("updateIsShown")
	public @ResponseBody boolean updateIsShown(WebComments comments) {
		boolean res = true;
		try {
			webCommentsService.updateByKeySelective(comments);
		} catch (Exception e) {
			res = false;
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 单个删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delComment")
	public @ResponseBody boolean delcomments(String id) {
		boolean res = true;
		try {
			webCommentsService.deleteByKey(id);
		} catch (Exception e) {
			res = false;
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 批量修改状态
	 * 
	 * @param del_id
	 * @param isShown
	 * @return
	 */
	@RequestMapping("/batchUpdateIsShown")
	public void batchUpdateIsShown(HttpServletResponse response, String[] del_id, String isShown) {
		try {
			List<String> ids = Arrays.asList(del_id);
			webCommentsService.updateBatch(ids, isShown);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 批量删除评论
	 * 
	 * @param del_id
	 * @return
	 */
	@RequestMapping("/batchDel")
	public void batchDel(HttpServletResponse response, String[] del_id) {
		try {
			List<String> ids = Arrays.asList(del_id);
			webCommentsService.deleteBatch(ids);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

}
