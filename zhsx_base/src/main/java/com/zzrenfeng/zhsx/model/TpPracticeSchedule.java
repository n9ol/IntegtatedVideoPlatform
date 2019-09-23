package com.zzrenfeng.zhsx.model;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-09-20 17:21:34
 * @see com.zzrenfeng.zhsx.model.TpPracticeSchedule
 */

public class TpPracticeSchedule {

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 习题id
	 */
	private java.lang.String pid;
	/**
	 * 课表id
	 */
	private java.lang.String sid;
	/**
	 * 教室id
	 */
	private java.lang.String cid;
	/**
	 * 出题人
	 */
	private java.lang.String createId;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;

	// get and set

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getPid() {
		return pid;
	}

	public void setPid(java.lang.String pid) {
		this.pid = pid;
	}

	public java.lang.String getSid() {
		return sid;
	}

	public void setSid(java.lang.String sid) {
		this.sid = sid;
	}

	public java.lang.String getCid() {
		return cid;
	}

	public void setCid(java.lang.String cid) {
		this.cid = cid;
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

	public String toString() {
		return new StringBuffer().append("id[").append(getId()).append("],")
				.append("习题id[").append(getPid()).append("],").append("课表id[")
				.append(getSid()).append("],").append("教室id[").append(getCid())
				.append("],").append("出题人[").append(getCreateId()).append("],")
				.append("创建时间[").append(getCreateTime()).append("],")
				.toString();
	}

}
