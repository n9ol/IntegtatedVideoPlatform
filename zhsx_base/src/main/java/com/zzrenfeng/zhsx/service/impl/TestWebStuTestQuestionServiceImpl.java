package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TestWebStuTestQuestion;
import com.zzrenfeng.zhsx.service.TestWebStuTestQuestionService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.TestWebStuTestQuestionMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-27 17:28:49
 * @see com.zzrenfeng.zhsx.service.impl.TestWebStuTestQuestion
 */

@Service
public class TestWebStuTestQuestionServiceImpl extends BaseServiceImpl<BaseMapper<TestWebStuTestQuestion>, TestWebStuTestQuestion> implements TestWebStuTestQuestionService {

	
	@Resource
	private TestWebStuTestQuestionMapper testWebStuTestQuestionMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<TestWebStuTestQuestion> testWebStuTestQuestionMapper) {
		super.setBaseMapper(testWebStuTestQuestionMapper);
	}

	@Override
	public void addQuestion(TestWebStuTestQuestion tq) {
		
		testWebStuTestQuestionMapper.addQuestion(tq);
	}

	@Override
	public TestWebStuTestQuestion findOneByqId(TestWebStuTestQuestion q) {
	     return	testWebStuTestQuestionMapper.findOneByqId(q);
		
	}

	







}
