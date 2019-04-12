package com.lk.netty.client.ui.listener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.lk.netty.client.service.LoginServiceManager;

/**
 * 主窗口监听事件
 * @author likai
 * 2019年4月12日
 */
public class MainFrameListener implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("关闭窗口");
		//退出登录
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
