package com.zzrenfeng.zhsx.controller.online;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.LoPgGroupUser;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.LoPgGroupUserService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-29 16:52:45
 * @see com.zzrenfeng.zhsx.controller.LoPgGroupUser
 */
@Controller
@RequestMapping(value = "/adminLopgGroupUser")
public class AdminLoPgGroupUserController extends BaseController {

	@Resource
	private LoPgGroupUserService loPgGroupUserService;
	@Resource
	private UserService userService;

	@RequestMapping("/pgGroupUser")
	public String pgGroupUser(@RequestParam String pgGroupId, String search, Model model) {

		LoPgGroupUser loPgGroupUser = new LoPgGroupUser();
		loPgGroupUser.setPgGroupId(pgGroupId);
		loPgGroupUser.setSearch(search);
		List<LoPgGroupUser> loPgGroupUsers = loPgGroupUserService.findSelective(loPgGroupUser);

		model.addAttribute("loPgGroupUsers", loPgGroupUsers);
		model.addAttribute("pgGroupId", pgGroupId);
		model.addAttribute("searchval", search);
		return "/admin/pgGroup/editPgGroupUser";
	}

	@RequestMapping("/addPgGroupUser")
	public String addPgGroupUser(@ModelAttribute("pgGroupId") String pgGroupId, String[] userIds, Model model) {
		model.addAttribute("userIds", userIds);
		return "/admin/pgGroup/addPgGroupUser";
	}

	@RequestMapping("/getPgGroupUser")
	public String getPgGroupUser(String[] userIds, String search, Model model) {

		// 获取权限下所有非学生用户-(评估人员)
		User user = new User();

		String bak1 = getUserBak1();
		String bak2 = getUserBak2();
		if (bak1.equals(User.bak1_operator)) {
			user.setAuthority(bak1);
		} else if (!bak1.equals(User.bak1_no)) {
			user.setAuthority(bak1);
			user.setCountyId(bak2);
			if (bak1.equals(User.bak1_schoool)) {
				user.setSchoolId(getUserSchoolId());
			}
		}
		if (userIds != null && userIds.length > 0) {
			user.setUserIds(userIds);
		}
		user.setSearch(search);
		List<User> pgUserList = userService.findNotStudents(user);

		model.addAttribute("pgUserList", pgUserList);
		return "/admin/pgGroup/pgGroupUserData";
	}

	@RequestMapping("/insterPgGroupUser")
	public void insterPgGroupUser(String pgGroupId, String[] pgUserIds, HttpServletResponse response) {
		try {
			loPgGroupUserService.batchInster(pgGroupId, pgUserIds);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	@RequestMapping("/delPgGroupUser")
	public void delPgGroupUser(@RequestParam String id, HttpServletResponse response) {
		try {
			loPgGroupUserService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	@RequestMapping("/batchDelPgGroupUser")
	public void batchDelPgGroupUser(String[] del_id, HttpServletResponse response) {
		try {
			loPgGroupUserService.batchDel(del_id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

}
