package com.zzrenfeng.zhsx.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.TpPracticeResultMapper;
import com.zzrenfeng.zhsx.model.TpPracticeResult;
import com.zzrenfeng.zhsx.service.TpPracticeResultService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-09-21 10:10:23
 * @see com.zzrenfeng.zhsx.service.impl.TpPracticeResult
 */

@Service
public class TpPracticeResultServiceImpl extends
		BaseServiceImpl<BaseMapper<TpPracticeResult>, TpPracticeResult>
		implements TpPracticeResultService {

	@Resource
	private TpPracticeResultMapper tpPracticeResultMapper;

	@Resource
	public void setBaseMapper(
			BaseMapper<TpPracticeResult> tpPracticeResultMapper) {
		super.setBaseMapper(tpPracticeResultMapper);
	}

	@Override
	public Page<Map<String, Object>> findPageClassRecord(Map<String, Object> t,
			int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return tpPracticeResultMapper.findPageClassRecord(t);

	}
	@Override
	public Page<Map<String, Object>> findPageClassRecordPad(Map<String, Object> t, int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return tpPracticeResultMapper.findPageClassRecordPad(t);
	}
	@Override
	public int putInData(List<LinkedHashMap<String, Object>> ans) {

		for (Map<String, Object> map : ans) {
			Object questionId = map.get("questionId");
			Object userAnswer = map.get("answer");
			Object userName = map.get("userName");
			Object boardId = map.get("boardId");
			Object classId = map.get("classId");
			Object bak1 = map.get("scheduleId");
			Object classType = map.get("classType");
			TpPracticeResult tpr = new TpPracticeResult();
			tpr.setUserName(userName + "");
			tpr.setBoardId(boardId + "");
			tpr.setBak(classId + "");
			tpr.setBak1(bak1 + "");
			tpr.setClassType(classType+"");
			tpr.setCreateTime(new Date());

			Object type = map.get("type");
			if ("D".equals(type)) { // 主观题
				tpr.setRightAnswer(userAnswer + "");
				String[] qids = (String.valueOf(questionId)).split(",");
				for (int i = 0; qids != null && i < qids.length; i++) {
					tpr.setQuestionId(qids[i]);
					tpPracticeResultMapper.insert(tpr);
				}
			} else if("C".equals(type)){//判断
				tpr.setQuestionId(questionId + "");
				if("正确".equals(userAnswer))
					userAnswer="A";
				if("错误".equals(userAnswer))
					userAnswer="B";
				tpr.setUserAnswer(userAnswer + "");
				tpPracticeResultMapper.insert(tpr);
			}else {
				tpr.setQuestionId(questionId + "");
				tpr.setUserAnswer(userAnswer + "");
				tpPracticeResultMapper.insert(tpr);
			}
		}

		return 0;
	}

	@Override
	public Page<Map<String, Object>> findDetailedRecord(Map<String, Object> t,
			int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return tpPracticeResultMapper.findDetailedRecord(t);
	}

	@Override
	public List<Map<String, Object>> findAnswerStatistics(Map<String, Object> t) {
		// TODO Auto-generated method stub
		return tpPracticeResultMapper.findAnswerStatistics(t);
	}

	@Override
	public Page<Map<String, Object>> findSutdentRecord(Map<String, Object> t,
			Integer p, int i) {
		PageHelper.startPage(p, i);
		return tpPracticeResultMapper.findSutdentRecord(t);
	}

	@Override
	public List<Map<String, Object>> findSutdentAnswerInfo(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return tpPracticeResultMapper.findSutdentAnswerInfo(map);
	}



}
