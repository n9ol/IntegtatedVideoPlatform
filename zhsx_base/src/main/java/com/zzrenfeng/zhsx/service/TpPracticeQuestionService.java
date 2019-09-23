package com.zzrenfeng.zhsx.service;


import java.util.List;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.TpPracticeQuestion;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-09-20 10:56:04
 * @see com.zzrenfeng.zhsx.service.TpPracticeQuestion
 */
public interface TpPracticeQuestionService extends BaseService<TpPracticeQuestion> {

	void saveBatBylId(List<String> lidslist, String qid, String userId);








}
