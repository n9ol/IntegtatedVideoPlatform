package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-08 15:16:31
 * @see com.zzrenfeng.zhsx.model.WebDeviceRepair
 */

public class WebDeviceRepair {
	//alias
	/*
	public static final String TABLE_ALIAS = "报修管理";
	public static final String ALIAS_REPAIR_ID = "id";
	public static final String ALIAS_REPAIR_UNIT = "报修单位(比如学校id)";
	public static final String ALIAS_REPAIR_UNIT_NAME = "维修单位名称(比如学校名称)";
	public static final String ALIAS_REPAIR_PERSON = "报修人";
	public static final String ALIAS_REPAIR_TEL = "报修电话";
	public static final String ALIAS_REPAIR_TIME = "报修时间";
	public static final String ALIAS_REPAIR_STATE = "报修状态(0 已经维修 1 未维修  2 维修中 )";
	public static final String ALIAS_REPAIR_DESCRIPTION = "故障描述（报修）";
	public static final String ALIAS_DEVICE_ID = "设备id(引入设备表)";
	public static final String ALIAS_DEVICE_TYPE = "设备类型";
	public static final String ALIAS_REPAIR_ADDR = "上门地址";
	public static final String ALIAS_REPAIR_BUY_TIME = "购机时间";
	public static final String ALIAS_REPAIR_SERVICE_TIME = "服务时间";
	public static final String ALIAS_REPAIR_RECORD_DESCRIPTION = "维修历史描述";
	public static final String ALIAS_MAINTENANCE_MAN = "维修人";
	public static final String ALIAS_MAINTENANCE_TEL = "维修电话";
	public static final String ALIAS_MAINTENANCE_SUGGESTION = "maintenance_suggestion";
	public static final String ALIAS_MAINTENANCE_TIME = "维修时间";
	public static final String ALIAS_EVALUATION_GRADE = "评价等级(非常满意、满意、一般、不满意、非常不满意)";
	public static final String ALIAS_REPAIR_ISVALID = "是否启用（0 启用 1 未启用）";
	public static final String ALIAS_CREATE_TIME = "创建时间";
	public static final String ALIAS_MODIFY_TIME = "更新时间";
	public static final String ALIAS_CREATE_ID = "创建人id";
	public static final String ALIAS_MODIFY_ID = "更新人id";
    */
	//date formats
	//public static final String FORMAT_REPAIR_TIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_REPAIR_BUY_TIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_REPAIR_SERVICE_TIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_MAINTENANCE_TIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATE_TIME = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_MODIFY_TIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	
	public static final String  DEVICE_ISVALID_STATE="0";	
	public static final String  DEVICE_NOVALID_STATE="1";	
	
	
	
	/**
	 * id
	 */
	private java.lang.String repair_id;
	/**
	 * 报修单位(比如学校id)
	 */
	private java.lang.String repair_unit;
	/**
	 * 维修单位名称(比如学校名称)
	 */
	private java.lang.String repair_unit_name;
	/**
	 * 报修人
	 */
	private java.lang.String repair_person;
	/**
	 * 报修电话
	 */
	private java.lang.String repair_tel;
	/**
	 * 报修时间
	 */
	private java.util.Date repair_time;
	/**
	 * 报修状态(0 已经维修 1 未维修  2 维修中 )
	 */
	private java.lang.String repair_state;
	/**
	 * 故障描述（报修）
	 */
	private java.lang.String repair_description;
	/**
	 * 设备id(引入设备表)
	 */
	private java.lang.String device_id;
	/**
	 * 设备类型
	 */
	private java.lang.String device_type;
	/**
	 * 上门地址
	 */
	private java.lang.String repair_addr;
	/**
	 * 购机时间
	 */
	private java.util.Date repair_buy_time;
	/**
	 * 服务时间
	 */
	private java.util.Date repair_service_time;
	/**
	 * 维修历史描述
	 */
	private java.lang.String repair_record_description;
	/**
	 * 维修人
	 */
	private java.lang.String maintenance_man;
	/**
	 * 维修电话
	 */
	private java.lang.String maintenance_tel;
	/**
	 * maintenance_suggestion
	 */
	private java.lang.String maintenance_suggestion;
	/**
	 * 维修时间
	 */
	private java.util.Date maintenance_time;
	/**
	 * 评价等级(非常满意、满意、一般、不满意、非常不满意)
	 */
	private java.lang.String evaluation_grade;
	/**
	 * 是否启用（0 启用 1 未启用）
	 */
	private java.lang.String repair_isvalid;
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

	public WebDeviceRepair(){
	}

	public WebDeviceRepair(java.lang.String repair_id){
		this.repair_id = repair_id;
	}

	//get and set
	public java.lang.String getRepair_id() {
		return repair_id;
	}

	public void setRepair_id(java.lang.String repair_id) {
		this.repair_id = repair_id;
	}
	public void setRepair_unit(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.repair_unit = value;
	}
	
	public java.lang.String getRepair_unit() {
		return this.repair_unit;
	}
	public void setRepair_unit_name(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.repair_unit_name = value;
	}
	
	public java.lang.String getRepair_unit_name() {
		return this.repair_unit_name;
	}
	public void setRepair_person(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.repair_person = value;
	}
	
	public java.lang.String getRepair_person() {
		return this.repair_person;
	}
	public void setRepair_tel(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.repair_tel = value;
	}
	
	public java.lang.String getRepair_tel() {
		return this.repair_tel;
	}
		/*
	public String getrepair_timeString() {
		return DateUtils.convertDate2String(FORMAT_REPAIR_TIME, getrepair_time());
	}
	public void setrepair_timeString(String value) throws ParseException{
		setrepair_time(DateUtils.convertString2Date(FORMAT_REPAIR_TIME,value));
	}*/
	
