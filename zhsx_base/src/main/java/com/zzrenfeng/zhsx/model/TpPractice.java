package com.zzrenfeng.zhsx.model;



/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-09-18 10:30:41
 * @see com.zzrenfeng.zhsx.model.TpPractice
 */

public class TpPractice {
		
	
	
	
	
	private String searchTitle;

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 标题
	 */
	private java.lang.String title;
	/**
	 * 年级名
	 */
	private java.lang.String gradeName;
	/**
	 * 学科
	 */
	private java.lang.String subjectName;
	/**
	 * remarks
	 */
	private java.lang.String remarks;
	/**
	 * 出题人
	 */
	private java.lang.String createId;
	/**
	 * 修改人
	 */
	private java.lang.String modiyId;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 是否显示 是Y，否N
	 */
	private java.lang.String isShown;
	
	
	

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
	}

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setGradeName(java.lang.String value) {
		this.gradeName = value;
	}
	
	public java.lang.String getGradeName() {
		return this.gradeName;
	}
	public void setSubjectName(java.lang.String value) {
		this.subjectName = value;
	}
	
	public java.lang.String getSubjectName() {
		return this.subjectName;
	}
	public void setRemarks(java.lang.String value) {
		this.remarks = value;
	}
	
	public java.lang.String getRemarks() {
		return this.remarks;
	}
	public void setCreateId(java.lang.String value) {
		this.createId = value;
	}
	
	public java.lang.String getCreateId() {
		return this.createId;
	}
	public void setModiyId(java.lang.String value) {
		this.modiyId = value;
	}
	
	public java.lang.String getModiyId() {
		return this.modiyId;
	}

	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setIsShown(java.lang.String value) {
		this.isShown = value;
	}
	
	public java.lang.String getIsShown() {
		return this.isShown;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("标题[").append(getTitle()).append("],")
			.append("年级名[").append(getGradeName()).append("],")
			.append("学科[").append(getSubjectName()).append("],")
			.append("remarks[").append(getRemarks()).append("],")
			.append("出题人[").append(getCreateId()).append("],")
			.append("修改人[").append(getModiyId()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("是否显示 是Y，否N[").append(getIsShown()).append("],")
			.toString();
	}
	

}

	
