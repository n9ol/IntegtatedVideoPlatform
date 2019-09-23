package com.zzrenfeng.zhsx.model;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-27 17:28:49
 * @see com.zzrenfeng.zhsx.model.TestWebStuTestQuestion
 */

public class TestWebStuTestQuestion {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * questionId
	 */
	private java.lang.String questionId;
	/**
	 * studentId
	 */
	private java.lang.String studentId;
	/**
	 * questionType
	 */
	private java.lang.Integer questionType;
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
	 * questionText
	 */
	private java.lang.String questionText;
	/**
	 * sortCode
	 */
	private java.lang.Integer sortCode;
	/**
	 * isRight
	 */
	private Integer isRight;
	/**
	 * score
	 */
	private java.lang.Integer score;
	/**
	 * stuScore
	 */
	private java.lang.Integer stuScore;
	/**
	 * isComm
	 */
	private String testId;
	/**
	 * commTime
	 */
	private java.util.Date commTime;
	//columns END 数据库字段结束
	private List<TestWebStuTestQuestionAnswer> stuAnswer;//考试选择的答案
	private List<TestWebQuestionAnswer> answer;//根据问题id获取的考题选项
	private java.lang.String search ;
	private Integer questionNo;//题号
	
	//concstructor
	
	public TestWebStuTestQuestion(){
	}

	



	public List<TestWebQuestionAnswer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<TestWebQuestionAnswer> answer) {
		this.answer = answer;
	}

	public Integer getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}

	public java.lang.String getSearch() {
		return search;
	}

	public void setSearch(java.lang.String search) {
		this.search = search;
	}

	public List<TestWebStuTestQuestionAnswer> getStuAnswer() {
		return stuAnswer;
	}

	public void setStuAnswer(List<TestWebStuTestQuestionAnswer> stuAnswer) {
		this.stuAnswer = stuAnswer;
	}

	public TestWebStuTestQuestion(
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
	public void setQuestionId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.questionId = value;
	}
	
	public java.lang.String getQuestionId() {
		return this.questionId;
	}
	public void setStudentId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.studentId = value;
	}
	
	public java.lang.String getStudentId() {
		return this.studentId;
	}
	public void setQuestionType(java.lang.Integer value) {
		this.questionType = value;
	}
	
	public java.lang.Integer getQuestionType() {
		return this.questionType;
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
	public void setQuestionText(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.questionText = value;
	}
	
	public java.lang.String getQuestionText() {
		return this.questionText;
	}
	
	



	public java.lang.Integer getSortCode() {
		return sortCode;
	}





	public void setSortCode(java.lang.Integer sortCode) {
		this.sortCode = sortCode;
	}





	public void setIsRight(Integer value) {
		this.isRight = value;
	}
	
	public Integer getIsRight() {
		return this.isRight;
	}
	public void setScore(java.lang.Integer value) {
		this.score = value;
	}
	
	public java.lang.Integer getScore() {
		return this.score;
	}
	public void setStuScore(java.lang.Integer value) {
		this.stuScore = value;
	}
	
	public java.lang.Integer getStuScore() {
		return this.stuScore;
	}
	

	
	public String getTestId() {
		return testId;
	}





	public void setTestId(String testId) {
		this.testId = testId;
	}





	public void setCommTime(java.util.Date value) {
		this.commTime = value;
	}
	
	public java.util.Date getCommTime() {
		return this.commTime;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("questionId[").append(getQuestionId()).append("],")
			.append("studentId[").append(getStudentId()).append("],")
			.append("questionType[").append(getQuestionType()).append("],")
			.append("stuType[").append(getStuType()).append("],")
			.append("gradeName[").append(getGradeName()).append("],")
			.append("subjectName[").append(getSubjectName()).append("],")
			.append("volume[").append(getVolume()).append("],")
			.append("version[").append(getVersion()).append("],")
			.append("questionText[").append(getQuestionText()).append("],")
			.append("isRight[").append(getIsRight()).append("],")
			.append("score[").append(getScore()).append("],")
			.append("stuScore[").append(getStuScore()).append("],")
			.append("testId[").append(getTestId()).append("],")
			.append("commTime[").append(getCommTime()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TestWebStuTestQuestion == false) return false;
		if(this == obj) return true;
		TestWebStuTestQuestion other = (TestWebStuTestQuestion)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
