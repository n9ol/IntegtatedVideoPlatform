package com.zzrenfeng.zhsx.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.LoPgGroupScheduleMapper;
import com.zzrenfeng.zhsx.model.LoPgGroupSchedule;
import com.zzrenfeng.zhsx.service.LoPgGroupScheduleService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-29 16:53:02
 * @see com.zzrenfeng.zhsx.service.impl.LoPgGroupSchedule
 */

@Service
public class LoPgGroupScheduleServiceImpl extends BaseServiceImpl<BaseMapper<LoPgGroupSchedule>, LoPgGroupSchedule>
		implements LoPgGroupScheduleService {

	@Resource
	private LoPgGroupScheduleMapper loPgGroupScheduleMapper;

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<LoPgGroupSchedule> loPgGroupScheduleMapper) {
		super.setBaseMapper(loPgGroupScheduleMapper);
	}

	@Override
	public void batchInster(String loscheduleId, String[] pgGroupIds) {
		Map<String, Object> hm = new HashMap<>();
		hm.put("loscheduleId", loscheduleId);
		hm.put("pgGroupIds", pgGroupIds);
		loPgGroupScheduleMapper.batchInster(hm);
	}

}
