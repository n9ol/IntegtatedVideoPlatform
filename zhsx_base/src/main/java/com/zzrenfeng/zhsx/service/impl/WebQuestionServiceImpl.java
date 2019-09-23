package com.zzrenfeng.zhsx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.model.WebQuestion;
import com.zzrenfeng.zhsx.service.WebQuestionService;
import com.zzrenfeng.zhsx.mapper.WebAnswerMapper;
import com.zzrenfeng.zhsx.mapper.WebQuestionMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-06-02 14:33:25
 * @see com.zzrenfeng.zhsx.service.impl.WebQuestion
 */

@Service
public class WebQuestionServiceImpl extends BaseServiceImpl<BaseMapper<WebQuestion>, WebQuestion> implements WebQuestionService {

	
	@Resource
	private WebQuestionMapper webQuestionMapper;
	@Resource
	private WebAnswerMapper webAnswerMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<WebQuestion> webQuestionMapper) {
		super.setBaseMapper(webQuestionMapper);
	}

	@Override
	public void updateBatch(List<String> ids, String ifShow) {
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("ids", ids);
		hm.put("ifShow", ifShow);
		webQuestionMapper.updateBatch(hm);
		
	}

	@Override
	public void deleteBatch(List<String> ids) {
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("ids", ids);
		webQuestionMapper.deleteBatch(hm);
		webAnswerMapper.deleteAnswer(hm);
	}

	@Override
	public List<WebQuestion> getNew(WebQuestion w) {
		List<WebQuestion> list = webQuestionMapper.getNew(w);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}
	@Override
	public Page<WebQuestion> getNotAnswer(WebQuestion w, int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return webQuestionMapper.getNotAnswer(w);
	}

	@Override
	public List<WebQuestion> getBest(WebQuestion w) {
		List<WebQuestion>  list = webQuestionMapper.getBest(w);
		if(list!=null && list.size()>0){
			
			return list;
		}
		return null;
	}

	@Override
	public Page<WebQuestion> findNewQue(WebQuestion que, int p, int pageSize) {
		PageHelper.startPage(p,pageSize);
		return webQuestionMapper.findNewQue(que);
	}

	







}
