package com.zzrenfeng.zhsx.service;

import java.text.ParseException;
import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.AnswerQuestionsCollected;


/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-24 16:57:58
 * @see com.zzrenfeng.zhsx.service.AnswerQuestionsCollected
 */
public interface AnswerQuestionsCollectedService extends BaseService<AnswerQuestionsCollected> {




	/**
	 * 获得时间段
	 * @param smdate
	 * @param bdate
	 * @return
	 */
	int getPeriodTime(String bdate) throws ParseException;

	/**
	 * 更新数据库信息
	 * @param answerQuestionsCollected
	 */
	void recordAnswerQuestionsCollected(AnswerQuestionsCollected answerQuestionsCollected);
	
	/**
	 * 获得时间段内回答次数
	 * @param answerQuestionsCollected
	 * @return
	 */
	List<AnswerQuestionsCollected> findNumByzid(String zid,String classid);
}
