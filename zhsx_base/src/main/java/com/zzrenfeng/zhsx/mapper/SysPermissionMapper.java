package com.zzrenfeng.zhsx.mapper;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.SysPermission;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-14 16:22:38
 * @see com.zzrenfeng.zhsx.service.SysPermission
 */

public interface SysPermissionMapper extends BaseMapper<SysPermission> {

	List<SysPermission> findSysPermissionByids(List<String> ids);

}
