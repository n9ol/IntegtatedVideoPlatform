package com.zzrenfeng.zhsx.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.LoPgGroupUserMapper;
import com.zzrenfeng.zhsx.model.LoPgGroupUser;
import com.zzrenfeng.zhsx.service.LoPgGroupUserService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-29 16:52:45
 * @see com.zzrenfeng.zhsx.service.impl.LoPgGroupUser
 */

@Service
public class LoPgGroupUserServiceImpl extends BaseServiceImpl<BaseMapper<LoPgGroupUser>, LoPgGroupUser>
		implements LoPgGroupUserService {

	@Resource
	private LoPgGroupUserMapper loPgGroupUserMapper;

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<LoPgGroupUser> loPgGroupUserMapper) {
		super.setBaseMapper(loPgGroupUserMapper);
	}

	@Override
	public void batchInster(String pgGroupId, String[] userIds) {
		Map<String, Object> hm = new HashMap<>();
		hm.put("pgGroupId", pgGroupId);
		hm.put("userIds", userIds);
		loPgGroupUserMapper.batchInster(hm);
	}

	@Override
	public void batchDel(String[] ids) {
		loPgGroupUserMapper.batchDel(ids);
	}

	@Override
	public int isPgAuthority(String userId, String loscheduleId) {
		Map<String, Object> hm = new HashMap<>();
		hm.put("userId", userId);
		hm.put("loscheduleId", loscheduleId);
		return loPgGroupUserMapper.isPgAuthority(hm);
	}

}
