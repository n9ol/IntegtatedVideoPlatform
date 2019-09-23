package com.zzrenfeng.zhsx.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzrenfeng.zhsx.controller.base.BaseController;

@Controller
@RequestMapping("/skin")
public class AdminSkinController extends BaseController {

	/**
	 * 进入皮肤管理页面
	 * 
	 * @return
	 */
	@RequestMapping("/skinManagement")
	public String skinManagement() {
		System.out.println(11);
		return "/admin/skin/skinManagement";
	}

}
