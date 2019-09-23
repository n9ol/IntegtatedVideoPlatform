package com.zzrenfeng.zhsx.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.zzrenfeng.zhsx.base.ValidateGroup1;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.model.SysClassroom
 */

public class SysClassroom {

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 教室编号
	 */
	@NotNull(message = "{notnull}", groups = { ValidateGroup1.class })
	private java.lang.String classCode;
	/**
	 * 教室名称
	 */
	@NotNull(message = "{notnull}", groups = { ValidateGroup1.class })
	private java.lang.String className;
	/**
	 * 学校ID
	 */
	@NotNull(message = "{notnull}")
	private java.lang.String schoolId;
	/**
	 * 直播服务器IP:Port
	 */
	@NotNull(message = "{notnull}", groups = { ValidateGroup1.class })
	private java.lang.String serviceIp;
	/**
	 * web端口
	 */
	@NotNull(message = "{notnull}", groups = { ValidateGroup1.class })
	private java.lang.String webPort;
	/**
	 * 会议房间
	 */
	@NotNull(message = "{notnull}", groups = { ValidateGroup1.class })
	private java.lang.String roomId;
	/**
	 * 教学编号
	 */
	@NotNull(message = "{notnull}", groups = { ValidateGroup1.class })
	private java.lang.String uid;
	/**
	 * 教室主机IP:Port
	 */
	private java.lang.String clientIp;
	/**
	 * 状态 Y代表正常 N代表作废
	 */
	private java.lang.String bak;
	/**
	 * 当前教室直播状态(1 直播[有人观看], 0未直播[无人观看])
	 */
	private java.lang.String bak1;
	/**
	 * 老师摄像头视频路径
	 */
	private java.lang.String bak2;

	/**
	 * 学生摄像头视频路径
	 */
	private String studentUrl;

	/**
	 * 教学楼id
	 */
	private String teachingBuildingId;

	/**
	 * 教学楼名称
	 */
	private String teachingBuildingName;

	// columns END 数据库字段结束

	// 关联字段
	private java.lang.String schoolName; // 学校名称
	private java.lang.String search; // 学校名称
	private java.lang.String authority; // 用户权限
	private List<String> schoolIds; // 用户权限对应的学校id集合
	// concstructor

	public SysClassroom() {
	}

	public SysClassroom(java.lang.String id) {
		this.id = id;
	}

	// get and set
	public void setId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.id = value;
	}

	public String getTeachingBuildingId() {
		return teachingBuildingId;
	}

	public void setTeachingBuildingId(String teachingBuildingId) {
		this.teachingBuildingId = teachingBuildingId;
	}

	public String getTeachingBuildingName() {
		return teachingBuildingName;
	}

	public void setTeachingBuildingName(String teachingBuildingName) {
		this.teachingBuildingName = teachingBuildingName;
	}

	public String getStudentUrl() {
		return studentUrl;
	}

	public void setStudentUrl(String studentUrl) {
		this.studentUrl = studentUrl;
	}

	public java.lang.String getClientIp() {
		return clientIp;
	}

	public void setClientIp(java.lang.String clientIp) {
		this.clientIp = clientIp;
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

	public java.lang.String getSearch() {
		return search;
	}

	public void setSearch(java.lang.String search) {
		this.search = search;
	}

	public java.lang.String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(java.lang.String schoolName) {
		this.schoolName = schoolName;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setClassCode(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.classCode = value;
	}

	public java.lang.String getClassCode() {
		return this.classCode;
	}

	public void setClassName(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.className = value;
	}

	public java.lang.String getClassName() {
		return this.className;
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

	public void setServiceIp(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.serviceIp = value;
	}

	public java.lang.String getServiceIp() {
		return this.serviceIp;
	}

	public void setWebPort(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.webPort = value;
	}

	public java.lang.String getWebPort() {
		return this.webPort;
	}

	public void setRoomId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.roomId = value;
	}

	public java.lang.String getRoomId() {
		return this.roomId;
	}

	public void setUid(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.uid = value;
	}

	public java.lang.String getUid() {
		return this.uid;
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
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SysClassroom == false)
			return false;
		if (this == obj)
			return true;
		SysClassroom other = (SysClassroom) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	public SysClassroom(String classCode, String serviceIp) {
		super();
		this.classCode = classCode;
		this.serviceIp = serviceIp;
	}

	@Override
	public String toString() {
		return "SysClassroom [id=" + id + ", classCode=" + classCode + ", className=" + className + ", schoolId="
				+ schoolId + ", serviceIp=" + serviceIp + ", webPort=" + webPort + ", roomId=" + roomId + ", uid=" + uid
				+ ", clientIp=" + clientIp + ", bak=" + bak + ", bak1=" + bak1 + ", bak2=" + bak2 + ", studentUrl="
				+ studentUrl + ", teachingBuildingId=" + teachingBuildingId + ", teachingBuildingName="
				+ teachingBuildingName + ", schoolName=" + schoolName + ", search=" + search + ", authority="
				+ authority + ", schoolIds=" + schoolIds + "]";
	}

}
