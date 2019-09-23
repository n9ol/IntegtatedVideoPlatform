package com.zzrenfeng.zhsx.service.eclassbrand.course;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseScheduleBigTime;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseScheduleTime;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-03-24 17:46:39
 * @see com.zzrenfeng.classbrand.service.CourseScheduleBigTime
 */
public interface CourseScheduleBigTimeService {

	CourseScheduleBigTime insert(CourseScheduleBigTime t);

	int deleteByKey(String id);

	CourseScheduleBigTime updateByKey(CourseScheduleBigTime t);

	int updateByKeySelective(CourseScheduleBigTime t);

	CourseScheduleBigTime findByKey(String id);

	List<CourseScheduleBigTime> findAll();

	List<CourseScheduleBigTime> findSelective(CourseScheduleBigTime t);

	Page<CourseScheduleBigTime> findPageSelective(CourseScheduleBigTime t, int p, int pageSize);

	/**
	 * 添加、编辑小节时间时,同步更新大节时间
	 * 
	 * @param courseScheduletime
	 * @return
	 */
	CourseScheduleBigTime insertOrUpdate(CourseScheduleTime courseScheduletime, Date date);

	/**
	 * 删除小节是同步更新大节数据
	 * 
	 * @param courseScheduletime
	 * @return
	 */
	int deleteAndUpdate(CourseScheduleTime courseScheduletime);

	/**
	 * 通过节次获取开始时间和结束时间
	 * 
	 * @param bigSectionOfDay
	 * @return
	 */
	List<Date> listTime(int bigSectionOfDay);

	/**
	 * bigSectionOfDay 转换成字符串
	 * 
	 * @param listCourseScheduleBigTime
	 */
	void bigSectionOfDayStringFrombigSectionOfDay(List<CourseScheduleBigTime> listCourseScheduleBigTime);

}