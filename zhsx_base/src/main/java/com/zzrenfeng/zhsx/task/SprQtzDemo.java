package com.zzrenfeng.zhsx.task;

import java.io.IOException;
import java.util.Properties;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SprQtzDemo extends QuartzJobBean {
	private final Logger LOGGER = LoggerFactory.getLogger(SprQtzDemo.class);

	//读取properties配置文件
    Properties prop = null;
    public SprQtzDemo() {
    	prop = new Properties();
    	try {
			prop.load(SprQtzDemo.class.getResourceAsStream("/commonConfig.properties"));			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		LOGGER.debug("==============>>>> 执行定时任务(Spring) <<<<==============");
		String startTime = String.valueOf(context.getMergedJobDataMap().get("startTime"));    //任务初始化时传递的参数
		String bublishCtrlService = prop.getProperty("bublishCtrlService");    //系统配置参数
		System.out.println("==============>>>> 【开始录播任务】执行UDP指令向流媒体服务器发送Start Record命令 <<<<==============");
		System.out.println("==============>>>> 【获取传递参数】执行参数startTime=" + startTime + " <<<<==============");
		System.out.println("==============>>>> 【获取系统参数】系统配置参数bublishCtrlService=" + bublishCtrlService + " <<<<==============");
	}

}

/*public class SprQtzDemo implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("==============>>>> 【开始录播任务】执行UDP指令向流媒体服务器发送Start Record命令 <<<<==============");
	}

}*/