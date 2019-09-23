package com.zzrenfeng.zhsx.controller.admin;

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
import com.zzrenfeng.zhsx.model.WebNews;
import com.zzrenfeng.zhsx.model.WuFile;
import com.zzrenfeng.zhsx.service.WebNewsService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.Utils;
import com.zzrenfeng.zhsx.util.WriterUtils;

@Controller
@RequestMapping(value = "/adminnew")
public class AdminNewsController extends BaseController {

	@Resource
	private WebNewsService webNewsService;

	/**
	 * 进入新闻管理界面
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param n
	 * @param p
	 * @return
	 */
	@RequestMapping("/newManage")
	public String newManage(HttpServletRequest request, HttpServletResponse response, Model model, WebNews n,
			Integer p) {
		if (p == null) {
			p = 1;
		}
		Page<WebNews> pageInfo = webNewsService.findPageSelective(n, p, 10);
		int pages = pageInfo.getPages();
		model.addAttribute("pages", pages);
		model.addAttribute("pageNum", p);
		List<WebNews> list = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("lists", list);
		return "/admin/news/sysNew";
	}

	/**
	 * 查看详情
	 * 
	 * @param response
	 * @param model
	 * @param id
	 * @return
	 */
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
			return "admin/news/wordDetail";
		} else {
			return "admin/news/videoDetail";
		}
	}

	/*
	 * 进入编辑页面
	 */
	@RequestMapping("/update")
	public String update(HttpServletResponse response, Model model, String id) {
		WebNews n = webNewsService.findByKey(id);
		model.addAttribute("news", n);
		return "/admin/news/editnew";
	}

	@RequestMapping("/addInfor")
	public String addInfor() {
		return "/admin/news/addinfor";
	}

	@RequestMapping("/del")
	public void del(HttpServletResponse response, String id) {
		try {
			webNewsService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}

	}

	@RequestMapping("/updateNews")
	public void updateNews(HttpServletResponse response, Model model, WebNews w) {
		try {
			webNewsService.updateByKeySelective(w);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}

	}

	/**
	 * 添加
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @param n
	 */
	@ResponseBody
	@RequestMapping("/addsave")
	public Map<String, Object> addsave(HttpServletResponse response, HttpServletRequest request, WebNews n) {
		Map<String, Object> hm = new HashMap<>();
		String webpath = null;
		String msg;

		n.setCreateid(getUserId());
		n.setAddtime(new Date());
		n.setStatus(1);
		n.setView(0);
		if (n.getModelType().equals("S")) {
			hm.put("filePath", n.getBak2());
			String videoPath = n.getBak2().substring(0, n.getBak2().lastIndexOf(".")) + ".flv";
			n.setBak2(videoPath);
			webpath = "http://" + request.getServerName() + ":" + request.getServerPort() + "/"
					+ request.getRequestURI().split("/")[1] + "/adminnew/updateVideoRes";
		}

		try {
			webNewsService.insert(n);
			msg = MessageUtils.SUCCESS;
			hm.put("id", n.getId());
		} catch (Exception e1) {
			msg = MessageUtils.FAilURE;
			e1.printStackTrace();
		}
		hm.put("msg", msg);
		hm.put("webpath", webpath);
		return hm;
	}

	/**
	 * 修改
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @param n
	 */
	@ResponseBody
	@RequestMapping("/editNew")
	public Map<String, Object> editNew(HttpServletResponse response, HttpServletRequest request, Model model,
			WebNews n) {
		Map<String, Object> hm = new HashMap<>();
		String webpath = null;
		String msg;

		if (n.getModelType().equals("S")) {
			hm.put("filePath", n.getBak2());
			String videoPath = n.getBak2().substring(0, n.getBak2().lastIndexOf(".")) + ".flv";
			n.setBak2(videoPath);
			webpath = "http://" + request.getServerName() + ":" + request.getServerPort() + "/"
					+ request.getRequestURI().split("/")[1] + "/adminnew/updateVideoRes";
		}

		try {
			webNewsService.updateByKeySelective(n);
			msg = MessageUtils.SUCCESS;
			hm.put("id", n.getId());
		} catch (Exception e) {
			msg = MessageUtils.FAilURE;
		}

		hm.put("msg", msg);
		hm.put("webpath", webpath);
		return hm;
	}

	/**
	 * 远程更新数据库-此处并不需要真正给更新数据库信息
	 * 
	 * @param response
	 * @param videoResources
	 */
	@RequestMapping("/updateVideoRes")
	public void updateVideoRes(HttpServletResponse response, WebNews videoResources) {
		// webNewsService.renewalData(videoResources);
	}

	/**
	 * 文件上传页面
	 * 
	 * @param request
	 * @param modle
	 * @return
	 */
	@RequestMapping("/fileUpload")
	public String fileUpload(HttpServletRequest request, Model modle, WuFile wuFile) {
		String size = Utils.sizeToString(Long.valueOf(wuFile.getSize()));
		wuFile.setSize(size);
		modle.addAttribute("file", wuFile);
		return "/admin/news/fileUpload";
	}

}
