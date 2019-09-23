package com.zzrenfeng.zhsx.model;



/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-07-23 16:17:42
 * @see com.zzrenfeng.zhsx.model.WebClassDevice
 */

public class WebClassDevice {
		

	/**
	 * 主键id
	 */
	private java.lang.String id;
	/**
	 * class_id
	 */
	private java.lang.String classId;
	/**
	 * 设备表中的id
	 */
	private java.lang.String deviceId;
	
	
	

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setClassId(java.lang.String value) {
		this.classId = value;
	}
	
	public java.lang.String getClassId() {
		return this.classId;
	}
	public void setDeviceId(java.lang.String value) {
		this.deviceId = value;
	}
	
	public java.lang.String getDeviceId() {
		return this.deviceId;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("主键id[").append(getId()).append("],")
			.append("class_id[").append(getClassId()).append("],")
			.append("设备表中的id[").append(getDeviceId()).append("],")
			.toString();
	}
	

}

	
