package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TpPracticeSchedule;
import com.zzrenfeng.zhsx.service.TpPracticeScheduleService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.TpPracticeScheduleMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-09-20 17:21:34
 * @see com.zzrenfeng.zhsx.service.impl.TpPracticeSchedule
 */

@Service
public class TpPracticeScheduleServiceImpl extends BaseServiceImpl<BaseMapper<TpPracticeSchedule>, TpPracticeSchedule> implements TpPracticeScheduleService {

	
	@Resource
	private TpPracticeScheduleMapper tpPracticeScheduleMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<TpPracticeSchedule> tpPracticeScheduleMapper) {
		super.setBaseMapper(tpPracticeScheduleMapper);
	}

	@Override
	public int insert(TpPracticeSchedule tps) {
		
		TpPracticeSchedule t = new TpPracticeSchedule();
		t.setSid(tps.getSid());
		List<TpPracticeSchedule> t0 =tpPracticeScheduleMapper.findSelective(t);
		if(t0!=null){
			for (TpPracticeSchedule tpPracticeSchedule : t0) {
				tpPracticeScheduleMapper.deleteByPrimaryKey(tpPracticeSchedule.getId());
			}
		}
		tpPracticeScheduleMapper.insert(tps);
		return 0;
	}







}
