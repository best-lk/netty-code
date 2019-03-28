package com.lk.netty.server.service;

import com.lk.netty.server.constant.ResponseMsg;
import com.lk.netty.server.info.User;
import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.manager.SessionManager;
import com.lk.netty.server.packet.res.ResUserLogin;

/**
 *  �û���¼����
 * 2019��3��26��
 * likai
 */
public class UserLoginService {
	
	/**
	 * �û���¼
	 * @param user
	 * 2019��3��26��
	 * likai
	 */
	public static void login(User user, IoSession session) {
		ResUserLogin res = new ResUserLogin();
		//У������
		if(user.getPassword().equals("123456")) {
			res.setCode(ResponseMsg.SUCCESS);
			res.setMessage("��¼�ɹ���");
			session.setUser(user);
		}else {
			res.setCode(ResponseMsg.FAILD);
			res.setMessage("��¼ʧ�ܣ�");
		}
		SessionManager.INSTANCE.sendPacketTo(session, res);
	}
}
