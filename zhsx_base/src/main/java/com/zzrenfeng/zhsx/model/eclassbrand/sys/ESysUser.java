package com.zzrenfeng.zhsx.model.eclassbrand.sys;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.zzrenfeng.zhsx.base.ValidateGroup1;
import com.zzrenfeng.zhsx.base.ValidateGroup2;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:07
 * @see com.zzrenfeng.ESysUser.model.sys.SysUser
 */

public class ESysUser {

	/**
	 * 用戶状态标识符-正常的
	 */
	public final static int USER_STATE_NORMAL = 1;

	/**
	 * 主键
	 */
	@NotNull(groups = { ValidateGroup1.class })
	private java.lang.String id;

	/**
	 * 账号
	 */
	@Email(groups = { ValidateGroup2.class })
	private java.lang.String userCode;
	/**
	 * 用户昵称
	 */
	@NotNull(groups = { ValidateGroup1.class, ValidateGroup2.class })
	private java.lang.String nickname;
	/**
	 * 密码
	 */
	@NotNull(groups = { ValidateGroup2.class })
	private java.lang.String password;
	/**
	 * 加密密码的盐
	 */
	private java.lang.String salt;
	/**
	 * 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
	 */
	private Integer state;

	private String search; // 搜索字段
	private String roleNames; // 用户拥有的角色名称

	// get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setUserCode(java.lang.String value) {
		this.userCode = value;
	}

	public java.lang.String getUserCode() {
		return this.userCode;
	}

	public void setNickname(java.lang.String value) {
		this.nickname = value;
	}

	public java.lang.String getNickname() {
		return this.nickname;
	}

	public void setPassword(java.lang.String value) {
		this.password = value;
	}

	public java.lang.String getPassword() {
		return this.password;
	}

	public void setSalt(java.lang.String value) {
		this.salt = value;
	}

	public java.lang.String getSalt() {
		return this.salt;
	}

	public void setState(Integer value) {
		this.state = value;
	}

	public Integer getState() {
		return this.state;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getRoleNames() {
		return roleNames;
	}

	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}

	@Override
	public String toString() {
		return "SysUser [id=" + id + ", userCode=" + userCode + ", nickname=" + nickname + ", password=" + password
				+ ", salt=" + salt + ", state=" + state + ", search=" + search + ", roleNames=" + roleNames + "]";
	}

}
