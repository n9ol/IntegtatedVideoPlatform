package com.zzrenfeng.zhsx.controller.web.pgselfmotion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.zzrenfeng.zhsx.util.DataSourcePool;

@WebListener
public class PersistCommentTask implements ServletContextListener {

	private ApplicationContext applicationContext;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		if (applicationContext == null) {
			setApplicationContext(arg0);
		}
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Queue<Map<String, Object>> q = CommentMsgQueue.instance()
						.getQueue();
				Map<String, Object> m = null;
				while (!q.isEmpty()) {
					try {
						m = q.poll();
						insert(m);
						m = null;
					} catch (Exception e) {
						e.printStackTrace();
						if (m != null) {
							q.offer(m);
							m = null;
						}
					}
				}
			}
		};
		new Timer().schedule(task, 1000, 1000 * 20);
	}

	public void insert(Map<String, Object> m) throws Exception {

		// w.setUserId(m.get("userId").toString());
		// w.setContext(m.get("noTagMsg").toString());
		// w.setContextMo(m.get("msg").toString());
		// w.setContextId(m.get("sourceId").toString());
		// w.setContextType(m.get("mType") + "");
		
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(m.get("timeStamp").toString());
			Timestamp d = new Timestamp(date.getTime());
			String sql = "insert into web_comments (id,userId,contextType,contextId,context,contextMo,isShown,thumbsUp,thumbsDown,createTime,modiyTime) values(?,?,?,?,?,?,?,?,?,?,?)";
			conn = DataSourcePool.getConnect(applicationContext);
			ps = conn.prepareStatement(sql);
			ps.setString(1, UUID.randomUUID().toString());
			ps.setString(2, m.get("userId").toString());
			ps.setString(3, m.get("mType") + "");
			ps.setString(4, m.get("sourceId").toString());
			ps.setString(5, m.get("noTagMsg").toString());
			ps.setString(6, m.get("msg").toString());
			ps.setString(7, "Y");
			ps.setInt(8, 0);
			ps.setInt(9, 0);
			ps.setTimestamp(10, d);
			ps.setTimestamp(11, d);
			ps.execute();
			// conn.commit();
		} catch (Exception e) {
			throw e;
		} finally {
			DataSourcePool.instance().colse(conn, ps);
		}
	}

	private void setApplicationContext(ServletContextEvent arg0) {
		applicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(arg0.getServletContext());
	}

}
