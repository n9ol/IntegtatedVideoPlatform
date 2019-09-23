package com.zzrenfeng.zhsx.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.AnswerQuestionsCollectedMapper;
import com.zzrenfeng.zhsx.model.AnswerQuestionsCollected;
import com.zzrenfeng.zhsx.service.AnswerQuestionsCollectedService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.util.DateUtil;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-24 16:57:58
 * @see com.zzrenfeng.zhsx.service.impl.AnswerQuestionsCollected
 */

@Service
public class AnswerQuestionsCollectedServiceImpl extends BaseServiceImpl<BaseMapper<AnswerQuestionsCollected>, AnswerQuestionsCollected> implements AnswerQuestionsCollectedService {

	
	@Resource
	private AnswerQuestionsCollectedMapper answerQuestionsCollectedMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<AnswerQuestionsCollected> answerQuestionsCollectedMapper) {
		super.setBaseMapper(answerQuestionsCollectedMapper);
	}

	@Override
	public int getPeriodTime(String bdate) throws ParseException {
		int i = 0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		String dayDate = sdf1.format(new Date());
		Date bdate1 = sdf.parse(dayDate+" "+bdate);
		int s = DateUtil.getminutesBetween(bdate1,new Date());	
		if(s<=10){
			i = 1;
		}else if(s<=20){
			i = 2;
		}else if(s<=30){
			i = 3;
		}else{
			i = 4;
		}
		return i;
	}

	@Override
	public void recordAnswerQuestionsCollected(AnswerQuestionsCollected answerQuestionsCollected) {
		answerQuestionsCollectedMapper.insert(answerQuestionsCollected);
	}

	@Override
	public List<AnswerQuestionsCollected> findNumByzid(String zid,String classid) {
		AnswerQuestionsCollected answerQuestionsCollected = new AnswerQuestionsCollected();
		answerQuestionsCollected.setZid(zid);
		answerQuestionsCollected.setClassid(classid);
		return answerQuestionsCollectedMapper.findNumByzid(answerQuestionsCollected);
	}





}
