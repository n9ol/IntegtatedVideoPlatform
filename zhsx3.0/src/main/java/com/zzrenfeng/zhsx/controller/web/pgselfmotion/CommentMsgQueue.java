package com.zzrenfeng.zhsx.controller.web.pgselfmotion;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CommentMsgQueue {
	private static class Holder {
		private static final CommentMsgQueue cmq = new CommentMsgQueue();
	}

	private CommentMsgQueue() {
	}

	private Queue<Map<String, Object>> queue = new ConcurrentLinkedQueue<Map<String, Object>>();

	public static final CommentMsgQueue instance() {
		return Holder.cmq;
	}

	public Queue<Map<String, Object>> getQueue() {
		return queue;
	}

}
