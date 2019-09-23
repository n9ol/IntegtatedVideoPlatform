package com.zzrenfeng.zhsx.mapper.eclassbrand.course;

import java.util.List;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.jdbc.DataSource;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseScheduleBigTime;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-03-24 17:46:39
 * @see com.zzrenfeng.classbrand.service.CourseScheduleBigTimeBigTime
 */
@DataSource(value = "2")
public interface CourseScheduleBigTimeMapper {

	int insert(CourseScheduleBigTime t);

	int deleteByPrimaryKey(String id);

	int updateByPrimaryKey(CourseScheduleBigTime t);

	int updateByPrimaryKeySelective(CourseScheduleBigTime t);

	CourseScheduleBigTime selectByPrimaryKey(String id);

	List<CourseScheduleBigTime> selectAll();

	List<CourseScheduleBigTime> findSelective(CourseScheduleBigTime t);

	Page<CourseScheduleBigTime> findPageSelective(CourseScheduleBigTime t);

	/**
	 * 通过节次删除上课课程
	 * 
	 * @param bigSectionOfDay
	 * @return
	 */
	int deleteBybigSectionOfDay(int bigSectionOfDay);

}
