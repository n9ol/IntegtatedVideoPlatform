package com.zzrenfeng.zhsx.service;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.SysPermission;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-14 16:22:38
 * @see com.zzrenfeng.zhsx.service.ESysPermission
 */
public interface SysPermissionService extends BaseService<SysPermission> {

	List<SysPermission> findSysPermissionByids(List<String> ids);

}