package com.zzrenfeng.zhsx.controller.web;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zzrenfeng.zhsx.base.ExceptionMessage;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.SysLog;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.SysLogService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.shiro.UserNamePasswordUserTypeToken;
import com.zzrenfeng.zhsx.util.BaseHttpService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.RemoteConnectUtil;
import com.zzrenfeng.zhsx.util.Utils;
import com.zzrenfeng.zhsx.util.ValidationUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 登录
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

	@Resource
	private SysLogService sysLogService;
	@Resource
	private UserService userService;
	@Resource
	private Environment environment;

	/**
	 * 进入前台登录页
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/denglu")
	public Object denglu(HttpServletRequest request, HttpServletResponse response) {
		String[] deciceArray = new String[] { "android", "iphone", "windows phone" };
		String requestHeader = request.getHeader("user-agent").toLowerCase();
		if (requestHeader != null) {
			for (int i = 0; i < deciceArray.length; i++) {
				if (requestHeader.indexOf(deciceArray[i]) > 0) {
					return androidiosLogin();
				}
			}
		}
		return pcDenglu(request, response);
	}

	/**
	 * pc端登录
	 * 
	 * @return
	 */
	public ModelAndView pcDenglu(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView Mav = new ModelAndView();
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String webUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + "/"
				+ request.getRequestURI().split("/")[1];
		Mav.addObject("webUrl", webUrl);
		Mav.addObject("channel", uuid);
		Mav.setViewName("/zhsx/denglu");
		return Mav;
	}

	@ResponseBody
	public Map<String, Object> androidiosLogin() {
		Map<String, Object> hm = new HashMap<>();
		hm.put("isNeedLogin", 1);
		hm.put("currPage", 0);
		hm.put("totalPage", 0);
		hm.put("data", "请登录");
		hm.put("loginStatus", "N");
		return hm;
	}

	/**
	 * 登录
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws ExceptionMessage
	 * @throws ParseException
	 */
	@RequestMapping("/denglu.do")
	public String adminlogin(HttpServletResponse response, HttpServletRequest request)
			throws ExceptionMessage, ParseException {
		String username = request.getParameter("username");
		int res = ValidationUtils.checkCodePhoneEmail(username);
		switch (res) {
		case 2:
			User user = userService.findByPhone(username);
			if (user != null)
				username = user.getUserCode();
			break;
		case 3:
			User user1 = userService.findByEmail(username);
			if (user1 != null)
				username = user1.getUserCode();
			break;
		}
		String password = request.getParameter("password");
		Md5Hash md5 = new Md5Hash(password, username, 2);
		String passwordMd5 = md5.toString();
		Subject subject = SecurityUtils.getSubject();
		UserNamePasswordUserTypeToken token = new UserNamePasswordUserTypeToken(username, passwordMd5);
		try {
			subject.login(token);
		} catch (Exception e) {
			token.clear();
			throw new ExceptionMessage("账号或密码错误");
		}

		String bak = getUserBak();
		Date date = new Date();
		if (bak.equals("Y")) {
			SysLog log = new SysLog();
			log.setOperationname(getShiroUser().getNickName());
			log.setOperationmehtod("登录");
			log.setIssuccess(MessageUtils.SUCCESS);
			log.setReason(null);
			log.setOperationdate(date);
			log.setContent(null);
			sysLogService.recordLog(log);

			if (getUserType().equals(User.userType_teachers)) {
				userService.updateUserExp(getUserId());
			}

			User user = new User();
			user.setId(getUserId());
			user.setModiyTime(date);
			userService.updateByKeySelective(user);
		} else {
			throw new ExceptionMessage("该账户已被禁用");
		}
		String url = "/index.action";
		Session session = subject.getSession(false);
		if (session != null) {
			SavedRequest savedRequest = WebUtils.getSavedRequest(request);
			if (savedRequest != null) {
				url = savedRequest.getRequestUrl();
				String ctx = request.getContextPath();
				url = url.substring(ctx.length());
			}
		}

		recordToRedis(request, username, password);
		return "redirect:" + url;
	}

	/**
	 * 记录登录信息到_redis
	 * 
	 * @param username
	 * @param password
	 */
	private void recordToRedis(HttpServletRequest request, String username, String password) {
		String isEClassBrand = environment.getProperty("is.EClassBrand");
		if ("Y".equals(isEClassBrand)) {
			String url = environment.getProperty("EClassBrand.path")
					+ environment.getProperty("storeAccountPassword.path");
			String ipAdrress = Utils.getIpAdrress(request);
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("username", username);
			paramMap.put("password", password);
			paramMap.put("ipAdrress", ipAdrress);
			BaseHttpService.getResponseResult(paramMap, url);
		}
	}

	/**
	 * 错误页面
	 * 
	 * @return
	 */
	@RequestMapping("/err/401")
	public String err401() {
		return "/err/err401";
	}

	/**
	 * 安全退出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/backHomepage")
	public void backHomepage(HttpServletRequest request, HttpServletResponse response) {
		WriterUtils.toText(response, "安全退出");
		String ipAdrress = Utils.getIpAdrress(request);
		String url = environment.getProperty("EClassBrand.path") + "/logoutEClassBrand?ipAdrress=" + ipAdrress;
		RemoteConnectUtil.getRemoteConnect(url);
	}

}
