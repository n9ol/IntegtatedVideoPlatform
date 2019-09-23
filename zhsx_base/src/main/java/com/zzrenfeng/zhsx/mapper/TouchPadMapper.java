package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TouchPad;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-06-25 11:42:53
 * @see com.zzrenfeng.zhsx.service.TouchPad
 */

public interface TouchPadMapper extends BaseMapper<TouchPad>{

	List<String> findHandWrittenBoardCodeByClassCode(TouchPad touchPad);
	int delBatch(Map<String, Object> schoolMap);
	






}

