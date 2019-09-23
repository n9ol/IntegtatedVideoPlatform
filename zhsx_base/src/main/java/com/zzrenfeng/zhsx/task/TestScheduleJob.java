package com.zzrenfeng.zhsx.task;

import java.util.HashMap;
import java.util.Map;

import com.zzrenfeng.zhsx.util.quartz.QuartzManager;

public class TestScheduleJob {
	
	public static void main(String[] args) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("startTime", "14:00:00");
		try {
			String jobName = "动态任务调度1";
			String cronExpression = "0/1 * * * * ?";
			System.out.println("【系统启动】增加任务开始(每1秒输出一次)...");
			QuartzManager.addJob(jobName, SprQtzDemo.class, cronExpression, params);
			System.out.println("【系统启动】增加任务结束(每1秒输出一次)...");
			Thread.sleep(5000);
			System.out.println("【修改时间】开始(每2秒输出一次)...");
			QuartzManager.modifyJobTime(jobName, "0/2 * * * * ?");
			System.out.println("【修改时间】结束(每2秒输出一次)...");
			Thread.sleep(10000);
			System.out.println("【移除任务】开始...");
			QuartzManager.removeJob(jobName);
			System.out.println("【移除任务】成功...");
			
			jobName = "动态任务调度2";
			System.out.println("【再次添加定时任务】开始(每10秒输出一次)...");    
            QuartzManager.addJob(jobName, SprQtzDemo.class, "0/10 * * * * ?", params);
            System.out.println("【再次添加定时任务】结束(每10秒输出一次)...");
            Thread.sleep(60000);    
            System.out.println("【移除定时任务】开始...");    
            QuartzManager.shutdownAllJobs();    //QuartzManager.removeJob(jobName);    
            System.out.println("【移除定时任务】成功..."); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
