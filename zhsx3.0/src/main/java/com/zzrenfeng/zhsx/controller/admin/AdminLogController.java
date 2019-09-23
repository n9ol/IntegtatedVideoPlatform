package com.zzrenfeng.zhsx.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.SysLog;
import com.zzrenfeng.zhsx.service.SysLogService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 操作记录日志管理
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/log")
public class AdminLogController extends BaseController {

	@Resource
	private SysLogService logService;

	@RequestMapping("/sysLog")
	public String sysLog(Model model, Integer p, SysLog log) {
		if (p == null)
			p = 1;
		Page<SysLog> pageInfo = logService.findPageSelective(log, p, 12);
		int pages = pageInfo.getPages();
		List<SysLog> lists = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNum", p);// 当前页
		model.addAttribute("pages", pages);// 总页数
		model.addAttribute("lists", lists);
		model.addAttribute("search", log.getSearch());
		return "/admin/log/sysLog";
	}

	/**
	 * 批量删除日志
	 * 
	 * @param del_id
	 */
	@RequestMapping("/batchDelLog")
	public void batchDelLog(HttpServletResponse response, String[] del_id) {
		try {
			logService.delBatchLog(del_id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 清空日志
	 * 
	 * @param response
	 */
	@RequestMapping("/emptyLog")
	public void emptyLog(HttpServletResponse response) {
		try {
			logService.emptyLog();
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

}
