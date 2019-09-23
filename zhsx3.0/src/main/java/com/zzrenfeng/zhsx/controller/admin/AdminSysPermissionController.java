package com.zzrenfeng.zhsx.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.service.SysPermissionService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-14 16:22:38
 * @see com.zzrenfeng.zhsx.controller.SysPermission
 */
@Controller
@RequestMapping(value = "/syspermission")
public class AdminSysPermissionController extends BaseController {

	@Resource
	private SysPermissionService sysPermissionService;

}
