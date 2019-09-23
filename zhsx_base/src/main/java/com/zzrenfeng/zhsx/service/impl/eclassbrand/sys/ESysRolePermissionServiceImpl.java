package com.zzrenfeng.zhsx.service.impl.eclassbrand.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zzrenfeng.zhsx.mapper.eclassbrand.sys.ESysRolePermissionMapper;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysRolePermission;
import com.zzrenfeng.zhsx.service.eclassbrand.sys.ESysRolePermissionService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:07
 * @see com.ESysRolePermission.zzrenfeng.classbrand.model.sys.SysRolePermission
 */

@Service
public class ESysRolePermissionServiceImpl implements ESysRolePermissionService {

	@Resource
	private ESysRolePermissionMapper esysRolePermissionMapper;

	@Override
	public int updateRolePermission(String roleId, String[] permissionIds) {
		Map<String, Object> hm = new HashMap<>();
		hm.put("roleId", roleId);
		hm.put("permissionIds", permissionIds);
		return esysRolePermissionMapper.updateRolePermission(hm);
	}

	@Override
	public ESysRolePermission insert(ESysRolePermission t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ESysRolePermission updateByKey(ESysRolePermission t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByKeySelective(ESysRolePermission t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ESysRolePermission findByKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ESysRolePermission> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ESysRolePermission> findSelective(ESysRolePermission t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<ESysRolePermission> findPageSelective(ESysRolePermission t, int p, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
