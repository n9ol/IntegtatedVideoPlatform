package com.zzrenfeng.zhsx.mapper;

import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.WebDeviceRepair;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-07-23 16:17:29
 * @see com.zzrenfeng.zhsx.service.WebDeviceRepair
 */

public interface WebDeviceRepairMapper extends BaseMapper<WebDeviceRepair>{


	Page<WebDeviceRepair> selectDRByContions(Map<String, Object> paramMap);

	void dataInsert(WebDeviceRepair dr);


}

