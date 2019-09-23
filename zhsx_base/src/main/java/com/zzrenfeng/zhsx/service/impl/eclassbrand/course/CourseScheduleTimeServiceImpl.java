package com.zzrenfeng.zhsx.service.impl.eclassbrand.course;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.mapper.eclassbrand.course.CourseScheduleTimeMapper;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseScheduleTime;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleTimeService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author zzrenfeng.com
 * @version 2018-03-02 16:32:03
 * @see com.zzrenfeng.classbrand.service.impl.CourseScheduleTime
 */

@Service("courseScheduleTimeService")
public class CourseScheduleTimeServiceImpl implements CourseScheduleTimeService {

	@Resource
	private CourseScheduleTimeMapper courseScheduletimeMapper;

	@Override
	public Boolean validateSectionOfDay(Integer sectionOfDay) {
		CourseScheduleTime courseScheduletime = new CourseScheduleTime();
		courseScheduletime.setSectionOfDay(sectionOfDay);
		List<CourseScheduleTime> listCourseScheduletime = findSelective(courseScheduletime);
		if (listCourseScheduletime != null && listCourseScheduletime.size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public CourseScheduleTime insert(CourseScheduleTime t) {
		courseScheduletimeMapper.insert(t);
		return t;
	}

	@Override
	public int deleteByKey(String id) {
		return courseScheduletimeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public CourseScheduleTime updateByKey(CourseScheduleTime t) {
		courseScheduletimeMapper.updateByPrimaryKey(t);
		return t;
	}

	@Override
	public int updateByKeySelective(CourseScheduleTime t) {
		return courseScheduletimeMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public CourseScheduleTime findByKey(String id) {
		return courseScheduletimeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CourseScheduleTime> findAll() {
		return courseScheduletimeMapper.selectAll();
	}

	@Override
	public List<CourseScheduleTime> findSelective(CourseScheduleTime t) {
		return courseScheduletimeMapper.findSelective(t);
	}

	@Override
	public Page<CourseScheduleTime> findPageSelective(CourseScheduleTime t, int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return courseScheduletimeMapper.findPageSelective(t);
	}

	@Override
	public List<Map<String, Object>> geCourseStartEndTimeList() {
		return courseScheduletimeMapper.geCourseStartEndTimeList();
	}

	@Override
	public CourseScheduleTime getCourseScheduleTimeByConditions(CourseScheduleTime courseScheduleTime) {
		return courseScheduletimeMapper.getCourseScheduleTimeByConditions(courseScheduleTime);
	}


}
