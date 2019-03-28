package com.lk.netty.client.service;

import com.lk.netty.client.ui.LoginFrame;
import com.lk.netty.client.ui.MainFrame;

/**
 *  ��¼�������
 * 2019��3��27��
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
	 * �û���¼�ɹ�
	 * 
	 * 2019��3��27��
	 * likai
	 */
	public void loginSuccess() {
		LoginFrame.frame.setVisible(false);
		MainFrame.mainFrameStartup();
	}
	
	/**
	 * �û���¼ʧ��
	 * 
	 * 2019��3��27��
	 * likai
	 */
	public void loginFaild(String msg) {
		LoginFrame.errorLabel.setText(msg);
	}
}
