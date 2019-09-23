package com.zzrenfeng.zhsx.service;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.WebQuestionnaire;

public interface WebQuestionnaireService  extends BaseService<WebQuestionnaire>{

	void updateState(WebQuestionnaire wq);

}
