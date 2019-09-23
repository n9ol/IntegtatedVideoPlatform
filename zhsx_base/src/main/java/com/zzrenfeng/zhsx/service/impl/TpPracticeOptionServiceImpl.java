package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TpPracticeOption;
import com.zzrenfeng.zhsx.service.TpPracticeOptionService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.TpPracticeOptionMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-09-19 16:53:27
 * @see com.zzrenfeng.zhsx.service.impl.TpPracticeOption
 */

@Service
public class TpPracticeOptionServiceImpl extends BaseServiceImpl<BaseMapper<TpPracticeOption>, TpPracticeOption> implements TpPracticeOptionService {

	
	@Resource
	private TpPracticeOptionMapper tpPracticeOptionMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<TpPracticeOption> tpPracticeOptionMapper) {
		super.setBaseMapper(tpPracticeOptionMapper);
	}

	@Override
	public int updateBySelective(TpPracticeOption wq) {
		TpPracticeOption t = new TpPracticeOption();
		t.setQid(wq.getQid());
		List<TpPracticeOption> list = tpPracticeOptionMapper.findSelective(t);
		
		for (TpPracticeOption tpPracticeOption : list) {
			if(tpPracticeOption.getId().equals(wq.getId())){
				tpPracticeOption.setIsRight("Y");
			}else{
				tpPracticeOption.setIsRight("N");
			}
			tpPracticeOptionMapper.updateByPrimaryKeySelective(tpPracticeOption);
		}
		return 0;
	}







}
