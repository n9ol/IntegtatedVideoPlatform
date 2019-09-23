package com.zzrenfeng.zhsx.service;


import java.text.ParseException;
import java.util.Map;

import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.LoTermTime;

/**
 * TODO 在此加入类描述
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version  2017-04-27 11:33:39
 * @see com.zzrenfeng.zhsx.service.LoTermTime
 */
public interface LoTermTimeService extends BaseService<LoTermTime> {


	/**
	 * 获得当前的学期时间
	 * @param SchoolId
	 * @return
	 */
	LoTermTime getCurrTermTime(String schoolId);
	
	/**
	 * 获得当前时间是第几周,总周数,TermTimeId
	 * @param SchoolId
	 * @return
	 */
	Map<String, Object> getCurrTermTimeWeeks(String schoolId) throws ParseException;


}
