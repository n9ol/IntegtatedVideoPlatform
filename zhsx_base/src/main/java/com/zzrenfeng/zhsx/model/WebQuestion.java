package com.zzrenfeng.zhsx.model;


import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-06-02 14:33:25
 * @see com.zzrenfeng.zhsx.model.WebQuestion
 */

public class WebQuestion {
		

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 用户id
	 */
	private java.lang.String fromId;
	/**
	 * 提问题目
	 */
	@NotNull(message="{notnull}")
	private java.lang.String title;
	/**
	 * 提问内容
	 */
	@NotNull(message="{notnull}")
	private java.lang.String content;
	/**
	 * 提问内容（有标签）
	 */
	@NotNull(message="{notnull}")
	private java.lang.String contentTag;
	/**
	 * 是否已解答 0：否 1：是
	 */
	private java.lang.Integer ifRespone;
	/**
	 * 是否显示 0：否 1：是
	 */
	private java.lang.Integer ifShow;
	/**
	 * 提问时间
	 */
	private java.util.Date sendTime;
	/**
	 * 备用1
	 */
	private java.lang.String bak1;
	/**
	 * 备用2
	 */
	private java.lang.String bak2;
	/**
	 * 备用3
	 */
	private java.lang.String bak3;
	//columns END 数据库字段结束
	
	//关联字段
	private java.lang.String nickName;//用户昵称
	private java.lang.String filePath;//用户头像路径
	private java.lang.String userType;//用户类型
	private java.lang.String schoolId;//用户学校id
	private java.lang.String schoolName;//用户学校名称
	private java.lang.String search;//搜索字段
	private java.lang.String type;//搜索字段、全部
	private java.lang.String gradeId;//搜索字段/年级查询
	private java.lang.String subjectId;//搜索字段./科目查询
	private java.lang.String subject;///类型所属科目
	private java.lang.String grade;///类型所属年级
	private java.lang.String ansName;//回答者名称
	private java.lang.String ifBest;//回答者名称
	private int count;//问题回答数量
	private String area;//地区查询
	private String dateTime;//多少分鐘之前
	
	
	
	
	
	
	//concstructor

	
	
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public java.lang.String getSubject() {
		return subject;
	}

	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public java.lang.String getIfBest() {
		return ifBest;
	}

	public void setIfBest(java.lang.String ifBest) {
		this.ifBest = ifBest;
	}

	public java.lang.String getAnsName() {
		return ansName;
	}

	public void setAnsName(java.lang.String ansName) {
		this.ansName = ansName;
	}

	public void setSubject(java.lang.String subject) {
		this.subject = subject;
	}

	public java.lang.String getGrade() {
		return grade;
	}

	public void setGrade(java.lang.String grade) {
		this.grade = grade;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getGradeId() {
		return gradeId;
	}

	public void setGradeId(java.lang.String gradeId) {
		this.gradeId = gradeId;
	}

	public java.lang.String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(java.lang.String subjectId) {
		this.subjectId = subjectId;
	}

	public java.lang.String getSearch() {
		return search;
	}

	public void setSearch(java.lang.String search) {
		this.search = search;
	}

	public java.lang.String getNickName() {
		return nickName;
	}

	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	public java.lang.String getFilePath() {
		return filePath;
	}

	public void setFilePath(java.lang.String filePath) {
		this.filePath = filePath;
	}

	public java.lang.String getUserType() {
		return userType;
	}

	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}

	public java.lang.String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(java.lang.String schoolId) {
		this.schoolId = schoolId;
	}

	public java.lang.String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(java.lang.String schoolName) {
		this.schoolName = schoolName;
	}

	public WebQuestion(){
	}

	public WebQuestion(
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
	public void setFromId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.fromId = value;
	}
	
	public java.lang.String getFromId() {
		return this.fromId;
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
	
	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public java.lang.String getContentTag() {
		return contentTag;
	}

	public void setContentTag(java.lang.String contentTag) {
		this.contentTag = contentTag;
	}

	public void setIfRespone(java.lang.Integer value) {
		this.ifRespone = value;
	}
	
	public java.lang.Integer getIfRespone() {
		return this.ifRespone;
	}
	public void setIfShow(java.lang.Integer value) {
		this.ifShow = value;
	}
	
	public java.lang.Integer getIfShow() {
		return this.ifShow;
	}

	
	public void setSendTime(java.util.Date value) {
		this.sendTime = value;
	}
	
	public java.util.Date getSendTime() {
		return this.sendTime;
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
			.append("用户id[").append(getFromId()).append("],")
			.append("提问题目[").append(getTitle()).append("],")
			.append("提问内容[").append(getContent()).append("],")
			.append("提问内容[").append(getContentTag()).append("],")
			.append("是否已解答 0：否 1：是[").append(getIfRespone()).append("],")
			.append("是否显示 0：否 1：是[").append(getIfShow()).append("],")
			.append("提问时间[").append(getSendTime()).append("],")
			.append("备用[").append(getBak1()).append("],")
			.append("备用2[").append(getBak2()).append("],")
			.append("备用3[").append(getBak3()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WebQuestion == false) return false;
		if(this == obj) return true;
		WebQuestion other = (WebQuestion)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
