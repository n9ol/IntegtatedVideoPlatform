package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.WebClassDevice;
import com.zzrenfeng.zhsx.service.WebClassDeviceService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.mapper.WebClassDeviceMapper;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-09-04 17:10:22
 * @see com.zzrenfeng.zhsx.service.impl.WebClassDevice
 */

@Service
public class WebClassDeviceServiceImpl extends BaseServiceImpl<BaseMapper<WebClassDevice>, WebClassDevice> implements WebClassDeviceService {

	
	@Resource
	private WebClassDeviceMapper webClassDeviceMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<WebClassDevice> webClassDeviceMapper) {
		super.setBaseMapper(webClassDeviceMapper);
	}







}
