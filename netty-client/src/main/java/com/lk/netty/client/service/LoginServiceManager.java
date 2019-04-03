package com.lk.netty.client.service;

import java.util.UUID;

import com.lk.netty.client.constant.ResponseMsg;
import com.lk.netty.client.exception.BusinessException;
import com.lk.netty.client.packet.req.ReqUserInfo;
import com.lk.netty.client.packet.req.ReqUserLogin;
import com.lk.netty.client.packet.res.ResUserLogin;
import com.lk.netty.client.session.SessionManager;

/**
 *  ��¼����
 * 2019��3��27��
 * likai
 */
public class LoginServiceManager {
	
	private static LoginServiceManager INSTANCE = new LoginServiceManager();
	
	private LoginServiceManager() {
		
	}

	public static LoginServiceManager getINSTANCE() {
		if(INSTANCE != null) {
			return INSTANCE;
		}else {
			return new LoginServiceManager();
		}
	}
	
	/**
	 *  ��¼
	 * @param userName
	 * @param password
	 * @param userId
	 * 2019��3��27��
	 * likai
	 */
	public void LoginReq(String userName, String password) {
		ReqUserLogin login = new ReqUserLogin();
		login.setUserId(UUID.randomUUID().toString());
		login.setUserName(userName);
		login.setPassword(password);
		SessionManager.INSTANCE.sendMessage(login);
	}
	
	/**
	 *  ��¼��Ļص�����
	 * @param res
	 * 2019��3��27��
	 * likai
	 */
	public void LoginResHandler(ResUserLogin res) {
		if(res == null) {
			throw new BusinessException("��¼�쳣��");
		}
		//��¼�ɹ�
		if(res.getCode().equals(ResponseMsg.SUCCESS)) {
			LoginFrameUIService.getINSTANCE().loginSuccess();
			ReqUserInfo userInfo = new ReqUserInfo();
			//���������û�����
			SessionManager.INSTANCE.sendMessage(userInfo);
			//String[] personalData = new String[] {"��ϵ��1","��ϵ��2","��ϵ��3","��ϵ��4","��ϵ��1","��ϵ��2","��ϵ��3","��ϵ��4","��ϵ��1","��ϵ��2","��ϵ��3","��ϵ��4","��ϵ��1","��ϵ��2","��ϵ��3","��ϵ��4","��ϵ��1","��ϵ��2","��ϵ��3","��ϵ��4","��ϵ��1","��ϵ��2","��ϵ��3","��ϵ��4","��ϵ��1","��ϵ��2","��ϵ��3","��ϵ��4","��ϵ��1","��ϵ��2","��ϵ��3","��ϵ��4","��ϵ��1","��ϵ��2","��ϵ��3","��ϵ��4","��ϵ��1","��ϵ��2","��ϵ��3","��ϵ��4","��ϵ��1","��ϵ��2","��ϵ��3","��ϵ��4","��ϵ��1","��ϵ��2","��ϵ��3","��ϵ��4"};
			//String[] groupData = new String[] {"��ϵ��11","��ϵ��22","��ϵ��33","��ϵ��44"};
			//MainFrameUIService.getINSTANCE().refreshShowData(personalData, groupData);
		}else {
			//����������ʾ
			LoginFrameUIService.getINSTANCE().loginFaild(res.getMessage());
		}
	}
}
