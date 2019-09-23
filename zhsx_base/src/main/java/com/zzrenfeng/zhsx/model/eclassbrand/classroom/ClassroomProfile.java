package com.zzrenfeng.zhsx.model.eclassbrand.classroom;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-08-28 11:08:39
 * @see com.zzrenfeng.model.ClassroomProfile
 */

public class ClassroomProfile {

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 教室ip
	 */
	@NotBlank
	private java.lang.String classroomId;
	/**
	 * classroom_name
	 */
	@NotBlank
	private java.lang.String classroomName;
	/**
	 * 教室简介
	 */
	@NotBlank
	private java.lang.String messageInfo;
	/**
	 * 是否开启该条教室简介
	 */
	@NotNull
	private java.lang.Boolean state;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 更新时间
	 */
	private java.util.Date moidyDate;

	/**
	 * 搜索字段
	 */
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

	public java.lang.String getId() {
		return this.id;
	}

	public void setClassroomId(java.lang.String value) {
		this.classroomId = value;
	}

	public java.lang.String getClassroomId() {
		return this.classroomId;
	}

	public void setClassroomName(java.lang.String value) {
		this.classroomName = value;
	}

	public java.lang.String getClassroomName() {
		return this.classroomName;
	}

	public void setMessageInfo(java.lang.String value) {
		this.messageInfo = value;
	}

	public java.lang.String getMessageInfo() {
		return this.messageInfo;
	}

	public void setState(java.lang.Boolean value) {
		this.state = value;
	}

	public java.lang.Boolean getState() {
		return this.state;
	}

	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}

	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	public void setMoidyDate(java.util.Date value) {
		this.moidyDate = value;
	}

	public java.util.Date getMoidyDate() {
		return this.moidyDate;
	}

	@Override
	public String toString() {
		return "ClassroomProfile [id=" + id + ", classroomId=" + classroomId + ", classroomName=" + classroomName
				+ ", messageInfo=" + messageInfo + ", state=" + state + ", createDate=" + createDate + ", moidyDate="
				+ moidyDate + ", search=" + search + "]";
	}

}
