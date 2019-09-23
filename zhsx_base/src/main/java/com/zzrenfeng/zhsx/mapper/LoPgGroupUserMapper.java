package com.zzrenfeng.zhsx.mapper;

import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.LoPgGroupUser;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-12-29 16:52:45
 * @see com.zzrenfeng.zhsx.service.LoPgGroupUser
 */

public interface LoPgGroupUserMapper extends BaseMapper<LoPgGroupUser> {

	void batchInster(Map<String, Object> hm);

	void batchDel(String[] ids);

	int isPgAuthority(Map<String, Object> hm);
}
