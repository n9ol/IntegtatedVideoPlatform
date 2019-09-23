package com.zzrenfeng.zhsx.mapper.eclassbrand.sys;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.jdbc.DataSource;
import com.zzrenfeng.zhsx.model.eclassbrand.sys.ESysUser;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-01-24 17:35:07
 * @see com.zzrenfeng.ESysUser.model.sys.SysUser
 */
@DataSource(value = "2")
public interface ESysUserMapper extends BaseMapper<ESysUser> {

	ESysUser findByUserCode(String userCode);

}
