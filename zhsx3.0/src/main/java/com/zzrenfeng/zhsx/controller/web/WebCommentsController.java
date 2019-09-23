package com.zzrenfeng.zhsx.controller.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.WebComments;
import com.zzrenfeng.zhsx.service.WebCommentsService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 评论信息
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/webComments")
public class WebCommentsController extends BaseController {

	@Resource
	private WebCommentsService webCommentsService;

	/**
	 * 获得评论
	 * 
	 * @param model
	 * @param webComments
	 * @return
	 */
	@RequestMapping("/comment")
	public String comment(Model model, WebComments webComments, Integer p) {
		if (p == null)
			p = 1;
		model.addAttribute("pageNum", p);// 当前页

		webComments.setIsShown("Y");
		Page<WebComments> pageInfo = webCommentsService.findPageSelective(webComments, p, 6);
		int pages = pageInfo.getPages();// 总页数
		List<WebComments> lists = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("contextType", webComments.getContextType());
		return "/web/comment";
	}

	/**
	 * 添加评论信息到数据库
	 * 
	 * @param response
	 * @param webComments
	 */
	@RequestMapping("/insterComment")
	public void insterComment(HttpServletResponse response, @Validated WebComments webComments) {
		webComments.setUserId(getUserId());
		webComments.setIsShown("Y");
		webComments.setThumbsUp(0);
		webComments.setThumbsDown(0);
		Date date = new Date();
		webComments.setModiyTime(date);
		webComments.setCreateTime(date);
		try {
			webCommentsService.insert(webComments);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 修改评论信息
	 * 
	 * @param webComments
	 */
	@RequestMapping("/updataComment")
	public void updataComment(HttpServletResponse response, WebComments webComments) {
		try {
			webCommentsService.updateByKeySelective(webComments);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

}
