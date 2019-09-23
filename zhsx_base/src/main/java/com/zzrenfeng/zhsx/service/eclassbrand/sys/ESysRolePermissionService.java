package com.zzrenfeng.zhsx.service.eclassbrand.sys;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysRolePermission;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:07
 * @see com.zzrenfeng.ESysRolePermission.model.sys.SysRolePermission
 */
public interface ESysRolePermissionService {

	ESysRolePermission insert(ESysRolePermission t);

	int deleteByKey(String id);

	ESysRolePermission updateByKey(ESysRolePermission t);

	int updateByKeySelective(ESysRolePermission t);

	ESysRolePermission findByKey(String id);

	List<ESysRolePermission> findAll();

	List<ESysRolePermission> findSelective(ESysRolePermission t);

	PageInfo<ESysRolePermission> findPageSelective(ESysRolePermission t, int p, int pageSize);

	int updateRolePermission(String roleId, String[] permissionIds);

}