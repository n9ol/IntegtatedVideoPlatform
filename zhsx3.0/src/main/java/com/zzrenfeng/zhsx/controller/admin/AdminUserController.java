package com.zzrenfeng.zhsx.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.ExceptionMessage;
import com.zzrenfeng.zhsx.constant.Constant;
import com.zzrenfeng.zhsx.controller.base.BaseController;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysRole;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.SysUserRole;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysRole;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysUser;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysRoleService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.SysUserRoleService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.eclassbrand.sys.ESysRoleService;
import com.zzrenfeng.zhsx.service.eclassbrand.sys.ESysUserRoleService;
import com.zzrenfeng.zhsx.service.eclassbrand.sys.ESysUserService;
import com.zzrenfeng.zhsx.service.usersynchronization.UserSynchronizationService;
import com.zzrenfeng.zhsx.shiro.UserNamePasswordUserTypeToken;
import com.zzrenfeng.zhsx.util.ExcelUtil;
import com.zzrenfeng.zhsx.util.MessageUtils;
import com.zzrenfeng.zhsx.util.ValidationUtils;
import com.zzrenfeng.zhsx.util.WriterUtils;

/**
 * 用户管理
 * 
 * @author 田杰熠
 *
 */
@Controller
@RequestMapping("/user")
public class AdminUserController extends BaseController {

	@Resource
	private UserService userService;
	@Resource
	private SysSchoolService sysSchoolService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private SysRoleService sysRoleService;
	@Resource
	private SysUserRoleService sysUserRoleService;
	@Resource
	private String platformLevel;
	@Resource
	private String platformLevelId;
	@Resource
	private Environment env;
	@Resource
	private ESysRoleService eSysRoleService;
	@Resource
	private ESysUserService eSysUserService;
	@Resource
	private ESysUserRoleService eSysUserRoleService;
	@Resource
	private UserSynchronizationService userSynchronizationService;

	/**
	 * 用户密码md5加密次数
	 */
	private final int MD5_ENCRYPT_NUMBER = 2;

	/**
	 * 进入用户列表
	 * 
	 * @return
	 * @throws ExceptionMessage
	 */
	@RequestMapping("/usersList")
	public String usersList(Model model, Integer p, User user) throws ExceptionMessage {
		String bak1 = getUserBak1();
		if (bak1.equals(User.bak1_no)) {
			throw new ExceptionMessage("没有权限");
		}
		String bak2 = getUserBak2();
		user.setAuthority(bak1);
		switch (bak1) {
		case User.bak1_schoool:
			user.setSchoolId(getUserSchoolId());
			break;
		default:
			user.setCountyId(bak2);
			break;
		}

		if (p == null)
			p = 1;
		Page<User> pageInfo = userService.findPageSelective(user, p, 9);
		int pages = pageInfo.getPages();
		List<User> lists = pageInfo.getResult();
		long total = pageInfo.getTotal();
		int pageSize = pageInfo.getPageSize();

		model.addAttribute("total", total);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNum", p);// 当前页
		model.addAttribute("pages", pages);// 总页数
		model.addAttribute("lists", lists);
		model.addAttribute("search", user.getSearch());

		/**
		 * 是否为校级平台
		 */
		String schoolLevel = env.getProperty("school.level");
		model.addAttribute("schoolLevel", schoolLevel);

		return "/admin/user/usersList";
	}

	/**
	 * 进入添加用户页面
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/addUser")
	public String addUser(HttpServletResponse response, HttpServletRequest request, Model model) {
		String bak1 = getUserBak1();
		String bak2 = getUserBak2();

		SysDict sysDict = new SysDict();
		SysSchool school = new SysSchool();
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			school.setAuthority(bak1);
			List<String> ids = userService.getUserSchoolIds(bak1, bak2, getUserSchoolId());
			if (ids != null && ids.size() > 0) {
				school.setIds(ids);
			}
			sysDict.setId(sysDictService.findByKey(sysDictService.findByKey(bak2).getPid()).getPid());
		}

		// 获取平台对应地区
		if (platformLevel == null || platformLevel.equals("") || platformLevel.equals("N")) {
			sysDict.setKeyname("P");
		} else if (platformLevel.equals("P")) {
			sysDict.setKeyname("C");
			sysDict.setPid(platformLevelId);

		} else if (platformLevel.equals("C")) {
			sysDict.setKeyname("A");
			sysDict.setPid(platformLevelId);
		} else if (platformLevel.equals("A")) {
			sysDict.setKeyname("T");
			sysDict.setPid(platformLevelId);
			model.addAttribute("platformLevelId", platformLevelId);
		}
		if (!platformLevel.equals("T")) {
			List<SysDict> sysDicts = sysDictService.findSelective(sysDict);
			model.addAttribute("sysDicts", sysDicts);
		}
		model.addAttribute("platformLevel", platformLevel);

		// 获得学校
		List<SysSchool> schools = sysSchoolService.findSelective(school);
		model.addAttribute("schools", schools);

		/**
		 * 是否为校级平台
		 */
		String schoolLevel = env.getProperty("school.level");
		model.addAttribute("schoolLevel", schoolLevel);
		String schoolId = env.getProperty("school.id");
		model.addAttribute("schoolId", schoolId);

