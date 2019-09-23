package com.zzrenfeng.zhsx.service;


import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.WebDeviceRecord;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-08 14:27:49
 * @see com.zzrenfeng.zhsx.service.WebDeviceRecord
 */
public interface WebDeviceRecordService extends BaseService<WebDeviceRecord> {

	int upByKeySelective(WebDeviceRecord deviceRecord);

	String selectEndTimeByDeviceCode(String deviceCode);

	void deleteBatchByKeys(String ids);

	void deleteAllDRecord();








}
