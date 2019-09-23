package com.zzrenfeng.zhsx.model.eclassbrand.course;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-03-24 17:46:39
 * @see com.zzrenfeng.zhsx.model.eclassbrand.course.classbrand.model.CourseScheduleBigTime
 */

public class CourseScheduleBigTime {

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 节次
	 */
	private Integer bigSectionOfDay;
	/**
	 * 对应的奇数小节
	 */
	private Integer oddSectionOfDay;
	/**
	 * 对应的偶数小节
	 */
	private Integer evenSectionOfDay;
	/**
	 * 开始时间
	 */
	private java.util.Date startTime;
	/**
	 * 结束时间
	 */
	private java.util.Date endTime;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 更新时间
	 */
	private java.util.Date modiyDate;

	// 字符串类型的节次
	private String bigSectionOfDayString;

	// get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}

	public String getBigSectionOfDayString() {
		return bigSectionOfDayString;
	}

	public void setBigSectionOfDayString(String bigSectionOfDayString) {
		this.bigSectionOfDayString = bigSectionOfDayString;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setBigSectionOfDay(Integer value) {
		this.bigSectionOfDay = value;
	}

	public Integer getBigSectionOfDay() {
		return this.bigSectionOfDay;
	}

	public void setOddSectionOfDay(Integer value) {
		this.oddSectionOfDay = value;
	}

	public Integer getOddSectionOfDay() {
		return this.oddSectionOfDay;
	}

	public void setEvenSectionOfDay(Integer value) {
		this.evenSectionOfDay = value;
	}

	public Integer getEvenSectionOfDay() {
		return this.evenSectionOfDay;
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
		return new StringBuffer().append("id[").append(getId()).append("],").append("节次[").append(getBigSectionOfDay())
				.append("],").append("对应的奇数小节[").append(getOddSectionOfDay()).append("],").append("对应的偶数小节[")
				.append(getEvenSectionOfDay()).append("],").append("开始时间[").append(getStartTime()).append("],")
				.append("结束时间[").append(getEndTime()).append("],").append("创建时间[").append(getCreateDate()).append("],")
				.append("更新时间[").append(getModiyDate()).append("],").toString();
	}

}
