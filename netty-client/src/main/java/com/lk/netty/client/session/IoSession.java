package com.lk.netty.client.session;

import com.lk.netty.client.packet.AbstractPacket;

import io.netty.channel.Channel;

/**
 * ����������Ϣ
 * 2019��3��8��
 * likai
 */
public class IoSession {
	
	/** ��������channel */
	private Channel channel;

	private String userName;

	private String userId;

	public IoSession() {

	}

	public IoSession(Channel channel) {
		this.channel = channel;
	}
	
	public IoSession(Channel channel, String userName, String userId) {
		this.channel = channel;
		this.userId = userId;
		this.userName = userName;
	}
	
	
	/**
	 * ��ͻ��˷�����Ϣ
	 * @param packet
	 */
	public void sendPacket(AbstractPacket packet) {
		if (packet == null) {
			return;
		}
		if (channel != null) {
			channel.writeAndFlush(packet);
		}
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
