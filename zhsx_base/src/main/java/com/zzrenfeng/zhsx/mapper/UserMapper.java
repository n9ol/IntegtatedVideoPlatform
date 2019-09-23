package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.User;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.User
 */

public interface UserMapper extends BaseMapper<User> {

	/**
	 * 批量添加用户
	 * 
	 * @param userList
	 */
	void insertBatch(List<User> userList);

	/**
	 * 教师关注度
	 * 
	 * @return
	 */
	Page<Map<String, Object>> findHotTeacher(String userId);

	/**
	 * 最新注册教师
	 * 
	 * @return
	 */
	Page<User> findNewTeacher();

	/**
	 * 名师团队列表
	 * 
	 * @param m
	 * @return
	 */
	Page<Map<String, String>> findTeacherList(Map<String, String> m);

	/**
	 * 名师详细信息
	 * 
	 * @param param
	 * @return
	 */
	Map<String, Object> findTeacherDetails(Map<String, String> param);

	/**
	 * 更新用户经验值
	 * 
	 * @param user
	 */
	void updateUserExp(User user);

	/**
	 * 通过学校ID 发现该学校 非学生用户
	 * 
	 * @param schoolId
	 * @return
	 */
	List<User> findTeacherBySchoolId(String schoolId);

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

	List<Map<String, Object>> countTeacherUsedThisYear(Map<String, Object> paramMap);

	Map<String, Object> countOverYearsUsed();

	List<Integer> selectEXPsByLeader(Map<String, Object> paramMap);

	/**
	 * 发现非学生用户
	 * 
	 * @param user
	 * @return
	 */
	List<User> findNotStudents(User user);

}
