package com.zzrenfeng.zhsx.model;



/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-09-21 10:10:23
 * @see com.zzrenfeng.zhsx.model.TpPracticeResult
 */

public class TpPracticeResult {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 问题id
	 */
	private java.lang.String questionId;
	/**
	 * 用户答案
	 */
	private java.lang.String userAnswer;
	/**
	 * 正确答案
	 */
	private java.lang.String rightAnswer;
	/**
	 * 手写板id
	 */
	private java.lang.String boardId;
	/**
	 * 答题人姓名
	 */
	private java.lang.String userName;
	/**
	 * createTime
	 */
	private java.util.Date createTime;
	/**
	 * 备用字段
	 */
	private java.lang.String bak;
	/**
	 * 备用字段
	 */
	private java.lang.String bak1;
	
	/**
	 * 主教室（Z）分教室（F）
	 */
	private java.lang.String classType;
	
	//get and set
	 
private String searchClass;

private String searchUserName;
private String searchType;
private String searchQuestion;


	public String getSearchType() {
	return searchType;
}

public void setSearchType(String searchType) {
	this.searchType = searchType;
}

public String getSearchQuestion() {
	return searchQuestion;
}

public void setSearchQuestion(String searchQuestion) {
	this.searchQuestion = searchQuestion;
}

	public String getSearchUserName() {
	return searchUserName;
}

public void setSearchUserName(String searchUserName) {
	this.searchUserName = searchUserName;
}

	public String getSearchClass() {
	return searchClass;
}

public void setSearchClass(String searchClass) {
	this.searchClass = searchClass;
}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(java.lang.String questionId) {
		this.questionId = questionId;
	}

	public java.lang.String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(java.lang.String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public java.lang.String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(java.lang.String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public java.lang.String getBoardId() {
		return boardId;
	}

	public void setBoardId(java.lang.String boardId) {
		this.boardId = boardId;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getBak() {
		return bak;
	}

	public void setBak(java.lang.String bak) {
		this.bak = bak;
	}

	public java.lang.String getBak1() {
		return bak1;
	}

	public void setBak1(java.lang.String bak1) {
		this.bak1 = bak1;
	}
	
	public java.lang.String getClassType() {
		return classType;
	}

	public void setClassType(java.lang.String classType) {
		this.classType = classType;
	}

	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("问题id[").append(getQuestionId()).append("],")
			.append("用户答案[").append(getUserAnswer()).append("],")
			.append("正确答案[").append(getRightAnswer()).append("],")
			.append("手写板id[").append(getBoardId()).append("],")
			.append("答题人姓名[").append(getUserName()).append("],")
			.append("createTime[").append(getCreateTime()).append("],")
			.append("备用字段[").append(getBak()).append("],")
			.append("备用字段[").append(getBak1()).append("],")
			.append("主教室分教室[").append(getClassType()).append("],")
			.toString();
	}
}

	
