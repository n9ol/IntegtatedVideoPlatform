package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-06-25 11:42:53
 * @see com.zzrenfeng.zhsx.model.TouchPad
 */

public class TouchPad {
	//alias
	/*
	public static final String TABLE_ALIAS = "TouchPad";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_CLASS_CODE = "班级编号";
	public static final String ALIAS_TIME_CODE = "时间编号";
	public static final String ALIAS_HAND_WRITTEN_BOARD_CODE = "手写板编号";
	public static final String ALIAS_DATA = "数据";
	public static final String ALIAS_CREATE_BY = "创建人";
	public static final String ALIAS_CREATE_DATE = "创建时间";
	public static final String ALIAS_UPDATE_BY = "更新人";
	public static final String ALIAS_UPDATE_DATE = "更新时间";
	public static final String ALIAS_REMARKS = "备注";
	public static final String ALIAS_DEL_FLAG = "删除标记";
	public static final String ALIAS_BAK1 = "备用";
	public static final String ALIAS_BAK2 = "备用";
	public static final String ALIAS_BAK3 = "备用";
	public static final String ALIAS_BAK4 = "备用";
	public static final String ALIAS_BAK5 = "备用";
    */
	//date formats
	//public static final String FORMAT_CREATE_DATE = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_UPDATE_DATE = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 班级编号
	 */
	private java.lang.String class_code;
	/**
	 * 时间编号
	 */
	private java.lang.String time_code;
	/**
	 * 手写板编号
	 */
	private java.lang.String hand_written_board_code;
	/**
	 * 数据
	 */
	private java.lang.String data;
	/**
	 * 创建人
	 */
	private java.lang.String create_by;
	/**
	 * 创建时间
	 */
	private java.util.Date create_date;
	/**
	 * 更新人
	 */
	private java.lang.String update_by;
	/**
	 * 更新时间
	 */
	private java.util.Date update_date;
	/**
	 * 备注
	 */
	private java.lang.String remarks;
	/**
	 * 删除标记
	 */
	private java.lang.String del_flag;
	/**
	 * 备用
	 */
	private java.lang.String bak1;
	/**
	 * 备用
	 */
	private java.lang.String bak2;
	/**
	 * 备用
	 */
	private java.lang.String bak3;
	/**
	 * 备用
	 */
	private java.lang.String bak4;
	/**
	 * 备用
	 */
	private java.lang.String bak5;
	//columns END 数据库字段结束
	
	//concstructor

	public TouchPad(){
	}

	public TouchPad(
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
	public void setClass_code(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.class_code = value;
	}
	
	public java.lang.String getClass_code() {
		return this.class_code;
	}
	public void setTime_code(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.time_code = value;
	}
	
	public java.lang.String getTime_code() {
		return this.time_code;
	}
	public void setHand_written_board_code(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.hand_written_board_code = value;
	}
	
	public java.lang.String getHand_written_board_code() {
		return this.hand_written_board_code;
	}
	public void setData(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.data = value;
	}
	
	public java.lang.String getData() {
		return this.data;
	}
	public void setCreate_by(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.create_by = value;
	}
	
	public java.lang.String getCreate_by() {
		return this.create_by;
	}
		/*
	public String getcreate_dateString() {
		return DateUtils.convertDate2String(FORMAT_CREATE_DATE, getcreate_date());
	}
	public void setcreate_dateString(String value) throws ParseException{
		setcreate_date(DateUtils.convertString2Date(FORMAT_CREATE_DATE,value));
	}*/
	
	public void setCreate_date(java.util.Date value) {
		this.create_date = value;
	}
	
	public java.util.Date getCreate_date() {
		return this.create_date;
	}
	public void setUpdate_by(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.update_by = value;
	}
	
	public java.lang.String getUpdate_by() {
		return this.update_by;
	}
		/*
	public String getupdate_dateString() {
		return DateUtils.convertDate2String(FORMAT_UPDATE_DATE, getupdate_date());
	}
	public void setupdate_dateString(String value) throws ParseException{
		setupdate_date(DateUtils.convertString2Date(FORMAT_UPDATE_DATE,value));
	}*/
	
	public void setUpdate_date(java.util.Date value) {
		this.update_date = value;
	}
	
	public java.util.Date getUpdate_date() {
		return this.update_date;
	}
	public void setRemarks(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remarks = value;
	}
	
	public java.lang.String getRemarks() {
		return this.remarks;
	}
	public void setDel_flag(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.del_flag = value;
	}
	
	public java.lang.String getDel_flag() {
		return this.del_flag;
	}
	public void setBak1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak1 = value;
	}
	
	public java.lang.String getBak1() {
		return this.bak1;
	}
	public void setBak2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak2 = value;
	}
	
	public java.lang.String getBak2() {
		return this.bak2;
	}
	public void setBak3(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak3 = value;
	}
	
	public java.lang.String getBak3() {
		return this.bak3;
	}
	public void setBak4(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak4 = value;
	}
	
	public java.lang.String getBak4() {
		return this.bak4;
	}
	public void setBak5(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak5 = value;
	}
	
	public java.lang.String getBak5() {
		return this.bak5;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("班级编号[").append(getClass_code()).append("],")
			.append("时间编号[").append(getTime_code()).append("],")
			.append("手写板编号[").append(getHand_written_board_code()).append("],")
			.append("数据[").append(getData()).append("],")
			.append("创建人[").append(getCreate_by()).append("],")
			.append("创建时间[").append(getCreate_date()).append("],")
			.append("更新人[").append(getUpdate_by()).append("],")
			.append("更新时间[").append(getUpdate_date()).append("],")
			.append("备注[").append(getRemarks()).append("],")
			.append("删除标记[").append(getDel_flag()).append("],")
			.append("备用[").append(getBak1()).append("],")
			.append("备用[").append(getBak2()).append("],")
			.append("备用[").append(getBak3()).append("],")
			.append("备用[").append(getBak4()).append("],")
			.append("备用[").append(getBak5()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TouchPad == false) return false;
		if(this == obj) return true;
		TouchPad other = (TouchPad)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
