package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-27 17:47:36
 * @see com.zzrenfeng.zhsx.model.CourResource
 */

public class CourResource {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 原文件名(发布时可修改)
	 */
	private java.lang.String name;
	/**
	 * 文件下载路径
	 */
	private java.lang.String downloadPath;
	/**
	 * 文件pdf 格式 路径
	 */
	private java.lang.String pdfPath;
	/**
	 * 文件后缀
	 */
	private java.lang.String type;
	/**
	 * 文件大小
	 */
	private java.lang.String size;
	/**
	 * 是否发布(Y 已发布 N 未发布)
	 */
	private java.lang.String state;
	/**
	 * 上传人的id
	 */
	private java.lang.String uploadPersonId;
	/**
	 * 作者Id
	 */
	private java.lang.String authorId;
	/**
	 * 年级名称
	 */
	private java.lang.String gradeName;
	/**
	 * 科目名称
	 */
	private java.lang.String subjectsName;
	/**
	 * 对应课程的id
	 */
	private java.lang.String fileMd5;
	/**
	 * 上传时间
	 */
	private java.util.Date createTime;
	/**
	 * 版本
	 */
	private java.lang.String bak;
	/**
	 * 上下册
	 */
	private java.lang.String bak1;
	/**
	 * 审核状态
	 */
	private java.lang.String bak2;
	//columns END 数据库字段结束
	
	
	//关联字段
	/**
	 * 搜索字段
	 */
	private java.lang.String search;
	
	/**
	 * 排序方式
	 */
	private java.lang.String sortord;
	
	/**
	 * 上传人名称
	 */
	private java.lang.String uploadPersonName;
	
	
	/**
	 * 上传人所在学校
	 */
	private String uploadpersonschool;
	
	/**
	 * 下载量
	 */
	private int downloadNum;
	
	/**
	 * 收藏量
	 */
	private int collectionNum;
	
	/**
	 * 地区筛选字段
	 */
	private java.lang.String area;
	
	/**
	 * 当前登录用户id
	 */
	private java.lang.String currUserId;
	
	/**
	 * 当前用户是否收藏
	 */
	private int isCollection;
	
	
	
	
	//concstructor
	public CourResource(){
	}

	public CourResource(
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
	
	public String getUploadpersonschool() {
		return uploadpersonschool;
	}

	public void setUploadpersonschool(String uploadpersonschool) {
		this.uploadpersonschool = uploadpersonschool;
	}

	public int getCollectionNum() {
		return collectionNum;
	}

	public void setCollectionNum(int collectionNum) {
		this.collectionNum = collectionNum;
	}

	public int getIsCollection() {
		return isCollection;
	}

	public void setIsCollection(int isCollection) {
		this.isCollection = isCollection;
	}

	public java.lang.String getCurrUserId() {
		return currUserId;
	}

	public void setCurrUserId(java.lang.String currUserId) {
		this.currUserId = currUserId;
	}

	public java.lang.String getArea() {
		return area;
	}

	public void setArea(java.lang.String area) {
		this.area = area;
	}

	public int getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(int downloadNum) {
		this.downloadNum = downloadNum;
	}

	public java.lang.String getUploadPersonName() {
		return uploadPersonName;
	}

	public void setUploadPersonName(java.lang.String uploadPersonName) {
		this.uploadPersonName = uploadPersonName;
	}

	public java.lang.String getSortord() {
		return sortord;
	}

	public void setSortord(java.lang.String sortord) {
		this.sortord = sortord;
	}

	public java.lang.String getSearch() {
		return search;
	}

	public void setSearch(java.lang.String search) {
		this.search = search;
	}

	public java.lang.String getId() {
		return this.id;
	}
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	public void setDownloadPath(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.downloadPath = value;
	}
	
	public java.lang.String getDownloadPath() {
		return this.downloadPath;
	}
	public void setPdfPath(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pdfPath = value;
	}
	
	public java.lang.String getPdfPath() {
		return this.pdfPath;
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
	public void setSize(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.size = value;
	}
	
	public java.lang.String getSize() {
		return this.size;
	}
	public void setState(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.state = value;
	}
	
	public java.lang.String getState() {
		return this.state;
	}
	public void setUploadPersonId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.uploadPersonId = value;
	}
	
	public java.lang.String getUploadPersonId() {
		return this.uploadPersonId;
	}
	public void setAuthorId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.authorId = value;
	}
	
	public java.lang.String getAuthorId() {
		return this.authorId;
	}
	public void setGradeName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gradeName = value;
	}
	
	public java.lang.String getGradeName() {
		return this.gradeName;
	}
	public void setSubjectsName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.subjectsName = value;
	}
	
	public java.lang.String getSubjectsName() {
		return this.subjectsName;
	}
	
	public java.lang.String getFileMd5() {
		return fileMd5;
	}

	public void setFileMd5(java.lang.String fileMd5) {
		this.fileMd5 = fileMd5;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
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
	
	
	
	@Override
	public String toString() {
		return "CourResource [id=" + id + ", name=" + name + ", downloadPath=" + downloadPath + ", pdfPath=" + pdfPath
				+ ", type=" + type + ", size=" + size + ", state=" + state + ", uploadPersonId=" + uploadPersonId
				+ ", authorId=" + authorId + ", gradeName=" + gradeName + ", subjectsName=" + subjectsName
				+ ", fileMd5=" + fileMd5 + ", createTime=" + createTime + ", bak=" + bak + ", bak1=" + bak1 + ", bak2="
				+ bak2 + ", search=" + search + ", sortord=" + sortord + ", uploadPersonName=" + uploadPersonName
				+ ", downloadNum=" + downloadNum + ", collectionNum=" + collectionNum + ", area=" + area
				+ ", currUserId=" + currUserId + ", isCollection=" + isCollection + "]";
	}

	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof CourResource == false) return false;
		if(this == obj) return true;
		CourResource other = (CourResource)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
