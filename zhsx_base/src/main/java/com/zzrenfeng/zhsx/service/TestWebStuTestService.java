package com.zzrenfeng.zhsx.service;


import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.TestWebStuTest;
import com.zzrenfeng.zhsx.model.TestWebStuTestQuestion;
import com.zzrenfeng.zhsx.model.TestWebStuTestQuestionAnswer;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-13 18:01:31
 * @see com.zzrenfeng.zhsx.service.TestWebStuTest
 */
public interface TestWebStuTestService extends BaseService<TestWebStuTest> {





	/**
	 * 学生成绩柱状图
	 * @param twst
	 * @return
	 */
	public List<TestWebStuTest> histogram(TestWebStuTest twst);

	/**
	 * 提交试卷
	 * @param twst
	 */
	public void addStuTest(TestWebStuTest twst);


}
