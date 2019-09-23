package com.zzrenfeng.zhsx.service.impl.eclassbrand.course;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.mapper.eclassbrand.course.CourseScheduleBigTimeMapper;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseScheduleBigTime;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseScheduleTime;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleBigTimeService;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-03-24 17:46:39
 * @see com.zzrenfeng.classbrand.service.impl.CourseScheduleBigTime
 */

@Service
public class CourseScheduleBigTimeServiceImpl implements CourseScheduleBigTimeService {

	@Resource
	private CourseScheduleBigTimeMapper courseScheduleBigTimeMapper;

	@Override
	public CourseScheduleBigTime insert(CourseScheduleBigTime t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteByKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CourseScheduleBigTime updateByKey(CourseScheduleBigTime t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByKeySelective(CourseScheduleBigTime t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CourseScheduleBigTime findByKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseScheduleBigTime> findAll() {
		return courseScheduleBigTimeMapper.selectAll();
	}

	@Override
	public List<CourseScheduleBigTime> findSelective(CourseScheduleBigTime t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CourseScheduleBigTime> findPageSelective(CourseScheduleBigTime t, int p, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseScheduleBigTime insertOrUpdate(CourseScheduleTime courseScheduletime, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteAndUpdate(CourseScheduleTime courseScheduletime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Date> listTime(int bigSectionOfDay) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bigSectionOfDayStringFrombigSectionOfDay(List<CourseScheduleBigTime> listCourseScheduleBigTime) {
		for (CourseScheduleBigTime courseScheduleBigTime : listCourseScheduleBigTime) {
			courseScheduleBigTime.setBigSectionOfDayString(String.valueOf(courseScheduleBigTime.getBigSectionOfDay()));
		}
	}

}
