package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TestWebTestQuestion;
import com.zzrenfeng.zhsx.service.TestWebTestQuestionService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.TestWebTestQuestionMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-13 14:53:43
 * @see com.zzrenfeng.zhsx.service.impl.TestWebTestQuestion
 */

@Service
public class TestWebTestQuestionServiceImpl extends BaseServiceImpl<BaseMapper<TestWebTestQuestion>, TestWebTestQuestion> implements TestWebTestQuestionService {

	
	@Resource
	private TestWebTestQuestionMapper testWebTestQuestionMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<TestWebTestQuestion> testWebTestQuestionMapper) {
		super.setBaseMapper(testWebTestQuestionMapper);
	}

	@Override
	public Page<TestWebTestQuestion> getTestpaper(TestWebTestQuestion tp,
			int p, int pageSize) {
		PageHelper.startPage(p,pageSize);
		
		return testWebTestQuestionMapper.getTestpaper(tp);
	}

	@Override
	public void batchAdd(List<TestWebTestQuestion> list) {
		testWebTestQuestionMapper.batchAdd(list);
	}







}
