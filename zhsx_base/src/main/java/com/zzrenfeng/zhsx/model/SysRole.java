package com.zzrenfeng.zhsx.model;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-14 16:22:49
 * @see com.zzrenfeng.zhsx.model.SysRole
 */

public class SysRole {

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 角色名称
	 */
	private java.lang.String role;
	/**
	 * 角色描述
	 */
	private java.lang.String description;
	/**
	 * 是否可用
	 */
	private java.lang.Boolean available;

	/**
	 * 角色类型(base 基本信息类型角色, zhsx 综合视讯类型角色)
	 */
	private String roleType;

	// get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setRole(java.lang.String value) {
		this.role = value;
	}

	public java.lang.String getRole() {
		return this.role;
	}

	public void setDescription(java.lang.String value) {
		this.description = value;
	}

	public java.lang.String getDescription() {
		return this.description;
	}

	public void setAvailable(java.lang.Boolean value) {
		this.available = value;
	}

	public java.lang.Boolean getAvailable() {
		return this.available;
	}

	@Override
	public String toString() {
		return "SysRole [id=" + id + ", role=" + role + ", description=" + description + ", available=" + available
				+ ", roleType=" + roleType + "]";
	}

}
