package com.zzrenfeng.zhsx.mapper.eclassbrand.sys;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.jdbc.DataSource;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysRole;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:07
 * @see com.zzrenfeng.ESysRole.model.sys.SysRole
 */
@DataSource(value = "2")
public interface ESysRoleMapper extends BaseMapper<ESysRole> {

	List<ESysRole> getRolesByUserId(String userId);

	List<ESysRole> findAllAvailable(String userId);

}
