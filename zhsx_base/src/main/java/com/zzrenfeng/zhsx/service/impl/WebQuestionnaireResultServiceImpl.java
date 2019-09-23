package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.WebQuestionnaireResultMapper;
import com.zzrenfeng.zhsx.model.WebPj;
import com.zzrenfeng.zhsx.model.WebQuestionnaireResult;
import com.zzrenfeng.zhsx.service.WebQuestionnaireResultService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;



@Service
public class WebQuestionnaireResultServiceImpl  extends BaseServiceImpl<BaseMapper<WebQuestionnaireResult>, WebQuestionnaireResult> implements
		WebQuestionnaireResultService {
	
	@Resource
	private WebQuestionnaireResultMapper webQuestionnaireResultMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<WebQuestionnaireResult> webQuestionnaireResultMapper) {
		super.setBaseMapper(webQuestionnaireResultMapper);
	}
}
