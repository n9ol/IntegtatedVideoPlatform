package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-08 15:16:16
 * @see com.zzrenfeng.zhsx.model.WebDeviceManage
 */

public class WebDeviceManage {
	//alias
	/*
	public static final String TABLE_ALIAS = "设备管理表";
	public static final String ALIAS_DEVICE_ID = "id";
	public static final String ALIAS_DEVICE_CODE = "设备编号";
	public static final String ALIAS_DEVICE_TYPE = "设备型号";
	public static final String ALIAS_DEVICE_PROVINCE = "省份";
	public static final String ALIAS_DEVICE_CITY = "地市";
	public static final String ALIAS_DEVICE_AREA = "地区";
	public static final String ALIAS_SCHOOL_ID = "学校id";
	public static final String ALIAS_SCHOOL_NAME = "学校名字";
	public static final String ALIAS_DEVICE_STATE = "设备状态（0 启用  1 未启用）";
	public static final String ALIAS_DEVICE_CLIENT_VERSION_NUM = "设备客户端版本号";
	public static final String ALIAS_DEVICE_IP = "设备ip";
	public static final String ALIAS_DEVICE_MAC = "网卡的mac地址";
	public static final String ALIAS_DEVICE_ISVALID = "是否有效( 0 启用 1 未启用) ";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_MODIFY_TIME = "更新时间";
	public static final String ALIAS_CREATE_ID = "创建人id";
	public static final String ALIAS_MODIFY_ID = "更新人id";
    */
	//date formats
	//public static final String FORMAT_CREATE_TIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_MODIFY_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	
	public static final String  DEVICE_ONLINE_STATE="0";	
	public static final String  DEVICE_UNLINE_STATE="1";	
	
	
	
	
	/**
	 * id
	 */
	private java.lang.String device_id;
	/**
	 * 设备编号
	 */
	private java.lang.String device_code;
	/**
	 * 设备型号
	 */
	private java.lang.String device_type;
	/**
	 * 省份
	 */
	private java.lang.String device_province;
	/**
	 * 地市
	 */
	private java.lang.String device_city;
	/**
	 * 地区
	 */
	private java.lang.String device_area;
	/**
	 * 学校id
	 */
	private java.lang.String school_id;
	/**
	 * 学校名字
	 */
	private java.lang.String school_name;
	/**
	 * 设备状态（0 在线  1 不在线）
	 */
	private java.lang.String device_state;
	/**
	 * 设备客户端版本号
	 */
	private java.lang.String device_client_version_num;
	/**
	 * 设备ip
	 */
	private java.lang.String device_ip;
	/**
	 * 网卡的mac地址
	 */
	private java.lang.String device_mac;
	/**
	 * 是否有效( 0 启用 1 未启用) 
	 */
	private java.lang.String device_isvalid;
	/**
	 * 创建时间
	 */
	private java.util.Date create_time;
	/**
	 * 更新时间
	 */
	private java.util.Date modify_time;
	/**
	 * 创建人id
	 */
	private java.lang.String create_id;
	/**
	 * 更新人id
	 */
	private java.lang.String modify_id;
	//columns END 数据库字段结束
	
	//concstructor

	public WebDeviceManage(){
	}

	public WebDeviceManage(
		java.lang.String device_id,
		java.lang.String device_code
	){
		this.device_id = device_id;
		this.device_code = device_code;
	}

	//get and set
	public void setDevice_id(java.lang.String value) {
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
	public void setDevice_type(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.device_type = value;
	}
	
	public java.lang.String getDevice_type() {
		return this.device_type;
	}
	public void setDevice_province(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.device_province = value;
	}
	
	public java.lang.String getDevice_province() {
		return this.device_province;
	}
	public void setDevice_city(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.device_city = value;
	}
	
	public java.lang.String getDevice_city() {
		return this.device_city;
	}
	public void setDevice_area(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.device_area = value;
	}
	
	public java.lang.String getDevice_area() {
		return this.device_area;
	}
	public void setSchool_id(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.school_id = value;
	}
	
	public java.lang.String getSchool_id() {
		return this.school_id;
	}
	public void setSchool_name(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.school_name = value;
	}
	
	public java.lang.String getSchool_name() {
		return this.school_name;
	}
	public void setDevice_state(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.device_state = value;
	}
	
	public java.lang.String getDevice_state() {
		return this.device_state;
	}
	public void setDevice_client_version_num(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.device_client_version_num = value;
	}
	
	public java.lang.String getDevice_client_version_num() {
		return this.device_client_version_num;
	}
	public void setDevice_ip(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.device_ip = value;
	}
	
	public java.lang.String getDevice_ip() {
		return this.device_ip;
	}
	public void setDevice_mac(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.device_mac = value;
	}
	
	public java.lang.String getDevice_mac() {
		return this.device_mac;
	}
	public void setDevice_isvalid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.device_isvalid = value;
	}
	
	public java.lang.String getDevice_isvalid() {
		return this.device_isvalid;
	}
		/*
	public String getcreate_timeString() {
		return DateUtils.convertDate2String(FORMAT_CREATE_TIME, getcreate_time());
	}
	public void setcreate_timeString(String value) throws ParseException{
		setcreate_time(DateUtils.convertString2Date(FORMAT_CREATE_TIME,value));
	}*/
	
	public void setCreate_time(java.util.Date value) {
		this.create_time = value;
	}
	
	public java.util.Date getCreate_time() {
		return this.create_time;
	}
		/*
	public String getmodify_timeString() {
		return DateUtils.convertDate2String(FORMAT_MODIFY_TIME, getmodify_time());
	}
	public void setmodify_timeString(String value) throws ParseException{
		setmodify_time(DateUtils.convertString2Date(FORMAT_MODIFY_TIME,value));
	}*/
	
	public void setModify_time(java.util.Date value) {
		this.modify_time = value;
	}
	
	public java.util.Date getModify_time() {
		return this.modify_time;
	}
	public void setCreate_id(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.create_id = value;
	}
	
	public java.lang.String getCreate_id() {
		return this.create_id;
	}
	public void setModify_id(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.modify_id = value;
	}
	
	public java.lang.String getModify_id() {
		return this.modify_id;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getDevice_id()).append("],")
			.append("设备编号[").append(getDevice_code()).append("],")
			.append("设备型号[").append(getDevice_type()).append("],")
			.append("省份[").append(getDevice_province()).append("],")
			.append("地市[").append(getDevice_city()).append("],")
			.append("地区[").append(getDevice_area()).append("],")
			.append("学校id[").append(getSchool_id()).append("],")
			.append("学校名字[").append(getSchool_name()).append("],")
			.append("设备状态（0 启用  1 未启用）[").append(getDevice_state()).append("],")
			.append("设备客户端版本号[").append(getDevice_client_version_num()).append("],")
			.append("设备ip[").append(getDevice_ip()).append("],")
			.append("网卡的mac地址[").append(getDevice_mac()).append("],")
			.append("是否有效( 0 启用 1 未启用) [").append(getDevice_isvalid()).append("],")
			.append("创建时间[").append(getCreate_time()).append("],")
			.append("更新时间[").append(getModify_time()).append("],")
			.append("创建人id[").append(getCreate_id()).append("],")
			.append("更新人id[").append(getModify_id()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getDevice_id())
			.append(getDevice_code())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WebDeviceManage == false) return false;
		if(this == obj) return true;
		WebDeviceManage other = (WebDeviceManage)obj;
		return new EqualsBuilder()
			.append(getDevice_id(),other.getDevice_id())
			.append(getDevice_code(),other.getDevice_code())
			.isEquals();
	}
}

	
