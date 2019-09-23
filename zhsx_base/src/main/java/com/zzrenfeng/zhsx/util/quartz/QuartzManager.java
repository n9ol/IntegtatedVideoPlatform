package com.zzrenfeng.zhsx.util.quartz;

import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Description: 定时任务管理类
 * 
 * @ClassName: QuartzManager
 * @Copyright: Copyright (c) 2019
 * 
 * @author zhoujincheng
 * @date 2019-03-01 10:15:52
 * @version V1.0.0
 */
public class QuartzManager {
	
	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	
	private static String JOB_GROUP_NAME = "DEFAULT_JOBGROUP_NAME";
	private static String TRIGGER_GROUP_NAME = "DEFAULT_TRIGGERGROUP_NAME";

	/**
	 * 功能： 添加一个定时任务并启动(不带可变参数)，使用默认任务组名、触发器名和默认触发器组名；
	 * 
	 * @param jobName 任务名
	 * @param jobClass 要执行的定时任务类；任务的类类型 eg:TimedMassJob.class
	 * @param cron quartz时间设置表达式，参考quartz说明文档，eg: "0/10 * * * * ?"等(每10s执行一次)
	 * @param params 额外扩展参数
	 */
	@SuppressWarnings("rawtypes")
	public static void addJob(String jobName, Class jobClass, String cron, Map<String, Object> params) {
		addJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME, jobClass, cron, params);
	}
	
	/**
	 * 功能： 添加一个定时任务并启动(不带可变参数)，使用默认任务组名、触发器名和默认触发器组名；
	 * 
	 * @param jobName 任务名
	 * @param jobClass 要执行的定时任务类；任务的类类型 eg:TimedMassJob.class
	 * @param cron quartz时间设置表达式，参考quartz说明文档，eg: "0/10 * * * * ?"等(每10s执行一次)
	 */
	@SuppressWarnings("rawtypes")
	public static void addJob(String jobName, Class jobClass, String cron) {
		addJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME, jobClass, cron);
	}
	
	/**
	 * 功能： 添加一个定时任务并启动(不带可变参数)，使用默认任务组名和默认触发器组名；
	 * 
	 * @param jobName 任务名
	 * @param triggerName 触发器名
	 * @param jobClass 要执行的定时任务类；任务的类类型 eg:TimedMassJob.class
	 * @param cron quartz时间设置表达式，参考quartz说明文档，eg: "0/10 * * * * ?"等(每10s执行一次)
	 */
	@SuppressWarnings("rawtypes")
	public static void addJob(String jobName, String triggerName, Class jobClass, String cron) {
		addJob(jobName, JOB_GROUP_NAME, triggerName, TRIGGER_GROUP_NAME, jobClass, cron);
	}
	
	/**
	 * 功能： 添加一个定时任务并启动(不带可变参数)
	 * 
	 * @param jobName 任务名
	 * @param jobGroupName 任务组名
	 * @param triggerName 触发器名
	 * @param triggerGroupName 触发器组名
	 * @param jobClass 要执行的定时任务类；任务的类类型 eg:TimedMassJob.class
	 * @param cron quartz时间设置表达式，参考quartz说明文档，eg: "0/10 * * * * ?"等(每10s执行一次)
	 */
	@SuppressWarnings("rawtypes")
	public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, String cron) {
		addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron, null);
	}

	/**
	 * 功能： 添加一个定时任务并启动(带可变参数)
	 * 
	 * @param jobName
	 *            任务名
	 * @param jobGroupName
	 *            任务组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param jobClass
	 *            任务的类类型 eg:TimedMassJob.class
	 * @param cron
	 *            quartz时间设置表达式，参考quartz说明文档，eg: "0/10 * * * * ?"等(每10s执行一次)
	 * @param params //将Object...objects类型转为Map类型params
	 *            可变参数需要进行传参的值
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
			Class jobClass, String cron, Map<String, Object> params) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			// 任务名，任务组，任务执行类
			JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
			// 触发器
			if (null != params && !params.isEmpty()) {
				/* for (int i = 0; i < objects.length; i++) {
					// 该数据可以通过Job中的JobDataMap dataMap =
					// context.getJobDetail().getJobDataMap();来进行参数传递值
					jobDetail.getJobDataMap().put("data" + (i + 1), objects[i]);
				}*/
				jobDetail.getJobDataMap().putAll(params);
			}
			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
			// 触发器名,触发器组
			triggerBuilder.withIdentity(triggerName, triggerGroupName);
			triggerBuilder.startNow();
			// 触发器时间设定
			triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
			// 创建Trigger对象
			CronTrigger trigger = (CronTrigger) triggerBuilder.build();
			// 调度容器设置JobDetail和Trigger
			scheduler.scheduleJob(jobDetail, trigger);
			// 启动
			if (!scheduler.isShutdown()) {
				scheduler.start();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 功能：修改一个任务的触发时间并生效，使用默认的任务组名、触发器名和触发器组名；
	 * 
	 * @param jobName 任务名
	 * @param cron quartz时间设置表达式，参考quartz说明文档，eg: "0/10 * * * * ?"等(每10s执行一次)
	 */
	public static void modifyJobTime(String jobName, String cron) {
		modifyJobTime(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME, cron);
	}
	
	/**
	 * 功能：修改一个任务的触发时间并生效，使用默认的任务组名和触发器组名；
	 * 
	 * @param jobName 任务名
	 * @param triggerName 触发器名
	 * @param cron quartz时间设置表达式，参考quartz说明文档，eg: "0/10 * * * * ?"等(每10s执行一次)
	 */
	public static void modifyJobTime(String jobName, String triggerName, String cron) {
		modifyJobTime(jobName, JOB_GROUP_NAME, triggerName, TRIGGER_GROUP_NAME, cron);
	}

	/**
	 * 功能：修改一个任务的触发时间并生效
	 * 
	 * @param jobName 任务名
	 * @param jobGroupName 任务组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param cron
	 *            quartz时间设置表达式，参考quartz说明文档，eg: "0/10 * * * * ?"等(每10s执行一次)
	 */
	public static void modifyJobTime(String jobName, String jobGroupName, String triggerName, String triggerGroupName,
			String cron) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(cron)) {
				// 触发器
				TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
				// 触发器名,触发器组
				triggerBuilder.withIdentity(triggerName, triggerGroupName);
				triggerBuilder.startNow();
				// 触发器时间设定
				triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
				// 创建Trigger对象
				trigger = (CronTrigger) triggerBuilder.build();
				// 方式一 ：修改一个任务的触发时间
				scheduler.rescheduleJob(triggerKey, trigger);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 功能: 移除一个任务，使用默认的任务组名、触发器名和触发器组名；
	 * 
	 * @param jobName 任务名
	 */
	public static void removeJob(String jobName) {
		removeJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME);
	}

	/**
	 * 功能: 移除一个任务，使用默认的任务组名和触发器组名；
	 * 
	 * @param jobName 任务名
	 * @param triggerName 任务组名
	 */
	public static void removeJob(String jobName, String triggerName) {
		removeJob(jobName, JOB_GROUP_NAME, triggerName, TRIGGER_GROUP_NAME);
	}
	
	/**
	 * 功能: 移除一个任务
	 * 
	 * @param jobName 任务名 
	 * @param jobGroupName 任务组名
	 * @param triggerName 触发器名
	 * @param triggerGroupName 触发器组名
	 */
	public static void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();

			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
			// 停止触发器
			scheduler.pauseTrigger(triggerKey);
			// 移除触发器
			scheduler.unscheduleJob(triggerKey);
			// 删除任务
			scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 功能：立即出发一个Job，使用默认的任务组名
	 * 
	 * @param jobName 任务名
	 */
	public static void runJobNow(String jobName) {
		runJobNow(jobName, JOB_GROUP_NAME);
	}
	
	/**
	 * 功能：立即出发一个Job
	 * 
	 * @param jobName 任务名
	 * @param jobGroupName 任务组名
	 */
	public static void runJobNow(String jobName, String jobGroupName) {		
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.triggerJob(JobKey.jobKey(jobName, jobGroupName));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能：暂停一个Job，使用默认任务组名
	 * @param jobName
	 */
	public static void pauseJob(String jobName) {
		pauseJob(jobName, JOB_GROUP_NAME);
	}
	
	/**
	 * 功能：暂停一个Job
	 * @param jobName
	 * @param jobGroupName
	 */
	public static void pauseJob(String jobName, String jobGroupName) {		
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.pauseJob(JobKey.jobKey(jobName, jobGroupName));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能：恢复一个Job，使用默认任务组名
	 * @param jobName
	 */
	public static void resumeJob(String jobName) {
		resumeJob(jobName, JOB_GROUP_NAME);
	}
	/**
	 * 功能：恢复一个Job
	 * @param jobName
	 * @param jobGroupName
	 */
	public static void resumeJob(String jobName, String jobGroupName) {		
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.resumeJob(JobKey.jobKey(jobName, jobGroupName));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 功能：删除一个Job，使用默认任务组名
	 * @param jobName
	 */
	public static void deleteJob(String jobName) {
		deleteJob(jobName, JOB_GROUP_NAME);
	}
	
	/**
	 * 功能：删除一个Job
	 * @param jobName
	 * @param jobGroupName
	 */
	public static void deleteJob(String jobName, String jobGroupName) {		
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能：启动所有定时任务
	 */
	public static void startAllJobs() {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 功能：关闭所有定时任务
	 */
	public static void shutdownAllJobs() {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			if (!scheduler.isShutdown()) {
				scheduler.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
