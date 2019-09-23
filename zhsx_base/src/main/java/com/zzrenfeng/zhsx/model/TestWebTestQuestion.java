package com.zzrenfeng.zhsx.model;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-13 14:53:43
 * @see com.zzrenfeng.zhsx.model.TestWebTestQuestion
 */

public class TestWebTestQuestion {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * testId
	 */
	private java.lang.String testId;
	/**
	 * questionId
	 */
	private java.lang.String questionId;
	/**
	 * 题分值
	 */
	private String scores;
	/**
	 * 题的顺序
	 */
	private Integer sortCode;
	/**
	 * 备1
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
	private Integer questionNo;//题号
	private String questionTitle;//问题标题
	private String questionType;//问题类型
	private List<TestWebQuestionAnswer> questionAnswer;//问题选项
	
	private String gradeName;//问题分类 年级
	private String subjectName;//问题分类 科目
	private String stuType;//基础教育
	private int volume;//侧目
	private String version;//版本
	private String memberId;//出题人Id
	private String totalScore;//题总分
	private Integer times;//考试时间
	private List<SysHistory> history;//收藏试题
	private TestWebStuTestQuestion stuTestQue;//学生问题得分详细
	private List<TestWebStuTestQuestionAnswer> stuAnswer;//学生选项情况；
	
	//columns END 数据库字段结束
	
	//concstructor
	
	public TestWebTestQuestion(){
	}

	

	

	public TestWebStuTestQuestion getStuTestQue() {
		return stuTestQue;
	}





	public void setStuTestQue(TestWebStuTestQuestion stuTestQue) {
		this.stuTestQue = stuTestQue;
	}





	public List<TestWebStuTestQuestionAnswer> getStuAnswer() {
		return stuAnswer;
	}



	public void setStuAnswer(List<TestWebStuTestQuestionAnswer> stuAnswer) {
		this.stuAnswer = stuAnswer;
	}



	public List<SysHistory> getHistory() {
		return history;
	}



	public void setHistory(List<SysHistory> history) {
		this.history = history;
	}



	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getStuType() {
		return stuType;
	}

	public void setStuType(String stuType) {
		this.stuType = stuType;
	}



	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public Integer getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}

	public List<TestWebQuestionAnswer> getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(List<TestWebQuestionAnswer> questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public TestWebTestQuestion(
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
	public void setTestId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.testId = value;
	}
	
	public java.lang.String getTestId() {
		return this.testId;
	}
	public void setQuestionId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.questionId = value;
	}
	
	public java.lang.String getQuestionId() {
		return this.questionId;
	}
	
	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public void setSortCode(Integer value) {
		this.sortCode = value;
	}
	
	public Integer getSortCode() {
		return this.sortCode;
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
			.append("testId[").append(getTestId()).append("],")
			.append("questionId[").append(getQuestionId()).append("],")
			.append("题分值[").append(getScores()).append("],")
			.append("题的顺序[").append(getSortCode()).append("],")
			.append("备1[").append(getBak1()).append("],")
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
		if(obj instanceof TestWebTestQuestion == false) return false;
		if(this == obj) return true;
		TestWebTestQuestion other = (TestWebTestQuestion)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
