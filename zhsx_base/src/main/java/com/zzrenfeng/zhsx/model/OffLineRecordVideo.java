package com.zzrenfeng.zhsx.model;



/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version  2018-04-18 14:33:31
 * @see com.zzrenfeng.zhsx.model.OffLineRecordVideo
 */

public class OffLineRecordVideo {
		

	/**
	 * 主键
	 */
	private java.lang.String id;
	/**
	 * 对应离线视频资源表id
	 */
	private java.lang.String offlinevideoid;
	/**
	 * 录制教室id
	 */
	private java.lang.String classroomid;
	/**
	 * 视频类型（Z 直播课堂 A 在线评估  G 专递课堂）
	 */
	private java.lang.String videotype;
	/**
	 * 流类型(1、2、3、a  分别是 教师、学生、电子白板、音频 ）
	 */
	private java.lang.String streamtype;
	/**
	 * 教室类型(0、1、2、3  分别代表 主、辅1、辅2、辅3 ）
	 */
	private java.lang.String classroomtype;
	/**
	 * 视频路径
	 */
	private java.lang.String videopatch;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 更新时间
	 */
	private java.util.Date modifyDate;
	
	
	

	//get and set
	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setOfflinevideoid(java.lang.String value) {
		this.offlinevideoid = value;
	}
	
	public java.lang.String getOfflinevideoid() {
		return this.offlinevideoid;
	}
	public void setClassroomid(java.lang.String value) {
		this.classroomid = value;
	}
	
	public java.lang.String getClassroomid() {
		return this.classroomid;
	}
	public void setVideotype(java.lang.String value) {
		this.videotype = value;
	}
	
	public java.lang.String getVideotype() {
		return this.videotype;
	}
	public void setStreamtype(java.lang.String value) {
		this.streamtype = value;
	}
	
	public java.lang.String getStreamtype() {
		return this.streamtype;
	}
	public void setClassroomtype(java.lang.String value) {
		this.classroomtype = value;
	}
	
	public java.lang.String getClassroomtype() {
		return this.classroomtype;
	}
	public void setVideopatch(java.lang.String value) {
		this.videopatch = value;
	}
	
	public java.lang.String getVideopatch() {
		return this.videopatch;
	}

	
	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}
	
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	
	public void setModifyDate(java.util.Date value) {
		this.modifyDate = value;
	}
	
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	public String toString() {
		return new StringBuffer()
			.append("主键[").append(getId()).append("],")
			.append("对应离线视频资源表id[").append(getOfflinevideoid()).append("],")
			.append("录制教室id[").append(getClassroomid()).append("],")
			.append("视频类型（Z 直播课堂 A 在线评估  G 专递课堂）[").append(getVideotype()).append("],")
			.append("流类型(1、2、3、a  分别是 教师、学生、电子白板、音频 ）[").append(getStreamtype()).append("],")
			.append("教室类型(0、1、2、3  分别代表 主、辅1、辅2、辅3 ）[").append(getClassroomtype()).append("],")
			.append("视频路径[").append(getVideopatch()).append("],")
			.append("创建时间[").append(getCreateDate()).append("],")
			.append("更新时间[").append(getModifyDate()).append("],")
			.toString();
	}
	

}

	
