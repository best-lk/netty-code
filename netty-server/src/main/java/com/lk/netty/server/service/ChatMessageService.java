package com.lk.netty.server.service;

import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.manager.SessionManager;
import com.lk.netty.server.packet.res.ResChatToSingle;

/**
 *  聊天消息服务
 * @author likai
 * 2019年4月8日
 */
public class ChatMessageService {

	/**
	 * 发送消息给指定人
	 * @author likai
	 * 2019年4月8日
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
		//发送消息
		ResChatToSingle res = new ResChatToSingle();
		res.setMessage(message);
		res.setFromUserId(fromUserId);
		res.setFromUserName(fromUser.getUser().getUserName());
		res.setToUserId(toUserId);
		res.setToUserName(toUser.getUser().getUserName());
		SessionManager.INSTANCE.sendPacketTo(toUser, res);
	}
	
	
}
