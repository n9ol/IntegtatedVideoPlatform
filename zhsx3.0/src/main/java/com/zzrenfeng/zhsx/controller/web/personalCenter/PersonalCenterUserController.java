package com.zzrenfeng.zhsx.controller.web.personalCenter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysHistoryService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.util.DateUtil;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 个人中心 user 控制器
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/personalCenterUser")
public class PersonalCenterUserController extends BaseController {

	@Resource
	private UserService userService;
	@Resource
	private SysSchoolService sysSchoolService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private SysHistoryService sysHistoryService;
	@Resource
	private String platformLevel;
	@Resource
	private String platformLevelId;

	/**
	 * 进入个人中心基本信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/geren_jibenxinxi")
	public String geren_jibenxinxi(Model model) {
		return "/personalCenter/geren_jibenxinxi";
	}

	/**
	 * 得到用户基本信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/userInfo")
	public String userInfo(Model model) {
		User user = userService.findByKey(getUserId());

		// 获得省信息
		SysDict sysDict = new SysDict();
		sysDict.setKeyname("P");
		List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
		model.addAttribute("provinces", sysDicts);

		SysSchool school = new SysSchool();
		String countyId = user.getBak2();
		SysDict county = sysDictService.findByKey(countyId);
		if (county != null) {
			school.setCountyId(countyId);
			user.setCountyId(countyId);
			user.setCityId(county.getPid());
			SysDict city = sysDictService.findByKey(county.getPid());

			if (city != null) {
				user.setProvinceId(city.getPid());

				// 获得市级信息
				sysDict.setKeyname("C");
				sysDict.setPid(city.getPid());
				sysDicts = sysDictService.findSelective(sysDict);
				model.addAttribute("citys", sysDicts);

				// 获得区县
				sysDict.setKeyname("A");
				sysDict.setPid(city.getId());
				sysDicts = sysDictService.findSelective(sysDict);
				model.addAttribute("countys", sysDicts);

			}
		} else {

			if (platformLevel.equals("P")) {
				sysDict.setKeyname("C");
				sysDict.setPid(platformLevelId);
				sysDicts = sysDictService.findSelective(sysDict);
				model.addAttribute("citys", sysDicts);
			} else if (platformLevel.equals("C")) {
				sysDict.setKeyname("A");
				sysDict.setPid(platformLevelId);
				sysDicts = sysDictService.findSelective(sysDict);
				model.addAttribute("countys", sysDicts);
			} else if (platformLevel.equals("A")) {
				school.setCountyId(platformLevelId);
			}

		}

		// 获得学校
		List<SysSchool> schoolList = sysSchoolService.findSelective(school);

		model.addAttribute("user", user);
		model.addAttribute("schoolList", schoolList);
		model.addAttribute("platformLevel", platformLevel);
		model.addAttribute("platformLevelId", platformLevelId);

		return "/personalCenter/userInfo";
	}

	/**
	 * 修改用户基本信息
	 * 
	 * @param response
	 * @param user
	 */
	@RequestMapping("/updateUserInfo")
	public void updateUserInfo(HttpServletResponse response, User user) {
		try {
			userService.updateByKeySelective(user);
			if (getUserType().equals(User.userType_teachers)) {
				userService.updateUserExpByCompleteInfo(user);
			}
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 获得教师成长信息
	 * 
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/teacherGradeEx")
	public String teacherGradeEx(Model model) throws ParseException {
		User user = userService.findByKey(getUserId());

		// 获得用户等级
		int userGrade = userService.getUserGrade(user.getEXP());
		user.setUserGrade(userGrade);
		model.addAttribute("user", user);

		int currgExp = userService.getExp(userGrade); // 获得当前级所需经验值
		int nextgExp = userService.getExp(userGrade + 1);// 获得下级所需经验值
		model.addAttribute("currgExp", currgExp);
		model.addAttribute("nextgExp", nextgExp);
		model.addAttribute("currExp", user.getEXP());

		// 是否已经完成资料
		int isuf = 0;
		boolean isUserInfo = userService.isUserInfo(user);
		if (isUserInfo) {
			isuf = 1;
		}
		model.addAttribute("isuf", isuf);

		Date date = new Date();
		// 获得今天经验记录
		SysHistory sysh = new SysHistory();
		sysh.setUserId(getUserId());
		sysh.setPubFlag(SysHistory.PUBFLAG_H);
		sysh.setCreateTime(DateUtil.getDateDate(date, "yyyy-MM-dd"));
		List<SysHistory> syshList = sysHistoryService.findSelective(sysh);

		// 经验记录中 去除被关注的重复显示
		List<SysHistory> syshList1 = new ArrayList<SysHistory>();
		int exp = 0;
		for (int i = 0; i < syshList.size(); i++) {
			SysHistory s = syshList.get(i);
			String t = s.getPubType();// t
			String h = s.getPubFlag();// h
			if (SysHistory.PUBFLAG_H.equals(h) && SysHistory.PUBTYPE_T.equals(t)) {
				exp += Integer.parseInt(s.getBak() == null ? "0" : s.getBak());
			} else {
				syshList1.add(s);
			}
		}
		if (exp > 0) {
			SysHistory sysHistory = new SysHistory();
			sysHistory.setBak1("被关注一次,获得10经验值");
			syshList1.add(sysHistory);
		}

		model.addAttribute("syshList", syshList1);

		// 是否已完成发布课件
		sysh.setPubType(SysHistory.PUBTYPE_R);
		sysh.setStartTime(DateUtil.getDateDate(date, "yyyy-MM-dd"));
		sysh.setEndTime(DateUtil.getDateDate(date, "yyyy-MM-dd"));
		int expsum = Integer.valueOf(sysHistoryService.getExp(sysh));
		int isufR = 0;
		if (expsum >= 100) {
			isufR = 1;
		}
		model.addAttribute("isufR", isufR);

		// 是否已完成发布试卷
		sysh.setPubType(SysHistory.PUBTYPE_E);
		expsum = Integer.valueOf(sysHistoryService.getExp(sysh));
		int isufT = 0;
		if (expsum >= 100) {
			isufT = 1;
		}
		model.addAttribute("isufT", isufT);

		return "/personalCenter/teacherGradeEx";
	}

	/**
	 * 获得成长等级说明规则
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/gradeSystem")
	public String gradeSystem(Model model) {
		return "/personalCenter/gradeSystem";
	}

	/**
	 * 获得教师等级排行榜
	 * 
	 * @return
	 */
	@RequestMapping("/teacherGradeRank")
	public String teacherGradeRank(Model model) {
		User user = new User();
		user.setUserType(User.userType_teachers);
		user.setSortord("EXP");
		Page<User> pageInfo = userService.findPageSelective(user, 1, 6);
		List<User> userList = pageInfo.getResult();
		for (User user2 : userList) {
			user2.setUserGrade(userService.getUserGrade(user2.getEXP()));
		}
		model.addAttribute("userList", userList);
		return "/personalCenter/teacherGradeRank";
	}

	/**
	 * 进入用户修改密码页面
	 * 
	 * @return
	 */
	@RequestMapping("/modifyPassword")
	public String updataPassword() {
		return "/personalCenter/modifyPassword";
	}

	/**
	 * 验证旧密码是否正确
	 * 
	 * @param response
	 * @param oldPassword
	 * @return
	 */
	@RequestMapping("/validationOldPassword")
	public @ResponseBody boolean validationOldPassword(String oldPassword) {
		boolean isok = true;
		String password = new Md5Hash(oldPassword, getUserCode(), 2).toString();
		User s = userService.findByCodeAndpwd(getUserCode(), password);
		if (s == null) {
			isok = false;
		}
		return isok;
	}

	/**
	 * 修改用户密码
	 */
	@RequestMapping("/updateUserPassword")
	public void updateUserPassword(HttpServletResponse response, String password) {
		User user = new User();
		user.setId(getUserId());
		user.setPassword(new Md5Hash(password, getUserCode(), 2).toString());
		try {
			userService.updateByKeySelective(user);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 进入编辑头像页面
	 * 
	 * @return
	 */
	@RequestMapping("/editHeadPhoto")
	public String editHeadPhoto(Model model) {
		model.addAttribute("headPhotoPath", getUserFilePath());
		return "/personalCenter/editHeadPhoto";
	}

	/**
	 * 修改用户头像
	 * 
	 * @param response
	 * @param filePath
	 */
	@RequestMapping("/updateUserHeadPhoto")
	public void updateUserHeadPhoto(HttpServletResponse response, String filePath) {
		User user = new User();
		user.setId(getUserId());
		user.setFilePath(filePath);
		try {
			userService.updateByKeySelective(user);
			String oldheadPhotoPath = getUserFilePath();
			if (oldheadPhotoPath == null || oldheadPhotoPath.equals("")) {
				userService.updateUserExpByUploadHeadPortrait(getUserId());
			}
			getShiroUser().setFilePath(filePath);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 验证邮箱是否已存在
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping("/checkEmail")
	public @ResponseBody boolean checkEmail(String email) {
		boolean isok = true;
		User user = userService.findByEmail(email);
		if (user != null) {
			isok = false;
		}
		return isok;
	}

	/**
	 * 验证手机号是否已存在
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping("/checkPhone")
	public @ResponseBody boolean checkPhone(String phone) {
		boolean isok = true;
		User user = userService.findByPhone(phone);
		if (user != null) {
			isok = false;
		}
		return isok;
	}

}
