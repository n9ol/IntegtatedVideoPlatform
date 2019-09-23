package com.zzrenfeng.zhsx.mapper.eclassbrand.course;

import java.util.List;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.jdbc.DataSource;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseSchedule;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-05-22 09:59:04
 * @see com.zzrenfeng.service.CourseSchedule
 */
@DataSource(value = "2")
public interface CourseScheduleMapper {

	CourseSchedule getCourseSchedule(String id);

	List<CourseSchedule> listAllCourseSchedule();

	List<CourseSchedule> listCourseSchedule(CourseSchedule courseSchedule);

	Page<CourseSchedule> getPageSelective(CourseSchedule courseSchedule);

	/**
	 * 修改所在教室的名称
	 * 
	 * @param classroomId
	 * @param classroomName
	 * @return
	 */
	int updateClassroomNameByClassroomId(CourseSchedule courseSchedule);
}
