package com.zzrenfeng.zhsx.controller.admin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzrenfeng.zhsx.base.ExceptionMessage;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.SysLog;
import com.zzrenfeng.zhsx.model.SysPermission;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.SysLogService;
import com.zzrenfeng.zhsx.service.SysPermissionService;
import com.zzrenfeng.zhsx.service.SysRolePermissionService;
import com.zzrenfeng.zhsx.service.SysUserRoleService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.shiro.UserNamePasswordUserTypeToken;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.ValidationUtils;

/**
 * 后台登录
 * 
 * @author 田杰熠
 *
 */
@Controller
public class AdminIndexController extends BaseController {

	@Resource
	private SysLogService sysLogService;
	@Resource
	private UserService userService;
	@Resource
	private SysRolePermissionService sysRolePermissionService;
	@Resource
	private SysPermissionService sysPermissionService;
	@Resource
	private SysUserRoleService sysUserRoleService;

	/**
	 * 进入后台登录页
	 * 
	 * @return
	 */
	@RequestMapping("/loginPage")
	public String admin() {
		return "/admin/login/login";
	}

	/**
	 * 进入后台首页
	 * 
	 * @return
	 */
	@RequestMapping("/adminIndex")
	public String adminIndex(Model model) {
		List<String> roleIds = sysUserRoleService.findroleIdsByUserId(getUserId());
		if (roleIds != null && roleIds.size() > 0) {
			List<String> permissionIds = sysRolePermissionService.findPermissionIdSByRoleIds(roleIds);
			if (permissionIds != null && permissionIds.size() > 0) {
				List<SysPermission> sysPermissions = sysPermissionService.findSysPermissionByids(permissionIds);

				List<SysPermission> firstSysPermissions = new ArrayList<>();
				for (SysPermission sysPermission : sysPermissions) {
					if (sysPermission.getParentId().equals("0") && sysPermission.getResourceType().equals("menu")) {
						firstSysPermissions.add(sysPermission);
					}
				}
				model.addAttribute("firstSysPermissions", firstSysPermissions);
				model.addAttribute("sysPermissions", sysPermissions);
			}
		}
		return "/admin/login/index";
	}

	/**
	 * 后台欢迎页
	 * 
	 * @return
	 */
	@RequestMapping("/adminWelcome")
	public String adminWelcome() {
		return "/admin/login/welcome";
	}

	/**
	 * 后台登录
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws ExceptionMessage
	 * @throws ParseException
	 */
	@RequestMapping("/adminlogin")
	public String adminlogin(HttpServletResponse response, HttpServletRequest request)
			throws ExceptionMessage, ParseException {

		String username = request.getParameter("username");
		int res = ValidationUtils.checkCodePhoneEmail(username);
		if (res == 2) {
			User user = userService.findByPhone(username);
			if (user != null)
				username = user.getUserCode();
		} else if (res == 2) {
			User user1 = userService.findByEmail(username);
			if (user1 != null)
				username = user1.getUserCode();
		}
		String password = request.getParameter("password");
		Md5Hash md5 = new Md5Hash(password, username, 2);
		password = md5.toString();
		Subject subject = SecurityUtils.getSubject();
		UserNamePasswordUserTypeToken token = new UserNamePasswordUserTypeToken(username, password);
		try {
			subject.login(token);
		} catch (Exception e) {
			token.clear();
			throw new ExceptionMessage("账号或密码错误");
		}

		String bak = getUserBak();
		Date date = new Date();
		if (bak.equals("Y")) {
			String bak1 = getUserBak1();
			if (bak1.equals(User.bak1_no)) {
				throw new ExceptionMessage("该账户没有管理权限");
			} else {
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
			}
		} else {
			throw new ExceptionMessage("该账户已被禁用");
		}

		return "redirect:/adminIndex";
	}

}
