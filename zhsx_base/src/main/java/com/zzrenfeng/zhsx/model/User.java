package com.zzrenfeng.zhsx.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.model.User
 */
public class User {

	/**
	 * 标识符-领导
	 */
	public final static String userType_leader = "L";

	/**
	 * 标识符-教研员
	 */
	public final static String userType_research = "R";

	/**
	 * 标识符-教师
	 */
	public final static String userType_teachers = "T";

	/**
	 * 标识符-学生
	 */
	public final static String userType_students = "S";

	/**
	 * 标识符-运营商
	 */
	public final static String bak1_operator = "OA";
	/**
	 * 标识符-省级管理员
	 */
	public final static String bak1_province = "PA";
	/**
	 * 标识符-市级管理员
	 */
	public final static String bak1_city = "CA";
	/**
	 * 标识符-县/区管理员
	 */
	public final static String bak1_county = "AA";
	/**
	 * 标识符-校级管理员
	 */
	public final static String bak1_schoool = "SA";
	/**
	 * 标识符-不具备管理权限
	 */
	public final static String bak1_no = "NA";

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 用户账号
	 */
	@NotNull(message = "{notnull}")
	private java.lang.String userCode;
	/**
	 * 密码
	 */
	@NotNull(message = "{notnull}")
	private java.lang.String password;
	/**
	 * 昵称
	 */
	@NotNull(message = "{notnull}")
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
	 * 教龄
	 */
	private java.lang.Integer stature;
	/**
	 * 职称
	 */
	private java.lang.String hA;
	/**
	 * 成长等级(废除)
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
	 * 用户类型(领导 L,教研员 R,教师 T,学生 S)
	 */
	@Pattern(regexp = "[L|R|T|S]", message = "{tag}")
	private java.lang.String userType;
	/**
	 * 手机号
	 */
	private java.lang.String phone;
	/**
	 * 手机号是否验证通过 ,0-未通过,1-通过
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
	 * 邮箱验证是否通过,0-未通过,1-通过
	 */
	private Integer emailOk;
	/**
	 * 简介
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
	@Pattern(regexp = "[Y|N]", message = "{tag}")
	private java.lang.String bak;
	/**
	 * 是否为管理员（OA运营商,PA 省级管理员，CA 市级管理员，AA 区县管理员，SA 校级管理员 ，NA 不具备管理员）
	 */
	@Pattern(regexp = "[OA|PA|CA|AA|SA|NA]", message = "{tag}")
	private java.lang.String bak1;
	/**
	 * 用户对应区县Id
	 */
	private java.lang.String bak2;
	// columns END 数据库字段结束

	// 关联字段
	/**
	 * 搜索
	 */
	private java.lang.String search;
	/**
	 * 学校名称
	 */
	private java.lang.String schoolName;
	/**
	 * 用户权限
	 */
	private java.lang.String authority;

	/**
	 * 用户权限对应的学校id集合
	 */
	private List<String> schoolIds;
	/**
	 * 所在地区
	 */
	private java.lang.String areaName;
	/**
	 * 所在区县ID
	 */
	private java.lang.String countyId;
	/**
	 * 所在市Id
	 */
	private java.lang.String cityId;
	/**
	 * 所在省id
	 */
	private java.lang.String provinceId;

	/**
	 * 排序方式
	 */
	private String sortord;

	/**
	 * 用户等级
	 */
	private int userGrade;

	private String[] userIds;

	// concstructor
	public User() {
	}

	public User(java.lang.String id) {
		this.id = id;
	}

	// get and set
	public void setId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.id = value;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	public int getUserGrade() {
		return userGrade;
	}

	public void setUserGrade(int userGrade) {
		this.userGrade = userGrade;
	}

	public String getSortord() {
		return sortord;
	}

	public void setSortord(String sortord) {
		this.sortord = sortord;
	}

	public java.lang.String getCityId() {
		return cityId;
	}

	public void setCityId(java.lang.String cityId) {
		this.cityId = cityId;
	}

	public java.lang.String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(java.lang.String provinceId) {
		this.provinceId = provinceId;
	}

	public java.lang.String getCountyId() {
		return countyId;
	}

	public void setCountyId(java.lang.String countyId) {
		this.countyId = countyId;
	}

	public java.lang.String getAreaName() {
		return areaName;
	}

	public void setAreaName(java.lang.String areaName) {
		this.areaName = areaName;
	}

	public java.lang.String getAuthority() {
		return authority;
	}

	public void setAuthority(java.lang.String authority) {
		this.authority = authority;
	}

	public List<String> getSchoolIds() {
		return schoolIds;
	}

