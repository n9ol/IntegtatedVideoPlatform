package com.zzrenfeng.zhsx.model;



/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-12-29 16:53:02
 * @see com.zzrenfeng.zhsx.model.LoPgGroupSchedule
 */

public class LoPgGroupSchedule {
		

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 评估对象id
	 */
	private java.lang.String loscheduleId;
	/**
	 * 评估小组id
	 */
	private java.lang.String pgGroupId;
	
	
	

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setLoscheduleId(java.lang.String value) {
		this.loscheduleId = value;
	}
	
	public java.lang.String getLoscheduleId() {
		return this.loscheduleId;
	}
	public void setPgGroupId(java.lang.String value) {
		this.pgGroupId = value;
	}
	
	public java.lang.String getPgGroupId() {
		return this.pgGroupId;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("主键[").append(getId()).append("],")
			.append("评估对象id[").append(getLoscheduleId()).append("],")
			.append("评估小组id[").append(getPgGroupId()).append("],")
			.toString();
	}
	

}

	
