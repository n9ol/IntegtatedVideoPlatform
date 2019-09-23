package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.SysPermissionMapper;
import com.zzrenfeng.zhsx.model.SysPermission;
import com.zzrenfeng.zhsx.service.SysPermissionService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-14 16:22:38
 * @see com.zzrenfeng.zhsx.service.impl.SysPermission
 */

@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<BaseMapper<SysPermission>, SysPermission>
		implements SysPermissionService {

	@Resource
	private SysPermissionMapper sysPermissionMapper;

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<SysPermission> sysPermissionMapper) {
		super.setBaseMapper(sysPermissionMapper);
	}

	@Override
	public List<SysPermission> findSysPermissionByids(List<String> ids) {
		return sysPermissionMapper.findSysPermissionByids(ids);
	}

}
