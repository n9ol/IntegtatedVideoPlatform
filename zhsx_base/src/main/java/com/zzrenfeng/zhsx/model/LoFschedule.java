package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-05-22 15:17:49
 * @see com.zzrenfeng.zhsx.model.LoFschedule
 */

public class LoFschedule {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 直播id
	 */
	private java.lang.String zId;
	/**
	 * 教室id
	 */
	private java.lang.String classId;
	/**
	 * 学校id
	 */
	private java.lang.String schoolId;
	/**
	 * 辅讲教师ID
	 */
	private java.lang.String userId;
	/**
	 * 备用字段
	 */
	private java.lang.String bak;
	/**
	 * 备用字段
	 */
	private java.lang.String bak1;
	/**
	 * 备用字段
	 */
	private java.lang.String bak2;
	//columns END 数据库字段结束
	
	//关联字段
	private java.lang.String userName; //用户名称
	private java.lang.String schoolName; //学校名称
	private java.lang.String className; //教室名称
	
	//concstructor

	public LoFschedule(){
	}

	public LoFschedule(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(java.lang.String schoolName) {
		this.schoolName = schoolName;
	}

	public java.lang.String getClassName() {
		return className;
	}

	public void setClassName(java.lang.String className) {
		this.className = className;
	}

	public java.lang.String getId() {
		return this.id;
	}
	public void setZId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zId = value;
	}
	
	public java.lang.String getZId() {
		return this.zId;
	}
	public void setClassId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.classId = value;
	}
	
	public java.lang.String getClassId() {
		return this.classId;
	}
	public void setSchoolId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schoolId = value;
	}
	
	public java.lang.String getSchoolId() {
		return this.schoolId;
	}
	public void setUserId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	public void setBak(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak = value;
	}
	
	public java.lang.String getBak() {
		return this.bak;
	}
	public void setBak1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak1 = value;
	}
	
	public java.lang.String getBak1() {
		return this.bak1;
	}
	public void setBak2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak2 = value;
	}
	
	public java.lang.String getBak2() {
		return this.bak2;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("直播id[").append(getZId()).append("],")
			.append("教室id[").append(getClassId()).append("],")
			.append("学校id[").append(getSchoolId()).append("],")
			.append("辅讲教师ID[").append(getUserId()).append("],")
			.append("备用字段[").append(getBak()).append("],")
			.append("备用字段[").append(getBak1()).append("],")
			.append("备用字段[").append(getBak2()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LoFschedule == false) return false;
		if(this == obj) return true;
		LoFschedule other = (LoFschedule)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
