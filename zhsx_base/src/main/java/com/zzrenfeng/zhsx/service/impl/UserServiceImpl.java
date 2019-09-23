package com.zzrenfeng.zhsx.service.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.UserMapper;
import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.SysDict;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.LoPgGroupUserService;
import com.zzrenfeng.zhsx.service.SysDictService;
import com.zzrenfeng.zhsx.service.SysHistoryService;
import com.zzrenfeng.zhsx.service.SysSchoolService;
import com.zzrenfeng.zhsx.service.SysUserRoleService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.util.DateUtil;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.impl.User
 */

@Service
public class UserServiceImpl extends BaseServiceImpl<BaseMapper<User>, User> implements UserService {

	@Resource
	private UserMapper userMapper;
	@Resource
	private SysSchoolService schoolService;
	@Resource
	private SysDictService sysDictService;
	@Resource
	private SysHistoryService sysHistoryService;
	@Resource
	private SysUserRoleService sysUserRoleService;
	@Resource
	private LoPgGroupUserService loPgGroupUserService;

	private String fullUserInfoExp = "";

	private String reduceExp = "";

	private String uploadHeadPortrait = "";

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<User> userMapper) {
		super.setBaseMapper(userMapper);
	}

	@Override
	public User findByUserCode(String userCode) {
		User user = new User();
		user.setUserCode(userCode);
		List<User> ls = userMapper.findSelective(user);
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		} else {
			return null;
		}
	}

	@Override
	public User findByPhone(String phone) {
		User user = new User();
		user.setPhone(phone);
		List<User> ls = userMapper.findSelective(user);
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		} else {
			return null;
		}
	}

	@Override
	public User findByEmail(String email) {
		User user = new User();
		user.setEmail(email);
		List<User> ls = userMapper.findSelective(user);
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		} else {
			return null;
		}
	}

	@Override
	public User findByCodeAndpwd(String userCode, String password) {
		User user = new User();
		user.setUserCode(userCode);
		user.setPassword(password);
		List<User> ls = userMapper.findSelective(user);
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void updateUserState(String id, String bak) {
		User user = new User();
		user.setId(id);
		user.setBak(bak);
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void insertBatch(List<User> userList) {
		userMapper.insertBatch(userList);
	}

	@Override
	public List<String> getUserSchoolIds(String bak1, String bak2, String schoolId) {
		SysSchool school = new SysSchool();
		List<SysSchool> schools = new ArrayList<>();
		List<String> ids = new ArrayList<>();
		if (bak1.equals("SA")) {
			ids.add(schoolId);
		} else if (bak1.equals("AA")) {
			school.setCountyId(bak2);
			schools = schoolService.findSelective(school);
			for (SysSchool sysSchool : schools) {
				ids.add(sysSchool.getId());
			}
		} else if (bak1.equals("CA")) {
			SysDict sysDict = sysDictService.findByKey(bak2);
			if (sysDict != null) {
				school.setCityId(sysDict.getPid());
			}
			schools = schoolService.findSelective(school);
			for (SysSchool sysSchool : schools) {
				ids.add(sysSchool.getId());
			}
		} else if (bak1.equals("PA")) {
			SysDict sysDict = sysDictService.findByKey(bak2);
			if (sysDict != null) {
				SysDict sysDict2 = sysDictService.findByKey(sysDict.getPid());
				if (sysDict2 != null) {
					school.setProvinceId(sysDict2.getPid());
				}
			}
			schools = schoolService.findSelective(school);
			for (SysSchool sysSchool : schools) {
				ids.add(sysSchool.getId());
			}
		}
		return ids;
	}

	@Override
	public String getUserType(String usertype) {
		String type = null;
		if (usertype.equals("领导")) {
			type = User.userType_leader;
		} else if (usertype.equals("教研员")) {
			type = User.userType_research;
		} else if (usertype.equals("教师")) {
			type = User.userType_teachers;
		} else if (usertype.equals("学生")) {
			type = User.userType_students;
		}
		return type;
	}

	@Override
	public int getUserGrade(Integer Exp) {
		if (Exp == null || Exp < 50) {
			Exp = 50;
		}
		BigDecimal e = new BigDecimal(Exp);
		BigDecimal n = new BigDecimal(2);
		BigDecimal m = new BigDecimal(4);
		BigDecimal a = new BigDecimal(15);
		BigDecimal b = new BigDecimal(15);
		BigDecimal c = a.multiply(n).subtract(e);
		double square = Math.sqrt(b.multiply(b).subtract(m.multiply(a).multiply(c)).doubleValue());
		return (int) Math.floor(
				new BigDecimal(square).subtract(b).divide(n.multiply(a), 20, BigDecimal.ROUND_HALF_DOWN).doubleValue());
	}

	@Override
	public int getExp(int grade) {
		BigDecimal g = new BigDecimal(grade);
		BigDecimal n = new BigDecimal(2);
		BigDecimal a = new BigDecimal(15);
		return (int) a.multiply(g).multiply(g).add(a.multiply(g)).add(n.multiply(a)).doubleValue();
	}

	@Override
	public void updateUserExp(String userId, int exp) {
		User user = new User();
		user.setId(userId);
		user.setEXP(exp);
		userMapper.updateUserExp(user);
	}

	@Override
	public void updateUserExpBylogin(String userId) throws ParseException {
		String loginExp = "";
		SysHistory sysh = new SysHistory();
		sysh.setUserId(userId);
		sysh.setPubType(SysHistory.PUBTYPE_D);
		sysh.setPubFlag(SysHistory.PUBFLAG_H);
		sysh.setPubId(userId);
		Date date = new Date();
		sysh.setCreateTime(DateUtil.getDateDate(date, "yyyy-MM-dd"));
		List<SysHistory> shList = sysHistoryService.findSelective(sysh);
		if (shList == null || shList.size() == 0) {
			int e = DateUtil.getIntWeekDay(date);
			String MondayDate = DateUtil.getNextDay(date, 1 - e, "yyyy-MM-dd");
			String SundayDate = DateUtil.getNextDay(date, 7 - e, "yyyy-MM-dd");
			sysh.setStartTime(java.sql.Date.valueOf(MondayDate));
			sysh.setEndTime(java.sql.Date.valueOf(SundayDate));
			sysh.setCreateTime(null);
			List<SysHistory> sh2Lsit = sysHistoryService.findSelective(sysh);
			int loginnumber = sh2Lsit.size(); // 当前周登录次数
			if (loginExp.isEmpty()) {
				Properties props = new Properties();
				InputStream in;
				in = getClass().getResourceAsStream("/exp.properties");
				try {
					props.load(in);
				} catch (Exception e1) {
					loginExp = "1";
				}
				if (props.isEmpty()) {
					loginExp = "1";
				} else {
					String loginexp = "login" + (loginnumber + 1) + ".exp";
					loginExp = props.get(loginexp).toString();
				}
			}
			int le = Integer.valueOf(loginExp);
			sysh.setBak(loginExp);
			sysh.setBak1("本周第" + le + "次登录,获得" + le + "经验值");
			sysHistoryService.insert(sysh);
			updateUserExp(userId, le);
		}
	}

	@Override
	public void updateUserExpByCompleteInfo(User user) {
		if (isUserInfo(user)) {
			SysHistory sysh = new SysHistory();
			sysh.setUserId(user.getId());
			sysh.setPubFlag(SysHistory.PUBFLAG_H);
			sysh.setPubType(SysHistory.PUBTYPE_C);
			sysh.setPubId(user.getId());
			List<SysHistory> shList = sysHistoryService.findSelective(sysh);
			if (shList == null || shList.size() == 0) {
				if (fullUserInfoExp.isEmpty()) {
					Properties props = new Properties();
					InputStream in;
					in = getClass().getResourceAsStream("/exp.properties");
					try {
						props.load(in);
					} catch (Exception e1) {
						fullUserInfoExp = "50";
					}
					if (props.isEmpty()) {
						fullUserInfoExp = "50";
					} else {
						fullUserInfoExp = props.get("fullUserInfo.exp").toString();
					}
				}
				int fuie = Integer.valueOf(fullUserInfoExp);
				sysh.setBak(fullUserInfoExp);
				sysh.setBak1("完善资料,获得" + fuie + "经验值");
				sysHistoryService.insert(sysh);
				updateUserExp(user.getId(), fuie);
			}
		}
	}

	@Override
	public boolean isUserInfo(User user) {
		boolean isok = true;
		if (user.getNickName() == null || user.getNickName().equals("")) {
			isok = false;
		} else if (user.getHA() == null || user.getHA().equals("")) {
			isok = false;
		} else if (user.getStature() == null || user.getStature() == 0) {
			isok = false;
		} else if (user.getBak2() == null || user.getBak2().equals("")) {
			isok = false;
		} else if (user.getSchoolId() == null || user.getSchoolId().equals("")) {
			isok = false;
		} else if (user.getPoliticsStatus() == null || user.getPoliticsStatus().equals("")) {
			isok = false;
		} else if (user.getEmail() == null || user.getEmail().equals("")) {
			isok = false;
		} else if (user.getPhone() == null || user.getPhone().equals("")) {
			isok = false;
		} else if (user.getCurrName() == null || user.getCurrName().equals("")) {
			isok = false;
		} else if (user.getGender() == null || user.getGender().equals("")) {
			isok = false;
		} else if (user.getAge() == null || user.getAge() == 0) {
			isok = false;
		} else if (user.getMemos() == null || user.getMemos().equals("")) {
			isok = false;
		}
		return isok;
	}

	@Override
	public void updateUserExp(String userId) throws ParseException {
		updateUserExpBylogin(userId);
		updateUserExpByPunishment(userId);
	}

	@Override
	public void updateUserExpByPunishment(String userId) throws ParseException {
		User user = userMapper.selectByPrimaryKey(userId);
		int grade = getUserGrade(user.getEXP());
		if (grade > 30) {
			Date time = sysHistoryService.getTeacherConTime(userId);
			if (time == null) {
				return;
			}
			int d = DateUtil.getdaysBetween(time, new Date());
			if (d > 3) {
				if (reduceExp.isEmpty()) {
					Properties props = new Properties();
					InputStream in;
					in = getClass().getResourceAsStream("/exp.properties");
					try {
						props.load(in);
					} catch (Exception e1) {
						reduceExp = "15";
					}
					if (props.isEmpty()) {
						reduceExp = "15";
					} else {
						reduceExp = props.get("reduce.exp").toString();
					}
				}
				int re = Integer.valueOf(reduceExp);

				SysHistory sysh = new SysHistory();
				sysh.setUserId(userId);
				sysh.setPubFlag(SysHistory.PUBFLAG_H);
				sysh.setPubId(userId);
				sysh.setBak("-" + String.valueOf((d - 3) * re));
				sysHistoryService.insert(sysh);
				User user1 = new User();
				user1.setId(userId);
				int exp = user.getEXP() - ((d - 3) * re);
				user1.setEXP(exp);
				userMapper.updateByPrimaryKeySelective(user1);
			}
		}
	}

	@Override
	public List<User> findTeacherBySchoolId(String schoolId) {
		return userMapper.findTeacherBySchoolId(schoolId);
	}

	@Override
	public void recomposeUserPassword(String userCode, String newPasseord) {
		User user1 = findByUserCode(userCode);
		User user = new User();
		user.setId(user1.getId());
		user.setPassword(new Md5Hash(newPasseord, userCode, 2).toString());
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public String getUserGradeGlory(int lev) {
		String gradeGlory = "";
		if (lev > 1 && lev < 10) {
			gradeGlory = "初级教师";
		} else if (lev > 11 && lev < 30) {
			gradeGlory = "三级教师";
		} else if (lev > 31 && lev < 60) {
			gradeGlory = "二级教师";
		} else if (lev > 61 && lev < 80) {
			gradeGlory = "一级教师";
		} else if (lev > 81 && lev < 90) {
			gradeGlory = "高级教师";
		} else if (lev > 91 && lev < 100) {
			gradeGlory = "特级教师";
		} else {
			gradeGlory = "初级教师";// 不存在就是新手老师
		}
		return gradeGlory;
	}

	@Override
	public void insertBatchFromOld(List<User> userList) {
		userMapper.insertBatchFromOld(userList);
	}

	@Override
	public List<Integer> selectEXPs(String string) {
		return userMapper.selectEXPs(string);
	}

	@Override
	public List<Map<String, Object>> countTeacherUsedThisYear(Map<String, Object> paramMap) {
		return userMapper.countTeacherUsedThisYear(paramMap);
	}

	@Override
	public Map<String, Object> countOverYearsUsed() {
		return userMapper.countOverYearsUsed();
	}

	@Override
	public List<Integer> selectEXPsByLeader(Map<String, Object> paramMap) {
		return userMapper.selectEXPsByLeader(paramMap);
	}

	@Override
	public void updateUserExpByUploadHeadPortrait(String userId) {

		if (uploadHeadPortrait.isEmpty()) {
			Properties props = new Properties();
			InputStream in;
			in = getClass().getResourceAsStream("/exp.properties");
			try {
				props.load(in);
			} catch (Exception e1) {
				uploadHeadPortrait = "20";
			}
			if (props.isEmpty()) {
				uploadHeadPortrait = "20";
			} else {
				uploadHeadPortrait = props.get("uploadHeadPortrait.exp").toString();
			}
		}

		int uhp = Integer.valueOf(uploadHeadPortrait);
		updateUserExp(userId, uhp);

		SysHistory sysh = new SysHistory();
		sysh.setUserId(userId);
		sysh.setPubFlag(SysHistory.PUBFLAG_H);
		sysh.setPubId(userId);
		sysh.setPubType(SysHistory.PUBTYPE_C);
		sysh.setBak(uploadHeadPortrait);
		sysh.setBak1("上传头像,获得" + uploadHeadPortrait + "经验值");
		sysHistoryService.insert(sysh);

	}

	@Override
	public void updateUserIsadmin(User user, boolean isadmin, String[] role_id) {
		sysUserRoleService.deleteByUserId(user.getId());
		if (isadmin) {
			String bak1 = user.getBak1();
			if (!bak1.equals(User.bak1_schoool)) {
				user.setSchoolId(null);
			}
			if (role_id != null && role_id.length > 0) {
				sysUserRoleService.updateUserRole(user.getId(), role_id);
			}
		} else {
			user.setBak1(User.bak1_no);
			user.setSchoolId(null);
		}
		updateByKeySelective(user);
	}

	@Override
	public List<User> findNotStudents(User user) {
		return userMapper.findNotStudents(user);
	}

	@Override
	public String isPgAuthority(String userId, String userType, String scheduleId, String schedulePgModel) {
		String isPgAuthority = "Y";
		if (userType.equals(User.userType_students)) {
			isPgAuthority = "N";
			return isPgAuthority;
		}
		if (schedulePgModel != null && schedulePgModel.equals(LoSchedule.PG_BAK2_S)) {
			int pgAuthority = loPgGroupUserService.isPgAuthority(userId, scheduleId);
			if (pgAuthority <= 0) {
				isPgAuthority = "N";
			}
		}
		return isPgAuthority;
	}

}