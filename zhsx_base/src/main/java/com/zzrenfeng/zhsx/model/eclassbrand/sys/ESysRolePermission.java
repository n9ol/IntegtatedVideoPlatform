package com.zzrenfeng.zhsx.model.eclassbrand.sys;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:07
 * @see com.zzrenfeng.ESysRolePermission.model.sys.SysRolePermission
 */

public class ESysRolePermission {

	/**
	 * 角色Id
	 */
	private java.lang.String roleId;
	/**
	 * 权限Id
	 */
	private java.lang.String permissionId;

	// get and set
	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}

	public java.lang.String getRoleId() {
		return this.roleId;
	}

	public void setPermissionId(java.lang.String value) {
		this.permissionId = value;
	}

	public java.lang.String getPermissionId() {
		return this.permissionId;
	}

	public String toString() {
		return new StringBuffer().append("角色Id[").append(getRoleId()).append("],").append("权限Id[")
				.append(getPermissionId()).append("],").toString();
	}

}
