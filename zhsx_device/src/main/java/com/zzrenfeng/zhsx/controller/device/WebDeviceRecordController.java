package com.zzrenfeng.zhsx.controller.device;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.service.WebDeviceRecordService;

/**
 * 设备运行记录表
 * 
 * @copyright {@link zzrenfeng.com}
 * @author David
 * @version 2017-08-08 14:27:49
 * @see com.zzrenfeng.zhsx.controller.WebDeviceRecord
 */
@Controller
@RequestMapping(value = "/webdevicerecord")
public class WebDeviceRecordController extends BaseController {

	@Resource
	private WebDeviceRecordService webDeviceRecordService;

}
