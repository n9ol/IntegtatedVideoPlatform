package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-09-04 17:10:22
 * @see com.zzrenfeng.zhsx.model.WebClassDevice
 */

public class WebClassDevice {
	//alias
	/*
	public static final String TABLE_ALIAS = "WebClassDevice";
	public static final String ALIAS_ID = "主键id";
	public static final String ALIAS_CLASSID = "班级表";
	public static final String ALIAS_CLASSNAME = "classname";
	public static final String ALIAS_DEVICE_ID = "设备表中的id";
	public static final String ALIAS_DEVICE_CODE = "设备编码";
    */
	//date formats
	
	//columns START
	/**
	 * 主键id
	 */
	private java.lang.String id;
	/**
	 * 班级表
	 */
	private java.lang.String classid;
	/**
	 * classname
	 */
	private java.lang.String classname;
	/**
	 * 设备表中的id
	 */
	private java.lang.String device_id;
	/**
	 * 设备编码
	 */
	private java.lang.String device_code;
	//columns END 数据库字段结束
	
	//concstructor

	public WebClassDevice(){
	}

	public WebClassDevice(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setClassid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.classid = value;
	}
	
	public java.lang.String getClassid() {
		return this.classid;
	}
	public void setClassname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.classname = value;
	}
	
	public java.lang.String getClassname() {
		return this.classname;
	}
	public void setDevice_id(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.device_id = value;
	}
	
	public java.lang.String getDevice_id() {
		return this.device_id;
	}
	public void setDevice_code(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.device_code = value;
	}
	
	public java.lang.String getDevice_code() {
		return this.device_code;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("主键id[").append(getId()).append("],")
			.append("班级表[").append(getClassid()).append("],")
			.append("classname[").append(getClassname()).append("],")
			.append("设备表中的id[").append(getDevice_id()).append("],")
			.append("设备编码[").append(getDevice_code()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WebClassDevice == false) return false;
		if(this == obj) return true;
		WebClassDevice other = (WebClassDevice)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
