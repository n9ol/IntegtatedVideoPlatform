package com.zzrenfeng.zhsx.service;


import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.SysLog;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.SysLog
 */
public interface SysLogService extends BaseService<SysLog> {



	/**
	 * 记录日志
	 * @param sysLog
	 * @return
	 */
	int recordLog(SysLog sysLog);
	
	/**
	 * 清空日志
	 * @return
	 */
	int emptyLog();

	/**
	 * 批量删除日志
	 * @param ids
	 */
	void delBatchLog(String[] del_id);
	
}
