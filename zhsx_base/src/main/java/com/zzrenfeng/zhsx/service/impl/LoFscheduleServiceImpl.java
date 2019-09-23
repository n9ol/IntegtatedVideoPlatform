package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.LoFscheduleMapper;
import com.zzrenfeng.zhsx.model.LoFschedule;
import com.zzrenfeng.zhsx.service.LoFscheduleService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-05-22 15:17:49
 * @see com.zzrenfeng.zhsx.service.impl.LoFschedule
 */

@Service
public class LoFscheduleServiceImpl extends BaseServiceImpl<BaseMapper<LoFschedule>, LoFschedule> implements LoFscheduleService {

	
	@Resource
	private LoFscheduleMapper loFscheduleMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<LoFschedule> loFscheduleMapper) {
		super.setBaseMapper(loFscheduleMapper);
	}

	@Override
	public void insertBatch(List<LoFschedule> slist) {
		loFscheduleMapper.insertBatch(slist);
	}

	@Override
	public int deleteByzId(String zId) {
		return loFscheduleMapper.deleteByzId(zId);
	}









}
