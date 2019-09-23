package com.zzrenfeng.zhsx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.SysUserRoleMapper;
import com.zzrenfeng.zhsx.model.SysUserRole;
import com.zzrenfeng.zhsx.service.SysUserRoleService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-16 10:51:33
 * @see com.zzrenfeng.zhsx.service.impl.ESysUserRole
 */

@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<BaseMapper<SysUserRole>, SysUserRole>
		implements SysUserRoleService {

	@Resource
	private SysUserRoleMapper sysUserRoleMapper;

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<SysUserRole> sysUserRoleMapper) {
		super.setBaseMapper(sysUserRoleMapper);
	}

	@Override
	public void deleteByUserId(String userId) {
		sysUserRoleMapper.deleteByUserId(userId);
	}

	@Override
	public void updateUserRole(String userId, String[] roleIds) {
		Map<String, Object> hm = new HashMap<>();
		hm.put("userId", userId);
		hm.put("roleIds", roleIds);
		sysUserRoleMapper.updateUserRole(hm);
	}

	@Override
	public List<String> findroleIdsByUserId(String userId) {
		return sysUserRoleMapper.findroleIdsByUserId(userId);
	}

}
