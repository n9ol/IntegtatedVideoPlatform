package com.zzrenfeng.zhsx.model;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-09-19 16:53:27
 * @see com.zzrenfeng.zhsx.model.TpPracticeOption
 */

public class TpPracticeOption {

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 题目id
	 */
	private java.lang.String qid;
	/**
	 * 选项
	 */
	private java.lang.String option;
	/**
	 * 是否正确答案 是Y，否N
	 */
	private java.lang.String isRight;
	/**
	 * 出题人
	 */
	private java.lang.String createId;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 序号
	 */
	private java.lang.String sort;

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getQid() {
		return qid;
	}

	public void setQid(java.lang.String qid) {
		this.qid = qid;
	}

	public java.lang.String getOption() {
		return option;
	}

	public void setOption(java.lang.String option) {
		this.option = option;
	}

	public java.lang.String getIsRight() {
		return isRight;
	}

	public void setIsRight(java.lang.String isRight) {
		this.isRight = isRight;
	}

	public java.lang.String getCreateId() {
		return createId;
	}

	public void setCreateId(java.lang.String createId) {
		this.createId = createId;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getSort() {
		return sort;
	}

	public void setSort(java.lang.String sort) {
		this.sort = sort;
	}

	public String toString() {
		return new StringBuffer().append("id[").append(getId()).append("],")
				.append("题目id[").append(getQid()).append("],").append("选项[")
				.append(getOption()).append("],").append("是否正确答案 是Y，否N[")
				.append(getIsRight()).append("],").append("出题人[")
				.append(getCreateId()).append("],").append("创建时间[")
				.append(getCreateTime()).append("],").append(" 序号[")
				.append(getSort()).append("],").toString();
	}

}
