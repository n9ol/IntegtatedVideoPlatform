package com.zzrenfeng.zhsx.model;


import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.zzrenfeng.zhsx.base.ValidateGroup1;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-05-03 19:10:21
 * @see com.zzrenfeng.zhsx.model.SysSchool
 */

public class SysSchool {
		

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 名称
	 */
	@NotNull(message="{notnull}")
	private java.lang.String schoolName;
	/**
	 * 省级id
	 */
	@NotNull(message="{notnull}",groups={ValidateGroup1.class})
	private java.lang.String provinceId;
	/**
	 * 市Id
	 */
	@NotNull(message="{notnull}",groups={ValidateGroup1.class})
	private java.lang.String cityId;
	/**
	 * 区(县)id
	 */
	@NotNull(message="{notnull}",groups={ValidateGroup1.class})
	private java.lang.String countyId;
	/**
	 * 详细地址
	 */
	@NotNull(message="{notnull}")
	private java.lang.String address;
	/**
	 * 是否开通安防
	 */
	private java.lang.String isaf;
	/**
	 * 添加时间
	 */
	private java.util.Date createTime;
	/**
	 * 最后修改时间
	 */
	private java.util.Date modiyTime;
	/**
	 * 备用字段
	 */
	private java.lang.String bak;
	/**
	 * 备用字段
	 */
	private java.lang.String bak1;
	/**
	 * 备用字段
	 */
	private java.lang.String bak2;
	//columns END 数据库字段结束
	
	
	//关联字段
	private java.lang.String provinceName; //省名称
	private java.lang.String cityName; //市名称
	private java.lang.String countyName; //区县名称
	private java.lang.String search; //搜索字段
	private List<SysClassroom> classRoomList;
	private java.lang.String authority; //用户权限
	private List<String> ids; //用户权限对应的学校id集合
	
	
	//concstructor

	public SysSchool(){
	}

	public SysSchool(
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
	
	public java.lang.String getAuthority() {
		return authority;
	}

	public void setAuthority(java.lang.String authority) {
		this.authority = authority;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public List<SysClassroom> getClassRoomList() {
		return classRoomList;
	}

	public void setClassRoomList(List<SysClassroom> classRoomList) {
		this.classRoomList = classRoomList;
	}

	public java.lang.String getSearch() {
		return search;
	}

	public void setSearch(java.lang.String search) {
		this.search = search;
	}

	public java.lang.String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(java.lang.String provinceName) {
		this.provinceName = provinceName;
	}

	public java.lang.String getCityName() {
		return cityName;
	}

	public void setCityName(java.lang.String cityName) {
		this.cityName = cityName;
	}

	public java.lang.String getCountyName() {
		return countyName;
	}

	public void setCountyName(java.lang.String countyName) {
		this.countyName = countyName;
	}

	public java.lang.String getId() {
		return this.id;
	}
	public void setSchoolName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schoolName = value;
	}
	
	public java.lang.String getSchoolName() {
		return this.schoolName;
	}
	public void setProvinceId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.provinceId = value;
	}
	
	public java.lang.String getProvinceId() {
		return this.provinceId;
	}
	public void setCityId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.cityId = value;
	}
	
	public java.lang.String getCityId() {
		return this.cityId;
	}
	public void setCountyId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.countyId = value;
	}
	
	public java.lang.String getCountyId() {
		return this.countyId;
	}
	public void setAddress(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.address = value;
	}
	
	public java.lang.String getAddress() {
		return this.address;
	}
	public void setIsaf(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.isaf = value;
	}
	
	public java.lang.String getIsaf() {
		return this.isaf;
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
			.append("主键[").append(getId()).append("],")
			.append("名称[").append(getSchoolName()).append("],")
			.append("省级id[").append(getProvinceId()).append("],")
			.append("市Id[").append(getCityId()).append("],")
			.append("区(县)id[").append(getCountyId()).append("],")
			.append("详细地址[").append(getAddress()).append("],")
			.append("是否开通安防[").append(getIsaf()).append("],")
			.append("添加时间[").append(getCreateTime()).append("],")
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
		if(obj instanceof SysSchool == false) return false;
		if(this == obj) return true;
		SysSchool other = (SysSchool)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
