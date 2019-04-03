package com.lk.netty.client.ui.listener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.lk.netty.client.packet.req.ReqChatToSingle;
import com.lk.netty.client.service.ChatService;
import com.lk.netty.client.session.SessionManager;
import com.lk.netty.client.ui.MainFrame;

/**
 * �������췢����Ϣ����
 * @author likai
 * 2019��4��3��
 */
public class SingleSendMessageListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		//1����ȡ��ǰѡ�����
		String selectValue = (String)MainFrame.personDataModel.getSelectedItem();
		String userId = selectValue.split("-")[1];
		//2����ȡ������������
		String message = MainFrame.sendSingleContent.getText();
		//3��������Ϣ
		ReqChatToSingle req = new ReqChatToSingle();
		req.setMessage(message);
		req.setUserId(userId);
		//ChatService.sendMessageToSingle(req);
		//4����ʾ����ʾ���͵�����
		MainFrame.showSingleAcceptContent.setFont(new Font("����",Font.BOLD,24));
		MainFrame.showSingleAcceptContent.append("\n"+selectValue.split("-")[0]);
		MainFrame.showSingleAcceptContent.setFont(new Font("����",Font.PLAIN,18));
		MainFrame.showSingleAcceptContent.append("\n"+message);
	}

}
