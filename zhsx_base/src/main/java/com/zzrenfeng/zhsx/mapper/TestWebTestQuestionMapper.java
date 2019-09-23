package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.SysSchool;
import com.zzrenfeng.zhsx.model.TestWebTestQuestion;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-13 14:53:43
 * @see com.zzrenfeng.zhsx.service.TestWebTestQuestion
 */

public interface TestWebTestQuestionMapper extends BaseMapper<TestWebTestQuestion>{



	/**
	 * 获取试卷详细信息
	 * @param tp
	 * @return
	 */
	public Page<TestWebTestQuestion> getTestpaper(TestWebTestQuestion tp);
	/**
	 * 批量插入
	 * @param slist
	 */
	void batchAdd(List<TestWebTestQuestion> list);
 
}

