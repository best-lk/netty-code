package com.lk.netty.client.packet.res;

import com.lk.netty.client.packet.AbstractPacket;
import com.lk.netty.client.packet.PacketType;
import com.lk.netty.client.service.UserInfoService;

import io.netty.buffer.ByteBuf;

/**
 *  获取在线用户信息
 * @author likai
 * 2019年4月3日
 */
public class ResUserInfo extends AbstractPacket{

	//返回json数组字符串
	private String arrayJson;
	//状态码
	private Integer code;
	//返回描述
	private String message;
	
	@Override
	public PacketType getPacketType() {
		return PacketType.ResUserInfo;
	}

	@Override
	public void execPacket() {
		UserInfoService.getOnlineData(this);
	}

	@Override
	public void writeBody(ByteBuf buf) {
		writeUTF8(buf, arrayJson);
		buf.writeInt(code);
		writeUTF8(buf, message);	
	}

	@Override
	public void readBody(ByteBuf buf) {
		this.arrayJson = readUTF8(buf);
		this.code = buf.readInt();
		this.message = readUTF8(buf);
	}
	
	public String getArrayJson() {
		return arrayJson;
	}

	public void setArrayJson(String arrayJson) {
		this.arrayJson = arrayJson;
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
}
