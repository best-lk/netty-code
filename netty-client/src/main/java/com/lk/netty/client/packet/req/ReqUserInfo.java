package com.lk.netty.client.packet.req;

import com.lk.netty.client.packet.AbstractPacket;
import com.lk.netty.client.packet.PacketType;

public class ReqUserInfo extends AbstractPacket{

	@Override
	public PacketType getPacketType() {
		return PacketType.ReqUserInfo;
	}

	@Override
	public void execPacket() {
		// TODO Auto-generated method stub
		
	}

}
