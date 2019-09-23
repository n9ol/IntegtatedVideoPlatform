package com.zzrenfeng.zhsx.controller.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

@Controller
@RequestMapping("/skin")
public class SkinController extends BaseController {

	@Resource
	private SysDictService SysDictService;

	@RequestMapping("/changeSkin")
	public void changeSkin(HttpServletResponse response, @RequestParam String skinName) {
		try {
			SysDictService.changeSkin(skinName);
			WriterUtils.toText(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toText(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

}
