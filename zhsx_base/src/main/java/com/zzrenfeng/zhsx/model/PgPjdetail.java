package com.zzrenfeng.zhsx.model;


import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:39
 * @see com.zzrenfeng.zhsx.model.PgPjdetail
 */

public class PgPjdetail {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 评价相id
	 */
	@NotNull(message="{notnull}")
	private java.lang.String pjinfoId;
	/**
	 * 内容
	 */
	@NotNull(message="{notnull}")
	private java.lang.String content;
	/**
	 * 分数
	 */
	private java.math.BigDecimal score;
	/**
	 * 权重
	 */
	@NotNull(message="{notnull}")
	private java.math.BigDecimal weight;
	/**
	 * 备用(序列)
	 */
	@NotNull(message="{notnull}")
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
	private java.lang.String pjInfoName;//评估项名称
	private java.lang.String type;//类型
	
	
	//concstructor

	public PgPjdetail(){
	}

	public PgPjdetail(
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
	
	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getPjInfoName() {
		return pjInfoName;
	}

	public void setPjInfoName(java.lang.String pjInfoName) {
		this.pjInfoName = pjInfoName;
	}

	public java.lang.String getId() {
		return this.id;
	}
	public void setPjinfoId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pjinfoId = value;
	}
	
	public java.lang.String getPjinfoId() {
		return this.pjinfoId;
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
			.append("id[").append(getId()).append("],")
			.append("评价相id[").append(getPjinfoId()).append("],")
			.append("内容[").append(getContent()).append("],")
			.append("分数[").append(getScore()).append("],")
			.append("权重[").append(getWeight()).append("],")
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
		if(obj instanceof PgPjdetail == false) return false;
		if(this == obj) return true;
		PgPjdetail other = (PgPjdetail)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
