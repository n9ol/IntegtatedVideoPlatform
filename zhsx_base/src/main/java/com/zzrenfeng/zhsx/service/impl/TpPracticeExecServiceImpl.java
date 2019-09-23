package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TpPracticeExec;
import com.zzrenfeng.zhsx.service.TpPracticeExecService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.TpPracticeExecMapper;



/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-09-19 15:21:43
 * @see com.zzrenfeng.zhsx.service.impl.TpPracticeExec
 */

@Service
public class TpPracticeExecServiceImpl extends BaseServiceImpl<BaseMapper<TpPracticeExec>, TpPracticeExec> implements TpPracticeExecService {

	
	@Resource
	private TpPracticeExecMapper tpPracticeExecMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<TpPracticeExec> tpPracticeExecMapper) {
		super.setBaseMapper(tpPracticeExecMapper);
	}







}
