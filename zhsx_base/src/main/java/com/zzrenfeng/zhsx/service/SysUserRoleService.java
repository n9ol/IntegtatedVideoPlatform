package com.zzrenfeng.zhsx.service;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.SysUserRole;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-16 10:51:33
 * @see com.zzrenfeng.zhsx.service.SysUserRole
 */
public interface SysUserRoleService extends BaseService<SysUserRole> {

	void deleteByUserId(String userId);

	void updateUserRole(String userId, String[] roleIds);

	List<String> findroleIdsByUserId(String userId);

}