package com.zzrenfeng.zhsx.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:39
 * @see com.zzrenfeng.zhsx.model.LoSchedule
 */

public class LoSchedule {

	/**
	 * 评估模式 (G 公开模式，S 私有模式 , N 禁止评估)
	 */
	public final static String PG_BAK2_S = "S";
	public final static String PG_BAK2_G = "G";
	public final static String PG_BAK2_N = "N";

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 教室Id
	 */
	private java.lang.String classId;

	/**
	 * 学校Id
	 */
	private java.lang.String schoolId;
	/**
	 * 一周的第几天（星期几）
	 */
	private Integer dayofweek;
	/**
	 * 一天的第几节
	 */
	private Integer sectionofday;
	/**
	 * 年级id(改为年级名称)
	 */
	private java.lang.String gradeId;
	/**
	 * 科目id(改为科目名称)
	 */
	private java.lang.String subjectId;
	/**
	 * 主讲人id
	 */
	private java.lang.String userId;
	/**
	 * 直播封面
	 */
	private java.lang.String coverpath;
	/**
	 * 加入直播人员，最多3个
	 */
	private Integer john_num;
	/**
	 * 直播日期
	 */
	private java.util.Date z_date;
	/**
	 * 周次，安排在第几周上课
	 */
	private Integer weeks;
	/**
	 * 直播类型（A 质量评估 ,G 专递课堂, Z 直播课堂）
	 */
	private java.lang.String type;
	/**
	 * 备用字段(classTimeId) 开始时间，结束时间
	 */
	private java.lang.String bak;
	/**
	 * 备用字段(termTimeId) 学期时间
	 */
	private java.lang.String bak1;
	/**
	 * 评估模式 (G 公开模式，S 私有模式 )
	 */
	private java.lang.String bak2;
	// columns END 数据库字段结束

	// 关联字段
	private java.lang.String startDate; // 开始时间
	private java.lang.String endDate; // 结束时间
	private List<LoFschedule> loFscheduleList; // 辅讲信息列表
	private java.lang.String gradeName; // 年级名称-弃用
	private java.lang.String subjectName; // 科目名称-弃用
	private java.lang.String userName; // 主讲人名称
	private Integer startWeek; // 开始周(用于批量删除)
	private Integer endWeek; // 结束周(用于批量删除)
	private java.lang.String schoolName; // 学校名称
	private java.lang.String className; // 教室名称
	private java.lang.String search; // 搜索字段
	private java.lang.String timeSorting; // 时间排序
	private int isGoClass;// 上课状态(0 已结束,1 正在上课,2 未上课)
	private java.lang.String userPic; // 主讲人头像路径
	private String ispj; // 是否评估
	private String area;
	private String pgUserId; // 评估权限用户Id-(我的评估课)
	private String shootingWay;
	private String fClassNames; // 辅教室名称
	private String fTeachers; // 辅教室教师

	// concstructor
	public LoSchedule() {
	}

	public LoSchedule(java.lang.String id) {
		this.id = id;
	}

