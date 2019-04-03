package com.lk.netty.client.service;

import com.lk.netty.client.packet.req.ReqChatToSingle;
import com.lk.netty.client.session.SessionManager;

/**
 * 聊天服务
 * @author likai
 * 2019年4月3日
 */
public class ChatService {
	
	/**
	 *  单人聊天
	 * @author likai
	 * 2019年4月3日
	 * @param req
	 */
	public static void sendMessageToSingle(ReqChatToSingle req) {
		SessionManager.INSTANCE.sendMessage(req);
	}
}
