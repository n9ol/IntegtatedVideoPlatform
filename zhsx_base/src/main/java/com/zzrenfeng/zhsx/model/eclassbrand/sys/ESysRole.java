package com.zzrenfeng.zhsx.model.eclassbrand.sys;

import javax.validation.constraints.NotNull;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:07
 * @see com.zzrenfeng.ESysRole.model.sys.SysRole
 */

public class ESysRole {

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 角色标识(唯一的)
	 */
	private java.lang.String role;
	/**
	 * 角色描述
	 */
	@NotNull(message = "角色描述不能为空")
	private java.lang.String description;
	/**
	 * 是否可用
	 */
	private java.lang.Boolean available;

	/**
	 * 角色权限类型
	 */
	private String roleType;

	private String permissions; // 角色拥有的权限
	private String search; // 搜索字段
	private Boolean isHave; // 用户是否拥有该角色

	// get and set
	public java.lang.String getId() {
		return id;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getRole() {
		return role;
	}

	public void setRole(java.lang.String role) {
		this.role = role;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.Boolean getAvailable() {
		return available;
	}

	public void setAvailable(java.lang.Boolean available) {
		this.available = available;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Boolean getIsHave() {
		return isHave;
	}

	public void setIsHave(Boolean isHave) {
		this.isHave = isHave;
	}

	@Override
	public String toString() {
		return "ESysRole [id=" + id + ", role=" + role + ", description=" + description + ", available=" + available
				+ ", roleType=" + roleType + ", permissions=" + permissions + ", search=" + search + ", isHave="
				+ isHave + "]";
	}

}
