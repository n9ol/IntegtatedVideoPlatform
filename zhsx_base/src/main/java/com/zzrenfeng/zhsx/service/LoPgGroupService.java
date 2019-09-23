package com.zzrenfeng.zhsx.service;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.LoPgGroup;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-29 16:52:30
 * @see com.zzrenfeng.zhsx.service.LoPgGroup
 */
public interface LoPgGroupService extends BaseService<LoPgGroup> {

	void batchDelPgGroup(String[] ids);
}