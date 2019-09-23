package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-23 09:27:38
 * @see com.zzrenfeng.zhsx.model.WebPj
 */

public class WebPj {
		

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 用户id
	 */
	private java.lang.String userId;
	/**
	 * 评估对象id
	 */
	private java.lang.String pgId;
	/**
	 * 在线离线( ON 在线 ，OFF 离线 ）
	 */
	private java.lang.String onOff;
	/**
	 * 总评评估
	 */
	private java.lang.String allResult;
	/**
	 * 得分
	 */
	private java.math.BigDecimal score;
	/**
	 * 是否评价
	 */
	private Integer ispj;
	/**
	 * 评价时间
	 */
	private java.util.Date addTime;
	/**
	 * 课前评估得分
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
	
	
	//关联字段
	/**
	 * 类型（F 课前 I 课中  A 课后）
	 */
	private java.lang.String type;
	
	/**
	 * 评估人名称
	 */
	private java.lang.String userName;
	
	//concstructor

	public WebPj(){
	}

	public WebPj(
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
	
	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getId() {
		return this.id;
	}
	public void setUserId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	public void setPgId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pgId = value;
	}
	
	public java.lang.String getPgId() {
		return this.pgId;
	}
	public void setOnOff(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.onOff = value;
	}
	
	public java.lang.String getOnOff() {
		return this.onOff;
	}
	public void setAllResult(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.allResult = value;
	}
	
	public java.lang.String getAllResult() {
		return this.allResult;
	}
	public void setScore(java.math.BigDecimal value) {
		this.score = value;
	}
	
	public java.math.BigDecimal getScore() {
		return this.score;
	}
	public void setIspj(Integer value) {
		this.ispj = value;
	}
	
	public Integer getIspj() {
		return this.ispj;
	}

	
	public void setAddTime(java.util.Date value) {
		this.addTime = value;
	}
	
	public java.util.Date getAddTime() {
		return this.addTime;
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
			.append("主键[").append(getId()).append("],")
			.append("用户id[").append(getUserId()).append("],")
			.append("评估对象id[").append(getPgId()).append("],")
			.append("在线离线( ON 在线 ，OFF 离线 ）[").append(getOnOff()).append("],")
			.append("总评评估[").append(getAllResult()).append("],")
			.append("得分[").append(getScore()).append("],")
			.append("是否评价[").append(getIspj()).append("],")
			.append("评价时间[").append(getAddTime()).append("],")
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
		if(obj instanceof WebPj == false) return false;
		if(this == obj) return true;
		WebPj other = (WebPj)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
