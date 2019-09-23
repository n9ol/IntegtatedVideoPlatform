package com.zzrenfeng.zhsx.service;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.LoPgGroupSchedule;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-29 16:53:02
 * @see com.zzrenfeng.zhsx.service.LoPgGroupSchedule
 */
public interface LoPgGroupScheduleService extends BaseService<LoPgGroupSchedule> {

	void batchInster(String loscheduleId, String[] pgGroupIds);
}