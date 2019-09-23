package com.zzrenfeng.zhsx.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.model.WebComments
 */

public class WebComments {
	
	/**
	 * 标识符-离线视频
	 */
	public final static String contextType_video = "V";
	
	/**
	 * 标识符-在线直播
	 */
	public final static String contextType_liveOnline = "L";
	
	/**
	 * 标识符-教师
	 */
	public final static String contextType_teachers = "T";
	
	/**
	 * 标识符-课件资源
	 */
	public final static String contextType_courRes = "C";

	
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 评论人的id
	 */
	private java.lang.String userId;
	/**
	 * 评论类型
	 */
	@Pattern(regexp = "[V|T|L|C]",message="{tag}")
	private java.lang.String contextType;
	/**
	 * 评论对象id
	 */
	@NotNull(message="{notnull}")
	private java.lang.String contextId;
	/**
	 * 评论内容
	 */
	@NotNull(message="{notnull}")
	private java.lang.String context;
	/**
	 * 评论内容(带样式标签)
	 */
	@NotNull(message="{notnull}")
	private java.lang.String contextMo;
	/**
	 * 是否显示
	 */
	private java.lang.String isShown;
	/**
	 * 点赞数量
	 */
	private java.lang.Integer thumbsUp;
	/**
	 * 点差的数量
	 */
	private java.lang.Integer thumbsDown;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 最后修改时间
	 */
	private java.util.Date modiyTime;
	//columns END 数据库字段结束
	
	//关联字段
	private java.lang.String search; //搜索字段
	private java.lang.String userName; //评论人的名称
	private java.lang.String userPic; //评论人头像路径
	
	
	
	//concstructor

	public WebComments(){
	}

	public WebComments(
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
	
	public java.lang.String getUserPic() {
		return userPic;
	}

	public void setUserPic(java.lang.String userPic) {
		this.userPic = userPic;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
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
	public void setUserId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	public void setContextType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.contextType = value;
	}
	
	public java.lang.String getContextType() {
		return this.contextType;
	}
	public void setContextId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.contextId = value;
	}
	
	public java.lang.String getContextId() {
		return this.contextId;
	}
	public void setContext(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.context = value;
	}
	
	public java.lang.String getContext() {
		return this.context;
	}
	public void setContextMo(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.contextMo = value;
	}
	
	public java.lang.String getContextMo() {
		return this.contextMo;
	}
	public void setIsShown(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.isShown = value;
	}
	
	public java.lang.String getIsShown() {
		return this.isShown;
	}
	public void setThumbsUp(java.lang.Integer value) {
		this.thumbsUp = value;
	}
	
	public java.lang.Integer getThumbsUp() {
		return this.thumbsUp;
	}
	public void setThumbsDown(java.lang.Integer value) {
		this.thumbsDown = value;
	}
	
	public java.lang.Integer getThumbsDown() {
		return this.thumbsDown;
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
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("评论人的id[").append(getUserId()).append("],")
			.append("评论类型[").append(getContextType()).append("],")
			.append("评论对象id[").append(getContextId()).append("],")
			.append("评论内容[").append(getContext()).append("],")
			.append("评论内容(带样式标签)[").append(getContextMo()).append("],")
			.append("是否显示[").append(getIsShown()).append("],")
			.append("点赞数量[").append(getThumbsUp()).append("],")
			.append("点差的数量[").append(getThumbsDown()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("最后修改时间[").append(getModiyTime()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WebComments == false) return false;
		if(this == obj) return true;
		WebComments other = (WebComments)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
