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
 * @see com.zzrenfeng.zhsx.model.PgPjinfo
 */

public class PgPjinfo {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 标题
	 */
	@NotNull(message="{notnull}")
	private java.lang.String title;
	/**
	 * 权重
	 */
	@NotNull(message="{notnull}")
	private java.math.BigDecimal weight;
	/**
	 * 在线离线( ON 在线 ，OFF 离线 ）
	 */
	private java.lang.String onOff;
	/**
	 * 类型（F 课前 I 课中  A 课后）
	 */
	@NotNull(message="{notnull}")
	private java.lang.String type;
	/**
	 * 备用(序号)
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
	/**
	 * 直播id
	 */
	private String zId;
	/**
	 * 相关课件名称
	 */
	private String courName;
	
	
	//concstructor
	public PgPjinfo(){
	}

	public PgPjinfo(
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
	
	public String getzId() {
		return zId;
	}

	public void setzId(String zId) {
		this.zId = zId;
	}

	public String getCourName() {
		return courName;
	}

	public void setCourName(String courName) {
		this.courName = courName;
	}

	public java.lang.String getId() {
		return this.id;
	}
	public void setTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setWeight(java.math.BigDecimal value) {
		this.weight = value;
	}
	
	public java.math.BigDecimal getWeight() {
		return this.weight;
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
	public void setType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.type = value;
	}
	
	public java.lang.String getType() {
		return this.type;
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
			.append("标题[").append(getTitle()).append("],")
			.append("权重[").append(getWeight()).append("],")
			.append("在线离线( ON 在线 ，OFF 离线 ）[").append(getOnOff()).append("],")
			.append("类型（F 课前 I 课中  A 课后）[").append(getType()).append("],")
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
		if(obj instanceof PgPjinfo == false) return false;
		if(this == obj) return true;
		PgPjinfo other = (PgPjinfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
