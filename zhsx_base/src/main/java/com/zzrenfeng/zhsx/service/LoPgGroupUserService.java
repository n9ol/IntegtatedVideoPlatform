package com.zzrenfeng.zhsx.service;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.LoPgGroupUser;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-29 16:52:45
 * @see com.zzrenfeng.zhsx.service.LoPgGroupUser
 */
public interface LoPgGroupUserService extends BaseService<LoPgGroupUser> {

	void batchInster(String pgGroupId, String[] userIds);

	void batchDel(String[] ids);

	/**
	 * 用户是否具有该课程的评估权限
	 * 
	 * @param userId
	 * @param loscheduleId
	 * @return
	 */
	int isPgAuthority(String userId, String loscheduleId);
}