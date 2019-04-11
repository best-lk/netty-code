package com.lk.netty.client.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.lk.netty.client.constant.ResponseMsg;
import com.lk.netty.client.packet.req.User;
import com.lk.netty.client.packet.res.ResUserInfo;

/**
 *  用户数据服务
 * @author likai
 * 2019年4月3日
 */
public class UserInfoService {

	/**
	 *  获取在线用户数据
	 * @author likai
	 * 2019年4月3日
	 * @param resUserInfo
	 */
	public static void getOnlineData(ResUserInfo res) {
		User localUser = LocalUserLoginInfo.getUserInfo();
		if(localUser == null) {
			System.out.println("用户未登录！");
			return ;
		}
		//返回成功
		if(res.getCode().equals(ResponseMsg.SUCCESS)) {
			List<User> onlinePerson = JSONArray.parseArray(res.getArrayJson(), User.class);
			List<String> personalData = new ArrayList<>();
			for(User user : onlinePerson) {
				if(user.getUserId().equals(localUser.getUserId())) {
					continue;
				}
				personalData.add(user.getUserName()+"-"+user.getUserId());
			}
			MainFrameUIService.getINSTANCE().refreshShowData(personalData, null);
		}else {
			//失败的处理逻辑
			
		}
	}
	
	
}
