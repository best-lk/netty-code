package com.lk.netty.client.service;

import java.util.UUID;

import com.lk.netty.client.constant.ResponseMsg;
import com.lk.netty.client.exception.BusinessException;
import com.lk.netty.client.packet.req.ReqUserInfo;
import com.lk.netty.client.packet.req.ReqUserLogin;
import com.lk.netty.client.packet.req.User;
import com.lk.netty.client.packet.res.ResUserLogin;
import com.lk.netty.client.session.SessionManager;

/**
 *  登录服务
 * 2019年3月27日
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
	 *  登录
	 * @param userName
	 * @param password
	 * @param userId
	 * 2019年3月27日
	 * likai
	 */
	public void LoginReq(String userName, String password) {
		ReqUserLogin login = new ReqUserLogin();
		login.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
		login.setUserName(userName);
		login.setPassword(password);
		SessionManager.INSTANCE.sendMessage(login);
	}
	
	/**
	 *  登录后的回调处理
	 * @param res
	 * 2019年3月27日
	 * likai
	 */
	public void LoginResHandler(ResUserLogin res) {
		if(res == null) {
			throw new BusinessException("登录异常！");
		}
		//登录成功
		if(res.getCode().equals(ResponseMsg.SUCCESS)) {
			LoginFrameUIService.getINSTANCE().loginSuccess();
			//ReqUserInfo userInfo = new ReqUserInfo();
			//请求在线用户数据
			//SessionManager.INSTANCE.sendMessage(userInfo);
			User user = new User();
			user.setUserId(res.getUserId());
			user.setUserName(res.getUserName());
			LocalUserLoginInfo.setUserInfo(user);
		}else {
			//弹出错误提示
			LoginFrameUIService.getINSTANCE().loginFaild(res.getMessage());
		}
	}
}
