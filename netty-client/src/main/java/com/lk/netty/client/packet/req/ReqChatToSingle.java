package com.lk.netty.client.packet.req;

import com.lk.netty.client.packet.AbstractPacket;
import com.lk.netty.client.packet.PacketType;

/**
 *  向一个人发送消息
 * @author likai
 * 2019年4月3日
 */
public class ReqChatToSingle extends AbstractPacket{

	private String userId;
	
	private String message;
	
	@Override
	public PacketType getPacketType() {
		return PacketType.ReqChatToSingle;
	}

	@Override
	public void execPacket() {
		// TODO Auto-generated method stub
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
