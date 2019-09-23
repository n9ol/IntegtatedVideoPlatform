package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TpPractice;
import com.zzrenfeng.zhsx.service.TpPracticeService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.TpPracticeMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-09-18 10:17:57
 * @see com.zzrenfeng.zhsx.service.impl.TpPractice
 */

@Service
public class TpPracticeServiceImpl extends BaseServiceImpl<BaseMapper<TpPractice>, TpPractice> implements TpPracticeService {

	
	@Resource
	private TpPracticeMapper tpPracticeMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<TpPractice> tpPracticeMapper) {
		super.setBaseMapper(tpPracticeMapper);
	}







}
