package com.zzrenfeng.zhsx.service;


import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.TestWebStuTestQuestion;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-27 17:28:49
 * @see com.zzrenfeng.zhsx.service.TestWebStuTestQuestion
 */
public interface TestWebStuTestQuestionService extends BaseService<TestWebStuTestQuestion> {




	/**
	 * 添加(添加修改)
	 */
	public void addQuestion(TestWebStuTestQuestion tq);

	/**
	 * 通过问题id stuTestId 获取该题问答结果
	 * @param questionId
	 * @param stuTestId
	 * @return
	 */
	public TestWebStuTestQuestion findOneByqId(TestWebStuTestQuestion q);


}
