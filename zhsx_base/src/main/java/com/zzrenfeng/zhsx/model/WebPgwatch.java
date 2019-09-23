package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.model.WebPgwatch
 */

public class WebPgwatch {
		

	/**
	 * id
	 */
	private java.lang.Integer id;
	/**
	 * 评价key
	 */
	private java.lang.String pjCode;
	/**
	 * 师生互动时间
	 */
	private java.lang.Integer tsTime;
	/**
	 * 生生互动时间
	 */
	private java.lang.Integer ssTime;
	/**
	 * 缓解互动时间
	 */
	private java.lang.Integer hjTime;
	/**
	 * 发言互动时间
	 */
	private java.lang.Integer fyTime;
	/**
	 * 作业完成率
	 */
	private java.math.BigDecimal comrate;
	/**
	 * 作业正确率
	 */
	private java.math.BigDecimal accuracy;
	/**
	 * 提交时间
	 */
	private java.util.Date addtime;
	/**
	 * 备用
	 */
	private java.lang.String bak;
	/**
	 * 备用
	 */
	private java.lang.String bak1;
	/**
	 * 备用
	 */
	private java.lang.String bak2;
	//columns END 数据库字段结束
	
	//concstructor

	public WebPgwatch(){
	}

	public WebPgwatch(
		java.lang.Integer id
	){
		this.id = id;
	}

	//get and set
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setPjCode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pjCode = value;
	}
	
	public java.lang.String getPjCode() {
		return this.pjCode;
	}
	public void setTsTime(java.lang.Integer value) {
		this.tsTime = value;
	}
	
	public java.lang.Integer getTsTime() {
		return this.tsTime;
	}
	public void setSsTime(java.lang.Integer value) {
		this.ssTime = value;
	}
	
	public java.lang.Integer getSsTime() {
		return this.ssTime;
	}
	public void setHjTime(java.lang.Integer value) {
		this.hjTime = value;
	}
	
	public java.lang.Integer getHjTime() {
		return this.hjTime;
	}
	public void setFyTime(java.lang.Integer value) {
		this.fyTime = value;
	}
	
	public java.lang.Integer getFyTime() {
		return this.fyTime;
	}
	public void setComrate(java.math.BigDecimal value) {
		this.comrate = value;
	}
	
	public java.math.BigDecimal getComrate() {
		return this.comrate;
	}
	public void setAccuracy(java.math.BigDecimal value) {
		this.accuracy = value;
	}
	
	public java.math.BigDecimal getAccuracy() {
		return this.accuracy;
	}

	
	public void setAddtime(java.util.Date value) {
		this.addtime = value;
	}
	
	public java.util.Date getAddtime() {
		return this.addtime;
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
			.append("评价key[").append(getPjCode()).append("],")
			.append("师生互动时间[").append(getTsTime()).append("],")
			.append("生生互动时间[").append(getSsTime()).append("],")
			.append("缓解互动时间[").append(getHjTime()).append("],")
			.append("发言互动时间[").append(getFyTime()).append("],")
			.append("作业完成率[").append(getComrate()).append("],")
			.append("作业正确率[").append(getAccuracy()).append("],")
			.append("提交时间[").append(getAddtime()).append("],")
			.append("备用[").append(getBak()).append("],")
			.append("备用[").append(getBak1()).append("],")
			.append("备用[").append(getBak2()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WebPgwatch == false) return false;
		if(this == obj) return true;
		WebPgwatch other = (WebPgwatch)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
