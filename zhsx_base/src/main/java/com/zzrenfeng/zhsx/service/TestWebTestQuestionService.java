package com.zzrenfeng.zhsx.service;


import java.util.List;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.TestWebTestQuestion;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-13 14:53:43
 * @see com.zzrenfeng.zhsx.service.TestWebTestQuestion
 */
public interface TestWebTestQuestionService extends BaseService<TestWebTestQuestion> {


	/**
	 * 获取试卷详细信息
	 * @param tp
	 * @return
	 */
	public Page<TestWebTestQuestion> getTestpaper(TestWebTestQuestion tp,int p, int pageSize);

	/**
	 * 批量插入
	 * @param slist
	 */
	void batchAdd(List<TestWebTestQuestion> list);




}
