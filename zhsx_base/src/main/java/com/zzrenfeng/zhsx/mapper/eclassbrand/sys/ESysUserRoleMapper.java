package com.zzrenfeng.zhsx.mapper.eclassbrand.sys;

import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.jdbc.DataSource;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysUserRole;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:07
 * @see com.zzrenfeng.ESysUserRole.model.sys.SysUserRole
 */
@DataSource(value = "2")
public interface ESysUserRoleMapper extends BaseMapper<ESysUserRole> {

	void batchInsertUserRole(Map<String, Object> hm);

}
