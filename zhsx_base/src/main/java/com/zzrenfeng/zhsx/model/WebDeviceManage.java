package com.zzrenfeng.zhsx.model;



/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-07-23 16:03:27
 * @see com.zzrenfeng.zhsx.model.WebDeviceManage
 */

public class WebDeviceManage {
		
	public static final String  DEVICE_ONLINE_STATE="0";	
	public static final String  DEVICE_UNLINE_STATE="1";
	public static final String  DEVICE_ISVALID_YES="0";	
	public static final String  DEVICE_ISVALID_NO="1";	

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 设备编号
	 */
	private java.lang.String deviceCode;
	/**
	 * 网卡的mac地址
	 */
	private java.lang.String deviceMac;
	/**
	 * 设备型号
	 */
	private java.lang.String deviceType;
	/**
	 * 省份
	 */
	private java.lang.String deviceProvince;
	/**
	 * 地市
	 */
	private java.lang.String deviceCity;
	/**
	 * 地区
	 */
	private java.lang.String deviceArea;
	/**
	 * 学校id
	 */
	private java.lang.String schoolId;
	/**
	 * 设备状态（0 在线  1 未在线）
	 */
	private java.lang.String deviceState;
	/**
	 * 设备客户端版本号
	 */
	private java.lang.String deviceClientVersionNum;
	/**
	 * 设备ip
	 */
	private java.lang.String deviceIp;
	/**
	 * 是否有效( 0 在用 1 已经过期,不可查询但统计时会用) 
	 */
	private java.lang.String isvalid;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 更新时间
	 */
	private java.util.Date modifyTime;
	/**
	 * 创建人id
	 */
	private java.lang.String createId;
	/**
	 * 更新人id
	 */
	private java.lang.String modifyId;
	
	private java.lang.String bak1;
	private java.lang.String bak2;
	private java.lang.String bak3;
	
	
	

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
	public void setDeviceType(java.lang.String value) {
		this.deviceType = value;
	}
	
	public java.lang.String getDeviceType() {
		return this.deviceType;
	}
	public void setDeviceProvince(java.lang.String value) {
		this.deviceProvince = value;
	}
	
	public java.lang.String getDeviceProvince() {
		return this.deviceProvince;
	}
	public void setDeviceCity(java.lang.String value) {
		this.deviceCity = value;
	}
	
	public java.lang.String getDeviceCity() {
		return this.deviceCity;
	}
	public void setDeviceArea(java.lang.String value) {
		this.deviceArea = value;
	}
	
	public java.lang.String getDeviceArea() {
		return this.deviceArea;
	}
	public void setSchoolId(java.lang.String value) {
		this.schoolId = value;
	}
	
	public java.lang.String getSchoolId() {
		return this.schoolId;
	}
	public void setDeviceState(java.lang.String value) {
		this.deviceState = value;
	}
	
	public java.lang.String getDeviceState() {
		return this.deviceState;
	}
	public void setDeviceClientVersionNum(java.lang.String value) {
		this.deviceClientVersionNum = value;
	}
	
	public java.lang.String getDeviceClientVersionNum() {
		return this.deviceClientVersionNum;
	}
	public void setDeviceIp(java.lang.String value) {
		this.deviceIp = value;
	}
	
	public java.lang.String getDeviceIp() {
		return this.deviceIp;
	}
	public void setIsvalid(java.lang.String value) {
		this.isvalid = value;
	}
	
	public java.lang.String getIsvalid() {
		return this.isvalid;
	}

	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	
	public void setModifyTime(java.util.Date value) {
		this.modifyTime = value;
	}
	
	public java.util.Date getModifyTime() {
		return this.modifyTime;
	}
	public void setCreateId(java.lang.String value) {
		this.createId = value;
	}
	
	public java.lang.String getCreateId() {
		return this.createId;
	}
	public void setModifyId(java.lang.String value) {
		this.modifyId = value;
	}
	
	public java.lang.String getModifyId() {
		return this.modifyId;
	}
	
	public java.lang.String getBak1() {
		return bak1;
	}

	public void setBak1(java.lang.String bak1) {
		this.bak1 = bak1;
	}

	public java.lang.String getBak2() {
		return bak2;
	}

	public void setBak2(java.lang.String bak2) {
		this.bak2 = bak2;
	}

	public java.lang.String getBak3() {
		return bak3;
	}

	public void setBak3(java.lang.String bak3) {
		this.bak3 = bak3;
	}

	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("设备编号[").append(getDeviceCode()).append("],")
			.append("网卡的mac地址[").append(getDeviceMac()).append("],")
			.append("设备型号[").append(getDeviceType()).append("],")
			.append("省份[").append(getDeviceProvince()).append("],")
			.append("地市[").append(getDeviceCity()).append("],")
			.append("地区[").append(getDeviceArea()).append("],")
			.append("学校id[").append(getSchoolId()).append("],")
			.append("设备状态（0 启用  1 未启用）[").append(getDeviceState()).append("],")
			.append("设备客户端版本号[").append(getDeviceClientVersionNum()).append("],")
			.append("设备ip[").append(getDeviceIp()).append("],")
			.append("isvalid[").append(getIsvalid()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("更新时间[").append(getModifyTime()).append("],")
			.append("创建人id[").append(getCreateId()).append("],")
			.append("更新人id[").append(getModifyId()).append("],")
			.toString();
	}
	

}

	
