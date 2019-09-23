package com.zzrenfeng.zhsx.controller.web;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

import com.zzrenfeng.zhsx.controller.web.pgselfmotion.CommentMsgQueue;
import com.zzrenfeng.zhsx.util.DateUtil;
import com.zzrenfeng.zhsx.util.JsonUtil;
import com.zzrenfeng.zhsx.util.JsonUtils;

@ServerEndpoint(value = "/ws/{groupId}")
public class WebSocketServer {

	
	public final Logger logger = Logger.getLogger(WebSocketServer.class);
	private Session session;

	public static Map<String, Map<String, Session>> groupMap = new ConcurrentHashMap<String, Map<String, Session>>();

	// 连接打开时执行
	@OnOpen
	public void onOpen(@PathParam(value = "groupId") String groupId, Session session) {
		this.session = session;
		logger.info("====================客户端:" + session.getId() + "连接成功====================");
		Map<String, Session> client = groupMap.get(groupId);
		if (client == null) {
			client = new ConcurrentHashMap<String, Session>();
		}
		client.put(session.getId(), session);
		groupMap.put(groupId, client);

	}

	// 收到消息时执行
	@OnMessage
	public void onMessage(@PathParam(value = "groupId") String groupId, String message, Session session) {
		logger.info("====================客户端:" + session.getId() + "发送消息:====================");
		logger.info("消息:" + message + "");
		logger.info("=============================================================");
		try {
			Map<String, Object> m = JsonUtils.str2Map(message);
			String msg = m.get("msg").toString();
			msg = msg.replaceAll("_;_", "_；_");
			m.put("msg", msg);
			m.put("timeStamp", DateUtil.NowDateLongStr());
			this.sendAllMessage(groupId, JsonUtil.mapJSON(m));
			persistData(m,groupId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 连接关闭时执行
	@OnClose
	public void onClose(@PathParam(value = "groupId") String groupId,Session session, CloseReason closeReason) {
		logger.info("====================客户端:" + session.getId() + "连接关闭====================");
		try {
			session.close(); // 关闭处理
			Map<String, Session> client = groupMap.get(groupId);
			if(client!=null){
				client.remove(session.getId());
			}
			if(client==null||(client!=null&&client.isEmpty())){
				groupMap.remove(groupId);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 连接错误时执行
	@OnError
	public void onError(@PathParam(value = "groupId") String groupId,Throwable t) {
		logger.info("====================客户端:" + session.getId() + "连接关闭====================");
		try {
			session.close(); // 关闭处理
			Map<String, Session> client = groupMap.get(groupId);
			if(client!=null){
				client.remove(session.getId());
			}
			if(client==null||(client!=null&&client.isEmpty())){
				groupMap.remove(groupId);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	t.printStackTrace();
	}

	// 发送消息
	public void sendMessage(String message) throws IOException {

		this.session.getAsyncRemote().sendText(message);
	}

	// 群发消息
	public void sendAllMessage(String groupId, String message) throws Exception {

		Map<String, Session> clients = groupMap.get(groupId);
		if (clients == null)
			throw new Exception("客户端获取失败");

		Set<String> keys = clients.keySet();
		for (String k : keys) {
			Session s = clients.get(k);
			if (s.isOpen()) {
				try {
					s.getAsyncRemote().sendText(message);
					logger.info("群发成功！");
				} catch (Exception e) {
					logger.info("群发失败！");
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 将数据传回客户端 异步的方式
	 * 
	 * @param myWebsocket
	 * @param message
	 */
	public static void broadcast(String myWebsocket, String message) {
		// if (clients.containsKey(myWebsocket)) {
		// clients.get(myWebsocket).getAsyncRemote().sendText(message);
		// } else {
		// throw new NullPointerException("[" + myWebsocket
		// + "]Connection does not exist");
		// }
	}

	//持久化数据
	private void persistData(Map<String, Object> m ,String sourceId){
		m.put("sourceId", sourceId);
		CommentMsgQueue.instance().getQueue().offer(m);
	}
	
}
