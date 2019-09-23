package com.zzrenfeng.zhsx.mapper;

import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TpPracticeLibrary;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-09-19 16:01:40
 * @see com.zzrenfeng.zhsx.service.TpPracticeLibrary
 */

public interface TpPracticeLibraryMapper extends BaseMapper<TpPracticeLibrary>{

	void delBatchLibrary(Map<String, Object> hm);






}

