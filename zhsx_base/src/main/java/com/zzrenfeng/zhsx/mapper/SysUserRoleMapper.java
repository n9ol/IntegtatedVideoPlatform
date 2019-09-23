package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.SysUserRole;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-16 10:51:33
 * @see com.zzrenfeng.zhsx.service.SysUserRole
 */

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

	void deleteByUserId(String userId);

	void updateUserRole(Map<String, Object> hm);

	List<String> findroleIdsByUserId(String userId);

}
