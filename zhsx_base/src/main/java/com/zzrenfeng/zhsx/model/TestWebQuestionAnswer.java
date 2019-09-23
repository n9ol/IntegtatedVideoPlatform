package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-12 09:51:44
 * @see com.zzrenfeng.zhsx.model.TestWebQuestionAnswer
 */

public class TestWebQuestionAnswer {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * questionId
	 */
	private java.lang.String questionId;
	/**
	 * answerCode
	 */
	private Integer answerCode;
	/**
	 * questionType
	 */
	private Integer questionType;
	/**
	 * answerText
	 */
	private java.lang.String answerText;
	/**
	 * answerMemo
	 */
	private java.lang.String answerMemo;
	/**
	 * isRight
	 */
	private Integer isRight;
	/**
	 * bak1
	 */
	private java.lang.String bak1;
	/**
	 * bak2
	 */
	private java.lang.String bak2;
	/**
	 * bak3
	 */
	private java.lang.String bak3;
	//columns END 数据库字段结束
	private String xuanxiang;
	//concstructor

	public TestWebQuestionAnswer(){
	}

	public String getXuanxiang() {
		return xuanxiang;
	}

	public void setXuanxiang(String xuanxiang) {
		this.xuanxiang = xuanxiang;
	}

	public TestWebQuestionAnswer(
		java.lang.String id,
		java.lang.String questionId
	){
		this.id = id;
		this.questionId = questionId;
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
	
	public Integer getAnswerCode() {
		return answerCode;
	}

	public void setAnswerCode(Integer answerCode) {
		this.answerCode = answerCode;
	}

	public void setQuestionType(Integer value) {
		this.questionType = value;
	}
	
	public Integer getQuestionType() {
		return this.questionType;
	}
	public void setAnswerText(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.answerText = value;
	}
	
	public java.lang.String getAnswerText() {
		return this.answerText;
	}
	public void setAnswerMemo(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.answerMemo = value;
	}
	
	public java.lang.String getAnswerMemo() {
		return this.answerMemo;
	}
	public void setIsRight(Integer value) {
		this.isRight = value;
	}
	
	public Integer getIsRight() {
		return this.isRight;
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
			.append("questionId[").append(getQuestionId()).append("],")
			.append("answerCode[").append(getAnswerCode()).append("],")
			.append("questionType[").append(getQuestionType()).append("],")
			.append("answerText[").append(getAnswerText()).append("],")
			.append("answerMemo[").append(getAnswerMemo()).append("],")
			.append("isRight[").append(getIsRight()).append("],")
			.append("bak1[").append(getBak1()).append("],")
			.append("bak2[").append(getBak2()).append("],")
			.append("bak3[").append(getBak3()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getQuestionId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TestWebQuestionAnswer == false) return false;
		if(this == obj) return true;
		TestWebQuestionAnswer other = (TestWebQuestionAnswer)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getQuestionId(),other.getQuestionId())
			.isEquals();
	}
}

	
