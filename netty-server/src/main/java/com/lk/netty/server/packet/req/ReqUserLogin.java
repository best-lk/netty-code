package com.lk.netty.server.packet.req;

import com.lk.netty.server.info.User;
import com.lk.netty.server.io.util.IoSession;
import com.lk.netty.server.packet.AbstractPacket;
import com.lk.netty.server.packet.PacketType;
import com.lk.netty.server.service.UserLoginService;

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
	public void execPacket(IoSession session) {
		System.out.println("登录业务处理！" +session.toString());
		User user = new User(userId, userName, password);
		//并且向客户端回应
		UserLoginService.login(user, session);
	}
	
}
