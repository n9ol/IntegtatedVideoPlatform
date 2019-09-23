package com.zzrenfeng.zhsx.model.eclassbrand.sys;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-03-05 09:34:59
 * @see com.zzrenfeng.ESysPermission.model.sys.SysPermission
 */

public class ESysPermission {

	public final static String RESOURCE_TYPE_MENU = "menu";

	public final static String RESOURCE_TYPE_BUTTON = "button";

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 名称
	 */
	@NotEmpty
	private java.lang.String name;
	/**
	 * 资源类型，[menu|button]
	 */
	@NotEmpty
	private java.lang.String resourceType;
	/**
	 * 资源路径
	 */
	@NotEmpty
	private java.lang.String url;
	/**
	 * 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:
	 * view
	 */
	@NotEmpty
	private java.lang.String permission;
	/**
	 * 父编号
	 */
	@NotEmpty
	private java.lang.String parentId;
	/**
	 * 父编号列表
	 */
	@NotEmpty
	private java.lang.String parentIds;
	/**
	 * 序号,排序（原意 是否可用）
	 */
	@NotNull
	private Integer available;

	// 扩展字段
	private java.lang.Boolean isHave; // 角色是否拥有改权限

	// get and set
	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getResourceType() {
		return resourceType;
	}

	public void setResourceType(java.lang.String resourceType) {
		this.resourceType = resourceType;
	}

	public java.lang.String getUrl() {
		return url;
	}

	public void setUrl(java.lang.String url) {
		this.url = url;
	}

	public java.lang.String getPermission() {
		return permission;
	}

	public void setPermission(java.lang.String permission) {
		this.permission = permission;
	}

	public java.lang.String getParentId() {
		return parentId;
	}

	public void setParentId(java.lang.String parentId) {
		this.parentId = parentId;
	}

	public java.lang.String getParentIds() {
		return parentIds;
	}

	public void setParentIds(java.lang.String parentIds) {
		this.parentIds = parentIds;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public java.lang.Boolean getIsHave() {
		return isHave;
	}

	public void setIsHave(java.lang.Boolean isHave) {
		this.isHave = isHave;
	}

	@Override
	public String toString() {
		return "SysPermission [id=" + id + ", name=" + name + ", resourceType=" + resourceType + ", url=" + url
				+ ", permission=" + permission + ", parentId=" + parentId + ", parentIds=" + parentIds + ", available="
				+ available + ", isHave=" + isHave + "]";
	}

}
