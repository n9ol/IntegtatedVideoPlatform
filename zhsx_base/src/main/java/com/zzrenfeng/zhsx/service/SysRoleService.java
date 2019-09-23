package com.zzrenfeng.zhsx.service;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.SysRole;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-14 16:22:49
 * @see com.zzrenfeng.zhsx.service.ESysRole
 */
public interface SysRoleService extends BaseService<SysRole> {

	void updateRolePermission(String id, String[] permissionIds);

	List<SysRole> findAll();

}