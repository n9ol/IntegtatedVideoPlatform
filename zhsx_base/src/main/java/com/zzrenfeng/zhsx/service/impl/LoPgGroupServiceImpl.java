package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.LoPgGroupMapper;
import com.zzrenfeng.zhsx.model.LoPgGroup;
import com.zzrenfeng.zhsx.service.LoPgGroupService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-29 16:52:30
 * @see com.zzrenfeng.zhsx.service.impl.LoPgGroup
 */

@Service
public class LoPgGroupServiceImpl extends BaseServiceImpl<BaseMapper<LoPgGroup>, LoPgGroup>
		implements LoPgGroupService {

	@Resource
	private LoPgGroupMapper loPgGroupMapper;

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<LoPgGroup> loPgGroupMapper) {
		super.setBaseMapper(loPgGroupMapper);
	}

	@Override
	public void batchDelPgGroup(String[] ids) {
		loPgGroupMapper.batchDelPgGroup(ids);
	}

}
