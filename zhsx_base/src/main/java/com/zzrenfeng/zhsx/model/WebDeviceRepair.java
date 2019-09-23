package com.zzrenfeng.zhsx.model;



/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-07-23 16:17:29
 * @see com.zzrenfeng.zhsx.model.WebDeviceRepair
 */

public class WebDeviceRepair {
	
	public static final String  DEVICE_ISVALID_STATE="0";	
	public static final String  DEVICE_NOVALID_STATE="1";
	
	public static final String  DEVICE_REPAIR_STATE_NONE="1";
	public static final String  DEVICE_REPAIR_STATE_MIDDLE="2";
	public static final String  DEVICE_REPAIR_STATE_ALREADY="0";
	
	public static final String  MANA_OVERDUE_STATE_NONE="0";//未授理
	public static final String  MANA_OVERDUE_STATE_OK="1";//已授理未逾期
	public static final String  MANA_OVERDUE_STATE_BUT="2";//已经授理但逾期
	
	
	/**
	 * id
	 */
	private java.lang.String id;
	
	private SysSchool school;
	private SysClassroom sysClass;
	private User user;
	private WebDeviceManage webDeviceManage;
	private SysDict sysDict;
	private User manager;
	
	/**
	 * school_id
	 
	private java.lang.String schoolId;*/
	/**
	 * 班级id
	 
	private java.lang.String classId;*/
	/**
	 * 用户id
	
	private java.lang.String userId; */
	/**
	 * 设备id(引入设备表)
	
	private java.lang.String deviceId; */
	/**
	 * 报修时间
	 */
	private java.util.Date repairTime;
	/**
	 * repair_phone
	
	private java.lang.String repairPhone; */
	/**
	 * 报修状态(0 已经维修 1 未维修  2 维修中 )
	 */
	private java.lang.String repairState;
	/**
	 * 故障描述（报修）
	 */
	private java.lang.String repairDescription;
	/**
	 * 设备管理员id
	 
	private java.lang.String managerId;*/
	/**
	 * 设备管理员授理时间
	 */
	private java.util.Date managerTime;
	/**
	 * 设备管理员授理逾期状态
	 * 
	 * 0未授理 1已授理未逾期 2已经授理但逾期
	 */
	private java.lang.String manaOverdueState;
	/**
	 * 设备管理员联系方式
	 
	private java.lang.String managerPhone;*/
	/**
	 * service_evaluation_grade
	 */
	private java.lang.String serviceEvaluationGrade;
	
