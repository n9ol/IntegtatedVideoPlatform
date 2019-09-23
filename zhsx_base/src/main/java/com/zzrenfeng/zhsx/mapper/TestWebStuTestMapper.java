package com.zzrenfeng.zhsx.mapper;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseMapper;
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

public interface TestWebStuTestMapper extends BaseMapper<TestWebStuTest>{

	/**
	 * 学生成绩柱状图
	 * @param twst
	 * @return
	 */
	public List<TestWebStuTest> histogram(TestWebStuTest twst);

	
	/**
	 * 提交试卷(有就修改没有就添加)
	 * @param twst
	 */
	public void addStuTest(TestWebStuTest twst);


}

