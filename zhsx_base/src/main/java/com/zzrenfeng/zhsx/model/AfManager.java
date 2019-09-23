package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-06-22 17:02:47
 * @see com.zzrenfeng.zhsx.model.AfManager
 */

public class AfManager {
		

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 安防学校id
	 */
	private java.lang.String schoolid;
	/**
	 * 学校区域名称
	 */
	private java.lang.String schoolarea;
	/**
	 * 摄像头路径
	 */
	private java.lang.String videopath;
	/**
	 * 状态
	 */
	private java.lang.String status;
	/**
	 * 摄像头名称
	 */
	private java.lang.String camearname;
	/**
	 * 摄像头描述
	 */
	private java.lang.String cameardesc;
	/**
	 * 安装时间
	 */
	private java.util.Date starttime;
	/**
	 * 弃用时间
	 */
	private java.util.Date endtime;
	/**
	 * 封面路径
	 */
	private java.lang.String firepath;
	/**
	 * 备注1
	 */
	private java.lang.String bak1;
	/**
	 * 备注2
	 */
	private java.lang.String bak2;
	/**
	 * 备注3
	 */
	private java.lang.String bak3;
	//columns END 数据库字段结束
	private String search;
	//concstructor
	private String schoolName;
	private String  address;
	private String dhSearch;//导航查询
	private String isaf;
	private java.lang.String dhname;
	
	
	
	public java.lang.String getDhname() {
		return dhname;
	}

	public void setDhname(java.lang.String dhname) {
		this.dhname = dhname;
	}

	public String getIsaf() {
		return isaf;
	}

	public void setIsaf(String isaf) {
		this.isaf = isaf;
	}

	public String getDhSearch() {
		return dhSearch;
	}

	public void setDhSearch(String dhSearch) {
		this.dhSearch = dhSearch;
	}

	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(java.lang.String schoolName) {
		this.schoolName = schoolName;
	}

	public AfManager(){
	}

	public java.lang.String getSearch() {
		return search;
	}

	public void setSearch(java.lang.String search) {
		this.search = search;
	}

	public AfManager(
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
	public void setSchoolid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schoolid = value;
	}
	
	public java.lang.String getSchoolid() {
		return this.schoolid;
	}
	public void setSchoolarea(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schoolarea = value;
	}
	
	public java.lang.String getSchoolarea() {
		return this.schoolarea;
	}
	public void setVideopath(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.videopath = value;
	}
	
	public java.lang.String getVideopath() {
		return this.videopath;
	}
	public void setStatus(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.status = value;
	}
	
	public java.lang.String getStatus() {
		return this.status;
	}
	public void setCamearname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.camearname = value;
	}
	
	public java.lang.String getCamearname() {
		return this.camearname;
	}
	public void setCameardesc(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.cameardesc = value;
	}
	
	public java.lang.String getCameardesc() {
		return this.cameardesc;
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
	public void setFirepath(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.firepath = value;
	}
	
	public java.lang.String getFirepath() {
		return this.firepath;
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
			.append("主键[").append(getId()).append("],")
			.append("安防学校id[").append(getSchoolid()).append("],")
			.append("学校区域名称[").append(getSchoolarea()).append("],")
			.append("摄像头路径[").append(getVideopath()).append("],")
			.append("状态[").append(getStatus()).append("],")
			.append("摄像头名称[").append(getCamearname()).append("],")
			.append("摄像头描述[").append(getCameardesc()).append("],")
			.append("安装时间[").append(getStarttime()).append("],")
			.append("弃用时间[").append(getEndtime()).append("],")
			.append("封面路径[").append(getFirepath()).append("],")
			.append("备注1[").append(getBak1()).append("],")
			.append("备注2[").append(getBak2()).append("],")
			.append("备注3[").append(getBak3()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof AfManager == false) return false;
		if(this == obj) return true;
		AfManager other = (AfManager)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
