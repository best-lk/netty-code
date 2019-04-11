package com.lk.netty.client.service;

import java.util.HashMap;
import java.util.Map;

import com.lk.netty.client.packet.req.User;

/**
 *  客户端缓存的用户信息
 * @author likai
 * 2019年4月11日
 */
public class LocalUserLoginInfo {
	
	private static final String USER_INFO = "user_info";
	
	private static Map<String, Object> infoMap = new HashMap<>();
	
	/**
	 * 缓存用户信息
	 * @author likai
	 * 2019年4月11日
	 * @param user
	 */
	public static void setUserInfo(User user) {
		infoMap.put(USER_INFO, user);
	}
	
	/**
	 * 获取用户信息
	 * @author likai
	 * 2019年4月11日
	 * @return
	 */
	public static User getUserInfo() {
		return (User)infoMap.get(USER_INFO);
	}
}