	public void setRepair_time(java.util.Date value) {
		this.repair_time = value;
	}
	
	public java.util.Date getRepair_time() {
		return this.repair_time;
	}
	public void setRepair_state(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.repair_state = value;
	}
	
	public java.lang.String getRepair_state() {
		return this.repair_state;
	}
	public void setRepair_description(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.repair_description = value;
	}
	
	public java.lang.String getRepair_description() {
		return this.repair_description;
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
	public void setDevice_type(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.device_type = value;
	}
	
	public java.lang.String getDevice_type() {
		return this.device_type;
	}
	public void setRepair_addr(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.repair_addr = value;
	}
	
	public java.lang.String getRepair_addr() {
		return this.repair_addr;
	}
		/*
	public String getrepair_buy_timeString() {
		return DateUtils.convertDate2String(FORMAT_REPAIR_BUY_TIME, getrepair_buy_time());
	}
	public void setrepair_buy_timeString(String value) throws ParseException{
		setrepair_buy_time(DateUtils.convertString2Date(FORMAT_REPAIR_BUY_TIME,value));
	}*/
	
	public void setRepair_buy_time(java.util.Date value) {
		this.repair_buy_time = value;
	}
	
	public java.util.Date getRepair_buy_time() {
		return this.repair_buy_time;
	}
		/*
	public String getrepair_service_timeString() {
		return DateUtils.convertDate2String(FORMAT_REPAIR_SERVICE_TIME, getrepair_service_time());
	}
	public void setrepair_service_timeString(String value) throws ParseException{
		setrepair_service_time(DateUtils.convertString2Date(FORMAT_REPAIR_SERVICE_TIME,value));
	}*/
	
	public void setRepair_service_time(java.util.Date value) {
		this.repair_service_time = value;
	}
	
	public java.util.Date getRepair_service_time() {
		return this.repair_service_time;
	}
	public void setRepair_record_description(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.repair_record_description = value;
	}
	
	public java.lang.String getRepair_record_description() {
		return this.repair_record_description;
	}
	public void setMaintenance_man(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.maintenance_man = value;
	}
	
	public java.lang.String getMaintenance_man() {
		return this.maintenance_man;
	}
	public void setMaintenance_tel(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.maintenance_tel = value;
	}
	
	public java.lang.String getMaintenance_tel() {
		return this.maintenance_tel;
	}
	public void setMaintenance_suggestion(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.maintenance_suggestion = value;
	}
	
	public java.lang.String getMaintenance_suggestion() {
		return this.maintenance_suggestion;
	}
		/*
	public String getmaintenance_timeString() {
		return DateUtils.convertDate2String(FORMAT_MAINTENANCE_TIME, getmaintenance_time());
	}
	public void setmaintenance_timeString(String value) throws ParseException{
		setmaintenance_time(DateUtils.convertString2Date(FORMAT_MAINTENANCE_TIME,value));
	}*/
	
	public void setMaintenance_time(java.util.Date value) {
		this.maintenance_time = value;
	}
	
	public java.util.Date getMaintenance_time() {
		return this.maintenance_time;
	}
	public void setEvaluation_grade(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.evaluation_grade = value;
	}
	
	public java.lang.String getEvaluation_grade() {
		return this.evaluation_grade;
	}
	public void setRepair_isvalid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.repair_isvalid = value;
	}
	
	public java.lang.String getRepair_isvalid() {
		return this.repair_isvalid;
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
			.append("id[").append(getRepair_id()).append("],")
			.append("报修单位(比如学校id)[").append(getRepair_unit()).append("],")
			.append("维修单位名称(比如学校名称)[").append(getRepair_unit_name()).append("],")
			.append("报修人[").append(getRepair_person()).append("],")
			.append("报修电话[").append(getRepair_tel()).append("],")
			.append("报修时间[").append(getRepair_time()).append("],")
			.append("报修状态(0 已经维修 1 未维修  2 维修中 )[").append(getRepair_state()).append("],")
			.append("故障描述（报修）[").append(getRepair_description()).append("],")
			.append("设备id(引入设备表)[").append(getDevice_id()).append("],")
			.append("设备类型[").append(getDevice_type()).append("],")
			.append("上门地址[").append(getRepair_addr()).append("],")
			.append("购机时间[").append(getRepair_buy_time()).append("],")
			.append("服务时间[").append(getRepair_service_time()).append("],")
			.append("维修历史描述[").append(getRepair_record_description()).append("],")
			.append("维修人[").append(getMaintenance_man()).append("],")
			.append("维修电话[").append(getMaintenance_tel()).append("],")
			.append("maintenance_suggestion[").append(getMaintenance_suggestion()).append("],")
			.append("维修时间[").append(getMaintenance_time()).append("],")
			.append("评价等级(非常满意、满意、一般、不满意、非常不满意)[").append(getEvaluation_grade()).append("],")
			.append("是否启用（0 启用 1 未启用）[").append(getRepair_isvalid()).append("],")
			.append("创建时间[").append(getCreate_time()).append("],")
			.append("更新时间[").append(getModify_time()).append("],")
			.append("创建人id[").append(getCreate_id()).append("],")
			.append("更新人id[").append(getModify_id()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRepair_id())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WebDeviceRepair == false) return false;
		if(this == obj) return true;
		WebDeviceRepair other = (WebDeviceRepair)obj;
		return new EqualsBuilder()
			.append(getRepair_id(),other.getRepair_id())
			.isEquals();
	}
}

	
