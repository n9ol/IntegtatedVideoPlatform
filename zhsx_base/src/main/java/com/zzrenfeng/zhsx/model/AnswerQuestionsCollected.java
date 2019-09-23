package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-24 17:29:18
 * @see com.zzrenfeng.zhsx.model.AnswerQuestionsCollected
 */

public class AnswerQuestionsCollected {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 教室id
	 */
	private java.lang.String classid;
	/**
	 * 科目名称
	 */
	private java.lang.String subjectname;
	/**
	 * 时长
	 */
	private java.lang.String timelength;
	/**
	 * 直播id
	 */
	private java.lang.String zid;
	/**
	 * createtime
	 */
	private java.util.Date createtime;
	/**
	 * createdate
	 */
	private java.util.Date createdate;
	/**
	 * 1代表(0-10分钟),2代表(10-20分钟),3代表(20-30分钟),4代表(30-40分钟)
	 */
	private java.lang.Integer isz;
	/**
	 * 0 代表学生, 
	 */
	private java.lang.Integer type;
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
	//columns END 数据库字段结束
	
	
	
	//关联字段
	/**
	 * 回答次数统计
	 */
	private java.lang.Integer num; 
	
	
	
	//concstructor

	public AnswerQuestionsCollected(){
	}

	public AnswerQuestionsCollected(
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
	
	public java.lang.Integer getNum() {
		return num;
	}

	public void setNum(java.lang.Integer num) {
		this.num = num;
	}

	public java.lang.String getId() {
		return this.id;
	}
	public void setClassid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.classid = value;
	}
	
	public java.lang.String getClassid() {
		return this.classid;
	}
	public void setSubjectname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.subjectname = value;
	}
	
	public java.lang.String getSubjectname() {
		return this.subjectname;
	}
	public void setTimelength(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.timelength = value;
	}
	
	public java.lang.String getTimelength() {
		return this.timelength;
	}
	public void setZid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zid = value;
	}
	
	public java.lang.String getZid() {
		return this.zid;
	}

	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}

	
	public void setCreatedate(java.util.Date value) {
		this.createdate = value;
	}
	
	public java.util.Date getCreatedate() {
		return this.createdate;
	}
	public void setIsz(java.lang.Integer value) {
		this.isz = value;
	}
	
	public java.lang.Integer getIsz() {
		return this.isz;
	}
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	public java.lang.Integer getType() {
		return this.type;
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
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("教室id[").append(getClassid()).append("],")
			.append("科目名称[").append(getSubjectname()).append("],")
			.append("时长[").append(getTimelength()).append("],")
			.append("直播id[").append(getZid()).append("],")
			.append("createtime[").append(getCreatetime()).append("],")
			.append("createdate[").append(getCreatedate()).append("],")
			.append("1代表(0-10分钟),2代表(10-20分钟),3代表(20-30分钟),4代表(30-40分钟)[").append(getIsz()).append("],")
			.append("0 代表学生, [").append(getType()).append("],")
			.append("备用[").append(getBak1()).append("],")
			.append("备用[").append(getBak2()).append("],")
			.append("备用[").append(getBak3()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AnswerQuestionsCollected == false) return false;
		if(this == obj) return true;
		AnswerQuestionsCollected other = (AnswerQuestionsCollected)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
