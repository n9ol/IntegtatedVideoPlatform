package com.zzrenfeng.zhsx.model;



/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-09-19 15:21:43
 * @see com.zzrenfeng.zhsx.model.TpPracticeExec
 */

public class TpPracticeExec {
		

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
	 * 问题内容
	 */
	private java.lang.String option;
	/**
	 * 正确答案
	 */
	private java.lang.String right;
	/**
	 * 类型：单选A，多选B，判断C,主观题D
	 */
	private java.lang.String type;
	/**
	 * 出题人
	 */
	private java.lang.String createid;
	/**
	 * 创建时间
	 */
	private java.util.Date createtime;
	
	
	

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setPid(java.lang.String value) {
		this.pid = value;
	}
	
	public java.lang.String getPid() {
		return this.pid;
	}
	public void setQuestion(java.lang.String value) {
		this.question = value;
	}
	
	public java.lang.String getQuestion() {
		return this.question;
	}
	public void setOption(java.lang.String value) {
		this.option = value;
	}
	
	public java.lang.String getOption() {
		return this.option;
	}
	public void setRight(java.lang.String value) {
		this.right = value;
	}
	
	public java.lang.String getRight() {
		return this.right;
	}
	public void setType(java.lang.String value) {
		this.type = value;
	}
	
	public java.lang.String getType() {
		return this.type;
	}
	public void setCreateid(java.lang.String value) {
		this.createid = value;
	}
	
	public java.lang.String getCreateid() {
		return this.createid;
	}

	
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
			.append("题库id[").append(getPid()).append("],")
			.append("题目[").append(getQuestion()).append("],")
			.append("问题内容[").append(getOption()).append("],")
			.append("正确答案[").append(getRight()).append("],")
			.append("类型：单选A，多选B，判断C,主观题D[").append(getType()).append("],")
			.append("出题人[").append(getCreateid()).append("],")
			.append("创建时间[").append(getCreatetime()).append("],")
			.toString();
	}
	

}

	
