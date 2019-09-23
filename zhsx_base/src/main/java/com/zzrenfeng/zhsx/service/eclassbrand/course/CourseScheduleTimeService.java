package com.zzrenfeng.zhsx.service.eclassbrand.course;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseScheduleTime;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-03-02 16:32:03
 * @see com.zzrenfeng.classbrand.service.CourseScheduleTime
 */
public interface CourseScheduleTimeService {

	CourseScheduleTime insert(CourseScheduleTime t);

	int deleteByKey(String id);

	CourseScheduleTime updateByKey(CourseScheduleTime t);

	int updateByKeySelective(CourseScheduleTime t);

	CourseScheduleTime findByKey(String id);

	List<CourseScheduleTime> findAll();

	List<CourseScheduleTime> findSelective(CourseScheduleTime t);

	Page<CourseScheduleTime> findPageSelective(CourseScheduleTime t, int p, int pageSize);

	/**
	 * 验证该节次时间是否 不存在
	 * 
	 * @param sectionOfDay
	 * @return
	 */
	Boolean validateSectionOfDay(Integer sectionOfDay);
	/**
	 * 获取所有课程安排的开始与结束时间列表
	 * @author zjc
	 * @date 20190228
	 * 
	 * @return 课程的起止时间列表
	 */
	List<Map<String, Object>> geCourseStartEndTimeList();
	/**
	 * @功能描述：通过“一天中的第几节”【section_of_day】和所给的时间是否落在开始、结束时间的区间内来获取上课节次时间信息对象
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2019年3月8日 上午9:58:53
	 * 
	 * @param courseScheduleTime
	 * @return
	 */
	CourseScheduleTime getCourseScheduleTimeByConditions(CourseScheduleTime courseScheduleTime);
}