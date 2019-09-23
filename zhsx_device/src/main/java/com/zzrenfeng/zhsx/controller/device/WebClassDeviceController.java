package com.zzrenfeng.zhsx.controller.device;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.service.WebClassDeviceService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-09-04 17:10:22
 * @see com.zzrenfeng.zhsx.controller.WebClassDevice
 */
@Controller
@RequestMapping(value = "/webclassdevice")
public class WebClassDeviceController extends BaseController {

	@Resource
	private WebClassDeviceService webClassDeviceService;

}
