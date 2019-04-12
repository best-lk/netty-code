package com.lk.netty.client.service;

import com.lk.netty.client.packet.req.ReqChatToSingle;
import com.lk.netty.client.packet.res.ResChatToSingle;
import com.lk.netty.client.session.SessionManager;
import com.lk.netty.client.ui.MainFrame;

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
	
	/**
	 * ������Ϣ
	 * @author likai
	 * 2019��4��8��
	 * @param res
	 */
	public static void acceptMessageFromSingle(ResChatToSingle res) {
		//MainFrame.showSingleAcceptContent.setAlignmentX(Component.LEFT_ALIGNMENT);
		System.out.println("���յ���Ϣ��"+ res.getMessage());
		MainFrame.showSingleAcceptContent.append("\n"+res.getFromUserName() + ":");
		MainFrame.showSingleAcceptContent.append("\n"+res.getMessage());
		MainFrame.showSingleAcceptContent.setCaretPosition(MainFrame.showSingleAcceptContent.getText().length());
	}
}
