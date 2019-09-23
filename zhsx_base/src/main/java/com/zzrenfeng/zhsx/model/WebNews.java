package com.zzrenfeng.zhsx.model;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-06-17 10:36:53
 * @see com.zzrenfeng.zhsx.model.WebNews
 */

public class WebNews {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 新闻标题
	 */
	private java.lang.String title;
	/**
	 * 作者
	 */
	private java.lang.String author;
	/**
	 * 创建者id
	 */
	private java.lang.String createid;
	/**
	 * 前台显示类型S:视频    W：文字/图片
	 */
	private java.lang.String modelType;
	/**
	 * addtime
	 */
	private java.util.Date addtime;
	/**
	 * 浏览量
	 */
	private java.lang.Integer view;
	/**
	 * 来源
	 */
	private java.lang.String source;
	/**
	 * 状态 0：不显示 1：显示
	 */
	private java.lang.Integer status;
	/**
	 * 首页新闻显示顺序
	 */
	private java.lang.Integer sortOrder;
	/**
	 * bak1
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
	
	//concstructor

	private java.lang.String ISWORD="W";
	private java.lang.String ISPIC="P";
	private java.lang.String ISVIDEO="S";
	
	
	public java.lang.String getISWORD() {
		return ISWORD;
	}

	public void setISWORD(java.lang.String iSWORD) {
		ISWORD = iSWORD;
	}

	public java.lang.String getISPIC() {
		return ISPIC;
	}

	public void setISPIC(java.lang.String iSPIC) {
		ISPIC = iSPIC;
	}

	public java.lang.String getISVIDEO() {
		return ISVIDEO;
	}

	public void setISVIDEO(java.lang.String iSVIDEO) {
		ISVIDEO = iSVIDEO;
	}

	public WebNews(){
	}

	public WebNews(
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
	public void setTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.title = value;
	}
	
	public java.lang.String getTitle() {
		return this.title;
	}
	public void setAuthor(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.author = value;
	}
	
	public java.lang.String getAuthor() {
		return this.author;
	}
	public void setCreateid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.createid = value;
	}
	
	public java.lang.String getCreateid() {
		return this.createid;
	}
	public void setModelType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.modelType = value;
	}
	
	public java.lang.String getModelType() {
		return this.modelType;
	}

	
	public void setAddtime(java.util.Date value) {
		this.addtime = value;
	}
	
	public java.util.Date getAddtime() {
		return this.addtime;
	}
	public void setView(java.lang.Integer value) {
		this.view = value;
	}
	
	public java.lang.Integer getView() {
		return this.view;
	}
	public void setSource(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.source = value;
	}
	
	public java.lang.String getSource() {
		return this.source;
	}
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	
	public java.lang.Integer getStatus() {
		return this.status;
	}
	public void setSortOrder(java.lang.Integer value) {
		this.sortOrder = value;
	}
	
	public java.lang.Integer getSortOrder() {
		return this.sortOrder;
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
			.append("新闻标题[").append(getTitle()).append("],")
			.append("作者[").append(getAuthor()).append("],")
			.append("创建者id[").append(getCreateid()).append("],")
			.append("前台显示类型S:视频+文字 W：纯文字 P：文字加图片[").append(getModelType()).append("],")
			.append("addtime[").append(getAddtime()).append("],")
			.append("浏览量[").append(getView()).append("],")
			.append("来源[").append(getSource()).append("],")
			.append("状态 0：不显示 1：显示[").append(getStatus()).append("],")
			.append("首页新闻显示顺序[").append(getSortOrder()).append("],")
			.append("bak1[").append(getBak1()).append("],")
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
		if(obj instanceof WebNews == false) return false;
		if(this == obj) return true;
		WebNews other = (WebNews)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
