package com.zzrenfeng.zhsx.mapper;

import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TestWebTest;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-05 15:34:13
 * @see com.zzrenfeng.zhsx.service.TestWebTest
 */

public interface TestWebTestMapper extends BaseMapper<TestWebTest>{


	/**
	 * 批量删除
	 */
	public void delBatchTest(Map<String, Object> hm);



}

