package com.lk.netty.client.service;

import com.lk.netty.client.ui.MainFrame;

/**
 * ���������
 * 2019��3��27��
 * likai
 */
public class MainFrameUIService {
	
	private static MainFrameUIService INSTANCE = new MainFrameUIService();
	
	private MainFrameUIService() {
		
	}
	
	public static MainFrameUIService getINSTANCE() {
		if(INSTANCE == null) {
			return new MainFrameUIService();
		}else {
			return INSTANCE;
		}
	}
	
	/**
	 * ˢ����ϵ���б�
	 * 
	 * 2019��3��27��
	 * likai
	 */
	public void refreshShowData(String[] personalData, String[] groupData) {
		for(String personal : personalData) {
			MainFrame.personDataModel.addElement(personal);			
		}
		for(String group : groupData) {
			
			MainFrame.groupDataModel.addElement(group);
		}
	}
}
