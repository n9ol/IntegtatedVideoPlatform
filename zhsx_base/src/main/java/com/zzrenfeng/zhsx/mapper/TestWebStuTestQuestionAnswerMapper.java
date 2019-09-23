package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TestWebStuTestQuestionAnswer;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-14 10:44:27
 * @see com.zzrenfeng.zhsx.service.TestWebStuTestQuestionAnswer
 */

public interface TestWebStuTestQuestionAnswerMapper extends BaseMapper<TestWebStuTestQuestionAnswer>{



	/**
	 * （我的试卷管理 通过stuTestId进入试卷作答详情）
	 * @param twstq
	 * @return
	 */
	public Page<TestWebStuTestQuestionAnswer> findTestDetail(TestWebStuTestQuestionAnswer twstq);

	/**
	 * 我的错题库
	 * @param twstq
	 * @return
	 */
	public Page<TestWebStuTestQuestionAnswer> FindMyErrors(TestWebStuTestQuestionAnswer twstq);

	/**
	 * 批量插入（试卷提交用）
	 * @param list
	 */
	public void insertBetch(List<TestWebStuTestQuestionAnswer> list);
	
	
	
}

