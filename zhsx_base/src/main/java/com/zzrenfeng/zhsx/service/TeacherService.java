package com.zzrenfeng.zhsx.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.model.User;

public interface TeacherService extends BaseService<User>{

	
	/**
	 * 名师关注度排行
	 */
	Page<Map<String, Object>> findHotTheacher(int p,int pageSize); 
	/**
	 * 新注册教师
	 */
	Page<User> findNewTeacher(int p,int pageSize); 
	
	/**
	 * 名师团队列表
	 */
	Page<Map<String, String>> findTeacherList(Map<String, String> m,int p,int pageSize); 
	
	/**
	 * 教师详细信息
	 */
	Map<String, Object> findTeacherDetails(Map<String, String> param);
	
	/**
	 * 教师成长曲线查询
	 * @param userId
	 */
	List<Map<String, Object>> findGrowthCurve(String userId);
	/**
	 * 查询教师在本年度之前获得的经验值（注册初始经验值为50）
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> findEXPBeforeThisYear(String userId);
	
	/**
	 * 教师成长体系查询
	 * @param userId
	 */
	Map<String, Object> findGrowth(String userId);
	
	/**
	 * 关注教师
	 * @param sysHistory
	 * @return
	 */
	int addCollection(SysHistory sysHistory);
	/**
	 * 取消关注
	 * @param pubFlag
	 * @param pubType
	 * @param pubId
	 * @param userId
	 */
	void deleteCollectionByPub(String pubFlag, String pubType, String pubId,
			String userId);
	
}
