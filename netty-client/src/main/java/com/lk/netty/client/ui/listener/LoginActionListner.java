package com.lk.netty.client.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.lk.netty.client.packet.req.ReqUserLogin;
import com.lk.netty.client.service.LoginServiceManager;
import com.lk.netty.client.session.SessionManager;
import com.lk.netty.client.ui.MainFrame;

/**
 * 登录按钮事件
 * 2019年3月15日
 * likai
 */
public class LoginActionListner implements ActionListener {

	private JFrame frame;
	
	private JPasswordField password;
	
	private JTextField userName;
	
	public LoginActionListner(JFrame frame, JTextField userName, JPasswordField password) {
		this.frame = frame;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//请求服务端登录
		LoginServiceManager.getINSTANCE().LoginReq(userName.getText(), password.getText());
	}
}
