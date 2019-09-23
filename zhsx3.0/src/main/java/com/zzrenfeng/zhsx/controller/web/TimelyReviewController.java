package com.zzrenfeng.zhsx.controller.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.model.WebComments;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.WebCommentsService;

@Controller
@RequestMapping("/timelyReview")
public class TimelyReviewController extends BaseController {

	@Resource
	private WebCommentsService webCommentsService;

	@Resource
	private UserService userService;

	/**
	 * 即时品论页面
	 * 
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/timelyWindow")
	public String timelyWindow(HttpServletRequest request, WebComments webComments, HttpServletResponse response,
			Model model) {

		User u = new User();
		if (isLogined()) {
			u = userService.findByKey(getUserId());
		}

		// 此处有本地ip_port 改为访问的ip_port(解决内网情况下,通过映射ip_port访问平台,无法连接本地ip_port问题)
		int port = request.getServerPort(); // 取得服务器端口
		String ipport = request.getServerName() + ":" + port; // 取得服务器IP

		model.addAttribute("u", u);
		model.addAttribute("sourceId", webComments.getContextId());
		model.addAttribute("ip_port", ipport);
		model.addAttribute("mType", webComments.getContextType());
		return "/web/pg/timelyWindow";
	}
}
