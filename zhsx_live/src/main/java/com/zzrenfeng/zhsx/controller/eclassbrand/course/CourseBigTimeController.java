package com.zzrenfeng.zhsx.controller.eclassbrand.course;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzrenfeng.zhsx.model.eclassbrand.course.CourseScheduleBigTime;
import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleBigTimeService;

@Controller
@RequestMapping("/courseBigTime")
public class CourseBigTimeController {

	@Resource
	private CourseScheduleBigTimeService courseScheduleBigTimeService;

	@ResponseBody
	@RequestMapping("/listCourseScheduleBigTime")
	public List<CourseScheduleBigTime> listCourseScheduleBigTime() {
		List<CourseScheduleBigTime> listCourseScheduleBigTime = courseScheduleBigTimeService.findAll();
		return listCourseScheduleBigTime;
	}
}
