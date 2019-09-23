package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.model.SysLog
 */

public class SysLog {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 操作人
	 */
	private java.lang.String operationname;
	/**
	 * 操作方法
	 */
	private java.lang.String operationmehtod;
	/**
	 * 是否成功
	 */
	private java.lang.String issuccess;
	/**
	 * 失败原因
	 */
	private java.lang.String reason;
	/**
	 * 操作日期
	 */
	private java.util.Date operationdate;
	/**
	 * 操作内容
	 */
	private java.lang.String content;
	//columns END 数据库字段结束
	
	
	//关联字段
	private java.lang.String search;   //搜索条件
	
	
	//concstructor

	public SysLog(){
	}

	public SysLog(
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
	
	public java.lang.String getSearch() {
		return search;
	}

	public void setSearch(java.lang.String search) {
		this.search = search;
	}

	public java.lang.String getId() {
		return this.id;
	}
	public void setOperationname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.operationname = value;
	}
	
	public java.lang.String getOperationname() {
		return this.operationname;
	}
	public void setOperationmehtod(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.operationmehtod = value;
	}
	
	public java.lang.String getOperationmehtod() {
		return this.operationmehtod;
	}
	public void setIssuccess(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.issuccess = value;
	}
	
	public java.lang.String getIssuccess() {
		return this.issuccess;
	}
	public void setReason(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.reason = value;
	}
	
	public java.lang.String getReason() {
		return this.reason;
	}

	
	public void setOperationdate(java.util.Date value) {
		this.operationdate = value;
	}
	
	public java.util.Date getOperationdate() {
		return this.operationdate;
	}
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("操作人[").append(getOperationname()).append("],")
			.append("操作方法[").append(getOperationmehtod()).append("],")
			.append("是否成功[").append(getIssuccess()).append("],")
			.append("失败原因[").append(getReason()).append("],")
			.append("操作日期[").append(getOperationdate()).append("],")
			.append("操作内容[").append(getContent()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SysLog == false) return false;
		if(this == obj) return true;
		SysLog other = (SysLog)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
