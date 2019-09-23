package com.zzrenfeng.zhsx.model.eclassbrand.social;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-08-30 11:02:58
 * @see com.zzrenfeng.model.SocialExam2
 */

public class SocialExam2 {

	/**
	 * 主键id
	 */
	private java.lang.String id;
	/**
	 * 教室id
	 */
	@NotNull(message = "{notnull}")
	private java.lang.String classroomId;
	/**
	 * 教室名称
	 */
	@NotNull(message = "{notnull}")
	private java.lang.String classroomName;
	/**
	 * 考试日期
	 */
	@NotNull(message = "{notnull}")
	private java.util.Date examDate;
	/**
	 * 考试开始时间
	 */
	@NotNull(message = "{notnull}")
	private java.util.Date startTime;
	/**
	 * 考试结束时间
	 */
	@NotNull(message = "{notnull}")
	private java.util.Date endTime;
	/**
	 * 第一行内容
	 */
	private java.lang.String firstLineInfo;
	/**
	 * 第一行字体颜色
	 */
	private java.lang.String firstLineColor;
	/**
	 * 第一行字体大小
	 */
	private Integer firstLineFontsize;
	/**
	 * 第一行字体是否加粗
	 */
	private java.lang.Boolean firstLineBold;
	/**
	 * 第一行对齐方式
	 */
	private java.lang.String firstLineAlignment;
	/**
	 * 第二行内容
	 */
	private java.lang.String secondLineInfo;
	/**
	 * 第二行字体颜色
	 */
	private java.lang.String secondLineColor;
	/**
	 * 第二行字体大小
	 */
	private Integer secondLineFontsize;
	/**
	 * 第二行字体是否加粗
	 */
	private java.lang.Boolean secondLineBold;
	/**
	 * 第二行对齐方式
	 */
	private java.lang.String secondLineAlignment;
	/**
	 * 第三行内容
	 */
	private java.lang.String thirdLineInfo;
	/**
	 * 第三行字体颜色
	 */
	private java.lang.String thirdLineColor;
	/**
	 * 第三行字体大小
	 */
	private Integer thirdLineFontsize;
	/**
	 * 第三行字体是否加粗
	 */
	private java.lang.Boolean thirdLineBold;
	/**
	 * 第三行对齐方式
	 */
	private java.lang.String thirdLineAlignment;
	/**
	 * 第四行内容
	 */
	private java.lang.String fourthLineInfo;
	/**
	 * 第四行字体颜色
	 */
	private java.lang.String fourthLineColor;
	/**
	 * 第四行字体大小
	 */
	private Integer fourthLineFontsize;
	/**
	 * 第四行字体是否加粗
	 */
	private java.lang.Boolean fourthLineBold;
	/**
	 * 第四行对齐方式
	 */
	private java.lang.String fourthLineAlignment;
	/**
	 * 第五行内容
	 */
	private java.lang.String fifthLineInfo;
	/**
	 * 第五行字体颜色
	 */
	private java.lang.String fifthLineColor;
	/**
	 * 第五行字体大小
	 */
	private Integer fifthLineFontsize;
	/**
	 * 第五行字体是否加粗
	 */
	private java.lang.Boolean fifthLineBold;
	/**
	 * 第五行对齐方式
	 */
	private java.lang.String fifthLineAlignment;
	/**
	 * 信息类型( E 社会考试信息, C 教室预约信息 )
	 */
	@Pattern(regexp = "[EC]")
	private java.lang.String infoType;
	/**
	 * 添加时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modiyDate;

	// 扩展字段
	/**
	 * 搜索
	 */
	private java.lang.String search;

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

	public java.lang.String getFirstLineInfo() {
		return firstLineInfo;
	}

	public void setFirstLineInfo(java.lang.String firstLineInfo) {
		this.firstLineInfo = firstLineInfo;
	}

	public java.lang.String getFirstLineColor() {
		return firstLineColor;
	}

	public void setFirstLineColor(java.lang.String firstLineColor) {
		this.firstLineColor = firstLineColor;
	}

	public Integer getFirstLineFontsize() {
		return firstLineFontsize;
	}

	public void setFirstLineFontsize(Integer firstLineFontsize) {
		this.firstLineFontsize = firstLineFontsize;
	}

	public java.lang.Boolean getFirstLineBold() {
		return firstLineBold;
	}

	public void setFirstLineBold(java.lang.Boolean firstLineBold) {
		this.firstLineBold = firstLineBold;
	}

	public java.lang.String getFirstLineAlignment() {
		return firstLineAlignment;
	}

	public void setFirstLineAlignment(java.lang.String firstLineAlignment) {
		this.firstLineAlignment = firstLineAlignment;
	}

	public java.lang.String getSecondLineInfo() {
		return secondLineInfo;
	}

	public void setSecondLineInfo(java.lang.String secondLineInfo) {
		this.secondLineInfo = secondLineInfo;
	}

	public java.lang.String getSecondLineColor() {
		return secondLineColor;
	}

	public void setSecondLineColor(java.lang.String secondLineColor) {
		this.secondLineColor = secondLineColor;
	}

	public Integer getSecondLineFontsize() {
		return secondLineFontsize;
	}

	public void setSecondLineFontsize(Integer secondLineFontsize) {
		this.secondLineFontsize = secondLineFontsize;
	}

	public java.lang.Boolean getSecondLineBold() {
		return secondLineBold;
	}

	public void setSecondLineBold(java.lang.Boolean secondLineBold) {
		this.secondLineBold = secondLineBold;
	}

	public java.lang.String getSecondLineAlignment() {
		return secondLineAlignment;
	}

	public void setSecondLineAlignment(java.lang.String secondLineAlignment) {
		this.secondLineAlignment = secondLineAlignment;
	}

