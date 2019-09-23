package com.zzrenfeng.zhsx.model;



/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-12-16 10:51:33
 * @see com.zzrenfeng.zhsx.model.SysUserRole
 */

public class SysUserRole {
		

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 用户id
	 */
	private java.lang.String userId;
	/**
	 * 角色id
	 */
	private java.lang.String roleId;
	
	
	

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	public void setRoleId(java.lang.String value) {
		this.roleId = value;
	}
	
	public java.lang.String getRoleId() {
		return this.roleId;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("主键[").append(getId()).append("],")
			.append("用户id[").append(getUserId()).append("],")
			.append("角色id[").append(getRoleId()).append("],")
			.toString();
	}
	

}

	
