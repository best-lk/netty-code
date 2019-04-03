package com.lk.netty.client.service;

import java.util.List;

import com.lk.netty.client.ui.MainFrame;

/**
 * 主界面服务
 * 2019年3月27日
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
	 * 刷新联系人列表
	 * 
	 * 2019年3月27日
	 * likai
	 */
	public void refreshShowData(List<String> personalData, List<String> groupData) {
		if(personalData != null) {			
			for(String personal : personalData) {
				MainFrame.personDataModel.addElement(personal);			
			}
		}
		if(groupData != null) {			
			for(String group : groupData) {
				MainFrame.groupDataModel.addElement(group);
			}
		}
	}
}
