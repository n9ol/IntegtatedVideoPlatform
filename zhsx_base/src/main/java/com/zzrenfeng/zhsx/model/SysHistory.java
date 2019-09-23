package com.zzrenfeng.zhsx.model;


import java.util.Date;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.model.SysHistory
 */

public class SysHistory {
	
	/**
	 * 行为标记-收藏
	 */
	public final static String PUBFLAG_C= "C";
	
	/**
	 * 行为标记- 获取经验值
	 */
	public final static String PUBFLAG_H= "H";
	
	/**
	 * 行为标记- 下载
	 */
	public final static String PUBFLAG_D= "D";
	
	/**
	 * 行为标记- 观看
	 */
	public final static String PUBFLAG_K= "K";
	
	
	
	/**
	 * 行为类型- 教师
	 */
	public final static String PUBTYPE_T= "T";
	
	/**
	 * 行为类型- 课件资源
	 */
	public final static String PUBTYPE_R= "R";
	
	/**
	 * 行为类型- 试卷
	 */
	public final static String PUBTYPE_E= "E";
	
	/**
	 * 行为类型- 试题
	 */
	public final static String PUBTYPE_Q= "Q";
	
	/**
	 * 行为类型- 点播视频
	 */
	public final static String PUBTYPE_V= "V";
	
	/**
	 * 行为类型- 直播
	 */
	public final static String PUBTYPE_N= "N";
	
	/**
	 * 行为类型- 登录
	 */
	public final static String PUBTYPE_D= "D";
	
	/**
	 * 行为类型 - 评估
	 */
	public final static String PUBTYPE_P= "P";
	
	/**
	 * 行为类型- 完善资料
	 */
	public final static String PUBTYPE_C= "C";
	
	/**
	 * 行为类型- 在线答疑
	 */
	public final static String PUBTYPE_A= "A";
	
	
	
	
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 操作人id
	 */
	private java.lang.String userId;
	/**
	 * 操作对象类型(T 名师,R 课件资源,E 试卷,Q 试题,N 直播,V 点播,D 登录 ,P 评估,C 完善资料,A 在线答疑)
	 */
	@Pattern(regexp = "[T|R|E|Q|N|V|D|P]",message="{tag}")
	private java.lang.String pubType;
	/**
	 * 操作行为类型( C 收藏,D 下载, H 经验值获取记录   ,K 视频观看 ) 
	 */
	@Pattern(regexp = "[C|D|H|K]",message="{tag}")
	private java.lang.String pubFlag;
	/**
	 * 操作对象id
	 */
	private java.lang.String pubId;
	/**
	 * 创建日期（yyyy-MM-ss）
	 */
	private java.util.Date createTime;
	/**
	 * 创建时间 (HH:MM:ss)
	 */
	private java.util.Date modiyTime;
	/**
	 * 教师经验值增量
	 */
	private java.lang.String bak;
	/**
	 * 操作说明
	 */
	private java.lang.String bak1;
	/**
	 * 备用字段
	 */
	private java.lang.String bak2;
	//columns END 数据库字段结束
	
	
	//关联字段
	private Date startTime;
	private Date endTime;
	
	
	
	
	//concstructor
	public SysHistory(){
	}

	public SysHistory(
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
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	public void setPubType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pubType = value;
	}
	
	public java.lang.String getPubType() {
		return this.pubType;
	}
	public void setPubFlag(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pubFlag = value;
	}
	
	public java.lang.String getPubFlag() {
		return this.pubFlag;
	}
	public void setPubId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pubId = value;
	}
	
	public java.lang.String getPubId() {
		return this.pubId;
	}

	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	
	public void setModiyTime(java.util.Date value) {
		this.modiyTime = value;
	}
	
	public java.util.Date getModiyTime() {
		return this.modiyTime;
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
			.append("操作人id[").append(getUserId()).append("],")
			.append("类型（T 名师）[").append(getPubType()).append("],")
			.append("标记( C 收藏、关注   H 历史记录) [").append(getPubFlag()).append("],")
			.append("操作对象id[").append(getPubId()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("最后修改时间[").append(getModiyTime()).append("],")
			.append("备用字段[").append(getBak()).append("],")
			.append("备用字段[").append(getBak1()).append("],")
			.append("备用字段[").append(getBak2()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof SysHistory == false) return false;
		if(this == obj) return true;
		SysHistory other = (SysHistory)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
