package com.zzrenfeng.zhsx.model;



/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-07-23 16:17:16
 * @see com.zzrenfeng.zhsx.model.WebDeviceRecord
 */

public class WebDeviceRecord {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 设备id
	 */
	private java.lang.String deviceCode;
	/**
	 * 设备的mac地址
	 */
	private java.lang.String deviceMac;
	/**
	 * 设备开始使用时间
	 */
	private java.util.Date drStartTime;
	/**
	 * 设备使用结束
	 */
	private java.util.Date drEndTime;
	/**
	 * 设备使用时间段（精确到豪秒）
	 */
	private java.lang.Long drUsingLong;
	
	
	

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setDeviceCode(java.lang.String value) {
		this.deviceCode = value;
	}
	
	public java.lang.String getDeviceCode() {
		return this.deviceCode;
	}
	public void setDeviceMac(java.lang.String value) {
		this.deviceMac = value;
	}
	
	public java.lang.String getDeviceMac() {
		return this.deviceMac;
	}

	
	public void setDrStartTime(java.util.Date value) {
		this.drStartTime = value;
	}
	
	public java.util.Date getDrStartTime() {
		return this.drStartTime;
	}

	
	public void setDrEndTime(java.util.Date value) {
		this.drEndTime = value;
	}
	
	public java.util.Date getDrEndTime() {
		return this.drEndTime;
	}
	public void setDrUsingLong(java.lang.Long value) {
		this.drUsingLong = value;
	}
	
	public java.lang.Long getDrUsingLong() {
		return this.drUsingLong;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("设备id[").append(getDeviceCode()).append("],")
			.append("设备的mac地址[").append(getDeviceMac()).append("],")
			.append("设备开始使用时间[").append(getDrStartTime()).append("],")
			.append("设备使用结束[").append(getDrEndTime()).append("],")
			.append("设备使用时间段（精确到豪秒）[").append(getDrUsingLong()).append("],")
			.toString();
	}
	

}

	
