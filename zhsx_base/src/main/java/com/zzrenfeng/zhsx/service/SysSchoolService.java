package com.zzrenfeng.zhsx.service;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.SysSchool;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.SysSchool
 */
public interface SysSchoolService extends BaseService<SysSchool> {

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	int delBatchSchool(List<String> ids);

	/**
	 * 批量插入
	 * 
	 * @param slist
	 */
	void insertBatch(List<SysSchool> slist);

	/**
	 * 有选择性的获得学校名称和id
	 * 
	 * @param sysSchool
	 * @return
	 */
	List<SysSchool> findIdAndSchoolNameSelective(SysSchool sysSchool);

	/**
	 * 同步老平台学校信息到新平台
	 * 
	 * @param slist
	 */
	void insertBatchFromOld(List<SysSchool> slist);

	/**
	 * 获得有教室存在的学校
	 * 
	 * @param sysSchool
	 * @return
	 */
	List<SysSchool> findSchoolClassNotNull(SysSchool sysSchool);

	/**
	 * 根据用户管理等级 获取该用户有权限管理的学校
	 * 
	 * @param bak1
	 *            用户管理等级
	 * @param bak2
	 *            用户管理等级对应区县id
	 * @param schoolId
	 *            用户所在学校
	 * @return
	 */
	List<SysSchool> listSysSchoolHavePermission(String bak1, String bak2, String schoolId);

	 /** 根据条件来获得数据
	 * @param sysSchool
	 * @return
	 */
	List<SysSchool> findSelectiveData(SysSchool sysSchool);
	
}
