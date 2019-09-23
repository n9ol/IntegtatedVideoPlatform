package com.zzrenfeng.zhsx.controller.online;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.LoPgGroup;
import com.zzrenfeng.zhsx.service.LoPgGroupService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-29 16:52:30
 * @see com.zzrenfeng.zhsx.controller.LoPgGroup
 */
@Controller
@RequestMapping(value = "/adminLoPgGroup")
public class AdminLoPgGroupController extends BaseController {

	@Resource
	private LoPgGroupService loPgGroupService;

	@RequestMapping("/pgGroup")
	public String pgGroup(Model model, LoPgGroup loPgGroup, Integer p) {
		if (p == null) {
			p = 1;
		}

		loPgGroup.setCreaterId(getUserId());
		Page<LoPgGroup> pageInfo = loPgGroupService.findPageSelective(loPgGroup, p, 12);
		List<LoPgGroup> lists = pageInfo.getResult();
		int pages = pageInfo.getPages();
		long total = pageInfo.getTotal();

		model.addAttribute("pageNum", p);// 当前页
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		model.addAttribute("total", total);
		model.addAttribute("searchval", loPgGroup.getSearch());

		return "/admin/pgGroup/pgGroup";
	}

	@RequestMapping("/insertPgGroup")
	public void insertPgGroup(LoPgGroup loPgGroup, HttpServletResponse response) {
		try {
			loPgGroup.setCreaterId(getUserId());
			loPgGroupService.insert(loPgGroup);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	@RequestMapping("/delPgGroup")
	public void delPgGroup(@RequestParam String id, HttpServletResponse response) {
		try {
			loPgGroupService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	@RequestMapping("/batchDelPgGroup")
	public void batchDelPgGroup(String[] del_id, HttpServletResponse response) {
		try {
			loPgGroupService.batchDelPgGroup(del_id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	@RequestMapping("/updatePgGroup")
	public void updatePgGroup(LoPgGroup loPgGroup, HttpServletResponse response) {
		try {
			loPgGroupService.updateByKeySelective(loPgGroup);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}
}
