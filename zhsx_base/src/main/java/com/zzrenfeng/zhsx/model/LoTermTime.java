package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-05-08 10:22:35
 * @see com.zzrenfeng.zhsx.model.LoTermTime
 */

public class LoTermTime {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 学校id
	 */
	private java.lang.String schoolId;
	/**
	 * 第一天的时间
	 */
	private java.util.Date firstDay;
	/**
	 * 最后一天
	 */
	private java.util.Date lastDay;
	/**
	 * 总周数
	 */
	private java.lang.Integer totalWeeks;
	//columns END 数据库字段结束
	
	
	
	//关联字段
	private java.util.Date currTime;
	
	
	
	
	
	
	//concstructor

	public LoTermTime(){
	}

	public LoTermTime(
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
	
	public java.util.Date getCurrTime() {
		return currTime;
	}

	public void setCurrTime(java.util.Date currTime) {
		this.currTime = currTime;
	}

	public java.lang.String getId() {
		return this.id;
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

	
	public void setFirstDay(java.util.Date value) {
		this.firstDay = value;
	}
	
	public java.util.Date getFirstDay() {
		return this.firstDay;
	}

	
	public void setLastDay(java.util.Date value) {
		this.lastDay = value;
	}
	
	public java.util.Date getLastDay() {
		return this.lastDay;
	}
	public void setTotalWeeks(java.lang.Integer value) {
		this.totalWeeks = value;
	}
	
	public java.lang.Integer getTotalWeeks() {
		return this.totalWeeks;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("学校id[").append(getSchoolId()).append("],")
			.append("第一天的时间[").append(getFirstDay()).append("],")
			.append("最后一天[").append(getLastDay()).append("],")
			.append("总周数[").append(getTotalWeeks()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LoTermTime == false) return false;
		if(this == obj) return true;
		LoTermTime other = (LoTermTime)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
