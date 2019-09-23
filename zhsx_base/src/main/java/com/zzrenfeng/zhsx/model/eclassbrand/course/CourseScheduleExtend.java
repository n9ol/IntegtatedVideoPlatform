package com.zzrenfeng.zhsx.model.eclassbrand.course;

import java.util.List;

public class CourseScheduleExtend {

	/**
	 * 教室主键id
	 */
	private java.lang.String classroomId;

	/**
	 * 星期几
	 */
	private Integer dayOfWeek;

	/**
	 * 查询字段,显示字段
	 */
	private List<String> listSign;

	public java.lang.String getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(java.lang.String classroomId) {
		this.classroomId = classroomId;
	}

	public Integer getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public List<String> getListSign() {
		return listSign;
	}

	public void setListSign(List<String> listSign) {
		this.listSign = listSign;
	}

	@Override
	public String toString() {
		return "CourseScheduleExtend [classroomId=" + classroomId + ", dayOfWeek=" + dayOfWeek + ", listSign="
				+ listSign + "]";
	}

}
