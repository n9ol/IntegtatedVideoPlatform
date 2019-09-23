package com.zzrenfeng.zhsx.mapper;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.SysRolePermission;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-14 16:23:00
 * @see com.zzrenfeng.zhsx.service.SysRolePermission
 */

public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

	List<String> findPermissionIdSByRoleIds(List<String> roleIds);

	void delSysRolePermissionByRoleId(String roleId);

}
