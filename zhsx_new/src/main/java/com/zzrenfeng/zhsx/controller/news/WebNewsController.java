package com.zzrenfeng.zhsx.controller.news;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.WebNews;
import com.zzrenfeng.zhsx.service.WebNewsService;

@Controller
@RequestMapping(value = "/new")
public class WebNewsController extends BaseController {

	@Resource
	private WebNewsService webNewsService;

	@RequestMapping("/xinwen")
	public String xinwen() {
		return "/news/xinwen";
	}

	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request, HttpServletResponse response, Model model, WebNews n, Integer p) {
		if (p == null) {
			p = 1;
		}
		n.setStatus(1);
		Page<WebNews> pageInfo = webNewsService.findAll(n, p, 8);
		int pages = pageInfo.getPages();
		model.addAttribute("pages", pages);
		model.addAttribute("pageNum", p);
		List<WebNews> list = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("lists", list);
		return "/news/allnews";
	}

	@RequestMapping("/getTop")
	public String getTop(HttpServletRequest request, HttpServletResponse response, WebNews n, Model model) {
		n.setStatus(1);
		List<WebNews> list = webNewsService.getTop(n);
		model.addAttribute("lists", list);
		return "/news/xwph";

	}

	@RequestMapping("/findOne")
	public String findOne(HttpServletResponse response, Model model, String id) {
		WebNews n = webNewsService.findByKey(id);
		int view = n.getView() + 1;
		n.setView(view);
		webNewsService.uNewsView(id, view);
		model.addAttribute("webNews", n);
		model.addAttribute("vedioPath", n.getBak2());
		model.addAttribute("id", n.getId());
		if (n.getModelType().equals("W")) {
			return "/news/wordDetail";
		} else {
			return "/news/videoDetail";
		}
	}

}
