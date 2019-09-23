package com.zzrenfeng.zhsx.model;



/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2019-05-21 18:17:22
 * @see com.zzrenfeng.zhsx.model.StudentBehaviorRecord
 */

public class StudentBehaviorRecord {
		

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 学校id
	 */
	private java.lang.String schoolId;
	/**
	 * 班级id
	 */
	private java.lang.String classId;
	/**
	 * 课程id
	 */
	private java.lang.String scheduleId;
	/**
	 * 采集数据的这个时刻(2019-02-22 11:22:03)
	 */
	private java.util.Date createDate;
	
	
	

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setSchoolId(java.lang.String value) {
		this.schoolId = value;
	}
	
	public java.lang.String getSchoolId() {
		return this.schoolId;
	}
	public void setClassId(java.lang.String value) {
		this.classId = value;
	}
	
	public java.lang.String getClassId() {
		return this.classId;
	}
	public void setScheduleId(java.lang.String value) {
		this.scheduleId = value;
	}
	
	public java.lang.String getScheduleId() {
		return this.scheduleId;
	}

	
	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}
	
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("主键[").append(getId()).append("],")
			.append("学校id[").append(getSchoolId()).append("],")
			.append("班级id[").append(getClassId()).append("],")
			.append("课程id[").append(getScheduleId()).append("],")
			.append("采集数据的这个时刻(2019-02-22 11:22:03)[").append(getCreateDate()).append("],")
			.toString();
	}
	

}

	
