package com.zzrenfeng.zhsx.service;


import java.util.Date;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.SysHistory;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.SysHistory
 */
public interface SysHistoryService extends BaseService<SysHistory> {

	
	/**
	 * 删除记录通过pub
	 * @param pubFlag
	 * @param pubType
	 * @param pubId
	 * @param currUserId
	 */
	void deleteByPub(String pubFlag,String pubType,String pubId,String currUserId);

	/**
	 * 根据用户查询收藏记录
	 * @param m
	 * @param p
	 * @param pageSize
	 * @return
	 */
	Page<Map<String, String>> findCollectRecord(Map<String, Object> m,int p,int pageSize);

	/**
	 * 查询用户观看记录
	 * @param m
	 * @param p
	 * @param pageSize
	 * @return
	 */
	Page<Map<String, String>> findWatchRecord(Map<String, Object> m, int p, int pageSize); 

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
