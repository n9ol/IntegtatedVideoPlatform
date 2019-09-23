package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-14 10:44:27
 * @see com.zzrenfeng.zhsx.model.TestWebStuTestQuestionAnswer
 */

public class TestWebStuTestQuestionAnswer {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * testId
	 */
	private java.lang.String testId;
	/**
	 * 学生id
	 */
	private java.lang.String stuId;
	/**
	 * 问题id
	 */
	private java.lang.String questionId;
	/**
	 * answerCode
	 */
	private java.lang.String answerCode;
	/**
	 * 学生选择的答案id
	 */
	private java.lang.String answerId;
	private java.lang.String stuTestId;
	/**
	 * 该选项是否为答案
	 */
	private Integer isRight;
	/**
	 * 学生是否答对 0：不对 1：正确
	 */
	private Integer stuIsRight;
	/**
	 * 备用1关联字段stuQuestion的主建
	 */
	private java.lang.String bak1;
	/**
	 * 该考生是否选择该选项N：否Y：是
	 */
	private java.lang.String bak2;
	/**
	 * 备用3
	 */
	private java.lang.String bak3;
	private  String answerText;//选项
	//columns END 数据库字段结束
	
	//concstructor
	
	public TestWebStuTestQuestionAnswer(){
	}

	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public TestWebStuTestQuestionAnswer(
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
	
	public java.lang.String getStuTestId() {
		return stuTestId;
	}

	public void setStuTestId(java.lang.String stuTestId) {
		this.stuTestId = stuTestId;
	}

	public java.lang.String getId() {
		return this.id;
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
	public void setStuId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.stuId = value;
	}
	
	public java.lang.String getStuId() {
		return this.stuId;
	}
	public void setQuestionId(java.lang.String value) {
		this.questionId = value;
	}
	
	public java.lang.String getQuestionId() {
		return this.questionId;
	}
	public void setAnswerCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.answerCode = value;
	}
	
	public java.lang.String getAnswerCode() {
		return this.answerCode;
	}
	public void setAnswerId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.answerId = value;
	}
	
	public java.lang.String getAnswerId() {
		return this.answerId;
	}
	public void setIsRight(Integer value) {
		this.isRight = value;
	}
	
	public Integer getIsRight() {
		return this.isRight;
	}
	public void setStuIsRight(Integer value) {
		this.stuIsRight = value;
	}
	
	public Integer getStuIsRight() {
		return this.stuIsRight;
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
			.append("testId[").append(getTestId()).append("],")
			.append("学生id[").append(getStuId()).append("],")
			.append("问题id[").append(getQuestionId()).append("],")
			.append("answerCode[").append(getAnswerCode()).append("],")
			.append("学生选择的答案id[").append(getAnswerId()).append("],")
			.append("该答案是否正确0：不正确1：正确[").append(getIsRight()).append("],")
			.append("学生是否答对 0：不对 1：正确[").append(getStuIsRight()).append("],")
			.append("备用1[").append(getBak1()).append("],")
			.append("备用2[").append(getBak2()).append("],")
			.append("备用3[").append(getBak3()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TestWebStuTestQuestionAnswer == false) return false;
		if(this == obj) return true;
		TestWebStuTestQuestionAnswer other = (TestWebStuTestQuestionAnswer)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
