package com.lk.netty.client.service;

import com.lk.netty.client.packet.req.ReqChatToSingle;
import com.lk.netty.client.packet.res.ResChatToSingle;
import com.lk.netty.client.session.SessionManager;
import com.lk.netty.client.ui.MainFrame;

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
	
	/**
	 * 接受消息
	 * @author likai
	 * 2019年4月8日
	 * @param res
	 */
	public static void acceptMessageFromSingle(ResChatToSingle res) {
		//MainFrame.showSingleAcceptContent.setAlignmentX(Component.LEFT_ALIGNMENT);
		System.out.println("接收到消息："+ res.getMessage());
		MainFrame.showSingleAcceptContent.append("\n"+res.getFromUserName() + ":");
		MainFrame.showSingleAcceptContent.append("\n"+res.getMessage());
		MainFrame.showSingleAcceptContent.setCaretPosition(MainFrame.showSingleAcceptContent.getText().length());
	}
}
