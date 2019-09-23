package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TestWebQuestionAnswer;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-12 09:51:44
 * @see com.zzrenfeng.zhsx.service.TestWebQuestionAnswer
 */

public interface TestWebQuestionAnswerMapper extends BaseMapper<TestWebQuestionAnswer>{



	public void delByQuestionId(String questionId);
	public void delBatch(Map<String, Object> hm);
	/**
	 * 批量修改试题选项
	 */
	public void updateBatch(List<TestWebQuestionAnswer> list);
	
}

