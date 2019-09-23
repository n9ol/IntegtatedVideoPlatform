package com.zzrenfeng.zhsx.model.eclassbrand.sys;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:07
 * @see com.zzrenfeng.ESysUserRole.model.sys.SysUserRole
 */

public class ESysUserRole {

	/**
	 * 用户id
	 */
	private java.lang.String uid;
	/**
	 * 角色Id
	 */
	private java.lang.String roleId;

	// get and set
	public void setUid(java.lang.String value) {
		this.uid = value;
	}

	public java.lang.String getUid() {
		return this.uid;
	}

	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}

	public java.lang.String getRoleId() {
		return this.roleId;
	}

	public String toString() {
		return new StringBuffer().append("用户id[").append(getUid()).append("],").append("角色Id[").append(getRoleId())
				.append("],").toString();
	}

}
