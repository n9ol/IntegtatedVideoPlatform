package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.SysRolePermissionMapper;
import com.zzrenfeng.zhsx.model.SysRolePermission;
import com.zzrenfeng.zhsx.service.SysRolePermissionService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-14 16:23:00
 * @see com.zzrenfeng.zhsx.service.impl.SysRolePermission
 */

@Service
public class SysRolePermissionServiceImpl extends BaseServiceImpl<BaseMapper<SysRolePermission>, SysRolePermission>
		implements SysRolePermissionService {

	@Resource
	private SysRolePermissionMapper sysRolePermissionMapper;

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<SysRolePermission> sysRolePermissionMapper) {
		super.setBaseMapper(sysRolePermissionMapper);
	}

	@Override
	public List<String> findPermissionIdSByRoleIds(List<String> roleIds) {
		return sysRolePermissionMapper.findPermissionIdSByRoleIds(roleIds);
	}

	@Override
	public void delSysRolePermissionByRoleId(String roleId) {
		sysRolePermissionMapper.delSysRolePermissionByRoleId(roleId);
	}

}
