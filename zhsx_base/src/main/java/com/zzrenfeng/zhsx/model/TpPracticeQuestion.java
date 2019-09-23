package com.zzrenfeng.zhsx.model;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-09-20 10:56:04
 * @see com.zzrenfeng.zhsx.model.TpPracticeQuestion
 */

public class TpPracticeQuestion {

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 题库id
	 */
	private java.lang.String pid;
	/**
	 * 题目
	 */
	private java.lang.String question;
	/**
	 * gradeName
	 */
	private java.lang.String gradeName;
	/**
	 * subjectName
	 */
	private java.lang.String subjectName;
	/**
	 * 正确答案
	 */
	private java.lang.String rights;
	/**
	 * 类型：单选A，多选B，判断C,主观题D
	 */
	private java.lang.String type;
	/**
	 * 问题内容
	 */
	private java.lang.String options;
	/**
	 * 出题人
	 */
	private java.lang.String createId;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	
	
	private String bak;
	private String searchQuestion;

	// get and set

	public String getSearchQuestion() {
		return searchQuestion;
	}

	public void setSearchQuestion(String searchQuestion) {
		this.searchQuestion = searchQuestion;
	}

	public String getBak() {
		return bak;
	}

	public void setBak(String bak) {
		this.bak = bak;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getPid() {
		return pid;
	}

	public void setPid(java.lang.String pid) {
		this.pid = pid;
	}

	public java.lang.String getQuestion() {
		return question;
	}

	public void setQuestion(java.lang.String question) {
		this.question = question;
	}

	public java.lang.String getGradeName() {
		return gradeName;
	}

	public void setGradeName(java.lang.String gradeName) {
		this.gradeName = gradeName;
	}

	public java.lang.String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(java.lang.String subjectName) {
		this.subjectName = subjectName;
	}

	public java.lang.String getRights() {
		return rights;
	}

	public void setRights(java.lang.String rights) {
		this.rights = rights;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getOptions() {
		return options;
	}

	public void setOptions(java.lang.String options) {
		this.options = options;
	}

	public java.lang.String getCreateId() {
		return createId;
	}

	public void setCreateId(java.lang.String createId) {
		this.createId = createId;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("题库id[").append(getPid()).append("],")
			.append("题目[").append(getQuestion()).append("],")
			.append("gradeName[").append(getGradeName()).append("],")
			.append("subjectName[").append(getSubjectName()).append("],")
			.append("正确答案[").append(getRights()).append("],")
			.append("类型：单选A，多选B，判断C,主观题D[").append(getType()).append("],")
			.append("问题内容[").append(getOptions()).append("],")
			.append("出题人[").append(getCreateId()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.toString();
	}

}
