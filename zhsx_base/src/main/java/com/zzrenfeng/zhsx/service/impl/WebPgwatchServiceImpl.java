package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.WebPgwatchMapper;
import com.zzrenfeng.zhsx.model.WebPgwatch;
import com.zzrenfeng.zhsx.service.WebPgwatchService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.impl.WebPgwatch
 */

@Service
public class WebPgwatchServiceImpl extends BaseServiceImpl<BaseMapper<WebPgwatch>, WebPgwatch> implements WebPgwatchService {

	
	@Resource
	private WebPgwatchMapper webPgwatchMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<WebPgwatch> webPgwatchMapper) {
		super.setBaseMapper(webPgwatchMapper);
	}







}
