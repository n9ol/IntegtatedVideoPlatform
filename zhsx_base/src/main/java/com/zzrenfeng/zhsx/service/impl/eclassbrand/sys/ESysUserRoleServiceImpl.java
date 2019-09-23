package com.zzrenfeng.zhsx.service.impl.eclassbrand.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zzrenfeng.zhsx.mapper.eclassbrand.sys.ESysUserRoleMapper;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysUserRole;
import com.zzrenfeng.zhsx.service.eclassbrand.sys.ESysUserRoleService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:07
 * @see com.ESysUserRole.zzrenfeng.classbrand.model.sys.SysUserRole
 */

@Service
public class ESysUserRoleServiceImpl implements ESysUserRoleService {

	@Resource
	private ESysUserRoleMapper esysUserRoleMapper;

	@Override
	public void batchInsertUserRole(String userId, String[] roleIds) {
		Map<String, Object> hm = new HashMap<>();
		hm.put("userId", userId);
		hm.put("roleIds", roleIds);
		esysUserRoleMapper.batchInsertUserRole(hm);
	}

	@Override
	public ESysUserRole insert(ESysUserRole t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ESysUserRole updateByKey(ESysUserRole t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByKeySelective(ESysUserRole t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ESysUserRole findByKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ESysUserRole> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ESysUserRole> findSelective(ESysUserRole t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<ESysUserRole> findPageSelective(ESysUserRole t, int p, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
