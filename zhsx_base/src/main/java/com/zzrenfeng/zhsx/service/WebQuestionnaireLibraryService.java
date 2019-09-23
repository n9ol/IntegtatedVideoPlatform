package com.zzrenfeng.zhsx.service;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.WebQuestionnaireLibrary;

public interface WebQuestionnaireLibraryService  extends BaseService<WebQuestionnaireLibrary>{

	void delBatchLibrary(List<String> ids);

}
