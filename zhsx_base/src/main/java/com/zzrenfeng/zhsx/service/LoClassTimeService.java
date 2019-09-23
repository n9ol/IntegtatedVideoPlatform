package com.zzrenfeng.zhsx.service;


import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.LoClassTime;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:39
 * @see com.zzrenfeng.zhsx.service.LoClassTime
 */
public interface LoClassTimeService extends BaseService<LoClassTime> {



	/**
	 * 批量添加时间
	 * @param loClassTimeList
	 */
	void insertBatch(List<LoClassTime> loClassTimeList);




}
