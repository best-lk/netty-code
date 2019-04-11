package com.lk.netty.client.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.lk.netty.client.packet.req.ReqChatToSingle;
import com.lk.netty.client.packet.req.User;
import com.lk.netty.client.service.ChatService;
import com.lk.netty.client.service.LocalUserLoginInfo;
import com.lk.netty.client.ui.MainFrame;

import io.netty.util.internal.StringUtil;

/**
 * �������췢����Ϣ����
 * @author likai
 * 2019��4��3��
 */
public class SingleSendMessageListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		User localUser = LocalUserLoginInfo.getUserInfo();
		if(localUser == null) {
			System.out.println("���û���¼��");
			return;
		}
		//1����ȡ��ǰѡ�����
		String selectValue = MainFrame.jSingleList.getSelectedValue();
		if(selectValue == null) {
			MainFrame.showSingleAcceptContent.append("��ѡ���������");
			return;
		}
		String userId = selectValue.split("-")[1];
		//2����ȡ������������
		String message = MainFrame.sendSingleContent.getText();
		//3��������Ϣ
		ReqChatToSingle req = new ReqChatToSingle();
		req.setMessage(message);
		req.setToUserId(userId);
		req.setFromUserId(localUser.getUserId());
		ChatService.sendMessageToSingle(req);
		//4����ʾ����ʾ���͵�����
		//MainFrame.showSingleAcceptContent.setAlignmentX(Component.RIGHT_ALIGNMENT);
		MainFrame.showSingleAcceptContent.append("\n"+ localUser.getUserName() + ":");
		MainFrame.showSingleAcceptContent.append("\n"+message);
		MainFrame.sendSingleContent.setText("");
	}

}
