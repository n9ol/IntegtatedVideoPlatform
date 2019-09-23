package com.zzrenfeng.zhsx.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.User;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.User
 */
public interface UserService extends BaseService<User> {

	/**
	 * 通过账号获得用户
	 * 
	 * @param userCode
	 * @return
	 */
	User findByUserCode(String userCode);

	/**
	 * 通过手机号获得用户
	 * 
	 * @param userCode
	 * @return
	 */
	User findByPhone(String phone);

	/**
	 * 通过邮箱获得用户
	 * 
	 * @param userCode
	 * @return
	 */
	User findByEmail(String email);

	/**
	 * 通过账号和密码获得用户
	 * 
	 * @param userCode
	 * @param password
	 * @return
	 */
	User findByCodeAndpwd(String userCode, String password);

	/**
	 * 禁用或启用用户
	 * 
	 * @param id
	 * @param bak
	 */
	void updateUserState(String id, String bak);

	/**
	 * 批量添加用户
	 * 
	 * @param userList
	 */
	void insertBatch(List<User> userList);

	/**
	 * 通过用户权限等级获得用户对应的学校ids
	 * 
	 * @param bak1
	 *            用户权限
	 * @param bak2
	 *            用户所在地区
	 * @param schoolId
	 * @return
	 */
	List<String> getUserSchoolIds(String bak1, String bak2, String schoolId);

	/**
	 * 得到用户类型
	 * 
	 * @param usertype
	 * @return
	 */
	String getUserType(String usertype);

	/**
	 * 通过经验值获得用户等级
	 * 
	 * @param Exp
	 * @return
	 */
	int getUserGrade(Integer Exp);

	/**
	 * 根据等级获取所需经验值
	 * 
	 * @param grade
	 * @return
	 */
	int getExp(int grade);

	/**
	 * 更新用户经验值
	 * 
	 * @param userId
	 *            用户
	 * @param exp
	 *            经验值增长量
	 */
	void updateUserExp(String userId, int exp);

	/**
	 * 教师连续登录行为,更新用户经验值
	 * 
	 * @param userId
	 */
	void updateUserExpBylogin(String userId) throws ParseException;

	/**
	 * 教师完善资料,更新用户经验值
	 * 
	 * @param userId
	 */
	void updateUserExpByCompleteInfo(User user);

	/**
	 * 判断用户资料是否完整
	 * 
	 * @param user
	 * @return
	 */
	boolean isUserInfo(User user);

	/**
	 * 用户登录, 更新用户经验值
	 * 
	 * @param user
	 */
	void updateUserExp(String userId) throws ParseException;

	/**
	 * 更新用户经验值,惩罚
	 * 
	 * @param userId
	 */
	void updateUserExpByPunishment(String userId) throws ParseException;

	/**
	 * 通过学校ID 发现该学校 非学生用户
	 * 
	 * @param schoolId
	 * @return
	 */
	List<User> findTeacherBySchoolId(String schoolId);

	/**
	 * 重置用户密码
	 * 
	 * @param userCode
	 * @param newPasseord
	 */
	void recomposeUserPassword(String userCode, String newPasseord);

	/**
	 * 根据等级获取教师荣誉
	 * 
	 * @param lev
	 * @return
	 */
	String getUserGradeGlory(int lev);

	/**
	 * 同步老平台用户信息到新平台
	 * 
	 * @param userList
	 */
	void insertBatchFromOld(List<User> userList);

	/**
	 * 根据日期查询等级
	 * 
	 * @param string
	 * @return
	 */
	List<Integer> selectEXPs(String string);

	/**
	 * 今年省份之内各个地方教师使用平台人数
	 * 
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> countTeacherUsedThisYear(Map<String, Object> paramMap);

	/**
	 * 历年使用平台人数的变化 近3年的变化
	 * 
	 * @return
	 */
	Map<String, Object> countOverYearsUsed();

	List<Integer> selectEXPsByLeader(Map<String, Object> paramMap);

	/**
	 * 更新用户经验值 - 首次上传头像
	 */
	void updateUserExpByUploadHeadPortrait(String userId);

	void updateUserIsadmin(User user, boolean isadmin, String[] role_id);

	/**
	 * 发现非学生用户
	 * 
	 * @param user
	 * @return
	 */
	List<User> findNotStudents(User user);

	/**
	 * 判断用户是否拥有评估权限
	 * 
	 * @param userId
	 * @param userType
	 * @param scheduleId
	 *            课程id
	 * @param schedulePgModel
	 *            课程评估模式
	 * @return
	 */
	String isPgAuthority(String userId, String userType, String scheduleId, String schedulePgModel);

}
