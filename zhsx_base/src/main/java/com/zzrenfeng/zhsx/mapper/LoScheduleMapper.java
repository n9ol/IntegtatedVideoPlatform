package com.zzrenfeng.zhsx.mapper;

import java.util.List;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseMapper;
import com.zzrenfeng.zhsx.model.LoSchedule;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:39
 * @see com.zzrenfeng.zhsx.service.LoSchedule
 */

public interface LoScheduleMapper extends BaseMapper<LoSchedule> {

	/**
	 * 有条件的删除课程
	 * 
	 * @param loSchedule
	 */
	void deleteScheduleSelective(LoSchedule loSchedule);

	/**
	 * 批量添加课程信息
	 * 
	 * @param LoSchedules
	 */
	void insertScheduleBatch(List<LoSchedule> LoSchedules);

	/**
	 * 前台直播查询
	 * 
	 * @param loSchedule
	 * @return
	 */
	Page<LoSchedule> findPage(LoSchedule loSchedule);

	/**
	 * 通过教室编号获取当前教室信息
	 * 
	 * @param classCode
	 * @return
	 */
	LoSchedule findByCDtime(String classCode);

	/**
	 * 通过当前时间和教室编号获取教室信息
	 * 
	 * @param classcode
	 * @return
	 */
	LoSchedule findFByCDtime(String classCode);

	/**
	 * 获取直播课程包含附讲教室、教师信息
	 * 
	 * @param loSchedule
	 * @return
	 */
	List<LoSchedule> listLoscheduleIncludeLoFschedule(LoSchedule loSchedule);
	
	/**
	 * 根据条件查询当前正在上课的课程表
	 * @param t
	 * @return
	 */
	List<LoSchedule> findSelectiveNow(LoSchedule t);

	List<LoSchedule> getLoScheduleBySC(LoSchedule loSchedule);
}
