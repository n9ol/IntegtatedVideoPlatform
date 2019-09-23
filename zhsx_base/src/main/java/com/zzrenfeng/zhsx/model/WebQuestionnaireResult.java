package com.zzrenfeng.zhsx.model;

import java.util.Date;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class WebQuestionnaireResult {

	private String id;
	private String userId;
	private String optionId;
	private String opinion;
	private String type;
	private Date createTime;
	private String bak;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBak() {
		return bak;
	}

	public void setBak(String bak) {
		this.bak = bak;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("主键[").append(getId()).append("],").append("用户id[").append(getUserId())
				.append("],").append("选项[").append(getOptionId()).append("],").append("意见[").append(getOpinion())
				.append("],").append("问题类型[").append(getType()).append("],").append("备用[").append(getBak()).append("],")
				.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	// public boolean equals(Object obj) {
	// if(obj instanceof WebQuestion == false) return false;
	// if(this == obj) return true;
	// WebQuestion other = (WebQuestion)obj;
	// return new EqualsBuilder()
	// .append(getId(),other.getId())
	// .isEquals();
	// }
}
