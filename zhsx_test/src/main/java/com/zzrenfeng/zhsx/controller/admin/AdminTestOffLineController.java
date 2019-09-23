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
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.TestWebOffLine;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.TestWebOffLineService;
import com.zzrenfeng.zhsx.service.TestWebTestService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-08-14 15:33:19
 * @see com.zzrenfeng.zhsx.controller.TestWebOffLine
 */
@Controller
@RequestMapping(value = "/admintestOffLine")
public class AdminTestOffLineController extends BaseController {

	@Resource
	private TestWebOffLineService testWebOffLineService;
	@Resource
	private UserService userService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private TestWebTestService testWebTestService;

	/**
	 * 进入离线考试
	 */
	@RequestMapping("/offLine")
	public String managerOffLine(HttpServletRequest request, Model model) {

		// 获得年级
		SysDict dict = new SysDict();
		dict.setKeyname("G");
		List<SysDict> grades = sysDictService.findSelective(dict);
		model.addAttribute("grades", grades);

		return "/admin/test/offLine";
	}

	/**
	 * 获得离线考试数据
	 * 
	 * @param p
	 * @param offLine
	 * @return
	 */
	@RequestMapping("/offLineData")
	public String offLineData(Integer p, TestWebOffLine offLine, Model model) {

		if (p == null) {
			p = 1;
		}

		Page<TestWebOffLine> pageInfo = testWebOffLineService.findPageSelective(offLine, p, 10);
		int pages = pageInfo.getPages();
		List<TestWebOffLine> lists = pageInfo.getResult();

		int page = 0;
		int sum = 1;
		for (int i = 0; i < lists.size(); i++) {
			page = lists.get(i).getPassNum();
			sum = lists.get(i).getSum();
			if (sum == 0) {
				lists.get(i).setPercentage("0%");
			} else {
				String x = String.valueOf((page * 100 / sum));
				lists.get(i).setPercentage(x + "%");
			}
		}

		model.addAttribute("pageNum", p);
		model.addAttribute("pages", pages);
		model.addAttribute("lists", lists);
		return "/admin/test/offLineData";
	}

	/**
	 * 删除
	 */
	@RequestMapping("/del")
	public void del(HttpServletResponse response, String id) {
		try {
			testWebOffLineService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
		}
	}

	@RequestMapping("/deleteBatch")
	public void deleteBatch(HttpServletResponse response, String[] delTestWebOffLine_id) {
		List<String> ids = Arrays.asList(delTestWebOffLine_id);
		try {
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
			testWebOffLineService.deleteBatch(ids);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

}
