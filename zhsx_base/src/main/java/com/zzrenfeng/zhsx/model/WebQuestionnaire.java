package com.zzrenfeng.zhsx.model;

import java.util.Date;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class WebQuestionnaire {

	private String id;
	private String title;
	private String createId;
	private String modiyId;
	private Date createTime;
	private Date modiyTime;
	private String isShown;
	private java.lang.String nickName;// 用户昵称
	private String remarks;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public java.lang.String getNickName() {
		return nickName;
	}

	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getModiyId() {
		return modiyId;
	}

	public void setModiyId(String modiyId) {
		this.modiyId = modiyId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModiyTime() {
		return modiyTime;
	}

	public void setModiyTime(Date modiyTime) {
		this.modiyTime = modiyTime;
	}

	public String getIsShown() {
		return isShown;
	}

	public void setIsShown(String isShown) {
		this.isShown = isShown;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("主键[").append(getId()).append("],").append("标题[").append(getTitle())
				.append("],").append("用户id[").append(getCreateId()).append("],").append("修改人id[").append(getModiyId())
				.append("],").append("备用[").append(getIsShown()).append("],").toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	// public boolean equals(Object obj) {
	// if(obj instanceof WebQuestion == false) return false;
	// if(this == obj) return true;
	// WebQuestionnaire other = (WebQuestionnaire)obj;
	// return new EqualsBuilder()
	// .append(getId(),other.getId())
	// .isEquals();
	// }
}
