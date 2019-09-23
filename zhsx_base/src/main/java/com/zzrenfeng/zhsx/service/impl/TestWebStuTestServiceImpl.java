package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TestWebStuTest;
import com.zzrenfeng.zhsx.model.TestWebStuTestQuestion;
import com.zzrenfeng.zhsx.model.TestWebStuTestQuestionAnswer;
import com.zzrenfeng.zhsx.service.TestWebStuTestService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.TestWebStuTestMapper;
import com.zzrenfeng.zhsx.mapper.TestWebStuTestQuestionAnswerMapper;
import com.zzrenfeng.zhsx.mapper.TestWebStuTestQuestionMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-13 18:01:31
 * @see com.zzrenfeng.zhsx.service.impl.TestWebStuTest
 */

@Service
public class TestWebStuTestServiceImpl extends BaseServiceImpl<BaseMapper<TestWebStuTest>, TestWebStuTest> implements TestWebStuTestService {

	
	@Resource
	private TestWebStuTestMapper testWebStuTestMapper;
	@Resource
	private TestWebStuTestQuestionMapper testWebStuTestQuestionMapper;
	@Resource
	private TestWebStuTestQuestionAnswerMapper testWebStuTestQuestionAnswerMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<TestWebStuTest> testWebStuTestMapper) {
		super.setBaseMapper(testWebStuTestMapper);
	}

	@Override
	public List<TestWebStuTest> histogram(TestWebStuTest twst) {
		List<TestWebStuTest> list = testWebStuTestMapper.histogram(twst);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public void addStuTest(TestWebStuTest twst
			
			) {
		testWebStuTestMapper.insert(twst);
		
	}







}
