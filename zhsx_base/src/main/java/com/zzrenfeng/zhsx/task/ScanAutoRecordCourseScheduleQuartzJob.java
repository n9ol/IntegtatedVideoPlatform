package com.zzrenfeng.zhsx.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleTimeService;
import com.zzrenfeng.zhsx.util.SpringUtil;
import com.zzrenfeng.zhsx.util.UUIDUtils;
import com.zzrenfeng.zhsx.util.quartz.QuartzManager;

public class ScanAutoRecordCourseScheduleQuartzJob extends QuartzJobBean {
	private final Logger LOGGER = LoggerFactory.getLogger(ScanAutoRecordCourseScheduleQuartzJob.class);
	
	private CourseScheduleTimeService courseScheduleTimeService = SpringUtil.getBean("courseScheduleTimeService");
	

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		LOGGER.debug("==============>>>> 扫描自动录课的教室课程表起止时间定时任务【开始】 <<<<==============");
		LOGGER.debug("==============>>>> 关闭所有定时任务【开始】 <<<<==============");
		Map<String, Object> params = null;
		QuartzManager.shutdownAllJobs();
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			LOGGER.debug("==============>>>> 关闭所有定时任务【异常】 <<<<==============");
			e.printStackTrace();
		}
		LOGGER.debug("==============>>>> 关闭所有定时任务【结束】 <<<<==============");
		List<Map<String, Object>> startEndTimeList = courseScheduleTimeService.geCourseStartEndTimeList();
		for(Map<String, Object> startEndTimeMap : startEndTimeList) {
			String[] startTimes = String.valueOf(startEndTimeMap.get("start_time")).split(":");
			String[] endTimes = String.valueOf(startEndTimeMap.get("end_time")).split(":");
			String jobName = null;
			String startCronExp = startTimes[2] + " " + startTimes[1] + " " + startTimes[0] + " * * ?";
			String endCronExp = endTimes[2] + " " + endTimes[1] + " " + endTimes[0] + " * * ?";			
			params = new HashMap<String, Object>();
			params.put("startTime", String.valueOf(startEndTimeMap.get("start_time")));
			params.put("endTime", String.valueOf(startEndTimeMap.get("end_time")));
			
			jobName = UUIDUtils.getUpperUUID();
			QuartzManager.addJob(jobName, SendStartRecordCourseQuartzJob.class, startCronExp, params);
			LOGGER.debug("=====================>>>>开始录制任务【" + jobName + "】的触发时间[" + startCronExp + "]设置成功");
			jobName = UUIDUtils.getUpperUUID();
			QuartzManager.addJob(jobName, SendEndRecordCourseQuartzJob.class, endCronExp, params);
			LOGGER.debug("=====================>>>>结束录制任务【" + jobName + "】的触发时间[" + endCronExp + "]设置成功");
			
		}
		
		LOGGER.debug("==============>>>> 扫描自动录课的教室课程表起止时间定时任务【结束】 <<<<==============");
	}
	
	

}
