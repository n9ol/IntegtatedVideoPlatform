package com.zzrenfeng.zhsx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.SysRoleMapper;
import com.zzrenfeng.zhsx.model.SysRole;
import com.zzrenfeng.zhsx.service.SysRolePermissionService;
import com.zzrenfeng.zhsx.service.SysRoleService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-14 16:22:49
 * @see com.zzrenfeng.zhsx.service.impl.SysRole
 */

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<BaseMapper<SysRole>, SysRole> implements SysRoleService {

	@Resource
	private SysRoleMapper sysRoleMapper;
	@Resource
	private SysRolePermissionService sysRolePermissionService;

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<SysRole> sysRoleMapper) {
		super.setBaseMapper(sysRoleMapper);
	}

	@Override
	public void updateRolePermission(String id, String[] permissionIds) {
		sysRolePermissionService.delSysRolePermissionByRoleId(id);
		Map<String, Object> hm = new HashMap<>();
		hm.put("id", id);
		hm.put("permissionIds", permissionIds);
		sysRoleMapper.updateRolePermission(hm);
	}

	@Override
	public List<SysRole> findAll() {
		return sysRoleMapper.findAll();
	}

}
