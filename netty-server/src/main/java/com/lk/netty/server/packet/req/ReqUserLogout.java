package com.lk.netty.server.packet.req;

import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.packet.AbstractPacket;
import com.lk.netty.server.packet.PacketType;
import com.lk.netty.server.service.UserLoginService;

import io.netty.buffer.ByteBuf;

/**
 *  退出登录
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
	public void execPacket(IoSession session) {
		UserLoginService.logout(session, this);
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
