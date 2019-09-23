package com.zzrenfeng.zhsx.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.zzrenfeng.zhsx.model.ShiroUser;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.UserService;

public class CustomRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;

	/**
	 * 用于认证 通过token获取用户输入的账号
	 * 通过用户账号获取用户信息id、username、password、salt、用户菜单(如果获取信息为空,返回null)
	 * 将(id、username、用户菜单)设置到activeUser中 对获取到的用户信息进行认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UserNamePasswordUserTypeToken token = (UserNamePasswordUserTypeToken) authcToken;
		String userCode = token.getUsername();
		String password = new String(token.getPassword());

		User user = null;
		try {
			user = userService.findByCodeAndpwd(userCode, password);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (user == null) {
			return null;
		}

		ShiroUser shiroUser = new ShiroUser();
		shiroUser.setPassword(user.getPassword());
		shiroUser.setId(user.getId());
		shiroUser.setUserCode(user.getUserCode());
		shiroUser.setNickName(user.getNickName());
		shiroUser.setCurrName(user.getCurrName());
		shiroUser.setGender(user.getGender());
		shiroUser.setAge(user.getAge());
		shiroUser.setPoliticsStatus(user.getPoliticsStatus());
		shiroUser.sethA(user.getHA());
		shiroUser.setGrade(user.getGrade());
		shiroUser.seteXP(user.getEXP());
		shiroUser.setSchoolId(user.getSchoolId());
		shiroUser.setUserType(user.getUserType());
		shiroUser.setPhone(user.getPhoto());
		shiroUser.setPhoneOk(user.getPhoneOk());
		shiroUser.setqQ(user.getQQ());
		shiroUser.setEmail(user.getEmail());
		shiroUser.setEmailOk(user.getEmailOk());
		shiroUser.setMemos(user.getMemos());
		shiroUser.setFilePath(user.getFilePath());
		shiroUser.setPhoto(user.getPhoto());
		shiroUser.setCreateTime(user.getCreateTime());
		shiroUser.setModiyTime(user.getModiyTime());
		shiroUser.setBak(user.getBak());
		shiroUser.setBak1(user.getBak1());
		shiroUser.setBak2(user.getBak2());
		SimpleAuthenticationInfo SimpleAuthenticationInfo = new SimpleAuthenticationInfo(shiroUser, password,
				getName());
		return SimpleAuthenticationInfo;
	}

	/**
	 * 授权 从principalCollection 获取 Principal主体信息 getPrimaryPrincipal方法返回值(
	 * doGetAuthenticationInfo认证通过后填充到SimpleAuthenticationInfo的principal主题信息)
	 * 
	 * 
	 */
	@SuppressWarnings("unused")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		ShiroUser shiroUser = (ShiroUser) principalCollection.getPrimaryPrincipal();
		/*
		 * List<SysPermission> permissionlList=null; try {
		 * permissionlList=sysService.findPermissionListByUserId(activeUser.
		 * getUserId()); } catch (Exception e) { e.printStackTrace(); }
		 */
		List<String> permissions = new ArrayList<String>();
		/*
		 * for (SysPermission sysPermission : permissionlList) {
		 * permissions.add(sysPermission.getPercode()); }
		 */
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addStringPermissions(permissions);
		return simpleAuthorizationInfo;
	}

	/**
	 * 清除缓存 shiro默认是关闭认证信息缓存的，对于授权信息的缓存shiro默认开启的,因为授权的数据量大。
	 * 如果用户正常退出和用户非正常退出，shiro提供缓存自动清空。不必调用此方法 用户权限改变时,调用此方法清除缓存,已达到立即生效的需求
	 */
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}

}
