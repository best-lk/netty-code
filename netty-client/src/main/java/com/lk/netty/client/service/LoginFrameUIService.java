package com.lk.netty.client.service;

import com.lk.netty.client.ui.LoginFrame;
import com.lk.netty.client.ui.MainFrame;

/**
 *  登录界面服务
 * 2019年3月27日
 * likai
 */
public class LoginFrameUIService {
	
	private static LoginFrameUIService INSTANCE = new LoginFrameUIService();
	
	private LoginFrameUIService() {
		
	}
	
	public static LoginFrameUIService getINSTANCE() {
		if(INSTANCE == null) {
			return new LoginFrameUIService();
		}else {
			return INSTANCE;
		}
	}
	
	/**
	 * 用户登录成功
	 * 
	 * 2019年3月27日
	 * likai
	 */
	public void loginSuccess() {
		LoginFrame.frame.setVisible(false);
		MainFrame.mainFrameStartup();
	}
	
	/**
	 * 用户登录失败
	 * 
	 * 2019年3月27日
	 * likai
	 */
	public void loginFaild(String msg) {
		LoginFrame.errorLabel.setText(msg);
	}
}
