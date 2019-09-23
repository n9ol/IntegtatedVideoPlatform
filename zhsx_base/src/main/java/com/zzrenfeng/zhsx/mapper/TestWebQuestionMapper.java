package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TestWebQuestion;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-07-05 15:24:05
 * @see com.zzrenfeng.zhsx.service.TestWebQuestion
 */

public interface TestWebQuestionMapper extends BaseMapper<TestWebQuestion> {

	/**
	 * 批量删除
	 */
	public void delBatchQuestion(Map<String, Object> hm);

	/**
	 * 根据试卷id获得试卷下的题
	 */
	public Page<TestWebQuestion> findTestQuestion(TestWebQuestion twq);

	/**
	 * 随机获得多少个试题
	 * 
	 * @param twq
	 * @return
	 */
	public List<TestWebQuestion> findRandom(TestWebQuestion twq);
}
