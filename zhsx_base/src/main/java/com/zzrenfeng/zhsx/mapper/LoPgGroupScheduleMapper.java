package com.zzrenfeng.zhsx.mapper;

import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.LoPgGroupSchedule;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-29 16:53:02
 * @see com.zzrenfeng.zhsx.service.LoPgGroupSchedule
 */

public interface LoPgGroupScheduleMapper extends BaseMapper<LoPgGroupSchedule> {

	void batchInster(Map<String, Object> hm);

}
