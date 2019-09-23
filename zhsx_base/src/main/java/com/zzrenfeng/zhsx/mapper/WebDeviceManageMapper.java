package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.WebDeviceManage;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-07-23 16:03:27
 * @see com.zzrenfeng.zhsx.service.WebDeviceManage
 */

public interface WebDeviceManageMapper extends BaseMapper<WebDeviceManage>{
	
	Map<String, Object> getTotalSchool(Map<String, Object> paramMap);

	Map<String, Object> getdetailSchool(Map<String, Object> paramMap2);

	Map<String, Object> getDetailClass(Map<String, Object> params);

	
	Map<String, Object> selectDeviceCalssInfo(String string);

	List<String> selectAllDeviceCodeOnline();

	Map<String, Object> findTotal();

	List<Map<String,Object>> onlineDeviceEveryday(Map<String, Object> paramMap);

	Map<String, Object> totalOnlineDeviceCount(Map<String, Object> paramMap);

	List<Map<String, Object>> everyDayAccessCount(Map<String, Object> paramMap);

	List<Map<String, Object>> repairDeviceCount(Map<String, Object> paramMap);

	List<Map<String, Object>> usageRateRank(Map<String, Object> paramMap);

	Integer findDeviceStateByClassId(String classId);

	List<Map<String, Object>> getDeviceByClassId(String classId);

}

