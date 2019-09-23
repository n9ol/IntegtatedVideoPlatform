package com.zzrenfeng.zhsx.service;


import java.util.List;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.TestWebStuTestQuestionAnswer;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-14 10:44:27
 * @see com.zzrenfeng.zhsx.service.TestWebStuTestQuestionAnswer
 */
public interface TestWebStuTestQuestionAnswerService extends BaseService<TestWebStuTestQuestionAnswer> {



	/**
	 * （我的试卷管理 通过stuTestId进入试卷作答详情）
	 * @param twstq
	 * @return
	 */
	public Page<TestWebStuTestQuestionAnswer> findTestDetail(TestWebStuTestQuestionAnswer twstq,int p,int pageSize);



	/**
	 * 我的错题库
	 * @param twstq
	 * @return
	 */
	public Page<TestWebStuTestQuestionAnswer> FindMyErrors(TestWebStuTestQuestionAnswer twstq,int p,int pageSize);

	/**
	 * 批量插入（试卷提交用）
	 * @param list
	 */
	public void insertBetch(List<TestWebStuTestQuestionAnswer> list);

}
