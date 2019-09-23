package com.zzrenfeng.zhsx.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.SysHistory;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.SysHistory
 */

public interface SysHistoryMapper extends BaseMapper<SysHistory>{



	void deleteByPub(SysHistory sysHistory);

	/**
	 * 教师成长曲线查询
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> findGrowthCurve(String userId);
	
	/**
	 * 查询教师在本年度之前获得的经验值（注册初始经验值为50）
	 * @param userId
	 * @return
	 */
	List<Map<String, Object>> findEXPBeforeThisYear(String userId);

	/**
	 * 收藏记录
	 * @param userId
	 * @return
	 */
	Page<Map<String, String>> findCollectRecord(Map<String, Object> m);
	/**
	 * 观看记录
	 * @param m
	 * @return
	 */
	Page<Map<String, String>> findWatchRecord(Map<String, Object> m);
	
	
	/**
	 * 获得指定条件下获得经验值和
	 * @param sysHistory
	 * @return
	 */
	String getExp(SysHistory sysHistory); 
	
	
	/**
	 * 获得用户最新贡献时间
	 * @param userId
	 * @return
	 */
	Date getTeacherConTime(String userId);
	
	
}

