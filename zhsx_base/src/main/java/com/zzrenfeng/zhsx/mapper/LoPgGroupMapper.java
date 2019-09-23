package com.zzrenfeng.zhsx.mapper;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.LoPgGroup;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-29 16:52:30
 * @see com.zzrenfeng.zhsx.service.LoPgGroup
 */

public interface LoPgGroupMapper extends BaseMapper<LoPgGroup> {

	void batchDelPgGroup(String[] ids);

}