	public java.lang.String getThirdLineInfo() {
		return thirdLineInfo;
	}

	public void setThirdLineInfo(java.lang.String thirdLineInfo) {
		this.thirdLineInfo = thirdLineInfo;
	}

	public java.lang.String getThirdLineColor() {
		return thirdLineColor;
	}

	public void setThirdLineColor(java.lang.String thirdLineColor) {
		this.thirdLineColor = thirdLineColor;
	}

	public Integer getThirdLineFontsize() {
		return thirdLineFontsize;
	}

	public void setThirdLineFontsize(Integer thirdLineFontsize) {
		this.thirdLineFontsize = thirdLineFontsize;
	}

	public java.lang.Boolean getThirdLineBold() {
		return thirdLineBold;
	}

	public void setThirdLineBold(java.lang.Boolean thirdLineBold) {
		this.thirdLineBold = thirdLineBold;
	}

	public java.lang.String getThirdLineAlignment() {
		return thirdLineAlignment;
	}

	public void setThirdLineAlignment(java.lang.String thirdLineAlignment) {
		this.thirdLineAlignment = thirdLineAlignment;
	}

	public java.lang.String getFourthLineInfo() {
		return fourthLineInfo;
	}

	public void setFourthLineInfo(java.lang.String fourthLineInfo) {
		this.fourthLineInfo = fourthLineInfo;
	}

	public java.lang.String getFourthLineColor() {
		return fourthLineColor;
	}

	public void setFourthLineColor(java.lang.String fourthLineColor) {
		this.fourthLineColor = fourthLineColor;
	}

	public Integer getFourthLineFontsize() {
		return fourthLineFontsize;
	}

	public void setFourthLineFontsize(Integer fourthLineFontsize) {
		this.fourthLineFontsize = fourthLineFontsize;
	}

	public java.lang.Boolean getFourthLineBold() {
		return fourthLineBold;
	}

	public void setFourthLineBold(java.lang.Boolean fourthLineBold) {
		this.fourthLineBold = fourthLineBold;
	}

	public java.lang.String getFourthLineAlignment() {
		return fourthLineAlignment;
	}

	public void setFourthLineAlignment(java.lang.String fourthLineAlignment) {
		this.fourthLineAlignment = fourthLineAlignment;
	}

	public java.lang.String getFifthLineInfo() {
		return fifthLineInfo;
	}

	public void setFifthLineInfo(java.lang.String fifthLineInfo) {
		this.fifthLineInfo = fifthLineInfo;
	}

	public java.lang.String getFifthLineColor() {
		return fifthLineColor;
	}

	public void setFifthLineColor(java.lang.String fifthLineColor) {
		this.fifthLineColor = fifthLineColor;
	}

	public Integer getFifthLineFontsize() {
		return fifthLineFontsize;
	}

	public void setFifthLineFontsize(Integer fifthLineFontsize) {
		this.fifthLineFontsize = fifthLineFontsize;
	}

	public java.lang.Boolean getFifthLineBold() {
		return fifthLineBold;
	}

	public void setFifthLineBold(java.lang.Boolean fifthLineBold) {
		this.fifthLineBold = fifthLineBold;
	}

	public java.lang.String getFifthLineAlignment() {
		return fifthLineAlignment;
	}

	public void setFifthLineAlignment(java.lang.String fifthLineAlignment) {
		this.fifthLineAlignment = fifthLineAlignment;
	}

	public java.lang.String getInfoType() {
		return infoType;
	}

	public void setInfoType(java.lang.String infoType) {
		this.infoType = infoType;
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

	public java.lang.String getSearch() {
		return search;
	}

	public void setSearch(java.lang.String search) {
		this.search = search;
	}

	public Date getCurrTime() {
		return currTime;
	}

	public void setCurrTime(Date currTime) {
		this.currTime = currTime;
	}

	@Override
	public String toString() {
		return "SocialExam2 [id=" + id + ", classroomId=" + classroomId + ", classroomName=" + classroomName
				+ ", examDate=" + examDate + ", startTime=" + startTime + ", endTime=" + endTime + ", firstLineInfo="
				+ firstLineInfo + ", firstLineColor=" + firstLineColor + ", firstLineFontsize=" + firstLineFontsize
				+ ", firstLineBold=" + firstLineBold + ", firstLineAlignment=" + firstLineAlignment
				+ ", secondLineInfo=" + secondLineInfo + ", secondLineColor=" + secondLineColor
				+ ", secondLineFontsize=" + secondLineFontsize + ", secondLineBold=" + secondLineBold
				+ ", secondLineAlignment=" + secondLineAlignment + ", thirdLineInfo=" + thirdLineInfo
				+ ", thirdLineColor=" + thirdLineColor + ", thirdLineFontsize=" + thirdLineFontsize + ", thirdLineBold="
				+ thirdLineBold + ", thirdLineAlignment=" + thirdLineAlignment + ", fourthLineInfo=" + fourthLineInfo
				+ ", fourthLineColor=" + fourthLineColor + ", fourthLineFontsize=" + fourthLineFontsize
				+ ", fourthLineBold=" + fourthLineBold + ", fourthLineAlignment=" + fourthLineAlignment
				+ ", fifthLineInfo=" + fifthLineInfo + ", fifthLineColor=" + fifthLineColor + ", fifthLineFontsize="
				+ fifthLineFontsize + ", fifthLineBold=" + fifthLineBold + ", fifthLineAlignment=" + fifthLineAlignment
				+ ", infoType=" + infoType + ", createDate=" + createDate + ", modiyDate=" + modiyDate + ", search="
				+ search + ", currTime=" + currTime + "]";
	}

}
