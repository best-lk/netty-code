package com.lk.netty.client.packet.req;

import com.lk.netty.client.packet.AbstractPacket;
import com.lk.netty.client.packet.PacketType;

import io.netty.buffer.ByteBuf;

/**
 *  用户退出登录请求体
 * @author likai
 * 2019年4月12日
 */
public class ReqUserLogout extends AbstractPacket{

	private String userId;
	
	@Override
	public PacketType getPacketType() {
		return PacketType.ReqUserLogout;
	}

	@Override
	public void execPacket() {
		
	}

	@Override
	public void writeBody(ByteBuf buf) {
		writeUTF8(buf, userId);
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.userId = readUTF8(buf);
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
