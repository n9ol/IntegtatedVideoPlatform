package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.LoClassTimeMapper;
import com.zzrenfeng.zhsx.model.LoClassTime;
import com.zzrenfeng.zhsx.service.LoClassTimeService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:39
 * @see com.zzrenfeng.zhsx.service.impl.LoClassTime
 */

@Service
public class LoClassTimeServiceImpl extends BaseServiceImpl<BaseMapper<LoClassTime>, LoClassTime> implements LoClassTimeService {

	
	@Resource
	private LoClassTimeMapper loClassTimeMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<LoClassTime> loClassTimeMapper) {
		super.setBaseMapper(loClassTimeMapper);
	}
	
	
	@Override
	public void insertBatch(List<LoClassTime> loClassTimeList) {
		loClassTimeMapper.insertBatch(loClassTimeList);
	}







}
