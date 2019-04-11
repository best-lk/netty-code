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
 * 单人聊天发送消息监听
 * @author likai
 * 2019年4月3日
 */
public class SingleSendMessageListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		User localUser = LocalUserLoginInfo.getUserInfo();
		if(localUser == null) {
			System.out.println("请用户登录！");
			return;
		}
		//1：获取当前选择的人
		String selectValue = MainFrame.jSingleList.getSelectedValue();
		if(selectValue == null) {
			MainFrame.showSingleAcceptContent.append("请选择聊天对象");
			return;
		}
		String userId = selectValue.split("-")[1];
		//2：获取发送区的内容
		String message = MainFrame.sendSingleContent.getText();
		//3：发送消息
		ReqChatToSingle req = new ReqChatToSingle();
		req.setMessage(message);
		req.setToUserId(userId);
		req.setFromUserId(localUser.getUserId());
		ChatService.sendMessageToSingle(req);
		//4：显示区显示发送的内容
		//MainFrame.showSingleAcceptContent.setAlignmentX(Component.RIGHT_ALIGNMENT);
		MainFrame.showSingleAcceptContent.append("\n"+ localUser.getUserName() + ":");
		MainFrame.showSingleAcceptContent.append("\n"+message);
		MainFrame.sendSingleContent.setText("");
	}

}
