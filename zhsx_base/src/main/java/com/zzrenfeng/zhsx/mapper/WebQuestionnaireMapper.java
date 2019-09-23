package com.zzrenfeng.zhsx.mapper;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.WebQuestionnaire;

public interface WebQuestionnaireMapper extends BaseMapper <WebQuestionnaire>{

	void updateState(WebQuestionnaire wq);

}
