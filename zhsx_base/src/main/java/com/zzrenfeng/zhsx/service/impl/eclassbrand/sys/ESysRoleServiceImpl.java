package com.zzrenfeng.zhsx.service.impl.eclassbrand.sys;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.zzrenfeng.zhsx.mapper.eclassbrand.sys.ESysRoleMapper;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysRole;
import com.zzrenfeng.zhsx.service.eclassbrand.sys.ESysRoleService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:07
 * @see com.ESysRole.zzrenfeng.classbrand.model.sys.SysRole
 */

@Service
public class ESysRoleServiceImpl implements ESysRoleService {

	@Resource
	private ESysRoleMapper esysRoleMapper;

	@Override
	public List<ESysRole> getRolesByUserId(String userId) {
		return esysRoleMapper.getRolesByUserId(userId);
	}

	@Override
	public void changeRoleAvailable(String id, boolean available) {
		ESysRole sysRole = new ESysRole();
		sysRole.setId(id);
		sysRole.setAvailable(available);
		esysRoleMapper.updateByPrimaryKeySelective(sysRole);
	}

	@Override
	public List<ESysRole> findAllAvailable() {
		ESysRole sysRole = new ESysRole();
		sysRole.setAvailable(true);
		return findSelective(sysRole);
	}

	@Override
	public List<ESysRole> findAllAvailable(String userId) {
		return esysRoleMapper.findAllAvailable(userId);
	}

	@Override
	public boolean validateDescription(String description) {
		ESysRole sysRole = new ESysRole();
		sysRole.setDescription(description);
		List<ESysRole> listSysRole = findSelective(sysRole);
		if (listSysRole == null || listSysRole.size() <= 0) {
			return true;
		}
		return false;
	}

	@Override
	public ESysRole insert(ESysRole t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ESysRole updateByKey(ESysRole t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByKeySelective(ESysRole t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ESysRole findByKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ESysRole> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ESysRole> findSelective(ESysRole t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<ESysRole> findPageSelective(ESysRole t, int p, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
