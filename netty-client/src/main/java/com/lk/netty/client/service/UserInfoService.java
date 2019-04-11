package com.lk.netty.client.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.lk.netty.client.constant.ResponseMsg;
import com.lk.netty.client.packet.req.User;
import com.lk.netty.client.packet.res.ResUserInfo;

/**
 *  �û����ݷ���
 * @author likai
 * 2019��4��3��
 */
public class UserInfoService {

	/**
	 *  ��ȡ�����û�����
	 * @author likai
	 * 2019��4��3��
	 * @param resUserInfo
	 */
	public static void getOnlineData(ResUserInfo res) {
		User localUser = LocalUserLoginInfo.getUserInfo();
		if(localUser == null) {
			System.out.println("�û�δ��¼��");
			return ;
		}
		//���سɹ�
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
			//ʧ�ܵĴ����߼�
			
		}
	}
	
	
}
