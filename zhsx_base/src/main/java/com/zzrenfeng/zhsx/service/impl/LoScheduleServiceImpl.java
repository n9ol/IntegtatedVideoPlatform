package com.zzrenfeng.zhsx.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.mapper.LoClassTimeMapper;
import com.zzrenfeng.zhsx.mapper.LoFscheduleMapper;
import com.zzrenfeng.zhsx.mapper.LoScheduleMapper;
import com.zzrenfeng.zhsx.mapper.LoTermTimeMapper;
import com.zzrenfeng.zhsx.model.LoFschedule;
import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.LoTermTime;
import com.zzrenfeng.zhsx.model.SysHistory;
import com.zzrenfeng.zhsx.model.User;
import com.zzrenfeng.zhsx.service.LoScheduleService;
import com.zzrenfeng.zhsx.service.SysHistoryService;
import com.zzrenfeng.zhsx.service.UserService;
import com.zzrenfeng.zhsx.service.impl.base.BaseServiceImpl;
import com.zzrenfeng.zhsx.util.DateUtil;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:39
 * @see com.zzrenfeng.zhsx.service.impl.LoSchedule
 */

@Service
public class LoScheduleServiceImpl extends BaseServiceImpl<BaseMapper<LoSchedule>, LoSchedule>
		implements LoScheduleService {

	@Resource
	private LoScheduleMapper loScheduleMapper;
	@Resource
	private LoFscheduleMapper loFscheduleMapper;
	@Resource
	private LoTermTimeMapper loTimeMapper;
	@Resource
	private LoClassTimeMapper loClassTimeMapper;
	@Resource
	private SysHistoryService sysHistoryService;
	@Resource
	private UserService userService;

	/**
	 * 发布一节直播获得经验值
	 */
	private String byWatchLosExp = "";

	@Override
	@Resource
	public void setBaseMapper(BaseMapper<LoSchedule> loScheduleMapper) {
		super.setBaseMapper(loScheduleMapper);
	}

	@Override
	public void insertScheduleInMany(LoSchedule loSchedule) {
		List<LoFschedule> loFschedules = loSchedule.getLoFscheduleList();
		if (loFschedules != null && loFschedules.size() > 0) {
			loSchedule.setJohn_num(loFschedules.size());
			loScheduleMapper.insert(loSchedule);
			for (LoFschedule loFschedule : loFschedules) {
				loFschedule.setZId(loSchedule.getId());
			}
			loFscheduleMapper.insertBatch(loFschedules);
		}
	}

	@Override
	public void delScheduleBatch(LoSchedule loSchedule) {
		loScheduleMapper.deleteScheduleSelective(loSchedule);
	}

	@Override
	public void uploadScheduleInMany(LoSchedule loSchedule) {
		loFscheduleMapper.deleteByzId(loSchedule.getId());
		List<LoFschedule> loFschedules = loSchedule.getLoFscheduleList();
		if (loFschedules != null) {
			loSchedule.setJohn_num(loFschedules.size());
		}
		loScheduleMapper.updateByPrimaryKeySelective(loSchedule);
		if (loFschedules != null && loFschedules.size() > 0) {
			for (LoFschedule loFschedule : loFschedules) {
				loFschedule.setZId(loSchedule.getId());
			}
			loFscheduleMapper.insertBatch(loFschedules);
		}
	}

	@Override
	public void insertScheduleBatch(LoSchedule loSchedule, Integer startWeek, Integer endWeek) {
		String type = loSchedule.getType();

		String termTimeId = loSchedule.getBak1();
		Integer dayofweek = loSchedule.getDayofweek();
		LoTermTime loTermTime = loTimeMapper.selectByPrimaryKey(termTimeId);
		String firstWeekDate = DateUtil.getNextDay(loTermTime.getFirstDay(), dayofweek - 1, "yyyy-MM-dd");

		// LoClassTime loc=new LoClassTime();
		// loc.setTermTimeId(termTimeId);
		// loc.setSectionofDay(loSchedule.getSectionofday());
		// List<LoClassTime> loClassTimes =
		// loClassTimeMapper.findSelective(loc);
		// String classTimeId=loClassTimes.get(0).getId();

		// 添加前删除重复
		LoSchedule loSchedule1 = new LoSchedule();
		loSchedule1.setSchoolId(loSchedule.getSchoolId());
		loSchedule1.setClassId(loSchedule.getClassId());
		loSchedule1.setDayofweek(loSchedule.getDayofweek());
		loSchedule1.setSectionofday(loSchedule.getSectionofday());
		loSchedule1.setStartWeek(startWeek);
		loSchedule1.setEndWeek(endWeek);
		delScheduleBatch(loSchedule1);

		if (type.equals("A") || type.equals("Z")) {
			List<LoSchedule> loSchedules = new ArrayList<>();
			for (int i = startWeek; i <= endWeek; i++) {
				String z_date = DateUtil.getNextDay(java.sql.Date.valueOf(firstWeekDate), (i - 1) * 7, "yyyy-MM-dd");
				LoSchedule ls = new LoSchedule();
				ls.setClassId(loSchedule.getClassId());
				ls.setSchoolId(loSchedule.getSchoolId());
				ls.setDayofweek(loSchedule.getDayofweek());
				ls.setSectionofday(loSchedule.getSectionofday());
				ls.setGradeId(loSchedule.getGradeId());
				ls.setSubjectId(loSchedule.getSubjectId());
				ls.setUserId(loSchedule.getUserId());
				ls.setCoverpath(loSchedule.getCoverpath());
				ls.setJohn_num(0);
				ls.setZ_date(java.sql.Date.valueOf(z_date));
				ls.setWeeks(i);
				ls.setType(loSchedule.getType());
				ls.setBak(loSchedule.getBak());
				ls.setBak1(termTimeId);
				ls.setBak2(LoSchedule.PG_BAK2_G);
				loSchedules.add(ls);
			}
			loScheduleMapper.insertScheduleBatch(loSchedules);
		}
		List<LoFschedule> loFschedules = loSchedule.getLoFscheduleList();
		if (type.equals("G")) {
			// 此处频繁连接数据库(以后改进)
			for (int i = startWeek; i <= endWeek; i++) {
				String z_date = DateUtil.getNextDay(java.sql.Date.valueOf(firstWeekDate), (i - 1) * 7, "yyyy-MM-dd");
				LoSchedule ls = new LoSchedule();
				ls.setClassId(loSchedule.getClassId());
				ls.setSchoolId(loSchedule.getSchoolId());
				ls.setDayofweek(loSchedule.getDayofweek());
				ls.setSectionofday(loSchedule.getSectionofday());
				ls.setGradeId(loSchedule.getGradeId());
				ls.setSubjectId(loSchedule.getSubjectId());
				ls.setUserId(loSchedule.getUserId());
				ls.setCoverpath(loSchedule.getCoverpath());
				if (loFschedules != null) {
					ls.setJohn_num(loFschedules.size());
				}
				ls.setZ_date(java.sql.Date.valueOf(z_date));
				ls.setWeeks(i);
				ls.setType(loSchedule.getType());
				ls.setBak(loSchedule.getBak());
				ls.setBak1(termTimeId);
				ls.setBak2(loSchedule.getBak2());
				loScheduleMapper.insert(ls);
				if (loFschedules != null && loFschedules.size() > 0) {
					for (LoFschedule loFschedule : loFschedules) {
						loFschedule.setZId(ls.getId());
					}
					loFscheduleMapper.insertBatch(loFschedules);
				}
			}
		}
	}

	@Override
	public Page<LoSchedule> findPage(LoSchedule loSchedule, int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return loScheduleMapper.findPage(loSchedule);
	}

	@Override
	public List<LoSchedule> findRecommendedCourse(LoSchedule schedule) {
		LoSchedule loSchedule = new LoSchedule();
		loSchedule.setType(schedule.getType());
		loSchedule.setGradeId(schedule.getGradeId());
		loSchedule.setSubjectId(schedule.getSubjectId());
		// loSchedule.setZ_date(new Date());
		Page<LoSchedule> pageInfo = findPage(loSchedule, 1, 8);
		return pageInfo.getResult();
	}

	@Override
	public LoSchedule findByCDtime(String classCode) {
		return loScheduleMapper.findByCDtime(classCode);
	}

	@Override
	public LoSchedule findFByCDtime(String classcode) {
		return loScheduleMapper.findFByCDtime(classcode);
	}

	@Override
	public void updateUserExp(String userId, String pudId) {
		User user = userService.findByKey(userId);
		if (user == null || user.getUserType() == null) {
			return;
		}
		if (user.getUserType().equals(User.userType_teachers)) {
			SysHistory sysh = new SysHistory();
			sysh.setUserId(userId);
			sysh.setPubType(SysHistory.PUBTYPE_N);
			sysh.setPubFlag(SysHistory.PUBFLAG_H);
			sysh.setPubId(pudId);
			List<SysHistory> shList = sysHistoryService.findSelective(sysh);
			if (shList == null || shList.size() == 0) {
				if (byWatchLosExp.isEmpty()) {
					Properties props = new Properties();
					InputStream in;
					in = getClass().getResourceAsStream("/exp.properties");
					try {
						props.load(in);
					} catch (Exception e) {
						byWatchLosExp = "150";
					}
					if (props.isEmpty()) {
						byWatchLosExp = "150";
					} else {
						byWatchLosExp = props.get("byWatchLos.exp").toString();
					}
				}
				int bwle = Integer.valueOf(byWatchLosExp);
				sysh.setBak(byWatchLosExp);
				sysh.setBak1("发布了一直播,获得" + bwle + "经验值");
				sysHistoryService.insert(sysh);
				userService.updateUserExp(userId, bwle);
			}
		}
	}

	@Override
	public Page<LoSchedule> findMyPgLoSchedule(String userId, int p) {
		LoSchedule loSchedule = new LoSchedule();
		loSchedule.setPgUserId(userId);
		loSchedule.setTimeSorting("Q");
		Page<LoSchedule> loSchedules = findPageSelective(loSchedule, p, 6);
		return loSchedules;
	}

	@Override
	public List<LoSchedule> listLoscheduleIncludeLoFschedule(LoSchedule loSchedule) {
		return loScheduleMapper.listLoscheduleIncludeLoFschedule(loSchedule);
	}

	@Override
	public List<LoSchedule> findSelectiveNow(LoSchedule t) {
		// TODO Auto-generated method stub
		return loScheduleMapper.findSelectiveNow(t);
	}

	@Override
	public List<LoSchedule> getLoScheduleBySC(LoSchedule loSchedule) {
		
		return loScheduleMapper.getLoScheduleBySC(loSchedule);
	}

}
