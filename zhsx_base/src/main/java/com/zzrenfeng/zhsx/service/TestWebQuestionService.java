package com.zzrenfeng.zhsx.service;

import java.util.List;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.TestWebQuestion;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-07-05 15:24:05
 * @see com.zzrenfeng.zhsx.service.TestWebQuestion
 */
public interface TestWebQuestionService extends BaseService<TestWebQuestion> {

	/**
	 * 批量删除
	 * 
	 * @param ids
	 */
	public void delBatchQuestion(List<String> ids);

	/**
	 * 根据试卷id获得试卷下的题
	 */
	public Page<TestWebQuestion> findTestQuestion(TestWebQuestion twq, int p, int pageSize);

	/**
	 * 随机获得多少个试题
	 * 
	 * @param twq
	 * @return
	 */
	public List<TestWebQuestion> findRandom(TestWebQuestion twq);

}
