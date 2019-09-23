package com.zzrenfeng.zhsx.model;



/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-12-14 16:22:38
 * @see com.zzrenfeng.zhsx.model.SysPermission
 */

public class SysPermission {
		

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 名称
	 */
	private java.lang.String name;
	/**
	 * 资源类型，[menu|button]
	 */
	private java.lang.String resourceType;
	/**
	 * 资源路径
	 */
	private java.lang.String url;
	/**
	 * 权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
	 */
	private java.lang.String permission;
	/**
	 * 父编号
	 */
	private java.lang.String parentId;
	/**
	 * 父编号列表
	 */
	private java.lang.String parentIds;
	/**
	 * 是否可用
	 */
	private java.lang.Boolean available;
	
	
	

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setResourceType(java.lang.String value) {
		this.resourceType = value;
	}
	
	public java.lang.String getResourceType() {
		return this.resourceType;
	}
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	
	public java.lang.String getUrl() {
		return this.url;
	}
	public void setPermission(java.lang.String value) {
		this.permission = value;
	}
	
	public java.lang.String getPermission() {
		return this.permission;
	}
	public void setParentId(java.lang.String value) {
		this.parentId = value;
	}
	
	public java.lang.String getParentId() {
		return this.parentId;
	}
	public void setParentIds(java.lang.String value) {
		this.parentIds = value;
	}
	
	public java.lang.String getParentIds() {
		return this.parentIds;
	}
	public void setAvailable(java.lang.Boolean value) {
		this.available = value;
	}
	
	public java.lang.Boolean getAvailable() {
		return this.available;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("主键[").append(getId()).append("],")
			.append("名称[").append(getName()).append("],")
			.append("资源类型，[menu|button][").append(getResourceType()).append("],")
			.append("资源路径[").append(getUrl()).append("],")
			.append("权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view[").append(getPermission()).append("],")
			.append("父编号[").append(getParentId()).append("],")
			.append("父编号列表[").append(getParentIds()).append("],")
			.append("是否可用[").append(getAvailable()).append("],")
			.toString();
	}
	

}

	
