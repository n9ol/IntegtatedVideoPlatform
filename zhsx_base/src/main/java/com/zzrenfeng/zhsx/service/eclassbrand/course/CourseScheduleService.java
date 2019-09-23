package com.zzrenfeng.zhsx.service.eclassbrand.course;

import java.util.List;

import com.github.pagehelper.Page;
import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseSchedule;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link zzrenfeng.com}
 * @author 田杰熠
 * @version 2018-05-22 09:59:04
 * @see com.zzrenfeng.service.CourseSchedule
 */
public interface CourseScheduleService {

	/**
	 * 主键查询
	 * 
	 * @param id
	 * @return
	 */
	CourseSchedule getCourseSchedule(String id);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<CourseSchedule> listAllCourseSchedule();

	/**
	 * 筛选查询
	 * 
	 * @param t
	 * @return
	 */
	List<CourseSchedule> listCourseSchedule(CourseSchedule t);

	/**
	 * 分页查询
	 * 
	 * @param t
	 * @param p
	 * @param pageSize
	 * @return
	 */
	Page<CourseSchedule> getPageInfo(CourseSchedule t, int p, int pageSize);

	/**
	 * 获得推荐直播视频
	 * 
	 * @param t
	 * @return
	 */
	List<CourseSchedule> listRecommendVideos(CourseSchedule courseSchedule, int pageSize);

	/**
	 * 修改所在教室的名称
	 * 
	 * @param classroomId
	 * @param classroomName
	 * @return
	 */
	int updateClassroomNameByClassroomId(String classroomId, String classroomName);

	/**
	 * 获取某教室的课程表
	 * 
	 * @param classroomId
	 * @return
	 */
	List<CourseSchedule> listClassroomCourseSchedule(String classroomId);

}