package com.zzrenfeng.zhsx.service;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.OffLineRecordVideo;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-04-18 14:33:31
 * @see com.zzrenfeng.zhsx.service.OffLineRecordVideo
 */
public interface OffLineRecordVideoService extends BaseService<OffLineRecordVideo> {

	/**
	 * 重写添加方法-避免日志切面拦截
	 * 
	 * @param offLineRecordVideo
	 * @return
	 */
	int tInsert(OffLineRecordVideo offLineRecordVideo);

}