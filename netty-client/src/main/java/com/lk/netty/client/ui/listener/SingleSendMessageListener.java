package com.lk.netty.client.ui.listener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.lk.netty.client.packet.req.ReqChatToSingle;
import com.lk.netty.client.service.ChatService;
import com.lk.netty.client.session.SessionManager;
import com.lk.netty.client.ui.MainFrame;

/**
 * 单人聊天发送消息监听
 * @author likai
 * 2019年4月3日
 */
public class SingleSendMessageListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		//1：获取当前选择的人
		String selectValue = (String)MainFrame.personDataModel.getSelectedItem();
		String userId = selectValue.split("-")[1];
		//2：获取发送区的内容
		String message = MainFrame.sendSingleContent.getText();
		//3：发送消息
		ReqChatToSingle req = new ReqChatToSingle();
		req.setMessage(message);
		req.setUserId(userId);
		//ChatService.sendMessageToSingle(req);
		//4：显示区显示发送的内容
		MainFrame.showSingleAcceptContent.setFont(new Font("宋体",Font.BOLD,24));
		MainFrame.showSingleAcceptContent.append("\n"+selectValue.split("-")[0]);
		MainFrame.showSingleAcceptContent.setFont(new Font("宋体",Font.PLAIN,18));
		MainFrame.showSingleAcceptContent.append("\n"+message);
	}

}
