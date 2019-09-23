package com.zzrenfeng.zhsx.model;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-29 16:52:45
 * @see com.zzrenfeng.zhsx.model.LoPgGroupUser
 */

public class LoPgGroupUser {

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 评估小组id
	 */
	private java.lang.String pgGroupId;
	/**
	 * 用户id
	 */
	private java.lang.String userId;

	// 关联字段
	private String nickName;
	private String userType;
	private String schoolName;
	private String search;

	// get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setPgGroupId(java.lang.String value) {
		this.pgGroupId = value;
	}

	public java.lang.String getPgGroupId() {
		return this.pgGroupId;
	}

	public void setUserId(java.lang.String value) {
		this.userId = value;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("主键[").append(getId()).append("],").append("评估小组id[").append(getPgGroupId())
				.append("],").append("用户id[").append(getUserId()).append("],").toString();
	}

}
