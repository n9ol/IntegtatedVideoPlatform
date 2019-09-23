package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.PgPjdetailMapper;
import com.zzrenfeng.zhsx.model.PgPjdetail;
import com.zzrenfeng.zhsx.service.PgPjdetailService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:39
 * @see com.zzrenfeng.zhsx.service.impl.PgPjdetail
 */

@Service
public class PgPjdetailServiceImpl extends BaseServiceImpl<BaseMapper<PgPjdetail>, PgPjdetail> implements PgPjdetailService {

	
	@Resource
	private PgPjdetailMapper pgPjdetailMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<PgPjdetail> pgPjdetailMapper) {
		super.setBaseMapper(pgPjdetailMapper);
	}







}
