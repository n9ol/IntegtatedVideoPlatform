package com.zzrenfeng.zhsx.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.LoTermTimeMapper;
import com.zzrenfeng.zhsx.model.LoTermTime;
import com.zzrenfeng.zhsx.service.LoTermTimeService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.util.DateUtil;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:39
 * @see com.zzrenfeng.zhsx.service.impl.LoTermTime
 */

@Service
public class LoTermTimeServiceImpl extends BaseServiceImpl<BaseMapper<LoTermTime>, LoTermTime>
		implements LoTermTimeService {

	@Resource
	private LoTermTimeMapper loTermTimeMapper;

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<LoTermTime> loTermTimeMapper) {
		super.setBaseMapper(loTermTimeMapper);
	}

	@Override
	public LoTermTime getCurrTermTime(String schoolId) {
		LoTermTime loTermTime = new LoTermTime();
		loTermTime.setCurrTime(new Date());
		loTermTime.setSchoolId(schoolId);
		List<LoTermTime> loTermTimes = loTermTimeMapper.findSelective(loTermTime);
		if (loTermTimes != null && loTermTimes.size() > 0) {
			loTermTime = loTermTimes.get(0);
		} else {
			loTermTime = null;
		}
		return loTermTime;
	}

	@Override
	public Map<String, Object> getCurrTermTimeWeeks(String schoolId) throws ParseException {
		Map<String, Object> hm = new HashMap<String, Object>();
		LoTermTime loTermTime = getCurrTermTime(schoolId);
		if (loTermTime != null) {
			hm.put("termTimeId", loTermTime.getId());
			hm.put("totalWeeks", loTermTime.getTotalWeeks());
			if (loTermTime.getFirstDay() == null) {
				hm.put("weeks", 0);
			} else {
				String dayTime = DateUtil.getStringDate(loTermTime.getFirstDay(), "yyyy-MM-dd");
				int e = DateUtil.getIntWeekDay();
				String MondayDate = DateUtil.getNextDay(1 - e, "yyyy-MM-dd");
				int i = DateUtil.getdaysBetween(dayTime, MondayDate) / 7 + 1;
				hm.put("weeks", i);
			}
		}
		return hm;
	}

}
