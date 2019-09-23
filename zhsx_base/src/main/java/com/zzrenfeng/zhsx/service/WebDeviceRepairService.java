package com.zzrenfeng.zhsx.service;


import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.WebDeviceRepair;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-08 14:28:17
 * @see com.zzrenfeng.zhsx.service.WebDeviceRepair
 */
public interface WebDeviceRepairService extends BaseService<WebDeviceRepair> {

	List<WebDeviceRepair> selectDRByContions(Map<String, Object> paramMap);

	int dataUpdateByKeySelective(WebDeviceRepair dr);

	void dataInsert(WebDeviceRepair dr);

	Page<WebDeviceRepair> findPageByMapSelective(Map<String, Object> paramMap,Integer p, int pageSize);

	void deleteBatchByKeys(String ids);
	
		
	
	




}
