package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-08 14:27:49
 * @see com.zzrenfeng.zhsx.model.WebDeviceRecord
 */

public class WebDeviceRecord {
	//alias
	/*
	public static final String TABLE_ALIAS = "设备使用时间记录表（流水表）";
	public static final String ALIAS_DR_ID = "id值自动递增";
	public static final String ALIAS_DEVICE_CODE = "设备id";
	public static final String ALIAS_DR_START_TIME = "设备开始使用时间";
	public static final String ALIAS_DR_END_TIME = "设备使用结束";
	public static final String ALIAS_DR_USING_LONG = "设备使用时间段（精确到豪秒）";
    */
	//date formats
	//public static final String FORMAT_DR_START_TIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_DR_END_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id值自动递增
	 */
	private java.lang.String dr_id;
	/**
	 * 设备id
	 */
	private java.lang.String device_code;
	/**
	 * 设备开始使用时间
	 */
	private java.util.Date dr_start_time;
	/**
	 * 设备使用结束
	 */
	private java.util.Date dr_end_time;
	/**
	 * 设备使用时间段（精确到豪秒）
	 */
	private java.lang.Long dr_using_long;
	//columns END 数据库字段结束
	
	//concstructor

	public WebDeviceRecord(){
	}

	public WebDeviceRecord(java.lang.String dr_id){
		this.dr_id = dr_id;
	}

	//get and set
	public void setDr_id(java.lang.String value) {
		this.dr_id = value;
	}
	
	public java.lang.String getDr_id() {
		return this.dr_id;
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
		/*
	public String getdr_start_timeString() {
		return DateUtils.convertDate2String(FORMAT_DR_START_TIME, getdr_start_time());
	}
	public void setdr_start_timeString(String value) throws ParseException{
		setdr_start_time(DateUtils.convertString2Date(FORMAT_DR_START_TIME,value));
	}*/
	
	public void setDr_start_time(java.util.Date value) {
		this.dr_start_time = value;
	}
	
	public java.util.Date getDr_start_time() {
		return this.dr_start_time;
	}
		/*
	public String getdr_end_timeString() {
		return DateUtils.convertDate2String(FORMAT_DR_END_TIME, getdr_end_time());
	}
	public void setdr_end_timeString(String value) throws ParseException{
		setdr_end_time(DateUtils.convertString2Date(FORMAT_DR_END_TIME,value));
	}*/
	
	public void setDr_end_time(java.util.Date value) {
		this.dr_end_time = value;
	}
	
	public java.util.Date getDr_end_time() {
		return this.dr_end_time;
	}
	public void setDr_using_long(java.lang.Long value) {
		this.dr_using_long = value;
	}
	
	public java.lang.Long getDr_using_long() {
		return this.dr_using_long;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id值自动递增[").append(getDr_id()).append("],")
			.append("设备id[").append(getDevice_code()).append("],")
			.append("设备开始使用时间[").append(getDr_start_time()).append("],")
			.append("设备使用结束[").append(getDr_end_time()).append("],")
			.append("设备使用时间段（精确到豪秒）[").append(getDr_using_long()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDr_id())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WebDeviceRecord == false) return false;
		if(this == obj) return true;
		WebDeviceRecord other = (WebDeviceRecord)obj;
		return new EqualsBuilder()
			.append(getDr_id(),other.getDr_id())
			.isEquals();
	}
}

	
