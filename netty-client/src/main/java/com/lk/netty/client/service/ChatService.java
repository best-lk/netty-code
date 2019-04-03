package com.lk.netty.client.service;

import com.lk.netty.client.packet.req.ReqChatToSingle;
import com.lk.netty.client.session.SessionManager;

/**
 * �������
 * @author likai
 * 2019��4��3��
 */
public class ChatService {
	
	/**
	 *  ��������
	 * @author likai
	 * 2019��4��3��
	 * @param req
	 */
	public static void sendMessageToSingle(ReqChatToSingle req) {
		SessionManager.INSTANCE.sendMessage(req);
	}
}
