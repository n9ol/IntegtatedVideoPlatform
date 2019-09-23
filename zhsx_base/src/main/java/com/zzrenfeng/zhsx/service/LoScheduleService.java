package com.zzrenfeng.zhsx.service;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.base.BaseService;
import com.zzrenfeng.zhsx.model.LoSchedule;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseSchedule;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2017-04-27 11:33:39
 * @see com.zzrenfeng.zhsx.service.LoSchedule
 */
public interface LoScheduleService extends BaseService<LoSchedule> {

	/**
	 * 添加一带多模式课程
	 * 
	 * @param loSchedule
	 */
	void insertScheduleInMany(LoSchedule loSchedule);

	/**
	 * 批量删除
	 * 
	 * @param loSchedule
	 */
	void delScheduleBatch(LoSchedule loSchedule);

	/**
	 * 修改一校带多校模式课程信息
	 * 
	 * @param loSchedule
	 */
	void uploadScheduleInMany(LoSchedule loSchedule);

	/**
	 * 批量添加课程
	 * 
	 * @param loSchedule
	 * @param startWeek
	 * @param endWeek
	 */
	void insertScheduleBatch(LoSchedule loSchedule, Integer startWeek, Integer endWeek);

	/**
	 * 前台直播查询
	 * 
	 * @param t
	 * @return
	 */
	Page<LoSchedule> findPage(LoSchedule loSchedule, int p, int pageSize);

	/**
	 * 获得推荐课程
	 * 
	 * @param schedule
	 * @return
	 */
	List<LoSchedule> findRecommendedCourse(LoSchedule schedule);

	/**
	 * 通过教室编号获取当前教室信息
	 * 
	 * @param classCode
	 * @return
	 */
	LoSchedule findByCDtime(String classCode);

	/**
	 * 通过当前时间和教室编号获取直播zid
	 * 
	 * @param classcode
	 * @return
	 */
	LoSchedule findFByCDtime(String classcode);

	/**
	 * 直播被观看更新用户经验值
	 * 
	 * @param userId
	 * @param pudId
	 */
	void updateUserExp(String userId, String pudId);

	/**
	 * 获得我的评估课程-(通知)
	 * 
	 * @param userId
	 * @return
	 */
	Page<LoSchedule> findMyPgLoSchedule(String userId, int p);

	/**
	 * 获取直播课程包含附讲教室、教师信息
	 * 
	 * @param loSchedule
	 * @return
	 */
	List<LoSchedule> listLoscheduleIncludeLoFschedule(LoSchedule loSchedule);

	/**
	 * CourseSchedule 一一转换成 LoSchedule
	 * 
	 * @param courseSchedule
	 * @return
	 */
	List<LoSchedule> listLoschedule(HttpServletRequest request, List<CourseSchedule> listCourseSchedule)
			throws ParseException;

}
