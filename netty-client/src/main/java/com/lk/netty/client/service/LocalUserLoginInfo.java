package com.lk.netty.client.service;

import java.util.HashMap;
import java.util.Map;

import com.lk.netty.client.packet.req.User;

/**
 *  �ͻ��˻�����û���Ϣ
 * @author likai
 * 2019��4��11��
 */
public class LocalUserLoginInfo {
	
	private static final String USER_INFO = "user_info";
	
	private static Map<String, Object> infoMap = new HashMap<>();
	
	/**
	 * �����û���Ϣ
	 * @author likai
	 * 2019��4��11��
	 * @param user
	 */
	public static void setUserInfo(User user) {
		infoMap.put(USER_INFO, user);
	}
	
	/**
	 * ��ȡ�û���Ϣ
	 * @author likai
	 * 2019��4��11��
	 * @return
	 */
	public static User getUserInfo() {
		return (User)infoMap.get(USER_INFO);
	}
}
