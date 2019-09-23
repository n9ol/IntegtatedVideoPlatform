package com.zzrenfeng.zhsx.service;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.SysRolePermission;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-14 16:23:00
 * @see com.zzrenfeng.zhsx.service.SysRolePermission
 */
public interface SysRolePermissionService extends BaseService<SysRolePermission> {

	List<String> findPermissionIdSByRoleIds(List<String> roleIds);

	void delSysRolePermissionByRoleId(String roleId);

}