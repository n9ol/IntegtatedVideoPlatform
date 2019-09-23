package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-28 20:39:04
 * @see com.zzrenfeng.zhsx.model.LoPgCour
 */

public class LoPgCour {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 直播id
	 */
	private java.lang.String loScheduleId;
	/**
	 * 评估项ID
	 */
	private java.lang.String pjInfoId;
	/**
	 * 资源id
	 */
	private java.lang.String courId;
	/**
	 * 备用
	 */
	private java.lang.String bak;
	/**
	 * 备用
	 */
	private java.lang.String bak1;
	/**
	 * 备用字段
	 */
	private java.lang.String bak2;
	//columns END 数据库字段结束
	
	
	//关联字段
	/**
	 * 评估项名称
	 */
	private java.lang.String pjInfoName;
	/**
	 * 评估项权重
	 */
	private int pjInfoWeight;
	/**
	 * 资源名称
	 */
	private java.lang.String courName;
	
	/**
	 * 评估得分
	 */
	private double total;
	
	/**
	 * 用户ID
	 */
	private java.lang.String userId;
	
	//concstructor

	public LoPgCour(){
	}

	public LoPgCour(
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
	
	public java.lang.String getPjInfoName() {
		return pjInfoName;
	}

	public void setPjInfoName(java.lang.String pjInfoName) {
		this.pjInfoName = pjInfoName;
	}

	public int getPjInfoWeight() {
		return pjInfoWeight;
	}

	public void setPjInfoWeight(int pjInfoWeight) {
		this.pjInfoWeight = pjInfoWeight;
	}

	public java.lang.String getCourName() {
		return courName;
	}

	public void setCourName(java.lang.String courName) {
		this.courName = courName;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public java.lang.String getUserId() {
		return userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getId() {
		return this.id;
	}
	public void setLoScheduleId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.loScheduleId = value;
	}
	
	public java.lang.String getLoScheduleId() {
		return this.loScheduleId;
	}
	public void setPjInfoId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pjInfoId = value;
	}
	
	public java.lang.String getPjInfoId() {
		return this.pjInfoId;
	}
	public void setCourId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.courId = value;
	}
	
	public java.lang.String getCourId() {
		return this.courId;
	}
	public void setBak(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak = value;
	}
	
	public java.lang.String getBak() {
		return this.bak;
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
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("直播id[").append(getLoScheduleId()).append("],")
			.append("评估项ID[").append(getPjInfoId()).append("],")
			.append("资源id[").append(getCourId()).append("],")
			.append("备用[").append(getBak()).append("],")
			.append("备用[").append(getBak1()).append("],")
			.append("备用字段[").append(getBak2()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LoPgCour == false) return false;
		if(this == obj) return true;
		LoPgCour other = (LoPgCour)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
