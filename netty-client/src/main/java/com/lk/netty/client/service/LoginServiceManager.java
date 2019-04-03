package com.lk.netty.client.service;

import java.util.UUID;

import com.lk.netty.client.constant.ResponseMsg;
import com.lk.netty.client.exception.BusinessException;
import com.lk.netty.client.packet.req.ReqUserInfo;
import com.lk.netty.client.packet.req.ReqUserLogin;
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
		login.setUserId(UUID.randomUUID().toString());
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
			ReqUserInfo userInfo = new ReqUserInfo();
			//请求在线用户数据
			SessionManager.INSTANCE.sendMessage(userInfo);
			//String[] personalData = new String[] {"联系人1","联系人2","联系人3","联系人4","联系人1","联系人2","联系人3","联系人4","联系人1","联系人2","联系人3","联系人4","联系人1","联系人2","联系人3","联系人4","联系人1","联系人2","联系人3","联系人4","联系人1","联系人2","联系人3","联系人4","联系人1","联系人2","联系人3","联系人4","联系人1","联系人2","联系人3","联系人4","联系人1","联系人2","联系人3","联系人4","联系人1","联系人2","联系人3","联系人4","联系人1","联系人2","联系人3","联系人4","联系人1","联系人2","联系人3","联系人4"};
			//String[] groupData = new String[] {"联系人11","联系人22","联系人33","联系人44"};
			//MainFrameUIService.getINSTANCE().refreshShowData(personalData, groupData);
		}else {
			//弹出错误提示
			LoginFrameUIService.getINSTANCE().loginFaild(res.getMessage());
		}
	}
}
