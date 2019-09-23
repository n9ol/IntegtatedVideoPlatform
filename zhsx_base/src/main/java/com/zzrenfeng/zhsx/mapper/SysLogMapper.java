package com.zzrenfeng.zhsx.mapper;

import java.util.List;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.SysLog;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:40
 * @see com.zzrenfeng.zhsx.service.SysLog
 */

public interface SysLogMapper extends BaseMapper<SysLog>{


	 /**
	  * 清空日志
	  * @return
	  */
	 int emptyLog();
	 
	 /**
	  * 批量删除日志
	  * @param ids
	  */
	 void delBatchLog(List<String> ids);

}

