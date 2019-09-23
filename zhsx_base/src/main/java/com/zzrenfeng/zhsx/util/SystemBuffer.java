package com.zzrenfeng.zhsx.util;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @功能描述：系统资源缓冲器，用于系统静态资源缓存和获取
 * @创  建  者：zhoujincheng
 * @版        本：V1.0.0
 * @创建日期：2019年3月6日 上午9:23:28
 * 
 * @修  改  人：
 * @版        本：V1.1.0
 * @修改日期：
 * @修改描述：
 *
 */
public class SystemBuffer {
	protected static Logger LOGGER = LoggerFactory.getLogger(SystemBuffer.class);
	/**
	 * 私有构造方法，不需要创建对象
	 */
	private SystemBuffer() {
		
	}
	
	/**
	 * @功能描述：系统静态资源缓冲加载，该方法在WebContainerListener中被加载，即web容器启动时自动被加载
	 * @创  建  者：zhoujincheng
	 * @版        本：V1.0.0
	 * @创建日期：2019年3月6日 上午9:24:39
	 * 
	 * @修  改  人：
	 * @版        本：V1.1.0
	 * @修改日期：
	 * @修改描述：
	 *
	 */
	public static synchronized void init() {
		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@====>>>>系统静态资源初始化开始！（web容器启动时加载）<<<<====@@@@@@@@@@@@@@@@@@@@@@");
		
	}	
	

}
