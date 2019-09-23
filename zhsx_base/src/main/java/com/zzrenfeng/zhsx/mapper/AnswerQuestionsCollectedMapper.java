package com.zzrenfeng.zhsx.mapper;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.AnswerQuestionsCollected;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-24 16:57:58
 * @see com.zzrenfeng.zhsx.service.AnswerQuestionsCollected
 */

public interface AnswerQuestionsCollectedMapper extends BaseMapper<AnswerQuestionsCollected>{


	/**
	 * 获得时间段内回答次数
	 * @param answerQuestionsCollected
	 * @return
	 */
	List<AnswerQuestionsCollected> findNumByzid(AnswerQuestionsCollected answerQuestionsCollected);



}

