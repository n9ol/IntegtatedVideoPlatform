package com.zzrenfeng.zhsx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TestWebQuestionAnswer;
import com.zzrenfeng.zhsx.service.TestWebQuestionAnswerService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.TestWebQuestionAnswerMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-12 09:51:44
 * @see com.zzrenfeng.zhsx.service.impl.TestWebQuestionAnswer
 */

@Service
public class TestWebQuestionAnswerServiceImpl extends BaseServiceImpl<BaseMapper<TestWebQuestionAnswer>, TestWebQuestionAnswer> implements TestWebQuestionAnswerService {

	
	@Resource
	private TestWebQuestionAnswerMapper testWebQuestionAnswerMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<TestWebQuestionAnswer> testWebQuestionAnswerMapper) {
		super.setBaseMapper(testWebQuestionAnswerMapper);
	}

	@Override
	public void delByQuestionId(String questionId) {
		
		testWebQuestionAnswerMapper.delByQuestionId(questionId);
	}

	@Override
	public void delBatch(List<String> ids) {
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("ids", ids);
		testWebQuestionAnswerMapper.delBatch(hm);
		
	}

	@Override
	public void updateBatch(List<TestWebQuestionAnswer> list) {
		
		testWebQuestionAnswerMapper.updateBatch(list);
	}

	






}
