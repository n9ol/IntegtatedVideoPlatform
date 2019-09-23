package com.zzrenfeng.zhsx.model;



/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-12-14 16:23:00
 * @see com.zzrenfeng.zhsx.model.SysRolePermission
 */

public class SysRolePermission {
		

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 角色id
	 */
	private java.lang.String roleId;
	/**
	 * 权限id
	 */
	private java.lang.String permissionId;
	
	
	

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
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
		return new StringBuffer()
			.append("主键[").append(getId()).append("],")
			.append("角色id[").append(getRoleId()).append("],")
			.append("权限id[").append(getPermissionId()).append("],")
			.toString();
	}
	

}

	
