package com.zzrenfeng.zhsx.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.TpPracticeResult;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2018-09-21 10:10:23
 * @see com.zzrenfeng.zhsx.service.TpPracticeResult
 */

public interface TpPracticeResultMapper extends BaseMapper<TpPracticeResult>{


	/**
	 * 查询使用过手写板答题的课程信息
	 * @param t
	 * @return
	 */
	Page<Map<String, Object>> findPageClassRecord(Map<String, Object> t);
	/**
	 * 查询使用过手写板答题的课程信息（手写板端）
	 * @param t
	 * @return
	 */
	Page<Map<String, Object>> findPageClassRecordPad(Map<String, Object> t);
	Page<Map<String, Object>> findDetailedRecord(Map<String, Object> t);
	
	List<Map<String, Object>> findAnswerStatistics(Map<String, Object> t);

	Page<Map<String, Object>> findSutdentRecord(Map<String, Object> t);

	List<Map<String, Object>> findSutdentAnswerInfo(Map<String, Object> map);

	
	


}

