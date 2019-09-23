package com.zzrenfeng.zhsx.service.impl;

import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.SysLogMapper;
import com.zzrenfeng.zhsx.model.SysLog;
import com.zzrenfeng.zhsx.service.SysLogService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.impl.SysLog
 */

@Service
public class SysLogServiceImpl extends BaseServiceImpl<BaseMapper<SysLog>, SysLog> implements SysLogService {

	
	@Resource
	private SysLogMapper sysLogMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<SysLog> sysLogMapper) {
		super.setBaseMapper(sysLogMapper);
	}

	@Override
	public int recordLog(SysLog sysLog) {
		return sysLogMapper.insert(sysLog);
	}

	@Override
	public int emptyLog() {
		return sysLogMapper.emptyLog();
	}

	@Override
	public void delBatchLog(String[] del_id) {
		List<String> ids=Arrays.asList(del_id);
		sysLogMapper.delBatchLog(ids);
	}







}
