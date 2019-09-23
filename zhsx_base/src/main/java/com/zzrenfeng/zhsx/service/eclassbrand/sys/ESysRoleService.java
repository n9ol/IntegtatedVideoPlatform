package com.zzrenfeng.zhsx.service.eclassbrand.sys;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysRole;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:07
 * @see com.zzrenfeng.ESysRole.model.sys.SysRole
 */
public interface ESysRoleService {

	ESysRole insert(ESysRole t);

	int deleteByKey(String id);

	ESysRole updateByKey(ESysRole t);

	int updateByKeySelective(ESysRole t);

	ESysRole findByKey(String id);

	List<ESysRole> findAll();

	List<ESysRole> findSelective(ESysRole t);

	PageInfo<ESysRole> findPageSelective(ESysRole t, int p, int pageSize);

	List<ESysRole> getRolesByUserId(String userId);

	void changeRoleAvailable(String id, boolean available);

	/**
	 * 发现所有可用的角色
	 * 
	 * @return
	 */
	List<ESysRole> findAllAvailable();

	/**
	 * 发现所有可用的角色-(用户是否拥有)
	 * 
	 * @param userId
	 * @return
	 */
	List<ESysRole> findAllAvailable(String userId);

	/**
	 * 验证角色名称(描述)是否 不存在
	 * 
	 * @param description
	 * @return
	 */
	boolean validateDescription(String description);

}