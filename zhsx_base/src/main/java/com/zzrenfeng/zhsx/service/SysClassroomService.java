package com.zzrenfeng.zhsx.service;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.SysClassroom;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.SysClassroom
 */
public interface SysClassroomService extends BaseService<SysClassroom> {

	/**
	 * 批量删除教室
	 * 
	 * @param ids
	 */
	void delbatchClassRoom(List<String> ids);

	/**
	 * 批量插入
	 * 
	 * @param slist
	 */
	void insertBatch(List<SysClassroom> slist);

	/**
	 * 批量修改教室状态
	 * 
	 * @param ids
	 * @param bak
	 */
	void batchUpdateState(List<String> ids, String bak);

	/**
	 * 发现所有的教室名称和编号 - 电子白板接口使用
	 * 
	 * @return
	 */
	List<Map<String, Object>> findAllNameAndCode(String schoolId);

	/**
	 * 更改教室直播状态
	 * 
	 * @param code
	 * @param bakl
	 * @return
	 */
	int tUpdateOnlineState(String classCode, String serviceIp, String onlineState);

	/**
	 * 获取教室信息通过 classCode
	 * 
	 * @param classCode
	 * @return
	 */
	SysClassroom getSysClassroom(String classCode, String serviceIp);

	/**
	 * 获取教室信息通过 classCode
	 * 
	 * @param classCode
	 * @return
	 */
	SysClassroom getSysClassroomByClassCode(String classCode);

	/**
	 * 更新教室所在教学楼的名称
	 * 
	 * @param teachingBuildingId
	 * @param teachingBuildingName
	 * @return
	 */
	int updateClassroomTeachingBuildingName(String teachingBuildingId, String teachingBuildingName);

}
