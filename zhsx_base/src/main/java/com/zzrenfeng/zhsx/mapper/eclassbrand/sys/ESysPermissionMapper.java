package com.zzrenfeng.zhsx.mapper.eclassbrand.sys;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.jdbc.DataSource;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysPermission;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:06
 * @see com.zzrenfeng.ESysPermission.model.sys.SysPermission
 */
@DataSource(value = "2")
public interface ESysPermissionMapper extends BaseMapper<ESysPermission> {

	List<ESysPermission> getPermissionsByRoldId(String roldId);

	List<ESysPermission> getPermissionsByUserId(String userId);

	List<ESysPermission> findAllAvailable(String roleId);

}
