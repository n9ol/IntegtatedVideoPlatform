package com.zzrenfeng.zhsx.model;

import java.io.Serializable;

public class ShiroUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 用户账号
	 */
	private java.lang.String userCode;
	/**
	 * 密码
	 */
	private java.lang.String password;
	/**
	 * 昵称
	 */
	private java.lang.String nickName;
	/**
	 * 真实姓名
	 */
	private java.lang.String currName;
	/**
	 * 性别
	 */
	private java.lang.String gender;
	/**
	 * 年龄
	 */
	private java.lang.Integer age;
	/**
	 * 政治面貌
	 */
	private java.lang.String politicsStatus;
	/**
	 * 身高
	 */
	private java.lang.Integer stature;
	/**
	 * 家庭地址
	 */
	private java.lang.String hA;
	/**
	 * 成长等级
	 */
	private java.lang.String grade;
	/**
	 * 经验值
	 */
	private java.lang.Integer eXP;
	/**
	 * 学校id
	 */
	private java.lang.String schoolId;
	/**
	 * 用户类型
	 */
	private java.lang.String userType;
	/**
	 * 手机号
	 */
	private java.lang.String phone;
	/**
	 * 绑定手机,0-未绑定,1-已绑定
	 */
	private Integer phoneOk;
	/**
	 * QQ号
	 */
	private java.lang.String qQ;
	/**
	 * 邮箱
	 */
	private java.lang.String email;
	/**
	 * 绑定(激活)邮箱,0-未绑定,1-已绑定
	 */
	private Integer emailOk;
	/**
	 * 个性签名
	 */
	private java.lang.String memos;
	/**
	 * 头像路径
	 */
	private java.lang.String filePath;
	/**
	 * 头像格式
	 */
	private java.lang.String photo;
	/**
	 * 注册时间
	 */
	private java.util.Date createTime;
	/**
	 * 最近活跃时间
	 */
	private java.util.Date modiyTime;
	/**
	 * 是否禁用
	 */
	private java.lang.String bak;
	/**
	 * 是否为管理员
	 */
	private java.lang.String bak1;
	/**
	 * 管理员对应地区Id
	 */
	private java.lang.String bak2;

	/**
	 * 明文密码
	 */
	private String cleartextPassword;

	public String getCleartextPassword() {
		return cleartextPassword;
	}

	public void setCleartextPassword(String cleartextPassword) {
		this.cleartextPassword = cleartextPassword;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getUserCode() {
		return userCode;
	}

	public void setUserCode(java.lang.String userCode) {
		this.userCode = userCode;
	}

	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getNickName() {
		return nickName;
	}

	public void setNickName(java.lang.String nickName) {
		this.nickName = nickName;
	}

	public java.lang.String getCurrName() {
		return currName;
	}

	public void setCurrName(java.lang.String currName) {
		this.currName = currName;
	}

	public java.lang.String getGender() {
		return gender;
	}

	public void setGender(java.lang.String gender) {
		this.gender = gender;
	}

	public java.lang.Integer getAge() {
		return age;
	}

	public void setAge(java.lang.Integer age) {
		this.age = age;
	}

	public java.lang.String getPoliticsStatus() {
		return politicsStatus;
	}

	public void setPoliticsStatus(java.lang.String politicsStatus) {
		this.politicsStatus = politicsStatus;
	}

	public java.lang.Integer getStature() {
		return stature;
	}

	public void setStature(java.lang.Integer stature) {
		this.stature = stature;
	}

	public java.lang.String gethA() {
		return hA;
	}

	public void sethA(java.lang.String hA) {
		this.hA = hA;
	}

	public java.lang.String getGrade() {
		return grade;
	}

	public void setGrade(java.lang.String grade) {
		this.grade = grade;
	}

	public java.lang.Integer geteXP() {
		return eXP;
	}

	public void seteXP(java.lang.Integer eXP) {
		this.eXP = eXP;
	}

	public java.lang.String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(java.lang.String schoolId) {
		this.schoolId = schoolId;
	}

	public java.lang.String getUserType() {
		return userType;
	}

	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}

	public java.lang.String getPhone() {
		return phone;
	}

	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	public Integer getPhoneOk() {
		return phoneOk;
	}

	public void setPhoneOk(Integer phoneOk) {
		this.phoneOk = phoneOk;
	}

	public java.lang.String getqQ() {
		return qQ;
	}

	public void setqQ(java.lang.String qQ) {
		this.qQ = qQ;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public Integer getEmailOk() {
		return emailOk;
	}

	public void setEmailOk(Integer emailOk) {
		this.emailOk = emailOk;
	}

	public java.lang.String getMemos() {
		return memos;
	}

	public void setMemos(java.lang.String memos) {
		this.memos = memos;
	}

	public java.lang.String getFilePath() {
		return filePath;
	}

	public void setFilePath(java.lang.String filePath) {
		this.filePath = filePath;
	}

	public java.lang.String getPhoto() {
		return photo;
	}

	public void setPhoto(java.lang.String photo) {
		this.photo = photo;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getModiyTime() {
		return modiyTime;
	}

	public void setModiyTime(java.util.Date modiyTime) {
		this.modiyTime = modiyTime;
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

	public java.lang.String getBak2() {
		return bak2;
	}

	public void setBak2(java.lang.String bak2) {
		this.bak2 = bak2;
	}

	public ShiroUser() {
		super();
	}

	@Override
	public String toString() {
		return "ShiroUser [id=" + id + ", userCode=" + userCode + ", password=" + password + ", nickName=" + nickName
				+ ", currName=" + currName + ", gender=" + gender + ", age=" + age + ", politicsStatus="
				+ politicsStatus + ", stature=" + stature + ", hA=" + hA + ", grade=" + grade + ", eXP=" + eXP
				+ ", schoolId=" + schoolId + ", userType=" + userType + ", phone=" + phone + ", phoneOk=" + phoneOk
				+ ", qQ=" + qQ + ", email=" + email + ", emailOk=" + emailOk + ", memos=" + memos + ", filePath="
				+ filePath + ", photo=" + photo + ", createTime=" + createTime + ", modiyTime=" + modiyTime + ", bak="
				+ bak + ", bak1=" + bak1 + ", bak2=" + bak2 + "]";
	}

}
