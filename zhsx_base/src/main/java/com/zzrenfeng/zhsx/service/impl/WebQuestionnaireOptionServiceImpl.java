package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.WebQuestionnaireOptionMapper;
import com.zzrenfeng.zhsx.model.WebQuestionnaireOption;
import com.zzrenfeng.zhsx.service.WebQuestionnaireOptionService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;


@Service
public class WebQuestionnaireOptionServiceImpl
		extends
		BaseServiceImpl<BaseMapper<WebQuestionnaireOption>, WebQuestionnaireOption>
		implements WebQuestionnaireOptionService {
	@Resource
	private WebQuestionnaireOptionMapper webQuestionnaireOptionMapper;

	@Resource
	public void setBaseMapper(
			BaseMapper<WebQuestionnaireOption> webQuestionnaireOptionMapper) {
		super.setBaseMapper(webQuestionnaireOptionMapper);
	}
}