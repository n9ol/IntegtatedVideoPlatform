package com.zzrenfeng.zhsx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.WebQuestionnaireLibraryMapper;
import com.zzrenfeng.zhsx.model.WebQuestionnaireLibrary;
import com.zzrenfeng.zhsx.service.WebQuestionnaireLibraryService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

@Service
public class WebQuestionnaireLibraryServiceImpl
		extends
		BaseServiceImpl<BaseMapper<WebQuestionnaireLibrary>, WebQuestionnaireLibrary>
		implements WebQuestionnaireLibraryService {
	@Resource
	private WebQuestionnaireLibraryMapper webQuestionnaireLibraryMapper;

	@Resource
	public void setBaseMapper(
			BaseMapper<WebQuestionnaireLibrary> webQuestionnaireLibraryMapper) {
		super.setBaseMapper(webQuestionnaireLibraryMapper);
	}

	@Override
	public void delBatchLibrary(List<String> ids) {
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("ids", ids);
		webQuestionnaireLibraryMapper.delBatchLibrary(hm);
	}
}
