package com.zzrenfeng.zhsx.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-08-08 11:23:59
 * @see com.zzrenfeng.zhsx.model.OffLineVideoResources
 */

public class OffLineVideoResources {

	/**
	 * 视频类型 (P 离线评估，B 课堂回放 , S 精品微课 ,H 优质课程）
	 */
	public final static String TYPE_P = "P";
	public final static String TYPE_B = "B";
	public final static String TYPE_S = "S";
	public final static String TYPE_H = "H";

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 上传人
	 */
	private java.lang.String userId;
	/**
	 * 标题
	 */
	private java.lang.String title;
	/**
	 * 所属学校
	 */
	private java.lang.String schoolId;
	/**
	 * 年级名称
	 */
	private java.lang.String gradeName;
	/**
	 * 科目名称
	 */
	private java.lang.String subjectName;
	/**
	 * 主讲教师
	 */
	private java.lang.String teacherId;
	/**
	 * 是否允许下载 - (由原文件名 弃用 修改 Y 允许,N 不允许 )
	 */
	private java.lang.String uploadName;
	/**
	 * 视频路径
	 */
	private java.lang.String videoPath;
	/**
	 * 视频类型(P 离线评估，B 课堂回放 , S 精品微课 ,H 优质课程）
	 */
	private java.lang.String type;
	/**
	 * 视频封面路径
	 */
	private java.lang.String picPath;
	/**
	 * 转码状态 U=已上传,C=正在转码,O=已转码,D=删除
	 */
	private java.lang.String transcodingState;
	/**
	 * 转码进度
	 */
	private java.lang.Integer progress;
	/**
	 * 视频时长
	 */
	private java.lang.String timeLength;
	/**
	 * 转码后视频大小
	 */
	private java.lang.String size;
	/**
	 * 发布状态(Y 发布,N 未发布）
	 */
	private java.lang.String releaseState;
	/**
	 * 审核状态（Y 审核通过,N 审核未通过）
	 */
	private java.lang.String isShow;
	/**
	 * 浏览量
	 */
	private java.lang.Integer pageView;
	/**
	 * 上传时间
	 */
	private java.util.Date createTime;
	/**
	 * 驳回理由
	 */
	private java.lang.String bak;
	/**
	 * 文件的MD5值
	 */
	private java.lang.String bak1;
	/**
	 * 原视频大小
	 */
	private java.lang.String bak2;

	/**
	 * 是否是自动录制视频(Y 是，N 不是）
	 */
	private String isRecord;

	// columns END 数据库字段结束

	// 关联字段
	/**
	 * 搜索字段
	 */
	private java.lang.String search;

	/**
	 * 排序方式
	 */
	private java.lang.String sortord;

	/**
	 * 学校名称
	 */
	private java.lang.String schoolName;

	/**
	 * 教师名称
	 */
	private java.lang.String teacherName;

	/**
	 * 教师头像路径
	 */
	private java.lang.String teacherPic;

	/**
	 * 用户权限
	 */
	private java.lang.String authority;

	/**
	 * 用户权限所对应的学校id
	 */
	private List<String> schoolIds;

	/**
	 * 地区 筛选使用
	 */
	private String area;

	// concstructor
	public OffLineVideoResources() {
	}

	public OffLineVideoResources(java.lang.String id) {
		this.id = id;
	}

	// get and set
	public void setId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.id = value;
	}

	public String getIsRecord() {
		return isRecord;
	}

	public void setIsRecord(String isRecord) {
		this.isRecord = isRecord;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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

	public java.lang.String getTeacherPic() {
		return teacherPic;
	}

	public void setTeacherPic(java.lang.String teacherPic) {
		this.teacherPic = teacherPic;
	}

	public java.lang.String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(java.lang.String teacherName) {
		this.teacherName = teacherName;
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

	public java.lang.String getSortord() {
		return sortord;
	}

	public void setSortord(java.lang.String sortord) {
		this.sortord = sortord;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setUserId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.userId = value;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	public void setTitle(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.title = value;
	}

	public java.lang.String getTitle() {
		return this.title;
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

	public void setGradeName(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.gradeName = value;
	}

	public java.lang.String getGradeName() {
		return this.gradeName;
	}

	public void setSubjectName(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.subjectName = value;
	}

	public java.lang.String getSubjectName() {
		return this.subjectName;
	}

	public void setTeacherId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.teacherId = value;
	}

	public java.lang.String getTeacherId() {
		return this.teacherId;
	}

	public void setUploadName(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.uploadName = value;
	}

	public java.lang.String getUploadName() {
		return this.uploadName;
	}

	public void setVideoPath(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.videoPath = value;
	}

	public java.lang.String getVideoPath() {
		return this.videoPath;
	}

	public void setType(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.type = value;
	}

	public java.lang.String getType() {
		return this.type;
	}

	public void setPicPath(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.picPath = value;
	}

	public java.lang.String getPicPath() {
		return this.picPath;
	}

	public void setTranscodingState(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.transcodingState = value;
	}

	public java.lang.String getTranscodingState() {
		return this.transcodingState;
	}

	public void setProgress(java.lang.Integer value) {
		this.progress = value;
	}

	public java.lang.Integer getProgress() {
		return this.progress;
	}

	public void setTimeLength(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.timeLength = value;
	}

	public java.lang.String getTimeLength() {
		return this.timeLength;
	}

	public void setSize(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.size = value;
	}

	public java.lang.String getSize() {
		return this.size;
	}

	public void setReleaseState(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.releaseState = value;
	}

	public java.lang.String getReleaseState() {
		return this.releaseState;
	}

	public void setIsShow(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.isShow = value;
	}

	public java.lang.String getIsShow() {
		return this.isShow;
	}

	public void setPageView(java.lang.Integer value) {
		this.pageView = value;
	}

	public java.lang.Integer getPageView() {
		return this.pageView;
	}

	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
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
		return "OffLineVideoResources [id=" + id + ", userId=" + userId + ", title=" + title + ", schoolId=" + schoolId
				+ ", gradeName=" + gradeName + ", subjectName=" + subjectName + ", teacherId=" + teacherId
				+ ", uploadName=" + uploadName + ", videoPath=" + videoPath + ", type=" + type + ", picPath=" + picPath
				+ ", transcodingState=" + transcodingState + ", progress=" + progress + ", timeLength=" + timeLength
				+ ", size=" + size + ", releaseState=" + releaseState + ", isShow=" + isShow + ", pageView=" + pageView
				+ ", createTime=" + createTime + ", bak=" + bak + ", bak1=" + bak1 + ", bak2=" + bak2 + ", isRecord="
				+ isRecord + ", search=" + search + ", sortord=" + sortord + ", schoolName=" + schoolName
				+ ", teacherName=" + teacherName + ", teacherPic=" + teacherPic + ", authority=" + authority
				+ ", schoolIds=" + schoolIds + ", area=" + area + "]";
	}

}