	private WebDeviceTechnician webDeviceTechnician;
	
	
	/**
	 * 技术人员id
	 
	private java.lang.String technicianId;*/
	/**
	 * 技术人员逾期状态  暂时不用
	 */
	private java.lang.String techOverdueState;
	/** 
	 * 技术人员处理时间 暂时不用
	 */
	private java.util.Date technicianTime;
	/**
	 * 技术人员联系方式 暂时不用
	 */
	private java.lang.String technicianPhone;
	/**
	 * isvalid
	 */
	private java.lang.String isvalid;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 更新时间 
	 */
	private java.util.Date modifyTime;
	/**
	 * 创建人id   暂时不用
	 */
	private java.lang.String createId;
	/**
	 * 更新人id 暂时不用
	 */
	private java.lang.String modifyId;
	
	
	

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}

	
	public void setRepairTime(java.util.Date value) {
		this.repairTime = value;
	}
	
	public java.util.Date getRepairTime() {
		return this.repairTime;
	}
	
	public void setRepairState(java.lang.String value) {
		this.repairState = value;
	}
	
	public java.lang.String getRepairState() {
		return this.repairState;
	}
	public void setRepairDescription(java.lang.String value) {
		this.repairDescription = value;
	}
	
	public java.lang.String getRepairDescription() {
		return this.repairDescription;
	}

	
	public void setManagerTime(java.util.Date value) {
		this.managerTime = value;
	}
	
	public java.util.Date getManagerTime() {
		return this.managerTime;
	}
	public void setManaOverdueState(java.lang.String value) {
		this.manaOverdueState = value;
	}
	
	public java.lang.String getManaOverdueState() {
		return this.manaOverdueState;
	}
	public void setServiceEvaluationGrade(java.lang.String value) {
		this.serviceEvaluationGrade = value;
	}
	
	public java.lang.String getServiceEvaluationGrade() {
		return this.serviceEvaluationGrade;
	}
	public void setTechOverdueState(java.lang.String value) {
		this.techOverdueState = value;
	}
	
	public java.lang.String getTechOverdueState() {
		return this.techOverdueState;
	}

	
	public void setTechnicianTime(java.util.Date value) {
		this.technicianTime = value;
	}
	
	public java.util.Date getTechnicianTime() {
		return this.technicianTime;
	}
	public void setTechnicianPhone(java.lang.String value) {
		this.technicianPhone = value;
	}
	
	public java.lang.String getTechnicianPhone() {
		return this.technicianPhone;
	}
	public void setIsvalid(java.lang.String value) {
		this.isvalid = value;
	}
	
	public java.lang.String getIsvalid() {
		return this.isvalid;
	}

	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	
	public void setModifyTime(java.util.Date value) {
		this.modifyTime = value;
	}
	
	public java.util.Date getModifyTime() {
		return this.modifyTime;
	}
	public void setCreateId(java.lang.String value) {
		this.createId = value;
	}
	
	public java.lang.String getCreateId() {
		return this.createId;
	}
	public void setModifyId(java.lang.String value) {
		this.modifyId = value;
	}
	
	public java.lang.String getModifyId() {
		return this.modifyId;
	}

	
	public SysSchool getSchool() {
		return school;
	}

	public void setSchool(SysSchool school) {
		this.school = school;
	}

	public SysClassroom getSysClass() {
		return sysClass;
	}

	public void setSysClass(SysClassroom sysClass) {
		this.sysClass = sysClass;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WebDeviceManage getWebDeviceManage() {
		return webDeviceManage;
	}

	public void setWebDeviceManage(WebDeviceManage webDeviceManage) {
		this.webDeviceManage = webDeviceManage;
	}

	public SysDict getSysDict() {
		return sysDict;
	}

	public void setSysDict(SysDict sysDict) {
		this.sysDict = sysDict;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public WebDeviceTechnician getWebDeviceTechnician() {
		return webDeviceTechnician;
	}

	public void setWebDeviceTechnician(WebDeviceTechnician webDeviceTechnician) {
		this.webDeviceTechnician = webDeviceTechnician;
	}
	

	public String toString() {
		return new StringBuffer()
			.append("id[").append(getId()).append("],")
//			.append("school_id[").append(getSchoolId()).append("],")
//			.append("班级id[").append(getClassId()).append("],")
//			.append("用户id[").append(getUserId()).append("],")
//			.append("设备id(引入设备表)[").append(getDeviceId()).append("],")
			.append("报修时间[").append(getRepairTime()).append("],")
//			.append("repair_phone[").append(getRepairPhone()).append("],")
			.append("报修状态(0 已经维修 1 未维修  2 维修中 )[").append(getRepairState()).append("],")
			.append("故障描述（报修）[").append(getRepairDescription()).append("],")
//			.append("设备管理员id[").append(getManagerId()).append("],")
			.append("设备管理员授理时间[").append(getManagerTime()).append("],")
			.append("设备管理员授理逾期状态[").append(getManaOverdueState()).append("],")
//			.append("设备管理员联系方式[").append(getManagerPhone()).append("],")
			.append("service_evaluation_grade[").append(getServiceEvaluationGrade()).append("],")
//			.append("技术人员id[").append(getTechnicianId()).append("],")
			.append("技术人员逾期状态[").append(getTechOverdueState()).append("],")
			.append("技术人员处理时间[").append(getTechnicianTime()).append("],")
			.append("技术人员联系方式[").append(getTechnicianPhone()).append("],")
			.append("isvalid[").append(getIsvalid()).append("],")
			.append("创建时间[").append(getCreateTime()).append("],")
			.append("更新时间[").append(getModifyTime()).append("],")
			.append("创建人id[").append(getCreateId()).append("],")
			.append("更新人id[").append(getModifyId()).append("],")
			.toString();
	}
	

}

	
