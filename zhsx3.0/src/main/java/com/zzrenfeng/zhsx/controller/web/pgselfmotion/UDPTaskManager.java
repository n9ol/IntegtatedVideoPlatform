package com.zzrenfeng.zhsx.controller.web.pgselfmotion;

import java.io.Serializable;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class UDPTaskManager implements ServletContextListener, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("--学生回答问题时间监听  关闭--");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("--学生回答问题时间监听  开启--");
		new Thread(new UDPRece()).start();
	}

}
