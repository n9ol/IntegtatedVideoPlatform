package com.zzrenfeng.zhsx.model;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-29 09:56:25
 * @see com.zzrenfeng.zhsx.model.TestWebTest
 */

public class TestWebTest {
		

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 出题人id
	 */
	private java.lang.String memberId;
	/**
	 * 试卷标题
	 */
	private java.lang.String testTitle;
	/**
	 * 试卷类型
	 */
	private java.lang.String stuType;
	/** 
	 * 年级名称
	 */
	private java.lang.String gradeName;
	/**
	 * 学科名称
	 */
	private java.lang.String subiectName;
	/**
	 * 册 0：全册 1：上册 2：下册
	 */
	private java.lang.Integer volume;
	/**
	 * 版本
	 */
	private java.lang.String version;
	/**
	 * 搜索字段1
	 */
	private java.lang.String search1;
	/**
	 * 搜索字段2
	 */
	private java.lang.String search2;
	/**
	 * 总分
	 */
	private java.lang.String totalScore;
	/**
	 * 考试时间
	 */
	private java.lang.Integer times;
	/**
	 * 问题数量
	 */
	private java.lang.Integer questions;
	/**
	 * 公开 :Y:公开在考试中心,N:试卷存为草稿
	 */
	private java.lang.String publicType;
	/**
	 * 公开期限
	 */
	private java.lang.Integer validDays;
	/**
	 * 浏览量
	 */
	private java.lang.Integer views;
	/**
	 * 测试次数
	 */
	private java.lang.Integer tests;
	/**
	 * 是否有效
	 */
	private java.lang.Integer isDrop;
	/**
	 * 终止时间
	 */
	private java.util.Date lockTime;
	/**
	 * 添加时间
	 */
	private java.util.Date addTime;
	/**
	 * 试卷类型 T：老师出的普通试卷 S：随机练习试卷
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
	private java.lang.String search;
	private java.lang.String userName;
	private java.lang.String commTime;
	private java.lang.String versionName;
	private java.util.Date addtime;
	private List<SysHistory> history;//收藏类
	private String area;//地区搜索字段
	private String lock;//前台显示区分试卷是否过期条件：Y
	private String addTim;//查询 条件添加时间
	private String lockTim;//查询 条件截止时间
	private String status;//试卷状态查询字段
	private String schoolName; //出卷人所在学校
	
	
	
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddTim() {
		return addTim;
	}

	public void setAddTim(String addTim) {
		this.addTim = addTim;
	}

	public String getLockTim() {
		return lockTim;
	}

	public void setLockTim(String lockTim) {
		this.lockTim = lockTim;
	}

	public String getLock() {
		return lock;
	}

	public void setLock(String lock) {
		this.lock = lock;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public List<SysHistory> getHistory() {
		return history;
	}

	public void setHistory(List<SysHistory> history) {
		this.history = history;
	}

	public java.lang.String getSearch() {
		return search;
	}

	public void setSearch(java.lang.String search) {
		this.search = search;
	}

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public java.lang.String getCommTime() {
		return commTime;
	}

	public void setCommTime(java.lang.String commTime) {
		this.commTime = commTime;
	}

	public java.lang.String getVersionName() {
		return versionName;
	}

	public void setVersionName(java.lang.String versionName) {
		this.versionName = versionName;
	}

	public java.util.Date getAddtime() {
		return addtime;
	}

	public void setAddtime(java.util.Date addtime) {
		this.addtime = addtime;
	}

	public TestWebTest(){
	}

	public TestWebTest(
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
	public void setTestTitle(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.testTitle = value;
	}
	
	public java.lang.String getTestTitle() {
		return this.testTitle;
	}
	public void setStuType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.stuType = value;
	}
	
	public java.lang.String getStuType() {
		return this.stuType;
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
	public void setSubiectName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.subiectName = value;
	}
	
	public java.lang.String getSubiectName() {
		return this.subiectName;
	}
	public void setVolume(java.lang.Integer value) {
		this.volume = value;
	}
	
	public java.lang.Integer getVolume() {
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
	public void setSearch1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.search1 = value;
	}
	
	public java.lang.String getSearch1() {
		return this.search1;
	}
	public void setSearch2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.search2 = value;
	}
	
	public java.lang.String getSearch2() {
		return this.search2;
	}
	public void setTotalScore(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.totalScore = value;
	}
	
	public java.lang.String getTotalScore() {
		return this.totalScore;
	}
	public void setTimes(java.lang.Integer value) {
		this.times = value;
	}
	
	public java.lang.Integer getTimes() {
		return this.times;
	}
	public void setQuestions(java.lang.Integer value) {
		this.questions = value;
	}
	
	public java.lang.Integer getQuestions() {
		return this.questions;
	}
	public void setPublicType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.publicType = value;
	}
	
	public java.lang.String getPublicType() {
		return this.publicType;
	}
	public void setValidDays(java.lang.Integer value) {
		this.validDays = value;
	}
	
	public java.lang.Integer getValidDays() {
		return this.validDays;
	}
	public void setViews(java.lang.Integer value) {
		this.views = value;
	}
	
	public java.lang.Integer getViews() {
		return this.views;
	}
	public void setTests(java.lang.Integer value) {
		this.tests = value;
	}
	
	public java.lang.Integer getTests() {
		return this.tests;
	}
	public void setIsDrop(java.lang.Integer value) {
		this.isDrop = value;
	}
	
	public java.lang.Integer getIsDrop() {
		return this.isDrop;
	}

	
	public void setLockTime(java.util.Date value) {
		this.lockTime = value;
	}
	
	public java.util.Date getLockTime() {
		return this.lockTime;
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
			.append("id[").append(getId()).append("],")
			.append("出题人id[").append(getMemberId()).append("],")
			.append("试卷标题[").append(getTestTitle()).append("],")
			.append("试卷类型[").append(getStuType()).append("],")
			.append("年级名称[").append(getGradeName()).append("],")
			.append("学科名称[").append(getSubiectName()).append("],")
			.append("册 0：全册 1：上册 2：下册[").append(getVolume()).append("],")
			.append("版本[").append(getVersion()).append("],")
			.append("搜索字段1[").append(getSearch1()).append("],")
			.append("搜索字段2[").append(getSearch2()).append("],")
			.append("总分[").append(getTotalScore()).append("],")
			.append("考试时间[").append(getTimes()).append("],")
			.append("问题数量[").append(getQuestions()).append("],")
			.append("公开范围S:本校 C：本班级 Q:全部范围[").append(getPublicType()).append("],")
			.append("公开期限[").append(getValidDays()).append("],")
			.append("浏览量[").append(getViews()).append("],")
			.append("测试次数[").append(getTests()).append("],")
			.append("是否有效[").append(getIsDrop()).append("],")
			.append("终止时间[").append(getLockTime()).append("],")
			.append("添加时间[").append(getAddTime()).append("],")
			.append("试卷类型 T：老师出的普通试卷 S：随机练习试卷[").append(getBak1()).append("],")
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
		if(obj instanceof TestWebTest == false) return false;
		if(this == obj) return true;
		TestWebTest other = (TestWebTest)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
