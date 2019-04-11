package com.lk.netty.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.lk.netty.server.constant.ResponseMsg;
import com.lk.netty.server.info.User;
import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.manager.SessionManager;
import com.lk.netty.server.packet.res.NotifyOnlineRes;
import com.lk.netty.server.packet.res.ResUserLogin;

/**
 *  用户登录服务
 * 2019年3月26日
 * likai
 */
public class UserLoginService {
	
	/**
	 * 用户登录
	 * @param user
	 * 2019年3月26日
	 * likai
	 */
	public static void login(User user, IoSession session) {
		ResUserLogin res = new ResUserLogin();
		//校验密码
		boolean isSuccess = true;
		if(user.getPassword().equals("123456")) {
			res.setCode(ResponseMsg.SUCCESS);
			res.setMessage("登录成功！");
			res.setUserId(user.getUserId());
			res.setUserName(user.getUserName());
			session.setUser(user);
			SessionManager.INSTANCE.registerSession(user, session);
			UserInfoService.addOnlineUser(user);
		}else {
			isSuccess = false;
			res.setCode(ResponseMsg.FAILD);
			res.setMessage("登录失败！");
		}
		SessionManager.INSTANCE.sendPacketTo(session, res);
		
		//登录成功后通知所有在线用户
		if(isSuccess) {
			UserInfoService.notifyAllUserLogin();
		}
	}
}