		return "/admin/user/addUser";
	}

	/**
	 * 验证账号是否唯一
	 * 
	 * @param userCode
	 * @return
	 */
	@RequestMapping("/checkUserCodeSole")
	public @ResponseBody boolean checkUserCodeSole(String userCode) {
		boolean res = true;
		User user = userService.findByUserCode(userCode);
		if (user != null)
			res = false;
		return res;
	}

	/**
	 * 验证手机号是否唯一
	 * 
	 * @param phone
	 * @return
	 */
	@RequestMapping("/checkUserPhoneSole")
	@ResponseBody
	public boolean checkUserPhoneSole(String phone) {
		boolean res = true;
		User user = userService.findByPhone(phone);
		if (user != null)
			res = false;
		return res;
	}

	/**
	 * 验证邮箱是否唯一
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping("/checkUserEmailSole")
	@ResponseBody
	public boolean checkUserEmailSole(String email) {
		boolean res = true;
		User user = userService.findByEmail(email);
		if (user != null)
			res = false;
		return res;
	}

	/**
	 * 添加用户到数据库
	 * 
	 * @param response
	 * @param user
	 */
	@RequestMapping("/insertUser")
	public void insertUser(HttpServletResponse response, @Validated User user) {
		Date date = new Date();
		String password = user.getPassword();
		user.setBak("Y");
		user.setBak1("NA");
		user.setCreateTime(date);
		user.setModiyTime(date);
		user.setEXP(50);
		user.setPassword(new Md5Hash(password, user.getUserCode(), 2).toString());
		try {
			userService.insert(user);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
		insertEUser(user, password);
	}

	/**
	 * 同步添加电子班牌用户
	 * 
	 * @param user
	 */
	private void insertEUser(User user, String password) {
		String iSEClassBrand = env.getProperty("is.EClassBrand");
		if ("Y".equals(iSEClassBrand)) {
			String salt = UUID.randomUUID().toString();
			String passwordMd5 = new Md5Hash(password, user.getUserCode() + salt, MD5_ENCRYPT_NUMBER).toString();
			ESysUser t = new ESysUser();
			t.setUserCode(user.getUserCode());
			t.setNickname(user.getNickName());
			t.setPassword(passwordMd5);
			t.setSalt(salt);
			t.setState(Constant.USER_STATE_NORMAL);
			eSysUserService.insert(t);
		}
	}

	/**
	 * 删除用户
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delUser")
	public void delUser(HttpServletResponse response, String id) {
		deleteEUser(id);
		try {
			userService.deleteByKey(id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
	}

	/**
	 * 同步删除电子班牌用户
	 * 
	 * @param id
	 */
	private void deleteEUser(String id) {
		String iSEClassBrand = env.getProperty("is.EClassBrand");
		if ("Y".equals(iSEClassBrand)) {
			User user = userService.findByKey(id);
			ESysUser eSysUser = eSysUserService.findByUserCode(user.getUserCode());
			if (eSysUser != null) {
				eSysUserService.deleteByKey(eSysUser.getId());
			}
		}
	}

	/**
	 * 禁用或启用用户
	 * 
	 * @param response
	 * @param id
	 * @param bak
	 */
	@RequestMapping("/balUser")
	public void balUser(HttpServletResponse response, String id, String bak) {
		try {
			userService.updateUserState(id, bak);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
		changeEUserState(id, bak);
	}

	/**
	 * 改变用户状态
	 * 
	 * @param id
	 * @param bak
	 */
	private void changeEUserState(String id, String bak) {
		String iSEClassBrand = env.getProperty("is.EClassBrand");
		if ("Y".equals(iSEClassBrand)) {
			User user = userService.findByKey(id);
			ESysUser eSysUser = eSysUserService.findByUserCode(user.getUserCode());
			int state = 1;
			if ("N".equals(bak)) {
				state = 2;
			}
			eSysUserService.changeUserState(eSysUser.getId(), state);
		}
	}

	/**
	 * 进入授权页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/authorizeUser")
	public String authorizeUser(Model model, String id) {
		// 获得用户信息
		User user = userService.findByKey(id);
		model.addAttribute("user", user);
		model.addAttribute("userbak1", getUserBak1());

		String bak1 = getUserBak1(); // 是否为管理员
		String bak2 = getUserBak2(); // 所在地区Id
		// 获得学校
		SysSchool school = new SysSchool();
		if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
			school.setAuthority(bak1);
			List<String> ids = userService.getUserSchoolIds(bak1, bak2, getUserSchoolId());
			if (ids != null && ids.size() > 0) {
				school.setIds(ids);
			}
		}
		List<SysSchool> schools = sysSchoolService.findSelective(school);
		model.addAttribute("schools", schools);

		// 获得特殊管理角色
		List<SysRole> sysRoles = sysRoleService.findAll();
		model.addAttribute("sysRoles", sysRoles);
		// 获得拥有的特殊角色
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setUserId(id);
		List<SysUserRole> sysUserRoles = sysUserRoleService.findSelective(sysUserRole);
		model.addAttribute("sysUserRoles", sysUserRoles);
		model.addAttribute("platformLevel", platformLevel);

		/**
		 * 是否为校级平台
		 */
		String schoolLevel = env.getProperty("school.level");
		model.addAttribute("schoolLevel", schoolLevel);
		String schoolId = env.getProperty("school.id");
		model.addAttribute("schoolId", schoolId);
		String iSEClassBrand = env.getProperty("is.EClassBrand");
		model.addAttribute("iSEClassBrand", iSEClassBrand);
		if ("Y".equals(iSEClassBrand)) {
			String userId = "";
			ESysUser eSysUser = eSysUserService.findByUserCode(user.getUserCode());
			if (eSysUser != null) {
				userId = eSysUser.getId();
			}
			List<ESysRole> listESysRole = eSysRoleService.findAllAvailable(userId);
			model.addAttribute("listESysRole", listESysRole);
		}
		return "/admin/user/authorizeUser";
	}

	/**
	 * 修改用户是否为管理员
	 * 
	 * @param response
	 * @param user
	 * @param isadmin
	 * @param role_id
	 *            综合视讯权限角色
	 * @param roleIds
	 *            电子班牌权限角色
	 */
	@RequestMapping("/updateUserIsadmin")
	public void updateUserIsadmin(HttpServletResponse response, User user, boolean isadmin, String[] role_id,
			String[] roleIds) {
		if (isadmin) {
			if (user.getBak1() == null || user.getBak1().equals("") || role_id == null || role_id.length == 0) {
				WriterUtils.toHtml(response, "综合视讯管理权都不能为空!");
				return;
			}
		}
		try {
			userService.updateUserIsadmin(user, isadmin, role_id);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
		updateEUserAuthority(user, roleIds);
	}

	/**
	 * 同步更新用户电子班牌的管理权限
	 * 
	 * @param user
	 * @param roleIds
	 */
	private void updateEUserAuthority(User user, String[] roleIds) {
		String iSEClassBrand = env.getProperty("is.EClassBrand");
		if ("Y".equals(iSEClassBrand)) {
			User user2 = userService.findByKey(user.getId());
			ESysUser eSysUser = eSysUserService.findByUserCode(user2.getUserCode());
			eSysUserRoleService.batchInsertUserRole(eSysUser.getId(), roleIds);
		}
	}

	/**
	 * 进入批量添加页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/batchAddUser")
	public String batchAddUser(Model model) {
		// 获得学校
		// SysSchool school = new SysSchool();
		// String bak1 = getUserBak1();
		// String bak2 = getUserBak2();
		// if (!bak1.equals(User.bak1_no) && !bak1.equals(User.bak1_operator)) {
		// school.setAuthority(bak1);
		// List<String> ids = userService.getUserSchoolIds(bak1, bak2,
		// getUserSchoolId());
		// if (ids != null && ids.size() > 0)
		// school.setIds(ids);
		// }
		// List<SysSchool> schools = sysSchoolService.findSelective(school);
		// model.addAttribute("schools", schools);

		return "/admin/user/batchAddUser";
	}

	/**
	 * 批量添加用户
	 * 
	 * @param response
	 * @param request
	 * @param filePath
	 * @param userType
	 * @param schoolId
	 */
	@RequestMapping("/batchInsertUser")
	public void batchInsertUser(HttpServletResponse response, HttpServletRequest request, String filePath,
			String schoolId) {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
		filePath = uploadPath + filePath;
		Map<String, Object> hm = null;
		try {
			hm = ExcelUtil.getDataFromExcel(filePath);
		} catch (Exception e1) {
			WriterUtils.toHtml(response, "execl导入失败");
		}
		if (hm == null) {
			WriterUtils.toHtml(response, "execl导入失败");
			return;
		}
		Sheet sheet = (Sheet) hm.get("sheet");
		Row rowHead = (Row) hm.get("rowHead");
		Integer totalRowNum = (Integer) hm.get("totalRowNum");
		// 判断表头是否正确
		if (rowHead.getPhysicalNumberOfCells() != 7) {
			WriterUtils.toHtml(response, "表头的数量不对,请下载导入模板");
		} else {
			Date date = new Date();
			String userCode = null;
			String pwd = null;
			String nickName = null;
			String userType = null;
			String schoolName = null;
			String phone = null;
			String email = null;

			StringBuilder errorMessage = new StringBuilder();
			StringBuilder userCodes = new StringBuilder();
			List<User> userList = new ArrayList<User>();
			for (int i = 1; i <= totalRowNum; i++) {
				User user1 = new User();
				user1.setBak("Y");
				user1.setBak1("NA");
				user1.setCreateTime(date);
				user1.setModiyTime(date);
				user1.setEXP(50);

				// 获得第i行对象
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}

				// 获得获得第i行第0列的 String类型对象
				Cell cell = row.getCell((short) 0);
				userCode = ExcelUtil.formatCell(cell);
				if (userCode == null || userCode.equals("")) {
					errorMessage.append("第" + (i + 1) + "行的账号不能为空<br>");
					continue;
				}
				if (!ValidationUtils.checkLetterAndDigit(userCode) || ValidationUtils.isInteger(userCode)) {
					errorMessage.append("第" + (i + 1) + "行的账号必须由3到16位字母与数字组成的字符串<br>");
					continue;
				}
				if (!checkUserCodeSole(userCode)) {
					errorMessage.append("第" + (i + 1) + "行的账号已存在<br>");
					continue;
				}
				if (userCodes.indexOf(userCode) != -1) {
					errorMessage.append("第" + (i + 1) + "行的账号不能与其他行重复<br>");
					continue;
				}
				userCodes.append(userCode + ",");
				user1.setUserCode(userCode);

				cell = row.getCell((short) 1);
				pwd = ExcelUtil.formatCell(cell);
				if (pwd != null && !pwd.equals("") && ValidationUtils.checkStringLength(pwd, 6, 16)) {
					user1.setPassword(new Md5Hash(pwd, userCode, 2).toString());
				} else {
					errorMessage.append("第" + (i + 1) + "行的密码必须由6到16位任意字符组成<br>");
					continue;
				}

				cell = row.getCell((short) 2);
				nickName = ExcelUtil.formatCell(cell);
				if (nickName != null && !nickName.equals("")) {
					user1.setNickName(nickName);
				} else {
					errorMessage.append("第" + (i + 1) + "行的昵称不能为空<br>");
					continue;
				}

				cell = row.getCell((short) 3);
				userType = ExcelUtil.formatCell(cell);
				userType = userService.getUserType(userType);
				if (userType != null) {
					user1.setUserType(userType);
				} else {
					errorMessage.append("第" + (i + 1) + "行的用户类型不能为空或书写不正确<br>");
					continue;
				}

				cell = row.getCell((short) 4);
				schoolName = ExcelUtil.formatCell(cell);
				if (schoolName == null || schoolName.equals("")) {
					errorMessage.append("第" + (i + 1) + "行的学校不能为空<br>");
					continue;
				}

				SysSchool school = new SysSchool();
				school.setSchoolName(schoolName);
				List<SysSchool> schools = sysSchoolService.findSelective(school);
				if (schools == null || schools.size() <= 0) {
					errorMessage.append("第" + (i + 1) + "行的学校不存在<br>");
					continue;
				}
				user1.setSchoolId(schools.get(0).getId());
				user1.setBak2(schools.get(0).getCountyId());

				cell = row.getCell((short) 5);
				phone = ExcelUtil.formatCell(cell);
				if (phone != null && !phone.equals("") && !checkUserPhoneSole(phone)) {
					errorMessage.append("第" + (i + 1) + "行的手机号已存在<br>");
					continue;
				} else {
					phone = null;
				}
				user1.setPhone(phone);

				cell = row.getCell((short) 6);
				email = ExcelUtil.formatCell(cell);
				if (email != null && !email.equals("") && !checkUserEmailSole(email)) {
					errorMessage.append("第" + (i + 1) + "行的邮箱已存在<br>");
					continue;
				} else {
					email = null;
				}
				user1.setEmail(email);

				userList.add(user1);
			}
			try {
				if (userList != null && userList.size() > 0) {
					userService.insertBatch(userList);
				}
				String errorMessageString = errorMessage.toString();
				if (errorMessageString == null || "".equals(errorMessageString)) {
					WriterUtils.toHtml(response, MessageUtils.SUCCESS);
				} else {
					WriterUtils.toHtml(response, errorMessageString);
				}
			} catch (Exception e) {
				WriterUtils.toHtml(response, MessageUtils.FAilURE);
				e.printStackTrace();
			} finally {
				// 删除上传的文件
				File f = new File(filePath);
				if (f.exists()) {
					f.delete();
				}
			}
		}
	}

	/**
	 * 密码初始化
	 * 
	 * @param response
	 * @param id
	 */
	@RequestMapping("/initializePassword")
	public void initializePassword(HttpServletResponse response, @RequestParam String id, String userCode) {
		// User user = new User();
		// user.setId(id);
		String password = "";
		try {
			password = env.getProperty("initialize.password").trim();
			if (password == null || "".equals(password)) {
				password = "111111";
			}
		} catch (Exception e1) {
			password = "111111";
			e1.printStackTrace();
		}
		// STRING PASSWORDMD5 = NEW MD5HASH(PASSWORD, USERCODE, 2).TOSTRING();
		// USER.SETPASSWORD(PASSWORDMD5);
		try {
			// userService.updateByKeySelective(user);

			userSynchronizationService.updatePasswordSynchronization(userCode, password);
			WriterUtils.toHtml(response, MessageUtils.SUCCESS + ",初始密码为:" + password);
		} catch (Exception e) {
			WriterUtils.toHtml(response, MessageUtils.FAilURE);
			e.printStackTrace();
		}
		// initializeEUserPassword(id, password);
	}

	// /**
	// * 同步初始化电子班牌用户密码
	// *
	// * @param id
	// * @param password
	// */
	// private void initializeEUserPassword(String id, String password) {
	// String iSEClassBrand = env.getProperty("is.EClassBrand");
	// if ("Y".equals(iSEClassBrand)) {
	// User user2 = userService.findByKey(id);
	// eSysUserService.initializeUserPasswordByUserCode(user2.getUserCode(),
	// password);
	// }
	// }

	/**
	 * 客户端登录临时接口
	 */
	@RequestMapping("/webclogin")
	public String webclogin(HttpServletRequest request, HttpServletResponse response, Model model) {
		String loginState = "fail";
		String tmMemberId = "000";
		String lname = request.getParameter("loginName");
		int res = ValidationUtils.checkCodePhoneEmail(lname);
		switch (res) {
		case 2:
			User user = userService.findByPhone(lname);
			if (user != null)
				lname = user.getUserCode();
			break;
		case 3:
			User user1 = userService.findByEmail(lname);
			if (user1 != null)
				lname = user1.getUserCode();
			break;
		}

		String lpwd = request.getParameter("pwd");
		try {
			Md5Hash md5 = new Md5Hash(lpwd, lname, 2);
			lpwd = md5.toString();
		} catch (Exception e) {

		}
		Subject subject = SecurityUtils.getSubject();
		UserNamePasswordUserTypeToken token = new UserNamePasswordUserTypeToken(lname, lpwd);
		try {
			subject.login(token);
			loginState = "success";
			tmMemberId = "100";
		} catch (AuthenticationException e) {
			token.clear();
		}

		model.addAttribute("loginState", loginState);
		model.addAttribute("tmMemberId", tmMemberId);
		return "/cxml/alone/loginResult";
	}

	// 客户端评估
	@RequestMapping("/webcloginpg")
	public String webcloginpg(HttpServletRequest request, HttpServletResponse response, Model model) {
		String lname = request.getParameter("u");
		String lpwd = request.getParameter("p");
		String id = request.getParameter("id");
		String url = "redirect:/online/zb_online?type=A";
		if (StringUtils.isNotBlank(lname) && StringUtils.isNotBlank(lpwd) && StringUtils.isNotBlank(id)) {
			try {
				Md5Hash md5 = new Md5Hash(lpwd, lname, 2);
				lpwd = md5.toString();
			} catch (Exception e) {
			}
			Subject subject = SecurityUtils.getSubject();
			UserNamePasswordUserTypeToken token = new UserNamePasswordUserTypeToken(lname, lpwd);
			try {
				subject.login(token);
				url = "redirect:/pgInfo/clientPg?id=" + id;
			} catch (AuthenticationException e) {
				token.clear();
			}
		}
		return url;
	}

}
