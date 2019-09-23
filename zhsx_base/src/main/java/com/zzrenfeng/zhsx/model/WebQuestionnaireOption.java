package com.zzrenfeng.zhsx.model;

import java.util.Date;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class WebQuestionnaireOption {

	private String id;
	private String qid;
	private String option;
	private String createId;
	private Date createTime;
	private String isShown;
	private String sort;
	private java.lang.String nickName;// 用户昵称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
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

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public java.lang.String getNickName() {
		return nickName;
	}

	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("主键[").append(getId()).append("],").append("题目id[").append(getQid())
				.append("],").append("选项[").append(getOption()).append("],").append("用户id[").append(getCreateId())
				.append("],").append("排序[").append(getSort()).append("],").append("备用[").append(getIsShown())
				.append("],").toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	// public boolean equals(Object obj) {
	// if(obj instanceof WebQuestion == false) return false;
	// if(this == obj) return true;
	// WebQuestionnaireOption other = (WebQuestionnaireOption)obj;
	// return new EqualsBuilder()
	// .append(getId(),other.getId())
	// .isEquals();
	// }
}
