package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.PgPjinfoMapper;
import com.zzrenfeng.zhsx.model.PgPjinfo;
import com.zzrenfeng.zhsx.service.PgPjinfoService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:39
 * @see com.zzrenfeng.zhsx.service.impl.PgPjinfo
 */

@Service
public class PgPjinfoServiceImpl extends BaseServiceImpl<BaseMapper<PgPjinfo>, PgPjinfo> implements PgPjinfoService {

	
	@Resource
	private PgPjinfoMapper pgPjinfoMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<PgPjinfo> pgPjinfoMapper) {
		super.setBaseMapper(pgPjinfoMapper);
	}







}
