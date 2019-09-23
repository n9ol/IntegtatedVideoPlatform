package com.zzrenfeng.zhsx.model.base;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-03-09 18:20:37
 * @see com.zzrenfeng.classbrand.model.BaseTeachingBuilding
 */

public class BaseTeachingBuilding {

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 建筑的名称
	 */
	private java.lang.String buildName;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 更新时间
	 */
	private java.util.Date modiyDate;

	// 扩展字段
	private String search; // 搜索字段

	// get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setBuildName(java.lang.String value) {
		this.buildName = value;
	}

	public java.lang.String getBuildName() {
		return this.buildName;
	}

	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}

	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	public void setModiyDate(java.util.Date value) {
		this.modiyDate = value;
	}

	public java.util.Date getModiyDate() {
		return this.modiyDate;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	@Override
	public String toString() {
		return "BaseTeachingBuilding [id=" + id + ", buildName=" + buildName + ", createDate=" + createDate
				+ ", modiyDate=" + modiyDate + ", search=" + search + "]";
	}

}
