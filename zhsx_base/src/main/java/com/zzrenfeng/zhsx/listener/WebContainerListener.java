package com.zzrenfeng.zhsx.listener;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zzrenfeng.zhsx.service.eclassbrand.course.CourseScheduleTimeService;
import com.zzrenfeng.zhsx.task.SendEndRecordCourseQuartzJob;
import com.zzrenfeng.zhsx.task.SendStartRecordCourseQuartzJob;
import com.zzrenfeng.zhsx.util.SpringUtil;
import com.zzrenfeng.zhsx.util.UUIDUtils;
import com.zzrenfeng.zhsx.util.quartz.QuartzManager;

public class WebContainerListener implements ServletContextListener, Serializable {
	private static final long serialVersionUID = 6731118822430328850L;
	
	protected static Logger LOGGER = LoggerFactory.getLogger(WebContainerListener.class);	
	
    /**
     * Default constructor. 
     */
    public WebContainerListener() {
        super();
    }

	/**
     * 在Container加载Web应用程序时（例如启动 Container之后），会呼叫contextInitialized()，
     * 再调用lifeInit的init()方法；
     */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		LOGGER.info("==============>>>> WEB容器启动，加载资源【开始】 <<<<==============");
		doResetJobState();
		LOGGER.info("==============>>>> WEB容器启动，加载资源【结束】 <<<<==============");
	}	

    /**
     * @see Web应用被卸载时调用如下销毁方法
     * Servlet容器先调用lifeInit的destroy()方法，再调用contextDestroyed()方法。
     */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		LOGGER.info("==============>>>> WEB容器停止，销毁资源【开始】 <<<<==============");
		
		LOGGER.info("==============>>>> WEB容器停止，销毁资源【结束】 <<<<==============");
	}

	/**
	 * 重设定时任务状态
	 */
	private synchronized void doResetJobState() { 
		LOGGER.debug("==============>>>> 【WEB容器启动时】扫描自动录课的教室课程表起止时间定时任务【开始】 <<<<==============");
		LOGGER.debug("==============>>>> 关闭所有定时任务【开始】 <<<<==============");
		CourseScheduleTimeService courseScheduleTimeService = SpringUtil.getBean("courseScheduleTimeService");    //此实例的初始化不能放在类成员变量位置，否则该类无法实例化SpringUtil，因为该监听器初始化先于Web容器
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
		
		LOGGER.debug("==============>>>> 【WEB容器启动时】扫描自动录课的教室课程表起止时间定时任务【结束】 <<<<==============");
	}
	
}
