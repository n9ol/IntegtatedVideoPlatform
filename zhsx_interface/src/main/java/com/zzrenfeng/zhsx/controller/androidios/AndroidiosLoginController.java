package com.zzrenfeng.zhsx.controller.androidios;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.AndroidiosModel;
import com.zzrenfeng.zhsx.model.SysLog;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.model.webSocket.WiselyResponse;
import com.zzrenfeng.zhsx.service.SysLogService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.shiro.UserNamePasswordUserTypeToken;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.ValidationUtils;

/**
 * 移动端接口 - 登录
 * 
 * @author 田杰熠
 */
@Controller
@RequestMapping("/androidiosLogin")
public class AndroidiosLoginController extends BaseController {

	@Resource
	private SysLogService sysLogService;
	@Resource
	private UserService userService;
	@Resource
	private SimpMessagingTemplate simpMessagingTemplate;

	/**
	 * 移动端登录
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping("/phonelogin")
	public AndroidiosModel phonelogin(HttpServletResponse response, HttpServletRequest request) throws ParseException {
		AndroidiosModel androidiosModel = new AndroidiosModel();
		Map<String, Object> hm = new HashMap<>();

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
		String password = request.getParameter("userpsw");
		Md5Hash md5 = new Md5Hash(password, username, 2);
		password = md5.toString();
		Subject subject = SecurityUtils.getSubject();
		UserNamePasswordUserTypeToken token = new UserNamePasswordUserTypeToken(username, password);
		try {
			subject.login(token);
		} catch (Exception e) {
			token.clear();
			hm.put("r", 0);
			hm.put("rs", "账号或密码错误");
			androidiosModel.setData(hm);
			return androidiosModel;
		}
		String bak = getUserBak();
		if (bak.equals("Y")) {
			SysLog log = new SysLog();
			log.setOperationname(getShiroUser().getNickName());
			log.setOperationmehtod("登录");
			log.setIssuccess(MessageUtils.SUCCESS);
			log.setReason(null);
			log.setOperationdate(new Date());
			log.setContent(null);
			sysLogService.recordLog(log);
			if (getUserType().equals(User.userType_teachers)) {
				userService.updateUserExp(getUserId());
			}
		} else {
			hm.put("r", 2);
			hm.put("rs", "该账户已被禁用");
			androidiosModel.setData(hm);
			return androidiosModel;
		}
		hm.put("r", 1);
		hm.put("rs", "登录成功");
		androidiosModel.setData(hm);
		return androidiosModel;
	}

	/**
	 * 扫码登录
	 * 
	 * @param channel
	 * @param username
	 * @param userpsw
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/QRCodeLoginPush")
	public AndroidiosModel QRCodeLoginPush(@RequestParam String channel, @RequestParam String username,
			@RequestParam String userpsw) {
		AndroidiosModel androidiosModel = new AndroidiosModel();
		final Map<String, Object> hm = new HashMap<>();
		try {
			String responseMessage = username + "," + userpsw;
			simpMessagingTemplate.convertAndSend("/topic/getResponse/" + channel, new WiselyResponse(responseMessage));
			hm.put("r", 1);
			hm.put("rs", MessageUtils.SUCCESS);
		} catch (Exception e) {
			hm.put("r", 0);
			hm.put("rs", MessageUtils.FAilURE);
			e.printStackTrace();
		}
		androidiosModel.setData(hm);
		return androidiosModel;
	}

}
