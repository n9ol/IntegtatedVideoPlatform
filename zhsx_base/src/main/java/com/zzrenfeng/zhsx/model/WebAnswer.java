package com.zzrenfeng.zhsx.model;


import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-06-05 10:15:08
 * @see com.zzrenfeng.zhsx.model.WebAnswer
 */

public class WebAnswer {
		

	/**
	 * 答案id
	 */
	private java.lang.String id;
	/**
	 * 问题id
	 */
	private java.lang.String qid;
	/**
	 * 回答者id
	 */
	private java.lang.String answerId;
	/**
	 * 回答内容
	 */
	@NotNull(message="{notnull}")
	private java.lang.String answerContentTag;
	/**
	 * answerContent
	 */
	@NotNull(message="{notnoll}")
	private java.lang.String answerContent;
	/**
	 * 回答时间
	 */
	private java.util.Date time;
	/**
	 * 是否显示 0：否 1： 是
	 */
	private java.lang.Integer ifShow;
	/**
	 * 是否为最佳答案 0 ：否 1 ： 是 
	 */
	private java.lang.Integer ifBest;
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
	private java.lang.String nickName;//(回答者)用户昵称
	private java.lang.String filePath;//用户头像路径
	private java.lang.String userType;//用户类型
	private java.lang.String schoolId;//用户学校id
	private java.lang.String schoolName;//用户学校名称
	private java.lang.String search;//搜索字段
	private java.lang.String quesName;//(提问者昵称)
	private java.lang.String title;//(答案所属问题题目)
	private java.lang.String type;//搜索字段、全部
	private java.lang.String gradeId;//搜索字段/年级查询
	private java.lang.String subjectId;//搜索字段./科目查询
	private java.lang.String subject;///类型所属科目
	private java.lang.String grade;///类型所属年级
	private int count;///某一问题解答数
	private String area;//地区查询
	private String dateTime;//多少分鐘之前
	private String isBest;//多少分鐘之前
	
	//concstructor


	public WebAnswer(){
	}

	public String getIsBest() {
		return isBest;
	}

	public void setIsBest(String isBest) {
		this.isBest = isBest;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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

	public java.lang.String getSubject() {
		return subject;
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

	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getQuesName() {
		return quesName;
	}

	public void setQuesName(java.lang.String quesName) {
		this.quesName = quesName;
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

	public java.lang.String getSearch() {
		return search;
	}

	public void setSearch(java.lang.String search) {
		this.search = search;
	}

	public WebAnswer(
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
	
	public java.lang.String getQid() {
		return qid;
	}

	public void setQid(java.lang.String qid) {
		this.qid = qid;
	}

	public void setAnswerId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.answerId = value;
	}
	
	public java.lang.String getAnswerId() {
		return this.answerId;
	}
	public void setAnswerContentTag(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.answerContentTag = value;
	}
	
	public java.lang.String getAnswerContentTag() {
		return this.answerContentTag;
	}
	public void setAnswerContent(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.answerContent = value;
	}
	
	public java.lang.String getAnswerContent() {
		return this.answerContent;
	}

	
	public void setTime(java.util.Date value) {
		this.time = value;
	}
	
	public java.util.Date getTime() {
		return this.time;
	}
	public void setIfShow(java.lang.Integer value) {
		this.ifShow = value;
	}
	
	public java.lang.Integer getIfShow() {
		return this.ifShow;
	}
	public void setIfBest(java.lang.Integer value) {
		this.ifBest = value;
	}
	
	public java.lang.Integer getIfBest() {
		return this.ifBest;
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
			.append("答案id[").append(getId()).append("],")
			.append("问题id[").append(getQid()).append("],")
			.append("回答者id[").append(getAnswerId()).append("],")
			.append("回答内容[").append(getAnswerContentTag()).append("],")
			.append("answerContent[").append(getAnswerContent()).append("],")
			.append("回答时间[").append(getTime()).append("],")
			.append("是否显示 0：否 1： 是[").append(getIfShow()).append("],")
			.append("是否为最佳答案 0 ：否 1 ： 是 [").append(getIfBest()).append("],")
			.append("备用1[").append(getBak1()).append("],")
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
		if(obj instanceof WebAnswer == false) return false;
		if(this == obj) return true;
		WebAnswer other = (WebAnswer)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
