package com.zzrenfeng.zhsx.service;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.SysClassroom;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.WebDeviceManage;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-08 14:24:22
 * @see com.zzrenfeng.zhsx.service.WebDeviceManage
 */
public interface WebDeviceManageService extends BaseService<WebDeviceManage> {

	List<Map<String, Object>> getdetailSchool(List<SysSchool> schoolList, Map<String, Object> paramMap2);

	Map<String, Object> getTotalSchool(Map<String, Object> paramMap);

	List<Map<String, Object>> getdetailClass(List<SysClassroom> sysClassroomList, Map<String, Object> paramMap);
	
	/**
	 * 根据设备编号获取设备信息和班级信息
	 * @param deviceCode
	 * @return
	 */
	Map<String, Object> selectDeviceCalssInfo(String string);

	String inDeviceRecord(TreeMap<String, Object> param);

	Map<String, Object> inSomeMap(TreeMap<String, Object> param);

	Map<String, Object> upSomeMap(TreeMap<String, Object> param);
	
	/**
	 * 调用在线的方法
	 */
	List<String> selectAllDeviceCodeOnline();
	/**
	 * 更新设备信息，为了绕过事务控制
	 * @param dm
	 */
	void upByKeySelective(WebDeviceManage dm);

	Map<String, Object> findTotal();
	
	/**
	 * 
	 * @param paramMap 运行参数
	 * @return
	 */
	List<Map<String,Object>> onlineDeviceEveryday(Map<String, Object> paramMap);
	
	/**
	 * 根据条件查询 当前时间在线设备数量，以及总设备数量
	 * @param paramMap 运行参数
	 * @return
	 */
	Map<String, Object> totalOnlineDeviceCount(Map<String, Object> paramMap);
	
	/**
	 * 每天接入班班通数量
	 * @param paramMap 运行参数
	 * @return
	 */
	List<Map<String, Object>> everyDayAccessCount(Map<String, Object> paramMap);
	/**
	 * 设备维修数量变化曲线
	 * @param paramMap 运行参数
	 * @return
	 */
	List<Map<String, Object>> repairDeviceCount(Map<String, Object> paramMap);
	/**
	 * 班班通使用率排行榜 
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> usageRateRank(Map<String, Object> paramMap);

	/**
	 * 删除设备信息
	 * 同时删除WebClassDevice中对应的数据 
	 * @param ids
	 * @return
	 */
	void deleteBatchByKeys(String ids);
	
	/**
	 * 根据教室id或者设备的状态
	 * @param classId
	 * @return
	 */
	Integer findDeviceStateByClassId(String classId);





}
