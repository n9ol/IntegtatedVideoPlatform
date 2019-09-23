package com.zzrenfeng.zhsx.model.eclassbrand.course;


/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-05-22 09:59:04
 * @see com.zzrenfeng.model.CourseSchedule
 */

public class CourseSchedule {

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 教室主键id
	 */
	private java.lang.String classroomId;
	/**
	 * 星期几
	 */
	private Integer dayOfWeek;
	/**
	 * 节次(小节)
	 */
	private java.lang.String sectionOfDay;
	/**
	 * 节次(大节)-大学专用
	 */
	private java.lang.String bigSectionOfDay;
	/**
	 * 单双周（odd 单周、even 双周、all 所有）
	 */
	private java.lang.String oddEvenAll;
	/**
	 * 开始周
	 */
	private Integer beginWeek;
	/**
	 * 结束周
	 */
	private Integer endWeek;
	/**
	 * 上课教师名称
	 */
	private java.lang.String teacherName;
	/**
	 * 上课科目名称
	 */
	private java.lang.String subjectName;
	/**
	 * 届、级
	 */
	private java.lang.String theClass;
	/**
	 * 院系的名称
	 */
	private java.lang.String departmentName;
	/**
	 * 专业名称(或者年级名称)
	 */
	private java.lang.String specialtyName;
	/**
	 * 班级的名称
	 */
	private java.lang.String className;
	/**
	 * 开始时间
	 */
	private java.util.Date startTime;
	/**
	 * 结束时间
	 */
	private java.util.Date endTime;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 更新时间
	 */
	private java.util.Date modiyDate;
	/**
	 * 自定义显示字段
	 */
	private java.lang.String bak;
	/**
	 * 自定义显示字段
	 */
	private java.lang.String bak1;
	/**
	 * 自定义显示字段
	 */
	private java.lang.String bak2;
	/**
	 * 直播类型（Z 直播课堂 A 在线评估 G 专递课堂）
	 */
	private java.lang.String type;
	/**
	 * 学校id
	 */
	private java.lang.String schoolId;
	/**
	 * 学校名称
	 */
	private String schoolName;
	/**
	 * 教室名称
	 */
	private String classroomName;
	/**
	 * 老师id
	 */
	private String teacherId;

	// 扩展字段
	private java.lang.String search; // 搜索字段
	private java.lang.String timeSorting; // 时间排序
	private int isGoClass; // 上课状态(0 已结束,1 正在上课,2 未开始)
	private String notId; // 不需要查询的主键id集合
	private String startTimeStr;  // 课程开始时间的字符串格式;zjc add
	private String endTimeStr;    // 课程结束时间的字符串格式;zjc add

	// get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getNotId() {
		return notId;
	}

	public void setNotId(String notId) {
		this.notId = notId;
	}

	public java.lang.String getSearch() {
		return search;
	}

	public void setSearch(java.lang.String search) {
		this.search = search;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getClassroomName() {
		return classroomName;
	}

	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
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

	public java.lang.String getId() {
		return this.id;
	}

	public void setClassroomId(java.lang.String value) {
		this.classroomId = value;
	}

	public java.lang.String getClassroomId() {
		return this.classroomId;
	}

	public void setDayOfWeek(Integer value) {
		this.dayOfWeek = value;
	}

	public Integer getDayOfWeek() {
		return this.dayOfWeek;
	}

	public void setSectionOfDay(java.lang.String value) {
		this.sectionOfDay = value;
	}

	public java.lang.String getSectionOfDay() {
		return this.sectionOfDay;
	}

	public void setBigSectionOfDay(java.lang.String value) {
		this.bigSectionOfDay = value;
	}

	public java.lang.String getBigSectionOfDay() {
		return this.bigSectionOfDay;
	}

	public void setOddEvenAll(java.lang.String value) {
		this.oddEvenAll = value;
	}

	public java.lang.String getOddEvenAll() {
		return this.oddEvenAll;
	}

	public void setBeginWeek(Integer value) {
		this.beginWeek = value;
	}

	public Integer getBeginWeek() {
		return this.beginWeek;
	}

	public void setEndWeek(Integer value) {
		this.endWeek = value;
	}

	public Integer getEndWeek() {
		return this.endWeek;
	}

	public void setTeacherName(java.lang.String value) {
		this.teacherName = value;
	}

	public java.lang.String getTeacherName() {
		return this.teacherName;
	}

	public void setSubjectName(java.lang.String value) {
		this.subjectName = value;
	}

	public java.lang.String getSubjectName() {
		return this.subjectName;
	}

	public void setTheClass(java.lang.String value) {
		this.theClass = value;
	}

	public java.lang.String getTheClass() {
		return this.theClass;
	}

	public void setDepartmentName(java.lang.String value) {
		this.departmentName = value;
	}

	public java.lang.String getDepartmentName() {
		return this.departmentName;
	}

	public void setSpecialtyName(java.lang.String value) {
		this.specialtyName = value;
	}

	public java.lang.String getSpecialtyName() {
		return this.specialtyName;
	}

	public void setClassName(java.lang.String value) {
		this.className = value;
	}

	public java.lang.String getClassName() {
		return this.className;
	}

	public void setStartTime(java.util.Date value) {
		this.startTime = value;
	}

	public java.util.Date getStartTime() {
		return this.startTime;
	}

	public void setEndTime(java.util.Date value) {
		this.endTime = value;
	}

	public java.util.Date getEndTime() {
		return this.endTime;
	}

	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}

	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	public void setModiyDate(java.util.Date value) {
		this.modiyDate = value;
	}

	public java.util.Date getModiyDate() {
		return this.modiyDate;
	}

	public void setBak(java.lang.String value) {
		this.bak = value;
	}

	public java.lang.String getBak() {
		return this.bak;
	}

	public void setBak1(java.lang.String value) {
		this.bak1 = value;
	}

	public java.lang.String getBak1() {
		return this.bak1;
	}

	public void setBak2(java.lang.String value) {
		this.bak2 = value;
	}

	public java.lang.String getBak2() {
		return this.bak2;
	}

	public void setType(java.lang.String value) {
		this.type = value;
	}

	public java.lang.String getType() {
		return this.type;
	}

	public void setSchoolId(java.lang.String value) {
		this.schoolId = value;
	}

	public java.lang.String getSchoolId() {
		return this.schoolId;
	}

	@Override
	public String toString() {
		return "CourseSchedule [id=" + id + ", classroomId=" + classroomId + ", dayOfWeek=" + dayOfWeek
				+ ", sectionOfDay=" + sectionOfDay + ", bigSectionOfDay=" + bigSectionOfDay + ", oddEvenAll="
				+ oddEvenAll + ", beginWeek=" + beginWeek + ", endWeek=" + endWeek + ", teacherName=" + teacherName
				+ ", subjectName=" + subjectName + ", theClass=" + theClass + ", departmentName=" + departmentName
				+ ", specialtyName=" + specialtyName + ", className=" + className + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", createDate=" + createDate + ", modiyDate=" + modiyDate + ", bak=" + bak
				+ ", bak1=" + bak1 + ", bak2=" + bak2 + ", type=" + type + ", schoolId=" + schoolId + ", schoolName="
				+ schoolName + ", classroomName=" + classroomName + ", teacherId=" + teacherId + ", search=" + search
				+ ", timeSorting=" + timeSorting + ", isGoClass=" + isGoClass + ", notId=" + notId + "]";
	}

	public CourseSchedule() {
		super();
	}

	public CourseSchedule(String teacherId) {
		super();
		this.teacherId = teacherId;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

}
