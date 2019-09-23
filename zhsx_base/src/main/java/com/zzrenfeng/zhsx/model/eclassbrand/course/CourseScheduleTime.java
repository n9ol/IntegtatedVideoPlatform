package com.zzrenfeng.zhsx.model.eclassbrand.course;

import javax.validation.constraints.NotNull;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-03-12 16:48:59
 * @see com.zzrenfeng.zhsx.model.eclassbrand.course.classbrand.model.CourseScheduleTime
 */

public class CourseScheduleTime {

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 一天的第几节
	 */
	@NotNull
	private Integer sectionOfDay;
	/**
	 * 开始时间
	 */
	@NotNull
	private java.util.Date startTime;
	/**
	 * 结束时间
	 */
	@NotNull
	private java.util.Date endTime;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 更新时间
	 */
	private java.util.Date modiyDate;
	
	//20190307-zjc add;扩展字段
	private String startTimeStr;    //开始时间字符串
	private String endTimeStr;    //结束时间字符串

	// get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setSectionOfDay(Integer value) {
		this.sectionOfDay = value;
	}

	public Integer getSectionOfDay() {
		return this.sectionOfDay;
	}

	public void setStartTime(java.util.Date value) {
		this.startTime = value;
	}

	public java.util.Date getStartTime() {
		return this.startTime;
	}

	public void setEndTime(java.util.Date value) {
		this.endTime = value;
	}

	public java.util.Date getEndTime() {
		return this.endTime;
	}

	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}

	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	public void setModiyDate(java.util.Date value) {
		this.modiyDate = value;
	}

	public java.util.Date getModiyDate() {
		return this.modiyDate;
	}

	public String toString() {
		return new StringBuffer().append("主键[").append(getId()).append("],").append("一天的第几节[").append(getSectionOfDay())
				.append("],").append("开始时间[").append(getStartTime()).append("],").append("结束时间[").append(getEndTime())
				.append("],").append("创建时间[").append(getCreateDate()).append("],").append("更新时间[")
				.append(getModiyDate()).append("],").toString();
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

}
