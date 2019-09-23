package com.zzrenfeng.zhsx.model.eclassbrand.social;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-03-21 10:05:45
 * @see com.zzrenfeng.classbrand.model.SocialExam
 */

public class SocialExam {

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 教室id
	 */
	@NotBlank
	private java.lang.String classroomId;
	/**
	 * 教室名称
	 */
	@NotBlank
	private java.lang.String classroomName;
	/**
	 * 考试名称
	 */
	@NotBlank
	private java.lang.String examName;
	/**
	 * 考试科目
	 */
	@NotBlank
	private java.lang.String subjectName;
	/**
	 * 考点名称
	 */
	private java.lang.String timeDay;
	/**
	 * 考场号
	 */
	@NotNull
	private java.lang.Integer examRoomNum;
	/**
	 * 考试日期
	 */
	@NotNull
	private java.util.Date examDate;
	/**
	 * 考试开始时间
	 */
	@NotNull
	private java.util.Date startTime;
	/**
	 * 考试结束时间
	 */
	@NotNull
	private java.util.Date endTime;
	/**
	 * 监考教师
	 */
	@NotBlank
	private java.lang.String teacherName;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 更新时间
	 */
	private java.util.Date modiyDate;
	/**
	 * 院系
	 */
	private java.lang.String bak;
	/**
	 * 专业
	 */
	private java.lang.String bak1;
	/**
	 * 班级
	 */
	private java.lang.String bak2;

	/**
	 * 模板类型 (1 校内普通考试，2 全国统一考试，3 校外考试 )
	 */
	@NotBlank
	@Pattern(regexp = "1|2|3", message = "字符不合法")
	private java.lang.String templateType;

	// 扩展字段
	/**
	 * 搜索
	 */
	private java.lang.String search;

	/**
	 * 查询字段,显示字段
	 */
	private List<String> listSign;

	/**
	 * 当前时间
	 */
	private Date currTime;

	// get and set
	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(java.lang.String classroomId) {
		this.classroomId = classroomId;
	}

	public java.lang.String getClassroomName() {
		return classroomName;
	}

	public void setClassroomName(java.lang.String classroomName) {
		this.classroomName = classroomName;
	}

	public java.lang.String getExamName() {
		return examName;
	}

	public void setExamName(java.lang.String examName) {
		this.examName = examName;
	}

	public java.lang.String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(java.lang.String subjectName) {
		this.subjectName = subjectName;
	}

	public java.lang.String getTimeDay() {
		return timeDay;
	}

	public void setTimeDay(java.lang.String timeDay) {
		this.timeDay = timeDay;
	}

	public java.lang.Integer getExamRoomNum() {
		return examRoomNum;
	}

	public void setExamRoomNum(java.lang.Integer examRoomNum) {
		this.examRoomNum = examRoomNum;
	}

	public java.util.Date getExamDate() {
		return examDate;
	}

	public void setExamDate(java.util.Date examDate) {
		this.examDate = examDate;
	}

	public java.util.Date getStartTime() {
		return startTime;
	}

	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}

	public java.util.Date getEndTime() {
		return endTime;
	}

	public void setEndTime(java.util.Date endTime) {
		this.endTime = endTime;
	}

	public java.lang.String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(java.lang.String teacherName) {
		this.teacherName = teacherName;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getModiyDate() {
		return modiyDate;
	}

	public void setModiyDate(java.util.Date modiyDate) {
		this.modiyDate = modiyDate;
	}

	public java.lang.String getBak() {
		return bak;
	}

	public void setBak(java.lang.String bak) {
		this.bak = bak;
	}

	public java.lang.String getBak1() {
		return bak1;
	}

	public void setBak1(java.lang.String bak1) {
		this.bak1 = bak1;
	}

	public java.lang.String getBak2() {
		return bak2;
	}

	public void setBak2(java.lang.String bak2) {
		this.bak2 = bak2;
	}

	public java.lang.String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(java.lang.String templateType) {
		this.templateType = templateType;
	}

	public java.lang.String getSearch() {
		return search;
	}

	public void setSearch(java.lang.String search) {
		this.search = search;
	}

	public List<String> getListSign() {
		return listSign;
	}

	public void setListSign(List<String> listSign) {
		this.listSign = listSign;
	}

	public Date getCurrTime() {
		return currTime;
	}

	public void setCurrTime(Date currTime) {
		this.currTime = currTime;
	}

	@Override
	public String toString() {
		return "SocialExam [id=" + id + ", classroomId=" + classroomId + ", classroomName=" + classroomName
				+ ", examName=" + examName + ", subjectName=" + subjectName + ", timeDay=" + timeDay + ", examRoomNum="
				+ examRoomNum + ", examDate=" + examDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", teacherName=" + teacherName + ", createDate=" + createDate + ", modiyDate=" + modiyDate + ", bak="
				+ bak + ", bak1=" + bak1 + ", bak2=" + bak2 + ", templateType=" + templateType + ", search=" + search
				+ ", listSign=" + listSign + ", currTime=" + currTime + "]";
	}

}
