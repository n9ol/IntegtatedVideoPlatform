package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.SysRole;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-14 16:22:49
 * @see com.zzrenfeng.zhsx.service.ESysRole
 */

public interface SysRoleMapper extends BaseMapper<SysRole> {

	void updateRolePermission(Map<String, Object> hm);

	List<SysRole> findAll();

}
