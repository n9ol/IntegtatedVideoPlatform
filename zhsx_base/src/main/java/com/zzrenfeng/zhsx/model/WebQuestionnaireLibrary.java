package com.zzrenfeng.zhsx.model;

import java.util.Date;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class WebQuestionnaireLibrary {

	private String id;
	private String question;
	private String type;
	private String createId;
	private Date createTime;
	private String isShown;
	private java.lang.String nickName;// 用户昵称
	private String option;
	private String bak;

	public String getBak() {
		return bak;
	}

	public void setBak(String bak) {
		this.bak = bak;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsShown() {
		return isShown;
	}

	public void setIsShown(String isShown) {
		this.isShown = isShown;
	}

	public java.lang.String getNickName() {
		return nickName;
	}

	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("主键[").append(getId()).append("],").append("标题[").append(getQuestion())
				.append("],").append("用户id[").append(getCreateId()).append("],").append("备用[").append(getIsShown())
				.append("],").toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	// public boolean equals(Object obj) {
	// if(obj instanceof WebQuestion == false) return false;
	// if(this == obj) return true;
	// WebQuestionnaireLibrary other = (WebQuestionnaireLibrary)obj;
	// return new EqualsBuilder()
	// .append(getId(),other.getId())
	// .isEquals();
	// }

}
