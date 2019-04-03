package com.lk.netty.server.packet.req;

import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.packet.AbstractPacket;
import com.lk.netty.server.packet.PacketType;
import com.lk.netty.server.service.UserInfoService;

import io.netty.buffer.ByteBuf;

public class ReqUserInfo extends AbstractPacket{

	@Override
	public PacketType getPacketType() {
		return PacketType.ReqUserInfo;
	}

	@Override
	public void execPacket(IoSession session) {
		UserInfoService.sendOnlineUser(session);
	}

	@Override
	public void writeBody(ByteBuf buf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readBody(ByteBuf buf) {
		// TODO Auto-generated method stub
		
	}

}
