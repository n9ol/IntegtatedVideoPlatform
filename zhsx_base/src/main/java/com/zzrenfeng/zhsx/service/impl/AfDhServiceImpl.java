package com.zzrenfeng.zhsx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.AfDh;
import com.zzrenfeng.zhsx.service.AfDhService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.AfDhMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-06-26 15:34:18
 * @see com.zzrenfeng.zhsx.service.impl.AfDh
 */

@Service
public class AfDhServiceImpl extends BaseServiceImpl<BaseMapper<AfDh>, AfDh> implements AfDhService {

	
	@Resource
	private AfDhMapper afDhMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<AfDh> afDhMapper) {
		super.setBaseMapper(afDhMapper);
	}

	







}
