package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.OffLineRecordVideoMapper;
import com.zzrenfeng.zhsx.model.OffLineRecordVideo;
import com.zzrenfeng.zhsx.service.OffLineRecordVideoService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-04-18 14:33:31
 * @see com.zzrenfeng.zhsx.service.impl.OffLineRecordVideo
 */

@Service
public class OffLineRecordVideoServiceImpl extends BaseServiceImpl<BaseMapper<OffLineRecordVideo>, OffLineRecordVideo>
		implements OffLineRecordVideoService {

	@Resource
	private OffLineRecordVideoMapper offLineRecordVideoMapper;

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<OffLineRecordVideo> offLineRecordVideoMapper) {
		super.setBaseMapper(offLineRecordVideoMapper);
	}

	@Override
	public int tInsert(OffLineRecordVideo offLineRecordVideo) {
		return offLineRecordVideoMapper.insert(offLineRecordVideo);
	}

}
