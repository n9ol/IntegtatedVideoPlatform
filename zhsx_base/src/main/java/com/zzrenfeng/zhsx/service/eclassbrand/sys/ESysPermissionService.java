package com.zzrenfeng.zhsx.service.eclassbrand.sys;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysPermission;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:06
 * @see com.zzrenfeng.ESysPermission.model.sys.SysPermission
 */
public interface ESysPermissionService {

	ESysPermission insert(ESysPermission t);

	int deleteByKey(String id);

	ESysPermission updateByKey(ESysPermission t);

	int updateByKeySelective(ESysPermission t);

	ESysPermission findByKey(String id);

	List<ESysPermission> findAll();

	List<ESysPermission> findSelective(ESysPermission t);

	PageInfo<ESysPermission> findPageSelective(ESysPermission t, int p, int pageSize);

	List<ESysPermission> getPermissionsByRoldId(String roldId);

	List<ESysPermission> getPermissionsByUserId(String userId);

	void insterLevelOneMenu(String name, int available);

	/**
	 * 获得一级菜单、二级菜单、权限按钮 (这里是否使用缓存,那个效率高? 使用缓存 读取 _redis 数据库 ,不用每次都执行业务逻辑. 不使用缓存
	 * ,不用连接_redis数据库,但要对重复执行业务逻辑,所以在其他方法中,间接调用该方法效率最高,不用重复连接
	 * _redis数据库也不用重复执行业务逻辑 )
	 * 
	 * @return
	 */
	Map<String, Object> findEachLevelPermissions(List<ESysPermission> sysPermissions);

	/**
	 * 发现所有可用的权限-(角色是否拥有)
	 * 
	 * @param userId
	 * @return
	 */
	List<ESysPermission> findAllAvailable(String roleId);

}