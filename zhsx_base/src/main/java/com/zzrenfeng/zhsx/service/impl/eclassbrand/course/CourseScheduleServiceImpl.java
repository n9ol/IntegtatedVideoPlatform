package com.zzrenfeng.zhsx.service.impl.eclassbrand.course;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzrenfeng.zhsx.constant.Constant;
import com.zzrenfeng.zhsx.mapper.eclassbrand.course.CourseScheduleMapper;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseSchedule;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleService;
import com.zzrenfeng.zhsx.util.DateUtil;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-05-22 09:59:04
 * @see com.zzrenfeng.service.impl.CourseSchedule
 */

@Service("courseScheduleService")
public class CourseScheduleServiceImpl implements CourseScheduleService {

	@Resource
	private CourseScheduleMapper courseScheduleMapper;

	public CourseSchedule getCourseSchedule(String id) {
		return courseScheduleMapper.getCourseSchedule(id);
	}

	public List<CourseSchedule> listAllCourseSchedule() {
		return courseScheduleMapper.listAllCourseSchedule();
	}

	public List<CourseSchedule> listCourseSchedule(CourseSchedule courseSchedule) {
		return courseScheduleMapper.listCourseSchedule(courseSchedule);
	}

	@Override
	public Page<CourseSchedule> getPageInfo(CourseSchedule t, int p, int pageSize) {
		PageHelper.startPage(p, pageSize);
		return courseScheduleMapper.getPageSelective(t);
	}

	@Override
	public List<CourseSchedule> listRecommendVideos(CourseSchedule courseSchedule, int pageSize) {
		courseSchedule.setDayOfWeek(DateUtil.getIntWeekDay());
		courseSchedule.setTimeSorting(Constant.COURSE_SCHEDULE_TIMESORTING_Q);
		PageHelper.startPage(1, pageSize);
		return courseScheduleMapper.getPageSelective(courseSchedule);
	}

	@Override
	public int updateClassroomNameByClassroomId(String classroomId, String classroomName) {
		CourseSchedule courseSchedule = new CourseSchedule();
		courseSchedule.setClassroomId(classroomId);
		courseSchedule.setClassroomName(classroomName);
		return courseScheduleMapper.updateClassroomNameByClassroomId(courseSchedule);
	}

	@Override
	public List<CourseSchedule> listClassroomCourseSchedule(String classroomId) {
		CourseSchedule courseSchedule = new CourseSchedule();
		courseSchedule.setClassroomId(classroomId);
		return courseScheduleMapper.listCourseSchedule(courseSchedule);
	}

}
