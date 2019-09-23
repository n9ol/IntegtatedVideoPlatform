package com.zzrenfeng.zhsx.model;

import java.util.Date;



/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2019-05-21 18:17:48
 * @see com.zzrenfeng.zhsx.model.StudentBehaviorRecordData
 */

public class StudentBehaviorRecordData {
		

	/**
	 * 和student_behavior_record中的id是对应相等的
	 */
	private java.lang.String id;

	private java.lang.String sbrId;
	private Date createTime;
	/**
	 * 教室中学生对应的序列号
	 */
	private java.lang.Integer num;
	/**
	 * LB(回头看),LH(低头),OT(趴桌子),W(书写),SU(起立)，TH（侧头），R（阅读），HU（举手），L（听讲）
	 */
	private java.lang.String behavior;

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public java.lang.String getSbrId() {
		return sbrId;
	}

	public void setSbrId(java.lang.String sbrId) {
		this.sbrId = sbrId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setNum(java.lang.Integer value) {
		this.num = value;
	}
	
	public java.lang.Integer getNum() {
		return this.num;
	}
	public void setBehavior(java.lang.String value) {
		this.behavior = value;
	}
	
	public java.lang.String getBehavior() {
		return this.behavior;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("和student_behavior_record中的id是对应相等的[").append(getId()).append("],")
			.append("父表中的数据[").append(getSbrId()).append("],")
			.append("时间[").append(getCreateTime()).append("],")
			.append("教室中学生对应的序列号[").append(getNum()).append("],")
			.append("LB(回头看),LH(低头),OT(趴桌子),W(书写),SU(起立)，TH（侧头），R（阅读），HU（举手），L（听讲）[").append(getBehavior()).append("],")
			.toString();
	}
	

}

	
