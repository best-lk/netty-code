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
 *  用户数据管理服务
 * @author likai
 * 2019年4月3日
 */
public class UserInfoService {
	
	/**
	 * 在线用户缓存
	 */
	private static ConcurrentMap<String, User> onlineUser = new ConcurrentHashMap<>(); 
	
	/**
	 *  获取在线用户
	 * @author likai
	 * 2019年4月3日
	 * @return
	 */
	public static List<User> getOnlineUser(){
		return new ArrayList<>(onlineUser.values());
	}
	
	/**
	 *  删除在线用户
	 * @author likai
	 * 2019年4月3日
	 * @param userId
	 */
	public static void removeOnlineUser(String userId) {
		onlineUser.remove(userId);
	}
	
	/**
	 * 添加在线用户
	 * @author likai
	 * 2019年4月3日
	 */
	public static void addOnlineUser(User user) {
		onlineUser.put(user.getUserId(), user);
	}

	/**
	 * 发送在线用户数据
	 * @author likai
	 * 2019年4月3日
	 * @param session
	 */
	public static void sendOnlineUser(IoSession session) {
		ResUserInfo pact = new ResUserInfo();
		pact.setCode(ResponseMsg.SUCCESS);
		pact.setMessage("获取数据成功！");
		pact.setArrayJson(JSONArray.toJSONString(getOnlineUser()));
		SessionManager.INSTANCE.sendPacketTo(session, pact);
	}
	
	/**
	 * 向所有在线用户推送上线通知
	 * @author likai
	 * 2019年4月11日
	 */
	public static void notifyAllUserLogin() {
		ResUserInfo pact = new ResUserInfo();
		pact.setCode(ResponseMsg.SUCCESS);
		pact.setMessage("获取数据成功！");
		pact.setArrayJson(JSONArray.toJSONString(getOnlineUser()));
		SessionManager.INSTANCE.notifyToAllOnlineUsers(pact);
	}
}
