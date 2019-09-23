package com.zzrenfeng.zhsx.mapper;

import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.WebQuestionnaireLibrary;

public interface WebQuestionnaireLibraryMapper extends BaseMapper <WebQuestionnaireLibrary>{


	void delBatchLibrary(Map<String, Object> hm);

}
