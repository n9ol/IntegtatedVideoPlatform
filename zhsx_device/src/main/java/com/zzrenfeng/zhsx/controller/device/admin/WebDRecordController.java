package com.zzrenfeng.zhsx.controller.device.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.WebDeviceRecord;
import com.zzrenfeng.zhsx.service.WebDeviceRecordService;

/**
 * 设备运行记录表
 * 
 * @author David
 * @version 2018-01-11 
 * @see com.zzrenfeng.zhsx.controller.WebDeviceRecord
 */
@Controller
@RequestMapping(value = "/webdevicerecord/admin/")
public class WebDRecordController extends BaseController {

	@Resource
	private WebDeviceRecordService webDeviceRecordService;
	
	@RequestMapping("/listDeviceRecordBycontion")
	public String listDeviceRecordBycontion(HttpServletRequest request, Model model, Integer p) {
		if (p == null)
			p = 1;

		WebDeviceRecord webDRecord = new WebDeviceRecord();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String deviceCode = request.getParameter("deviceCode");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			if (startTime != null && StringUtils.isNotEmpty(startTime)) {
				Date startTimeD = sdf.parse(startTime);
				webDRecord.setDrStartTime(startTimeD);
			}
			if (endTime != null && StringUtils.isNotEmpty(endTime)) {
				Date endTimeD = sdf.parse(endTime);
				webDRecord.setDrEndTime(endTimeD);
			}
		} catch (ParseException e) {
			// TODO
			e.printStackTrace();
		}
		
		if ((deviceCode != null) && (!StringUtils.isEmpty(deviceCode))) {
			webDRecord.setDeviceCode(deviceCode);
		}
		/* 查询数据 */
		Page<WebDeviceRecord> pageInfo = webDeviceRecordService.findPageSelective(webDRecord, p, 10);
		int pages = pageInfo.getPages(); // 总页数
		List<WebDeviceRecord> ldr = pageInfo.getResult();
		model.addAttribute("pages", pages);
		model.addAttribute("pageNum", p);// 当前页
		model.addAttribute("ldr", ldr);

		/* 传递参数 */
		model.addAttribute("startTimeAgu", startTime);
		model.addAttribute("endTimeAgu", endTime);
		model.addAttribute("deviceCodeAgu", deviceCode);
		
		return "/admin/device/devicerecord_list";
	}
	
	@RequestMapping(value = "/findDRecordById")
	public String findDRecordById(HttpServletRequest request, HttpServletResponse response, Model model) {
		String dr_id = request.getParameter("dr_id");
		WebDeviceRecord webDRecord = webDeviceRecordService.findByKey(dr_id);
		model.addAttribute("webDRecord", webDRecord);

		return "/admin/device/devicerecord_view";
	}

	
	/**
	 * 删除设备记录信息
	 * @param 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deviceDeleteDRecord")
	public String deviceDeleteDRecord(String ids, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		
		webDeviceRecordService.deleteBatchByKeys(ids);
		
		return "redirect:/webdevicerecord/admin/listDeviceRecordBycontion";
	}
	
	/**
	 * 清空所有设备记录
	 * @param 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteAllDRecord")
	public String deleteAllDRecord(String ids, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		webDeviceRecordService.deleteAllDRecord();
		return "redirect:/webdevicerecord/admin/listDeviceRecordBycontion";
	}
}
