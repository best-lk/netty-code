package com.lk.netty.server.service;

import com.lk.netty.server.constant.ResponseMsg;
import com.lk.netty.server.info.User;
import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.manager.SessionManager;
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
		if(user.getPassword().equals("123456")) {
			res.setCode(ResponseMsg.SUCCESS);
			res.setMessage("登录成功！");
			session.setUser(user);
		}else {
			res.setCode(ResponseMsg.FAILD);
			res.setMessage("登录失败！");
		}
		SessionManager.INSTANCE.sendPacketTo(session, res);
	}
}
