package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.WebQuestionnaireMapper;
import com.zzrenfeng.zhsx.model.WebQuestionnaire;
import com.zzrenfeng.zhsx.service.WebQuestionnaireService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

@Service
public class WebQuestionnaireServiceImpl  extends BaseServiceImpl<BaseMapper<WebQuestionnaire>, WebQuestionnaire> implements
		WebQuestionnaireService {
	
	@Resource
	private WebQuestionnaireMapper webQuestionnaireMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<WebQuestionnaire> webQuestionnaireMapper) {
		super.setBaseMapper(webQuestionnaireMapper);
	}

	@Override
	public void updateState(WebQuestionnaire wq) {
		webQuestionnaireMapper.updateState(wq);
		
	}
}
