package com.zzrenfeng.zhsx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TestWebQuestion;
import com.zzrenfeng.zhsx.service.TestWebQuestionService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.TestWebQuestionMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-07-05 15:24:05
 * @see com.zzrenfeng.zhsx.service.impl.TestWebQuestion
 */

@Service
public class TestWebQuestionServiceImpl extends BaseServiceImpl<BaseMapper<TestWebQuestion>, TestWebQuestion> implements TestWebQuestionService {

	
	@Resource
	private TestWebQuestionMapper testWebQuestionMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<TestWebQuestion> testWebQuestionMapper) {
		super.setBaseMapper(testWebQuestionMapper);
	}

	@Override
	public void delBatchQuestion(List<String> ids) {
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("ids", ids);
		testWebQuestionMapper.delBatchQuestion(hm);
		
	}

	@Override
	public Page<TestWebQuestion> findTestQuestion(TestWebQuestion twq, int p,
			int pageSize) {
		PageHelper.startPage(p,pageSize);
		return testWebQuestionMapper.findTestQuestion(twq);
	}

	@Override
	public List<TestWebQuestion> findRandom(TestWebQuestion twq) {
		List<TestWebQuestion> list = testWebQuestionMapper.findRandom(twq);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}







}
