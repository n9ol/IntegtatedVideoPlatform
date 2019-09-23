package com.zzrenfeng.zhsx.service;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.WebQuestionnaireQuestion;

public interface WebQuestionnaireQuestionService extends BaseService<WebQuestionnaireQuestion>{

	void saveBatBylId(List<String> lidslist,String qid,String userId);

	void deleteByCondition(WebQuestionnaireQuestion question);

	void delBatchQuestion(List<String> ids);

}
