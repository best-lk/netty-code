package com.lk.netty.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.alibaba.fastjson.JSONArray;
import com.lk.netty.server.constant.ResponseMsg;
import com.lk.netty.server.info.User;
import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.manager.SessionManager;
import com.lk.netty.server.packet.res.ResUserInfo;

/**
 *  �û����ݹ������
 * @author likai
 * 2019��4��3��
 */
public class UserInfoService {
	
	/**
	 * �����û�����
	 */
	private static ConcurrentMap<String, User> onlineUser = new ConcurrentHashMap<>(); 
	
	/**
	 *  ��ȡ�����û�
	 * @author likai
	 * 2019��4��3��
	 * @return
	 */
	public static List<User> getOnlineUser(){
		return new ArrayList<>(onlineUser.values());
	}
	
	/**
	 *  ɾ�������û�
	 * @author likai
	 * 2019��4��3��
	 * @param userId
	 */
	public static void removeOnlineUser(String userId) {
		onlineUser.remove(userId);
	}
	
	/**
	 * ��������û�
	 * @author likai
	 * 2019��4��3��
	 */
	public static void addOnlineUser(User user) {
		onlineUser.put(user.getUserId(), user);
	}

	/**
	 * ���������û�����
	 * @author likai
	 * 2019��4��3��
	 * @param session
	 */
	public static void sendOnlineUser(IoSession session) {
		ResUserInfo pact = new ResUserInfo();
		pact.setCode(ResponseMsg.SUCCESS);
		pact.setMessage("��ȡ���ݳɹ���");
		pact.setArrayJson(JSONArray.toJSONString(getOnlineUser()));
		SessionManager.INSTANCE.sendPacketTo(session, pact);
	}
	
	/**
	 * �����������û���������֪ͨ
	 * @author likai
	 * 2019��4��11��
	 */
	public static void notifyAllUserLogin() {
		ResUserInfo pact = new ResUserInfo();
		pact.setCode(ResponseMsg.SUCCESS);
		pact.setMessage("��ȡ���ݳɹ���");
		pact.setArrayJson(JSONArray.toJSONString(getOnlineUser()));
		SessionManager.INSTANCE.notifyToAllOnlineUsers(pact);
	}
}
