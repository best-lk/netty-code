package com.lk.netty.client.packet.req;

import com.lk.netty.client.packet.AbstractPacket;
import com.lk.netty.client.packet.PacketType;

import io.netty.buffer.ByteBuf;

public class ReqUserLogin extends AbstractPacket{

	private String userId;
	
	private String userName;
	
	private String password;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void writeBody(ByteBuf buf) {
		writeUTF8(buf, userId);
		writeUTF8(buf, userName);
		writeUTF8(buf, password);
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.userId = readUTF8(buf);
		this.userName = readUTF8(buf);
		this.password = readUTF8(buf);
	}
	
	@Override
	public PacketType getPacketType() {
		return PacketType.ReqUserLogin;
	}

	@Override
	public void execPacket() {
		// TODO Auto-generated method stub
		
	}

}
