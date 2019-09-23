package com.zzrenfeng.zhsx.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.LoPgCourMapper;
import com.zzrenfeng.zhsx.model.LoPgCour;
import com.zzrenfeng.zhsx.service.LoPgCourService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-08-26 16:55:19
 * @see com.zzrenfeng.zhsx.service.impl.LoPgCour
 */

@Service
public class LoPgCourServiceImpl extends BaseServiceImpl<BaseMapper<LoPgCour>, LoPgCour> implements LoPgCourService {

	
	@Resource
	private LoPgCourMapper loPgCourMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<LoPgCour> loPgCourMapper) {
		super.setBaseMapper(loPgCourMapper);
	}







}
