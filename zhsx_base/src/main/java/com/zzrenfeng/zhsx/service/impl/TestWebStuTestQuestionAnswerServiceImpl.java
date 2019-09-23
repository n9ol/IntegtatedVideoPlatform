package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TestWebStuTestQuestionAnswer;
import com.zzrenfeng.zhsx.service.TestWebStuTestQuestionAnswerService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.TestWebStuTestQuestionAnswerMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-14 10:44:27
 * @see com.zzrenfeng.zhsx.service.impl.TestWebStuTestQuestionAnswer
 */

@Service
public class TestWebStuTestQuestionAnswerServiceImpl extends BaseServiceImpl<BaseMapper<TestWebStuTestQuestionAnswer>, TestWebStuTestQuestionAnswer> implements TestWebStuTestQuestionAnswerService {

	
	@Resource
	private TestWebStuTestQuestionAnswerMapper testWebStuTestQuestionAnswerMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<TestWebStuTestQuestionAnswer> testWebStuTestQuestionAnswerMapper) {
		super.setBaseMapper(testWebStuTestQuestionAnswerMapper);
	}

	@Override
	public Page<TestWebStuTestQuestionAnswer> findTestDetail(
			TestWebStuTestQuestionAnswer twstq, int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return testWebStuTestQuestionAnswerMapper.findTestDetail(twstq);
	}

	@Override
	public Page<TestWebStuTestQuestionAnswer> FindMyErrors(
			TestWebStuTestQuestionAnswer twstq, int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return testWebStuTestQuestionAnswerMapper.FindMyErrors(twstq);
	}

	@Override
	public void insertBetch(List<TestWebStuTestQuestionAnswer> list) {
		
		testWebStuTestQuestionAnswerMapper.insertBetch(list);
	}







}