	// get and set
	public void setId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.id = value;
	}

	public String getfClassNames() {
		return fClassNames;
	}

	public void setfClassNames(String fClassNames) {
		this.fClassNames = fClassNames;
	}

	public String getfTeachers() {
		return fTeachers;
	}

	public void setfTeachers(String fTeachers) {
		this.fTeachers = fTeachers;
	}

	public String getShootingWay() {
		return shootingWay;
	}

	public void setShootingWay(String shootingWay) {
		this.shootingWay = shootingWay;
	}

	public String getPgUserId() {
		return pgUserId;
	}

	public void setPgUserId(String pgUserId) {
		this.pgUserId = pgUserId;
	}

	public String getIspj() {
		return ispj;
	}

	public void setIspj(String ispj) {
		this.ispj = ispj;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public java.lang.String getUserPic() {
		return userPic;
	}

	public void setUserPic(java.lang.String userPic) {
		this.userPic = userPic;
	}

	public int getIsGoClass() {
		return isGoClass;
	}

	public void setIsGoClass(int isGoClass) {
		this.isGoClass = isGoClass;
	}

	public java.lang.String getTimeSorting() {
		return timeSorting;
	}

	public void setTimeSorting(java.lang.String timeSorting) {
		this.timeSorting = timeSorting;
	}

	public java.lang.String getSearch() {
		return search;
	}

	public void setSearch(java.lang.String search) {
		this.search = search;
	}

	public java.lang.String getClassName() {
		return className;
	}

	public void setClassName(java.lang.String className) {
		this.className = className;
	}

	public java.lang.String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(java.lang.String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getStartWeek() {
		return startWeek;
	}

	public void setStartWeek(Integer startWeek) {
		this.startWeek = startWeek;
	}

	public Integer getEndWeek() {
		return endWeek;
	}

	public void setEndWeek(Integer endWeek) {
		this.endWeek = endWeek;
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

	public java.lang.String getUserName() {
		return userName;
	}

	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	public List<LoFschedule> getLoFscheduleList() {
		return loFscheduleList;
	}

	public void setLoFscheduleList(List<LoFschedule> loFscheduleList) {
		this.loFscheduleList = loFscheduleList;
	}

	public java.lang.String getStartDate() {
		return startDate;
	}

	public void setStartDate(java.lang.String startDate) {
		this.startDate = startDate;
	}

	public java.lang.String getEndDate() {
		return endDate;
	}

	public void setEndDate(java.lang.String endDate) {
		this.endDate = endDate;
	}

	public java.lang.String getId() {
		return this.id;
	}

	public void setClassId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.classId = value;
	}

	public java.lang.String getClassId() {
		return this.classId;
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

	public void setDayofweek(Integer value) {
		this.dayofweek = value;
	}

	public Integer getDayofweek() {
		return this.dayofweek;
	}

	public void setSectionofday(Integer value) {
		this.sectionofday = value;
	}

	public Integer getSectionofday() {
		return this.sectionofday;
	}

	public void setGradeId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.gradeId = value;
	}

	public java.lang.String getGradeId() {
		return this.gradeId;
	}

	public void setSubjectId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.subjectId = value;
	}

	public java.lang.String getSubjectId() {
		return this.subjectId;
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

	public void setCoverpath(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.coverpath = value;
	}

	public java.lang.String getCoverpath() {
		return this.coverpath;
	}

	public void setJohn_num(Integer value) {
		this.john_num = value;
	}

	public Integer getJohn_num() {
		return this.john_num;
	}

	public void setZ_date(java.util.Date value) {
		this.z_date = value;
	}

	public java.util.Date getZ_date() {
		return this.z_date;
	}

	public void setWeeks(Integer value) {
		this.weeks = value;
	}

	public Integer getWeeks() {
		return this.weeks;
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
	public String toString() {
		return "LoSchedule [id=" + id + ", classId=" + classId + ", schoolId=" + schoolId + ", dayofweek=" + dayofweek
				+ ", sectionofday=" + sectionofday + ", gradeId=" + gradeId + ", subjectId=" + subjectId + ", userId="
				+ userId + ", coverpath=" + coverpath + ", john_num=" + john_num + ", z_date=" + z_date + ", weeks="
				+ weeks + ", type=" + type + ", bak=" + bak + ", bak1=" + bak1 + ", bak2=" + bak2 + ", startDate="
				+ startDate + ", endDate=" + endDate + ", loFscheduleList=" + loFscheduleList + ", gradeName="
				+ gradeName + ", subjectName=" + subjectName + ", userName=" + userName + ", startWeek=" + startWeek
				+ ", endWeek=" + endWeek + ", schoolName=" + schoolName + ", className=" + className + ", search="
				+ search + ", timeSorting=" + timeSorting + ", isGoClass=" + isGoClass + ", userPic=" + userPic
				+ ", ispj=" + ispj + ", area=" + area + ", pgUserId=" + pgUserId + ", shootingWay=" + shootingWay
				+ ", fClassNames=" + fClassNames + ", fTeachers=" + fTeachers + "]";
	}

}
