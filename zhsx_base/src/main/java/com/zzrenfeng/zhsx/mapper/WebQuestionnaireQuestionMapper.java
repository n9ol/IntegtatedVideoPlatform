package com.zzrenfeng.zhsx.mapper;

import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.WebQuestionnaireQuestion;

public interface WebQuestionnaireQuestionMapper extends BaseMapper <WebQuestionnaireQuestion>{

	void deleteByCondition(WebQuestionnaireQuestion question);

	void delBatchQuestion(Map<String, Object> hm);

}
