package com.zzrenfeng.zhsx.service;


import java.util.List;
import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.TestWebQuestionAnswer;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-12 09:51:44
 * @see com.zzrenfeng.zhsx.service.TestWebQuestionAnswer
 */
public interface TestWebQuestionAnswerService extends BaseService<TestWebQuestionAnswer> {

	public void delByQuestionId(String questionId);
	public void delBatch(List<String> ids);

	/**
	 * 批量更改选项信息
	 * @param ids
	 * @param ifShow
	 */
	void updateBatch(List<TestWebQuestionAnswer> list);
}
