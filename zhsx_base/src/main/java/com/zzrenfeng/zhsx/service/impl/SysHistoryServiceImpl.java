package com.zzrenfeng.zhsx.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.SysHistoryMapper;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.service.SysHistoryService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.impl.SysHistory
 */

@Service
public class SysHistoryServiceImpl extends BaseServiceImpl<BaseMapper<SysHistory>, SysHistory> implements SysHistoryService {

	@Resource
	private SysHistoryMapper sysHistoryMapper;
	
	@Resource
	public void setBaseMapper(BaseMapper<SysHistory> sysHistoryMapper) {
		super.setBaseMapper(sysHistoryMapper);
	}

	@Override
	public void deleteByPub(String pubFlag, String pubType, String pubId,String currUserId) {
		SysHistory sysHistory = new SysHistory();
		sysHistory.setUserId(currUserId);
		sysHistory.setPubFlag(pubFlag);
		sysHistory.setPubType(pubType);
		sysHistory.setPubId(pubId);
		sysHistoryMapper.deleteByPub(sysHistory);
	}

	@Override
	public Page<Map<String, String>> findCollectRecord(Map<String, Object> m, int p,
			int pageSize) {
		PageHelper.startPage(p, pageSize);
		return sysHistoryMapper.findCollectRecord(m);
	}

	@Override
	public Page<Map<String, String>> findWatchRecord(Map<String, Object> m,
			int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return sysHistoryMapper.findWatchRecord(m);
	}

	@Override
	public String getExp(SysHistory sysHistory) {
		String expsum = sysHistoryMapper.getExp(sysHistory);
		if(expsum==null){expsum = "0";}
		return expsum;
	}

	@Override
	public Date getTeacherConTime(String userId) {
		return sysHistoryMapper.getTeacherConTime(userId);
	}
	
	
	
}
