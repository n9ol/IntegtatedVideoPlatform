package com.zzrenfeng.zhsx.service;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.WebPjdetail;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-08-22 18:28:08
 * @see com.zzrenfeng.zhsx.service.WebPjdetail
 */
public interface WebPjdetailService extends BaseService<WebPjdetail> {

	/**
	 * 获取评估细则(无,初始化数据在查询获取)
	 * 
	 * @param webPjdetail
	 * @return
	 */
	List<WebPjdetail> listWebPjdetail(WebPjdetail webPjdetail);

}
