package com.zzrenfeng.zhsx.model;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-11 18:34:22
 * @see com.zzrenfeng.zhsx.model.TestWebQuestion
 */

public class TestWebQuestion {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * memberId
	 */
	private java.lang.String memberId;
	/**
	 * questionCode
	 */
	private java.lang.String questionCode;
	/**
	 * 试题类型
	 */
	private Integer questionType;
	/**
	 * 教育类型
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
	 * 册
	 */
	private Integer volume;
	/**
	 * 版本
	 */
	private java.lang.String version;
	/**
	 * knowledges
	 */
	private java.lang.String knowledges;
	/**
	 * difficulty
	 */
	private Integer difficulty;
	/**
	 * questionText
	 */
	private java.lang.String questionText;
	/**
	 * questionMemo
	 */
	private java.lang.String questionMemo;
	/**
	 * isShare
	 */
	private Integer isShare;
	/**
	 * isDrop
	 */
	private Integer isDrop;
	/**
	 * addTime
	 */
	private java.util.Date addTime;
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
	private java.lang.String search;
	private java.lang.String search1;
	private java.lang.String userName;
	private java.lang.String answerText;
	private List<TestWebQuestionAnswer> answers;
	private java.lang.String scores;//题分值
	private java.lang.String sortCode;//题的顺序
	private java.lang.String testId;//试卷id
	private java.lang.String versionName;//版本
	private Integer questions;//随机测试生成的数量
	
	//concstructor

	
	public TestWebQuestion(){
	}

	

	public Integer getQuestions() {
		return questions;
	}



	public void setQuestions(Integer questions) {
		this.questions = questions;
	}



	public java.lang.String getVersionName() {
		return versionName;
	}

	public void setVersionName(java.lang.String versionName) {
		this.versionName = versionName;
	}

	public java.lang.String getTestId() {
		return testId;
	}

	public void setTestId(java.lang.String testId) {
		this.testId = testId;
	}

	public java.lang.String getScores() {
		return scores;
	}

	public void setScores(java.lang.String scores) {
		this.scores = scores;
	}

	public java.lang.String getSortCode() {
		return sortCode;
	}

	public void setSortCode(java.lang.String sortCode) {
		this.sortCode = sortCode;
	}

	public java.lang.String getSearch1() {
		return search1;
	}

	public void setSearch1(java.lang.String search1) {
		this.search1 = search1;
	}

	public java.lang.String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(java.lang.String answerText) {
		this.answerText = answerText;
	}

	public List<TestWebQuestionAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<TestWebQuestionAnswer> answers) {
		this.answers = answers;
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

	public TestWebQuestion(
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
	
	public java.lang.String getMemberId() {
		return memberId;
	}

	public void setMemberId(java.lang.String memberId) {
		this.memberId = memberId;
	}

	public void setQuestionCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.questionCode = value;
	}
	
	public java.lang.String getQuestionCode() {
		return this.questionCode;
	}
	public void setQuestionType(Integer value) {
		this.questionType = value;
	}
	
	public Integer getQuestionType() {
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
	public void setKnowledges(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.knowledges = value;
	}
	
	public java.lang.String getKnowledges() {
		return this.knowledges;
	}
	public void setDifficulty(Integer value) {
		this.difficulty = value;
	}
	
	public Integer getDifficulty() {
		return this.difficulty;
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
	public void setQuestionMemo(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.questionMemo = value;
	}
	
	public java.lang.String getQuestionMemo() {
		return this.questionMemo;
	}
	public void setIsShare(Integer value) {
		this.isShare = value;
	}
	
	public Integer getIsShare() {
		return this.isShare;
	}
	public void setIsDrop(Integer value) {
		this.isDrop = value;
	}
	
	public Integer getIsDrop() {
		return this.isDrop;
	}

	
	public void setAddTime(java.util.Date value) {
		this.addTime = value;
	}
	
	public java.util.Date getAddTime() {
		return this.addTime;
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
			.append("memberId[").append(getMemberId()).append("],")
			.append("questionCode[").append(getQuestionCode()).append("],")
			.append("试题类型[").append(getQuestionType()).append("],")
			.append("教育类型[").append(getStuType()).append("],")
			.append("gradeName[").append(getGradeName()).append("],")
			.append("subjectName[").append(getSubjectName()).append("],")
			.append("册[").append(getVolume()).append("],")
			.append("版本[").append(getVersion()).append("],")
			.append("knowledges[").append(getKnowledges()).append("],")
			.append("difficulty[").append(getDifficulty()).append("],")
			.append("questionText[").append(getQuestionText()).append("],")
			.append("questionMemo[").append(getQuestionMemo()).append("],")
			.append("isShare[").append(getIsShare()).append("],")
			.append("isDrop[").append(getIsDrop()).append("],")
			.append("addTime[").append(getAddTime()).append("],")
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
		if(obj instanceof TestWebQuestion == false) return false;
		if(this == obj) return true;
		TestWebQuestion other = (TestWebQuestion)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
