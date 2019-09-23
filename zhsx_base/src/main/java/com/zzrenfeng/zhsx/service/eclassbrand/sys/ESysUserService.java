package com.zzrenfeng.zhsx.service.eclassbrand.sys;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.zzrenfeng.zhsx.model.ShiroUser;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysUser;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:07
 * @see com.zzrenfeng.ESysUser.model.sys.SysUser
 */
public interface ESysUserService {

	ESysUser insert(ESysUser t);

	int deleteByKey(String id);

	ESysUser updateByKey(ESysUser t);

	int updateByKeySelective(ESysUser t);

	ESysUser findByKey(String id);

	List<ESysUser> findAll();

	List<ESysUser> findSelective(ESysUser t);

	PageInfo<ESysUser> findPageSelective(ESysUser t, int p, int pageSize);

	ESysUser findByUserCode(@RequestParam String userCode);

	void updatePersonInfo(ESysUser t, ShiroUser shiroUser);

	/**
	 * 验证用户密码
	 * 
	 * @param shiroUser
	 * @param oldPassword
	 * @return
	 */
	boolean validatePwd(String userCode, String salt, String password, String oldPassword);

	/**
	 * 修改用户密码
	 * 
	 * @param id
	 * @param password
	 * @param userCode
	 * @param salt
	 */
	void updateUserPassword(String id, String password, String userCode, String salt);

	ESysUser insterUser(ESysUser t, String[] roles);

	/**
	 * 初始化用户密码
	 * 
	 * @param id
	 */
	void initializeUserPasswordByUserCode(String userCode, String password);

	/**
	 * 改变用户状态
	 * 
	 * @param id
	 * @param state
	 */
	void changeUserState(String id, int state);

}