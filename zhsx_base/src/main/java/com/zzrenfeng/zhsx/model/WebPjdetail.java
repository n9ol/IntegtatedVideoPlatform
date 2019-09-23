package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-23 09:27:23
 * @see com.zzrenfeng.zhsx.model.WebPjdetail
 */

public class WebPjdetail {
		

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 评估人id
	 */
	private java.lang.String userId;
	/**
	 * 评估对象id
	 */
	private java.lang.String pgId;
	/**
	 * 评价项id
	 */
	private java.lang.String pgPjInfoId;
	/**
	 * 在线离线( ON 在线 ，OFF 离线 ）
	 */
	private java.lang.String onOff;
	/**
	 * 内容
	 */
	private java.lang.String content;
	/**
	 * 分数
	 */
	private java.math.BigDecimal score;
	/**
	 * 权重
	 */
	private java.math.BigDecimal weight;
	/**
	 * 序号
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

	public WebPjdetail(){
	}

	public WebPjdetail(
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
	public void setPgPjInfoId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pgPjInfoId = value;
	}
	
	public java.lang.String getPgPjInfoId() {
		return this.pgPjInfoId;
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
	public void setContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.content = value;
	}
	
	public java.lang.String getContent() {
		return this.content;
	}
	public void setScore(java.math.BigDecimal value) {
		this.score = value;
	}
	
	public java.math.BigDecimal getScore() {
		return this.score;
	}
	public void setWeight(java.math.BigDecimal value) {
		this.weight = value;
	}
	
	public java.math.BigDecimal getWeight() {
		return this.weight;
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
			.append("评估人id[").append(getUserId()).append("],")
			.append("评估对象id[").append(getPgId()).append("],")
			.append("评价项id[").append(getPgPjInfoId()).append("],")
			.append("在线离线( ON 在线 ，OFF 离线 ）[").append(getOnOff()).append("],")
			.append("内容[").append(getContent()).append("],")
			.append("分数[").append(getScore()).append("],")
			.append("权重[").append(getWeight()).append("],")
			.append("序号[").append(getBak()).append("],")
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
		if(obj instanceof WebPjdetail == false) return false;
		if(this == obj) return true;
		WebPjdetail other = (WebPjdetail)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
