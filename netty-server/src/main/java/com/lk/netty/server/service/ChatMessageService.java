package com.lk.netty.server.service;

import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.manager.SessionManager;
import com.lk.netty.server.packet.res.ResChatToSingle;

/**
 *  ������Ϣ����
 * @author likai
 * 2019��4��8��
 */
public class ChatMessageService {

	/**
	 * ������Ϣ��ָ����
	 * @author likai
	 * 2019��4��8��
	 * @param session
	 * @param userId
	 * @param message
	 */
	public static void sendMessage(IoSession session, String fromUserId, String toUserId, String message) {
		IoSession toUser = SessionManager.INSTANCE.getSessionBy(toUserId);
		IoSession fromUser = SessionManager.INSTANCE.getSessionBy(fromUserId);
		if(toUser == null) {
			return ;
		}
		if(fromUser == null) {
			return ;
		}
		//������Ϣ
		ResChatToSingle res = new ResChatToSingle();
		res.setMessage(message);
		res.setFromUserId(fromUserId);
		res.setFromUserName(fromUser.getUser().getUserName());
		res.setToUserId(toUserId);
		res.setToUserName(toUser.getUser().getUserName());
		SessionManager.INSTANCE.sendPacketTo(toUser, res);
	}
	
	
}
