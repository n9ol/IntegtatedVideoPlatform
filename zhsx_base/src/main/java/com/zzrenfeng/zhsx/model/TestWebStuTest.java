package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-13 18:01:31
 * @see com.zzrenfeng.zhsx.model.TestWebStuTest
 */

public class TestWebStuTest {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * tempId
	 */
	private java.lang.String tempId;
	/**
	 * studentId
	 */
	private java.lang.String studentId;
	/**
	 * tempTitle
	 */
	private java.lang.String tempTitle;
	/**
	 * testId
	 */
	private java.lang.String testId;
	/**
	 * memberId
	 */
	private java.lang.String memberId;
	/**
	 * testTitle
	 */
	private java.lang.String testTitle;
	/**
	 * stuType
	 */
	private java.lang.String stuType;
	/**
	 * gradeName
	 */
	private java.lang.String gradeName;
	/**
	 * subjectName
	 */
	private java.lang.String subjectName;
	/**
	 * volume
	 */
	private Integer volume;
	/**
	 * version
	 */
	private java.lang.String version;
	/**
	 * scores
	 */
	private java.lang.String scores;
	/**
	 * totalScores
	 */
	private java.lang.String totalScores;
	/**
	 * questions
	 */
	private java.lang.Integer questions;
	/**
	 * times
	 */
	private Integer times;
	/**
	 * addTime
	 */
	private java.util.Date addTime;
	/**
	 * isDone
	 */
	private Integer isDone;
	/**
	 * doneTime
	 */
	private java.util.Date doneTime;
	/**
	 * isComm
	 */
	private Integer isComm;
	/**
	 * commTime
	 */
	private java.util.Date commTime;
	/**
	 * 备注1
	 */
	private java.lang.String bak1;
	/**
	 * 备注2
	 */
	private java.lang.String bak2;
	/**
	 * 备注3
	 */
	private java.lang.String bak3;
	//columns END 数据库字段结束
	private java.lang.String userName;//答题人姓名
	private java.lang.String search;//搜索字段
	private java.lang.String searchOne;//关键词搜索字段
	private java.lang.String versionName;//版本名称
	
	//concstructor

	
	
	public TestWebStuTest(){
	}
	public java.lang.String getVersionName() {
		return versionName;
	}
	public void setVersionName(java.lang.String versionName) {
		this.versionName = versionName;
	}
	public java.lang.String getSearchOne() {
		return searchOne;
	}
	public void setSearchOne(java.lang.String searchOne) {
		this.searchOne = searchOne;
	}
	public java.lang.String getSearch() {
		return search;
	}
	public void setSearch(java.lang.String search) {
		this.search = search;
	}
	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public TestWebStuTest(
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
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setTempId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.tempId = value;
	}
	
	public java.lang.String getTempId() {
		return this.tempId;
	}
	public void setStudentId(java.lang.String value) {
		this.studentId = value;
	}
	
	public java.lang.String getStudentId() {
		return this.studentId;
	}
	public void setTempTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.tempTitle = value;
	}
	
	public java.lang.String getTempTitle() {
		return this.tempTitle;
	}
	public void setTestId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.testId = value;
	}
	
	public java.lang.String getTestId() {
		return this.testId;
	}
	public void setMemberId(java.lang.String value) {
		this.memberId = value;
	}
	
	public java.lang.String getMemberId() {
		return this.memberId;
	}
	public void setTestTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.testTitle = value;
	}
	
	public java.lang.String getTestTitle() {
		return this.testTitle;
	}
	public void setStuType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.stuType = value;
	}
	
	public java.lang.String getStuType() {
		return this.stuType;
	}
	public void setGradeName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gradeName = value;
	}
	
	public java.lang.String getGradeName() {
		return this.gradeName;
	}
	public void setSubjectName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.subjectName = value;
	}
	
	public java.lang.String getSubjectName() {
		return this.subjectName;
	}
	public void setVolume(Integer value) {
		this.volume = value;
	}
	
	public Integer getVolume() {
		return this.volume;
	}
	public void setVersion(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.version = value;
	}
	
	public java.lang.String getVersion() {
		return this.version;
	}
	public void setScores(String value) {
		this.scores = value;
	}
	
	public String getScores() {
		return this.scores;
	}
	public void setTotalScores(String value) {
		this.totalScores = value;
	}
	
	public String getTotalScores() {
		return this.totalScores;
	}
	public void setQuestions(java.lang.Integer value) {
		this.questions = value;
	}
	
	public java.lang.Integer getQuestions() {
		return this.questions;
	}
	public void setTimes(Integer value) {
		this.times = value;
	}
	
	public Integer getTimes() {
		return this.times;
	}

	
	public void setAddTime(java.util.Date value) {
		this.addTime = value;
	}
	
	public java.util.Date getAddTime() {
		return this.addTime;
	}
	public void setIsDone(Integer value) {
		this.isDone = value;
	}
	
	public Integer getIsDone() {
		return this.isDone;
	}

	
	public void setDoneTime(java.util.Date value) {
		this.doneTime = value;
	}
	
	public java.util.Date getDoneTime() {
		return this.doneTime;
	}
	public void setIsComm(Integer value) {
		this.isComm = value;
	}
	
	public Integer getIsComm() {
		return this.isComm;
	}

	
	public void setCommTime(java.util.Date value) {
		this.commTime = value;
	}
	
	public java.util.Date getCommTime() {
		return this.commTime;
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
	public void setBak3(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak3 = value;
	}
	
	public java.lang.String getBak3() {
		return this.bak3;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("tempId[").append(getTempId()).append("],")
			.append("studentId[").append(getStudentId()).append("],")
			.append("tempTitle[").append(getTempTitle()).append("],")
			.append("testId[").append(getTestId()).append("],")
			.append("memberId[").append(getMemberId()).append("],")
			.append("testTitle[").append(getTestTitle()).append("],")
			.append("stuType[").append(getStuType()).append("],")
			.append("gradeName[").append(getGradeName()).append("],")
			.append("subjectName[").append(getSubjectName()).append("],")
			.append("volume[").append(getVolume()).append("],")
			.append("version[").append(getVersion()).append("],")
			.append("scores[").append(getScores()).append("],")
			.append("totalScores[").append(getTotalScores()).append("],")
			.append("questions[").append(getQuestions()).append("],")
			.append("times[").append(getTimes()).append("],")
			.append("addTime[").append(getAddTime()).append("],")
			.append("isDone[").append(getIsDone()).append("],")
			.append("doneTime[").append(getDoneTime()).append("],")
			.append("isComm[").append(getIsComm()).append("],")
			.append("commTime[").append(getCommTime()).append("],")
			.append("备注1[").append(getBak1()).append("],")
			.append("备注2[").append(getBak2()).append("],")
			.append("备注3[").append(getBak3()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TestWebStuTest == false) return false;
		if(this == obj) return true;
		TestWebStuTest other = (TestWebStuTest)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
