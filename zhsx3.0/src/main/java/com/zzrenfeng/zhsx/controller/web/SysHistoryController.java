package com.zzrenfeng.zhsx.controller.web;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.service.SysHistoryService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 收藏,操作记录类
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/sysHistory")
public class SysHistoryController extends BaseController {

	@Resource
	private SysHistoryService sysHistoryService;

	/**
	 * 添加收藏或操作记录到数据库
	 * 
	 * @param response
	 * @param sysHistory
	 */
	@RequestMapping("/insterSysHistory")
	public void insterSysHistory(HttpServletResponse response, @Validated SysHistory sysHistory) {
		try {
			Date date = new Date();
			sysHistory.setUserId(getUserId());
			sysHistory.setCreateTime(date);
			sysHistory.setModiyTime(date);
			sysHistoryService.insert(sysHistory);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 删除数据库记录
	 * 
	 * @param response
	 * @param pubFlag
	 * @param pubType
	 * @param pubId
	 */
	@RequestMapping("/delSysHistory")
	public void delSysHistory(HttpServletResponse response, String pubFlag, String pubType, String pubId) {
		try {
			sysHistoryService.deleteByPub(pubFlag, pubType, pubId, getUserId());
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

}
