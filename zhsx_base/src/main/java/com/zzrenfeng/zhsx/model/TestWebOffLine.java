package com.zzrenfeng.zhsx.model;


import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-09-09 15:22:25
 * @see com.zzrenfeng.zhsx.model.TestWebOffLine
 */

public class TestWebOffLine {
		

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 上传者id
	 */
	private java.lang.String memberId;
	
	
	private String title;
	/**
	 * 学校id
	 */
	private java.lang.String schoolId;
	/**
	 * 班级id
	 */
	private java.lang.String classId;
	/**
	 * 年级id
	 */
	private java.lang.String gradeId;
	/**
	 * 学科id
	 */
	private java.lang.String subjectId;
	/**
	 * 册目
	 */
	private java.lang.String volume;
	/**
	 * 版本
	 */
	private java.lang.String version;
	/**
	 * 试卷分数
	 */
	private java.lang.Integer score;
	/**
	 * 本次考试平均分
	 */
	private java.lang.Double averScore;
	/**
	 * 总人数
	 */
	private java.lang.Integer sum;
	/**
	 * 不及格总人数
	 */
	private java.lang.Integer failNum;
	/**
	 * 考试及格人数
	 */
	private java.lang.Integer passNum;
	/**
	 * 考试时间
	 */
	private java.util.Date testTime;
	/**
	 * 添加时间
	 */
	private java.util.Date addTime;
	/**
	 * bak1
	 */
	private java.lang.String bak1;
	/**
	 * bak2
	 */
	private java.lang.String bak2;
	/**
	 * bak3
	 */
	private java.lang.String bak3;
	//columns END 数据库字段结束
	private String search;//搜索 条件
	private String gradeName;//搜索 条件
	private String subjectName;//搜索 条件

	private String className;//搜索 条件
	private String percentage;//及格人数百分比
	private String area;//地区搜索
	private TestWebOffLine tTestWebOffLine;
	private List<TestWebOffLine> offList;
	private String searchTime;//本月数据
	private int sumAver;//平均数和
	private int passRatio;//合格率
	private String schoolName;//学校名称
	private String month;//月份
	//concstructor

	public String getSearchTime() {
		return searchTime;
	}




	public String getMonth() {
		return month;
	}




	public void setMonth(String month) {
		this.month = month;
	}




	public String getSchoolName() {
		return schoolName;
	}




	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}






	public int getPassRatio() {
		return passRatio;
	}




	public void setPassRatio(int passRatio) {
		this.passRatio = passRatio;
	}




	public int getSumAver() {
		return sumAver;
	}




	public void setSumAver(int sumAver) {
		this.sumAver = sumAver;
	}




	public void setSearchTime(String searchTime) {
		this.searchTime = searchTime;
	}




	public TestWebOffLine(){
	}

	
	

	public List<TestWebOffLine> getOffList() {
		return offList;
	}




	public void setOffList(List<TestWebOffLine> offList) {
		this.offList = offList;
	}




	public TestWebOffLine gettTestWebOffLine() {
		return tTestWebOffLine;
	}




	public void settTestWebOffLine(TestWebOffLine tTestWebOffLine) {
		this.tTestWebOffLine = tTestWebOffLine;
	}




	public String getArea() {
		return area;
	}




	public void setArea(String area) {
		this.area = area;
	}







	public String getPercentage() {
		return percentage;
	}




	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}




	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public TestWebOffLine(
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
	public void setMemberId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.memberId = value;
	}
	
	public java.lang.String getMemberId() {
		return this.memberId;
	}
	
	public void setSchoolId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.schoolId = value;
	}
	
	public java.lang.String getSchoolId() {
		return this.schoolId;
	}
	public void setClassId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.classId = value;
	}
	
	public java.lang.String getClassId() {
		return this.classId;
	}
	public void setGradeId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.gradeId = value;
	}
	
	public java.lang.String getGradeId() {
		return this.gradeId;
	}
	public void setSubjectId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.subjectId = value;
	}
	
	public java.lang.String getSubjectId() {
		return this.subjectId;
	}
	public void setVolume(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.volume = value;
	}
	
	public java.lang.String getVolume() {
		return this.volume;
	}
	public void setVersion(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.version = value;
	}
	
	public java.lang.String getVersion() {
		return this.version;
	}
	public void setScore(java.lang.Integer value) {
		this.score = value;
	}
	
	public java.lang.Integer getScore() {
		return this.score;
	}

	public java.lang.Double getAverScore() {
		return averScore;
	}




	public void setAverScore(java.lang.Double averScore) {
		this.averScore = averScore;
	}




	public void setSum(java.lang.Integer value) {
		this.sum = value;
	}
	
	public java.lang.Integer getSum() {
		return this.sum;
	}
	public void setFailNum(java.lang.Integer value) {
		this.failNum = value;
	}
	
	public java.lang.Integer getFailNum() {
		return this.failNum;
	}
	public void setPassNum(java.lang.Integer value) {
		this.passNum = value;
	}
	
	public java.lang.Integer getPassNum() {
		return this.passNum;
	}

	
	public void setTestTime(java.util.Date value) {
		this.testTime = value;
	}
	
	public java.util.Date getTestTime() {
		return this.testTime;
	}

	
	public void setAddTime(java.util.Date value) {
		this.addTime = value;
	}
	
	public java.util.Date getAddTime() {
		return this.addTime;
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
			.append("上传者id[").append(getMemberId()).append("],")
			.append("学校id[").append(getSchoolId()).append("],")
			.append("班级id[").append(getClassId()).append("],")
			.append("年级id[").append(getGradeId()).append("],")
			.append("学科id[").append(getSubjectId()).append("],")
			.append("册目[").append(getVolume()).append("],")
			.append("版本[").append(getVersion()).append("],")
			.append("试卷分数[").append(getScore()).append("],")
			.append("本次考试平均分[").append(getAverScore()).append("],")
			.append("总人数[").append(getSum()).append("],")
			.append("不及格总人数[").append(getFailNum()).append("],")
			.append("考试及格人数[").append(getPassNum()).append("],")
			.append("考试时间[").append(getTestTime()).append("],")
			.append("添加时间[").append(getAddTime()).append("],")
			.append("bak1[").append(getBak1()).append("],")
			.append("bak2[").append(getBak2()).append("],")
			.append("bak3[").append(getBak3()).append("],")
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TestWebOffLine == false) return false;
		if(this == obj) return true;
		TestWebOffLine other = (TestWebOffLine)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
