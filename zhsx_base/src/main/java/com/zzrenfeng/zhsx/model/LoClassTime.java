package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-05-08 10:49:03
 * @see com.zzrenfeng.zhsx.model.LoClassTime
 */

public class LoClassTime {
		

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 学期时间id
	 */
	private java.lang.String termTimeId;
	/**
	 * 第几节
	 */
	private java.lang.Integer sectionofDay;
	/**
	 * 开始时间
	 */
	private java.util.Date starttime;
	/**
	 * 结束时间
	 */
	private java.util.Date endtime;
	//columns END 数据库字段结束
	
	
	//关联字段(此处对时间的处理比较丑陋)
	private java.lang.String starttimef; //开始时间
	private java.lang.String endtimef; //结束时间
	
	
	//concstructor

	public LoClassTime(){
	}

	public LoClassTime(
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
	
	public java.lang.String getStarttimef() {
		return starttimef;
	}

	public void setStarttimef(java.lang.String starttimef) {
		this.starttimef = starttimef;
	}

	public java.lang.String getEndtimef() {
		return endtimef;
	}

	public void setEndtimef(java.lang.String endtimef) {
		this.endtimef = endtimef;
	}

	public java.lang.String getId() {
		return this.id;
	}
	public void setTermTimeId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.termTimeId = value;
	}
	
	public java.lang.String getTermTimeId() {
		return this.termTimeId;
	}
	public void setSectionofDay(java.lang.Integer value) {
		this.sectionofDay = value;
	}
	
	public java.lang.Integer getSectionofDay() {
		return this.sectionofDay;
	}

	
	public void setStarttime(java.util.Date value) {
		this.starttime = value;
	}
	
	public java.util.Date getStarttime() {
		return this.starttime;
	}

	
	public void setEndtime(java.util.Date value) {
		this.endtime = value;
	}
	
	public java.util.Date getEndtime() {
		return this.endtime;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("主键[").append(getId()).append("],")
			.append("学期时间id[").append(getTermTimeId()).append("],")
			.append("第几节[").append(getSectionofDay()).append("],")
			.append("开始时间[").append(getStarttime()).append("],")
			.append("结束时间[").append(getEndtime()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LoClassTime == false) return false;
		if(this == obj) return true;
		LoClassTime other = (LoClassTime)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
