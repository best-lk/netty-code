package com.lk.netty.client.ui.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.lk.netty.client.service.LoginServiceManager;

/**
 * �����ڼ����¼�
 * @author likai
 * 2019��4��12��
 */
public class MainFrameListener implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("�رմ���");
		//�˳���¼
		LoginServiceManager.getINSTANCE().loginOut();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
