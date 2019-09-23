package com.zzrenfeng.zhsx.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.WebQuestionnaireLibraryMapper;
import com.zzrenfeng.zhsx.mapper.WebQuestionnaireQuestionMapper;
import com.zzrenfeng.zhsx.model.WebQuestionnaireLibrary;
import com.zzrenfeng.zhsx.model.WebQuestionnaireQuestion;
import com.zzrenfeng.zhsx.service.WebQuestionnaireQuestionService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;


@Service
public class WebQuestionnaireQuestionServiceImpl
		extends
		BaseServiceImpl<BaseMapper<WebQuestionnaireQuestion>, WebQuestionnaireQuestion>
		implements WebQuestionnaireQuestionService {
	@Resource
	private WebQuestionnaireQuestionMapper webQuestionnaireQuestionMapper;
	@Resource
	private WebQuestionnaireLibraryMapper webQuestionnaireLibraryMapper;
	@Resource
	public void setBaseMapper(
			BaseMapper<WebQuestionnaireQuestion> webQuestionnaireQuestionMapper) {
		super.setBaseMapper(webQuestionnaireQuestionMapper);
	}

	@Override
	public void saveBatBylId(List<String> lidslist,String qid,String userId) {
		for (int i = 0; lidslist!=null&&i < lidslist.size(); i++) {
		WebQuestionnaireLibrary wq=	webQuestionnaireLibraryMapper.selectByPrimaryKey(lidslist.get(i));
		WebQuestionnaireQuestion wqq = new WebQuestionnaireQuestion();
		wqq.setLid(wq.getId());
		wqq.setQid(qid);
		wqq.setQuestion(wq.getQuestion());
		wqq.setType(wq.getType());
		wqq.setIsShown(wq.getIsShown());
		wqq.setCreateId(userId);
		wqq.setCreateTime(new Date());
		wqq.setSort((i+1)+"");
		webQuestionnaireQuestionMapper.insert(wqq);
		}
		
	}

	@Override
	public void deleteByCondition(WebQuestionnaireQuestion question) {
		webQuestionnaireQuestionMapper.deleteByCondition(question);
		
	}

	@Override
	public void delBatchQuestion(List<String> ids) {
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("ids", ids);
		webQuestionnaireQuestionMapper.delBatchQuestion(hm);
	}
}