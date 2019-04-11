package com.lk.netty.client.packet.res;

import com.lk.netty.client.packet.AbstractPacket;
import com.lk.netty.client.packet.PacketType;
import com.lk.netty.client.service.LoginServiceManager;

import io.netty.buffer.ByteBuf;

public class ResUserLogin extends AbstractPacket{

	private Integer code;
	
	private String message;
	
	private String onlineRemind;
	
	private String userId;
	
	private String userName;
	
	@Override
	public PacketType getPacketType() {
		return PacketType.ResUserLogin;
	}

	@Override
	public void execPacket() {
		LoginServiceManager.getINSTANCE().LoginResHandler(this);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public void writeBody(ByteBuf buf) {
		buf.writeInt(code);
		writeUTF8(buf, message);
		writeUTF8(buf, onlineRemind);
		writeUTF8(buf, userId);
		writeUTF8(buf, userName);
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.code = buf.readInt();
		this.message = readUTF8(buf);
		this.onlineRemind = readUTF8(buf);
		this.userId = readUTF8(buf);
		this.userName = readUTF8(buf);
	}

	public String getOnlineRemind() {
		return onlineRemind;
	}

	public void setOnlineRemind(String onlineRemind) {
		this.onlineRemind = onlineRemind;
	}

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
}
