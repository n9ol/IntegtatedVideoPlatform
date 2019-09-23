package com.zzrenfeng.zhsx.mapper;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.WebDeviceRecord;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-07-23 16:17:16
 * @see com.zzrenfeng.zhsx.service.WebDeviceRecord
 */

public interface WebDeviceRecordMapper extends BaseMapper<WebDeviceRecord>{


	String selectEndTimeByDeviceCode(String deviceCode);

	void deleteAllDRecord();




}