	public void setSchoolIds(List<String> schoolIds) {
		this.schoolIds = schoolIds;
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

	public java.lang.String getId() {
		return this.id;
	}

	public void setUserCode(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.userCode = value;
	}

	public java.lang.String getUserCode() {
		return this.userCode;
	}

	public void setPassword(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.password = value;
	}

	public java.lang.String getPassword() {
		return this.password;
	}

	public void setNickName(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.nickName = value;
	}

	public java.lang.String getNickName() {
		return this.nickName;
	}

	public void setCurrName(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.currName = value;
	}

	public java.lang.String getCurrName() {
		return this.currName;
	}

	public void setGender(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.gender = value;
	}

	public java.lang.String getGender() {
		return this.gender;
	}

	public void setAge(java.lang.Integer value) {
		this.age = value;
	}

	public java.lang.Integer getAge() {
		return this.age;
	}

	public void setPoliticsStatus(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.politicsStatus = value;
	}

	public java.lang.String getPoliticsStatus() {
		return this.politicsStatus;
	}

	public void setStature(java.lang.Integer value) {
		this.stature = value;
	}

	public java.lang.Integer getStature() {
		return this.stature;
	}

	public void setHA(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.hA = value;
	}

	public java.lang.String getHA() {
		return this.hA;
	}

	public void setGrade(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.grade = value;
	}

	public java.lang.String getGrade() {
		return this.grade;
	}

	public void setEXP(java.lang.Integer value) {
		this.eXP = value;
	}

	public java.lang.Integer getEXP() {
		return this.eXP;
	}

	public void setSchoolId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.schoolId = value;
	}

	public java.lang.String getSchoolId() {
		return this.schoolId;
	}

	public void setUserType(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.userType = value;
	}

	public java.lang.String getUserType() {
		return this.userType;
	}

	public void setPhone(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.phone = value;
	}

	public java.lang.String getPhone() {
		return this.phone;
	}

	public void setPhoneOk(Integer value) {
		this.phoneOk = value;
	}

	public Integer getPhoneOk() {
		return this.phoneOk;
	}

	public void setQQ(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.qQ = value;
	}

	public java.lang.String getQQ() {
		return this.qQ;
	}

	public void setEmail(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.email = value;
	}

	public java.lang.String getEmail() {
		return this.email;
	}

	public void setEmailOk(Integer value) {
		this.emailOk = value;
	}

	public Integer getEmailOk() {
		return this.emailOk;
	}

	public void setMemos(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.memos = value;
	}

	public java.lang.String getMemos() {
		return this.memos;
	}

	public void setFilePath(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.filePath = value;
	}

	public java.lang.String getFilePath() {
		return this.filePath;
	}

	public void setPhoto(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.photo = value;
	}

	public java.lang.String getPhoto() {
		return this.photo;
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

	public void setBak(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.bak = value;
	}

	public java.lang.String getBak() {
		return this.bak;
	}

	public void setBak1(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.bak1 = value;
	}

	public java.lang.String getBak1() {
		return this.bak1;
	}

	public void setBak2(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.bak2 = value;
	}

	public java.lang.String getBak2() {
		return this.bak2;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("主键[").append(getId()).append("],").append("用户账号[").append(getUserCode())
				.append("],").append("密码[").append(getPassword()).append("],").append("昵称[").append(getNickName())
				.append("],").append("真实姓名[").append(getCurrName()).append("],").append("性别[").append(getGender())
				.append("],").append("年龄[").append(getAge()).append("],").append("政治面貌[").append(getPoliticsStatus())
				.append("],").append("身高[").append(getStature()).append("],").append("家庭地址[").append(getHA())
				.append("],").append("成长等级[").append(getGrade()).append("],").append("经验值[").append(getEXP())
				.append("],").append("学校id[").append(getSchoolId()).append("],").append("用户类型[").append(getUserType())
				.append("],").append("手机号[").append(getPhone()).append("],").append("绑定手机,0-未绑定,1-已绑定[")
				.append(getPhoneOk()).append("],").append("QQ号[").append(getQQ()).append("],").append("邮箱[")
				.append(getEmail()).append("],").append("绑定(激活)邮箱,0-未绑定,1-已绑定[").append(getEmailOk()).append("],")
				.append("个性签名[").append(getMemos()).append("],").append("头像路径[").append(getFilePath()).append("],")
				.append("头像格式[").append(getPhoto()).append("],").append("注册时间[").append(getCreateTime()).append("],")
				.append("最近活跃时间[").append(getModiyTime()).append("],").append("备用字段[").append(getBak()).append("],")
				.append("备用字段[").append(getBak1()).append("],").append("备用字段[").append(getBak2()).append("],")
				.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User == false)
			return false;
		if (this == obj)
			return true;
		User other = (User) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}
}
