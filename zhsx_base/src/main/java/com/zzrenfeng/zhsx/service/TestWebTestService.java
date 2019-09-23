package com.zzrenfeng.zhsx.service;


import java.text.ParseException;
import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.TestWebTest;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-05 15:34:13
 * @see com.zzrenfeng.zhsx.service.TestWebTest
 */
public interface TestWebTestService extends BaseService<TestWebTest> {





	/**
	 * 批量删除
	 * @param ids
	 */
	public void delBatchTest(List<String> ids);

	/**
	 * 发布试卷时更新用户经验值
	 * @param userId
	 * @param pubId
	 */
	void addUserExp(String userId,String pubId) throws ParseException;

}
