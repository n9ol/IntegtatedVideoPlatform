package com.zzrenfeng.zhsx.service.impl.eclassbrand.sys;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.zzrenfeng.zhsx.mapper.eclassbrand.sys.ESysUserMapper;
import com.zzrenfeng.zhsx.model.ShiroUser;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysUser;
import com.zzrenfeng.zhsx.service.eclassbrand.sys.ESysUserRoleService;
import com.zzrenfeng.zhsx.service.eclassbrand.sys.ESysUserService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:07
 * @see com.ESysUser.zzrenfeng.classbrand.model.sys.SysUser
 */

@Service
public class ESysUserServiceImpl implements ESysUserService {

	@Resource
	private ESysUserMapper esysUserMapper;
	@Resource
	private ESysUserRoleService esysUserRoleService;
	@Resource
	private Environment env;

	/**
	 * 用户密码md5加密次数
	 */
	private final int MD5_ENCRYPT_NUMBER = 2;

	@Override
	public ESysUser findByUserCode(String userCode) {
		return esysUserMapper.findByUserCode(userCode);
	}

	@Override
	@Transactional
	public void updatePersonInfo(ESysUser t, ShiroUser shiroUser) {
		// updateByKeySelective(t);
		// shiroUser.setNickname(t.getNickname());
	}

	@Override
	public boolean validatePwd(String userCode, String salt, String password, String oldPassword) {
		Md5Hash md5 = new Md5Hash(oldPassword, userCode + salt, MD5_ENCRYPT_NUMBER);
		if (md5.toString().equals(password))
			return true;
		return false;
	}

	@Override
	public void updateUserPassword(String id, String password, String userCode, String salt) {
		Md5Hash md5 = new Md5Hash(password, userCode + salt, MD5_ENCRYPT_NUMBER);
		ESysUser sysuser = new ESysUser();
		sysuser.setId(id);
		sysuser.setPassword(md5.toString());
		updateByKeySelective(sysuser);
	}

	@Override
	@Transactional
	public ESysUser insterUser(ESysUser t, String[] roleIds) {
		String salt = UUID.randomUUID().toString();
		t.setSalt(salt);
		String password = new Md5Hash(t.getPassword(), t.getUserCode() + salt, MD5_ENCRYPT_NUMBER).toString();
		t.setPassword(password);
		t.setState(ESysUser.USER_STATE_NORMAL);
		ESysUser sysUser = insert(t);
		esysUserRoleService.batchInsertUserRole(sysUser.getId(), roleIds);
		return sysUser;
	}

	@Override
	public void initializeUserPasswordByUserCode(String userCode, String password) {
		ESysUser t = findByUserCode(userCode);
		updateUserPassword(t.getId(), password, t.getUserCode(), t.getSalt());
	}

	@Override
	public void changeUserState(String id, int state) {
		ESysUser sysUser = new ESysUser();
		sysUser.setId(id);
		sysUser.setState(state);
		updateByKeySelective(sysUser);
	}

	@Override
	public ESysUser insert(ESysUser t) {
		esysUserMapper.insert(t);
		return t;
	}

	@Override
	public int deleteByKey(String id) {
		return esysUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public ESysUser updateByKey(ESysUser t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByKeySelective(ESysUser t) {
		return esysUserMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public ESysUser findByKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ESysUser> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ESysUser> findSelective(ESysUser t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<ESysUser> findPageSelective(ESysUser t, int p, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
