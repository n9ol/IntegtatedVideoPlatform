package com.zzrenfeng.zhsx.service.eclassbrand.sys;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysUserRole;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:07
 * @see com.zzrenfeng.ESysUserRole.model.sys.SysUserRole
 */
public interface ESysUserRoleService {

	ESysUserRole insert(ESysUserRole t);

	int deleteByKey(String id);

	ESysUserRole updateByKey(ESysUserRole t);

	int updateByKeySelective(ESysUserRole t);

	ESysUserRole findByKey(String id);

	List<ESysUserRole> findAll();

	List<ESysUserRole> findSelective(ESysUserRole t);

	PageInfo<ESysUserRole> findPageSelective(ESysUserRole t, int p, int pageSize);

	/**
	 * 批量添加用户角色
	 * 
	 * @param userId
	 * @param roleId
	 */
	void batchInsertUserRole(String userId, String[] roleIds);

}