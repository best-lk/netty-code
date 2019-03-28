package com.lk.netty.client.ui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.lk.netty.client.Client;
import com.lk.netty.client.ui.listener.LoginActionListner;

/**
 *  登录界面
 * 2019年3月15日
 * likai
 */
public class LoginFrame {
	
	public static JFrame frame = new JFrame("用户登录");
	public static JLabel errorLabel = new JLabel();
	
	public static void main(String[] args) {
		
		frame.setSize(500, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		
		placeComponents(panel, frame);
		frame.setVisible(true);
		
		new Client().start();
	}

	/**
	 * 构建面板
	 * @param panel
	 * 2019年3月15日
	 * likai
	 */
	private static void placeComponents(JPanel panel, JFrame frame) {
		
		panel.setLayout(null);
		
		JLabel userLabel = new JLabel("用户名：");
		userLabel.setBounds(50, 50, 100, 30);
		panel.add(userLabel);
		
		JTextField userField = new JTextField(20);
		userField.setBounds(150, 50, 300, 30);
		panel.add(userField);
		
		JLabel passwordLabel = new JLabel("密码：");
		passwordLabel.setBounds(50, 150, 100, 30);
		panel.add(passwordLabel);
		
		JPasswordField passwordField = new JPasswordField(20);
		passwordField.setBounds(150, 150, 300, 30);
		panel.add(passwordField);
		
		errorLabel.setBounds(100, 300, 100, 30);
		errorLabel.setForeground(Color.red);
		panel.add(errorLabel);
		
		JButton loginButton = new JButton("登录");
		loginButton.setBounds(200, 250, 100, 50);
		loginButton.addActionListener(new LoginActionListner(frame, userField, passwordField));
		panel.add(loginButton);
	}
}
