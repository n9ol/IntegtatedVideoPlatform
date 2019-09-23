package com.zzrenfeng.zhsx.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author Administrator
 *
 */
public class DataSourcePool {

	private ApplicationContext applicationContext;

	private static class Holder {
		private static final DataSourcePool pool = new DataSourcePool();
	}

	private DataSourcePool() {
	}

	//dbcp连接池
	private BasicDataSource cs = null;

	public static final DataSourcePool instance() {
		return Holder.pool;
	}

	public static final Connection getConnect(
			ApplicationContext applicationContext) {

		Holder.pool.setApplicationContext(applicationContext);
		return Holder.pool.connect();
	}
	
	public static final Connection getConnect() {

		Holder.pool.setApplicationContext();
		return Holder.pool.connect();
	}

	private void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	private void setApplicationContext() {
		String[] contextRef = new String[1];
		try {
			contextRef = new String[] { "classpath:spring-base.xml",
					"classpath:spring-serivce.xml",
					"classpath:spring-shiro.xml" };
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (applicationContext == null) {
			this.applicationContext = new ClassPathXmlApplicationContext(contextRef);
		}
	}
	private Connection connect() {
		if(cs==null){
			cs = (BasicDataSource) applicationContext.getBean("dataSource");
		}
		Connection c = null;
		try {
		 	c = cs.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	public void colse(Connection conn,PreparedStatement ps){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
